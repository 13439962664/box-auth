package com.box.auth.custom;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.box.auth.pojo.AuthPermissions;
import com.box.auth.pojo.AuthRole;
import com.box.auth.pojo.AuthUser;
import com.box.auth.service.LoginService;

/**
 * 验证方式
 * @author sunyizhuo
 *
 */
public class ShiroCustomRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;
	
	/**
	 * 初始角色、权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		AuthUser user = (AuthUser) principalCollection.getPrimaryPrincipal();
		// 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (AuthRole role : user.getRoles()) {
			// 添加角色
			simpleAuthorizationInfo.addRole(role.getRoleCode());
			// 添加权限
			for (AuthPermissions permissions : role.getPermissions()) {
				simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsCode());
			}
		}
		return simpleAuthorizationInfo;
	}

	/**
	 * 用户名、密码验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		// 获取用户信息
		String name = authenticationToken.getPrincipal().toString();
		AuthUser user = loginService.getUserByName(name);
		if (user == null) {
			// 这里返回后会报出对应异常
			return null;
		} else {
			// 这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,
					user.getPassword(), getName());
			return simpleAuthenticationInfo;
		}
	}
}
