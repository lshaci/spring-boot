package com.lshaci.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lshaci.domain.IDEntity;
import com.lshaci.utils.JedisUtil;

/**
 * 封装redis 缓存服务器服务接口
 */
public abstract class BaseRedisCrudService<T extends IDEntity<ID>, ID extends Serializable> 
		extends BaseCrudServiceImpl<T, ID> {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseRedisCrudService.class);

	/**
	 * 存放hash表的key值
	 */
	private String key;
	
	/**
	 * 模型对象的class
	 */
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseRedisCrudService() {
		this.clazz = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.key = JSON.toJSONString(clazz.getName());
		logger.debug("子类模型类名（JSON格式）：" + key);
	}

	@Override
	@Transactional
	public final int saveOrUpdate(T t) {
		ID id = t.getId();
		if (id != null) {
			this.hDel(id);
		}
		int i = super.saveOrUpdate(t);
		if (i == 1) {
			this.hSet(t);
		}
		return i;
	}

	@Override
	@Transactional
	public final int delete(ID id) {
		this.hDel(id);
		return super.delete(id);
	}

	@Override
	public final T findOne(ID id) {
		T t = this.hGet(id);
		if (t == null) {
			t = super.findOne(id);
			if (t != null) {
				this.hSet(t);
			}
		}
		return t;
	}

	@Override
	public final List<T> findAll() {
		List<T> ts = this.hGetAll();
		if (ts.size() != super.count()) {
			this.del();
			ts = super.findAll();
			for (T t : ts) {
				this.hSet(t);
			}
		}
		return ts;
	}
	
	/**
	 * 把一个对象存入到redis中
	 * 
	 * @param t		需要存入redis的对象
	 */
	private void hSet(T t) {
		JedisUtil.getJedis().hset(key, getField(t), getValue(t));
	}
	
	/**
	 * 删除该模型在redis中所有的数据
	 */
	private void del() {
		JedisUtil.getJedis().del(object2JSONString(key));
	}

	/**
	 * 根据对象主键在redis中获取对象
	 * 
	 * @param id	对象的主键
	 * @return		获取到的对象
	 */
	private T hGet(ID id) {
		return parseObject(JedisUtil.getJedis().hget(key, getField(id)));
	}
	
	/**
	 * 获取模型在redis中所有的实例
	 * 
	 * @return		获取到的对象集合
	 */
	private List<T> hGetAll() {
		Map<String, String> all = JedisUtil.getJedis().hgetAll(key);
		Collection<String> values = all.values();
		List<T> ts  = new ArrayList<>();
		for (String jsonString : values) {
			ts.add(parseObject(jsonString));
		}
		return ts;
	}
	
	/**
	 * 通过主键在hash表中删除一个对象
	 * 
	 * @param id	对象的主键id
	 */
	private void hDel(ID id) {
		JedisUtil.getJedis().hdel(key, getField(id));
	}
	
	/**
	 * 通过对象获取存储在redis中hash对象的field
	 * 
	 * @param t 存储的对象
	 * @return 对象的field对应的byte[]
	 */
	private String getField(T t) {
		return getField(t.getId());
	}
	
	/**
	 * 通过主键获取存储在redis中hash对象的field
	 * 
	 * @param t 存储的对象
	 * @return 对象的field对应的byte[]
	 */
	private String getField(ID id) {
		return object2JSONString(id.toString());
	}
	
	/**
	 * 把一个对象转换成JSON字符串
	 * 
	 * @param 	需要解析的JSON字符串
	 * @return 	对象对应的JSON字符串
	 */
	private String getValue(T t) {
		return object2JSONString(t);
	}
	
	/**
	 * 把一个对象转换成JSON字符串
	 * 
	 * @param t		需要转换为JSON字符串的对象
	 * @return		对象对应的JSON字符串
	 */
	private String object2JSONString(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	/**
	 * 把一个JSON字符串解析成对象
	 * 
	 * @param jsonString	需要解析的JSON字符串
	 * @return				JSON字符串对应的对象
	 */
	private T parseObject(String jsonString) {
		return JSON.parseObject(jsonString, clazz);
	}

}
