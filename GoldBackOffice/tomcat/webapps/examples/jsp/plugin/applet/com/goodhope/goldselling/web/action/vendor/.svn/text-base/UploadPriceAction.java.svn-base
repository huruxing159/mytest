package com.goodhope.goldselling.web.action.vendor;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.goodhope.goldselling.domain.VendorServerPrice;
import com.goodhope.goldselling.service.VendorUploadPriceService;
import com.opensymphony.xwork2.ActionSupport;

public class UploadPriceAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 7151574631253835952L;

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private VendorUploadPriceService vendorUploadPriceService;
	private static final Logger LOG = Logger.getLogger(UploadPriceAction.class);
	private List<VendorServerPrice> servers;
	private Map<String, Object> session;

	public String execute() throws Exception {
		LOG.debug(uploadContentType);
		LOG.debug(uploadFileName);
		List<String> error = vendorUploadPriceService.verify(upload);
		if (error.isEmpty()) {
			servers = vendorUploadPriceService.getServerByUploadPrice(upload);
			session.put("vendorStorageSetting", servers);
			return SUCCESS;
		} else {
			setActionErrors(error);
			return INPUT;
		}
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setVendorUploadPriceService(VendorUploadPriceService uploadPriceService) {
		this.vendorUploadPriceService = uploadPriceService;
	}

	public List<VendorServerPrice> getServers() {
		return this.servers;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
