package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Role;
import com.goodhope.goldselling.persistence.RoleDao;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		return getHibernateTemplate().find("from Role order by id");
	}

}
