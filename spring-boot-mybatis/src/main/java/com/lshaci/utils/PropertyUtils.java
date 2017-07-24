package com.lshaci.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类 
 * 加载方式分为一次性加载和每次访问都重新加载 
 * 	propsDefault是一次性加载 
 * 	properties是每次动态加载
 */
public class PropertyUtils {

	private static Properties properties = new Properties();

	/**
	 * 根据key从指定的properties配置文件中获取String类型的value
	 * 
	 * @param fileName 	properties配置文件名
	 * @param key		对应的键
	 * @return 			返回key对应的value, 如果不存在则返回null
	 */
	public static String getString(String fileName, String key) {
		String value = null;
		try {
			InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(in);
			value = properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 根据key从指定的properties配置文件中获取int类型的value
	 * 
	 * @param fileName 	properties配置文件名
	 * @param key		对应的键
	 * @return 			返回key对应的value, 如果不存在则返回0
	 */
	public static int getInt(String fileName, String key) {
		String valueStr = getString(fileName, key);
		int value = 0;
		if (CommUtils.stringIsNotEmpty(valueStr)) {
			value = Integer.parseInt(valueStr);
		}
		return value;
	}
	
	/**
	 * 根据key从指定的properties配置文件中获取long类型的value
	 * 
	 * @param fileName 	properties配置文件名
	 * @param key		对应的键
	 * @return 			返回key对应的value, 如果不存在则返回0
	 */
	public static long getLong(String fileName, String key) {
		String valueStr = getString(fileName, key);
		long value = 0;
		if (CommUtils.stringIsNotEmpty(valueStr)) {
			value = Long.parseLong(valueStr);
		}
		return value;
	}
	
	/**
	 * 根据key从指定的properties配置文件中获取boolean类型的value
	 * 
	 * @param fileName 	properties配置文件名
	 * @param key		对应的键
	 * @return 			返回key对应的value, 如果不存在则返回false
	 */
	public static boolean getBoolean(String fileName, String key) {
		String valueStr = getString(fileName, key);
		boolean value = false;
		if (CommUtils.stringIsNotEmpty(valueStr)) {
			value = Boolean.parseBoolean(valueStr);
		}
		return value;
	}

}