package com.goodhope.goldselling.web.action;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class OurOpenSessionInViewFilter extends OpenSessionInViewFilter {

	public OurOpenSessionInViewFilter() {
		super.setFlushMode(FlushMode.AUTO);
	}

	protected void closeSession(Session session, SessionFactory sessionFactory) {
		session.flush();

		try {
			session.getTransaction().commit();
		} catch (HibernateException e) {
		}

		super.closeSession(session, sessionFactory);
	}

}