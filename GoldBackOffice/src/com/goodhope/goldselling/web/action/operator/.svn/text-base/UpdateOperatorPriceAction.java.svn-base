package com.goodhope.goldselling.web.action.operator;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.goodhope.goldselling.domain.OperatorServerPrice;
import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateOperatorPriceAction extends ActionSupport implements TransactionAware, SessionAware {

	private static final long serialVersionUID = 1981593547163637421L;
	private List<OperatorServerPrice> servers = new ArrayList<OperatorServerPrice>();
	private ServerDao serverDao;
	private BaseDao baseDao;
	private Map<String, Object> session;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		List<OperatorServerPrice> serverInSession = (List<OperatorServerPrice>) session.get("operatorServerSetting");
		if (serverInSession != null) {
			this.servers = serverInSession;
		}
		for (OperatorServerPrice server : servers) {
			Server temp = serverDao.getServer(server.getCountry(), server.getServer(), server.getFaction());
			if (temp != null) {
				updateServer(server, temp);
			} else {
				saveServer(server);
			}
		}
		return SUCCESS;
	}

	private void saveServer(OperatorServerPrice server) {
		Server s = new Server();
		s.setCountry(serverDao.getCountry(server.getCountry()));
		s.setName(server.getServer());
		s.setFaction(server.getFaction());
		s.setHistory_unit_price(new BigDecimal(server.getPrice()));
		s.setCurrent_unit_price(new BigDecimal(server.getPrice()));
		s.setPriceLimit(new BigDecimal(server.getPriceLimit()));
		s.setAmountLimit(new BigDecimal(server.getAmountLimit()).longValue());
		s.setUpdated_at(Calendar.getInstance());
		s.setHistory_price_strategy("LinearPrice");
		s.setCurrent_price_strategy("LinearPrice");
		baseDao.save(s);
	}

	private void updateServer(OperatorServerPrice server, Server temp) {
		temp.setHistory_unit_price(temp.getCurrent_unit_price());
		temp.setCurrent_unit_price(new BigDecimal(server.getPrice()));
		temp.setPriceLimit(new BigDecimal(server.getPriceLimit()));
		temp.setAmountLimit(new BigDecimal(server.getAmountLimit()).longValue());
		baseDao.update(temp);
	}

	public void setServers(List<OperatorServerPrice> servers) {
		this.servers = servers;
	}

	public List<OperatorServerPrice> getServers() {
		return this.servers;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
