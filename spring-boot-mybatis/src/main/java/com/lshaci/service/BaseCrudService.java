package com.lshaci.service;

import java.util.List;

/**
 * 公共的CRUD业务层
 * 
 * @author lshaci
 *
 * @param <T>		业务对应的实体类型
 * @param <ID>		业务实体的主键类型
 */
public interface BaseCrudService<T, ID> {

	/**
	 * 保存或者根据主键修改一个对象
	 * 
	 * @param t		需要保存的对象
	 * @return		返回受影响的数据条数
	 */
	int saveOrUpdate(T t);
	
	/**
	 * 根据主键删除一个对象
	 * 
	 * @param id	返回受影响的数据条数
	 */
	int delete(ID id);
	
	/**
	 * 根据主键查询一个对象
	 * 
	 * @param id	需要查询对象的主键id
	 * @return		返回查询到的对象
	 */
	T findOne(ID id);
	
	/**
	 * 查询所有的对象
	 * 
	 * @return		返回查询到的对象集合
	 */
	List<T> findAll();
	
	/**
	 * 根据主键判断对象是否存在
	 * 
	 * @param id	需要判定对象的主键id
	 * @return
	 */
	boolean exists(ID id);
	
	/**
	 * 获取该实体在数据库中的总数
	 * 
	 * @return		返回该实体在数据库中的总数
	 */
	Long count();
	
}
