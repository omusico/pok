package com.onmyway.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.onmyway.common.exception.DAOException;
 

/**
 * 泛型DAO基类, 为单个Entity对象提供CRUD操作.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class及主键类型, 即拥有对单个Entity对象的CRUD操作.
 * <pre>
 * public class UserDAO extends GenericEntityDAO<User,Long> {
 * }
 * </pre>
 * 
 * @param <T> DAO访问的entity类
 * @param <PK> DAO访问的entity的主键类
 * 
 * @see HDAOImpl
 *  
 */
public class GenericEntityDAO<T, PK extends Serializable> extends HDAOImpl
		implements EntityDAO<T, PK> {
	protected final Log logger = LogFactory.getLog(getClass());

	protected Class<T> entityClass; // DAO所管理的Entity类型.
	protected String entityClassName; // DAO所管理的Entity名称.

	@SuppressWarnings("unchecked")
	public GenericEntityDAO() {
		// 通过范型反射，取得在子类中定义的entityClass.
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		entityClassName = entityClass.getSimpleName();
	}

	/**
	 * 取得entity的class.
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 使用泛型DAO根据ID获取对象.
	 * 
	 * @see HDAOImpl#get(Class, Serializable)
	 * 
	 * @return id对应的Entity.如果id不存在,返回null.
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public T get(final PK id) throws DAOException {
		Assert.notNull(id);

		if (logger.isDebugEnabled()) {
			logger.debug("get " + entityClassName + " instance with id: " + id);
		}

		return (T) get(entityClass, id);
	}

	/**
	 * 使用泛型DAO保存对象.
	 * 
	 * @see HDAOImpl#saveOrUpdate(Objet)
	 * 
	 * @throws DAOException
	 */
	public void save(T entity) throws DAOException {
		Assert.notNull(entity);

		if (logger.isDebugEnabled()) {
			logger.debug("saving " + entityClassName + " instance");
		}

		saveOrUpdate(entity);

		if (logger.isDebugEnabled()) {
			logger.debug("save successful");
		}
	}

	/**
	 * 使用泛型DAO根据ID移除单个对象.
	 * 
	 * @see HDAOImpl#delete(Class,Serializable)
	 * 
	 * @throws DAOException
	 */
	public void delete(PK id) throws DAOException {
		Assert.notNull(id);

		if (logger.isDebugEnabled()) {
			logger.debug("deleting " + entityClassName + " instance");
		}

		delete(entityClass, id);

		if (logger.isDebugEnabled()) {
			logger.debug("delete successful");
		}
	}
	
	/**
	 * 使用泛型DAO根据ID移除对象.
	 * 
	 * @see HDAOImpl#delete(Class,Serializable)
	 * 
	 * @throws DAOException
	 */
	public void delete(PK[] ids) throws DAOException {
		Assert.notNull(ids);

		if (logger.isDebugEnabled()) {
			logger.debug("deleting " + entityClassName + " instance");
		}

		delete(entityClass, ids);

		if (logger.isDebugEnabled()) {
			logger.debug("delete successful");
		}
	}
	
}
