package com.goodhope.goldselling.web.view.jmesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.view.View;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.web.view.jmesa.wrapper.VendorNewOrderWrapper;

public class NewOrderJmesaTemplate extends TableFacadeTemplate {

	private Collection<Order> items;
	private HttpServletRequest request;

	public NewOrderJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
		tableFacade.setMaxRows(10000);

	}

	public NewOrderJmesaTemplate(HttpServletRequest request, HttpServletResponse response) {
		this(TableFacadeFactory.createTableFacade("NewOrderJmesaTemplate", request, response));
		this.request = request;
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "purchaseAttemp.gh_transaction_id", "createTime", "purchaseAttemp.server", "purchaseAttemp.faction", "purchaseAttemp.character", "purchaseAttemp.trade_method", "vendorUnitPrice", "purchaseAttemp.gold_amount", "vendorTotalPrice", "jmesaOperation" };
	}

	@Override
	protected Collection<?> getItems() {
		List<VendorNewOrderWrapper> wrappers = new ArrayList<VendorNewOrderWrapper>();
		for (Order order : items) {
			wrappers.add(new VendorNewOrderWrapper(order));
		}
		return wrappers;
	}

	@Override
	protected void modifyTable(Table table) {
		HtmlTable htmlTable = (HtmlTable) table;
		htmlTable.getTableRenderer().setWidth("100%");

		HtmlRow htmlRow = htmlTable.getRow();

		HtmlColumn orderNumber = htmlRow.getColumn("purchaseAttemp.gh_transaction_id");
		orderNumber.setTitle("订单号");
		orderNumber.setSortable(false);

		HtmlColumn createTime = htmlRow.getColumn("createTime");
		createTime.setTitle("日期");
		createTime.setSortable(false);

		HtmlColumn server = htmlRow.getColumn("purchaseAttemp.server");
		server.setTitle("服务器");
		server.setSortable(false);

		HtmlColumn faction = htmlRow.getColumn("purchaseAttemp.faction");
		faction.setTitle("阵营");
		faction.setSortable(false);

		HtmlColumn charactor = htmlRow.getColumn("purchaseAttemp.character");
		charactor.setTitle("买家角色名");
		charactor.setSortable(false);

		HtmlColumn deliver = htmlRow.getColumn("purchaseAttemp.trade_method");
		deliver.setTitle("发货方式");
		deliver.setSortable(false);

		HtmlColumn unitPrice = htmlRow.getColumn("vendorUnitPrice");
		unitPrice.setTitle("单价(¥)");
		unitPrice.setSortable(false);

		HtmlColumn amount = htmlRow.getColumn("purchaseAttemp.gold_amount");
		amount.setTitle("数量");
		amount.setSortable(false);

		HtmlColumn price = htmlRow.getColumn("vendorTotalPrice");
		price.setTitle("金额(¥)");
		price.setSortable(false);

		HtmlColumn operation = htmlRow.getColumn("jmesaOperation");
		operation.setTitle("操作");
		operation.setSortable(false);
		operation.getCellRenderer().setCellEditor(new CellEditor() {

			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder html = new HtmlBuilder();
				Order order = ((VendorNewOrderWrapper) item).getOrder();
				if (order.getState().equals(OrderState.ASSIGNED_UNACCEPT)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/acceptOrder?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='accept_link'  icon='icon-add'").close();
					html.append("接受订单");
					html.aEnd();
					html.append("&nbsp;&nbsp;&nbsp;");
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/rejectOrder?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='reject_link'  icon='icon-cancel'").close();
					html.append("拒绝订单");
					html.aEnd();
				} else if (order.getState().equals(OrderState.ACCEPTED_UNDELIVER)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/initUploadPictureForOrder?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='upload_picture_link' icon='icon-save'").close();
					html.append("上传截图");
					html.aEnd();

					html.append("&nbsp;&nbsp;&nbsp;");
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/initDeliverFailure?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='reject_link'  icon='icon-cancel'").close();
					html.append("发货失败");
					html.aEnd();

				} else if (order.getState().equals(OrderState.DELIVERED_UNCHECK)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/viewOrderPicture?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='upload_picture_link' icon='icon-save'").close();
					html.append("查看截图");
					html.aEnd();
					html.append("&nbsp;&nbsp;&nbsp;");
					html.append("等待客服验证...");
				} else if (order.getState().equals(OrderState.CHECKE_FAILE)) {
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/viewOrderPicture?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='upload_picture_link' icon='icon-save'").close();
					html.append("查看截图");
					html.aEnd();
					html.append("&nbsp;&nbsp;&nbsp;");
					html.a().href().quote().append(request.getContextPath() + "/afterlogin/initUploadPictureForOrder?orderId=" + order.getId()).quote().append("class='easyui-linkbutton' name='upload_picture_link' icon='icon-save'").close();
					html.append("重新上传");
					html.aEnd();
					html.append("验证失败!");
				}
				return html.toString();
			}
		});

	}

	public void setItems(Collection<Order> items) {
		this.items = items;
	}

	@Override
	protected View createView() {
		return new JmesaTableNoHeadView();
	}
}
