package com.goodhope.goldselling.web.view.jmesa.wrapper;

import java.math.BigDecimal;

import org.apache.commons.lang.time.DateFormatUtils;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.PaymentType;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.service.MoneySymbolService;

public class FinancialOrderWrapper {

	private Order order;

	public FinancialOrderWrapper() {
	}

	public FinancialOrderWrapper(Order item) {
		this.order = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getId() {
		return order.getId();
	}

	public String getVendorName() {
		return order.getCurrentVendor().getName();
	}

	public String getOrderNumber() {
		return order.getPurchaseAttemp().getGh_transaction_id();
	}

	public String getTime() {
		return DateFormatUtils.format(order.getCreateTime(), "yyyy-MM-dd HH:ss:mm");
	}

	public String getState() {
		return order.getState();
	}

	public String getJmesaOperation() {
		return "";
	}

	public long getGoldAmount() {
		return order.getPurchaseAttemp().getGold_amount();
	}

	public String getCustomerServiceAction() {
		return "";
	}

	public User getCustomerService() {
		return order.getCustomerService();
	}

	public String getAcceptingTime() {
		return "";
	}

	public String getDelivingTime() {
		return "";
	}

	public String getValidate() {
		return "";
	}

	public String getServer() {
		return order.getPurchaseAttemp().getServer();
	}

	public String getFaction() {
		return order.getPurchaseAttemp().getFaction();
	}

	public String getUnitPrice() {
		return MoneySymbolService.getSymbol(order.getPurchaseAttemp().getConcurrency()) + order.getPurchaseAttemp().getUnit_price().toString();
	}

	public String getTotalPrice() {
		return MoneySymbolService.getSymbol(order.getPurchaseAttemp().getConcurrency()) + order.getPurchaseAttemp().getPrice().toString();
	}

	public BigDecimal getVendorUnitPrice() {
		return order.getVendorUnitPrice();
	}

	public BigDecimal getVendorTotalPrice() {
		BigDecimal totalPrice = order.getVendorUnitPrice().multiply(new BigDecimal(order.getPurchaseAttemp().getGold_amount()));
		return totalPrice;
	}

	public String getJmesaCheckBox() {
		return "";
	}

	public String getFinancialAction() {
		return "";
	}

	public String getPaymentTransactionNumber() {
		String paymentMethod = order.getPurchaseAttemp().getPayment_method();
		long paymentMethodId = order.getPurchaseAttemp().getPayment_method_id();
		PaymentType pay = PaymentType.valueOf(paymentMethod.toUpperCase());
		pay.setPaymentId(paymentMethodId);
		return paymentMethod + ":" + pay.getTransactionId();
	}
}
