package com.box.auth.pojo;

import java.io.Serializable;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.box.pojo.BasePojo;

import lombok.Data;

/**
 * 
 * @author sunyizhuo 用户，拥有角色集合
 */
@Data
@TableName("auth_user")
public class AuthUser extends BasePojo implements Serializable{
	private static final long serialVersionUID = -7333717548469547067L;
	private String userName;
	private String password;
	@TableField(exist = false)
	private String oldPassword;

	/**
	 * 用户对应的角色集合
	 */
	@TableField(exist = false)
	private Set<AuthRole> roles;
	@TableField(exist = false)
	private Set<Long> roleIds;

	public AuthUser() {
		super();
	}

	public AuthUser(Long id, String userName, String password, String oldPassword, Integer del_, Long version_,
			Set<AuthRole> roles, Set<Long> roleIds) {
		super();
		this.userName = userName;
		this.password = password;
		this.oldPassword = oldPassword;
		this.roles = roles;
		this.roleIds = roleIds;
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
