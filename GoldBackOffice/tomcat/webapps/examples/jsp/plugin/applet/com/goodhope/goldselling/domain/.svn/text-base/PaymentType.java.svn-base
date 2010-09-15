package com.goodhope.goldselling.domain;

import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.util.SpringBeanFactoryUtil;

public enum PaymentType {

	PAYPAL("Paypal") {
		@Override
		public String getTransactionId() {
			PaypalRecord paypal = baseDao.findById(PaypalRecord.class, getPaymentId());
			return paypal.getPaypal_transaction_id();
		}
	},
	MONEYBOOKERS("Moneybookers") {
		@Override
		public String getTransactionId() {
			MoneybookersRecord moneybookers = baseDao.findById(MoneybookersRecord.class, getPaymentId());
			return moneybookers.getMb_transaction_id();
		}
	};
	private String name;
	private long paymentId;
	private static BaseDao baseDao = (BaseDao) SpringBeanFactoryUtil.getBean("baseDao");

	private PaymentType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getTransactionId();

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getPaymentId() {
		return paymentId;
	}

}
