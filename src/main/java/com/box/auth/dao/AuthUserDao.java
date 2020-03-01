package com.box.auth.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.box.auth.pojo.AuthUser;

@Mapper
public interface AuthUserDao {

	public List<AuthUser> query(AuthUser authUser);

	public AuthUser get(Long id);

	public AuthUser getByUserName(String userName);

	public int create(AuthUser authUser);

	public int updatePassword(AuthUser authUser);

	public int removeBatch(@Param("ids")Set<Long> ids);
	
	public int addRoleBatch(@Param("userId")Long userId,@Param("roleIds")Set<Long> roleIds);
	
	public int removeRoleBatch(@Param("userId")Long userId,@Param("roleIds")Set<Long> roleIds);
	
	public int recoveryBatch(@Param("ids")Set<Long> ids);
	
	public List<AuthUser> findByPermissionsId(Long permissionsId);
	
	public List<AuthUser> findByRoleId(Long roleId);
}
