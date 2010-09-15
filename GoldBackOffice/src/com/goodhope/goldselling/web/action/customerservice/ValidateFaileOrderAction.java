package com.goodhope.goldselling.web.action.customerservice;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class ValidateFaileOrderAction extends ActionSupport implements TransactionAware, UserAware {

	private static final long serialVersionUID = 6836599492461410867L;
	private User user;
	private long orderId;
	private BaseDao baseDao;
	private Order order;

	@Override
	public String execute() throws Exception {
		this.order = this.baseDao.findById(Order.class, this.orderId);
		this.order.setState(OrderState.CHECKE_FAILE);
		this.baseDao.update(this.order);
		if (this.order != null && this.order.getCustomerService().getUsername().equals(user.getUsername())) {
			for (OrderRecord orderRecord : this.order.getOrderRecords()) {
				if (orderRecord.getVendor().getName().equals(this.order.getCurrentVendor().getName())) {
					orderRecord.setEvent(this.order.getState());
					this.baseDao.update(orderRecord);
					return super.execute();
				}
			}
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

}
