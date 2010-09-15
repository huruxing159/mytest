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

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.VendorDao;

public class VendorManagementJmesaTemplate extends TableFacadeTemplate {

	private VendorDao vendorDao;
	private HttpServletRequest request;

	public VendorManagementJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
	}

	public VendorManagementJmesaTemplate(VendorDao vendorDao) {
		this(TableFacadeFactory.createTableFacade("VendorManagementJmesaTemplate", ServletActionContext.getRequest(), ServletActionContext.getResponse()));
		this.request = ServletActionContext.getRequest();
		this.vendorDao = vendorDao;
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "id"), new NumericTypeFilterMatcher());
		filterMatchers.put(new MatcherKey(Set.class, "users"), new FilterMatcher() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean evaluate(Object itemValue, String filterValue) {
				if (itemValue == null || filterValue == null) {
					return false;
				}
				Set<User> item = (Set<User>) itemValue;
				String filter = StringUtils.lowerCase(String.valueOf(filterValue));
				for (User user : item) {
					if (user.getUsername().contains(filter)) {
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "id", "name", "users", "operation" };
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

		HtmlColumn firstName = htmlRow.getColumn("name");
		firstName.setTitle("供货商名");

		HtmlColumn roles = htmlRow.getColumn("users");
		roles.setTitle("用户列表");
		roles.setSortable(false);
		roles.getCellRenderer().setCellEditor(new CellEditor() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				Set<User> itemValue = (Set<User>) ItemUtils.getItemValue(item, property);
				HtmlBuilder html = new HtmlBuilder();
				html.append("<ul>");
				for (User user : itemValue) {
					html.append("<li>" + user.getUsername() + "</li>");
				}
				html.append("</ul>");
				return html.toString();
			}
		});

		HtmlColumn operation = htmlRow.getColumn("operation");
		operation.setTitle("操作");
		operation.setSortable(false);
		operation.getCellRenderer().setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				html.a().href().quote().append(request.getContextPath() + "/afterlogin/editVendorUser?vendorId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='editLinkClass'").close();
				html.append("修改用户类表");
				html.aEnd();
				return html.toString();
			}
		});
	}

	@Override
	protected Collection<?> getItems() {
		return vendorDao.getAllVendors();
	}

}
