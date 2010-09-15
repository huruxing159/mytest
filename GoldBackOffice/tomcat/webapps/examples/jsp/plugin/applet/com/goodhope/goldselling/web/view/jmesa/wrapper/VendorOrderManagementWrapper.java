package com.goodhope.goldselling.web.view.jmesa.wrapper;

import java.math.BigDecimal;

import org.apache.commons.lang.time.DateFormatUtils;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;

public class VendorOrderManagementWrapper {

	private OrderRecord orderRecord;

	public VendorOrderManagementWrapper() {
	}

	public VendorOrderManagementWrapper(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public OrderRecord getOrderRecord() {
		return orderRecord;
	}

	public String getVendorUnitPrice() {
		return orderRecord.getVendorUnitPrice().setScale(4, BigDecimal.ROUND_HALF_UP).toString();
	}

	public String getvendorTotalPrice() {
		BigDecimal vendorUnitPrice = orderRecord.getVendorUnitPrice();
		BigDecimal totalPrice = vendorUnitPrice.multiply(new BigDecimal(orderRecord.getOrder().getPurchaseAttemp().getGold_amount()));
		return totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public Order getOrder() {
		return orderRecord.getOrder();
	}

	public String getEvent() {
		return orderRecord.getEvent();
	}

	public String getCreateTime() {
		return DateFormatUtils.format(this.orderRecord.getOrder().getCreateTime(), "yyyy-MM-dd HH:ss:mm");
	}
}
