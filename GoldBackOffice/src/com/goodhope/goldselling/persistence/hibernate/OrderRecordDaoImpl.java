package com.goodhope.goldselling.persistence.hibernate;

import java.util.Calendar;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.OrderRecordDao;

public class OrderRecordDaoImpl extends HibernateDaoSupport implements OrderRecordDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRecord> getOrderRecordByDateAndVendor(Calendar from, Calendar to, Vendor vendor) {
		return getHibernateTemplate().find("from OrderRecord orR where orR.vendor=? and orR.order.createTime between ? and ?", vendor, from, to);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRecord> getOrderRecordByStateAndVendor(String state, Vendor vendor) {
		return getHibernateTemplate().find("from OrderRecord orR where orR.vendor=? and orR.event=?", vendor, state);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRecord> getOrderRecordByNumberAndVendor(String orderNumber, Vendor vendor) {
		return getHibernateTemplate().find("from OrderRecord orR where orR.vendor=? and orR.order.purchaseAttemp.gh_transaction_id=?", vendor, orderNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRecord> getOrderRecordByDate(Calendar from, Calendar to) {
		return getHibernateTemplate().find("from OrderRecord orR where  orR.order.createTime between ? and ?", from, to);
	}
}
