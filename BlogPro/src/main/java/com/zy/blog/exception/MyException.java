/*
 * 模块编号
 * 功能描述  
 * 文件名       MyException.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午2:20:04
 */
package com.zy.blog.exception;

import com.zy.blog.common.ResponseEnum;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
public class MyException extends RuntimeException{

	private int code;
	
	private static final long serialVersionUID = 4539478356994888574L;
	
	public MyException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	public MyException(ResponseEnum param) {
		super(param.getMsg());
		this.code = param.getCode();
	}
	
	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
