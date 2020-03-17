package com.box.auth.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.box.auth.pojo.AuthUser;

public class ShiroUtil {
	
	/**
	 * 获取会话
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * Subject：主体，代表了当前“用户”
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static AuthUser getUserEntity() {
		return (AuthUser) SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getId();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
}
