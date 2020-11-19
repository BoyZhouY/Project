/*
 * 模块编号
 * 功能描述
 * 文件名 IUserService.java
 * 作者 周泰斗
 * 编写日期 2020年4月30日下午1:56:36
 */
package com.zy.blog.service;

import com.zy.blog.entity.UserInfo;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
public interface IUserService {

	/**
	 * 用户注册
	 * 
	 * @param username
	 *            用户名
	 * @param email
	 *            用户邮箱
	 * @param password
	 *            用户密码
	 */
	public String registration(String username,String nickName, String email, String password);
	
	/**
	 * 用户登录
	 * @param identify
	 * @param password
	 * @return
	 */
	public UserInfo login(String identify,String password,boolean remeber);
	
	/**
	 * 验证注册校验码
	 * @param code
	 */
	public void valiateRegistCode(String userUid,String code);
}
