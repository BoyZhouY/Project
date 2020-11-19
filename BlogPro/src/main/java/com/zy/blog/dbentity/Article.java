/**
 * 功能描述 
 * 文件名 Article.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午10:29:30
 **/
package com.zy.blog.dbentity;

import java.util.Date;

import lombok.Data;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class Article {
	private long id;
	private String title;
	private String summary;
	private String mdContent;
	private String url;
	private String authorId;
	private int open;
	private int original;
	private long nextId;
	private long preId;
	private int readNumber;
	private Date publishDate;
	private Date updateDate;
	private int like;
}
