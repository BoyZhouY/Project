/**
 * 功能描述 
 * 文件名 CategoryController.java
 * 作者 周泰斗
 * 编写日期 2020年6月28日下午9:14:27
 **/
package com.zy.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zy.blog.common.Response;
import com.zy.blog.controller.param.AddCategoryReq;
import com.zy.blog.dbentity.Category;
import com.zy.blog.service.ICategoryService;
import com.zy.blog.util.ResponseUtil;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@RestController
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(value="/category")
	public Response query() {
		List<Category> categorys = categoryService.query();
		return ResponseUtil.success(categorys);
	}
	
	/**
     * 新增一个分类
     *
     * @param name 分类名
     * @return Response
     */
    @PostMapping("/category")
    public Response addOne(@RequestBody AddCategoryReq req) {
        return ResponseUtil.success(categoryService.create(req.getName(),req.getDesc()));
    }

    /**
     * 删除一个分类
     *
     * @param id 分类id
     * @return Response
     */
    @DeleteMapping("category/{id}")
    public Response deleteOne(@PathVariable long id) {
    	categoryService.delete(id);
        return ResponseUtil.success();
    }

    /**
     * 更新一个分类
     *
     * @param id   分类id
     * @param name 更新后的名字
     * @return Response
     */
    @PutMapping("/category/{id}")
    public Response updateOne(@PathVariable long id,@RequestBody AddCategoryReq req) {
        return ResponseUtil.success(categoryService.update(id, req.getName(),req.getDesc()));
    }
}
