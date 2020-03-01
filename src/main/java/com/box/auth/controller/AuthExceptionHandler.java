package com.box.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.config.CodeConfig;
import com.box.utils.JsonUtil;

@ControllerAdvice
public class AuthExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(AuthExceptionHandler.class);

	@Autowired
	private Environment env;
	
    @ExceptionHandler
    @ResponseBody
    public String AuthorizationExceptionHandler(AuthorizationException e,HttpServletRequest request) {
    	String json = JsonUtil.controllerReturnJson(env.getProperty(CodeConfig.unauthorized),request.getRequestURI());
        log.info("unauthorized--->{}",json);
        return json;
    }
    
    @ExceptionHandler
    @ResponseBody
    public String AuthenticationExceptionHandler(AuthenticationException e,HttpServletRequest request) {
    	String json = JsonUtil.controllerReturnJson(env.getProperty(CodeConfig.username_or_password_error),null);
        log.info("username_or_password_error--->{}",json);
        return json;
    }
}