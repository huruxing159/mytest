package com.goodhope.goldselling.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.goodhope.goldselling.domain.Role;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		try {
			UserDetails user = getUserbyName(username);
			Assert.isTrue(user != null);
			return user;
		} catch (Exception e) {
			LOG.error(e, e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	@Override
	public User getUserbyName(String username) {
		Assert.isTrue(StringUtils.hasText(username));
		@SuppressWarnings("unchecked")
		List<User> users = getHibernateTemplate().find("from User where username=?", username);
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		return getHibernateTemplate().find("from User order by id");
	}

	@Override
	public List<User> getCustomerServices() {
		return getUsersByRole(Role.CUSTOMERSERVICE);
	}

	@Override
	public List<User> getUsersByRole(String role) {
		List<User> users = new ArrayList<User>();
		List<User> allUsers = getAllUsers();
		for (User user : allUsers) {
			Set<Role> roles = user.getRoles();
			for (Role r : roles) {
				if (r.getRoleName().equals(role)) {
					users.add(user);
					break;
				}
			}
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByVendorId(long vendorId) {
		List<User> users = new ArrayList<User>();
		List<User> allUsers = getHibernateTemplate().find("from User u where u.vendor is null or u.vendor.id=? order by u.id", vendorId);
		for (User user : allUsers) {
			Set<Role> roles = user.getRoles();
			for (Role r : roles) {
				if (r.getRoleName().equals(Role.VENDOR)) {
					users.add(user);
					break;
				}
			}
		}
		return users;
	}
}
