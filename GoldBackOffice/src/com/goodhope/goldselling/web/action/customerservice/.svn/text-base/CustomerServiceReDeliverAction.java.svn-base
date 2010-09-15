package com.goodhope.goldselling.web.action.customerservice;

import java.util.Calendar;
import java.util.List;

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

public class CustomerServiceReDeliverAction extends ActionSupport implements UserAware, TransactionAware {

	private static final long serialVersionUID = 5720233587762083253L;
	private User user;
	private long orderId;
	private BaseDao baseDao;
	private OrderRecordDao orderRecordDao;
	private Order order;

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

		if (vendor != null) {
			this.order.setState(OrderState.ACCEPTED_UNDELIVER);
			if (this.order.getAcceptTime() == null) {
				this.order.setAcceptTime(Calendar.getInstance());// 因为拒绝订单暂时作为发货失败处理，所以再计算接受耗时的时候acceptTime是null
			}
			this.order.setDeliverTime(null);
			this.baseDao.update(this.order);
			List<OrderRecord> orderRecordList = this.orderRecordDao.getOrderRecordByNumberAndVendor(order.getPurchaseAttemp().getGh_transaction_id(), vendor);
			if (orderRecordList.size() > 0) {
				OrderRecord orderRecord = orderRecordList.get(0);
				orderRecord.setEvent(OrderState.ACCEPTED_UNDELIVER);
				this.baseDao.update(orderRecord);
			} else {
				throw new Exception();
			}
		} else {
			this.order.setState(OrderState.UNASSIGN);
			this.baseDao.update(this.order);
		}
		return super.execute();
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

}