package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderRecord implements Serializable {

	private static final long serialVersionUID = 7535430977634279038L;

	private long id;
	private String event;
	private BigDecimal vendorUnitPrice;

	private Order order;
	private Vendor vendor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	public void setVendorUnitPrice(BigDecimal vendorUnitPrice) {
		this.vendorUnitPrice = vendorUnitPrice;
	}

	public BigDecimal getVendorUnitPrice() {
		return vendorUnitPrice;
	}

}
