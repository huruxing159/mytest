package com.goodhope.goldselling.web.action.administrator;

import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.VendorDao;
import com.opensymphony.xwork2.ActionSupport;

public class AddVendorAction extends ActionSupport {
	private static final long serialVersionUID = 1896145631582227305L;
	private VendorDao vendorDao;
	private BaseDao baseDao;
	private String name;

	@Override
	public String execute() throws Exception {
		if (vendorDao.getVendorByName(name) != null) {
			addActionMessage("用户" + name + "已经存在,添加失败！！！");
		} else {
			Vendor vendor = new Vendor();
			vendor.setName(name);
			baseDao.save(vendor);
			addActionMessage("添加供应商" + name + "成功");
		}
		return SUCCESS;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setName(String name) {
		this.name = name;
	}

}
