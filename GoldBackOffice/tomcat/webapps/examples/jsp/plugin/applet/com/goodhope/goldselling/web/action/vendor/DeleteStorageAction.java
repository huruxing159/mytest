package com.goodhope.goldselling.web.action.vendor;

import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteStorageAction extends ActionSupport implements TransactionAware {

	private static final long serialVersionUID = -5593950055526964259L;

	private long[] id;
	private BaseDao baseDao;

	@Override
	public String execute() throws Exception {
		if (id != null) {
			for (long storageListId : id) {
				StorageList storageList = this.baseDao.findById(StorageList.class, storageListId);
				if (storageList != null) {
					this.baseDao.delete(storageList);
				}
			}
		}
		return super.execute();
	}

	public void setId(long[] id) {
		this.id = id;
	}

	public long[] getId() {
		return id;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
