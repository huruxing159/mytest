package com.goodhope.goldselling.web.view.jmesa;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.util.ItemUtils;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.goodhope.goldselling.domain.Authority;
import com.goodhope.goldselling.persistence.RoleDao;

public class RoleManagementJmesaTemplate extends TableFacadeTemplate {

	private RoleDao roleDao;

	public RoleManagementJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
	}

	public RoleManagementJmesaTemplate(RoleDao roleDao) {
		super(TableFacadeFactory.createTableFacade("RoleManagementJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.roleDao = roleDao;
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Set.class, "authorities"), new FilterMatcher() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean evaluate(Object itemValue, String filterValue) {
				if (itemValue == null || filterValue == null) {
					return false;
				}
				Set<Authority> item = (Set<Authority>) itemValue;
				String filter = StringUtils.lowerCase(String.valueOf(filterValue));
				for (Authority authority : item) {
					if (authority.getAuthorityName().contains(filter)) {
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "id", "roleShowName", "authorities", "createTime" };
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

		HtmlColumn firstName = htmlRow.getColumn("roleShowName");
		firstName.setTitle("角色名");

		HtmlColumn authorities = htmlRow.getColumn("authorities");
		authorities.setTitle("功能");
		authorities.getCellRenderer().setCellEditor(new CellEditor() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				Set<Authority> itemValue = (Set<Authority>) ItemUtils.getItemValue(item, property);
				HtmlBuilder html = new HtmlBuilder();
				html.append("<ul>");
				for (Authority authority : itemValue) {
					html.append("<li>" + authority.getAuthorityName() + "</li>");
				}
				html.append("</ul>");
				return html.toString();
			}
		});

		HtmlColumn createTime = htmlRow.getColumn("createTime");
		createTime.setTitle("创建时间");
		createTime.getCellRenderer().setCellEditor(new CalendarCellEditor("yyyy-MM-dd HH:mm:ss"));

	}

	@Override
	protected Collection<?> getItems() {
		return roleDao.getAllRoles();
	}

}
