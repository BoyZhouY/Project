/**
 * 功能描述 
 * 文件名 TagMapper.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午7:01:37
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

import com.zy.blog.dbentity.ArticleTag;
import com.zy.blog.dbentity.Tag;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface TagMapper {

	/**
	 * 根据标签名称查询标签
	 * @param tagName
	 * @return
	 */
	@Select("SELECT * FROM t_tag WHERE tag_name=#{tagName}")
	@Results(id="tagMap",value= {
			@Result(property = "id",column = "tag_id"),
			@Result(property = "tagName",column = "tag_name")
	})
	Tag queryByName(@Param("tagName")String tagName);

	/**
	 * 插入一条标签
	 * @param tag
	 */
	@Insert("INSERT INTO t_tag (tag_name)VALUES(#{tag.t_tag})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(@Param("tag")Tag tag);

	/**
	 * 插入一条文章标签关联记录
	 * @param articleTag
	 */
	@Insert("<script>"
			+ "INSERT IGNORE INTO t_artcle_tag VALUES"
			+ "<foreach collection='articleTags' item='item' index='index' separator=','>"
			+ "(#{item.artcleId},#{item.tagId})"
			+ "</foreach>"
			+ "</script>")
	void insertArticleTag(@Param("articleTags")List<ArticleTag> articleTags);
	
	@Delete("DELETE FROM t_artcle_tag WHERE artcle_id=#{artcleId}")
	void deleteArticleTagByArtcleId(long artcleId);

	/**
	 * @return
	 */
	@Select("SELECT * FROM t_tag WHERE tag_name=#{tagName}")
	@ResultMap(value = "tagMap")
	List<Tag> query();
	
	@Select("SELECT A.* FROM t_tag AS A JOIN ON t_artcle_tag as B ON A.tag_id = B.tag_id WHERE B.artcle_id=#{articleId}")
	@ResultMap(value = "tagMap")
	List<Tag> getByArticleId(long articleId);

}
