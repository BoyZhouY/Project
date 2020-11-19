/**
 * 功能描述 
 * 文件名 DisableAuth.java
 * 作者 周泰斗
 * 编写日期 2020年5月21日下午9:34:17
 **/
package com.zy.blog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DisableAuth {

}
