package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.util.Calendar;

public class MoneybookersRecord implements Serializable {

	private static final long serialVersionUID = 7683634864327755640L;
	private long id;
	private String merchant_id;
	private String md5sig;
	private String amount;
	private String transaction_id;
	private String pay_to_email;
	private String mb_currency;
	private String mb_amount;
	private String mb_transaction_id;
	private String status;
	private String currency;
	private String customer_id;
	private String payment_type;
	private String pay_from_email;
	private Calendar create_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchantId) {
		merchant_id = merchantId;
	}

	public String getMd5sig() {
		return md5sig;
	}

	public void setMd5sig(String md5sig) {
		this.md5sig = md5sig;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}

	public String getPay_to_email() {
		return pay_to_email;
	}

	public void setPay_to_email(String payToEmail) {
		pay_to_email = payToEmail;
	}

	public String getMb_currency() {
		return mb_currency;
	}

	public void setMb_currency(String mbCurrency) {
		mb_currency = mbCurrency;
	}

	public String getMb_amount() {
		return mb_amount;
	}

	public void setMb_amount(String mbAmount) {
		mb_amount = mbAmount;
	}

	public String getMb_transaction_id() {
		return mb_transaction_id;
	}

	public void setMb_transaction_id(String mbTransactionId) {
		mb_transaction_id = mbTransactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customerId) {
		customer_id = customerId;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String paymentType) {
		payment_type = paymentType;
	}

	public String getPay_from_email() {
		return pay_from_email;
	}

	public void setPay_from_email(String payFromEmail) {
		pay_from_email = payFromEmail;
	}

	public Calendar getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Calendar createTime) {
		create_time = createTime;
	}

}
