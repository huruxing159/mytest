package com.goodhope.goldselling.web.action.operator;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteServerAction extends ActionSupport implements TransactionAware {

	private static final long serialVersionUID = -5662981077994782405L;

	private long[] id;
	private BaseDao baseDao;

	@Override
	public String execute() throws Exception {
		if (id != null) {
			for (long serverId : id) {
				Server server = this.baseDao.findById(Server.class, serverId);
				if (server != null) {
					this.baseDao.delete(server);
				}
			}
		}
		return super.execute();
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setId(long[] id) {
		this.id = id;
	}

	public long[] getId() {
		return id;
	}

}
