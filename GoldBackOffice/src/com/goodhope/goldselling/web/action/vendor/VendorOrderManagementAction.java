package com.goodhope.goldselling.web.action.vendor;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.web.UserAware;
import com.goodhope.goldselling.web.view.jmesa.VendorOrderManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class VendorOrderManagementAction extends ActionSupport implements UserAware, ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -6930104573901641184L;
	private VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String jmesaCodeViewNewRecord;
	private User user;
	private String selectType;
	private Calendar fromDate;
	private Calendar toDate;
	private String orderState;
	private String orderNumber;
	private Map<String, String> orderStateMap = OrderState.VENDOR_STATE;

	@Override
	public String execute() throws Exception {
		this.vendorOrderManagementJmesaTemplate = new VendorOrderManagementJmesaTemplate(this.request, this.response);
		if (selectType != null) {
			return selectType;
		}
		return super.execute();
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setVendorOrderManagementJmesaTemplate(VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate) {
		this.vendorOrderManagementJmesaTemplate = vendorOrderManagementJmesaTemplate;
	}

	public VendorOrderManagementJmesaTemplate getVendorOrderManagementJmesaTemplate() {
		return vendorOrderManagementJmesaTemplate;
	}

	public User getUser() {
		return user;
	}

	public Map<String, String> getOrderStateMap() {
		return orderStateMap;
	}

	public String getJmesaCodeViewNewRecord() {
		return jmesaCodeViewNewRecord;
	}

}
