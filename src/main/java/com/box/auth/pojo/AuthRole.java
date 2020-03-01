package com.box.auth.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色，拥有权限集合
 * 
 * @author sunyizhuo
 *
 */
public class AuthRole implements Serializable {
	private static final long serialVersionUID = 5913372900362945797L;
	private Long id;
	private String roleCode;
	private String roleName;
	private Integer del_ = 0;
	private Long version_ = 0L;
	/**
	 * 角色对应权限集合
	 */
	private Set<AuthPermissions> permissions;
	private Set<Long> permissionsIds;
	private Set<Long> userIds;

	public AuthRole(Long id, String roleCode, String roleName, Integer del_, Long version_,
			Set<AuthPermissions> permissions, Set<Long> permissionsIds) {
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.del_ = del_;
		this.version_ = version_;
		this.permissions = permissions;
		this.permissionsIds = permissionsIds;
	}

	@Override
	public String toString() {
		return "SecurityRole [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", del_=" + del_
				+ ", version_=" + version_ + ", permissions=" + permissions + ", permissionsIds=" + permissionsIds
				+ "]";
	}

	public Set<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<Long> userIds) {
		this.userIds = userIds;
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

	public AuthRole() {
		super();
	}

	public Set<Long> getPermissionsIds() {
		return permissionsIds;
	}

	public void setPermissionsIds(Set<Long> permissionsIds) {
		this.permissionsIds = permissionsIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
