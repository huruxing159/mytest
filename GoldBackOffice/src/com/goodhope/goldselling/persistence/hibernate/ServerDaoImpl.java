package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Country;
import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.persistence.ServerDao;

public class ServerDaoImpl extends HibernateDaoSupport implements ServerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Server> getAllServers() {
		return getHibernateTemplate().find("from Server order by id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Server getServer(String country, String servername, String faction) {
		List<Server> server = getHibernateTemplate().find("from Server s where s.country.name=? and s.name=? and s.faction=?", new Object[] { country, servername, faction });
		if (!server.isEmpty()) {
			return server.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Country getCountry(String country) {
		List<Country> countries = getHibernateTemplate().find("from Country c where c.name=?", country);
		if (!countries.isEmpty()) {
			return countries.get(0);
		}
		return null;
	}
}
