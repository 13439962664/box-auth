package com.box.auth.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.box.auth.dao.AuthPermissionsDao;
import com.box.auth.dao.AuthRoleDao;
import com.box.auth.dao.AuthUserDao;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.AuthRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AuthRoleServiceImpl implements AuthRoleService {

	@Autowired
	private AuthRoleDao authRoleDao;
	
	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Autowired
	private AuthUserDao authUserDao;
	
	@Override
	public PageInfo<AuthRole> query(AuthRole authRole,Integer pageIndex,Integer pageSize) {
		if(!(authRole.getRoleName()==null||"".equals(authRole.getRoleName()))) {
			authRole.setRoleName("*"+authRole.getRoleName()+"*");
		}
		final PageInfo<AuthRole> pageInfo = PageHelper.startPage(pageIndex, pageSize).setOrderBy("id desc")
				.doSelectPageInfo(() -> this.authRoleDao.query(authRole));
		return pageInfo;
	}

	@Override
	public AuthRole get(Long id) {
		return authRoleDao.get(id);
	}

	@Override
	public int create(AuthRole authRole) {
		return authRoleDao.create(authRole);
	}

	@Override
	public int update(AuthRole authRole) {
		return authRoleDao.update(authRole);
	}

	@Override
	public int removeBatch(Set<Long> ids) {
		return authRoleDao.removeBatch(ids);
	}
	
	@Override
	public List<AuthPermissions> possessPermissions(Long roleId) {
		List<AuthPermissions> permissionsList = authPermissionsDao.findByRoleId(roleId);
		return permissionsList;
	}

	@Override
	public int addPermissionsBatch(Long roleId,Set<Long> permissionsIds) {
		int status = authRoleDao.addPermissionsBatch(roleId, permissionsIds);
		return status;
	}

	@Override
	public int removePermissionsBatch(Long roleId,Set<Long> permissionsIds) {
		int status = authRoleDao.removePermissionsBatch(roleId, permissionsIds);
		return status;
	}
	
	@Override
	public int recoveryBatch(Set<Long> ids) {
		return authRoleDao.recoveryBatch(ids);
	}

	@Override
	public List<AuthUser> possessUser(Long roleId) {
		List<AuthUser> userList = authUserDao.findByRoleId(roleId);
		return userList;
	}

	@Override
	public int addUserBatch(Long roleId, Set<Long> userIds) {
		int status = authRoleDao.addUserBatch(roleId, userIds);
		return status;
	}

	@Override
	public int removeUserBatch(Long roleId, Set<Long> userIds) {
		int status = authRoleDao.removeUserBatch(roleId, userIds);
		return status;
	}

}
