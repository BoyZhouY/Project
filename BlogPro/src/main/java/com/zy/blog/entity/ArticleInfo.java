/**
 * 功能描述 
 * 文件名 ArticleInfo.java
 * 作者 周泰斗
 * 编写日期 2020年6月16日下午9:54:59
 **/
package com.zy.blog.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class ArticleInfo {
	private long id;
	private String authorId;
	private String title;
	private String summary;//文章摘要
	private String filename;//md文件名称
	private String mdContent;
	private long categoryId;//分类Id
	private String category;//分类Id
	private String url;//转载Url
	private Date publishDate;//发布日期
	private Date updateDate;//发布日期
	private Long preArticleId;
	private Long nextArticleId;
	private String preArticleName;
	private String nextArticleName;
	private int original;
	private int open;
	private int readNumber;
	private String[] tags;
	
}
