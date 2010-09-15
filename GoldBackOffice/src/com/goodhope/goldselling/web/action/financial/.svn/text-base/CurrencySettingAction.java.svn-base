package com.goodhope.goldselling.web.action.financial;

import java.util.Collection;

import org.jmesa.facade.TableFacade;
import org.jmesa.worksheet.Worksheet;
import org.jmesa.worksheet.WorksheetCallbackHandler;
import org.jmesa.worksheet.WorksheetColumn;
import org.jmesa.worksheet.WorksheetRow;

import com.goodhope.goldselling.domain.Currency;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.CurrencyDao;
import com.goodhope.goldselling.web.view.jmesa.CurrencySettingJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class CurrencySettingAction extends ActionSupport {

	private static final long serialVersionUID = -1229216994319489841L;
	private CurrencyDao currencyDao;
	private BaseDao baseDao;
	private CurrencySettingJmesaTemplate currencySettingJmesaTemplate;
	private String currencyJmesaCode;

	@Override
	public String execute() throws Exception {
		currencySettingJmesaTemplate = new CurrencySettingJmesaTemplate(currencyDao);
		TableFacade tableFacade = currencySettingJmesaTemplate.getTableFacade();
		saveWorksheet(tableFacade);
		this.currencyJmesaCode = currencySettingJmesaTemplate.render();
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
				Currency currency = baseDao.findById(Currency.class, Long.parseLong(id));
				for (WorksheetColumn worksheetColumn : columns) {
					if (worksheetColumn.getProperty().equals("scaling_factor")) {
						String changedValue = worksheetColumn.getChangedValue();

						validateColumn(worksheetColumn, changedValue);
						if (worksheetColumn.hasError()) {
							continue;
						}
						currency.setScaling_factor(Float.parseFloat(changedValue));
						baseDao.update(currency);
					}
				}
			}

			private void validateColumn(WorksheetColumn worksheetColumn, String changedValue) {
				try {
					Float.parseFloat(changedValue);
					worksheetColumn.removeError();
				} catch (Exception e) {
					worksheetColumn.setError("scaling_factor.error");
				}
			}
		});

	}

	public void setCurrencyDao(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	public String getCurrencyJmesaCode() {
		return currencyJmesaCode;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
