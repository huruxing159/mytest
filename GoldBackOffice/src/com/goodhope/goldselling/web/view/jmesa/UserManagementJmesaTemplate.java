package com.goodhope.goldselling.web.view.jmesa;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import com.goodhope.goldselling.domain.Role;
import com.goodhope.goldselling.persistence.UserDao;

public class UserManagementJmesaTemplate extends TableFacadeTemplate {

	private UserDao userDao;
	private HttpServletRequest request;

	public UserManagementJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
	}

	public UserManagementJmesaTemplate(UserDao userDao) {
		this(TableFacadeFactory.createTableFacade("UserManagementJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.request = ServletActionContext.getRequest();
		this.userDao = userDao;
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Set.class, "roles"), new FilterMatcher() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean evaluate(Object itemValue, String filterValue) {
				if (itemValue == null || filterValue == null) {
					return false;
				}
				Set<Role> item = (Set<Role>) itemValue;
				String filter = StringUtils.lowerCase(String.valueOf(filterValue));
				for (Role role : item) {
					if (role.getRoleShowName().contains(filter)) {
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "id", "username", "roles", "createTime", "lastLoginTime", "operation" };
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

		HtmlColumn firstName = htmlRow.getColumn("username");
		firstName.setTitle("用户名");

		HtmlColumn roles = htmlRow.getColumn("roles");
		roles.setTitle("角色");
		roles.setSortable(false);
		roles.getCellRenderer().setCellEditor(new CellEditor() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				Set<Role> itemValue = (Set<Role>) ItemUtils.getItemValue(item, property);
				HtmlBuilder html = new HtmlBuilder();
				html.append("<ul>");
				for (Role role : itemValue) {
					html.append("<li>" + role.getRoleShowName() + "</li>");
				}
				html.append("</ul>");
				return html.toString();
			}
		});

		HtmlColumn createTime = htmlRow.getColumn("createTime");
		createTime.setTitle("创建时间");
		createTime.getCellRenderer().setCellEditor(new CalendarCellEditor("yyyy-MM-dd HH:mm:ss"));

		HtmlColumn lastLoginTime = htmlRow.getColumn("lastLoginTime");
		lastLoginTime.setTitle("上次登录时间");
		lastLoginTime.getCellRenderer().setCellEditor(new CalendarCellEditor("yyyy-MM-dd HH:mm:ss"));

		HtmlColumn operation = htmlRow.getColumn("operation");
		operation.setTitle("操作");
		operation.setSortable(false);
		operation.getCellRenderer().setCellEditor(new CellEditor() {
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				html.a().href().quote().append(request.getContextPath() + "/afterlogin/editRole?userId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='editLinkClass'").close();
				html.append("修改角色");
				html.aEnd();
				html.append("&nbsp;&nbsp;&nbsp;");
				html.a().href().quote().append(request.getContextPath() + "/afterlogin/deleteUser?userId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='deleteLinkClass'").close();
				html.append("删除");
				html.aEnd();
				return html.toString();
			}
		});

	}

	@Override
	protected Collection<?> getItems() {
		return userDao.getAllUsers();
	}

}
