package com.goodhope.goldselling.web.action.financial;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.persistence.OrderDao;
import com.goodhope.goldselling.web.view.jmesa.FinancialQueryOrderJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class FinancialOrderQueryAction extends ActionSupport {

	private static final long serialVersionUID = -8110708565666609780L;
	private String orderVendor;
	private OrderDao orderDao;
	private FinancialQueryOrderJmesaTemplate financialQueryOrderJmesaTemplate;
	private String jmesaCodeView;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Calendar fromDate;
	private Calendar toDate;
	private Calendar toDateNextDate;

	private String orderState;

	@Override
	public String execute() throws Exception {
		toDateNextDate = toDate;
		toDateNextDate.add(Calendar.DATE, 1);
		List<Order> orderByDate = orderDao.queryOrderByFinancial(orderVendor, orderState, fromDate, toDateNextDate);
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

	public void setOrderVendor(String orderVendor) {
		this.orderVendor = orderVendor;
	}

	public String getOrderVendor() {
		return orderVendor;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void validate() {
		if (fromDate == null || toDate == null) {
			addActionError("起始日期或结束日期不能为空");
			return;
		}
		if (fromDate.compareTo(toDate) > 0) {
			addActionError("起始日期不能大于结束日期");
			return;
		}
		super.validate();
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

	public String getFormatFromDate() {
		return DateFormatUtils.format(fromDate, "yyyy-MM-dd");
	}

	public String getFormatToDate() {
		return DateFormatUtils.format(toDate, "yyyy-MM-dd");
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setFinancialQueryOrderJmesaTemplate(FinancialQueryOrderJmesaTemplate financialQueryOrderJmesaTemplate) {
		this.financialQueryOrderJmesaTemplate = financialQueryOrderJmesaTemplate;
	}

}