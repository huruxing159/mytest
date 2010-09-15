package com.goodhope.goldselling.web.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.goodhope.goldselling.domain.Authority;
import com.goodhope.goldselling.persistence.AuthorityDao;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LocationAwareInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2220836390297904244L;
	private AuthorityDao authorityDao;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri = request.getRequestURI().replaceFirst(request.getContextPath(), "");
		for (Authority authority : authorityDao.getAllAuthorities()) {
			if (uri.equals(authority.getAuthorityURI())) {
				actionInvocation.getInvocationContext().put("location", authority.getAuthorityName());
				return actionInvocation.invoke();
			}
		}
		return actionInvocation.invoke();
	}

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

}
