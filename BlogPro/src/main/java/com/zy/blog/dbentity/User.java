/**
 * 功能描述 
 * 文件名 User.java
 * 作者 周泰斗
 * 编写日期 2020年4月30日下午7:51:03
 **/
package com.zy.blog.dbentity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Getter@Setter
public class User {
	private String userUid;

    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     * @JsonIgnore 注解用来忽略不想传递给前台的属性或接口
     */
    @JsonIgnore
    private String pwd;

    /**
     * 昵称
     */
    private String displayName;

    /**
     * 用户激活状态
     */
    private byte status = 0;

    /**
     * 头像地址
     */
    private String avatarImgUrl;

    /**
     * 用户描述
     */
    private String desc;

    /**
     * 最近登录时间
     */
    private Date recentlyLandedDate;
    
    private int isAdmin;
}
