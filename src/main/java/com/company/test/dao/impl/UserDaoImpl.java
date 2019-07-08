package com.company.test.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.test.dao.UserDao;
import com.company.test.model.User;
@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = ? and password = ?;";
		List<User> userList = jdbcTemplate.query(sql,  new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
		if(userList.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
}
