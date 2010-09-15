package com.goodhope.goldselling.persistence;

import java.util.Calendar;
import java.util.List;

import com.goodhope.goldselling.domain.Order;
import com.goodhope.goldselling.domain.Vendor;

public interface OrderDao {

	public List<Order> getAllOrdersByVendor(Vendor vendor);

	public List<Order> getAllNewOrdersByVendor(Vendor vendor, String... state);

	public List<Order> getOrderByNumber(String orderNumber);

	public List<Order> queryOrderByCustomerService(String orderVendor, String orderState, Calendar fromDate, Calendar toDate);

	public List<Order> queryOrderByFinancial(String orderVendor, String orderState, Calendar fromDate, Calendar toDate);

}
