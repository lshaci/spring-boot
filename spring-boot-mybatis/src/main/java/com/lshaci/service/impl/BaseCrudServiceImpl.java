package com.lshaci.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lshaci.domain.IDEntity;
import com.lshaci.mapper.BaseCrudMapper;
import com.lshaci.service.BaseCrudService;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class BaseCrudServiceImpl<T extends IDEntity<ID>, ID extends Serializable> implements BaseCrudService<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(BaseCrudServiceImpl.class);

	/**
	 * BaseServiceImpl中公共持久层字段名称
	 */
	private static final String BASE_CRUD_MAPPER = "baseCrudMapper";

	/**
	 * 各个Service中持久层字段名称的后缀
	 */
	private static final String MAPPER_SUFFIX = "Mapper";

	/**
	 * 公共的CRUD持久层对象
	 */
	public BaseCrudMapper<T, ID> baseCrudMapper;

	/**
	 * 构造函数和spring注入之后执行
	 */
	@PostConstruct
	public void init() throws Exception {
		Class<?> clazz = this.getClass();
		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
		// 子类泛型
		String domainName = ((Class<?>) type.getActualTypeArguments()[0]).getSimpleName();
		logger.debug("子类模型类名：" + domainName);
		// 子类持久层字段名称
		String subMapperName = domainName.substring(0, 1).toLowerCase() + domainName.substring(1) + MAPPER_SUFFIX;
		logger.debug("子类持久层字段名称：" + subMapperName);
		// 子类持久层字段
		Field subMapperField = clazz.getDeclaredField(subMapperName);
		subMapperField.setAccessible(true);
		// 子类持久层字段值
		Object subMapper = subMapperField.get(this);
		logger.debug("子类持久层字段：" + subMapper);
		// 父类持久层字段
		Field superMapperField = clazz.getField(BASE_CRUD_MAPPER);
		// 给父类持久层字段设值（值为子类对应模型持久层字段）
		superMapperField.set(this, subMapper);
	}

	@Override
	@Transactional
	public int saveOrUpdate(T t) {
		if (t.getId() == null) {
			return baseCrudMapper.save(t);
		}
		return baseCrudMapper.update(t);
	}

	@Override
	@Transactional
	public int delete(ID id) {
		return baseCrudMapper.delete(id);
	}

	@Override
	public T findOne(ID id) {
		return baseCrudMapper.findOne(id);
	}

	@Override
	public List<T> findAll() {
		return baseCrudMapper.findAll();
	}
	
	@Override
	public final boolean exists(ID id) {
		return findOne(id) != null;
	}
	
	@Override
	public final Long count() {
		return baseCrudMapper.count();
	}
	
}
