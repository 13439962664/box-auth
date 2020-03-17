package com.box.auth.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.box.auth.pojo.AuthRole;

@Mapper
public interface AuthRoleDao extends BaseMapper<AuthRole>{
//	public AuthRole get(Long id);

//	public List<AuthRole> query(AuthRole authRole);

	public List<AuthRole> findByUserId(Long userId);
	
	public List<AuthRole> findByPermissionsId(Long permissionsId);

//	public int create(AuthRole authRole);

//	public int update(AuthRole authRole);

//	public int removeBatch(@Param("ids")Set<Long> ids);
	
	public int addPermissionsBatch(@Param("roleId")Long roleId,@Param("permissionsIds")Set<Long> permissionsIds);
	
	public int removePermissionsBatch(@Param("roleId")Long roleId,@Param("permissionsIds")Set<Long> permissionsIds);
	
	public int recoveryBatch(@Param("ids")Set<Long> ids);
	
	public int addUserBatch(@Param("roleId")Long roleId,@Param("userIds")Set<Long> userIds);
	
	public int removeUserBatch(@Param("roleId")Long roleId,@Param("userIds")Set<Long> userIds);
	
}
