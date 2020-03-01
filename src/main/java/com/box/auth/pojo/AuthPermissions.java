package com.box.auth.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * 权限
 * 
 * @author sunyizhuo
 *
 */
public class AuthPermissions implements Serializable {
	private static final long serialVersionUID = 6818287501991919086L;
	private Long id;
	private String permissionsCode;
	private String permissionsName;
	private Integer del_ = 0;
	private Long version_ = 0L;
	private Set<Long> roleIds;// 批量时使用

	public AuthPermissions() {
		super();
	}

	public AuthPermissions(Long id, String permissionsCode, String permissionsName, Integer del_, Long version_,
			Set<Long> roleIds) {
		super();
		this.id = id;
		this.permissionsCode = permissionsCode;
		this.permissionsName = permissionsName;
		this.del_ = del_;
		this.version_ = version_;
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "SecurityPermissions [id=" + id + ", permissionsCode=" + permissionsCode + ", permissionsName="
				+ permissionsName + ", del_=" + del_ + ", version_=" + version_ + ", roleIds=" + roleIds + "]";
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

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionsCode() {
		return permissionsCode;
	}

	public void setPermissionsCode(String permissionsCode) {
		this.permissionsCode = permissionsCode;
	}

	public String getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}

}
