package com.onmyway.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Order;

import com.onmyway.common.exception.DAOException;
 
 
public interface IHDAO {

	/**
	 * Get object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	public Object get(Class refClass, Serializable key) throws DAOException;

	/**
	 * Load object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	 public Object load(Class refClass, Serializable key) throws DAOException;

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * 
	 * @param refClass
	 * @param defaultOrder
	 * @return
	 */
	public java.util.List findAll (Class refClass, Order defaultOrder) throws DAOException;

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
	public Query getQuery(String queryStr) throws DAOException;

	/**
	 * Execute a query. 
	 * @param query a query expressed in Hibernate's query language
	 * @param queryStr the name of a query defined externally 
	 * @param param the first parameter to set
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable param) throws DAOException;

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter array
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable[] params) throws DAOException;

	/**
	 * Obtain an instance of Query for a named query string defined in the mapping file.
	 * Use the parameters given.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter Map
	 * @return Query
	 */
	public Query getQuery(String queryStr, Map params) throws DAOException;

	/**
	 * 得到指定的条数
	 * @param queryStr
	 * @param recordNum
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr,int recordNum) throws DAOException;
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr) throws DAOException;
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param param
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr, Serializable param) throws DAOException;
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr, Serializable[] params) throws DAOException;
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr, Map params) throws DAOException;
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a generated identifier. 
	 * (Or using the current value of the identifier property if the assigned generator is used.) 
	 */
	public Serializable save(final Object obj) throws DAOException;

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	public void saveOrUpdate(final Object obj) throws DAOException;

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 */
	public void update(final Object obj) throws DAOException;
	
	
	/**
	 * update
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public int update(String queryStr, Map params) throws DAOException;
	
	/**
	 * update
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 * add by ljy on 2009-09-08
	 */
	public int update(String queryStr) throws DAOException;
	
//	/**
//	 * 执行删除操作
//	 * @param queryStr
//	 * @return
//	 * @throws DAOException
//	 * add by ljy on 2010-10-10
//	 */
//	public int delete (String queryStr)throws DAOException ;
	/**
	 * Delete all objects returned by the query
	 */
	public int delete (final Query query)throws DAOException;

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class refClass, Serializable key) throws DAOException;
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class refClass, Serializable[] key) throws DAOException;


}
