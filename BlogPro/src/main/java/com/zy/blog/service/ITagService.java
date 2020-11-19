/**
 * 功能描述 
 * 文件名 ITagService.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午9:42:33
 **/
package com.zy.blog.service;

import java.util.List;

import com.zy.blog.dbentity.Tag;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface ITagService {

	/**
	 * 查询所有标签
	 * @return
	 */
	List<Tag> query();
	
}
