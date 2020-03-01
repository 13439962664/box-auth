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
import com.box.auth.service.AuthPermissionsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AuthPermissionsServiceImpl implements AuthPermissionsService {

	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Autowired
	private AuthRoleDao authRoleDao;
	
	@Autowired
	private AuthUserDao authUserDao;
	
	@Override
	public PageInfo<AuthPermissions> query(AuthPermissions authPermissions,Integer pageIndex,Integer pageSize) {
		if(!(authPermissions.getPermissionsName()==null||"".equals(authPermissions.getPermissionsName()))) {
			authPermissions.setPermissionsName("*"+authPermissions.getPermissionsName()+"*");
		}
		final PageInfo<AuthPermissions> pageInfo = PageHelper.startPage(pageIndex, pageSize).setOrderBy("id desc")
				.doSelectPageInfo(() -> this.authPermissionsDao.query(authPermissions));
		return pageInfo;
	}

	@Override
	public AuthPermissions get(Long id) {
		return authPermissionsDao.get(id);
	}

	@Override
	public int create(AuthPermissions authPermissions) {
		return authPermissionsDao.create(authPermissions);
	}

	@Override
	public int update(AuthPermissions authPermissions) {
		return authPermissionsDao.update(authPermissions);
	}

	@Override
	public int removeBatch(Set<Long> ids) {
		return authPermissionsDao.removeBatch(ids);
	}

	@Override
	public int createAndPossessRoleBatch(AuthPermissions authPermissions,Set<Long> roleIds) {
		int status = this.create(authPermissions);
		if(status==1) {
			status = authPermissionsDao.addRoleBatch(authPermissions.getId(), roleIds);
		}
		return status;
	}
	
	@Override
	public int recoveryBatch(Set<Long> ids) {
		return authPermissionsDao.recoveryBatch(ids);
	}

	@Override
	public List<AuthRole> possessRole(Long permissionsId) {
		List<AuthRole> roleList = authRoleDao.findByPermissionsId(permissionsId);
		return roleList;
	}

	@Override
	public List<AuthUser> possessUser(Long permissionsId) {
		List<AuthUser> userList = authUserDao.findByPermissionsId(permissionsId);
		return userList;
	}

	@Override
	public int addRoleBatch(Long permissionsId, Set<Long> roleIds) {
		int status = authPermissionsDao.addRoleBatch(permissionsId, roleIds);
		return status;
	}

	@Override
	public int removeRoleBatch(Long permissionsId, Set<Long> roleIds) {
		int status = authPermissionsDao.removeRoleBatch(permissionsId, roleIds);
		return status;
	}
}
