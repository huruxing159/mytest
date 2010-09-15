package com.goodhope.goldselling.service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.goodhope.goldselling.domain.OperatorServerPrice;

public class OperatorUploadPriceService {
	private static final Logger LOG = Logger.getLogger(OperatorUploadPriceService.class);
	private static final int GAMEAREA_COLUMN = 0;
	private static final int GAMESERVERCLUSTER_COLUMN = 1;
	private static final int FACTION_COLUMN = 2;
	private static final int DISPLAY_PRICE_COLUMN = 3;
	private static final int PRICE_UPLIMIT_COLUMN = 4;
	private static final int GOLD_AMOUNT_DOWNLIMIT_COLUMN = 5;
	private static final int COLUMN_COUNT = 6;
	private static final List<String> GAMEAREA_LIST = Arrays.asList(new String[] { "EU", "US" });
	private static final List<String> FACTION_LIST = Arrays.asList(new String[] { "Alliance", "Horde" });

	public List<String> verify(File file) throws Exception {
		List<String> error = new ArrayList<String>();
		Sheet sheet = getSheetByFile(file);
		if (sheet.getLastRowNum() == 0) {
			error.add("请下载上传价格模板，按模板格式填写相关信息");
		} else {
			Row headRow = sheet.getRow(1);
			if (headRow.getLastCellNum() < COLUMN_COUNT) {
				error.add(String.format("应该为6列，分别为游戏区、游戏服务器、阵营、单价、价格上限、数量下限；", headRow.getRowNum()));
			} else {
				for (Row row : sheet) {
					StringBuffer sb = new StringBuffer();
					Cell country = row.getCell(GAMEAREA_COLUMN);
					if (country == null || !GAMEAREA_LIST.contains(country.getStringCellValue())) {
						sb.append(String.format("第%d列，只允许\"EU\",\"US\"；", GAMEAREA_COLUMN + 1));
					}
					Cell faction = row.getCell(FACTION_COLUMN);
					if (faction == null || !FACTION_LIST.contains(faction.getStringCellValue())) {
						sb.append(String.format("第%d列，只允许\"Alliance\"，\"Horde\"；", FACTION_COLUMN + 1));
					}
					if (verifyContentNullValue(row, GAMESERVERCLUSTER_COLUMN)) {
						sb.append(String.format("第%d列，不能为空；", GAMESERVERCLUSTER_COLUMN + 1));
					}
					verifyNumericValue(sb, row, DISPLAY_PRICE_COLUMN);
					verifyNumericValue(sb, row, PRICE_UPLIMIT_COLUMN);
					verifyLongValue(sb, row, GOLD_AMOUNT_DOWNLIMIT_COLUMN);

					if (!sb.toString().equals("")) {
						error.add(String.format("第%d行：%s", row.getRowNum() + 1, sb.toString()));
					}
				}
			}
		}
		return error;
	}

	public List<OperatorServerPrice> getServerByUploadPrice(File file) throws Exception {
		List<OperatorServerPrice> servers = new ArrayList<OperatorServerPrice>();
		Sheet sheet = getSheetByFile(file);
		for (Row row : sheet) {
			if (!verifyNullValue(row, DISPLAY_PRICE_COLUMN) || !verifyNullValue(row, PRICE_UPLIMIT_COLUMN) || !verifyNullValue(row, GOLD_AMOUNT_DOWNLIMIT_COLUMN)) {
				OperatorServerPrice server = new OperatorServerPrice(row.getCell(GAMEAREA_COLUMN).getStringCellValue(), row.getCell(GAMESERVERCLUSTER_COLUMN).getStringCellValue(), row.getCell(FACTION_COLUMN).getStringCellValue(), row.getCell(DISPLAY_PRICE_COLUMN).getStringCellValue(), row.getCell(
						PRICE_UPLIMIT_COLUMN).getStringCellValue(), String.valueOf(new BigDecimal(row.getCell(GOLD_AMOUNT_DOWNLIMIT_COLUMN).getStringCellValue()).longValue()));
				servers.add(server);
			}
		}
		return servers;
	}

	private Sheet getSheetByFile(File file) throws Exception {
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(file));
			Sheet sheet = wb.getSheetAt(0);
			sheet.removeRow(sheet.getRow(0));
			for (Row row : sheet) {
				for (Cell cell : row) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
				}
			}
			return sheet;
		} catch (Exception e) {
			LOG.error(e, e);
			throw new Exception(e);
		}
	}

	private boolean verifyNullValue(Row row, int columnNum) {
		Cell cell = row.getCell(columnNum);
		return (cell == null) || ("".equals(cell.getStringCellValue().trim())) || ("0".equals(cell.getStringCellValue().trim()));
	}

	private boolean verifyContentNullValue(Row row, int columnNum) {
		Cell cell = row.getCell(columnNum);
		return (cell == null) || ("".equals(cell.getStringCellValue().trim()));
	}

	private void verifyNumericValue(StringBuffer sb, Row row, int columnNum) {

		Cell cell = row.getCell(columnNum);
		try {
			if (cell == null || "".equals(cell.getStringCellValue().trim()) || new BigDecimal(cell.getStringCellValue()).compareTo(new BigDecimal("0")) <= 0) {
				sb.append(String.format("第%d列，只允许正数值；", columnNum + 1));
			}
		} catch (Exception e) {
			sb.append(String.format("第%d列，只允许正数值；", columnNum + 1));
		}
	}

	private void verifyLongValue(StringBuffer sb, Row row, int columnNum) {
		Cell amountLimit = row.getCell(columnNum);
		try {
			if (amountLimit == null || "".equals(amountLimit.getStringCellValue().trim()) || new BigDecimal(amountLimit.getStringCellValue()).longValue() <= 0) {
				sb.append(String.format("第%d列，只允许正数值(最大不超过%d)；", columnNum + 1, Long.MAX_VALUE));
			}
		} catch (Exception e) {
			sb.append(String.format("第%d列，只允许正数值(最大不超过%d)；", columnNum + 1, Long.MAX_VALUE));
		}
	}

}
