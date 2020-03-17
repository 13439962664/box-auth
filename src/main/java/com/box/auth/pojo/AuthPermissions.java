package com.box.auth.pojo;

import java.io.Serializable;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.box.pojo.BasePojo;

import lombok.Data;

/**
 * 权限
 * 
 * @author sunyizhuo
 *
 */
@Data
@TableName("auth_permissions")
public class AuthPermissions extends BasePojo implements Serializable {
	private static final long serialVersionUID = 6818287501991919086L;
	private String permissionsCode;
	private String permissionsName;
	@TableField(exist = false)
	private Set<Long> roleIds;// 批量时使用

	public AuthPermissions() {
		super();
	}

	public AuthPermissions(Long id, String permissionsCode, String permissionsName, Integer del_, Long version_,
			Set<Long> roleIds) {
		super();
		this.permissionsCode = permissionsCode;
		this.permissionsName = permissionsName;
		this.roleIds = roleIds;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
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
