package com.goodhope.goldselling.web.action.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.persistence.RoleDao;
import com.goodhope.goldselling.web.view.jmesa.RoleManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class RoleManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 7314403646658552979L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String jmesaViewRole;
	private RoleManagementJmesaTemplate roleManagementJmesaTemplate;
	private RoleDao roleDao;

	@Override
	public String execute() throws Exception {
		roleManagementJmesaTemplate = new RoleManagementJmesaTemplate(roleDao);
		jmesaViewRole = roleManagementJmesaTemplate.render();
		String ajax = request.getParameter("ajax");
		if (ajax != null && ajax.equals("true")) {
			byte[] contents = jmesaViewRole.getBytes("UTF-8");
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

	public String getJmesaViewRole() {
		return jmesaViewRole;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
