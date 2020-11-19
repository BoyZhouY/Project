/**
 * 功能描述 
 * 文件名 ICategoryService.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午9:27:23
 **/
package com.zy.blog.service;

import java.util.List;

import com.zy.blog.dbentity.Category;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface ICategoryService {

	/**
	 * 查询分类
	 * 
	 * @return 分类集合
	 */
	List<Category> query();

	/**
	 * @param name
	 * @param desc
	 * @return
	 */
	Category create(String name, String desc);

	/**
	 * @param id
	 */
	void delete(long id);

	/**
	 * @param id
	 * @param name
	 * @param desc
	 * @return
	 */
	Category update(long id, String name, String desc);
}
