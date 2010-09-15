package com.goodhope.goldselling.web.action.administrator;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.UserDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyVendorUserAction extends ActionSupport implements TransactionAware {

	private static final long serialVersionUID = 1375475264591688632L;
	private BaseDao baseDao;
	private UserDao userDao;
	private String[] users;
	private long vendorId;

	@Override
	public String execute() throws Exception {
		Vendor vendor = baseDao.findById(Vendor.class, vendorId);
		if (vendor == null) {
			return ActionSupport.INPUT;
		}
		vendor.getUsers().clear();
		if (users != null) {
			for (String username : users) {
				User user = userDao.getUserbyName(username);
				if (user.getVendor() != null && !user.getVendor().getName().equals(vendor.getName())) {
					addActionMessage("对不起，用户" + user.getUsername() + "已经有所属供应商了");
				} else {
					vendor.getUsers().add(user);
				}
			}
		}
		baseDao.update(vendor);
		return ActionSupport.SUCCESS;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUsers(String[] users) {
		this.users = users;
	}

}
