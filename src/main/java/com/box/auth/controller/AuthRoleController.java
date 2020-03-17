package com.box.auth.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.AuthRoleService;
import com.box.common.DispatchDTO;
import com.box.utils.JsonUtil;
//import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/auth/role")
public class AuthRoleController {

	@Autowired
	private AuthRoleService authRoleServiceImpl;

	@RequiresPermissions({ "auth_role_manage", "auth_role_sel" })
	@RequestMapping("/query")
	public String query(AuthRole authRole, Integer pageIndex, Integer pageSize) {
		IPage<AuthRole> pageInfo = authRoleServiceImpl.query(authRole, pageIndex, pageSize);
		return JsonUtil.controllerSuccessJson(pageInfo);
	}

	@RequiresPermissions({ "auth_role_sel" })
	@RequestMapping("/get")
	public String get(Long id) {
		AuthRole authRole = authRoleServiceImpl.get(id);
		return JsonUtil.controllerSuccessJson(authRole);
	}

	@RequiresPermissions({ "auth_role_add" })
	@RequestMapping("/create")
	public String create(AuthRole authRole) {
		int i = authRoleServiceImpl.create(authRole);
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authRole);
		} else {
			return JsonUtil.controllerErrorJson(authRole);
		}
	}

	@RequiresPermissions({ "auth_role_upd" })
	@RequestMapping("/update")
	public String update(AuthRole authRole) {
		int i = authRoleServiceImpl.update(authRole);
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authRole);
		} else {
			return JsonUtil.controllerErrorJson(authRole);
		}
	}

	@RequiresPermissions({ "auth_role_del" })
	@RequestMapping("/removeBatch")
	public String removeBatch(DispatchDTO dto) {
		int i = authRoleServiceImpl.removeBatch(dto.getIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(dto.getIds());
		} else {
			return JsonUtil.controllerErrorJson(dto.getIds());
		}
	}

	@RequiresPermissions({ "auth_role_del" })
	@RequestMapping("/recoveryBatch")
	public String recoveryBatch(DispatchDTO dto) {
		int i = authRoleServiceImpl.recoveryBatch(dto.getIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(dto.getIds());
		} else {
			return JsonUtil.controllerErrorJson(dto.getIds());
		}
	}

	@RequiresPermissions({ "auth_role_manage" })
	@RequestMapping("/possessPermissions")
	public String possessPermissions(Long roleId) {
		List<AuthPermissions> permissionsList = authRoleServiceImpl.possessPermissions(roleId);
		return JsonUtil.controllerSuccessJson(permissionsList);
	}
	
	@RequiresPermissions({ "auth_role_manage" })
	@RequestMapping("/possessUser")
	public String possessUser(Long roleId) {
		List<AuthUser> userList = authRoleServiceImpl.possessUser(roleId);
		return JsonUtil.controllerSuccessJson(userList);
	}

	@RequiresPermissions({ "auth_role_manage" })
	@RequestMapping("/addPermissionsBatch")
	public String addPermissionsBatch(AuthRole authRole) {
		int i = authRoleServiceImpl.addPermissionsBatch(authRole.getId(), authRole.getPermissionsIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}

	@RequiresPermissions({ "auth_role_manage" })
	@RequestMapping("/removePermissionsBatch")
	public String removePermissionsBatch(AuthRole authRole) {
		int i = authRoleServiceImpl.removePermissionsBatch(authRole.getId(), authRole.getPermissionsIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}
	
	@RequiresPermissions({ "auth_role_manage" })
	@RequestMapping("/addUserBatch")
	public String addUserBatch(AuthRole authRole) {
		int i = authRoleServiceImpl.addUserBatch(authRole.getId(), authRole.getUserIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}

	@RequiresPermissions({ "auth_role_manage" })
	@RequestMapping("/removeUserBatch")
	public String removeUserBatch(AuthRole authRole) {
		int i = authRoleServiceImpl.removeUserBatch(authRole.getId(), authRole.getUserIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}
}
