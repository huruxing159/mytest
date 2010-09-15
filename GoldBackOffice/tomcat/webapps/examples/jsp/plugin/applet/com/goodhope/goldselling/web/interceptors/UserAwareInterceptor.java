package com.goodhope.goldselling.web.interceptors;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAwareInterceptor extends AbstractInterceptor {

	private User user;
	private static final long serialVersionUID = -3821831802144987375L;
	private static final Logger LOG = Logger.getLogger(UserAwareInterceptor.class);

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			LOG.error("context =null");
			return actionInvocation.invoke();
		}
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			LOG.error("authentication =null");
			return actionInvocation.invoke();
		}
		user = (User) authentication.getPrincipal();
		if (user != null) {
			actionInvocation.getInvocationContext().put("username", user.getUsername());
			actionInvocation.getInvocationContext().put("userIp", ServletActionContext.getRequest().getRemoteHost());
			if (action instanceof UserAware) {
				((UserAware) action).setCurrentUser(user);
			}
		}
		return actionInvocation.invoke();
	}

}
