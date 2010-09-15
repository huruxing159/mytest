package com.goodhope.goldselling.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -5179864939155660424L;
	private String message = "";

	@Override
	public String execute() throws Exception {
		if (message.contains("loginParameterError")) {
			addActionMessage("用户名或密码错误！");
		}
		return super.execute();
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
