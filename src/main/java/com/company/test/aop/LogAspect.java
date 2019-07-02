package com.company.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author fastsun
 * @date 2019-06-28
 * web调用打印调用前参数和调用后参数
 */
@Component
@Aspect
public class LogAspect {
	private static final Logger logger = LogManager.getLogger(LogAspect.class);
	private static final String UTF_8 = "utf-8";
	
	// jackson转换工具
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

	
	@Pointcut("execution(public * com.company.test.controller.*.*(..))")//切点表达式
	public void log() {}
	
	@Before("log()")
	public void before(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		//Object[] args = joinPoint.getArgs();
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);

		System.out.println("before aspect executing");
	}
	
	/*
	 * @After("log()") public void after(JoinPoint joinPoint) {
	 * System.out.println("after aspect executed"); }
	 */

	

	@AfterReturning(pointcut = "log()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
		System.out.println("afterReturning executed, return result is "
				+ returnVal);
	}

	
	 @Around("log()") 
	 public Object  around(ProceedingJoinPoint pjp) throws Throwable
	 { 
		 RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		long endTime = System.currentTimeMillis();
		//String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		Object[] args = pjp.getArgs();
		String params = "";
		// result的值就是被拦截方法的返回值
		Object result = pjp.proceed();
		try {
		    long startTime = (long) request.getAttribute("startTime");
		    //获取请求参数集合并进行遍历拼接
		    if (args.length > 0) {
		        if ("POST".equals(method)) {
		            Object object = args[0];
		            //params = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
		            params = objectMapper.writeValueAsString(object);
		        } else if ("GET".equals(method)) {
		            params = queryString;
		        }
		        params = URLDecoder.decode(params,UTF_8);
		    }
		    logger.info("requestMethod:{},url:{},params:{},responseBody:{},elapsed:{}ms.", method , uri,  params,
		            //JSON.toJSONString(result,SerializerFeature.WriteMapNullValue),
		    		objectMapper.writeValueAsString(result),
		            (endTime - startTime));
		}catch (Exception e){
		    e.printStackTrace();
		    logger.error("log error !!",e);
		}
		return result;
	 }
	 
	/*
	 * @AfterThrowing(pointcut = "log()", throwing = "error") public void
	 * afterThrowing(JoinPoint jp, Throwable error) { System.out.println("error:" +
	 * error); }
	 */
	
}
