/**
 * 功能描述 
 * 文件名 QueryArtcleReq.java
 * 作者 周泰斗
 * 编写日期 2020年11月14日下午12:57:32
 **/
package com.zy.blog.controller.param;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Getter@Setter
public class QueryArtcleReq {
	@NotNull(message = "参数不能为空")
	private Integer mode;
	@NotNull(message = "页码不能为空") 
	private Integer pageIndex;
}
