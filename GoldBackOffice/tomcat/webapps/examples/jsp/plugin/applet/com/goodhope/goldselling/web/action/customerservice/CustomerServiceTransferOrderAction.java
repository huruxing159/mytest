package com.goodhope.goldselling.web.action.customerservice;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerServiceTransferOrderAction extends ActionSupport implements TransactionAware {

	private static final long serialVersionUID = -4300287276343697366L;
	private long customerServiceId;
	private long orderId;
	private BaseDao baseDao;
	private User customerService;
	private Order order;

	@Override
	public String execute() throws Exception {
		this.customerService = this.baseDao.findById(User.class, this.customerServiceId);
		this.order = this.baseDao.findById(Order.class, this.orderId);
		this.order.setCustomerService(this.customerService);
		this.baseDao.update(this.order);
		return super.execute();
	}

	public void setCustomerServiceId(long customerServiceId) {
		this.customerServiceId = customerServiceId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
