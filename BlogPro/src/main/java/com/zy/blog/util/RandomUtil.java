/**
 * 功能描述 
 * 文件名 RandomUtil.java
 * 作者 周泰斗
 * 编写日期 2020年5月10日下午3:28:33
 **/
package com.zy.blog.util;

import java.util.Random;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public class RandomUtil {
	/**
	 * 获取6位数随机验证码
	 * @return
	 */
	public static String getRandomCode() {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int r = random.nextInt(10); //每次随机出一个数字（0-9）
			code = code + r;  //把每次随机出的数字拼在一起
		}
		return code;
	}
}
