package com.goodhope.goldselling.web.view.jmesa;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.view.component.Table;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.editor.HtmlCellEditor;
import org.jmesa.worksheet.editor.HtmlWorksheetEditor;

import com.goodhope.goldselling.persistence.CurrencyDao;

public class CurrencySettingJmesaTemplate extends TableFacadeTemplate {

	private CurrencyDao currencyDao;
	private TableFacade tableFacade;

	public CurrencySettingJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
		this.tableFacade = tableFacade;

	}

	public CurrencySettingJmesaTemplate(CurrencyDao currencyDao) {
		this(TableFacadeFactory.createTableFacade("CurrencySettingJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.currencyDao = currencyDao;
		this.tableFacade.setEditable(true);
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "id", "label", "symbol", "scaling_factor" };
	}

	@Override
	protected Collection<?> getItems() {
		return currencyDao.getAllCurrencies();
	}

	@Override
	protected void modifyTable(Table table) {

		HtmlTable htmlTable = (HtmlTable) table;
		htmlTable.getTableRenderer().setWidth("100%");

		HtmlRow htmlRow = htmlTable.getRow();
		htmlRow.setUniqueProperty("id");

		HtmlColumn id = htmlRow.getColumn("id");
		id.setEditable(false);
		id.getCellRenderer().setWorksheetEditor(null);

		HtmlColumn label = htmlRow.getColumn("label");
		label.setEditable(false);
		label.getCellRenderer().setWorksheetEditor(null);
		label.setTitle("货币类型");

		HtmlColumn symbol = htmlRow.getColumn("symbol");
		symbol.setTitle("符号");
		symbol.setEditable(false);
		symbol.getCellRenderer().setCellEditor(new HtmlCellEditor());
		symbol.getCellRenderer().setWorksheetEditor(null);

		HtmlColumn scaling_factor = htmlRow.getColumn("scaling_factor");
		scaling_factor.setTitle("汇率");
		scaling_factor.setEditable(true);
		scaling_factor.getCellRenderer().setWorksheetEditor(new HtmlWorksheetEditor());

	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Float.class, "scaling_factor"), new NumericTypeFilterMatcher());
	}

	public TableFacade getTableFacade() {
		return tableFacade;
	}
}
