/**
 * 功能描述 
 * 文件名 IArtcleService.java
 * 作者 周泰斗
 * 编写日期 2020年6月16日下午9:24:11
 **/
package com.zy.blog.service;

import java.util.List;

import com.zy.blog.controller.param.ArticleReq;
import com.zy.blog.entity.ArticleDO;
import com.zy.blog.entity.ArticleModel;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface IArticleService {

	/**
	 * 创建文章
	 * @param userUid 作者Id
	 * @param title 文章标题
	 * @param mdContent 文章内容
	 * @param tags 文章标签
	 * @param url 转载url
	 * @param categoryId 分类Id
	 * @param open 是否可见（发布）
	 * @param original 是否原创
	 * @return 
	 */
	ArticleModel addArtcle(String userUid, String title, String mdContent, String tags, String url, String categorys,
			boolean open, boolean original);

	/**
	 * 根据查询模式查询文章信息
	 * @param mode 
	 * @param pageIndex 
	 * 
	 */
	List<ArticleDO> queryArtcle(int mode, int pageIndex);

	/**
	 * @param articleId
	 * @param userUid 
	 * @return
	 */
	ArticleModel queryArtcleById(long articleId, String userUid);

	/**
	 * @param articleId 
	 * @param req
	 * @return
	 */
	ArticleModel updateArtcle(long articleId, ArticleReq req);

	/**
	 * @param articleId
	 * @return
	 */
	void deleteArtcle(long articleId);

}
