package com.box.auth.service;

import com.box.auth.pojo.AuthUser;

public interface LoginService {
	public AuthUser getUserByName(String name);
}
