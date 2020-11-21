/**
 * 功能描述 
 * 文件名 QueryArtcleRes.java
 * 作者 周泰斗
 * 编写日期 2020年11月21日下午5:16:06
 **/
package com.zy.blog.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Getter@Setter
public class QueryArtcleRes {
	private List<ArticleDO> artcles;
	private int count;
}
