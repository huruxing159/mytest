package com.goodhope.goldselling.web.action.administrator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.UserDao;
import com.opensymphony.xwork2.ActionSupport;

public class EditVendorUserAction extends ActionSupport {

	private static final long serialVersionUID = -3531029732957943641L;
	private BaseDao baseDao;
	private UserDao userDao;
	private long vendorId;
	private Map<String, String> userMap = new HashMap<String, String>();
	private Vendor vendor;;

	@Override
	public String execute() throws Exception {
		vendor = baseDao.findById(Vendor.class, vendorId);
		List<User> allUsers = userDao.getUsersByVendorId(vendorId);
		if (allUsers.isEmpty()) {
			addActionMessage("还未添加用户或用户都有所属的供应商了");
		} else {
			for (User user : allUsers) {
				getUserMap().put(user.getUsername(), "");
			}
			for (User user : vendor.getUsers()) {
				getUserMap().put(user.getUsername(), "checked='checked'");
			}
		}
		return SUCCESS;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Map<String, String> getUserMap() {
		return userMap;
	}

}
