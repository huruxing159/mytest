package com.goodhope.goldselling.web.view.jmesa.wrapper;

import java.util.Calendar;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;

public class CustomerServiceOrderWrapper {
	private Order order;

	public CustomerServiceOrderWrapper() {
	}

	public CustomerServiceOrderWrapper(Order item) {
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

	public String getOrderDetailLink() {
		return "点击查看";
	}

	public String getVendorName() {
		return order.getCurrentVendor().getName();
	}

	public String getOrderNumber() {
		return order.getPurchaseAttemp().getGh_transaction_id();
	}

	public Calendar getTime() {
		return order.getCreateTime();
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
		if (OrderState.REJECT.equals(order.getState())) {
			return "";
		}
		Calendar acceptTime = order.getAcceptTime();
		Calendar createTime = order.getCreateTime();
		if (acceptTime != null) {
			long delay = acceptTime.getTimeInMillis() - createTime.getTimeInMillis();
			return (delay / 60000l) + "分";

		} else {
			if (OrderState.WAIT_REFUNDMENT.equals(order.getState()) || OrderState.REFUNDMENT_SUCCESS.equals(order.getState())) {
				return "";
			}
			return "<font color='red'>" + (Calendar.getInstance().getTimeInMillis() - createTime.getTimeInMillis()) / 60000l + "分</font>";
		}
	}

	public String getDelivingTime() {
		Calendar acceptTime = order.getAcceptTime();
		Calendar deliverTime = order.getDeliverTime();
		if (acceptTime != null) {
			if (deliverTime != null) {
				return ((deliverTime.getTimeInMillis() - acceptTime.getTimeInMillis()) / 60000l) + "分";
			} else {
				if (OrderState.WAIT_REFUNDMENT.equals(order.getState()) || OrderState.REFUNDMENT_SUCCESS.equals(order.getState())) {
					return "";
				}
				return "<font color='red'>"+((Calendar.getInstance().getTimeInMillis() - order.getAcceptTime().getTimeInMillis()) / 60000l) + "分</font>";
			}
		} else {
			return "";
		}
	}

	public String getValidate() {
		return "";
	}

	public String getRefundment() {
		return "";
	}

}
