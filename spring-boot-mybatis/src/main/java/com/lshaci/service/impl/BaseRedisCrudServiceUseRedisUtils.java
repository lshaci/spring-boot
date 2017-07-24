package com.lshaci.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.lshaci.domain.IDEntity;
import com.lshaci.utils.RedisUtils;

/**
 * 封装redis 缓存服务器服务接口
 */
public abstract class BaseRedisCrudServiceUseRedisUtils<T extends IDEntity<ID>, ID extends Serializable> 
		extends BaseCrudServiceImpl<T, ID> {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseRedisCrudServiceUseRedisUtils.class);

	/**
	 * 存放hash表的key值
	 */
	private String key;
	
	/**
	 * 模型对象的class
	 */
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseRedisCrudServiceUseRedisUtils() {
		this.clazz = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.key = clazz.getName();
		logger.debug("子类模型类名：" + key);
	}
	
	@Override
	@Transactional
	public final int saveOrUpdate(T t) {
		ID id = t.getId();
		if (id != null) {
			RedisUtils.hDel(key, getField(id));
		}
		int i = super.saveOrUpdate(t);
		if (i == 1) {
			RedisUtils.hSet(key, getField(t), t);
		}
		return i;
	}

	@Override
	@Transactional
	public final int delete(ID id) {
		RedisUtils.hDel(key, getField(id));
		return super.delete(id);
	}

	@Override
	public final T findOne(ID id) {
		T t = RedisUtils.hGet(key, getField(id), clazz);
		if (t == null) {
			t = super.findOne(id);
			if (t != null) {
				RedisUtils.hSet(key, getField(id), t);
			}
		}
		return t;
	}

	@Override
	public final List<T> findAll() {
		List<T> ts = RedisUtils.hGetAll(key, clazz);
		if (ts.size() != super.count()) {
			ts = super.findAll();
			RedisUtils.del(key);
			for (T t : ts) {
				RedisUtils.hSet(key, getField(t), t);
			}
		}
		return ts;
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
		return id.toString();
	}
}
