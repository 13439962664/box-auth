package com.box.auth.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.box.auth.dao.AuthPermissionsDao;
import com.box.auth.dao.AuthRoleDao;
import com.box.auth.dao.AuthUserDao;
import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.LoginService;

/**
 * 登入服务
 * @author sunyizhuo
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private AuthUserDao authUserDao;
	@Autowired
	private AuthRoleDao authRoleDao;
	@Autowired
	private AuthPermissionsDao authPermissionsDao;
	
	@Override
	public AuthUser getUserByName(String userName) {
		// 模拟数据库查询，正常情况此处是从数据库或者缓存查询。
		return getMapByName(userName);
	}

	/**
	 * 模拟数据库查询
	 * 
	 * @param userName
	 * @return
	 */
	private AuthUser getMapByName(String userName) {
//		AuthUser user = authUserDao.getByUserName(userName);
		AuthUser user = authUserDao.selectOne(new QueryWrapper<AuthUser>().eq("user_name", userName));
		if(!(user==null||user.getId()==null)) {
			List<AuthRole> roleList = authRoleDao.findByUserId(user.getId());
			
			Set<AuthRole> roleSet = new HashSet<>();
			for(AuthRole role:roleList) {
				Set<AuthPermissions> permissionsSet = new HashSet<>();
				List<AuthPermissions> permissionsList = authPermissionsDao.findByRoleId(role.getId());
				for(AuthPermissions permissions:permissionsList) {
					permissionsSet.add(permissions);
				}
				role.setPermissions(permissionsSet);
				roleSet.add(role);
			}
			
			
			user.setRoles(roleSet);
		}
		return user;
	}
}
