/**
 * 功能描述 
 * 文件名 CategoryMapper.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午7:01:23
 **/
package com.zy.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zy.blog.dbentity.Category;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface CategoryMapper {

	/**
	 * 根据Id查询分类
	 * @param categoryId
	 * @return
	 */
	@Select("SELECT * FROM t_category WHERE c_id=#{categoryId}")
	@Results(id="categoryMap",value= {
			@Result(property = "id",column = "c_id"),
            @Result(property = "name",column = "c_name"),
            @Result(property = "desc",column = "c_desc"),
	})
	Category queryById(long categoryId);
	
	/**
	 * 根据名称查询分类
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM t_category WHERE c_name=#{name}")
	@ResultMap(value = "categoryMap")
	Category queryByName(String name);

	/**
	 * 
	 * @return
	 */
	@Select("SELECT * FROM t_category")
	@ResultMap(value = "categoryMap")
	List<Category> query();

	/**
	 * @param category
	 */
	@Insert("INSERT INTO t_category (c_name,c_desc)VALUES(#{category.name},#{category.desc})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(Category category);

	/**
	 * @param id
	 */
	@Delete("DELETE FROM t_category WHERE c_id=#{id}")
	void delete(long id);

	/**
	 * @param id
	 * @param name
	 * @param desc
	 */
	@Update("UPDATE t_category SET c_name = #{name},c_desc=#{desc} WHERE c_id=#{id}")
	void update(@Param("id")long id, @Param("name")String name, @Param("desc")String desc);
	
	

}
