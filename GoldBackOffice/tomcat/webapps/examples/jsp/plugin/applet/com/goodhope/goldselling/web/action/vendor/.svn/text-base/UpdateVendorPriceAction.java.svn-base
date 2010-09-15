package com.goodhope.goldselling.web.action.vendor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.VendorServerPrice;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.persistence.StorageDao;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateVendorPriceAction extends ActionSupport implements UserAware, SessionAware {

	private static final long serialVersionUID = 1981593547163637421L;
	private List<VendorServerPrice> servers;
	private StorageDao storageDao;
	private BaseDao baseDao;
	private ServerDao serverDao;
	private User user;
	private Map<String, Object> session;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		List<VendorServerPrice> serverInSession = (List<VendorServerPrice>) session.get("vendorStorageSetting");
		if (serverInSession != null) {
			this.servers = serverInSession;
		}
		for (VendorServerPrice server : servers) {
			Server s = serverDao.getServer(server.getCountry(), server.getServer(), server.getFaction());
			StorageList temp = storageDao.getStorage(s, user.getVendor().getId());
			if (temp == null) {
				temp = new StorageList();
				temp.setServer(s);
				temp.setVendor(user.getVendor());
				temp.setPrice(new BigDecimal(server.getPrice()));
				temp.setAmount(Long.valueOf(server.getAmount()));
				baseDao.save(temp);
			} else {
				temp.setPrice(new BigDecimal(server.getPrice()));
				temp.setAmount(Long.valueOf(server.getAmount()));
				baseDao.update(temp);
			}
		}
		return SUCCESS;
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;
	}

	public void setServers(List<VendorServerPrice> servers) {
		this.servers = servers;
	}

	public List<VendorServerPrice> getServers() {
		return this.servers;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
