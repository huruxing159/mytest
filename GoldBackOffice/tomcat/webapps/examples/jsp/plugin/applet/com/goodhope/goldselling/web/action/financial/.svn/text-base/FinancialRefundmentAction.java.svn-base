package com.goodhope.goldselling.web.action.financial;

import java.util.Calendar;
import java.util.List;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionSupport;

public class FinancialRefundmentAction extends ActionSupport implements TransactionAware {

	private static final long serialVersionUID = -5838829394967834172L;
	private long orderId;
	private BaseDao baseDao;
	private OrderRecordDao orderRecordDao;

	@Override
	public String execute() throws Exception {
		Order order = this.baseDao.findById(Order.class, orderId);
		if (order != null && order.getState().equals(OrderState.WAIT_REFUNDMENT)) {
			// order.setBalanceTime(Calendar.getInstance());
			order.setState(OrderState.REFUNDMENT_SUCCESS);
			this.baseDao.update(order);
			Vendor currentVendor = order.getCurrentVendor();
			List<OrderRecord> orderRecordList = this.orderRecordDao.getOrderRecordByNumberAndVendor(order.getPurchaseAttemp().getGh_transaction_id(), currentVendor);
			if (orderRecordList.size() > 0) {
				OrderRecord orderRecord = orderRecordList.get(0);
				orderRecord.setEvent(order.getState());
				this.baseDao.update(orderRecord);
			}

		}
		return super.execute();
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}
