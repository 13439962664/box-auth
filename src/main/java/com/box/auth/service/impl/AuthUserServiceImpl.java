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
import com.box.auth.service.AuthUserService;
import com.box.utils.EncryptionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private AuthRoleDao authRoleDao;
	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Override
	public PageInfo<AuthUser> query(AuthUser authUser,Integer pageIndex,Integer pageSize) {
		final PageInfo<AuthUser> pageInfo = PageHelper.startPage(pageIndex, pageSize).setOrderBy("id desc")
				.doSelectPageInfo(() -> this.authUserDao.query(authUser));
		return pageInfo;
	}

	@Override
	public AuthUser get(Long id) {
		return authUserDao.get(id);
	}

	@Override
	public int create(AuthUser authUser) {
		authUser.setPassword(EncryptionUtil.encryption(authUser.getPassword()));
		int status = authUserDao.create(authUser);
		authUser.setPassword(null);
		return status;
	}

	@Override
	public int updatePassword(AuthUser authUser) {
		authUser.setPassword(EncryptionUtil.encryption(authUser.getPassword()));
		authUser.setOldPassword(EncryptionUtil.encryption(authUser.getOldPassword()));
		int status = authUserDao.updatePassword(authUser);
		authUser.setPassword(null);
		authUser.setOldPassword(null);
		return status;
	}

	@Override
	public int removeBatch(Set<Long> ids) {
		return authUserDao.removeBatch(ids);
	}

	@Override
	public List<AuthRole> possessRole(Long userId) {
		List<AuthRole> roleList = authRoleDao.findByUserId(userId);
		return roleList;
	}

	@Override
	public int addRoleBatch(Long userId, Set<Long> roleIds) {
		int status = authUserDao.addRoleBatch(userId, roleIds);
		return status;
	}

	@Override
	public int removeRoleBatch(Long userId, Set<Long> roleIds) {
		int status = authUserDao.removeRoleBatch(userId, roleIds);
		return status;
	}

	@Override
	public int recoveryBatch(Set<Long> ids) {
		return authUserDao.recoveryBatch(ids);
	}

	@Override
	public List<AuthPermissions> possessPermissions(Long userId) {
		List<AuthPermissions> permissionsList = authPermissionsDao.findByUserId(userId);
		return permissionsList;
	}

}
