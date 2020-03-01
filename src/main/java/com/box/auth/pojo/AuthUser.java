package com.box.auth.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author sunyizhuo 用户，拥有角色集合
 */
public class AuthUser implements Serializable {
	private static final long serialVersionUID = 2719604967484830846L;
	private Long id;
	private String userName;
	private String password;
	private String oldPassword;
	private Integer del_ = 0;
	private Long version_ = 0L;

	/**
	 * 用户对应的角色集合
	 */
	private Set<AuthRole> roles;
	private Set<Long> roleIds;

	public AuthUser() {
		super();
	}

	public AuthUser(Long id, String userName, String password, String oldPassword, Integer del_, Long version_,
			Set<AuthRole> roles, Set<Long> roleIds) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.oldPassword = oldPassword;
		this.del_ = del_;
		this.version_ = version_;
		this.roles = roles;
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "SecurityUser [id=" + id + ", userName=" + userName + ", password=" + password + ", oldPassword="
				+ oldPassword + ", del_=" + del_ + ", version_=" + version_ + ", roles=" + roles + ", roleIds="
				+ roleIds + "]";
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getDel_() {
		return del_;
	}

	public void setDel_(Integer del_) {
		this.del_ = del_;
	}

	public Long getVersion_() {
		return version_;
	}

	public void setVersion_(Long version_) {
		this.version_ = version_;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<AuthRole> roles) {
		this.roles = roles;
	}

}
