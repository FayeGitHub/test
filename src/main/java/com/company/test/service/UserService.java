package com.company.test.service;

import com.company.test.model.User;

public interface UserService {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public boolean login(User user);
	
	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 */
	public User get(User user);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public User save(User user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean delete(User user);
}
