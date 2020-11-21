/**
 * 功能描述 
 * 文件名 UserController.java
 * 作者 周泰斗
 * 编写日期 2020年5月10日下午6:10:09
 **/
package com.zy.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zy.blog.annotation.DisableAuth;
import com.zy.blog.common.Response;
import com.zy.blog.controller.param.LoginReq;
import com.zy.blog.controller.param.RegistrationReq;
import com.zy.blog.entity.UserInfo;
import com.zy.blog.service.IUserService;
import com.zy.blog.util.ResponseUtil;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@RestController
@RequestMapping("/session")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 注册用户
	 * @param req
	 * @return
	 */
	@DisableAuth
	@PostMapping(value="/registration",consumes = "application/json")
	public Response registration(@RequestBody @Valid RegistrationReq req) {
		String userUid = userService.registration(req.getUsername(),req.getNickName(), req.getEmail(), req.getPassword());
		return ResponseUtil.success(userUid);
	}
	
	/**
	 * 激活用户
	 * @param email 用户邮箱
	 * @param code 激活码
	 * @return
	 */
	@DisableAuth
	@PostMapping(value="/valiate")
	public Response emailVerify(@RequestParam("code") String code,
            @RequestParam("email") String email) {
		userService.valiateRegistCode(email, code);
		return ResponseUtil.success(true);
	}
	
	/**
	 * 登录
	 * @param req
	 * @return
	 */
	@DisableAuth
	@PostMapping(value="/login",consumes = "application/json")
	public Response login(@RequestBody @Valid LoginReq req) {
		UserInfo userInfo = userService.login(req.getIdentify(), req.getPassword(), req.getRemeber());
		return ResponseUtil.success(userInfo);
	}
	
	
}
