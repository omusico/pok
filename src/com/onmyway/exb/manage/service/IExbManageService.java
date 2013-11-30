package com.onmyway.exb.manage.service;

import java.util.List;
import java.util.Map;

import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.exb.manage.model.ExbBaseInfoConfig;
import com.onmyway.exb.manage.model.ExbMoneyAndLimit;
import com.onmyway.exb.manage.model.ExbZjInfo;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.exb.play.model.ExbTzInfo;

/**
 * @Title:快乐十分-后台管理
 * @Description: 
 * @Create on: Aug 14, 2010 5:30:03 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface IExbManageService {

	/**
	 * 保存信息
	 * @param config
	 * @throws AppException
	 */
	public void save(ExbZjNumConfig config) throws AppException;
	
	public void update(ExbZjNumConfig config) throws AppException;
	
	public ExbZjNumConfig get(String id) throws AppException;
	
	/**
	 * 查询所有的中奖信息
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<ExbZjNumConfig> getAllZjNum() throws AppException;
	/**
	 * 查询所有的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<ExbZjNumConfig> getAllZjNum(String currentIssue) throws AppException;
	
	/**
	 * 得到最新的中奖信息
	 * @return
	 * @throws AppException
	 */
	public ExbZjNumConfig getCurrentZjNum() throws AppException;
	/**
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws AppException
	 */
	public ExbZjNumConfig getCurrentZjNum(String currentIssue) throws AppException;
	/**
	 * 得到给定期号的中奖信息
	 * @return
	 * @throws AppException
	 */
	public ExbZjNumConfig getZjNumConfig(String issueNum) throws AppException;
	/**
	 * 每期基本信息设置
	 * @param info
	 * @throws AppException
	 */
	public void saveBaseInfo(ExbBaseInfoConfig info) throws AppException;
	
	public void updateBaseInfo(ExbBaseInfoConfig info) throws AppException;
	
	public ExbBaseInfoConfig getBaseInfo(String id) throws AppException;
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<ExbZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws AppException;

/**
 * 得到当天的基本配置信息
 * @param date
 * @return
 */
	public ExbBaseInfoConfig getConfigInfoOfToday();
	/**
	 * 奖金及限号信息设置
	 * @param info
	 * @throws AppException
	 */
	public void saveLimitInfo(ExbMoneyAndLimit info) throws AppException;

	/**
	 * 将限号信息放到map中
	 * @return
	 */
	public Map<String,Integer> getLimitInfoMap();
	/**
	 * 将奖金信息放到map中
	 * @return
	 */
	public Map<String,Integer> getPrizeInfoMap();
	/**
	 * 中奖信息保存，同时从数据库中查询已经投注的号码的中奖情况，并将其保存到数据中
	 * @param config
	 * @return
	 * @throws AppException
	 */
	public void saveZjInfo(ExbZjNumConfig config) throws AppException ;
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName) ;
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName,String userId) ;
	/**
	 * 根据发行期号，删除中奖号码信息，同时删除数据库中已存在的用户的中奖信息
	 * @param issueNum
	 * @return
	 */
	public boolean delZhongjianghaoma(String issueNum);
	/**
	 * 删除指定用户某一个中奖号码信息，同时删除数据库中已存在的用户的投注信息
	 * 同时将用户的账户中扣除相应的奖金（目前先不做)
	 * @param issueNum
	 * @return
	 */
	public boolean delUserZjInfo(String tzId,String userId);
	/**
	 * 查询指定用户总的中奖注数及中奖金额
	 * @param issueNum
	 * @return
	 */
	public ExbZjInfo getUserTotalZjInfo(String issueNum,String userId);
}
