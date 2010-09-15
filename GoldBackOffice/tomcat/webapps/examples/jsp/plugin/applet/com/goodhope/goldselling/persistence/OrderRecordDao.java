package com.goodhope.goldselling.persistence;

import java.util.Calendar;
import java.util.List;

import com.goodhope.goldselling.domain.OrderRecord;
import com.goodhope.goldselling.domain.Vendor;

public interface OrderRecordDao {

	public List<OrderRecord> getOrderRecordByDate(Calendar from, Calendar to);

	public List<OrderRecord> getOrderRecordByDateAndVendor(Calendar from, Calendar to, Vendor vendor);

	public List<OrderRecord> getOrderRecordByStateAndVendor(String state, Vendor vendor);

	public List<OrderRecord> getOrderRecordByNumberAndVendor(String orderNumber, Vendor vendor);

}
