/*
 * 模块编号
 * 功能描述  
 * 文件名       UserServiceImpl.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午1:56:55
 */
package com.zy.blog.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.zy.blog.common.ResponseEnum;
import com.zy.blog.dbentity.User;
import com.zy.blog.entity.UserInfo;
import com.zy.blog.exception.MyException;
import com.zy.blog.mapper.UserMapper;
import com.zy.blog.service.IMailService;
import com.zy.blog.service.IUserService;
import com.zy.blog.util.RandomUtil;
import com.zy.blog.util.RedisUtil;
import com.zy.blog.util.RegexUtil;
import com.zy.blog.util.SecuritySHA1Utils;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IMailService mailService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public String registration(String username, String nickName,String email, String password) {
		// 验证邮箱合法性
		if (!RegexUtil.emailMatch(email)) {
			throw new MyException(ResponseEnum.FAILURE.getCode(), "邮箱不合法！");
		}

		User user = userMapper.findByIdentity(username);
		if (user != null) {
			throw new MyException(ResponseEnum.USERNAME_HAS_EXIST);
		}
		User findByEmail = userMapper.findByEmail(email);
		if(findByEmail != null) {
			throw new MyException(ResponseEnum.FAILURE.getCode(),"邮箱已经被使用了！");
		}

		// 添加用户
		String userUid = UUID.randomUUID().toString().replaceAll("-", "");
		userMapper.addUser(userUid, username,nickName, email, password);

		// 发送激活邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("周泰斗个人博客邮箱验证");
		String code = RandomUtil.getRandomCode();
		message.setText(" 您的登录用户名为：" + username + "\n 您的账户激活验证码为：<b>" + code+"</b>" +
				"\n 您也可以点击下面链接进行邮箱验证:\n https://www.celess.cn/emailVerify?email=" + email + "&userUid=" +
				userUid + "&code=" + code + "\n该链接两日内有效,若失效了,请登录后台进行重新激活。");
		mailService.send(message);
		
		//将验证code存入redis，默认过期时间为2天，如果2天没有进行操作，则key失效
		redisUtil.setEx(userUid+ "regist", code, 2, TimeUnit.DAYS);
		return userUid;
	}

	@Override
	public UserInfo login(String identify, String password,boolean remeber) {
		//查询用户
		User user = userMapper.findByIdentity(identify);
		if(user == null) {
			throw new MyException(ResponseEnum.FAILURE.getCode(),"用户不存在！");
		}else {
			try {
				//验证成功
				if(SecuritySHA1Utils.shaEncode(password) == user.getPwd()) {
					//修改登录时间
					userMapper.updateLoginTime(user.getUserUid(), new Date());
					
					UserInfo info = new UserInfo();
					info.setDisplayName(user.getDisplayName());
					info.setUserUid(user.getUserUid());
					String token = UUID.randomUUID().toString().replaceAll("-", "");
					//将用户token保存到redis中
					if(remeber) {
						redisUtil.setEx(user.getUserUid(), token, 15, TimeUnit.DAYS);
					}else {
						redisUtil.setEx(user.getUserUid(), token, 30, TimeUnit.MINUTES);
					}
					info.setToken(token);
					return info;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new MyException(ResponseEnum.ERROR.getCode(),"系统错误！");
			}
		}
		throw new MyException(ResponseEnum.ERROR.getCode(),"登录失败！");
	}

	@Override
	public void valiateRegistCode(String userUid,String code) {
		String reg_code = redisUtil.get(userUid + "regist");
		if(reg_code == null) {
			throw new MyException(ResponseEnum.FAILURE.getCode(),"激活验证码过期，请重新获取激活验证码！");
		}
		if(!Objects.equals(reg_code, code)) {
			throw new MyException(ResponseEnum.FAILURE.getCode(),"激活验证码错误，请输入正确激活验证码！");
		}
		
		User user = userMapper.findByUserUid(userUid);
		if (user == null) {
			throw new MyException(ResponseEnum.FAILURE);
		}
		
		if(user.getStatus() == 1) {
			throw new MyException(ResponseEnum.FAILURE.getCode(), "用户已激活");
		}
		
		//激活用户
		userMapper.activateUser(userUid);
		
		//删除
		redisUtil.delete(userUid);
	}
}
