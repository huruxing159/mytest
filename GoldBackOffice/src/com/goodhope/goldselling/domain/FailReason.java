package com.goodhope.goldselling.domain;

import java.io.Serializable;

public class FailReason implements Serializable {

	private static final long serialVersionUID = -3789643058081400798L;

	private long id;
	private String reason;
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

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

}
