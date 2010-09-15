package com.goodhope.goldselling.web.action.vendor;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.view.jmesa.VendorOrderManagementJmesaTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class VendorOrderOnDateAction extends ActionSupport {

	private static final long serialVersionUID = 6964763033805840459L;
	private VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate;
	private String jmesaCodeView;
	private OrderRecordDao orderRecordDao;
	private Calendar fromDate;
	private Calendar toDate;
	private Calendar toDateNextDate;
	private User user;

	@Override
	public String execute() throws Exception {
		toDateNextDate = toDate;
		toDateNextDate.add(Calendar.DATE, 1);
		List<OrderRecord> orderRecordByDateAndVendor = orderRecordDao.getOrderRecordByDateAndVendor(fromDate, toDateNextDate, this.user.getVendor());
		this.vendorOrderManagementJmesaTemplate.setItems(orderRecordByDateAndVendor);
		this.jmesaCodeView = this.vendorOrderManagementJmesaTemplate.render();
		return super.execute();
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

	public void setVendorOrderManagementJmesaTemplate(VendorOrderManagementJmesaTemplate vendorOrderManagementJmesaTemplate) {
		this.vendorOrderManagementJmesaTemplate = vendorOrderManagementJmesaTemplate;
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

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJmesaCodeView() {
		return jmesaCodeView;
	}

	public String getFormatFromDate() {
		return DateFormatUtils.format(fromDate, "yyyy-MM-dd");
	}

	public String getFormatToDate() {
		return DateFormatUtils.format(toDate, "yyyy-MM-dd");
	}

}
