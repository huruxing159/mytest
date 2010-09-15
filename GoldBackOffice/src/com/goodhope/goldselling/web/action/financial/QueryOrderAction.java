package com.goodhope.goldselling.web.action.financial;

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
import com.goodhope.goldselling.web.view.jmesa.FinancialQueryOrderJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class QueryOrderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 8522447687632339121L;
	private FinancialQueryOrderJmesaTemplate financialQueryOrderJmesaTemplate;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, String> orderStateMap = OrderState.FINANCIAL_STATE;
	private VendorDao vendorDao;
	private String jmesaCodeView;
	private String selectType;
	private Calendar fromDate;
	private Calendar toDate;

	private String orderState;
	private String orderNumber;
	private String orderVendor;
	private List<Vendor> allVendors;
	private List<User> customerServices;
	private UserDao userDao;

	@Override
	public String execute() throws Exception {
		allVendors = this.vendorDao.getAllVendors();
		this.customerServices = this.userDao.getCustomerServices();
		if (selectType != null) {
			this.financialQueryOrderJmesaTemplate = new FinancialQueryOrderJmesaTemplate(request, response);
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

	public FinancialQueryOrderJmesaTemplate getFinancialQueryOrderJmesaTemplate() {
		return financialQueryOrderJmesaTemplate;
	}
}