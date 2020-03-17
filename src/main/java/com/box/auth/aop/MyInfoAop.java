package com.box.auth.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.box.auth.utils.ShiroUtil;
import com.box.common.SysConstant;
@Aspect
@Component
public class MyInfoAop {
	
	@Autowired
	private HttpServletRequest request;
	
	@Pointcut("execution(* com.box.*.controller..*.insert*(..)) "
			+ "|| execution(* com.box.*.controller..*.create*(..)) "
			+ "|| execution(* com.box.*.controller..*.add*(..)) "
			+ "|| execution(* com.box.*.controller..*.update*(..)) "
			+ "|| execution(* com.box.*.controller..*.edit*(..)) "
			+ "|| execution(* com.box.*.controller..*.delete*(..)) "
			+ "|| execution(* com.box.*.controller..*.remove*(..))")
	public void setMyInfoPointcut() {

	}

	@Before("setMyInfoPointcut()")
	public void setMyInfo() {
		request.setAttribute(SysConstant.REQUEST_MY_INFO_ID, ShiroUtil.getUserEntity());
	}
}