package com.company.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class HelloWorldController {
	/**
	 * 测试
	 * @param request
	 * @param response
	 * @return
	 */
	Logger logger = LogManager.getLogger(HelloWorldController.class);
	
	@RequestMapping("/helloworld.do")
	public String helloworld(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("message","你好，世界");
		System.out.println("你好，程序员!");
		logger.info("信息，测试日志输出");
		return "helloworld";
	}
	@RequestMapping("/index")  
    public String home(HttpServletRequest request, HttpServletResponse response) {  
        return "index";  
    }  
}
