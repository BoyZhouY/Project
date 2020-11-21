/**
 * 功能描述 
 * 文件名 ResponseEnum.java
 * 作者 周泰斗
 * 编写日期 2020年4月30日下午7:45:36
 **/
package com.zy.blog.common;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public enum ResponseEnum {
	SUCCESS(0, "成功"),
    FAILURE(-1, "失败"),
    ERROR(-2, "错误"), 
    USERNAME_HAS_EXIST(-3,"用户名已存在"),
	PARAM_ERROR(-4,"参数错误"),
	AUTH_ERROR(-5,"权限错误"),
	NORECORD_ERROR(-6,"数据不存在"),
	USER_NOT_ACTIVE(-7,"用户未激活");

    private int code;
    private String msg;


    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
