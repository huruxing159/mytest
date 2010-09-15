package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.persistence.StorageDao;

public class StorageDaoImpl extends HibernateDaoSupport implements StorageDao {

	@SuppressWarnings("unchecked")
	@Override
	public StorageList getStorage(Server s, long vendorId) {
		List<StorageList> storage = getHibernateTemplate().find("from StorageList s where s.server=? and s.vendor.id=? ", new Object[] { s, vendorId });
		if (!storage.isEmpty()) {
			return storage.get(0);
		}
		return null;
	}

}
