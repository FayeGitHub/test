package com.company.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.test.model.User;
import com.company.test.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")  
    public @ResponseBody boolean login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
        return userService.login(user);
    }  
}
