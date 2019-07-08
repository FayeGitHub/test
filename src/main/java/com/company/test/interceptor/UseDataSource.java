package com.company.test.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseDataSource {

     /**
                           * 数据源
      * @return
      */
     DataSourceType value() default DataSourceType.SOURCE_1;


     /**
      * use member in method to calculate hash key then choose the dataSource
      *
      * @return
      */
     boolean useHashKey() default false;



     /**
      * use spel expression to calculate hash key then choose the dataSource
      * @return
      */
     String  hashExp() default "";


     /**
      * assign a dataSource key,this key is in {@link com.netease.mail.activity.multiDataSource.aop.DataSourceAsp}
      * targetDataSource map
      * @return
      */
     String source() default "";

}
