package com.goodhope.goldselling.web.interceptors;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionLogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1784331576307674327L;
	private static final Logger LOG = Logger.getLogger(ExceptionLogInterceptor.class);

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		try {
			return actionInvocation.invoke();
		} catch (Exception e) {
			LOG.error(e, e);
			throw e;
		}
	}

}
