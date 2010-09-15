package com.goodhope.goldselling.web.action.customerservice;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.UserDao;
import com.goodhope.goldselling.persistence.VendorDao;
import com.goodhope.goldselling.web.UserAware;
import com.goodhope.goldselling.web.view.jmesa.CustomerServiceOrderManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerServiceOrderManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, UserAware {

	private static final long serialVersionUID = 1857659283840639667L;
	private CustomerServiceOrderManagementJmesaTemplate customerServiceOrderManagementJmesaTemplate;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, String> orderStateMap = OrderState.CUSTOMSERVICE_STATE;
	private VendorDao vendorDao;
	private String jmesaCodeView;
	private String selectType;
	private Calendar fromDate;
	private Calendar toDate;
	private String orderState;
	private String orderNumber;
	private String orderVendor;
	private List<Vendor> allVendors;
	private User user;
	private List<User> customerServices;
	private UserDao userDao;

	@Override
	public String execute() throws Exception {
		allVendors = this.vendorDao.getAllVendors();
		this.customerServices = this.userDao.getCustomerServices();
		if (selectType != null) {
			this.customerServiceOrderManagementJmesaTemplate = new CustomerServiceOrderManagementJmesaTemplate(request, response);
			this.customerServiceOrderManagementJmesaTemplate.setUser(user);
			return selectType;
		}
		return super.execute();
	}

	public Map<String, String> getOrderStateMap() {
		return orderStateMap;
	}

	public String getJmesaCodeView() {
		return jmesaCodeView;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getSelectType() {
		return selectType;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public CustomerServiceOrderManagementJmesaTemplate getCustomerServiceOrderManagementJmesaTemplate() {
		return customerServiceOrderManagementJmesaTemplate;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
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

	public void setOrderVendor(String orderVendor) {
		this.orderVendor = orderVendor;
	}

	public String getOrderVendor() {
		return orderVendor;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public List<Vendor> getAllVendors() {
		return allVendors;
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public List<User> getCustomerServices() {
		return customerServices;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
