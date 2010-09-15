package com.goodhope.goldselling.persistence.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.TimerWatch;
import com.goodhope.goldselling.persistence.TimerWatchDao;

public class TimerWatchDaoImpl extends HibernateDaoSupport implements TimerWatchDao {

	@SuppressWarnings("unchecked")
	@Override
	public TimerWatch getTimerWatchByClassName(String className) {
		List<TimerWatch> timerWList = getHibernateTemplate().find("from TimerWatch tw where tw.timerName=?", className);
		if (timerWList.size() != 0) {
			return timerWList.get(0);
		}
		return null;
	}

}
