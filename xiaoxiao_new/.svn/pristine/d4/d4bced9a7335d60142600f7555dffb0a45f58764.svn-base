/**
 * @Title:LoginStatusAnnotation.java
 * @Package:com.web.annotation
 * @Description:TODO 
 * @Author :libc
 * @date:2018年4月3日 下午4:59:36
 * @Version: V1.0.0
 */
package com.web.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * @类名:LoginStatusAnnotation
 * @描述:定义身份验证注释，在对应的Controller类上面加@LoginStatusAnnotation(status=""),其中status的值为登录用户身份
 * @Author: libc
 * @date: 2018年4月3日 下午4:59:36
 */
@Target(ElementType.TYPE) //指定注解的作用域，即可被用在什么上面。TYPE:是类上面;METHOD:是方法上面;FIELD:是属性上面
@Retention(RetentionPolicy.RUNTIME)//定义该注解在哪一个级别可用
public @interface LoginStatusAnnotation {
	public String status() default "0";
}
