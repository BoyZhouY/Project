/**
 * 功能描述 
 * 文件名 RedisUtil.java
 * 作者 周泰斗
 * 编写日期 2020年5月10日下午3:40:38
 **/
package com.zy.blog.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Component
public class RedisUtil {

	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 删除key
	 *
	 * @param key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 批量删除key
	 *
	 * @param keys
	 */
	public void delete(List<String> keys) {
		redisTemplate.delete(keys);
	}

	/**
	 * 是否存在key
	 *
	 * @param key
	 * @return
	 */
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 设置指定 key 的值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key
     * @param value
     * @param timeout
     *            过期时间
     * @param unit
     *            时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *            秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

	/**
	 * 获取指定 key 的值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 将键值对存入指定的hash表中
	 * 
	 * @param hashKey
	 * @param key
	 * @param value
	 */
	public void setHashValue(String hashKey, String key, Object value) {
		redisTemplate.opsForHash().put(hashKey, key, value);
	}

	public Object getHashValue(String hashKey, String key) {
		return redisTemplate.opsForHash().get(hashKey, key);
	}

	public void deleteFromHash(String hashKey, String key) {
		redisTemplate.opsForHash().get(hashKey, key);
	}
}
