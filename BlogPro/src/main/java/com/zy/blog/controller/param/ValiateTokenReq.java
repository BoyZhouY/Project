/**
 * 功能描述 
 * 文件名 ValiateTokenReq.java
 * 作者 周泰斗
 * 编写日期 2020年6月15日下午10:57:16
 **/
package com.zy.blog.controller.param;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Setter@Getter
public class ValiateTokenReq {
	/**
	 * 用户uid
	 */
	@NotBlank(message = "用户uid不能为空")
	private String userUid;
	
	/**
	 * 激活码
	 */
	@NotBlank(message = "激活码不能为空")
	private String code;
}
