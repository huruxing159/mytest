package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Server implements Serializable {

	private static final long serialVersionUID = -1832412971731991608L;
	private long id;
	private String name;
	private String faction;
	private Country country;
	private Calendar created_at;
	private Calendar updated_at;
	private BigDecimal priceLimit;
	private long amountLimit;
	private BigDecimal history_unit_price;
	private BigDecimal current_unit_price;
	private String history_price_strategy;
	private String current_price_strategy;
	private Set<StorageList> storageLists = new HashSet<StorageList>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Calendar getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Calendar createdAt) {
		created_at = createdAt;
	}

	public Calendar getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Calendar updatedAt) {
		updated_at = updatedAt;
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
		Server other = (Server) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public String getFaction() {
		return faction;
	}

	public void setHistory_unit_price(BigDecimal history_unit_price) {
		this.history_unit_price = history_unit_price;
	}

	public BigDecimal getHistory_unit_price() {
		return history_unit_price;
	}

	public void setCurrent_unit_price(BigDecimal current_unit_price) {
		this.current_unit_price = current_unit_price;
	}

	public BigDecimal getCurrent_unit_price() {
		return current_unit_price;
	}

	public void setHistory_price_strategy(String history_price_strategy) {
		this.history_price_strategy = history_price_strategy;
	}

	public String getHistory_price_strategy() {
		return history_price_strategy;
	}

	public void setCurrent_price_strategy(String current_price_strategy) {
		this.current_price_strategy = current_price_strategy;
	}

	public String getCurrent_price_strategy() {
		return current_price_strategy;
	}

	public void setPriceLimit(BigDecimal priceLimit) {
		this.priceLimit = priceLimit;
	}

	public BigDecimal getPriceLimit() {
		return priceLimit;
	}

	public void setAmountLimit(long amountLimit) {
		this.amountLimit = amountLimit;
	}

	public long getAmountLimit() {
		return amountLimit;
	}

	public void setStorageLists(Set<StorageList> storageLists) {
		this.storageLists = storageLists;
	}

	public Set<StorageList> getStorageLists() {
		return storageLists;
	}
}
