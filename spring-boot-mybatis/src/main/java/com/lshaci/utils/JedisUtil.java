package com.lshaci.utils;

import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;

/**
 * 获取redis客户端jedis
 * 
 * @author lshaci
 *
 */
public class JedisUtil {
	
	private JedisUtil() {}
	
	private static class JedisUtilHolder {
//		private static Jedis jedis = SpringUtils.getBean(JedisConnectionFactory.class).getShardInfo().createResource();
		private static Jedis getJedis () {
			JedisConnection jedisConnection = (JedisConnection) SpringUtils.getBean(JedisConnectionFactory.class).getConnection();
			return jedisConnection.getNativeConnection();
		}
	}

	/**
	 * 获取redis客户端（单例）
	 * 
	 * @return
	 */
	public static Jedis getJedis() {
		return JedisUtilHolder.getJedis();
	}
}
