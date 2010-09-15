package com.goodhope.goldselling.persistence.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.persistence.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	@Override
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	@Override
	public void update(Object o) {
		getHibernateTemplate().update(o);
	}

	@Override
	public <T> T findById(Class<T> entityClass, long userId) {
		return getHibernateTemplate().get(entityClass, userId);
	}

	@Override
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public Object merge(Object entity) {
		return getHibernateTemplate().merge(entity);

	}

	@Override
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);

	}

}
