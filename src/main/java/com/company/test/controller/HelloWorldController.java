package com.company.test.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.company.test.model.User;
import com.company.test.utils.ExcelUtils;
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
	@Autowired
	RedisTemplate redisTemplate;
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
	@ResponseBody
	@RequestMapping("/helloredis.do")
	public String redisTest(){
		//redisTemplate.opsForValue().set("test", "hello redis");
        String obj = (String)redisTemplate.opsForValue().get("test");
        System.out.println(obj);
        return obj;
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
		
		return null;
	}
	@ResponseBody    
    @RequestMapping(value="import.do")    
    public String importOrder(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {    
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        
        MultipartFile file = multipartRequest.getFile("upfile");       
        if(file.isEmpty()){    
            throw new Exception("文件不存在！");    
        }    
        
        InputStream in = file.getInputStream();  
        List<List<String>> data = ExcelUtils.readXlsx(in,0);
        in.close(); 
        String aa = "";
        for (List<String> rowList : data) {
			for (String cell : rowList) {
				aa += cell + "-";
			}
			aa = aa + "\n";
		}
        return aa;
    } 
	@ResponseBody   
    @RequestMapping(value="upload.do")
    public String fileUpload(@RequestParam(value = "upfile") List<MultipartFile> files, HttpServletRequest request) throws Exception {
        String msg = "";
        String aa = "";
        // 判断文件是否上传
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
            	InputStream in = file.getInputStream();  
                List<List<String>> data = ExcelUtils.readXlsx(in,0);
                in.close(); 
                
                for (List<String> rowList : data) {
        			for (String cell : rowList) {
        				aa += cell + "  -  ";
        			}
        			aa = aa + "\n";
        		}
            }
            msg = "文件上传成功！";
        } else {
            msg = "没有文件被上传！";
        }
        request.setAttribute("msg", msg);
        return aa;
    }


}
