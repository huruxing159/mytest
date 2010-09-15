package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.InformBackoffice;
import com.goodhope.goldselling.persistence.InformBackofficeDao;

public class InformBackofficeDaoImpl extends HibernateDaoSupport implements InformBackofficeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<InformBackoffice> getAllInformBackoffices() {
		return getHibernateTemplate().find("from InformBackoffice order by id");
	}

}
