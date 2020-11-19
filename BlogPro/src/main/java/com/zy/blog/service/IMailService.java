/**
 * 功能描述 
 * 文件名 IMailService.java
 * 作者 周泰斗
 * 编写日期 2020年4月30日下午9:42:29
 **/
package com.zy.blog.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public interface IMailService {

	/**
	 * 发送激活邮件
	 * @param message
	 */
	boolean asyncSend(SimpleMailMessage message);
	
	/**
	 * 发送激活邮件
	 * @param message
	 */
	boolean send(SimpleMailMessage message);

}
