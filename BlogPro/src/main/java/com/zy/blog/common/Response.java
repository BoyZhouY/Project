/*
 * 模块编号
 * 功能描述  
 * 文件名       Response.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午5:10:17
 */
package com.zy.blog.common;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
@Getter@Setter
public class Response {
	private int code;//状态码
    private String msg;//信息
    private Object result;//结果
    private long date;//时间
    
    public Response() {
    }

    public Response(int code, String msg, Object result, long date) {
        this.code = code;
        this.msg = msg;
        this.result = result;
        this.date = date;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(this);
        return jsonObject.toString();
    }
}
