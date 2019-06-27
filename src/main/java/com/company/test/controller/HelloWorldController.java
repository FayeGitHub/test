package com.company.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	/**
	 * 测试
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/helloworld.do")
	public String helloworld(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("message","你好，世界");
		return "helloworld";
	}

}
