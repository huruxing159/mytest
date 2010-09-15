package com.goodhope.goldselling.persistence.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.OrderState;
import com.goodhope.goldselling.domain.Vendor;
import com.goodhope.goldselling.persistence.OrderDao;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	// private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrdersByVendor(Vendor currentVendor) {
		return getHibernateTemplate().find("from Order o where o.currentVendor=?  order by id", currentVendor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllNewOrdersByVendor(Vendor currentVendor, String... state) {
		StringBuilder sb = new StringBuilder("");
		for (String string : state) {
			sb.append("'").append(string).append("'").append(",");
		}
		return getHibernateTemplate().find("from Order o where o.currentVendor=? and o.state in (" + sb.substring(0, sb.length() - 1) + ") order by id desc", currentVendor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByNumber(String orderNumber) {
		return getHibernateTemplate().find("from Order o where o.purchaseAttemp.gh_transaction_id=?  order by id", orderNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOrderByCustomerService(String orderVendor, String orderState, Calendar fromDate, Calendar toDate) {
		List<Object> value = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("from Order o where ");
		if (!"allVendors".equalsIgnoreCase(orderVendor)) {
			hql.append("  o.currentVendor.name=?  and ");
			value.add(orderVendor);
		}
		if (!"allStates".equalsIgnoreCase(orderState)) {
			hql.append("  o.state=?  and ");
			value.add(OrderState.CUSTOMSERVICE_STATE.get(orderState));
		}
		hql.append("  o.createTime between  ?  and  ?   order by id desc");
		value.add(fromDate);
		value.add(toDate);

		return getHibernateTemplate().find(hql.toString(), value.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOrderByFinancial(String orderVendor, String orderState, Calendar fromDate, Calendar toDate) {
		List<Object> value = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("from Order o where ");
		if (!"allVendors".equalsIgnoreCase(orderVendor)) {
			hql.append("  o.currentVendor.name=?  and ");
			value.add(orderVendor);
		}
		if (!"allStates".equalsIgnoreCase(orderState)) {
			hql.append("  o.state=?  and ");
			value.add(OrderState.CUSTOMSERVICE_STATE.get(orderState));
		} else {
			hql.append("  o.state in (");
			for (Map.Entry<String, String> state : OrderState.FINANCIAL_STATE.entrySet()) {
				hql.append("'" + state.getValue() + "',");
			}
			hql.deleteCharAt(hql.lastIndexOf(","));
			hql.append(")  and  ");
		}
		hql.append("  o.createTime between  ?  and  ?  ");
		value.add(fromDate);
		value.add(toDate);

		return getHibernateTemplate().find(hql.toString(), value.toArray());
	}
}
