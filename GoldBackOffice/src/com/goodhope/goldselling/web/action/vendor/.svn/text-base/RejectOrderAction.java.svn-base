package com.goodhope.goldselling.web.action.vendor;

import java.util.List;

import org.apache.log4j.Logger;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.OrderRecordDao;
import com.goodhope.goldselling.persistence.ServerDao;
import com.goodhope.goldselling.persistence.StorageDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class RejectOrderAction extends ActionSupport implements UserAware, TransactionAware {

	private static final long serialVersionUID = -4248603888593146752L;
	private static final Logger LOG = Logger.getLogger(RejectOrderAction.class);
	private User user;
	private long orderId;
	private BaseDao baseDao;
	private Vendor currentVendor;
	private OrderRecordDao orderRecordDao;
	private Order order;
	private ServerDao serverDao;
	private StorageDao storageDao;

	@Override
	public String execute() throws Exception {
		this.order = this.baseDao.findById(Order.class, this.orderId);
		Vendor vendor = order.getCurrentVendor();
		this.currentVendor = this.user.getVendor();
		if (vendor != null && this.currentVendor != null) {
			if (vendor.getName().equals(this.currentVendor.getName())) {
				this.order.setState(OrderState.REJECT);
				//this.order.setVendorUnitPrice(null);
				this.baseDao.update(this.order);
				/*
				 * 供货商拒绝以后，更新库存
				 */
				long goldAmount = this.order.getPurchaseAttemp().getGold_amount();
				Server server = this.serverDao.getServer(order.getPurchaseAttemp().getRegion(), order.getPurchaseAttemp().getServer(), order.getPurchaseAttemp().getFaction());
				StorageList storage = storageDao.getStorage(server, this.currentVendor.getId());
				if (storage != null) {
					storage.setAmount(storage.getAmount() + goldAmount);
					this.baseDao.update(storage);
				} else {
					LOG.error("没有找到供货商相应的库存,有可能供货商删除了对应的库存信息");
				}

				// 订单拒绝以后要重新进入分配流程，这里要添加代码，
				List<OrderRecord> orderRecordList = this.orderRecordDao.getOrderRecordByNumberAndVendor(order.getPurchaseAttemp().getGh_transaction_id(), vendor);
				if (orderRecordList.size() > 0) {
					OrderRecord orderRecord = orderRecordList.get(0);
					orderRecord.setEvent(OrderState.REJECT);
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

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	public User getUser() {
		return user;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

}
