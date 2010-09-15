package com.goodhope.goldselling.web.view.jmesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.util.ItemUtils;
import org.jmesa.view.View;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.jmesa.view.html.editor.HtmlCellEditor;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.web.view.jmesa.wrapper.FinancialOrderWrapper;

public class FinancialQueryOrderJmesaTemplate extends TableFacadeTemplate {

	private Collection<Order> items;
	private TableFacade tableFacade;
	private HttpServletRequest request;

	public FinancialQueryOrderJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
		this.tableFacade = tableFacade;

	}

	public FinancialQueryOrderJmesaTemplate(HttpServletRequest request, HttpServletResponse response) {
		this(TableFacadeFactory.createTableFacade("FinancialQueryOrderJmesaTemplate", request, response));
		this.request = request;

	}

	@Override
	protected ExportType[] getExportTypes() {
		return new ExportType[] { ExportType.JEXCEL };
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "jmesaCheckBox", "vendorName", "orderNumber","paymentTransactionNumber", "time", "server", "faction", "state", "goldAmount", "unitPrice", "totalPrice", "vendorUnitPrice", "vendorTotalPrice", "financialAction" };
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "goldAmount"), new NumericTypeFilterMatcher());
	}

	@Override
	protected Collection<?> getItems() {
		Collection<FinancialOrderWrapper> wrapperList = new ArrayList<FinancialOrderWrapper>();
		for (Order order : items) {
			wrapperList.add(new FinancialOrderWrapper(order));
		}
		return wrapperList;
	}

	@Override
	protected void modifyTable(Table table) {

		Row row = table.getRow();
		Column vendor = row.getColumn("vendorName");
		vendor.setTitle("供货商");

		Column orderNumber = row.getColumn("orderNumber");
		orderNumber.setTitle("订单号");

		Column paymentTransactionNumber = row.getColumn("paymentTransactionNumber");
		paymentTransactionNumber.setTitle("交易号");

		Column data = row.getColumn("time");
		data.setTitle("日期");

		Column server = row.getColumn("server");
		server.setTitle("服务器");

		Column faction = row.getColumn("faction");
		faction.setTitle("阵营");

		Column event = row.getColumn("state");
		event.setTitle("状态");

		Column amount = row.getColumn("goldAmount");
		amount.setTitle("数量");

		Column unitPrice = row.getColumn("unitPrice");
		unitPrice.setTitle("单价");

		Column totalPrice = row.getColumn("totalPrice");
		totalPrice.setTitle("金额");

		Column vendorUnitPrice = row.getColumn("vendorUnitPrice");
		vendorUnitPrice.setTitle("供货商单价");

		Column vendorTotalPrice = row.getColumn("vendorTotalPrice");
		vendorTotalPrice.setTitle("供货商金额");

		if (table instanceof org.jmesa.view.html.component.HtmlTable) {
			HtmlTable htmlTable = (HtmlTable) table;
			htmlTable.getTableRenderer().setWidth("100%");

			HtmlRow htmlRow = htmlTable.getRow();

			HtmlColumn checkbox = htmlRow.getColumn("jmesaCheckBox");
			checkbox.setTitle("_");
			checkbox.setEditable(false);
			checkbox.setFilterable(false);
			checkbox.setSortable(false);
			checkbox.setWidth("20px");
			checkbox.getCellRenderer().setCellEditor(new CellEditor() {

				@Override
				public Object getValue(Object item, String property, int rowcount) {
					Object value = new HtmlCellEditor().getValue(item, "id", rowcount);
					Object state = ItemUtils.getItemValue(item, "state");
					HtmlBuilder html = new HtmlBuilder();
					if (OrderState.CHECKED_UNBALANCE.equals(state.toString())) {
						html.input().type("checkbox").append(" value=" + value).append(" name='id' ").close();
					}
					return html.toString();
				}
			});

			HtmlColumn financialAction = htmlRow.getColumn("financialAction");
			financialAction.setTitle("退款");
			financialAction.setEditable(false);
			financialAction.setSortable(false);
			financialAction.getCellRenderer().setCellEditor(new CellEditor() {
				@Override
				public Object getValue(Object item, String property, int rowcount) {
					Object state = ItemUtils.getItemValue(item, "state");
					HtmlBuilder html = new HtmlBuilder();
					if (OrderState.WAIT_REFUNDMENT.equals(state.toString())) {
						html.a().href().quote().append(request.getContextPath() + "/afterlogin/financialRefundment?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton' name='customerServiceLink'").close();
						html.append("退款");
						html.aEnd();
					}
					return html.toString();
				}
			});

		}

	}

	public void setItems(Collection<Order> items) {
		this.items = items;
	}

	@Override
	protected View createView() {
		Limit limit = this.tableFacade.getLimit();
		if (limit.isExported()) {
			return null;
		}

		return new JmesaTableFootSelectAllView("<div id='balanceLinkDiv'><a href='#' id='balanceLink'>结算</a></div>");
	}

}
