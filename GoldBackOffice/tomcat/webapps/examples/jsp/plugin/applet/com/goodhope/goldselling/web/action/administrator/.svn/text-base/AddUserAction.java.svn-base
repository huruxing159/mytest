package com.goodhope.goldselling.web.action.administrator;

import java.util.Calendar;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.UserDao;
import com.goodhope.goldselling.service.MD5Service;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class AddUserAction extends ActionSupport implements TransactionAware {

	private static final long serialVersionUID = -1529988075938986380L;
	private User user;
	private BaseDao baseDao;
	private MD5Service md5Service;
	private UserDao userDao;

	@Override
	public String execute() throws Exception {
		if (userDao.getUserbyName(user.getUsername()) == null) {
			user.setPassword(md5Service.md5(user.getPassword()));
			user.setCreateTime(Calendar.getInstance());
			baseDao.save(user);
			addActionMessage("用户" + user.getUsername() + "添加成功");
		} else {
			addActionMessage("用户" + user.getUsername() + "已经存在,添加失败！！！");
		}
		return super.execute();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setMd5Service(MD5Service md5Service) {
		this.md5Service = md5Service;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
