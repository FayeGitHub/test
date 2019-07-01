package com.company.test.service;

import com.company.test.model.User;

public interface UserService {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public boolean login(User user);
}
