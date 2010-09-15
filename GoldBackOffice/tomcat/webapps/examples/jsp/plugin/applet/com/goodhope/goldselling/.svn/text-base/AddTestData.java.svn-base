package com.goodhope.goldselling;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.PaypalRecord;
import com.goodhope.goldselling.domain.PurchaseAttemp;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.BaseDao;

public class AddTestData {
	private ApplicationContext context;
	private BaseDao baseDao;

	public static void main(String[] args) {
		AddTestData addTestData = new AddTestData();
		for (int i = 0; i < 20; i++) {
			addTestData.addData();
		}

	}

	public AddTestData() {
		this.context = new ClassPathXmlApplicationContext(new String[] { "classpath:applicationContext.xml", "classpath:applicationContext-dao.xml", "classpath:applicationContext-hibernate.xml", "applicationContext-service.xml"

		});
		this.baseDao = (BaseDao) context.getBean("baseDao");
	}

	private void addData() {
		PaypalRecord payPalRecord = new PaypalRecord();
		payPalRecord.setCreate_time(Calendar.getInstance());
		payPalRecord.setPaypal_payer_id("QKAN2VJGUWT7Q");
		payPalRecord.setPaypal_transaction_id("8DJ024216W525672L");
		payPalRecord.setPaypal_token("EC-6WT94676BP952804Y");
		payPalRecord = (PaypalRecord) this.baseDao.merge(payPalRecord);

		PurchaseAttemp purchaseAttemp = new PurchaseAttemp();
		purchaseAttemp.setRegion("EU");
		purchaseAttemp.setFaction("Alliance");
		purchaseAttemp.setServer("Aerie Peak - EU");
		purchaseAttemp.setCharacter("魔兽角色名");
		purchaseAttemp.setTrade_method("In-game mail");
		purchaseAttemp.setGold_amount(500);
		purchaseAttemp.setPrice(new BigDecimal(0.05500000));
		purchaseAttemp.setUnit_price(new BigDecimal(0.00011000));
		purchaseAttemp.setConcurrency("usd");
		purchaseAttemp.setPayment_method("Paypal");
		purchaseAttemp.setPayment_method_id(payPalRecord.getId());
		purchaseAttemp.setCreate_time(Calendar.getInstance());
		this.baseDao.save(purchaseAttemp);
		purchaseAttemp = (PurchaseAttemp) this.baseDao.merge(purchaseAttemp);
		purchaseAttemp.setGh_transaction_id("GH" + Calendar.getInstance().getTimeInMillis() + "P" + purchaseAttemp.getId());
		this.baseDao.update(purchaseAttemp);

		Order order = new Order();
		order.setPurchaseAttemp(purchaseAttemp);
		order.setCreateTime(Calendar.getInstance());
		order.setVendorUnitPrice(new BigDecimal(0.00010000));
		order.setState("已分配未接受");
		Vendor vendor = this.baseDao.findById(Vendor.class, 1l);
		order.setCurrentVendor(vendor);
		this.baseDao.save(order);

		OrderRecord orderRecord = new OrderRecord();
		orderRecord.setEvent("已分配未接受");
		orderRecord.setVendor(vendor);
		orderRecord.setOrder(order);
		orderRecord.setVendorUnitPrice(new BigDecimal("0.00010000"));
		this.baseDao.save(orderRecord);

	}
}
