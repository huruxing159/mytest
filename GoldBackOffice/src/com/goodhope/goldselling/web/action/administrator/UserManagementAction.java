package com.goodhope.goldselling.web.action.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.persistence.UserDao;
import com.goodhope.goldselling.web.view.jmesa.UserManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class UserManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 6581758155710544979L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String jmesaView;
	private UserManagementJmesaTemplate userManagementJmesaTemplate;
	private UserDao userDao;

	@Override
	public String execute() throws Exception {

		userManagementJmesaTemplate = new UserManagementJmesaTemplate(userDao);
		jmesaView = userManagementJmesaTemplate.render();
		String ajax = request.getParameter("ajax");
		if (ajax != null && ajax.equals("true")) {
			byte[] contents = jmesaView.getBytes("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.getOutputStream().write(contents);
			return null;
		}
		return super.execute();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getJmesaView() {
		return jmesaView;
	}

	public void setUserManagementJmesaTemplate(UserManagementJmesaTemplate userManagementJmesaTemplate) {
		this.userManagementJmesaTemplate = userManagementJmesaTemplate;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
