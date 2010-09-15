package com.goodhope.goldselling.web.interceptors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.goodhope.goldselling.domain.Authority;
import com.goodhope.goldselling.domain.Role;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MenuAwareInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 8995259094820213671L;
	private static final Logger LOG = Logger.getLogger(MenuAwareInterceptor.class);
	private BaseDao baseDao;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Set<Authority> menus = new HashSet<Authority>();
		if (user != null) {
			user = baseDao.findById(User.class, user.getId());
			if (user != null) {
				Set<Role> roles = user.getRoles();
				for (Role role : roles) {
					LOG.debug(role.getRoleName());
					menus.addAll(role.getAuthorities());
				}
			}
		}
		actionInvocation.getInvocationContext().put("menus", sortMenuList(menus));
		
		return actionInvocation.invoke();
	}

	private List<Authority> sortMenuList(Set<Authority> menus) {
		List<Authority> orderList = new ArrayList<Authority>(menus);
		Collections.sort(orderList, new Comparator<Authority>() {
			@Override
			public int compare(Authority a, Authority b) {
				return (int) (a.getId() - b.getId());
			}
		});
		return orderList;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
