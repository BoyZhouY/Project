/**
 * 功能描述 
 * 文件名 QueryArtcleByTagReq.java
 * 作者 周泰斗
 * 编写日期 2020年11月21日下午5:36:50
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
public class QueryArtcleByTagReq {
	@NotBlank(message = "标签名称不能为空")
	@NotNull(message = "标签名称不能为空") 
	private String tagName;
	@NotNull(message = "页码不能为空") 
	private Integer pageIndex = 1;
}
