package com.box.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.auth.pojo.AuthUser;
import com.box.utils.JsonUtil;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@ResponseBody
	@RequestMapping("/loginPage")
	public String loginIndex(HttpServletRequest request) {
		String retUrl = request.getHeader("Referer"); 
		return JsonUtil.controllerSuccessJson("Login First Please! Referer--->"+retUrl);
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public String login(AuthUser user) throws Exception {
		// 添加用户认证信息
		Subject subject = SecurityUtils.getSubject();
//		String md5Password = EncryptionUtil.encryption(user.getPassword());
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		subject.login(usernamePasswordToken);
//		subject.checkPermissions("index");
		return JsonUtil.controllerSuccessJson(user.getUserName());
	}

	// 注解验角色和权限
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		return "redirect:index.html";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "redirect:index.html";
	}
	
	@ResponseBody
	@RequiresPermissions("unauthorized")
	@RequestMapping("/unauthorized")
	public String testNoPermissions() {
		return "unauthorized!";
	}
}
