package com.company.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
	public boolean login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	/**
	 *如果启用Redis，将@Cacheable启用，同时将Redis服务启动。
	 */
	@Override
	@Cacheable(value = "redisCacheManager", key = "'user'+#user.username")
	public User get(User user) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setEmail("aa@qq.com");
		u.setPassword("12");
		u.setUsername(user.getUsername());
		u.setValid(1);
		System.out.println("Redis缓冲区没有该值，新查询结果将保存到缓冲区！");
		try {
			Thread.sleep(5000);//测试缓存功能，模拟查询耗时的情况
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	@CachePut(value = "redisCacheManager", key = "'user'+#user.username")
	public User save(User user) {
		System.out.println("User执行保存操作，同时返回的保存对象保存到缓存中");
		//执行保存操作 必须返回User，作为缓存，调用Get的时候查询缓存内容
		return user;
	}

	@Override
	@CacheEvict(value = "redisCacheManager", key = "'user'+#user.username")
	public boolean delete(User user) {
		boolean result = false;
		result = true;
		System.out.println("User执行删除操作,同时删除了缓存的数据");
		return result;
	}
}
