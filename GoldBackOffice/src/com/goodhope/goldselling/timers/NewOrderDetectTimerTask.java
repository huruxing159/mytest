package com.goodhope.goldselling.timers;

import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import com.goodhope.goldselling.domain.InformBackoffice;
import com.goodhope.goldselling.domain.TimerWatch;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.InformBackofficeDao;
import com.goodhope.goldselling.persistence.TimerWatchDao;
import com.goodhope.goldselling.service.DispatchStrategyService;

public class NewOrderDetectTimerTask extends TimerTask {

	private InformBackofficeDao informBackofficeDao;
	private DispatchStrategyService dispatchStrategyService;
	private TimerWatchDao timerWatchDao;
	private BaseDao baseDao;

	@Override
	public void run() {
		TimerWatch tw = timerWatchDao.getTimerWatchByClassName(this.getClass().getName());
		if (tw == null) {
			tw = new TimerWatch();
			tw.setTimerName(this.getClass().getName());
		}
		tw.setActiveTime(Calendar.getInstance());
		this.baseDao.saveOrUpdate(tw);

		List<InformBackoffice> informations = informBackofficeDao.getAllInformBackoffices();
		for (InformBackoffice i : informations) {
			dispatchStrategyService.dispatch(i);
		}

	}

	public void setInformBackofficeDao(InformBackofficeDao informBackofficeDao) {
		this.informBackofficeDao = informBackofficeDao;
	}

	public void setDispatchStrategyService(DispatchStrategyService dispatchStrategyService) {
		this.dispatchStrategyService = dispatchStrategyService;
	}

	public void setTimerWatchDao(TimerWatchDao timerWatchDao) {
		this.timerWatchDao = timerWatchDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
