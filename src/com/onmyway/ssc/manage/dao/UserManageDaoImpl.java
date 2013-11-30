package com.onmyway.ssc.manage.dao;

import java.util.List;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.model.UserInfo;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 24, 2010 9:11:47 PM
 * @Author:LJY
 * @Version:1.0
 */
public class UserManageDaoImpl  extends GenericEntityDAO<UserInfo,Integer> implements IUserManageDao{ 

	/**
	 * 通过用户名得到用户对象
	 * @param userName
	 * @return
	 * @throws DAOException
	 */
	public UserInfo getUserInfoByName(String userName) throws DAOException{
		String sql = "from UserInfo t where t.username=?";
		List<UserInfo> list = getList(sql,userName);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return new UserInfo();
	}
	/**
	 * 改变用户的余额
	 * @param userName
	 * @param tzMoney
	 * @return
	 * @throws DAOException
	 */
	public boolean updateUserMoney(String userName,int tzMoney) throws DAOException{
		String sql = "update UserInfo t set t.totalmoney=t.totalmoney-" + tzMoney +" where t.username='" + userName + "'";
		int count = update(sql);
		if(count == 1){
			return true;
		}
		if(count == 0){
			return false;
		}
		return true;
		
	}
}
