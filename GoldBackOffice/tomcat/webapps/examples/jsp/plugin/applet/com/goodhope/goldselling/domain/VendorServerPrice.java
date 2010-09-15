package com.goodhope.goldselling.domain;

public class VendorServerPrice {

	private String country;
	private String server;
	private String faction;
	private String price;
	private String amount;

	public VendorServerPrice() {

	}

	public VendorServerPrice(String country, String server, String faction, String price, String amount) {
		super();
		this.country = country;
		this.server = server;
		this.faction = faction;
		this.price = price;
		this.amount = amount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
