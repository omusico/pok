package com.onmyway.ssc.manage.service;

import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.model.UserInfo;

/**
 * @Title:用户信息操作接口
 * @Description: 
 * @Create on: Aug 24, 2010 9:14:10 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface IUserManageService {
	/**
	 * 通过用户名得到用户对象
	 * @param userName
	 * @return
	 * @throws DAOException
	 */
	public UserInfo getUserInfoByName(String userName);
	
	/**
	 * 改变用户的余额
	 * @param userName
	 * @param tzMoney
	 * @return
	 * @throws DAOException
	 */
	public boolean updateUserMoney(String userName,int tzMoney) ;
}
