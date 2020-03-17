package com.box.auth.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.box.auth.dao.AuthPermissionsDao;
import com.box.auth.dao.AuthRoleDao;
import com.box.auth.dao.AuthUserDao;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.AuthUserService;
import com.box.utils.EncryptionUtil;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class AuthUserServiceImpl extends ServiceImpl<AuthUserDao, AuthUser> implements AuthUserService  {

	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private AuthRoleDao authRoleDao;
	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Override
	public IPage<AuthUser> query(AuthUser authUser,Integer pageIndex,Integer pageSize) {
		IPage<AuthUser> page = new Page<AuthUser>(pageIndex,pageSize);
		QueryWrapper<AuthUser> query = new QueryWrapper<AuthUser>();
		if(authUser.getDel_()!=null) {
			query.eq("del_", authUser.getDel_());
		}
		if(!(authUser.getUserName()==null||"".equals(authUser.getUserName()))) {
			query.like("user_name", authUser.getUserName());
		}
		page = authUserDao.selectPage(page, query);
		return page;
	}

	@Override
	public AuthUser get(Long id) {
		return authUserDao.selectById(id);
	}

	@Override
	public int create(AuthUser authUser) {
		authUser.setPassword(EncryptionUtil.encryption(authUser.getPassword()));
		int status = authUserDao.insert(authUser);
		authUser.setPassword(null);
		return status;
	}

	@Override
	public int updatePassword(AuthUser authUser) {
		authUser.setPassword(EncryptionUtil.encryption(authUser.getPassword()));
		authUser.setOldPassword(EncryptionUtil.encryption(authUser.getOldPassword()));
		UpdateWrapper<AuthUser> update = new UpdateWrapper<AuthUser>();
		update.eq("id",authUser.getId());
		update.eq("password",authUser.getOldPassword());
		int status = authUserDao.update(authUser, update);
//		int status = authUserDao.updatePassword(authUser);
		authUser.setPassword(null);
		authUser.setOldPassword(null);
		return status;
	}

	@Override
	public int removeBatch(Set<Long> ids) {
		return authUserDao.deleteBatchIds(ids);
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
