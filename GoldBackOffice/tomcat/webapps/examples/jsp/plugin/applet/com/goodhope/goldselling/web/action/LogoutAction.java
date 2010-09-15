package com.goodhope.goldselling.web.action;

import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	private static final long serialVersionUID = -705994082345982783L;

	@Override
	public String execute() throws Exception {
		SecurityContextHolder.clearContext();
		return super.execute();
	}

}
