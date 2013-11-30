package com.onmyway.common.dao;

import java.io.Serializable;

import com.onmyway.common.exception.DAOException;
  

/**
 * 泛型DAO接口, 为单个Entity对象定义CRUD操作.
 * 
 * @param <T> DAO访问的entity类
 * @param <PK> DAO访问的entity的主键类
 * 
 * @author  
 */
public interface EntityDAO<T, PK extends Serializable> {
	T get(PK id) throws DAOException;

	// List<T> getAll();

	void save(T entity) throws DAOException;

	// void delete(T entity);

	void delete(PK id) throws DAOException;

	void delete(PK[] ids) throws DAOException;
}
