package com.goodhope.goldselling.web.action.customerservice;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerServiceAcceptOrderAction extends ActionSupport implements UserAware, TransactionAware {

	private static final long serialVersionUID = -1918640700979812690L;
	private User user;
	private long orderId;
	private BaseDao baseDao;

	@Override
	public String execute() throws Exception {
		Order order = this.baseDao.findById(Order.class, this.orderId);
		order.setCustomerService(this.user);
		this.baseDao.update(order);
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
