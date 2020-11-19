/**
 * 功能描述 
 * 文件名 AddCategoryReq.java
 * 作者 周泰斗
 * 编写日期 2020年11月14日下午5:00:35
 **/
package com.zy.blog.controller.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Getter@Setter
public class AddCategoryReq extends Request {
	@NotNull(message = "分类名称不能为空")
	@NotBlank(message="分类名称不能为空")
	private String name;
	private String desc;
}
