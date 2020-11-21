/**
 * 功能描述 
 * 文件名 ArticleModel.java
 * 作者 周泰斗
 * 编写日期 2020年11月13日下午9:19:23
 **/
package com.zy.blog.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Getter@Setter
public class ArticleModel {
	private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * Markdown正文
     */
    private String mdContent;

    /**
     * 文章类型 true(1)为原创  false(0)为转载
     */
    private Boolean original;

    /**
     * 若为转载 则为转载文章的url
     */
    private String url;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 分类
     */
    private String category;

    /**
     * 标签
     */
    private String[] tags;

    /**
     * 作者
     */
    private String authorId;

    /**
     * 作者名字
     */
    private String authorName;

    /**
     * 上一篇文章
     */
    private Long preArticleId;

    /**
     * 下一篇文章
     */
    private Long nextArticleId;

    private String preArticleTitle;

    private String nextArticleTitle;

    /**
     * 阅读数
     */
    private Integer readingNumber;

    /**
     * 文章的状态  true：公开  false:不公开
     */
    private Boolean open;
}
