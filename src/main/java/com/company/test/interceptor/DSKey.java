package com.company.test.interceptor;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
*
* 数据源选择 注解
* 用在参数上，表示使用对应字段的hashcode来选择数据库
*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DSKey {

   String value() default "";
}
