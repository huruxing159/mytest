package com.goodhope.goldselling.persistence;

import java.util.List;

import com.goodhope.goldselling.domain.Vendor;

public interface VendorDao {
	public List<Vendor> getAllVendors();

	public Vendor getVendorByName(String name);

}
