package com.goodhope.goldselling.web.view.jmesa.wrapper;

import java.util.Set;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;

public class ServerSettingWrapper {
	private Server server;

	public ServerSettingWrapper() {
	}

	public ServerSettingWrapper(Server server) {
		this.server = server;
	}

	public String getJmesaDelete() {
		return "";
	}

	public String getJmesaCheckBox() {
		return "";
	}

	public long getId() {
		return server.getId();
	}

	public long getGoldAmount() {
		long goldAmount = 0l;
		Set<StorageList> storageLists = server.getStorageLists();
		for (StorageList storage : storageLists) {
			goldAmount += storage.getAmount();
		}
		return goldAmount;
	}

	public Server getServer() {
		return server;
	}

	public String getCurrent_unit_price() {
		return server.getCurrent_unit_price().toPlainString();
	}

	public String getPriceLimit() {
		return server.getPriceLimit().toPlainString();
	}
}
