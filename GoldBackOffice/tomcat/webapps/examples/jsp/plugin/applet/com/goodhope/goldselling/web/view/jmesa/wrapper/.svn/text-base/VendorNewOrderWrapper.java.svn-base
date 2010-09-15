package com.goodhope.goldselling.web.view.jmesa.wrapper;

import java.math.BigDecimal;

import org.apache.commons.lang.time.DateFormatUtils;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.PurchaseAttemp;

public class VendorNewOrderWrapper {

	private Order order;

	public VendorNewOrderWrapper() {
	}

	public VendorNewOrderWrapper(Order order) {
		this.order = order;
	}

	public PurchaseAttemp getPurchaseAttemp() {
		return order.getPurchaseAttemp();
	}

	public String getVendorTotalPrice() {
		BigDecimal totalPrice = order.getVendorUnitPrice().multiply(new BigDecimal(order.getPurchaseAttemp().getGold_amount()));
		return totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public String getVendorUnitPrice() {
		return order.getVendorUnitPrice().setScale(4, BigDecimal.ROUND_HALF_UP).toString();
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public String getCreateTime() {
		return DateFormatUtils.format(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
	}

}
