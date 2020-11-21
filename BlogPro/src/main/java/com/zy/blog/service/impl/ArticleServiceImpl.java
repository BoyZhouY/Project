/**
 * 功能描述 
 * 文件名 ArticleServiceImpl.java
 * 作者 周泰斗
 * 编写日期 2020年6月16日下午9:27:20
 **/
package com.zy.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zy.blog.common.ResponseEnum;
import com.zy.blog.controller.param.ArticleReq;
import com.zy.blog.dbentity.Article;
import com.zy.blog.dbentity.ArticleTag;
import com.zy.blog.dbentity.Category;
import com.zy.blog.dbentity.Tag;
import com.zy.blog.dbentity.User;
import com.zy.blog.entity.ArticleDO;
import com.zy.blog.entity.ArticleModel;
import com.zy.blog.exception.MyException;
import com.zy.blog.mapper.ArticleMapper;
import com.zy.blog.mapper.CategoryMapper;
import com.zy.blog.mapper.TagMapper;
import com.zy.blog.mapper.UserMapper;
import com.zy.blog.service.IArticleService;
import com.zy.blog.util.RedisUtil;
import com.zy.blog.util.RegexUtil;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private TagMapper tagMapper;

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private UserMapper userMapper;

	@Value(value = "${pageSize}")
	private int pageSize;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArticleModel addArtcle(String userUid, String title, String mdContent, String tags, String url, String categorys,
			boolean open, boolean original) {
		ArticleModel model = new ArticleModel();
		// 转载,判断链接
		if (!original) {
			if (StringUtils.isEmpty(url)) {
				throw new MyException(ResponseEnum.PARAM_ERROR);
			} else if (!RegexUtil.urlMatch(url)) {
				throw new MyException(ResponseEnum.PARAM_ERROR);
			}
		}

		//查询分类在不在
		Category category = categoryMapper.queryByName(categorys);
		if (category == null) {
			throw new MyException(ResponseEnum.NORECORD_ERROR);
		}

		//开始构建文章
		Article info = new Article();
		info.setAuthorId(userUid);
		info.setTitle(title);
		info.setMdContent(mdContent);
		info.setOpen(open ? 1 : 0);
		info.setOriginal(original ? 1:0);
		info.setPublishDate(new Date());
		info.setCategoryId(category.getId());

		// 是否需要更新上一篇文章
		boolean isUpdatePreArticle = true;

		// 寻找上一篇文章Id
		Article preArticle = articleMapper.getLastArticle();
		if (preArticle == null) {//上篇文章是空的，则不设置
			isUpdatePreArticle = false;
		} else {
			// 设置
			info.setPreId(preArticle.getId());
			model.setPreArticleTitle(preArticle.getTitle());
		}

		// 将tags分割，并查询是否存在，不存在则创建
		List<ArticleTag> articleTag = createArticleTag(model,tags);

		// 生成概要信息，概要信息为文章开头256个字符
		String summary = "概要信息";
		info.setSummary(summary);

		// 保存article
		articleMapper.insert(info);
		if (isUpdatePreArticle) {
			// 更新上一篇文章的“下一篇文章ID”
			articleMapper.updateNextArticleId(preArticle.getId(), info.getId());
		}

		if (articleTag.size() > 0) {
			// 添加文章的对应标签信息
			tagMapper.insertArticleTag(articleTag);
		}
		
		fullTransform(info,model);
		return model;
	}

	/**
	 * @param info 
	 * @return
	 */
	private void fullTransform(Article info,ArticleModel model) {
		model.setId(info.getId());
		model.setTitle(info.getTitle());
		model.setSummary(info.getSummary());
		model.setMdContent(info.getMdContent());
		model.setOriginal(info.getOriginal() == 1? true :false);
		model.setUrl(info.getUrl());
		model.setPublishDate(info.getPublishDate());
		model.setUpdateDate(info.getUpdateDate());
		model.setAuthorId(info.getAuthorId());
		User user = userMapper.findByUserUid(info.getAuthorId());
		model.setAuthorName(user.getDisplayName());
		String categoryName = categoryMapper.queryById(info.getCategoryId()).getName();
		model.setCategory(categoryName);
		model.setPreArticleId(info.getPreId());
		model.setNextArticleId(info.getNextId());
		model.setReadingNumber(info.getReadNumber());
		model.setOpen(info.getOpen() == 1 ? true : false);
	}
	
	/**
	 * 创建文章标签
	 * 
	 * @param tags
	 * @return
	 */
	private List<ArticleTag> createArticleTag(ArticleModel model,String tags) {
		List<ArticleTag> articleTag = new ArrayList<ArticleTag>();
		if (!StringUtils.isEmpty(tags)) {
			//将所有的空格全部替换为空
			tags = tags.replaceAll("\\s+", "");
			//用,分割标签
			String[] tagNames = tags.split(",");
			for (String tagName : tagNames) {
				Tag tag = tagMapper.queryByName(tagName);
				if (tag == null) {
					tag = new Tag();
					tag.setTagName(tagName);
					tagMapper.insert(tag);
				}
				
				// 保存标签的Id
				ArticleTag aTag = new ArticleTag();
				aTag.setTagId(tag.getId());
				aTag.setArtcleId(model.getId());
				articleTag.add(aTag);
			}
			model.setTags(tagNames);
		}
		return articleTag;
	}

	/**
	 * 根据不同模式查询最新数据
	 */
	@Override
	public List<ArticleDO> queryArtcle(int mode,int pageIndex) {
		//这里使用静态工厂来适应不同文章的统计
		// 最新，评论最多，点赞最多
		String queryFields = ArticleQueryFactory.getQueryFieldByMode(mode);
		if(StringUtils.isEmpty(queryFields)) {
			throw new MyException(ResponseEnum.PARAM_ERROR);
		}
		if(pageIndex <= 0) {
			pageIndex = 1;
		}
		pageIndex -= 1;
		List<ArticleDO> articleInfos = articleMapper.query(queryFields,pageIndex * pageSize,pageSize);
		return articleInfos;
	}
	
	private static class ArticleQueryFactory {
		
		private static Map<Integer,String> maps = new HashMap<>();
		
		static {
			maps.put(1, "publish_date");//发布时间
			maps.put(2, "reading_number");//阅读数
			maps.put(3, "like_number");//点赞数
		}
		
		public static String getQueryFieldByMode(int mode) {
			return maps.get(mode);
		}
	}

	@Override
	public ArticleModel queryArtcleById(long articleId,String userUid) {
		ArticleModel model = new ArticleModel();
		//是否可见
		Article article = articleMapper.getArticleById(articleId);
		//根据用户uid查询用户登录信息
		String token = redisUtil.get("userUid");
		//查询用户信息
		User user = userMapper.findByUserUid(userUid);
		
		if(article.getOpen() == 1 || article.getOpen() == 0 && user.getIsAdmin() == 1 && token != null) {
			if (article.getPreId() != -1) {
				Article preArticle = articleMapper.getArticleById(article.getPreId());
				model.setPreArticleTitle(preArticle.getTitle());
			}
			if (article.getNextId() != -1) {
				Article nextArticle = articleMapper.getArticleById(article.getNextId());
				model.setNextArticleTitle(nextArticle.getTitle());
			}
			
			List<Tag> tags = tagMapper.getByArticleId(articleId);
			List<String> tagNames = tags.stream().map(x->x.getTagName()).collect(Collectors.toList());
			model.setTags(tagNames.toArray(new String[tagNames.size()]));
			fullTransform(article,model);
			return model;
		}else {
			throw new MyException(ResponseEnum.AUTH_ERROR);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArticleModel updateArtcle(long articleId,ArticleReq req) {
		ArticleModel model = new ArticleModel();
		
		//查询分类在不在
		Category category = categoryMapper.queryByName(req.getCategory());
		if (category == null) {
			throw new MyException(ResponseEnum.NORECORD_ERROR);
		}
		
		Article info = new Article();
		info.setId(articleId);
		info.setAuthorId(req.getUserUid());
		info.setTitle(req.getTitle());
		info.setMdContent(req.getMdContent());
		info.setOpen(req.getOpen() ? 1 : 0);
		info.setOriginal(req.getOriginal() ? 1:0);
		info.setUpdateDate(new Date());
		
		//检查当前更新的文章是否存在
		Article old_article = articleMapper.getArticleById(articleId);
		if(old_article == null) {
			throw new MyException(ResponseEnum.NORECORD_ERROR);
		}
		
		// 将tags分割，并查询是否存在，不存在则创建
		List<ArticleTag> articleTag = createArticleTag(model,req.getTags());
		
		// 生成概要信息，概要信息为文章开头256个字符
		String summary = "概要信息";
		info.setSummary(summary);

		// 保存article
		articleMapper.update(info);
		if (articleTag.size() > 0) {
			// 添加文章的对应标签信息
			tagMapper.insertArticleTag(articleTag);
		}
		
		fullTransform(info,model);
		return model;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteArtcle(long articleId) {
		Article old_article = articleMapper.getArticleById(articleId);
		if(old_article == null) {
			throw new MyException(ResponseEnum.NORECORD_ERROR);
		}
		
		//获取上一篇和下一篇
		Article preArticle = articleMapper.getArticleById(old_article.getPreId());
        Article nextArticle = articleMapper.getArticleById(old_article.getNextId());
        
        //删除的文章处于中间位置
        if (nextArticle != null && preArticle != null) {

            //修改上一篇文章的“下一篇文章”y
            articleMapper.updateNextArticleId(old_article.getPreId(), old_article.getNextId());

            //修改下一篇文章的 “上一篇文章”
            articleMapper.updatePreArticleId(old_article.getNextId(), old_article.getPreId());
        }
        if (preArticle == null && nextArticle != null) {
            //删除的是第一篇文章
            articleMapper.updatePreArticleId(nextArticle.getId(), -1);
        }
        if (nextArticle == null && preArticle != null) {
            //删除的是最后一篇文章
            articleMapper.updateNextArticleId(preArticle.getId(), -1);
        }
        
        //删除和文章相关的评论--
        
        //删除和文章相关的artcle_tag记录
        tagMapper.deleteArticleTagByArtcleId(articleId);
        
		//删除文章
        articleMapper.deleteById(articleId);
	}
}
