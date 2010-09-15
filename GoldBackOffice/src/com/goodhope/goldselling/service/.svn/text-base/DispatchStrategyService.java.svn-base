package com.goodhope.goldselling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.goodhope.goldselling.domain.InformBackoffice;
import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.Server;
import com.goodhope.goldselling.domain.StorageList;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.ServerDao;

public class DispatchStrategyService {
	private static final Logger LOG = Logger.getLogger(DispatchStrategyService.class);
	private PlatformTransactionManager transactionManager;
	private transient ThreadLocal<TransactionStatus> tsHolder = new ThreadLocal<TransactionStatus>();
	private BaseDao baseDao;
	private ServerDao serverDao;

	public void dispatch(InformBackoffice i) {
		try {
			beginTransaction();
			Order order = this.baseDao.findById(Order.class, i.getOrderId());// 找到那条订单
			if (order == null) {
				this.baseDao.delete(i);
				commitTransaction();
				return;
			}
			Server server = this.serverDao.getServer(order.getPurchaseAttemp().getRegion(), order.getPurchaseAttemp().getServer(), order.getPurchaseAttemp().getFaction());
			if (server == null) {
				throw new Exception("服务器 " + order.getPurchaseAttemp().getServer() + " 不存在！");
			}
			Set<StorageList> storageLists = server.getStorageLists();// 找到这个服务器下面所有供应商的库存信息
			if (storageLists.size() == 0) {
				/*
				 * 如果size＝0 说明供应商在这个服务器没有信息，那为什么会生成的订单呢？
				 * 有可能是用户在购买的时候，供应商在这个服务器有信息，但是此时供货商删除了这个服务器的库存信息,或者还有别的可能！
				 */
				// 相关处理代码
				throw new Exception("storageLists.size() == 0");
			}
			List<StorageList> enoughStorage = filterEnoughStorage(order, storageLists);
			StorageList s = choosePriceLowestStorage(enoughStorage);
			if (s == null) {
				// 所有供货商货量不足
				order.setState(OrderState.ASSIGN_FAILED);
				this.baseDao.update(order);
				commitTransaction();
				LOG.info("所有供货商货量不足");
				return;
			}
			/*
			 * 分配给供货商，减去相应的库存量。
			 */
			s.setAmount(s.getAmount() - order.getPurchaseAttemp().getGold_amount());
			this.baseDao.update(s);

			order.setVendorUnitPrice(s.getPrice());
			order.setCurrentVendor(s.getVendor());
			order.setState(OrderState.ASSIGNED_UNACCEPT);
			this.baseDao.update(order);
			OrderRecord orderRecord = new OrderRecord();
			orderRecord.setOrder(order);
			orderRecord.setVendorUnitPrice(order.getVendorUnitPrice());// 需要记录那个时间点供货商的单价
			orderRecord.setVendor(s.getVendor());
			orderRecord.setEvent(order.getState());
			this.baseDao.save(orderRecord);
			this.baseDao.delete(i);
			commitTransaction();
		} catch (Exception e) {
			LOG.error(e);
			rollbackTransaction();
		}

	}

	private StorageList choosePriceLowestStorage(List<StorageList> enoughStorage) {
		if (enoughStorage.size() <= 0) {
			return null;
		}
		StorageList temp = enoughStorage.get(0);
		for (StorageList storageList : enoughStorage) {
			if (Math.abs(storageList.getPrice().floatValue() - temp.getPrice().floatValue()) <= 0.000000000001) {
				if (storageList.getAmount() >= temp.getAmount()) {
					temp = storageList;
				}
			} else if (storageList.getPrice().floatValue() - temp.getPrice().floatValue() <= 0) {
				temp = storageList;
			}
		}
		return temp;
	}

	private List<StorageList> filterEnoughStorage(Order order, Set<StorageList> storageLists) {
		List<StorageList> enoughStorage = new ArrayList<StorageList>();
		for (StorageList storage : storageLists) {
			if (storage.getAmount() >= order.getPurchaseAttemp().getGold_amount()) {
				enoughStorage.add(storage);
			}
		}
		return enoughStorage;
	}

	private void rollbackTransaction() {
		final TransactionStatus ts = tsHolder.get();
		if (!ts.isCompleted()) {
			transactionManager.rollback(ts);
			LOG.error("transaction rollbacked");
		}
	}

	private void beginTransaction() {
		LOG.debug("start transaction!");
		final TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
		tsHolder.set(ts);
	}

	void commitTransaction() {
		final TransactionStatus ts = tsHolder.get();
		if (!ts.isCompleted()) {
			transactionManager.commit(ts);
		}
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

}
