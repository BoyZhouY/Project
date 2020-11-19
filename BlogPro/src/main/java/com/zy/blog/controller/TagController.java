/**
 * 功能描述 
 * 文件名 TagController.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午9:39:59
 **/
package com.zy.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.blog.common.Response;
import com.zy.blog.dbentity.Tag;
import com.zy.blog.service.ITagService;
import com.zy.blog.util.ResponseUtil;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@RestController
public class TagController {
	
	@Autowired
	private ITagService tagService;
	
	@GetMapping(value="/tags")
	public Response query() {
		List<Tag> tags = tagService.query();
		return ResponseUtil.success(tags);
	}
}
