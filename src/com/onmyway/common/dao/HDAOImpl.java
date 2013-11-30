package com.onmyway.common.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.onmyway.common.exception.DAOException;
 

 
public class HDAOImpl implements IHDAO {

	int batchSize = 20;//批量更新时的大小
	/**
	 * SessionFactory
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	public Object get(Class refClass, Serializable key) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return get(refClass, key, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Get object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @param s
	 * @return
	 */
	private Object get(Class refClass, Serializable key, Session s) {
		return s.get(refClass, key);
	}

	/**
	 * Load object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	public Object load(Class refClass, Serializable key) throws DAOException {		
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return load(refClass, key, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Load object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @param s
	 * @return
	 */
	private Object load(Class refClass, Serializable key, Session s) {
		return s.load(refClass, key);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * 
	 * @param refClass
	 * @param defaultOrder
	 * @return
	 */
	public java.util.List findAll (Class refClass, Order defaultOrder) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return findAll(refClass, session, defaultOrder);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * 
	 * @param refClass
	 * @param s
	 * @param defaultOrder
	 * @return
	 */
	private java.util.List findAll (Class refClass, Session s, Order defaultOrder) {
		Criteria crit = s.createCriteria(refClass);
		if (null != defaultOrder) crit.addOrder(defaultOrder);
		return crit.list();
	}

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
	public Query getQuery(String queryStr) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @s the Session to use
	 */
	private Query getQuery(String queryStr, Session s) {		
		return s.createQuery(queryStr);
	}

	/**
	 * Execute a query. 
	 * @param query a query expressed in Hibernate's query language
	 * @param queryStr the name of a query defined externally 
	 * @param param the first parameter to set
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable param) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, param, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param param the first parameter to set
	 * @s the Session to use
	 * @return Query
	 */
	private Query getQuery(String queryStr, Serializable param, Session s) {
		Query q = getQuery(queryStr, s);
		q.setParameter(0, param);
		return q;
	}

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter array
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable[] params) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, params, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter array
	 * @s the Session
	 * @return Query
	 */
	private Query getQuery(String queryStr, Serializable[] params, Session s) {
		Query q = getQuery(queryStr, s);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q;
	}

	/**
	 * Obtain an instance of Query for a named query string defined in the mapping file.
	 * Use the parameters given.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter Map
	 * @return Query
	 */
	public Query getQuery(String queryStr, Map params) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, params, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Obtain an instance of Query for a named query string defined in the mapping file.
	 * Use the parameters given and the Session given.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter Map
	 * @s the Session
	 * @return Query
	 */
	private Query getQuery(String queryStr, Map params, Session s) {
		Query q = getQuery(queryStr, s);
		if (null != params) {
			for (Iterator i=params.entrySet().iterator(); i.hasNext(); ) {
				Map.Entry entry = (Map.Entry) i.next();
				q.setParameter((String) entry.getKey(), entry.getValue());
			}
		}
		return q;
	}
	/**
	 * 查询自定的条数
	 * @param queryStr
	 * @param params
	 * @param s
	 * @param max
	 * @return
	 */
	private Query getQuery(String queryStr, Map params,int recordNum) {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			Query q = getQuery(queryStr,session);
			if (null != params) {
				for (Iterator i=params.entrySet().iterator(); i.hasNext(); ) {
					Map.Entry entry = (Map.Entry) i.next();
					q.setParameter((String) entry.getKey(), entry.getValue());
				}
			}
			q.setMaxResults(recordNum);
			return q;
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	/**
	 * 查询指定条数的记录
	 * @param queryStr
	 * @param recordNum
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr,int recordNum) throws DAOException {
		return getQuery(queryStr,null,recordNum).list();
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr) throws DAOException {
		return getQuery(queryStr).list();
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param param
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr, Serializable param) throws DAOException {
		return getQuery(queryStr, param).list();
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr, Serializable[] params) throws DAOException {
		return getQuery(queryStr, params).list();
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List getList(String queryStr, Map params) throws DAOException {
		return getQuery(queryStr, params).list();
	}
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a generated identifier. 
	 * (Or using the current value of the identifier property if the assigned generator is used.) 
	 */
	public Serializable save(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return save(obj, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a generated identifier. 
	 * (Or using the current value of the identifier property if the assigned generator is used.) 
	 */
	private Serializable save(Object obj, Session s) {
		return s.save(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	public void saveOrUpdate(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			saveOrUpdate(obj, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	private void saveOrUpdate(Object obj, Session s) {
		s.saveOrUpdate(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 */
	public void update(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			update(obj, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 * @param s the Session
	 */
	private void update(Object obj, Session s) {
		s.update(obj);
	}

	/**
	 * update
	 * 
	 * @param queryStr
	 * @param param
	 * @return
	 * @throws DAOException
	 */
	public int update(String queryStr, Map params) throws DAOException {
		Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
		Query q = session.createQuery(queryStr);
		return q.executeUpdate();
	}
	/**
	 * update
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 * add by ljy on 2009-09-08
	 */
	public int update(String queryStr) throws DAOException{
		Map map = new HashMap();
		return update(queryStr,map);
	}
//	/**
//	 * 执行删除操作
//	 * @param queryStr
//	 * @return
//	 * @throws DAOException
//	 * add by ljy on 2010-10-10
//	 */
//	public int delete (String queryStr)throws DAOException {
//		try {
//			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
//			Query query = session.createQuery(queryStr);
//			return delete(query, session);
//		} catch(HibernateException heex) {
//            heex.printStackTrace();
//            throw SessionFactoryUtils.convertHibernateAccessException(heex);
//        }
//	}
	/**
	 * Delete all objects returned by the query
	 */
	public int delete (final Query query)throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return delete(query, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Delete all objects returned by the query
	 */
	private int delete (Query query, Session s) {
		List list = query.list();
		for (Iterator i=list.iterator(); i.hasNext(); ) {
			delete(i.next(), s);
		}
		return list.size();
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class refClass, Serializable key) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			delete(get(refClass, key, session), session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class refClass, Serializable[] key) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			for (int i = 0; i < key.length; i++) {
				delete(get(refClass, key[i], session), session);
			}
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	private void delete(Object obj, Session s) {
		s.delete(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 */
	protected void refresh(Object obj, Session s) {
		s.refresh(obj);
	}

	/**
	 * 
	 * @param t
	 */
	protected void throwException (Throwable t) {
		if (t instanceof HibernateException) throw (HibernateException) t;
		else if (t instanceof RuntimeException) throw (RuntimeException) t;
		else throw new HibernateException(t);
	}

	/**
	 * 
	 * @param t
	 */
	protected void handleError (Throwable t) {
	}
	/**
	 * 批量保存，
	 * @param list 同一类对象
	 * @throws DAOException
	 */

}
