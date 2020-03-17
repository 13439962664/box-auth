package com.box.auth.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
//import com.github.pagehelper.PageInfo;

public interface AuthRoleService extends IService<AuthRole> {
	public IPage<AuthRole> query(AuthRole authRole, Integer pageIndex, Integer pageSize);

	public AuthRole get(Long id);

	public int create(AuthRole authRole);

	public int update(AuthRole authRole);

	public int removeBatch(Set<Long> ids);

	public List<AuthPermissions> possessPermissions(Long roleId);

	public List<AuthUser> possessUser(Long roleId);

	public int addPermissionsBatch(Long roleId, Set<Long> permissionsIds);

	public int removePermissionsBatch(Long roleId, Set<Long> permissionsIds);

	public int recoveryBatch(Set<Long> ids);

	public int addUserBatch(Long roleId, Set<Long> userIds);

	public int removeUserBatch(Long roleId, Set<Long> userIds);
}
