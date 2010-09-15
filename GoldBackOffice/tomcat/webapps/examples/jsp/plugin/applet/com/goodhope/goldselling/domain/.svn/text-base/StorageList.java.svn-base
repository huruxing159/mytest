package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class StorageList implements Serializable {

	private static final long serialVersionUID = -3220432921614794503L;

	private long id;

	private long amount;
	private BigDecimal price;

	private Vendor vendor;
	private Server server;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public String getJmesaDeleteLink() {
		return "";
	}

	public String getJmesaCheckBox() {
		return "";
	}

	public String getHtmlPrice() {
		return this.price.toPlainString();
	}

	public String getPriceLimit() {
		return this.server.getPriceLimit().toPlainString();
	}

}
