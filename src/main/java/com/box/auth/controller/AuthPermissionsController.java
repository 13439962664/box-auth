package com.box.auth.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.AuthPermissionsService;
import com.box.common.DispatchDTO;
import com.box.utils.JsonUtil;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/auth/permissions")
public class AuthPermissionsController {
	
	@InitBinder("manager")    
    public void initBinder1(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("manager.");    
    }    
    @InitBinder("user")    
    public void initBinder2(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("user.");    
    }    

	@Autowired
	private AuthPermissionsService authPermissionsServiceImpl;

	@RequiresPermissions({"auth_permissions_manage","auth_permissions_sel"})
	@RequestMapping("/query")
	public String query(AuthPermissions authPermissions, Integer pageIndex, Integer pageSize) {
		PageInfo<AuthPermissions> pageInfo = authPermissionsServiceImpl.query(authPermissions, pageIndex, pageSize);
		return JsonUtil.controllerSuccessJson(pageInfo);
	}
	
	@RequiresPermissions({"auth_permissions_sel"})
	@RequestMapping("/get")
	public String get(Long id) {
		AuthPermissions authPermissions = authPermissionsServiceImpl.get(id);
		return JsonUtil.controllerSuccessJson(authPermissions);
	}

	@RequiresPermissions({"auth_permissions_add"})
	@RequestMapping("/create")
	public String create(AuthPermissions authPermissions) {
		int i = authPermissionsServiceImpl.create(authPermissions);
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authPermissions);
		} else {
			return JsonUtil.controllerErrorJson(authPermissions);
		}
	}
	
	@RequiresPermissions({"auth_permissions_add"})
	@RequestMapping("/createAndPossessRoleBatch")
	@Transactional
	public String createAndPossessRoleBatch(AuthPermissions authPermissions) {
		int i = authPermissionsServiceImpl.createAndPossessRoleBatch(authPermissions,authPermissions.getRoleIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authPermissions);
		} else {
			return JsonUtil.controllerErrorJson(authPermissions);
		}
	}

	@RequiresPermissions({"auth_permissions_upd"})
	@RequestMapping("/update")
	public String update(AuthPermissions authPermissions) {
		int i = authPermissionsServiceImpl.update(authPermissions);
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(authPermissions);
		} else {
			return JsonUtil.controllerErrorJson(authPermissions);
		}
	}
	
	@RequiresPermissions({"auth_permissions_del"})
	@RequestMapping("/removeBatch")
	public String removeBatch(DispatchDTO dto) {
		int i = authPermissionsServiceImpl.removeBatch(dto.getIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(dto.getIds());
		} else {
			return JsonUtil.controllerErrorJson(dto.getIds());
		}
	}
	@RequiresPermissions({"auth_permissions_del"})
	@RequestMapping("/recoveryBatch")
	public String recoveryBatch(DispatchDTO dto) {
		int i = authPermissionsServiceImpl.recoveryBatch(dto.getIds());
		if (i == 1) {
			return JsonUtil.controllerSuccessJson(dto.getIds());
		} else {
			return JsonUtil.controllerErrorJson(dto.getIds());
		}
	}
	
	@RequiresPermissions({ "auth_permissions_manage" })
	@RequestMapping("/possessUser")
	public String possessUser(Long permissionsId) {
		List<AuthUser> userList = authPermissionsServiceImpl.possessUser(permissionsId);
		return JsonUtil.controllerSuccessJson(userList);
	}
	
	@RequiresPermissions({ "auth_permissions_manage" })
	@RequestMapping("/possessRole")
	public String possessRole(Long permissionsId) {
		List<AuthRole> roleList = authPermissionsServiceImpl.possessRole(permissionsId);
		return JsonUtil.controllerSuccessJson(roleList);
	}
	
	@RequiresPermissions({ "auth_permissions_manage" })
	@RequestMapping("/addRoleBatch")
	public String addRoleBatch(AuthPermissions authPermissions) {
		int i = authPermissionsServiceImpl.addRoleBatch(authPermissions.getId(), authPermissions.getRoleIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}

	@RequiresPermissions({ "auth_permissions_manage" })
	@RequestMapping("/removeRoleBatch")
	public String removeRoleBatch(AuthPermissions authPermissions) {
		int i = authPermissionsServiceImpl.removeRoleBatch(authPermissions.getId(), authPermissions.getRoleIds());
		if (i > 0) {
			return JsonUtil.controllerSuccessJson(i);
		} else {
			return JsonUtil.controllerErrorJson(i);
		}
	}
}
