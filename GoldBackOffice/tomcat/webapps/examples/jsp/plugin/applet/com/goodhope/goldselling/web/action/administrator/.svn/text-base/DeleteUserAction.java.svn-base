package com.goodhope.goldselling.web.action.administrator;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteUserAction extends ActionSupport implements TransactionAware, UserAware {

	private static final long serialVersionUID = -8333302325405555361L;
	private long userId;
	private BaseDao baseDao;
	private User currentUser;

	@Override
	public String execute() throws Exception {
		User user = baseDao.findById(User.class, this.userId);
		if (user != null && !(currentUser.getUsername().equals(user.getUsername()))) {
			baseDao.delete(user);
		}

		return super.execute();
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

}
