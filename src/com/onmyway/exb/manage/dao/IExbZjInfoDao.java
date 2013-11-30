package com.onmyway.exb.manage.dao;

import java.util.List;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.exb.manage.model.ExbZjInfo;
import com.onmyway.exb.play.model.ExbTzInfo;
 

/**
 * @Title:快乐十分-后台管理-数据操作-对中奖用户信息进行操作
 * @Description: 
 * @Create on: Aug 14, 2010 5:36:59 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface IExbZjInfoDao extends  EntityDAO<ExbZjInfo,String>{

 
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName) throws DAOException;

	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName,String userId) throws DAOException;
	
	/**
	 * 删除指定用户某一个中奖号码信息，同时删除数据库中已存在的用户的投注信息
	 * 同时将用户的账户中扣除相应的奖金（目前先不做)
	 * @param issueNum
	 * @return
	 */
	public boolean delUserZjInfo(String tzId,String userId) throws DAOException ;
	
	/**
	 * 查询指定用户总的中奖注数及中奖金额
	 * @param issueNum
	 * @return
	 */
	public ExbZjInfo getUserTotalZjInfo(String issueNum,String userId) throws DAOException;
	/**
	 * 重置中奖号码时，删除对应期号已经存在的中奖信息
	 * @param issueNum
	 * @throws DAOException
	 */
	public void deleteHistoryZjInfo(String issueNum) throws DAOException;
	
}
