package com.company.test.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.company.test.interceptor.DSKey;
import com.company.test.interceptor.DataSourceType;
import com.company.test.interceptor.UseDataSource;
import com.company.test.utils.DataSourceSwitcher;

/**
*
* 数据源切换
*/
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE-1) //设置优先级该切面必须要在事务注解@Transactional之前，由于在开始事务之前就需要确定数据源，所以设置DataSourceAsp的**@Order(Ordered.LOWEST_PRECEDENCE-1)
public class DataSourceAsp {

   //@Autowired
   //MonitorService mMonitor;

   /**
             * 针对所有的Mapped
    */
   @Pointcut("@annotation(com.company.test.aop.UseDataSource)")
   public void useDataSource() {
   }

   /**
    * @param joinPoint
    * @return
    * @throws Throwable
    */
   @Around("@annotation(anno)")
   public Object dataSourceSwitcher(ProceedingJoinPoint joinPoint, UseDataSource anno) throws Throwable {
       String ds="";
       if(anno.useHashKey()){
           ds=DataSourceType.getByKey(getHashKeyFromMethod(joinPoint));
       }else{
           DataSourceType value = anno.value();
           ds=value.getSource();
       }
       DataSourceSwitcher.setDataSourceKey(ds);
       try {
           Object result = joinPoint.proceed();
           return result;
       }catch (Exception e){
           throw e;
       }finally {
           DataSourceSwitcher.setDataSourceKey(DataSourceType.SOURCE_1.getSource());
       }
   }


   /**
    * @param joinPoint
    * @return
    * @throws Exception 
    */
   public String getHashKeyFromMethod(ProceedingJoinPoint joinPoint) throws Exception{
       MethodSignature signature=MethodSignature.class.cast(joinPoint.getSignature());
       Method method = signature.getMethod();
       Object[] args = joinPoint.getArgs();
       Parameter[] declaredFields = method.getParameters();
       int index=0;
       for(Parameter temp:declaredFields){
           Annotation[] annotations = temp.getAnnotations();
           for(Annotation anTemp:annotations){
               if(anTemp instanceof DSKey){
                   return String.valueOf(args[index]);
               }
           }
           index++;
       }
       throw new Exception("can not get field with @DsKey annotation");
   }
}