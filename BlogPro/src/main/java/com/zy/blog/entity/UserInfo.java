/*
 * 模块编号
 * 功能描述  
 * 文件名       UserInfo.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午2:04:54
 */
package com.zy.blog.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
@Getter@Setter
public class UserInfo {
	/**
	 * 用户Id
	 */
	private String userUid;
	
	/**
	 * 昵称
	 */
	private String displayName;
	
	
	private String token;
}
