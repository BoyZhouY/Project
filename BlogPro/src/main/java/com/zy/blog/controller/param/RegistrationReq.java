/**
 * 功能描述 
 * 文件名 MyRequest.java
 * 作者 周泰斗
 * 编写日期 2020年5月10日下午7:13:54
 **/
package com.zy.blog.controller.param;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Getter@Setter
public class RegistrationReq {
	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	private String username;
	/**
	 * 用户昵称
	 */
	@NotBlank(message = "用户昵称不能为空")
	private String nickName;
	/**
	 * 用户邮箱
	 */
	@NotBlank(message = "用户邮箱不能为空")
	private String email;
	/**
	 * 用户密码
	 */
	@NotBlank(message = "用户密码不能为空")
	private String password;
}
