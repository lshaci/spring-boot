package com.lshaci.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单对象属性拷贝工具
 */
public abstract class CopyObjectUtil {
	
	/**
	 * 简单对象属性拷贝
	 * 
	 * @param source	源对象
	 * @param target	目标对象类型
	 * @param rename	需要重命名的字段(key: 源对象字段名, value: 目标对象字段名)
	 * @param extra		扩展参数(key: 目标对象字段名, value: 对应的值)
	 * @param skips		不需要从源对象获取值的字段
	 * @return 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> T copy(Object source, Class<T> target, Map<String, String> rename, Map<String, Object> extra, List<String> skips) 
			throws InstantiationException, IllegalAccessException {
		validateParameter(source, target);
		
		if (extra == null) extra = new HashMap<>();
		if (rename == null) rename = new HashMap<>();
		if (skips == null) skips = new ArrayList<>();
		
		obj2Map(source, extra, rename, skips);
		return setValue(target, extra);
	}
	
	/**
	 * 创建目标对象并设置值
	 * 
	 * @param target	目标对象
	 * @param extra		字段值
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static <T> T setValue(Class<T> target, Map<String, Object> extra) 
			throws InstantiationException, IllegalAccessException {
		T result = target.newInstance();
		Field[] fields = target.getDeclaredFields();
		
		if (fields == null || fields.length == 0) {
			throw new RuntimeException("Target NO Fields!");
		}
		
		for (Field field : fields) {
			String fieldName = field.getName();
			Object value = extra.get(fieldName);
			if (value != null) {
				try {
					field.setAccessible(true);
					field.set(result, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return result;
	}

	/**
	 * 验证参数
	 * 
	 * @param source	源对象
	 * @param target	目标对象类型
	 */
	private static <T> void validateParameter(Object source, Class<T> target) {
		if (source == null) {
			throw new RuntimeException("Source is null!");
		}
		
		if (target == null) {
			throw new RuntimeException("Target is null!");
		}
	}

	/**
	 * 将源对象中的数据转换为map, 一起放入到扩展参数中
	 * 
	 * @param source	源对象
	 * @param extra		扩展参数
	 * @param rename	需要重命名的字段
	 * @param skips		不需要从源对象获取值的字段
	 */
	private static void obj2Map(Object source, Map<String, Object> extra, Map<String, String> rename, List<String> skips) {
		Class<? extends Object> clazz = source.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		if (fields == null || fields.length == 0) {
			throw new RuntimeException("Source NO Fields!");
		}
		
		for (Field field : fields) {
			String fieldName = field.getName();
			if ("serialVersionUID".equals(fieldName) || skips.contains(fieldName)) continue;
			try {
				String temp = rename.get(fieldName);
				if (temp != null && !temp.trim().isEmpty()) {
					fieldName = temp;
				}
				field.setAccessible(true);
				Object value = field.get(source);
				if (value != null) {
					extra.put(fieldName, value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

}
