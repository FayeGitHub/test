package com.company.test.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	
	//protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//String relativelyPath = request.getContextPath();//项目名
		//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + relativelyPath + "/";
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Authorization authorization = handlerMethod.getMethodAnnotation(Authorization.class);
			
			if (authorization == null || authorization.validate() == false){
				HttpSession session=request.getSession();
				session.setAttribute("AuthorityState",null);
				//有权限访问
				return true;				
			}
			else {
				//无权限访问
				return false;
			}
		}
		else 
			return true;
	}
	
}
