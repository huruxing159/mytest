package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.VendorDao;

public class VendorDaoImpl extends HibernateDaoSupport implements VendorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getAllVendors() {
		return getHibernateTemplate().find("from Vendor order by id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vendor getVendorByName(String name) {
		List<Vendor> vendors = getHibernateTemplate().find("from Vendor v where v.name=?", name);
		if (!vendors.isEmpty()) {
			return vendors.get(0);
		}
		return null;
	}

}
