package com.goodhope.goldselling.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {

	private static final long serialVersionUID = -2653840797152120430L;
	public static final String CUSTOMERSERVICE = "ROLE_CUSTOMER_SERVICE";
	public static final String ADMINISTRATOR = "ROLE_ADMINISTRATOR";
	public static final String OPERATOR = "ROLE_OPERATOR";
	public static final String VENDOR = "ROLE_VENDOR";
	public static final String FINANCIAL = "ROLE_FINANCIAL";

	private long id;
	private String roleName;
	private String roleShowName;
	private Calendar createTime;
	private String description;
	private Set<User> users = new HashSet<User>();
	private Set<Authority> authorities = new HashSet<Authority>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setRoleShowName(String roleShowName) {
		this.roleShowName = roleShowName;
	}

	public String getRoleShowName() {
		return roleShowName;
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
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

}
