/**
 * 功能描述 
 * 文件名 ArticleMapper.java
 * 作者 周泰斗
 * 编写日期 2020年6月17日下午10:37:47
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

import com.zy.blog.dbentity.Article;
import com.zy.blog.entity.ArticleDO;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface ArticleMapper {

	/** 
	 * 获取最新的一篇文章
	 * @return
	 */
	@Select("SELECT * FROM t_article order by id limit 0,1")
	@Results(id="ArticleMap",value = {
		@Result(property = "id",column = "id"),
		@Result(property = "title",column = "title"),
		@Result(property = "summary",column = "summary"),
		@Result(property = "mdContent",column = "md_content"),
		@Result(property = "categoryId",column = "category_id"),
		@Result(property = "url",column = "url"),
		@Result(property = "authorId",column = "author_id"),
		@Result(property = "open",column = "open"),
		@Result(property = "original",column = "original"),
		@Result(property = "nextId",column = "next_id"),
		@Result(property = "preId",column = "pre_id"),
		@Result(property = "readNumber",column = "reading_number"),
		@Result(property = "publishDate",column = "publish_date"),
		@Result(property = "updateDate",column = "update_date"),
		@Result(property = "like",column = "like_number")
	})
	Article getLastArticle();

	/**
	 * 添加一篇文章
	 * @param info
	 */
	@Insert("<script>"
			+ "INSERT INTO t_article ("
			+ "title,"
			+ "summary,"
			+ "md_content,"
			+ "url,"
			+ "author_id,"
			+ "open,"
			+ "original,"
			+ "pre_id,"
			+ "next_id,"
			+ "publish_date"
			+ ")VALUES("
			+ "#{title},"
			+ "#{summary},"
			+ "#{mdContent},"
			+ "#{url},"
			+ "#{authorId},"
			+ "#{open},"
			+ "#{original},"
			+ "#{preId},"
			+ "#{nextId},"
			+ "#{publishDate})"
			+ "</script>")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(Article info);

	/**
	 * 更新文章的下一篇文章的Id
	 * @param articleId 当前文章Id
	 * @param nextArticleId 下篇文章Id
	 */
	@Update("UPDATE t_article SET next_id=#{nextArticleId} WHERE id=#{articleId}")
	void updateNextArticleId(@Param("articleId")long articleId, @Param("nextArticleId")long nextArticleId);
	
	/**
	 * 更新文章的上一篇文章的Id
	 * @param articleId 当前文章Id
	 * @param nextArticleId 下篇文章Id
	 */
	@Update("UPDATE t_article SET pre_id=#{preArticleId} WHERE id=#{articleId}")
	void updatePreArticleId(@Param("articleId")long articleId, @Param("nextArticleId")long preArticleId);
	
	/**
	 * 根据不同field查询指定个数的文章
	 * @param field 指定字段
	 * @param pageIndex 页码
	 * @param pageSize 页数
	 * @return
	 */
	@Select("SELECT A.id,"
			+ "C.display_name AS authorName,"
			+ "A.title,"
			+ "A.summary,"
			+ "A.reading_number,"
			+ "A.like_number,"
			+ "B.c_name AS categoryName,"
			+ "A.publish_date,"
			+ "A.original "
			+ "FROM t_article AS A "
			+ "JOIN t_category AS B "
			+ "ON A.category_id = B.c_id "
			+ "JOIN t_user AS C "
			+ "ON C.u_uid = A.author_id WHERE A.open = 1"
			+ "ORDER BY #{field} DESC LIMIT #{pageIndex},#{pageSize} ")
	@Results(id="ArticleDOMap",value = {
			@Result(property = "id",column = "id"),
			@Result(property = "title",column = "title"),
			@Result(property = "summary",column = "summary"),
			@Result(property = "categoryName",column = "categoryName"),
			@Result(property = "authorName",column = "authorName"),
			@Result(property = "original",column = "original"),
			@Result(property = "publishDate",column = "publish_date"),
			@Result(property = "readNum",column = "reading_number"),
			@Result(property = "like",column = "like_number"),
		})
	List<ArticleDO> query(@Param("field")String field,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);

	/**
	 * @param articleId
	 */
	@Select("SELECT * FROM t_article WHERE id=#{articleId}")
	@ResultMap(value = "ArticleMap")
	Article getArticleById(long articleId);

	/**
	 * @param info
	 */
	@Update("UPDATE t_article SET title=#{info.title},"
			+ "summary=#{info.summary},"
			+ "md_content=#{info.mdContent},"
			+ "category_id=#{info.categoryId},"
			+ "url=#{info.url},"
			+ "author_id=#{info.authorId},"
			+ "open=#{info.open},"
			+ "original=#{info.original},"
			+ "update_date=#{info.updateDate} WHERE id=#{info.id}")
	void update(Article info);

	/**
	 * 根据文章Id删除一篇文章
	 * @param articleId
	 */
	@Delete("DELETE FROM t_article WHERE id=#{articleId}")
	void deleteById(long articleId);
}
