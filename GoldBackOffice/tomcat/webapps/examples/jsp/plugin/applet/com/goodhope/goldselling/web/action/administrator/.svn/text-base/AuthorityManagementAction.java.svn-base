package com.goodhope.goldselling.web.action.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.persistence.AuthorityDao;
import com.goodhope.goldselling.web.view.jmesa.AuthorityManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class AuthorityManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 8517750269667310850L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String jmesaViewAuthority;
	private AuthorityManagementJmesaTemplate authorityManagementJmesaTemplate;
	private AuthorityDao authorityDao;

	@Override
	public String execute() throws Exception {
		authorityManagementJmesaTemplate = new AuthorityManagementJmesaTemplate(authorityDao);
		jmesaViewAuthority = authorityManagementJmesaTemplate.render();
		String ajax = request.getParameter("ajax");
		if (ajax != null && ajax.equals("true")) {
			byte[] contents = jmesaViewAuthority.getBytes("UTF-8");
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

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	public String getJmesaViewAuthority() {
		return jmesaViewAuthority;
	}

}
