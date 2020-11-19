/**
 * 功能描述 
 * 文件名 AddArtcleReq.java
 * 作者 周泰斗
 * 编写日期 2020年6月16日下午7:56:52
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
public class ArticleReq {
	/**
	 * 用户Id
	 */
	@NotBlank(message = "用户Id为空")
	@NotNull(message = "用户Id为空")
	private String userUid;
	
	/**
	 * 用户token
	 */
	@NotBlank(message = "用户会话Id为空")
	@NotNull(message = "用户会话Id为空")
	private String token;
	
	/**
	 * 文章标题
	 */
	@NotBlank(message = "文章标题为空")
	@NotNull(message = "文章标题为空")
	private String title;
	
	/**
	 * 文章md 文本
	 */
	@NotBlank(message = "文章内容为空")
	@NotNull(message="文章内容为空")
	private String mdContent;
	
	/**
	 * 标签，用“，分割”
	 */
	private String tags;
	
	/**
	 * 转载文章的url
	 */
	private String url;
	/**
	 * 分类Id
	 */
	@NotNull(message = "文章分类为空")
	@NotBlank(message = "文章分类为空")
	private String categorys;
	
	/**
	 * 是否为发布  true-发布 false-不发布
	 */
	private Boolean open = true;
	
	/**
	 * 原创
	 */
	private Boolean original = true;
}
