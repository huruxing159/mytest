package com.goodhope.goldselling.web.view.jmesa.wrapper;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;

public class VendorStorageSettingWrapper {

	private StorageList storageList;
	private Server server;

	public VendorStorageSettingWrapper() {
	}

	public VendorStorageSettingWrapper(StorageList storageList, Server server) {
		this.setStorageList(storageList);
		this.setServer(server);
	}

	public void setStorageList(StorageList storageList) {
		this.storageList = storageList;
	}

	public StorageList getStorageList() {
		return storageList;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Server getServer() {
		return server;
	}

	public String getPrice() {

		if (storageList != null) {
			return storageList.getPrice().toPlainString();
		}
		return "0";
	}

	public long getAmount() {
		if (storageList != null) {
			return storageList.getAmount();
		}
		return 0l;
	}

	public String getPriceLimit() {
		return server.getPriceLimit().toPlainString();
	}

	public long getAmountLimit() {
		return server.getAmountLimit();
	}
}
