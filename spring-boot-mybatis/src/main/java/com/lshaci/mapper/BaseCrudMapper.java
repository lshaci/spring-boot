package com.lshaci.mapper;

import java.io.Serializable;
import java.util.List;

import com.lshaci.domain.IDEntity;

/**
 * 
 * @author lshaci
 *
 * @param <T>	实体类型
 * @param <ID>	实体的主键类型
 */
public interface BaseCrudMapper<T extends IDEntity<?>, ID extends Serializable> {
	
	/**
	 * 保存一个对象
	 * 
	 * @param t		需要保存的对象
	 * @return		返回受影响的数据条数
	 */
	int save(T t);
	
	/**
	 * 根据主键删除一个对象
	 * 
	 * @param id	需要删除对象的主键id
	 */
	int delete(ID id);
	
	/**
	 * 根据主键修改一个对象
	 * 
	 * @param t		需要修改的对象
	 * @return		返回受影响的数据条数
	 */
	int update(T t);
	
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
	 * 获取该实体在数据库中的总数
	 * 
	 * @return		返回该实体在数据库中的总数
	 */
	Long count();

}
