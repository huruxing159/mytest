package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Authority;
import com.goodhope.goldselling.persistence.AuthorityDao;

public class AuthorityDaoImpl extends HibernateDaoSupport implements AuthorityDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Authority> getAllAuthorities() {
		return getHibernateTemplate().find("from Authority");
	}

}