package com.goodhope.goldselling.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class MoneySymbolService {

	private static final Map<String, String> SYMBOL = new HashMap<String, String>();
	private static final Logger LOG = Logger.getLogger(MoneySymbolService.class);
	static {
		SYMBOL.put("RMB", "¥");
		SYMBOL.put("GBP", "£");
		SYMBOL.put("USD", "$");
		SYMBOL.put("EUR", "€");
	}

	public static String getSymbol(String moneyType) {
		try {
			return SYMBOL.get(moneyType.toUpperCase());
		} catch (Exception e) {
			LOG.error(e);
		}
		return "?";
	}

}
