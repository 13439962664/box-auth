package com.box.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.auth.pojo.AuthUser;
import com.box.auth.utils.ShiroUtil;
import com.box.pojo.BasePojo;
import com.box.service.MyInfoService;

@Service
public class MyInfoServiceImpl implements MyInfoService {

	private static final Logger log = LoggerFactory.getLogger(MyInfoServiceImpl.class);
	
	@Override
	public BasePojo getMyInfo() {
		AuthUser authUser = ShiroUtil.getUserEntity();
		log.info("ShiroUtil.getUserEntity()--->{}",authUser);
		return authUser;
	}

}
