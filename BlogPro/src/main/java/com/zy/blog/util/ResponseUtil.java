/*
 * 模块编号
 * 功能描述  
 * 文件名       RepsonseUtil.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午1:55:56
 */
package com.zy.blog.util;

import com.zy.blog.common.Response;
import com.zy.blog.common.ResponseEnum;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
public class ResponseUtil {
	/**
     * 成功相应
     *
     * @param result 结果
     * @return
     */
    public static Response success(Object result) {
        Response response = new Response();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(ResponseEnum.SUCCESS.getMsg());
        response.setDate(System.currentTimeMillis());
        response.setResult(result);
        return response;
    }
    
    /**
     * 成功相应
     *
     * @param result 结果
     * @return
     */
    public static Response success() {
        Response response = new Response();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(ResponseEnum.SUCCESS.getMsg());
        response.setDate(System.currentTimeMillis());
        return response;
    }

    /**
     * 失败的响应
     *
     * @param result 结果
     * @return
     */
    public static Response failure(String result) {
        Response response = new Response();
        response.setCode(ResponseEnum.FAILURE.getCode());
        response.setMsg(ResponseEnum.FAILURE.getMsg());
        response.setDate(System.currentTimeMillis());
        response.setResult(result);
        return response;
    }

    /**
     * 其他的响应
     *
     * @param r      枚举常量
     * @param result 结果
     * @return
     */
    public static Response response(ResponseEnum r, String result) {
        Response response = new Response();
        response.setCode(r.getCode());
        response.setMsg(r.getMsg());
        response.setDate(System.currentTimeMillis());
        response.setResult(result);
        return response;
    }
}
