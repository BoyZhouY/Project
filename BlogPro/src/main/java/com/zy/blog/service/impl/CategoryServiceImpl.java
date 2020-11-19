/**
 * 功能描述 
 * 文件名 CategoryServiceImpl.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午9:30:11
 **/
package com.zy.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.blog.common.ResponseEnum;
import com.zy.blog.dbentity.Category;
import com.zy.blog.exception.MyException;
import com.zy.blog.mapper.CategoryMapper;
import com.zy.blog.service.ICategoryService;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> query() {
		return categoryMapper.query();
	}

	@Override
	public Category create(String name, String desc) {
		Category category = new Category();
		category.setName(name);
		category.setDesc(desc);
		
		categoryMapper.insert(category);
		return category;
	}

	@Override
	public void delete(long id) {
		Category category = categoryMapper.queryById(id);
		if (category == null) {
			throw new MyException(ResponseEnum.NORECORD_ERROR);
		}
		//删除分类
		categoryMapper.delete(id);
		
		//删除分类关联表记录
		
	}

	@Override
	public Category update(long id, String name, String desc) {
		Category category = categoryMapper.queryById(id);
		if (category == null) {
			throw new MyException(ResponseEnum.NORECORD_ERROR);
		}
		
		//更新
		categoryMapper.update(id,name,desc);
		category.setName(name);
		category.setDesc(desc);
		return category;
	}
}
