package com.goodhope.goldselling.web.view.jmesa;

import org.apache.commons.lang.StringUtils;
import org.jmesa.core.filter.FilterMatcher;

public class NumericTypeFilterMatcher implements FilterMatcher {
	public boolean evaluate(Object itemValue, String filterValue) {
		if (itemValue == null || filterValue == null) {
			return false;
		}
		String item = StringUtils.lowerCase(String.valueOf(itemValue));
		String filter = StringUtils.lowerCase(String.valueOf(filterValue));
		if (filter.equals(item)) {
			return true;
		}
		return false;
	}
}