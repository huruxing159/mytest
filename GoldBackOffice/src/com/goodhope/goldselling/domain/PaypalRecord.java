package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.util.Calendar;

public class PaypalRecord implements Serializable {

	private static final long serialVersionUID = 390921982004068449L;
	private long id;
	private String paypal_token;
	private String paypal_payer_id;
	private String paypal_transaction_id;
	private Calendar create_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPaypal_token() {
		return paypal_token;
	}

	public void setPaypal_token(String paypalToken) {
		paypal_token = paypalToken;
	}

	public String getPaypal_payer_id() {
		return paypal_payer_id;
	}

	public void setPaypal_payer_id(String paypalPayerId) {
		paypal_payer_id = paypalPayerId;
	}

	public String getPaypal_transaction_id() {
		return paypal_transaction_id;
	}

	public void setPaypal_transaction_id(String paypalTransactionId) {
		paypal_transaction_id = paypalTransactionId;
	}

	public Calendar getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Calendar createTime) {
		create_time = createTime;
	}

}
