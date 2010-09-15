package com.goodhope.goldselling.web;

import com.goodhope.goldselling.domain.User;

public interface UserAware {

	public void setCurrentUser(User user);
}
