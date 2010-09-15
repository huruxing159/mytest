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
import org.jmesa.util.ItemUtils;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.web.view.jmesa.wrapper.CustomerServiceOrderWrapper;

public class CustomerServiceOrderManagementJmesaTemplate extends TableFacadeTemplate {

	private Collection<Order> items;
	private HttpServletRequest request;
	private User user;

	public CustomerServiceOrderManagementJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
		tableFacade.setMaxRows(100);
		tableFacade.setMaxRowsIncrements(100, 200, 500);
	}

	public CustomerServiceOrderManagementJmesaTemplate(HttpServletRequest request, HttpServletResponse response) {
		this(TableFacadeFactory.createTableFacade("CustomerServiceOrderManagementJmesaTemplate", request, response));
		this.request = request;
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "time", "orderNumber", "orderDetailLink", "vendorName", "goldAmount", "state", "customerServiceAction", "acceptingTime", "delivingTime", "validate", "refundment" };
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "goldAmount"), new NumericTypeFilterMatcher());
	}

	@Override
	protected Collection<?> getItems() {
		Collection<CustomerServiceOrderWrapper> wrapperList = new ArrayList<CustomerServiceOrderWrapper>();
		for (Order order : items) {
			wrapperList.add(new CustomerServiceOrderWrapper(order));
		}
		return wrapperList;
	}

	@Override
	protected void modifyTable(Table table) {
		HtmlTable htmlTable = (HtmlTable) table;
		htmlTable.getTableRenderer().setWidth("100%");

		HtmlRow htmlRow = htmlTable.getRow();

		HtmlColumn data = htmlRow.getColumn("time");
		data.setTitle("时间");
		data.getCellRenderer().setCellEditor(new CalendarCellEditor("yyyy-MM-dd HH:mm:ss"));

		HtmlColumn orderNumber = htmlRow.getColumn("orderNumber");
		orderNumber.setTitle("订单号");

		HtmlColumn orderDetailLink = htmlRow.getColumn("orderDetailLink");
		orderDetailLink.setTitle("商品详情");
		orderDetailLink.getCellRenderer().setCellEditor(new CellEditor() {

			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				html.a().href().quote().append("#").quote().append("class='basic' rel='" + request.getContextPath() + "/afterlogin/orderDetails?orderId=" + ItemUtils.getItemValue(item, "id") + "'").close();
				html.append("商品详情");
				html.aEnd();
				return html.toString();
			}
		});

		HtmlColumn vendor = htmlRow.getColumn("vendorName");
		vendor.setTitle("供货商");

		HtmlColumn amount = htmlRow.getColumn("goldAmount");
		amount.setTitle("数量");

		HtmlColumn event = htmlRow.getColumn("state");
		event.setTitle("状态");

		HtmlColumn customerServiceAction = htmlRow.getColumn("customerServiceAction");
		customerServiceAction.setTitle("客服");
		customerServiceAction.setSortable(false);
		customerServiceAction.getCellRenderer().setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CustomerServiceOrderWrapper wrapper = (CustomerServiceOrderWrapper) item;
				HtmlBuilder html = new HtmlBuilder();
				if (wrapper.getCustomerService() != null) {
					html.append(wrapper.getCustomerService().getUsername());
					html.append("&nbsp;&nbsp;&nbsp;");
					if (wrapper.getCustomerService().getUsername().equals(user.getUsername())) {
						html.a().href().quote().append(ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'  name='transferLink'").close();
						html.append("移交");
						html.aEnd();
					}
				} else {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/customerServiceAcceptOrder?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton' name='customerServiceLink'").close();
					html.append("接管");
					html.aEnd();
				}

				return html.toString();
			}
		});

		HtmlColumn acceptingTime = htmlRow.getColumn("acceptingTime");
		acceptingTime.setTitle("接受订单耗时");
		acceptingTime.getCellRenderer().setCellEditor(new CellEditor() {

			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				html.append(ItemUtils.getItemValue(item, "acceptingTime"));
				return html.toString();
			}
		});

		HtmlColumn delivingTime = htmlRow.getColumn("delivingTime");
		delivingTime.setTitle("发货耗时");
		delivingTime.getCellRenderer().setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				html.append(ItemUtils.getItemValue(item, "delivingTime"));
				html.append("</font>");
				return html.toString();
			}
		});

		HtmlColumn validate = htmlRow.getColumn("validate");
		validate.setTitle("验证发货");
		validate.setSortable(false);
		validate.getCellRenderer().setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				CustomerServiceOrderWrapper wrapper = (CustomerServiceOrderWrapper) item;
				if (wrapper.getOrder().getState().equals(OrderState.DELIVERED_UNCHECK)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/viewOrderPicture?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'").close();
					html.append("查看截图");
					html.aEnd();
					html.append("&nbsp;&nbsp;&nbsp;");

					if (wrapper.getCustomerService() != null && wrapper.getCustomerService().getUsername().equals(user.getUsername())) {
						html.a().href().quote().append(request.getContextPath() + "/afterlogin/validateSuccessOrder?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'  name='customerServiceLink'").close();
						html.append("通过");
						html.aEnd();
						html.append("&nbsp;&nbsp;&nbsp;");
						html.a().href().quote().append(request.getContextPath() + "/afterlogin/validateFaileOrder?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton' name='customerServiceLink'").close();
						html.append("不通过");
						html.aEnd();
					}
				} else if (wrapper.getOrder().getState().equals(OrderState.CHECKED_UNBALANCE)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/viewOrderPicture?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'").close();
					html.append("查看截图");
					html.aEnd();
				} else if (wrapper.getOrder().getState().equals(OrderState.CHECKE_FAILE)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/viewOrderPicture?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'").close();
					html.append("查看截图");
					html.aEnd();
					if (wrapper.getCustomerService() != null && wrapper.getCustomerService().getUsername().equals(user.getUsername())) {
						html.a().href().quote().append(request.getContextPath() + "/afterlogin/customerServiceInitDeliverFailure?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'").close();
						html.append("发货失败");
						html.aEnd();
					}
				} else if (wrapper.getOrder().getState().equals(OrderState.ASSIGN_FAILED)) {
					if (wrapper.getCustomerService() != null && wrapper.getCustomerService().getUsername().equals(user.getUsername())) {
						html.a().href().quote().append(request.getContextPath() + "/afterlogin/customerServiceInitDeliverFailure?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'").close();
						html.append("发货失败");
						html.aEnd();
					}
				} else if (wrapper.getOrder().getState().equals(OrderState.DELIVERE_FAILE)) {
					if (wrapper.getCustomerService() != null && wrapper.getCustomerService().getUsername().equals(user.getUsername())) {
						html.a().href().quote().append(request.getContextPath() + "/afterlogin/customerServiceReDeliver?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'  name='customerServiceLink'").close();
						html.append("重新发货");
						html.aEnd();
					}
				}
				return html.toString();
			}
		});

		HtmlColumn refundment = htmlRow.getColumn("refundment");
		refundment.setTitle("是否退款");
		refundment.setSortable(false);
		refundment.getCellRenderer().setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				CustomerServiceOrderWrapper wrapper = (CustomerServiceOrderWrapper) item;
				if (wrapper.getOrder().getState().equals(OrderState.DELIVERE_FAILE)) {

					if (wrapper.getCustomerService() != null) {
						if (wrapper.getCustomerService().getUsername().equals(user.getUsername())) {
							html.a().href().quote().append(request.getContextPath() + "/afterlogin/customServiceRefundment?orderId=" + ItemUtils.getItemValue(item, "id")).quote().append("class='easyui-linkbutton'  name='customerServiceLink'").close();
							html.append("退款");
							html.aEnd();
						}
					}
				}
				return html.toString();
			}
		});

	}

	public void setItems(Collection<Order> items) {
		this.items = items;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
