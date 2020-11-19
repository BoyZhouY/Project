/**
 * 功能描述 
 * 文件名 LoginReq.java
 * 作者 周泰斗
 * 编写日期 2020年5月24日上午11:07:29
 **/
package com.zy.blog.controller.param;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class LoginReq {
	/**
	 * 用户唯一识别身份证
	 */
	@NotEmpty(message = "用户名不能为空")
	private String identify;
	/**
	 * 用户密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;
	/**
	 * 记住密码
	 */
	private Boolean remeber;
}
