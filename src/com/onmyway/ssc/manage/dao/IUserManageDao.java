package com.onmyway.ssc.manage.dao;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.model.UserInfo;

/**
 * @Title:用户操作DAO
 * @Description: 
 * @Create on: Aug 24, 2010 9:11:36 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface IUserManageDao extends  EntityDAO<UserInfo,Integer>{

	/**
	 * 通过用户名得到用户对象
	 * @param userName
	 * @return
	 * @throws DAOException
	 */
	public UserInfo getUserInfoByName(String userName) throws DAOException;
	/**
	 * 改变用户的余额
	 * @param userName
	 * @param tzMoney
	 * @return
	 * @throws DAOException
	 */
	public boolean updateUserMoney(String userName,int tzMoney) throws DAOException;
}
