package com.lshaci.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

/**
 * redis相关操作工具
 * 
 * @author lshaci
 *
 */
public class RedisUtils {
	
	/**
	 * 操作redis客户端
	 */
	private static Jedis jedis = JedisUtil.getJedis();
	
	/**
	 * 把对象保存到redis中
	 * 
	 * @param key		保存对象的key
	 * @param value		保存的对象
	 */
	public static void set(String key, Object value) {
		jedis.set(JSON.toJSONString(key), JSON.toJSONString(value));
	}
	
	/**
	 * 根据key从redis中取出指定对象
	 * 
	 * @param key		对象的key
	 * @param clazz		对象的类型
	 * @return			key在redis中对应的对象的实例
	 */
	public static <T> T get(String key, Class<T> clazz) {
		String jsonString = jedis.get(JSON.toJSONString(key));
		return JSON.parseObject(jsonString, clazz);
	}
	
	/**
	 * 根据key删除redis中缓存的对象
	 * 
	 * @param key		需要删除对象的key
	 */
	public static void del(String key) {
		jedis.del(JSON.toJSONString(key));
	}
	
	/**
	 * 把对象保存到redis的hash表中
	 * 
	 * @param key		hash的key
	 * @param field		对象对应的字段
	 * @param value		保存的对象
	 */
	public static void hSet(String key, String field, Object value) {
		jedis.hset(JSON.toJSONString(key), JSON.toJSONString(field), JSON.toJSONString(value));
	}
	
	/**
	 * 根据key从redis的hash表中取出指定对象
	 * 
	 * @param key		hash的key
	 * @param field		对象对应的字段
	 * @param clazz		对象的类型
	 * @return			key在redis的hash表中对应field的对象实例
	 */
	public static <T> T hGet(String key, String field, Class<T> clazz) {
		String jsonString = jedis.hget(JSON.toJSONString(key), JSON.toJSONString(field));
		return JSON.parseObject(jsonString, clazz);
	}
	
	/**
	 * 获取模型在redis的hash表中所有的实例
	 * 
	 * @param key		hash的key
	 * @param clazz		对象的类型
	 * @return			key在redis的hash表中对应对象的所有实例集合
	 */
	public static <T> List<T> hGetAll(String key,  Class<T> clazz) {
		Map<String, String> all = jedis.hgetAll(JSON.toJSONString(key));
		Collection<String> values = all.values();
		List<T> ts  = new ArrayList<>();
		for (String jsonString : values) {
			ts.add(JSON.parseObject(jsonString, clazz));
		}
		return ts;
	}
	
	/**
	 * 通过字段在redis指定key的hash表中删除一个对象
	 * 
	 * @param key		hash的key
	 * @param field		对象对应的字段
	 * @return			删除成功返回ture，失败则返回false
	 */
	public static boolean hDel(String key, String field) {
		return jedis.hdel(JSON.toJSONString(key), JSON.toJSONString(field)) == 1;
	}

}
