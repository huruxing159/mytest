package com.goodhope.goldselling.web.action.administrator;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.persistence.VendorDao;
import com.goodhope.goldselling.web.view.jmesa.VendorManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class VendorManagementAction extends ActionSupport implements ServletResponseAware {
	private static final long serialVersionUID = -3629780376352850768L;
	private HttpServletResponse response;
	private String jmesaView;
	private VendorManagementJmesaTemplate vendorManagementJmesaTemplate;
	private VendorDao vendorDao;
	private String ajax;

	@Override
	public String execute() throws Exception {
		vendorManagementJmesaTemplate = new VendorManagementJmesaTemplate(vendorDao);
		jmesaView = vendorManagementJmesaTemplate.render();
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
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getJmesaView() {
		return jmesaView;
	}

	public void setVendorManagementJmesaTemplate(VendorManagementJmesaTemplate vendorManagementJmesaTemplate) {
		this.vendorManagementJmesaTemplate = vendorManagementJmesaTemplate;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

}
