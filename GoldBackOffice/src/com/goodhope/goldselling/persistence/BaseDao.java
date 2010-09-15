package com.goodhope.goldselling.persistence;

public interface BaseDao {

	public void save(Object o);

	public void update(Object o);

	public <T> T findById(Class<T> entityClass, long userId);

	public void delete(Object entity);

	public Object merge(Object entity);

	public void saveOrUpdate(Object entity);

}
