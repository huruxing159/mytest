package com.goodhope.goldselling.web.action.all;

import org.springframework.util.StringUtils;

import com.goodhope.goldselling.domain.User;
import com.goodhope.goldselling.exception.MD5EncryptionException;
import com.goodhope.goldselling.persistence.BaseDao;
import com.goodhope.goldselling.service.MD5Service;
import com.goodhope.goldselling.web.TransactionAware;
import com.goodhope.goldselling.web.UserAware;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements TransactionAware, UserAware {

	private static final long serialVersionUID = 327838094533182671L;
	private User user;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private MD5Service md5Service;
	private BaseDao baseDao;

	@Override
	public String execute() throws Exception {
		this.user.setPassword(md5Service.md5(newPassword));
		this.user = (User) this.baseDao.merge(this.user);
		this.baseDao.update(this.user);
		addActionMessage("密码修改成功");
		return super.execute();
	}

	@Override
	public void validate() {
		try {
			if (!StringUtils.hasText(oldPassword)) {
				addActionError("旧密码不能为空");
				return;
			}
			if (!md5Service.md5(oldPassword).equals(user.getPassword())) {
				addActionError("旧密码不正确");
				return;
			}
			if (!StringUtils.hasText(newPassword)) {
				addActionError("新密码不能为空");
				return;
			}
			if (!newPassword.equals(confirmPassword)) {
				addActionError("新密码和确认密码不一致");
			}
			if (newPassword.length() > 20) {
				addActionError("密码不能超过20位");
			}
		} catch (MD5EncryptionException e) {
			addActionError("MD5加密出错");
		}
	}

	public String initChange() throws Exception {
		return super.execute();
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	@Override
	public void setCurrentUser(User user) {
		this.user = user;

	}

	public void setMd5Service(MD5Service md5Service) {
		this.md5Service = md5Service;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
