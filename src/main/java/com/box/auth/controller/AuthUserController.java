package com.box.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.AuthUserService;
import com.box.common.DispatchDTO;
import com.box.utils.JsonUtil;

@RestController
@RequestMapping("/auth/user")
public class AuthUserController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthUserController.class);

	@Autowired
	private AuthUserService authUserServiceImpl;

	@RequiresPermissions({ "auth_user_manage", "auth_user_sel" })
	@RequestMapping("/query")
	public String query(AuthUser authUser, Integer pageIndex, Integer pageSize) {
		IPage<AuthUser> page = authUserServiceImpl.query(authUser, pageIndex, pageSize);
		return JsonUtil.controllerSuccessJson(page);
	}

	@RequiresPermissions({ "auth_user_sel" })
	@RequestMapping("/get")
	public String get(Long id) {
		AuthUser authUser = authUserServiceImpl.get(id);
		return JsonUtil.controllerSuccessJson(authUser);
	}

	@RequiresPermissions({ "auth_user_add" })
	@RequestMapping("/create")
	public String create(AuthUser authUser) {
		int i = authUserServiceImpl.create(authUser);
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authUser);
		} else {
			return JsonUtil.controllerErrorJson(authUser);
		}
	}
	

	@RequiresPermissions({ "auth_user_upd" })
	@RequestMapping("/updatePassword")
	public String updatePassword(AuthUser authUser,HttpServletRequest request) {
		int i = authUserServiceImpl.updatePassword(authUser);
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authUser);
		} else {
			return JsonUtil.controllerErrorJson(authUser);
		}
	}

	@RequiresPermissions({ "auth_user_del" })
	@RequestMapping("/removeBatch")
	public String removeBatch(DispatchDTO dto) {
		int i = authUserServiceImpl.removeBatch(dto.getIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(dto.getIds());
		} else {
			return JsonUtil.controllerErrorJson(dto.getIds());
		}
	}

	@RequiresPermissions({ "auth_user_del" })
	@RequestMapping("/recoveryBatch")
	public String recoveryBatch(DispatchDTO dto) {
		int i = authUserServiceImpl.recoveryBatch(dto.getIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(dto.getIds());
		} else {
			return JsonUtil.controllerErrorJson(dto.getIds());
		}
	}

	@RequiresPermissions({ "auth_user_manage" })
	@RequestMapping("/possessRole")
	public String possessRole(Long userId) {
		List<AuthRole> roleList = authUserServiceImpl.possessRole(userId);
		return JsonUtil.controllerSuccessJson(roleList);
	}
	
	@RequiresPermissions({ "auth_user_manage" })
	@RequestMapping("/possessPermissions")
	public String possessPermissions(Long userId) {
		List<AuthPermissions> permissionsList = authUserServiceImpl.possessPermissions(userId);
		return JsonUtil.controllerSuccessJson(permissionsList);
	}

	@RequiresPermissions({ "auth_user_manage" })
	@RequestMapping("/addRoleBatch")
	public String addRoleBatch(AuthUser authUser) {
		int i = authUserServiceImpl.addRoleBatch(authUser.getId(), authUser.getRoleIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}

	@RequiresPermissions({ "auth_user_manage" })
	@RequestMapping("/removeRoleBatch")
	public String removeRoleBatch(AuthUser authUser) {
		int i = authUserServiceImpl.removeRoleBatch(authUser.getId(), authUser.getRoleIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}
}
