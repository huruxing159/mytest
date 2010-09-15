package com.goodhope.goldselling.web.action.vendor;

import java.util.List;

import org.springframework.util.StringUtils;

import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.view.jmesa.VendorOrderManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class VendorOrderOnNumberAction extends ActionSupport {

	private static final long serialVersionUID = 6964763033805840459L;
	private VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate;
	private User user;
	private OrderRecordDao orderRecordDao;
	private String jmesaCodeView;
	private String orderNumber;

	@Override
	public String execute() throws Exception {
		List<OrderRecord> orderRecordByNumberAndVendor = this.orderRecordDao.getOrderRecordByNumberAndVendor(orderNumber, this.user.getVendor());
		this.vendorOrderManagementJmesaTemplate.setItems(orderRecordByNumberAndVendor);
		this.jmesaCodeView = this.vendorOrderManagementJmesaTemplate.render();
		return super.execute();
	}

	@Override
	public void validate() {
		if (!StringUtils.hasText(this.orderNumber)) {
			addActionError("订单号不能为空");
			return;
		}
		super.validate();
	}

	public void setVendorOrderManagementJmesaTemplate(VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate) {
		this.vendorOrderManagementJmesaTemplate = vendorOrderManagementJmesaTemplate;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public void setJmesaCodeView(String jmesaCodeView) {
		this.jmesaCodeView = jmesaCodeView;
	}

	public String getJmesaCodeView() {
		return jmesaCodeView;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

}
