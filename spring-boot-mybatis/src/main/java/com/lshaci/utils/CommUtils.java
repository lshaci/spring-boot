package com.lshaci.utils;

import java.util.Collection;

/**
 * 一些公共的工具方法
 * 
 * @author lshaci
 *
 */
public class CommUtils {

	/**
	 * 判断一个集合是否有内容
	 * 
	 * @param collection 需要进行判断的集合
	 * @return
	 */
	public static <E> boolean listIsEmpty(Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * 判断一个字符串是否为空
	 * 
	 * @param str 需要进行判断的字符串
	 * @return
	 */
	public static boolean stringIsEmpty(String str) {
		return str == null || str.trim().length() < 1;
	}
	
	/**
	 * 判断一个字符串是否不为空
	 * 
	 * @param str 需要进行判断的字符串
	 * @return
	 */
	public static boolean stringIsNotEmpty(String str) {
		return !stringIsEmpty(str);
	}
}
