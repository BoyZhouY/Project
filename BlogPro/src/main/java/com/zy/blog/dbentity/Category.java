/**
 * 功能描述 
 * 文件名 Category.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午7:02:12
 **/
package com.zy.blog.dbentity;

import lombok.Data;

/**
 * 分类实体映射类
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class Category {
	/**
	 * 分类Id
	 */
	private long id;
	
	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 分类描述
	 */
	private String desc;
}
