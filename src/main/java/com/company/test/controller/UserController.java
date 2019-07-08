package com.company.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.test.interceptor.DataSourceType;
import com.company.test.interceptor.UseDataSource;
import com.company.test.model.User;
import com.company.test.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	private static final Logger logger = LogManager.getLogger(UserController.class);
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")  
	@UseDataSource(DataSourceType.SOURCE_1)
	//@UseDataSource(source = "ds1")//第1种方式设置数据源
	//@UseDataSource(useHashKey = true)//第2种方式设置数据源
	//@UseDataSource(hashExp = "'ds_'+#uid")//第3种方式设置数据源
    public @ResponseBody boolean login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		logger.info("信息：用户登录信息提交");
        return userService.login(user);
    }  
}
