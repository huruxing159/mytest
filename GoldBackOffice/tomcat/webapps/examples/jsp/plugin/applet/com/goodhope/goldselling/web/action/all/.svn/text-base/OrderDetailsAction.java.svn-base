package com.goodhope.goldselling.web.action.all;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.persistence.BaseDao;
import com.opensymphony.xwork2.ActionSupport;

public class OrderDetailsAction extends ActionSupport {

	private static final long serialVersionUID = -5477919344394601951L;
	private BaseDao baseDao;
	private long orderId;
	private Order order;

	@Override
	public String execute() throws Exception {
		this.order = this.baseDao.findById(Order.class, this.orderId);
		if (this.order == null) {
			return INPUT;
		}
		return super.execute();
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Order getOrder() {
		return order;
	}

}
