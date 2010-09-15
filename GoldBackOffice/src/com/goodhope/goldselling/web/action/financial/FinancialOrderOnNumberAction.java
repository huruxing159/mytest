package com.goodhope.goldselling.web.action.financial;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.persistence.OrderDao;
import com.goodhope.goldselling.web.view.jmesa.FinancialQueryOrderJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class FinancialOrderOnNumberAction extends ActionSupport {

	private static final long serialVersionUID = -4195839283269026023L;
	private FinancialQueryOrderJmesaTemplate financialQueryOrderJmesaTemplate;
	private String jmesaCodeView;
	private OrderDao orderDao;
	private String orderNumber;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public String execute() throws Exception {
		if (orderNumber == null) {
			addActionError("订单号不能为空");
			return ActionSupport.INPUT;
		}
		List<Order> orderByDate = orderDao.getOrderByNumber(orderNumber);
		this.financialQueryOrderJmesaTemplate.setItems(orderByDate);
		this.jmesaCodeView = this.financialQueryOrderJmesaTemplate.render();
		if (this.jmesaCodeView == null) {
			return null;
		}
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

	public void setFinancialQueryOrderJmesaTemplate(FinancialQueryOrderJmesaTemplate financialQueryOrderJmesaTemplate) {
		this.financialQueryOrderJmesaTemplate = financialQueryOrderJmesaTemplate;
	}

}
