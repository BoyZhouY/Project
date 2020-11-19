/**
 * 功能描述 
 * 文件名 MailServiceImpl.java
 * 作者 周泰斗
 * 编写日期 2020年4月30日下午9:42:46
 **/
package com.zy.blog.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.zy.blog.service.IMailService;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Service
public class MailServiceImpl implements IMailService {

	public static final String FROM = "<shangwaei@qq.com>";
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
    @Async
    public boolean asyncSend(SimpleMailMessage message) {
    	this.send(message);
    	return true;
    }
    
	@Override
	public boolean send(SimpleMailMessage message) {
		String nick = null;
        try {
            nick = javax.mail.internet.MimeUtility.encodeText("周泰斗博客");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        message.setFrom(nick + FROM);
        javaMailSender.send(message);
        return true;
	}

	
	

}
