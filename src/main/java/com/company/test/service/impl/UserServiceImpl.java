package com.company.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.test.dao.UserDao;
import com.company.test.model.User;
import com.company.test.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
}
