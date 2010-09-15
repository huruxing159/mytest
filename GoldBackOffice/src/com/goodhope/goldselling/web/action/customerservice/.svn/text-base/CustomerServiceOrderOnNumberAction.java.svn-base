package com.goodhope.goldselling.web.action.customerservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.persistence.OrderDao;
import com.goodhope.goldselling.web.view.jmesa.CustomerServiceOrderManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerServiceOrderOnNumberAction extends ActionSupport {

	private static final long serialVersionUID = -4195839283269026023L;
	private CustomerServiceOrderManagementJmesaTemplate customerServiceOrderManagementJmesaTemplate;
	private String jmesaCodeView;
	private OrderDao orderDao;
	private String orderNumber;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public String execute() throws Exception {
		if (!StringUtils.hasText(orderNumber)) {
			addActionError("订单号不能为空");
			return ActionSupport.INPUT;
		}
		List<Order> orderByDate = orderDao.getOrderByNumber(orderNumber);
		this.customerServiceOrderManagementJmesaTemplate.setItems(orderByDate);
		this.jmesaCodeView = this.customerServiceOrderManagementJmesaTemplate.render();
		String ajax = request.getParameter("ajax");
		if (ajax != null && ajax.equals("true")) {
			byte[] contents = jmesaCodeView.getBytes("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.getOutputStream().write(contents);
			return null;
		}
		return super.execute();
	}

	public void setCustomerServiceOrderManagementJmesaTemplate(CustomerServiceOrderManagementJmesaTemplate customerServiceOrderManagementJmesaTemplate) {
		this.customerServiceOrderManagementJmesaTemplate = customerServiceOrderManagementJmesaTemplate;
	}

	public String getJmesaCodeView() {
		return jmesaCodeView;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
