package com.goodhope.goldselling.web.action.vendor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.OrderDao;
import com.goodhope.goldselling.web.UserAware;
import com.goodhope.goldselling.web.view.jmesa.NewOrderJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class NewOrderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, UserAware {

	private static final long serialVersionUID = -1375593408770462077L;
	private String jmesaCodeViewNewOrder;
	private NewOrderJmesaTemplate newOrderJmesaTemplate;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDao orderDao;
	private User user;

	@Override
	public String execute() throws Exception {
		this.newOrderJmesaTemplate = new NewOrderJmesaTemplate(request, response);
		List<Order> allOrdersByVendor = this.orderDao.getAllNewOrdersByVendor(this.user.getVendor(), OrderState.ASSIGNED_UNACCEPT, OrderState.ACCEPTED_UNDELIVER,OrderState.DELIVERED_UNCHECK,OrderState.CHECKE_FAILE);
		this.newOrderJmesaTemplate.setItems(allOrdersByVendor);
		this.jmesaCodeViewNewOrder = this.newOrderJmesaTemplate.render();
		String ajax = request.getParameter("ajax");
		if (ajax != null && ajax.equals("true")) {
			byte[] contents = jmesaCodeViewNewOrder.getBytes("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.getOutputStream().write(contents);
			return null;
		}
		return super.execute();
	}

	public String getJmesaCodeViewNewOrder() {
		return jmesaCodeViewNewOrder;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;
	}

}
