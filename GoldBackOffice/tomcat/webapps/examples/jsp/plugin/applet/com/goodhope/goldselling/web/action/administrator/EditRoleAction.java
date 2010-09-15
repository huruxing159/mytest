package com.goodhope.goldselling.web.action.administrator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.goodhope.goldselling.domain.Role;
import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.persistence.RoleDao;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class EditRoleAction extends ActionSupport implements TransactionAware, UserAware {

	private static final long serialVersionUID = 3739126313961148434L;
	private User me;
	private User changeUser;
	private Set<Role> allRoles;
	private BaseDao baseDao;
	private RoleDao roleDao;
	private long userId;
	private String[] roleChanged;
	private Map<String, String> roleMap = new HashMap<String, String>();

	@Override
	public String execute() throws Exception {
		getAllRoles();
		for (Role role : allRoles) {
			roleMap.put(role.getRoleShowName(), "");
		}
		findChangeUser(userId);
		Set<Role> changerUserRole = changeUser.getRoles();
		for (Role role : changerUserRole) {
			roleMap.put(role.getRoleShowName(), "checked='checked'");
		}
		return super.execute();
	}

	private void findChangeUser(long userId) {
		changeUser = baseDao.findById(User.class, userId);
	}

	private void getAllRoles() {
		allRoles = new HashSet<Role>(roleDao.getAllRoles());
	}

	public String editRoleMethod() throws Exception {
		findChangeUser(userId);
		if (changeUser == null) {
			return ActionSupport.INPUT;
		}
		getAllRoles();
		changeUser.getRoles().clear();
		if (roleChanged != null) {
			for (String rchanged : roleChanged) {
				for (Role role : allRoles) {
					if (rchanged.equals(role.getRoleShowName())) {
						changeUser.getRoles().add(role);
					}
				}
			}
		}
		doWithAdministrator();
		baseDao.update(changeUser);
		return ActionSupport.SUCCESS;
	}

	private void doWithAdministrator() {
		if (this.me.getUsername().equals(this.changeUser.getUsername())) {
			for (Role role : this.me.getRoles()) {
				if (role.getRoleName().equals("ROLE_ADMINISTRATOR")) {
					this.changeUser.getRoles().add(role);
				}
			}
		}
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public User getChangeUser() {
		return changeUser;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Map<String, String> getRoleMap() {
		return roleMap;
	}

	public void setRoleChanged(String[] roleChanged) {
		this.roleChanged = roleChanged;
	}

	@Override
	public void setCurrentUser(User user) {
		this.me = user;
	}

}
