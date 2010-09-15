package com.goodhope.goldselling.web.view.jmesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmesa.core.filter.FilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.facade.TableFacadeTemplate;
import org.jmesa.view.component.Table;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.web.view.jmesa.wrapper.VendorOrderManagementWrapper;

public class VendorOrderManagementJmesaTemplate extends TableFacadeTemplate {

	private Collection<OrderRecord> items;

	public VendorOrderManagementJmesaTemplate(TableFacade tableFacade) {
		super(tableFacade);
	}

	@Override
	protected void addFilterMatchers(Map<MatcherKey, FilterMatcher> filterMatchers) {
		super.addFilterMatchers(filterMatchers);
		filterMatchers.put(new MatcherKey(Long.class, "order.purchaseAttemp.gold_amount"), new NumericTypeFilterMatcher());
	}

	public VendorOrderManagementJmesaTemplate(HttpServletRequest request, HttpServletResponse response) {
		this(TableFacadeFactory.createTableFacade("VendorOrderManagementJmesaTemplate", request, response));
	}

	@Override
	protected String[] getColumnProperties() {
		return new String[] { "order.purchaseAttemp.gh_transaction_id", "createTime", "order.purchaseAttemp.server", "order.purchaseAttemp.faction", "order.purchaseAttemp.character", "order.purchaseAttemp.trade_method", "event", "vendorUnitPrice", "order.purchaseAttemp.gold_amount",
				"vendorTotalPrice" };
	}

	@Override
	protected Collection<?> getItems() {
		List<VendorOrderManagementWrapper> wrappers = new ArrayList<VendorOrderManagementWrapper>();
		for (OrderRecord orderRecord : items) {
			wrappers.add(new VendorOrderManagementWrapper(orderRecord));
		}
		return wrappers;
	}

	@Override
	protected void modifyTable(Table table) {
		HtmlTable htmlTable = (HtmlTable) table;
		htmlTable.getTableRenderer().setWidth("100%");

		HtmlRow htmlRow = htmlTable.getRow();

		HtmlColumn orderNumber = htmlRow.getColumn("order.purchaseAttemp.gh_transaction_id");
		orderNumber.setTitle("订单号");

		HtmlColumn data = htmlRow.getColumn("createTime");
		data.setTitle("日期");
		HtmlColumn server = htmlRow.getColumn("order.purchaseAttemp.server");
		server.setTitle("服务器");
		HtmlColumn faction = htmlRow.getColumn("order.purchaseAttemp.faction");
		faction.setTitle("阵营");

		HtmlColumn charactor = htmlRow.getColumn("order.purchaseAttemp.character");
		charactor.setTitle("买家角色名");
		charactor.setSortable(false);

		HtmlColumn deliver = htmlRow.getColumn("order.purchaseAttemp.trade_method");
		deliver.setTitle("发货方式");
		deliver.setSortable(false);

		HtmlColumn event = htmlRow.getColumn("event");
		event.setTitle("订单状态");
		HtmlColumn unitPrice = htmlRow.getColumn("vendorUnitPrice");
		unitPrice.setTitle("单价");
		HtmlColumn amount = htmlRow.getColumn("order.purchaseAttemp.gold_amount");
		amount.setTitle("数量");
		HtmlColumn price = htmlRow.getColumn("vendorTotalPrice");
		price.setTitle("金额");

	}

	public void setItems(Collection<OrderRecord> items) {
		this.items = items;
	}
}
