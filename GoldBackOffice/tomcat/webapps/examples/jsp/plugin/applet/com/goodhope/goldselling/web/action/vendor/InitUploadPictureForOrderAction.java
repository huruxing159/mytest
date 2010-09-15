package com.goodhope.goldselling.web.action.vendor;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class InitUploadPictureForOrderAction extends ActionSupport implements UserAware {

	private static final long serialVersionUID = 4895457034582646886L;
	private User user;
	private long orderId;

	@Override
	public String execute() throws Exception {

		return super.execute();
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public User getUser() {
		return user;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

}