package com.box.auth.pojo;

import java.io.Serializable;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.box.pojo.BasePojo;

import lombok.Data;

/**
 * 角色，拥有权限集合
 * 
 * @author sunyizhuo
 *
 */
@Data
@TableName("auth_role")
public class AuthRole extends BasePojo implements Serializable {
	private static final long serialVersionUID = 5913372900362945797L;
	private String roleCode;
	private String roleName;
	/**
	 * 角色对应权限集合
	 */
	@TableField(exist = false)
	private Set<AuthPermissions> permissions;
	@TableField(exist = false)
	private Set<Long> permissionsIds;
	@TableField(exist = false)
	private Set<Long> userIds;

	public AuthRole(Long id, String roleCode, String roleName, Integer del_, Long version_,
			Set<AuthPermissions> permissions, Set<Long> permissionsIds) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.permissions = permissions;
		this.permissionsIds = permissionsIds;
	}


	public Set<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<Long> userIds) {
		this.userIds = userIds;
	}

	public AuthRole() {
		super();
	}

	public Set<Long> getPermissionsIds() {
		return permissionsIds;
	}

	public void setPermissionsIds(Set<Long> permissionsIds) {
		this.permissionsIds = permissionsIds;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<AuthPermissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<AuthPermissions> permissions) {
		this.permissions = permissions;
	}

}
