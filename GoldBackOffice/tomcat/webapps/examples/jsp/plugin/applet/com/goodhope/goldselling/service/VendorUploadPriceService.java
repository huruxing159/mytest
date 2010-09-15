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

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.VendorServerPrice;
import com.goodhope.goldselling.persistence.ServerDao;

public class VendorUploadPriceService {
	private static final Logger LOG = Logger.getLogger(OperatorUploadPriceService.class);
	private static final int GAMEAREA_COLUMN = 0;
	private static final int GAMESERVERCLUSTER_COLUMN = 1;
	private static final int FACTION_COLUMN = 2;
	private static final int PRICE_COLUMN = 3;
	private static final int AMOUNT_COLUMN = 4;
	private static final int COLUMN_COUNT = 5;
	private static final List<String> GAMEAREA_LIST = Arrays.asList(new String[] { "EU", "US" });
	private static final List<String> FACTION_LIST = Arrays.asList(new String[] { "Alliance", "Horde" });
	private ServerDao serverDao;

	public List<String> verify(File file) throws Exception {
		List<String> error = new ArrayList<String>();
		Sheet sheet = getSheetByFile(file);
		if (sheet.getLastRowNum() == 0) {
			error.add("请下载上传价格模板，按模板格式填写相关信息");
		} else {
			Row headRow = sheet.getRow(1);
			if (headRow.getLastCellNum() < COLUMN_COUNT) {
				error.add(String.format("第%d行：至少应该为5列，分别为游戏区、游戏服务器、阵营、价格、数量；", headRow.getRowNum()));
			} else {
				for (Row row : sheet) {
					StringBuffer sb = new StringBuffer();

					Cell country = row.getCell(GAMEAREA_COLUMN);
					if (country == null || "".equals(country.getStringCellValue()) || !GAMEAREA_LIST.contains(country.getStringCellValue())) {
						sb.append(String.format("第%d列，只允许\"EU\"，\"US\"；", GAMEAREA_COLUMN + 1));
					}

					Cell faction = row.getCell(FACTION_COLUMN);
					if (faction == null || !FACTION_LIST.contains(faction.getStringCellValue())) {
						sb.append(String.format("第%d列，只允许\"Alliance\"，\"Hord\"；", FACTION_COLUMN + 1));
					}

					Cell servername = row.getCell(GAMESERVERCLUSTER_COLUMN);
					if (verifyNullValue(row, GAMESERVERCLUSTER_COLUMN)) {
						sb.append("服务器不存在，请下载模板，根据模板指定区服上传价格；");
					}

					if (country != null && faction != null && servername != null) {
						Server server = serverDao.getServer(country.getStringCellValue(), servername.getStringCellValue(), faction.getStringCellValue());
						if (server == null) {
							sb.append("服务器不存在，请下载模板，根据模板指定区服上传价格；");
						} else {
							if (!verifyNullValue(row, PRICE_COLUMN) || !verifyNullValue(row, AMOUNT_COLUMN)) {
								verifyNumericValue(sb, row, PRICE_COLUMN, server.getPriceLimit());
								verifyLongValue(sb, row, AMOUNT_COLUMN, server.getAmountLimit());

							}
						}

					}
					if (!sb.toString().equals("")) {
						error.add(String.format("第%d行：%s", row.getRowNum() + 1, sb.toString()));
					}

				}

			}
		}
		return error;
	}

	public List<VendorServerPrice> getServerByUploadPrice(File file) throws Exception {
		List<VendorServerPrice> servers = new ArrayList<VendorServerPrice>();
		Sheet sheet = getSheetByFile(file);
		for (Row row : sheet) {
			if (!verifyNullValue(row, PRICE_COLUMN) || !verifyNullValue(row, AMOUNT_COLUMN)) {
				VendorServerPrice server = new VendorServerPrice(row.getCell(GAMEAREA_COLUMN).getStringCellValue(), row.getCell(GAMESERVERCLUSTER_COLUMN).getStringCellValue(), row.getCell(FACTION_COLUMN).getStringCellValue(), row.getCell(PRICE_COLUMN).getStringCellValue(), String
						.valueOf(new BigDecimal(row.getCell(AMOUNT_COLUMN).getStringCellValue()).longValue()));
				servers.add(server);
			}
		}
		return servers;
	}

	private boolean verifyNullValue(Row row, int columnNum) {
		Cell cell = row.getCell(columnNum);
		return (cell == null) || ("".equals(cell.getStringCellValue().trim())) || ("0".equals(cell.getStringCellValue().trim()));
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

	private void verifyNumericValue(StringBuffer sb, Row row, int columnNum, BigDecimal maxValue) {
		Cell cell = row.getCell(columnNum);
		try {
			if (maxValue == null || maxValue.compareTo(new BigDecimal(0)) <= 0) {
				sb.append(String.format("第%d列，该区服好望角运营人员还未设定最大单价；", columnNum + 1));
			} else if (cell == null || "".equals(cell.getStringCellValue()) || new BigDecimal(cell.getStringCellValue()).compareTo(maxValue) > 0) {
				sb.append(String.format("第%d列，只允许正数值(最大不超过%s)；", columnNum + 1, maxValue.toPlainString()));
			}
		} catch (Exception e) {
			sb.append(String.format("第%d列，只允许正数值(最大不超过%s)；", columnNum + 1, maxValue.toPlainString()));
		}
	}

	private void verifyLongValue(StringBuffer sb, Row row, int columnNum, long maxValue) {
		Cell amountLimit = row.getCell(columnNum);
		try {
			if (maxValue == 0) {
				sb.append(String.format("第%d列，该区服好望角运营人员还未设定最小金币数量；", columnNum + 1));
			} else if (amountLimit == null || "".equals(amountLimit.getStringCellValue()) || new BigDecimal(amountLimit.getStringCellValue()).longValue() < maxValue) {
				sb.append(String.format("第%d列，只允许正数值(最小不少于%d)；", columnNum + 1, maxValue));
			}
		} catch (NumberFormatException e) {
			sb.append(String.format("第%d列，只允许正数值(最小不少于%d)；", columnNum + 1, maxValue));
		}
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

}
