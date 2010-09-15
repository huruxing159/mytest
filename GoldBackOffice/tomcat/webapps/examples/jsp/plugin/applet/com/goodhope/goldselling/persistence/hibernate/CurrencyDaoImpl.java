package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Currency;
import com.goodhope.goldselling.persistence.CurrencyDao;

public class CurrencyDaoImpl extends HibernateDaoSupport implements CurrencyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Currency> getAllCurrencies() {
		return getHibernateTemplate().find("from Currency order by id");
	}

}
