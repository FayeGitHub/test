package com.company.test.dao.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.test.dao.UserDao;
import com.company.test.model.User;
import com.company.test.utils.DataSourceSwitcher;

import base.TestBase;

public class UserDaoImplTest extends TestBase{
	
	@Autowired
	UserDao userDao ;

	@Test
	public void testLogin() {
		DataSourceSwitcher.setDataSourceKey("ds1");
		User user = new User();
		user.setUsername("cff");
		user.setPassword("123456");
		assertTrue(userDao.login(user));
		//System.out.println(user.getValid());
	}

}
