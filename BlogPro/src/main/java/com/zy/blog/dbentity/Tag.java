/**
 * 功能描述 
 * 文件名 Tag.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午7:37:21
 **/
package com.zy.blog.dbentity;

import lombok.Data;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Data
public class Tag {
	/**
	 * 标签Id
	 */
	private long id;
	
	/**
	 * 标签名称
	 */
	private String tagName;
}
