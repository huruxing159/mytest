package com.goodhope.goldselling.web.action;

import java.util.Calendar;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport implements UserAware ,TransactionAware{

	private static final long serialVersionUID = 482845452838978545L;
	private User user;
	private BaseDao baseDao;

	@Override
	public String execute() throws Exception {
		this.user.setLastLoginTime(Calendar.getInstance());
		this.user=(User)this.baseDao.merge(this.user);
		this.baseDao.update(this.user);
		return super.execute();
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
