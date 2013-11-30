package com.onmyway.sxw.play.dao;

import java.util.List;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.play.model.SxwTzInfo;

/**
 * @Title:12选5-投注信息管理
 * @Description: -保存-验证
 * @Create on: Aug 20, 2010 5:42:25 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISxwTzInfoDao extends  EntityDAO<SxwTzInfo,String> {
	
	/**
	 * 批量保存投注信息
	 * @param list
	 * @return
	 * @throws DAOException
	 */
	public boolean jdbcSaveListInfo(List<SxwTzInfo> list) throws DAOException;
	/**
	 * 得到指定用户的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public List<SxwTzInfo> getUserTzInfo(String issueNum,String userId) ;
	/**
	 * 删除指定用户的投注信息
	 * @param tzId
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public boolean delUserTzInfo(String tzId,String userId)throws DAOException;
	/**
	 * 得到指定期号中指定用户的投注的最大序号
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public String getUserTzMaxSeq(String issueNum,String userId) ;
	
	/**
	 * 查询指定用户总的投注注数及投注金额
	 * @param issueNum
	 * @return
	 */
	public SxwTzInfo getUserTotalTzInfo(String issueNum,String userId);
	
}
