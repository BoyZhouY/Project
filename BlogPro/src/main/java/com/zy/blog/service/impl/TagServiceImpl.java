/**
 * 功能描述 
 * 文件名 TagServiceImpl.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午9:44:17
 **/
package com.zy.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.blog.dbentity.Tag;
import com.zy.blog.mapper.TagMapper;
import com.zy.blog.service.ITagService;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Service
public class TagServiceImpl implements ITagService {

	@Autowired
	private TagMapper tagMapper;
	
	@Override
	public List<Tag> query() {
		List<Tag> tags = tagMapper.query();
		return tags;
	}

}
