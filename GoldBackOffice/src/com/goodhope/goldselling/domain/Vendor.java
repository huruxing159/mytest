package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Vendor implements Serializable {

	private static final long serialVersionUID = -2467120606807661086L;
	private long id;
	private String name;
	private Set<User> users = new HashSet<User>();
	private Set<StorageList> storageLists = new HashSet<StorageList>();
	private Set<OrderRecord> orderRecords = new HashSet<OrderRecord>();
	private Set<Order> orders = new HashSet<Order>();

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<User> getUsers() {
		return users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendor other = (Vendor) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setStorageLists(Set<StorageList> storageLists) {
		this.storageLists = storageLists;
	}

	public Set<StorageList> getStorageLists() {
		return storageLists;
	}

	public void setOrderRecords(Set<OrderRecord> orderRecords) {
		this.orderRecords = orderRecords;
	}

	public Set<OrderRecord> getOrderRecords() {
		return orderRecords;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public String getOperation() {
		return "";
	}

}
