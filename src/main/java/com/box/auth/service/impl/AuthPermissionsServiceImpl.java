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
import com.box.auth.service.AuthPermissionsService;

@Service
public class AuthPermissionsServiceImpl extends ServiceImpl<AuthPermissionsDao, AuthPermissions> implements AuthPermissionsService {

	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Autowired
	private AuthRoleDao authRoleDao;
	
	@Autowired
	private AuthUserDao authUserDao;
	
	@Override
	public IPage<AuthPermissions> query(AuthPermissions authPermissions,Integer pageIndex,Integer pageSize) {
		IPage<AuthPermissions> page = new Page<AuthPermissions>(pageIndex,pageSize);
		QueryWrapper<AuthPermissions> query = new QueryWrapper<AuthPermissions>();
		if(authPermissions.getDel_()!=null) {
			query.eq("del_", authPermissions.getDel_());
		}
		if(!(authPermissions.getPermissionsName()==null||"".equals(authPermissions.getPermissionsName()))) {
			query.like("permissions_name", authPermissions.getPermissionsName());
		}
		page = authPermissionsDao.selectPage(page, query);
		return page;
	}

	@Override
	public AuthPermissions get(Long id) {
		return authPermissionsDao.selectById(id);
	}

	@Override
	public int create(AuthPermissions authPermissions) {
		return authPermissionsDao.insert(authPermissions);
	}

	@Override
	public int update(AuthPermissions authPermissions) {
		return authPermissionsDao.updateById(authPermissions);
	}

	@Override
	public int removeBatch(Set<Long> ids) {
		return authPermissionsDao.deleteBatchIds(ids);
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
