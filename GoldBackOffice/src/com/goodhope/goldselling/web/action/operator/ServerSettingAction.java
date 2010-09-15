package com.goodhope.goldselling.web.action.operator;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import org.jmesa.facade.TableFacade;
import org.jmesa.worksheet.Worksheet;
import org.jmesa.worksheet.WorksheetCallbackHandler;
import org.jmesa.worksheet.WorksheetColumn;
import org.jmesa.worksheet.WorksheetRow;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.web.view.jmesa.ServerSettingJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class ServerSettingAction extends ActionSupport {

	private static final long serialVersionUID = -7057685238961215030L;
	private ServerDao serverDao;
	private BaseDao baseDao;
	private String jmesaViewCode;
	private ServerSettingJmesaTemplate serverSettingJmesaTemplate;

	@Override
	public String execute() throws Exception {
		serverSettingJmesaTemplate = new ServerSettingJmesaTemplate(serverDao);
		TableFacade tableFacade = serverSettingJmesaTemplate.getTableFacade();
		saveWorksheet(tableFacade);
		jmesaViewCode = serverSettingJmesaTemplate.render();
		if (jmesaViewCode == null) {
			return null;
		}
		return super.execute();
	}

	private void saveWorksheet(TableFacade tableFacade) {
		Worksheet worksheet = tableFacade.getWorksheet();
		if (!worksheet.isSaving() || !worksheet.hasChanges()) {
			return;
		}
		worksheet.processRows(new WorksheetCallbackHandler() {
			public void process(WorksheetRow worksheetRow) {
				Collection<WorksheetColumn> columns = worksheetRow.getColumns();
				String id = worksheetRow.getUniqueProperty().getValue();
				Server server = baseDao.findById(Server.class, Long.parseLong(id));
				for (WorksheetColumn worksheetColumn : columns) {
					if (worksheetColumn.getProperty().equals("current_unit_price")) {
						String changedValue = worksheetColumn.getChangedValue();
						validateColumn(worksheetColumn, changedValue);
						if (worksheetColumn.hasError()) {
							continue;
						}
						if (server.getCurrent_unit_price() != null) {
							server.setHistory_unit_price(server.getCurrent_unit_price());
						}
						server.setCurrent_unit_price(new BigDecimal(changedValue));
						server.setUpdated_at(Calendar.getInstance());
					}

					if (worksheetColumn.getProperty().equals("server.amountLimit")) {
						String changedValue = worksheetColumn.getChangedValue();
						validateColumn(worksheetColumn, changedValue);
						if (worksheetColumn.hasError()) {
							continue;
						}
						server.setAmountLimit(Long.parseLong(changedValue));
						server.setUpdated_at(Calendar.getInstance());

					}
					if (worksheetColumn.getProperty().equals("priceLimit")) {
						String changedValue = worksheetColumn.getChangedValue();
						validateColumn(worksheetColumn, changedValue);
						if (worksheetColumn.hasError()) {
							continue;
						}
						server.setPriceLimit(new BigDecimal(changedValue));
						server.setUpdated_at(Calendar.getInstance());
					}

				}
				baseDao.update(server);
			}

			private void validateColumn(WorksheetColumn worksheetColumn, String changedValue) {
				try {
					BigDecimal change = new BigDecimal(changedValue);
					if (change.compareTo(new BigDecimal(0)) < 0) {
						throw new Exception();
					}
					if (change.compareTo(new BigDecimal(Integer.MAX_VALUE)) >= 0) {
						addActionError("输入的值过大，请输入合理的正数");
						throw new Exception();
					}
				} catch (Exception e) {
					worksheetColumn.setError("error");
				}
			}
		});

	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

	public String getJmesaViewCode() {
		return jmesaViewCode;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
