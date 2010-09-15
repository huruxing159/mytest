package com.goodhope.goldselling.persistence;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.goodhope.goldselling.domain.User;

public interface UserDao extends UserDetailsService {

	User getUserbyName(String username);

	List<User> getAllUsers();

	List<User> getCustomerServices();

	List<User> getUsersByRole(String role);

	List<User> getUsersByVendorId(long vendorId);

}
