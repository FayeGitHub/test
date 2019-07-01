package com.company.test.dao;

import com.company.test.model.User;

public interface UserDao {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public boolean login(User user);
}
