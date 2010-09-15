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

import com.goodhope.goldselling.persistence.AuthorityDao;

public class AuthorityManagementJmesaTemplate extends TableFacadeTemplate {

	private AuthorityDao authorityDao;

	public AuthorityManagementJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
	}

	public AuthorityManagementJmesaTemplate(AuthorityDao authorityDao) {
		this(TableFacadeFactory.createTableFacade("AuthorityManagementJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.authorityDao = authorityDao;
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "id", "authorityName", "createTime" };
	}

	/**
	 * After the column properties are set then we can modify the table.
	 * 
	 * Note: a new (and better) way to build an html table would be to override
	 * the createTable() method and use the HtmlTableBuilder.
	 */
	@Override
	protected void modifyTable(Table table) {
		HtmlTable htmlTable = (HtmlTable) table;
		htmlTable.getTableRenderer().setWidth("100%");
    
		HtmlRow htmlRow = htmlTable.getRow();

		HtmlColumn firstName = htmlRow.getColumn("authorityName");
		firstName.setTitle("功能");

		HtmlColumn createTime = htmlRow.getColumn("createTime");
		createTime.setTitle("创建时间");
		createTime.getCellRenderer().setCellEditor(new CalendarCellEditor("yyyy-MM-dd HH:mm:ss"));

	}

	@Override
	protected Collection<?> getItems() {
		return authorityDao.getAllAuthorities();
	}

}
