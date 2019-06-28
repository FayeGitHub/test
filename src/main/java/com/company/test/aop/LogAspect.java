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

/**
 * @author fastsun
 * @date 2019-06-28
 * web调用打印调用前参数和调用后参数
 */
@Component
@Aspect
public class LogAspect {
	@Pointcut("execution(* com.company.test.controller.*.*(..))")//切点表达式
	public void log() {}
	
	@Before("log()")
	public void before(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		//Object[] args = joinPoint.getArgs();
		System.out.println("before aspect executing");
	}
	
	@After("log()")
	public void after(JoinPoint joinPoint) {
		System.out.println("after aspect executed");
	}

	

	@AfterReturning(pointcut = "log()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
		System.out.println("afterReturning executed, return result is "
				+ returnVal);
	}

	@Around("log()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around start..");
		try {
			pjp.proceed();
		} catch (Throwable ex) {
			System.out.println("error in around");
			throw ex;
		}
		System.out.println("around end");
	}

	@AfterThrowing(pointcut = "log()", throwing = "error")
	public void afterThrowing(JoinPoint jp, Throwable error) {
		System.out.println("error:" + error);
	}
}
