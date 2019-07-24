package com.company.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.company.test.dao.UserDao;
import com.company.test.model.User;
import com.company.test.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
//	@Cacheable(value = "redisCacheManager", key = "'redis_role_'+#id")
	public boolean login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
}
