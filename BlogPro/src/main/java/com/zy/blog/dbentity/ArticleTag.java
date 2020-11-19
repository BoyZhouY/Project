/**
 * 功能描述 
 * 文件名 ArticleTag.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午7:29:14
 **/
package com.zy.blog.dbentity;

import lombok.Data;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class ArticleTag {
	/**
	 * 文章Id
	 */
	private long artcleId;
	
	/**
	 * 标签Id
	 */
	private long tagId;
}
