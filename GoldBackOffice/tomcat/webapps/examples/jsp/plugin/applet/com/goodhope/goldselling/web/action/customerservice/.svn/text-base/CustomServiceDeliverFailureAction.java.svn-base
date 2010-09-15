package com.goodhope.goldselling.web.action.customerservice;

import java.util.List;

import org.springframework.web.util.HtmlUtils;

import com.goodhope.goldselling.domain.FailReason;
import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class CustomServiceDeliverFailureAction extends ActionSupport implements UserAware, TransactionAware {

	private static final long serialVersionUID = -1638713178956052780L;
	private User user;
	private long orderId;
	private BaseDao baseDao;
	private OrderRecordDao orderRecordDao;
	private Order order;
	private String failReason;

	@Override
	public String execute() throws Exception {
		this.order = this.baseDao.findById(Order.class, this.orderId);
		User customerService = this.order.getCustomerService();
		if (customerService == null) {
			throw new Exception("您还没有接管该订单");
		}
		if (!customerService.getUsername().equals(this.user.getUsername())) {
			throw new Exception("您不是该订单的接管客服");
		}

		Vendor vendor = order.getCurrentVendor();
		this.order.setState(OrderState.DELIVERE_FAILE);
		this.baseDao.update(this.order);
		FailReason orderFailReason = new FailReason();
		orderFailReason.setOrder(this.order);
		orderFailReason.setReason(this.failReason);
		orderFailReason.setVendor(vendor);
		this.order.getFailReason().add(orderFailReason);
		this.baseDao.save(orderFailReason);
		if (vendor != null) {
			List<OrderRecord> orderRecordList = this.orderRecordDao.getOrderRecordByNumberAndVendor(order.getPurchaseAttemp().getGh_transaction_id(), vendor);
			if (orderRecordList.size() > 0) {
				OrderRecord orderRecord = orderRecordList.get(0);
				orderRecord.setEvent(OrderState.DELIVERE_FAILE);
				this.baseDao.update(orderRecord);
				return super.execute();
			}

		} else {
			return super.execute();
		}
		throw new Exception();
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public User getUser() {
		return user;
	}

	public void setFailReason(String failReason) {
		this.failReason = HtmlUtils.htmlEscape(failReason);
	}

}