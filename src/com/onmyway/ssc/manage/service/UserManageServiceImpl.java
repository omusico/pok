package com.onmyway.ssc.manage.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.dao.IUserManageDao;
import com.onmyway.ssc.manage.model.UserInfo;
import com.onmyway.ssc.play.service.SscTzInfoServiceImpl;

/**
 * @Title:用户信息操作类
 * @Description: 
 * @Create on: Aug 24, 2010 9:14:17 PM
 * @Author:LJY
 * @Version:1.0
 */
public class UserManageServiceImpl implements IUserManageService {

	private Log logger = LogFactory.getLog(UserManageServiceImpl.class);
	
	private IUserManageDao userManageDao;

	public IUserManageDao getUserManageDao() {
		return userManageDao;
	}

	public void setUserManageDao(IUserManageDao userInfoDao) {
		this.userManageDao = userInfoDao;
	}
	
	/**
	 * 通过用户名得到用户对象
	 * @param userName
	 * @return
	 * @throws DAOException
	 */
	public UserInfo getUserInfoByName(String userName){
		UserInfo userInfo = new UserInfo();
		try{
			userInfo = userManageDao.getUserInfoByName(userName);
		}catch(DAOException e){
			logger.error("查询用户信息异常:" + e.toString());
		}
		return userInfo;
	}
	

	/**
	 * 改变用户的余额
	 * @param userName
	 * @param tzMoney
	 * @return
	 * @throws DAOException
	 */
	public boolean updateUserMoney(String userName,int tzMoney) {
		boolean flag = true;
		try{
			flag = userManageDao.updateUserMoney(userName, tzMoney);
		}catch(DAOException e){
			logger.error("跟新用户余额异常:" + e.toString());
			flag = false;
		}
		return flag;
	}
}
