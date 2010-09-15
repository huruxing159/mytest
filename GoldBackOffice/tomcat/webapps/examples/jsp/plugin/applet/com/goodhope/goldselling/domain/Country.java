package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Country implements Serializable {

	private static final long serialVersionUID = -6139233528547105850L;
	private long id;
	private String name;
	private Set<Server> servers = new HashSet<Server>();

	private Calendar created_at;
	private Calendar updated_at;

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

	public void setServers(Set<Server> servers) {
		this.servers = servers;
	}

	public Set<Server> getServers() {
		return servers;
	}

}
