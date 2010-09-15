package com.goodhope.goldselling.domain;

public class OperatorServerPrice {
	private String country;
	private String server;
	private String faction;
	private String price;
	private String priceLimit;
	private String amountLimit;

	public OperatorServerPrice() {

	}

	public OperatorServerPrice(String country, String server, String faction, String price, String priceLimit, String amountLimit) {
		super();
		this.country = country;
		this.server = server;
		this.faction = faction;
		this.price = price;
		this.priceLimit = priceLimit;
		this.amountLimit = amountLimit;
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

	public String getPriceLimit() {
		return priceLimit;
	}

	public void setPriceLimit(String priceLimit) {
		this.priceLimit = priceLimit;
	}

	public String getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(String amountLimit) {
		this.amountLimit = amountLimit;
	}
}
