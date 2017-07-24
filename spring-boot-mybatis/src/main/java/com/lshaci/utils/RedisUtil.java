package com.lshaci.utils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
/**
 * redis工具类
 * @author lshaci
 * @param <T>
 *
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil<T> {

	@SuppressWarnings("rawtypes")
//	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
//		this.template.setHashKeySerializer(template.getKeySerializer());DoctorTypePojo
//		this.template.setKeySerializer(template.getStringSerializer());
//		this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//		this.redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
	}

	/**
	 * 批量删除对应键的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 删除对应键的value
	 * 
	 * @param key 键
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key 键
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key 键
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * @param key 键
	 * @param value 值
	 * @param expireTime 有效期 
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.MICROSECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
