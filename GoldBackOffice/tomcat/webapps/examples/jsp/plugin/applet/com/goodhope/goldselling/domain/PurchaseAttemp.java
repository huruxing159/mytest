package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.goodhope.goldselling.service.MoneySymbolService;

public class PurchaseAttemp implements Serializable {

	private static final long serialVersionUID = -7519582361318417648L;
	private long id;
	private String gh_transaction_id;
	private String region;
	private String faction;
	private String server;
	private String character;
	private String trade_method;
	private long gold_amount;
	private BigDecimal price;
	private BigDecimal unit_price;
	private String concurrency;
	private String payment_method;
	private long payment_method_id;
	private Calendar create_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGh_transaction_id() {
		return gh_transaction_id;
	}

	public void setGh_transaction_id(String ghTransactionId) {
		gh_transaction_id = ghTransactionId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getTrade_method() {
		return trade_method;
	}

	public void setTrade_method(String tradeMethod) {
		trade_method = tradeMethod;
	}

	public long getGold_amount() {
		return gold_amount;
	}

	public void setGold_amount(long goldAmount) {
		gold_amount = goldAmount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getConcurrency() {
		return concurrency;
	}

	public void setConcurrency(String concurrency) {
		this.concurrency = concurrency;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String paymentMethod) {
		payment_method = paymentMethod;
	}

	public long getPayment_method_id() {
		return payment_method_id;
	}

	public void setPayment_method_id(long paymentMethodId) {
		payment_method_id = paymentMethodId;
	}

	public Calendar getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Calendar createTime) {
		create_time = createTime;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public String getConcurrencySymbol() {

		return MoneySymbolService.getSymbol(this.concurrency.toUpperCase());
	}

}
