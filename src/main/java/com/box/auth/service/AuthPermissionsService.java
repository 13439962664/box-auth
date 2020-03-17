package com.box.auth.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
//import com.github.pagehelper.PageInfo;

public interface AuthPermissionsService extends IService<AuthPermissions> {
	public IPage<AuthPermissions> query(AuthPermissions authPermissions, Integer pageIndex,
			Integer pageSize);

	public AuthPermissions get(Long id);

	public int create(AuthPermissions authPermissions);

	public int update(AuthPermissions authPermissions);

	public int removeBatch(Set<Long> ids);

	public int createAndPossessRoleBatch(AuthPermissions authPermissions, Set<Long> roleIds);

	public int recoveryBatch(Set<Long> ids);

	public List<AuthRole> possessRole(Long permissionsId);

	public List<AuthUser> possessUser(Long permissionsId);

	public int addRoleBatch(Long permissionsId, Set<Long> roleIds);

	public int removeRoleBatch(Long permissionsId, Set<Long> roleIds);
}
