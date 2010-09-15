package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Authority implements Serializable {

	private static final long serialVersionUID = -5086426765681243258L;
	private long id;
	private String authorityName;
	private String authorityURI;
	private Calendar createTime;
	private String description;
	private Set<Role> roles = new HashSet<Role>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setAuthorityURI(String authorityURI) {
		this.authorityURI = authorityURI;
	}

	public String getAuthorityURI() {
		return authorityURI;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityURI == null) ? 0 : authorityURI.hashCode());
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
		Authority other = (Authority) obj;
		if (authorityURI == null) {
			if (other.authorityURI != null)
				return false;
		} else if (!authorityURI.equals(other.authorityURI))
			return false;
		return true;
	}

}
