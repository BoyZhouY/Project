/*
 * 模块编号
 * 功能描述  
 * 文件名       RegexUtil.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午2:08:16
 */
package com.zy.blog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述
 *
 * @version 1.0.0
 * @author 周泰斗
 */
public class RegexUtil {
	/**
     * 邮箱验证
     *
     * @param email
     * @return
     */
    public static boolean emailMatch(String email) {
        if (email == null || email.replaceAll(" ", "").isEmpty()) {
            return false;
        }
        //邮箱正则
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return match(email, pattern);
    }
    
    /**
     * 网址匹配
     *
     * @param url
     * @return
     */
    public static boolean urlMatch(String url) {
        if (url == null || url.replaceAll(" ", "").isEmpty()) {
            return false;
        }
        //正则 
        String pattern = "^(http://|https://|)([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        return match(url, pattern);
    }
    
    private static boolean match(String str, String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }
}
