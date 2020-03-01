package com.box.auth.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.box.auth.pojo.AuthPermissions;

@Mapper
public interface AuthPermissionsDao {
	public AuthPermissions get(Long id);

	public List<AuthPermissions> query(AuthPermissions authPermissions);

	public List<AuthPermissions> findByRoleId(Long roleId);

	public List<AuthPermissions> findByUserId(Long userId);

	public int create(AuthPermissions authPermissions);

	public int update(AuthPermissions authPermissions);

	public int removeBatch(@Param("ids") Set<Long> ids);

	public int addRoleBatch(@Param("permissionsId") Long permissionsId, @Param("roleIds") Set<Long> roleIds);

	public int removeRoleBatch(@Param("permissionsId") Long permissionsId, @Param("roleIds") Set<Long> roleIds);

	public int recoveryBatch(@Param("ids") Set<Long> ids);

}
