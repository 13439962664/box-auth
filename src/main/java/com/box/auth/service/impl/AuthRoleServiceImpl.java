package com.box.auth.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.box.auth.dao.AuthPermissionsDao;
import com.box.auth.dao.AuthRoleDao;
import com.box.auth.dao.AuthUserDao;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.AuthRoleService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;

@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleDao, AuthRole> implements AuthRoleService {

	@Autowired
	private AuthRoleDao authRoleDao;
	
	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Autowired
	private AuthUserDao authUserDao;
	
	@Override
	public IPage<AuthRole> query(AuthRole authRole,Integer pageIndex,Integer pageSize) {
		IPage<AuthRole> page = new Page<AuthRole>(pageIndex,pageSize);
		QueryWrapper<AuthRole> query = new QueryWrapper<AuthRole>();
		if(authRole.getDel_()!=null) {
			query.eq("del_", authRole.getDel_());
		}
		if(!(authRole.getRoleName()==null||"".equals(authRole.getRoleName()))) {
			query.like("role_name", authRole.getRoleName());
		}
		page = authRoleDao.selectPage(page, query);
		return page;
	}
	

	@Override
	public AuthRole get(Long id) {
		return authRoleDao.selectById(id);
	}

	@Override
	public int create(AuthRole authRole) {
		return authRoleDao.insert(authRole);
	}

	@Override
	public int update(AuthRole authRole) {
		return authRoleDao.updateById(authRole);
	}

	@Override
	public int removeBatch(Set<Long> ids) {
		return authRoleDao.deleteBatchIds(ids);
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
