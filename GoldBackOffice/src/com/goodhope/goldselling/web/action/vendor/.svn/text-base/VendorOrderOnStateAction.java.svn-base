package com.goodhope.goldselling.web.action.vendor;

import java.util.List;

import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.view.jmesa.VendorOrderManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class VendorOrderOnStateAction extends ActionSupport {

	private static final long serialVersionUID = 6964763033805840459L;
	private VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate;
	private String orderState;
	private User user;
	private OrderRecordDao orderRecordDao;
	private String jmesaCodeView;

	@Override
	public String execute() throws Exception {
		List<OrderRecord> orderRecordByStateAndVendor = orderRecordDao.getOrderRecordByStateAndVendor(OrderState.VENDOR_STATE.get(orderState), this.user.getVendor());
		this.vendorOrderManagementJmesaTemplate.setItems(orderRecordByStateAndVendor);
		this.jmesaCodeView = this.vendorOrderManagementJmesaTemplate.render();
		return super.execute();
	}

	public void setVendorOrderManagementJmesaTemplate(VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate) {
		this.vendorOrderManagementJmesaTemplate = vendorOrderManagementJmesaTemplate;
	}



	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public OrderRecordDao getOrderRecordDao() {
		return orderRecordDao;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJmesaCodeView() {
		return jmesaCodeView;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderState() {
		return orderState;
	}

}
