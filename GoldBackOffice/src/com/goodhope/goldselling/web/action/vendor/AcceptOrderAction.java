package com.goodhope.goldselling.web.action.vendor;

import java.util.Calendar;
import java.util.List;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class AcceptOrderAction extends ActionSupport implements UserAware, TransactionAware {

	private static final long serialVersionUID = 6053767681252961262L;
	private User user;
	private long orderId;
	private BaseDao baseDao;
	private Vendor currentVendor;
	private OrderRecordDao orderRecordDao;
	private Order order;

	@Override
	public String execute() throws Exception {
		/*
		 * 先找到这条订单,验证这条订单的当前的供货商是否是当前的用户，验证成功才能接受订单，否则另做处理（目前只是不执行代码，没做处理）
		 */
		this.order = this.baseDao.findById(Order.class, this.orderId);
		Vendor vendor = order.getCurrentVendor();
		this.currentVendor = this.user.getVendor();
		if (vendor != null && this.currentVendor != null) {
			if (vendor.getName().equals(this.currentVendor.getName())) {
				this.order.setState(OrderState.ACCEPTED_UNDELIVER);
				this.order.setAcceptTime(Calendar.getInstance());
				this.baseDao.update(this.order);

				/*
				 * 更新订单的历史，因为供货商的查询订单历史，是通过orderRecord表，
				 */
				List<OrderRecord> orderRecordList = this.orderRecordDao.getOrderRecordByNumberAndVendor(order.getPurchaseAttemp().getGh_transaction_id(), vendor);
				if (orderRecordList.size() > 0) {
					OrderRecord orderRecord = orderRecordList.get(0);
					orderRecord.setEvent(OrderState.ACCEPTED_UNDELIVER);
					this.baseDao.update(orderRecord);
					return super.execute();
				}
			}
		}
		throw new Exception();
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public User getUser() {
		return user;
	}

}
