package com.onmyway.ssc.manage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.manage.model.SscZjNumConfig;
 

/**
 * @Title:时时彩-后台管理-数据操作
 * @Description: 
 * @Create on: Aug 14, 2010 5:36:59 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISscZjNumConfigDao extends  EntityDAO<SscZjNumConfig,String>{

	/**
	 * 删除给定期号的的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public boolean deleteZjNum(String issueNum) throws DAOException;
	/**
	 * 得到给定期号的的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public SscZjNumConfig getZjNum(String issueNum) throws DAOException;
	/**
	 * 得到所有的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SscZjNumConfig> getAllZjNum() throws DAOException;
	
	/**
	 * 得到所有的中奖信息,除了当前正在发行的期别
	 * @return
	 * @throws DAOException
	 */
	public List<SscZjNumConfig> getAllZjNum(String currentIssue) throws DAOException;
	
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SscZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws DAOException;
	
	/**
	 * 得到最新的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public SscZjNumConfig getCurrentZjNum() throws DAOException;
	

	/**
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws DAOException
	 */
	public SscZjNumConfig getCurrentZjNum(String currentIssue) throws DAOException;
	/**
	 * 中奖信息保存
	 * 
	 * @param list
	 * @return
	 * @throws DAOException
	 */
	public void jdbcSaveListInfo(List<SscZjInfo> list,List<String> zjIdList,Map<String,Integer> zjMap) throws DAOException ;
	/**
	 * 根据发行期号，删除中奖号码信息，同时删除数据库中已存在的用户的中奖信息
	 * @param issueNum
	 * @return
	 */
	public boolean delZhongjianghaoma(String issueNum) throws DAOException;
}
