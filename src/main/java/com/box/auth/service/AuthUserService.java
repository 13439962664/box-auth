package com.box.auth.service;

import java.util.List;
import java.util.Set;

import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.github.pagehelper.PageInfo;

public interface AuthUserService {
	public PageInfo<AuthUser> query(AuthUser authUser,Integer pageIndex,Integer pageSize);
	public AuthUser get(Long id);
	public int create(AuthUser authUser);
	public int updatePassword(AuthUser authUser);
	public int removeBatch(Set<Long> ids);
	public List<AuthRole> possessRole(Long userId);
	public List<AuthPermissions> possessPermissions(Long userId);
	public int addRoleBatch(Long userId,Set<Long> roleIds);
	public int removeRoleBatch(Long userId,Set<Long> roleIds);
	public int recoveryBatch(Set<Long> ids);
}
