/**
 * 功能描述 
 * 文件名 ArticleDO.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午11:40:00
 **/
package com.zy.blog.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class ArticleDO {
	private long id;//文章Id
	private String authorName;//作者名称
	private String title;//文章标题
	private String summary;//文章摘要
	private String categoryName;//分类名称
	private Date publishDate;//发布日期
	private int original;//是否原创
	private int readNum;//阅读数
	private int like;//喜欢次数
}
