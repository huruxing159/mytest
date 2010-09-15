package com.goodhope.goldselling.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goodhope.goldselling.domain.Country;
import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.persistence.BaseDao;

public class ServerCrawlService {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "applicationContext-hibernate.xml", "applicationContext-dao.xml" });
		BaseDao baseDao = (BaseDao) context.getBean("baseDao");
		addUSServers(baseDao);
		addEUServers(baseDao);
	}

	private static void addEUServers(BaseDao baseDao) throws MalformedURLException, IOException {
		Country eu = baseDao.findById(Country.class, 2l);
		URL urlEU = new URL("http://www.wow-europe.com/realmstatus/index.html");
		addServers(baseDao, eu, urlEU);
	}

	private static void addUSServers(BaseDao baseDao) throws MalformedURLException, IOException {
		Country us = baseDao.findById(Country.class, 1l);
		URL urlUS = new URL("http://www.worldofwarcraft.com/realmstatus/compat.html");
		addServers(baseDao, us, urlUS);
	}

	private static void addServers(BaseDao baseDao, Country us, URL url) throws IOException {
		Source source = new Source(url);
		List<Element> allServer = source.getAllElementsByClass("smallBold");
		for (Element element : allServer) {
			saveNewServer(baseDao, us, element);
		}
	}

	private static void saveNewServer(BaseDao baseDao, Country country, Element element) {
		Server server = new Server();
		server.setName(element.getContent().getTextExtractor().toString() + " - " + country.getName());
		server.setCountry(country);
		server.setCreated_at(Calendar.getInstance());
		server.setUpdated_at(Calendar.getInstance());
		server.setHistory_unit_price(new BigDecimal(0));//这个必须设置，前台有取，不然报错
		server.setHistory_price_strategy("LinearPrice");
		server.setCurrent_price_strategy("LinearPrice");
		server.setFaction("Alliance");
		baseDao.save(server);
		server.setFaction("Horde");
		baseDao.save(server);
	}
}
