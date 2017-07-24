package com.lshaci.utils;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class CopyObjectUtil {
	
	/**
	 * 将一个对象转换为另一个对象
	 * 
	 * @param source	源对象
	 * @param target	目标对象类型
	 * @param rename	需要重命名的字段
	 * @param extra		额外参数
	 */
	public static <T> void obj2Obj(Object source, Class<T> target, Map<String, String> rename, Map<String, Object> extra) {
		
	}
	
	public void obj2Map(Object obj, Map<String, Object> extra) {
		if (obj == null) {
			throw new RuntimeException("Parameter is null!");
		}
		
		Class<? extends Object> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		if (fields == null || fields.length == 0) {
			throw new RuntimeException("NO Field!");
		}
		
		for (Field field : fields) {
			String fieldName = field.getName();
			if ("serialVersionUID".equals(fieldName)) continue;
			field.setAccessible(true);
			try {
				extra.put(fieldName, field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

}
