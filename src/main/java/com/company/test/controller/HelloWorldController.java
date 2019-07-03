package com.company.test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.test.model.User;
//import com.company.test.utils.ExcelUtils;
import com.mysql.cj.util.StringUtils;

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
	/**
	 * 导出用户数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/export.do")
	public String exportOrder(HttpServletRequest request, HttpServletResponse response){	
		//String userName = request.getParameter("userName");
		
		/*
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Map<String, String> headNameMap = new LinkedHashMap<String, String>();
		headNameMap.put("roleName", "角色");
		headNameMap.put("userName", "账号");
		headNameMap.put("realName", "姓名");
		headNameMap.put("mobile", "电话号码");
		headNameMap.put("createDate", "创建时间");
		headNameMap.put("status", "状态");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < 40; i++) {
			
			String statusName = "正常";
			String createDate = "";
			String roleName = "";
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("roleName", "管理员");
			map.put("userName", "zhangsan"+i);
			map.put("realName", "张三");
			map.put("mobile", "123331");
			map.put("createDate", createDate);
			map.put("status", statusName);
			list.add(map);
		}
		
		ExcelUtils.exportXlsx(response, "用户数据", headNameMap, list);
		*/
		return null;
	}
}
