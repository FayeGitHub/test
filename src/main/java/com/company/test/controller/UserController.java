package com.company.test.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
	@ResponseBody
	@RequestMapping("/getUser")
	public User redisTest(User user){
		System.out.println("如果看到User从数据库查询，代表缓冲区没有该值");
		User user1 = userService.get(user);
        return user1; 
   }
	/**
	 * 保存人员信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Validated User user) {
		userService.save(user);
		return"success";
	}
	/**
	 * 删除人员信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(User user) {
		userService.delete(user);
		return"success";
	}
	@RequestMapping("/validUser")
    public String validUser(@Valid User user, BindingResult errors){
		
        if(errors.hasErrors()){
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError objectError :allErrors){
                String defaultMessage = objectError.getDefaultMessage();
                String name = objectError.getObjectName().toString();
                System.out.println(name+"="+defaultMessage);
            }
            System.out.println("allErrors="+allErrors.toArray());
            return  "failed";
        }
        return  "success";
    }
	
}
