package com.onmyway.exb.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;
import util.ConvertToolkit;
import util.JDateToolkit;
import util.JStringToolkit;
import util.ZuheArith;

import com.onmyway.common.IdBuilder;
import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.exb.manage.dao.IExbBaseInfoConfigDao;
import com.onmyway.exb.manage.dao.IExbMoneyLimitDao;
import com.onmyway.exb.manage.dao.IExbZjInfoDao;
import com.onmyway.exb.manage.dao.IExbZjNumConfigDao;
import com.onmyway.exb.manage.model.ExbBaseInfoConfig;
import com.onmyway.exb.manage.model.ExbMoneyAndLimit;
import com.onmyway.exb.manage.model.ExbZjInfo;
import com.onmyway.exb.manage.model.ExbZjNum;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.exb.play.dao.IExbTzInfoDao;
import com.onmyway.exb.play.model.ExbTzInfo;
import com.onmyway.exb.utils.ExbTools;
import com.onmyway.factory.ExbConstant;
import com.onmyway.init.ExbLimitInfoInit;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:快乐十分-后台管理
 * @Description: 
 * @Create on: Aug 14, 2010 5:30:25 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbManageServiceImpl implements IExbManageService {	

	private Log logger = LogFactory.getLog(ExbManageServiceImpl.class);
//	Map<String,Integer> prizeMap = ExbLimitInfoInit.getExbPrizeMap();
	private IExbZjNumConfigDao dao;
	private IExbBaseInfoConfigDao exbBaseInfoConfigDao;
	private IExbMoneyLimitDao exbMoneyLimitDao;
	private IExbZjInfoDao exbZjInfoDao;
	private IExbTzInfoDao exbTzInfoDao;
	ZuheArith zuhe = new ZuheArith();
	DBTool dbTool = DBAccess.getDBTool();
	/**中奖信息****/
	public IExbZjInfoDao getExbZjInfoDao() {
		return exbZjInfoDao;
	}

	public void setExbZjInfoDao(IExbZjInfoDao exbZjInfoDao) {
		this.exbZjInfoDao = exbZjInfoDao;
	}
	
	public IExbTzInfoDao getExbTzInfoDao() {
		return exbTzInfoDao;
	}

	public void setExbTzInfoDao(IExbTzInfoDao exbTzInfoDao) {
		this.exbTzInfoDao = exbTzInfoDao;
	}

	/**
	 * 奖金设置及限制倍数
	 * @return
	 */
	public IExbMoneyLimitDao getExbMoneyLimitDao() {
		return exbMoneyLimitDao;
	}

	public void setExbMoneyLimitDao(IExbMoneyLimitDao exbMoneyLimitDao) {
		this.exbMoneyLimitDao = exbMoneyLimitDao;
	}

	/**
	 * 中奖号码DAO
	 * @return
	 */
	public IExbZjNumConfigDao getExbNumConfigDao() {
		return dao;
	}

	public void setExbNumConfigDao(IExbZjNumConfigDao dao) {
		this.dao = dao;
	}
	/**
	 * 基本信息dao
	 * @return
	 */
	public IExbBaseInfoConfigDao getExbBaseInfoConfigDao() {
		return exbBaseInfoConfigDao;
	}
	public void setExbBaseInfoConfigDao(IExbBaseInfoConfigDao baseInfoDao) {
		this.exbBaseInfoConfigDao = baseInfoDao;
	}
	
	public ExbZjNumConfig get(String id) throws AppException {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void save(ExbZjNumConfig config) throws AppException {
		try{
			dao.save(config);
		}catch(DAOException e){
			logger.error("保存中奖号码错误"+e.toString());
		}
		
	}

	public void update(ExbZjNumConfig config) throws AppException {
		// TODO Auto-generated method stub
		
	}
	public ExbBaseInfoConfig getBaseInfo(String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * 得到当天的基本配置信息
	 * @param date
	 * @return
	 */
	public ExbBaseInfoConfig getConfigInfoOfToday(){
		ExbBaseInfoConfig info = new ExbBaseInfoConfig();
		try{
			String today = JDateToolkit.getNowDate2();
			List<ExbBaseInfoConfig> list = exbBaseInfoConfigDao.getConfigInfoOfDay(today);
			if(list != null && !list.isEmpty()){
				info = list.get(0);
			}
		}catch(Exception e){
			logger.error("快乐十分：查询当天配置信息错误！"+e.toString());
		}
		//如果当天没有配置信息，则设置为默认值
		if(info.getBeginIssue()== null || info.getBeginIssue().equals("") ){
			String curDate = JDateToolkit.getNowDate5();
			String tempIssueNum = curDate.substring(2,curDate.length()) +"0001";
			info.setBeginHour("9");//开始小时
			info.setBeginMin("00");
			info.setBeginIssue(tempIssueNum);
			info.setSellMin("10");
			info.setWinMin("5");
			info.setDayIssueTimes("53");//每天卖多少期
			info.setOffsetFlag("+");  //以正负表示开奖时间与服务器时间的偏移量，“+”表示系统的销售/开奖时间比服务器的时间提前，“-”表示系统的开奖时间比服务器的时间滞后。
			info.setOffsetTime("0");//时间偏移量 )
		}
		return info;
	}
	/**
	 * 得到最新的中奖信息
	 * @return
	 * @throws AppException
	 */
	public ExbZjNumConfig getCurrentZjNum() throws AppException{
		ExbZjNumConfig info = new ExbZjNumConfig();
		try{
			info =  dao.getCurrentZjNum();
		}catch(DAOException ee){
			logger.error("快乐十分：查询中奖信息异常："+ee.toString());
			info.setPosition1("0");
			info.setPosition2("0");
			info.setPosition3("0");
			info.setPosition4("0");
			info.setPosition5("0");
			info.setPosition6("0");
			info.setPosition7("0");
			info.setPosition8("0");
		}
		return info;
	}
	/**
	 * 查询所有的中奖信息
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<ExbZjNumConfig> getAllZjNum() throws AppException{
		List<ExbZjNumConfig> list = new ArrayList<ExbZjNumConfig>();
		try{
			list = dao.getAllZjNum();
		}catch(Exception e){
			logger.error("快乐十分：查询中奖号码信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 得到给定期号的中奖信息
	 * @return
	 * @throws AppException
	 */
	public ExbZjNumConfig getZjNumConfig(String issueNum) throws AppException{
		ExbZjNumConfig info = new ExbZjNumConfig();
		try{
			info =  dao.getZjNum(issueNum);
		}catch(DAOException ee){
			logger.error("快乐十分：查询中奖信息异常："+ee.toString());
			info.setPosition1("0");
			info.setPosition2("0");
			info.setPosition3("0");
			info.setPosition4("0");
			info.setPosition5("0");
			info.setPosition6("0");
			info.setPosition7("0");
			info.setPosition8("0");
		}
		return info;
	}
	/**
	 * 基本信息保存
	 */
	public void saveBaseInfo(ExbBaseInfoConfig info) throws AppException {
		try{
			exbBaseInfoConfigDao.save(info);
		}catch(DAOException e){
			logger.error("快乐十分：保存基本信息错误"+e.toString());
		}
		
	}

	public void updateBaseInfo(ExbBaseInfoConfig info) throws AppException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 奖金及限号信息设置
	 * @param info
	 * @throws AppException
	 */
	public void saveLimitInfo(ExbMoneyAndLimit info) throws AppException{
		try{
			//先将该种玩法先前的限号信息都置为无效
			 exbMoneyLimitDao.updateLimitByRule("exb", info.getRule());
			//再保持现在的限号信息
			
			exbMoneyLimitDao.save(info);
			//同时更新系统中的限号配置信息
			ExbLimitInfoInit.exbLimitMap = getLimitInfoMap();
			ExbLimitInfoInit.exbPrizeMap = getPrizeInfoMap();			 
			
		}catch(Exception e){			
			logger.error("save 20x8 limit info error"+e.toString());
			throw new AppException("保存限号信息失败!");
		}
		
	}
	
	/**
	 * 将限号信息放到map中
	 * @return
	 */
	public Map<String,Integer> getLimitInfoMap(){
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<ExbMoneyAndLimit> list =  exbMoneyLimitDao.getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(ExbMoneyAndLimit info : list){
				String rule = info.getRule();
				String limit = info.getLimitOneIssue();
				int intLimit = 0;
				try{
					intLimit = Integer.parseInt(limit);
				}catch(Exception e){
					logger.error("快乐十分： 查询限号信息异常,number format exception:"+e.toString());
				}
				logger.info("快乐十分:rule="+rule+",limit="+limit);
				map.put(rule, intLimit);
			}
		}
		return map;
	}
	/**
	 * 将奖金信息放到map中
	 * @return
	 */
	public Map<String,Integer> getPrizeInfoMap(){
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<ExbMoneyAndLimit> list =  exbMoneyLimitDao.getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(ExbMoneyAndLimit info : list){
				String rule = info.getRule();
				String money = info.getMoney();
				if(money == null || money.equals("")){
					money = "0";
				}
				logger.info("快乐十分:rule="+rule+",prize="+money);
				map.put(rule, Integer.valueOf(money));
			}
		}
		return map;
	}
	/**
	 * 查询所有的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<ExbZjNumConfig> getAllZjNum(String currentIssue) throws AppException{
		List<ExbZjNumConfig> list = new ArrayList<ExbZjNumConfig>();
		try{
			list = dao.getAllZjNum(currentIssue);
		}catch(Exception e){
			logger.error("快乐十分：查询快乐十分中奖信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<ExbZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws AppException{
		List<ExbZjNumConfig> list = new ArrayList<ExbZjNumConfig>();
		try{
			list = dao.getZjNumBetweenDate(beginDate,endDate,currentIssue);
		}catch(Exception e){
			logger.error("快乐十分：查询快乐十分信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws AppException
	 */
	public ExbZjNumConfig getCurrentZjNum(String currentIssue) throws AppException{
		ExbZjNumConfig info = new ExbZjNumConfig();
		try{
			info =  dao.getCurrentZjNum(currentIssue);
		}catch(DAOException ee){
			logger.error("快乐十分：查询中奖信息异常："+ee.toString());
			info.setPosition1("0");
			info.setPosition2("0");
			info.setPosition3("0");
			info.setPosition4("0");
			info.setPosition5("0");
			info.setPosition6("0");
			info.setPosition7("0");
			info.setPosition8("0");
		}
		return info;
	}
	
	/**
	 * 中奖信息保存，同时从数据库中查询已经投注的号码的中奖情况，并将其保存到数据中
	 * @param config
	 * @return
	 * @throws AppException
	 */
	public void saveZjInfo(ExbZjNumConfig config) throws AppException {
		String isResetIssueInfo = config.getIsResetIssueInfo();//是否重新设置中奖号码 1：重新设置；0：不重新设置
		
		//定义容器
		List<ExbZjInfo> zjInfolist = new ArrayList<ExbZjInfo>();//存放中奖的记录的容器
		List<String> idDelList = new ArrayList<String>();//存放要删除的中奖记录ID
		Map<String,Integer> moneyMap = new HashMap<String,Integer>();//存放要返还的用户及相应的金额
		
		String issueNum = config.getIssueNum();

		if(isResetIssueInfo.equals("1")){//先删除原有已设定的期号的信息
			try{
				dao.deleteZjNum(issueNum);
//				logger.warn("删除"+issueNum + "期原有中奖号码：" + temp);
			}catch(DAOException ee){
				logger.error("快乐十分：删除"+issueNum + "期原有中奖号码异常:" + ee.toString());
				throw new AppException("快乐十分：删除"+issueNum + "期原有中奖号码异常");
			} 
		}
		try{
			//重新保存
			dao.save(config);
		}catch(DAOException ee){
			logger.error("快乐十分：保存中奖号码"+issueNum + "异常:" + ee.toString());
			throw new AppException("保存中奖号码"+issueNum + "异常");
		} 	
		String zjNum = config.getPosition1() + "," + config.getPosition2() + "," + config.getPosition3() + ","  
					 + config.getPosition4() + "," + config.getPosition5() + "," + config.getPosition6() + "," 
					 + config.getPosition7() + "," + config.getPosition8();
		setZjInfo(zjInfolist,idDelList,moneyMap,issueNum,zjNum);
 
		try{
			dao.jdbcSaveListInfo(zjInfolist, idDelList,moneyMap);//如果不抛出异常，就是保存成功
		}catch(DAOException ee){
			logger.error("快乐十分：中奖信息保存异常:" + ee.toString());
			throw new AppException("快乐十分：中奖信息保存异常:" + ee.toString());
		}  
	}
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName) {
		List<ExbZjInfo> list = new ArrayList<ExbZjInfo>();
		List<ExbZjInfo> tempList = new ArrayList<ExbZjInfo>();
		
		try{	
			tempList = exbZjInfoDao.getZjInfo(issueNum, userName);
			//将里面的字母转化为中文解释
			if(tempList != null){
				for(ExbZjInfo info : tempList){
					String playType = info.getPlayType();//玩法类型
					String playMode = info.getPlayMode();//玩法模式
					String zjRule = info.getZjType();//中奖规则
					
					String tempPlayType = ConvertToolkit.exbPlayTypeConvert(playType);
					String tempPlayMode = ConvertToolkit.exbPlayModeConvert(playType,playMode);
					String tempZjRule = "";
					String[] aryRule = JStringToolkit.splitString(zjRule,";");
					for(String sstr : aryRule){
						String[] aryZj = JStringToolkit.splitString(sstr,":");
						String ttype = aryZj[0];
						String ttzhushu = aryZj[1];
						String tempRule = ConvertToolkit.exbZjTypeConvert(ttype);
						
						tempZjRule = tempZjRule + tempRule + ":" + ttzhushu +"注;";
					}
					info.setPlayType(tempPlayType);
					info.setPlayMode(tempPlayMode);
					info.setZjType(tempZjRule);
					
					
					list.add(info);
				}
			}
		}catch(DAOException ee){
			logger.error("快乐十分：中奖信息保存异常:" + ee.toString());
		}
		return list;
	}
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<ExbZjInfo> getZjInfo(String issueNum,String userName,String userId) {
		List<ExbZjInfo> list = new ArrayList<ExbZjInfo>();
		List<ExbZjInfo> tempList = new ArrayList<ExbZjInfo>();
		
		try{	
			tempList =  exbZjInfoDao.getZjInfo(issueNum, userName,userId);
			//将里面的字母转化为中文解释
			if(tempList != null){
				for(ExbZjInfo info : tempList){
					String playType = info.getPlayType();//玩法类型
					String playMode = info.getPlayMode();//玩法模式
					String zjRule = info.getZjType();//中奖规则
					
					String tempPlayType = ConvertToolkit.exbPlayTypeConvert(playType);
					String tempPlayMode = ConvertToolkit.exbPlayModeConvert(playType,playMode);
					String tempZjRule = "";
					String[] aryRule = JStringToolkit.splitString(zjRule,";");
					if(aryRule != null && !aryRule.equals("")){
						for(String sstr : aryRule){
							String[] aryZj = JStringToolkit.splitString(sstr,":");
							String ttype = aryZj[0];
							String ttzhushu = aryZj[1];
							String tempRule = ConvertToolkit.exbZjTypeConvert(ttype);
							
							tempZjRule = tempZjRule + tempRule + ":" + ttzhushu +"注;";
						}
					}
					info.setPlayType(tempPlayType);
					info.setPlayMode(tempPlayMode);
					info.setZjType(tempZjRule);
					
					list.add(info);
				}
			}
//		try{	
//			list = exbZjInfoDao.getZjInfo(issueNum, userName,userId);
//			
		}catch(DAOException ee){
			logger.error("快乐十分：中奖信息查询异常:" + ee.toString());
		}
		return list;
	}
	/**
	 * 先将表里已存在的相应期号的中奖号码信息删除，然后再重新进行中奖号码的设置
	 * V2.0 任选2，任选3，任选4，规则适用新的规则，但是算法使用之前的5中2，5中3，5中4的规则
	 */
	public void setZjInfo(List<ExbZjInfo> zjInfolist,List<String> idDelList,Map<String,Integer> moneyMap,String issueNum, String zjNum) throws AppException{
		// TODO Auto-generated method stub
		//分解号码
		if(zjNum == null || zjNum.equals("")){
			throw new AppException("快乐十分：20x8 当前的中奖号码不能为空，请重新设置");
		}
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		int len = aryZjNum.length;
		if(len < 8){
			logger.error("快乐十分：20x8 开奖号码设置有误!请重新设置");
			throw new AppException("快乐十分：20x8 开奖号码设置有误!请重新设置");
			
		}
		try{
			exbZjInfoDao.deleteHistoryZjInfo(issueNum);
		}catch(DAOException e){
			logger.error("快乐十分：" + issueNum + "期的历史中奖信息删除失败!",e);
			throw new AppException(issueNum + "期的历史中奖信息删除失败!");
		}

		//拼接SQL
		String tempSql = getQueryZjSql(issueNum,zjNum);		
		String sql = "select a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,a.touzhu_num,a.parent_issue_num,a.parent_id,a.is_zhuitou,a.have_zt_flag,b.* from exb_tz_info a,(" + tempSql + ")b where a.id=b.p_id";
		logger.info("快乐十分:保存中奖号码:"+sql);
		
		
		List<ExbZjNum> zjNumToDelList = new ArrayList<ExbZjNum>();
		
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{
			
			while(crs.next()){
		
				String tempId = IdBuilder.getId();//生成主键ID
				String tempIssueNum = crs.getString("issue_num");
				String tempTzId = crs.getString("id");
				String tempDetailId = crs.getString("c_id");
				String tempUserId = crs.getString("user_id");
				String tempUserName = crs.getString("user_name");
				String tempPlayType = crs.getString("play_type");//主表中的play_type
				String tempPlayMode = crs.getString("play_mode");//主表中的play_mode					
				String tempTzNum = crs.getString("touzhu_num");//主表中的touzhu_num
				String tempTzTimes = crs.getString("num_times");
				String tempIsZhuitou = crs.getString("is_zhuitou");
				String tempOpTime = JDateToolkit.getNowDate1();//得到当前操作时间
				String tempZjType = crs.getString("type_name");//子表中玩法类型
				String cPlayMode = crs.getString("c_play_mode");//子表中的play_mode
				String tempNumDetail = crs.getString("num_detail");//子表中分解后的投注号码

				String tempParentIssueNum = crs.getString("parent_issue_num");
				
				//判断中奖后删除其余的投注信息用的
				String tempHaveZhuitou = crs.getString("have_zt_flag");//是否有追投
				String tempParentId = crs.getString("parent_id");//info表中的投注父ID
				String tempInfoIsZhuitou = crs.getString("is_zhuitou");//info表中的是否是追投	
				
				ExbZjNum zjNumInfo = new ExbZjNum();
				zjNumInfo.setId(tempId);
				zjNumInfo.setIssueNum(issueNum);
				zjNumInfo.setParentIssueNum(tempParentIssueNum);
				zjNumInfo.setUserId(tempUserId);
				zjNumInfo.setUserName(tempUserName);
				zjNumInfo.setTzId(tempTzId);//投注ID
				zjNumInfo.setHaveZhuitou(tempHaveZhuitou);
				zjNumInfo.setIsZhuitou(tempInfoIsZhuitou);
				zjNumInfo.setParentId(tempParentId);
				
				zjNumToDelList.add(zjNumInfo);
				
				//保存中奖信息
				//得到当前查询的号码中的中奖信息
				RecordZjInfo recordZjInfo = getZjInfoInRecord(zjNum,tempPlayType,tempPlayMode,tempNumDetail);
				
				ExbZjInfo zjInfo = new ExbZjInfo();
				zjInfo.setZjZhushu(recordZjInfo.getZjZhushuOneDetail().toString());//设置中奖注数
				
				//判断中奖注数，根据玩法及相应的投注号码，查询中奖的注数。判断中奖号码在相应的投注号码出现的次数即为中奖的注数
				int intZjZhushuInRecord = recordZjInfo.getZjZhushuOneDetail();//中奖的注数(如投：12345,12345，则注数是2
				int intZjMoneyInRecord = recordZjInfo.getZjMoneyOneDetail();//一个投注详细信息中的中奖金额 
				int intTzTimes = Integer.parseInt(tempTzTimes);//得到投注的倍数
				int intTotalZjZhushu = intZjZhushuInRecord * intTzTimes;//总的中奖注数=单注中奖注数*倍数
				int intTotalMoney = intZjMoneyInRecord * intTzTimes;//总的中奖金额=单注中奖金额*倍数
				String zjRule = recordZjInfo.getZjRule();//中奖金额
						
				String tempMoneyOfPlayType = recordZjInfo.getMoneyOfPlayType().toString();//当前玩法中奖的奖金设置
				
				zjInfo.setId(tempId);
				zjInfo.setIssueNum(tempIssueNum);
				zjInfo.setTzId(tempTzId);
				zjInfo.setDetailId(tempDetailId);
				zjInfo.setUserId(tempUserId);
				zjInfo.setUserName(tempUserName);
				zjInfo.setPlayType(tempPlayType);
				zjInfo.setPlayMode(tempPlayMode);
				zjInfo.setTzNum(tempTzNum);
				zjInfo.setTzTimes(tempTzTimes);
				zjInfo.setIsZhuitou(tempIsZhuitou);
				zjInfo.setOpTime(tempOpTime);
				zjInfo.setZjType( zjRule);
				zjInfo.setZjMoney(String.valueOf(intTotalMoney));
				zjInfo.setTotalZjZhushu(String.valueOf(intTotalZjZhushu));
				zjInfo.setMoneyOfZjType(tempMoneyOfPlayType);
				
				zjInfolist.add(zjInfo);					
			}
		}catch(Exception e){
			logger.error("快乐十分：中奖信息保存异常:" ,e);
			throw new AppException("中奖信息保存异常:" );
		}
		
		//多期投注的情况下，如果其中有一期中奖，那么中奖后的其余期数投注号码要自动删除，删除后用户余额与所投金额要相应增加与扣除。
		if(zjNumToDelList != null && !zjNumToDelList.isEmpty()){
			
			for(int i = 0; i < zjNumToDelList.size(); i++){
				ExbZjNum info = zjNumToDelList.get(i);
				if(info.isFirstZj() ){
					if(info.getHaveZhuitou().equals("1")){
						//查询PId为该ID 并且 have_zhuitou != 1的记录的所有的子记录
						getChildIssueIdList(info.getTzId(),info.getIssueNum(),info.getUserId(),true,idDelList,moneyMap);
					}
				}else{
					//查询所有PID为该PID的子ID，如果不是第一个投注，则PID应该是当前投注记录的PID
					 getChildIssueIdList(info.getParentId(),info.getIssueNum(),info.getUserId(),false,idDelList,moneyMap);
				}
			}					
		}
		
	}
	/**
	 * 根据期号及设置的中奖号码，生成相应的判断中奖号码的sql
	 * @param issueNum
	 * @param zjNum
	 * @return
	 */
	private String getQueryZjSql(String issueNum,String zjNum){
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		//分别设置整型和数值型各个位置上的值，主要是为了写着方便,记着好记
		int intPosition1 = Integer.parseInt(aryZjNum[0]);//第1位
		int intPosition2 = Integer.parseInt(aryZjNum[1]);//第2位
		int intPosition3 = Integer.parseInt(aryZjNum[2]);//第3位
		int intPosition4 = Integer.parseInt(aryZjNum[3]);//第4位
		int intPosition5 = Integer.parseInt(aryZjNum[4]);//第5位
		int intPosition6 = Integer.parseInt(aryZjNum[5]);//第6位
		int intPosition7 = Integer.parseInt(aryZjNum[6]);//第7位
		int intPosition8 = Integer.parseInt(aryZjNum[7]);//第8位
 
		
		int[] tempAry = {intPosition1,intPosition2,intPosition3,intPosition4,intPosition5,intPosition6,intPosition7,intPosition8};
		int[] intZjNumOrderAry = JStringToolkit.intOrder(tempAry, 1);//将开奖号码按从小到大排列,
		ZuheArith zuhe = new ZuheArith();
		
		//1.选1
		String sqlXuan1 = "";
//		String sqlXuan1= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
//					   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X1+"' ";
		String tempSqlXuan1Shutou = "";
//		for(String temp : aryZjNum){
//			tempSqlXuan1Shutou = tempSqlXuan1Shutou + " or t.num_detail like  '%|" + temp +"|%'" ;
//		}
//		if(!tempSqlXuan1Shutou.equals("")){
//			sqlXuan1 = sqlXuan1 + " and (1 = 2 " + tempSqlXuan1Shutou + " )";
//		}
//		
		//指从01至18中任意选择1个数字号码，对开奖号码中按开奖顺序出现的第一个位置的投注。
		if(intPosition1==19 || intPosition1 == 20){//此时只要是红投的可以中奖，数投的因为是1-18,肯定不会中奖
			sqlXuan1= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X1+"' and t.play_mode='hongtou' ";
		}else{
			sqlXuan1= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X1+"' and t.play_mode='shutou'";
			
			tempSqlXuan1Shutou = tempSqlXuan1Shutou + " or t.num_detail like  '%|" + intPosition1 +"|%'" ;
			if(!tempSqlXuan1Shutou.equals("")){
				sqlXuan1 = sqlXuan1 + " and (1 = 2 " + tempSqlXuan1Shutou + " )";
			}
		}
		
		
		//2.选2 任选 任选胆拖
		List<String> xuan2RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 2);//选二任选，选二任选中二：投注号码与当期开奖号码中任意两个位置的号码相符，即中奖
		String sqlXuan2= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X2+"' and t.play_mode in ('"+ExbConstant.X2_MODE_RENXUAN+"','"+ExbConstant.X2_MODE_RENXUAN_DANTUO+"')";
		String tempSqlXuan2Renxuan = "";
		for(String temp : xuan2RenxuanList){
			tempSqlXuan2Renxuan = tempSqlXuan2Renxuan + " or t.num_detail like  '%|" + temp +"|%'" ;
		}
		sqlXuan2 = sqlXuan2 + " and (1=2 " + tempSqlXuan2Renxuan + ") ";
		
		//将开奖号码分解成连续两位的数
		String[] xuan2TwoNumAry =  JStringToolkit.splitStringByCount(zjNum,",",2);
		//2.1 选二连直：指对开奖号码中按开奖顺序出现的两个连续位置按位相符的投注
		String sqlXuan2Lianzhi= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X2+"' and t.play_mode ='"+ExbConstant.X2_MODE_LIANZHI+"'";
		String tempSqlXuan2Lianzhi = "";
		for(String temp : xuan2TwoNumAry){
			tempSqlXuan2Lianzhi = tempSqlXuan2Lianzhi + " or t.num_detail like  '%|" + temp +"|%'" ;
		}
		sqlXuan2Lianzhi = sqlXuan2Lianzhi + " and (1=2 " + tempSqlXuan2Lianzhi + ") ";
		
		//2.2 选二连组中二：投注号码与当期开奖号码中按开奖顺序出现的两个连续位置的号码相符（顺序不限） 
		//将分解后的连续两位的数按照从小到大的顺序排列
		String[] xuan2TwoNumOrderAry = new String[xuan2TwoNumAry.length];
		for(int i=0; i<xuan2TwoNumAry.length;i++){
			xuan2TwoNumOrderAry[i] = JStringToolkit.strSort(xuan2TwoNumAry[i],",");//将数组中的值由小到大进行排序；
		}
		String sqlXuan2Lianzu= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X2+"' and t.play_mode in ('"+ExbConstant.X2_MODE_LIANZU+"','"+ExbConstant.X2_MODE_LIANZU_DANTUO+"')";
		String tempSqlXuan2Lianzu = "";
		for(String temp : xuan2TwoNumOrderAry){
			tempSqlXuan2Lianzu = tempSqlXuan2Lianzu + " or t.num_detail like  '%|" + temp +"|%'" ;
		}
		sqlXuan2Lianzu = sqlXuan2Lianzu + " and (1=2 " + tempSqlXuan2Lianzu + ") ";
		
		//3.选3 任选 任选胆拖
		List<String> xuan3RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 3);//选3任选，选3任选中3：投注号码与当期开奖号码中任意三个位置的号码相符，即中奖
		String sqlXuan3= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X3+"' and t.play_mode in ('"+ExbConstant.X3_MODE_RENXUAN+"','"+ExbConstant.X3_MODE_RENXUAN_DANTUO+"')";
		String tempSqlXuan3Renxuan = "";
		for(String temp : xuan3RenxuanList){
			tempSqlXuan3Renxuan = tempSqlXuan3Renxuan + " or t.num_detail like  '%|" + temp +"|%'" ;
		}
		sqlXuan3 = sqlXuan3 + " and (1=2 " + tempSqlXuan3Renxuan + ") ";
	
		//
		String beforeThreeNum = intPosition1 + "," + intPosition2 + "," + intPosition3;
		
		//3.1. 选三前直中三：投注号码与当期开奖号码中按开奖顺序出现的前三个连续位置的号码按位相符，即中奖。
		String sqlXuan3Qianzhi= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X3+"' and t.play_mode='"+ExbConstant.X3_MODE_QIANZHI+"'"
		   + " and t.num_detail like '%|" + beforeThreeNum + "|%'";
		
		//3.2选三前组中三(前组3，前组3胆拖)：投注号码与当期开奖号码中按开奖顺序出现的前三个连续位置的号码相符（顺序不限），即中奖
		String beforeThreeNumOrder = JStringToolkit.strSort(beforeThreeNum,",");//将数组中的值由小到大进行排序；
		String sqlXuan3Qianzu= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X3+"' and t.play_mode in ('"+ExbConstant.X3_MODE_QIANZU+"','"+ExbConstant.X3_MODE_QIANZU_DANTUO+"')"
		   + " and t.num_detail like '%|" + beforeThreeNumOrder + "|%'";
		   
	
		
		//4.选4 任选 任选胆拖 选四任选中四：投注号码与当期开奖号码中任意四个位置的号码相符，即中奖。
		List<String> xuan4RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 4);//选4任选，选4任选中4：投注号码与当期开奖号码中任意三个位置的号码相符，即中奖
		String sqlXuan4= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X4+"' and t.play_mode in ('"+ExbConstant.X4_MODE_RENXUAN+"','"+ExbConstant.X4_MODE_RENXUAN_DANTUO+"')";
		String tempSqlXuan4Renxuan = "";
		for(String temp : xuan4RenxuanList){
			tempSqlXuan4Renxuan = tempSqlXuan4Renxuan + " or t.num_detail like  '%|" + temp +"|%'" ;
		}
		sqlXuan4 = sqlXuan4 + " and (1=2 " + tempSqlXuan4Renxuan + ") ";
	
		//5.选5 任选 任选胆拖 投注号码与当期开奖号码中任意五个位置的号码相符，即中奖。
		List<String> xuan5RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 5);//选4任选，选4任选中4：投注号码与当期开奖号码中任意三个位置的号码相符，即中奖
		String sqlXuan5= " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from exb_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='"+ExbConstant.PLAY_TYPE_X5+"' and t.play_mode in ('"+ExbConstant.X5_MODE_RENXUAN+"','"+ExbConstant.X5_MODE_RENXUAN_DANTUO+"')";
		String tempSqlXuan5Renxuan = "";
		for(String temp : xuan5RenxuanList){
			tempSqlXuan5Renxuan = tempSqlXuan5Renxuan + " or t.num_detail like  '%|" + temp +"|%'" ;
		}
		sqlXuan5 = sqlXuan5 + " and (1=2 " + tempSqlXuan5Renxuan + ") ";
  
		//拼接最终的SQL
		String sql = "";
		sql = sql + sqlXuan1 ;
		sql = sql + " union " + sqlXuan2+ " union " + sqlXuan2Lianzu+ " union " + sqlXuan2Lianzhi ;
		sql = sql + " union " + sqlXuan3+ " union " + sqlXuan3Qianzu+ " union " + sqlXuan3Qianzhi ;
		sql = sql + " union " + sqlXuan4 ;
		sql = sql + " union " + sqlXuan5 ;
	 
		return sql;
	}
	
	/**
	 * 得到一个投注号码里中奖的注数
	 * @param zjNum
	 * @param playType
	 * @param playMode
	 * @param crsTzDetail
	 * @param crsTzTimes
	 * @return 中奖注数，中奖金额，中奖规则等，不包含倍数
	 */
	private RecordZjInfo getZjInfoInRecord(String zjNum,String playType,String playMode,String crsTzDetail){
		RecordZjInfo info = null;
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){
			info = getXuan1ZjInfoInRecord(zjNum,playMode,crsTzDetail);
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			info = getXuan2ZjInfoInRecord(zjNum,playMode,crsTzDetail);
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			info = getXuan3ZjInfoInRecord(zjNum,playMode,crsTzDetail);
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			info = getXuan4ZjInfoInRecord(zjNum,playMode,crsTzDetail);
		}else if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			info = getXuan5ZjInfoInRecord(zjNum,playMode,crsTzDetail);
		}
		
		return info;
	}
	/**
	 * 判断选1玩法中奖的信息,只判断当前投注号码中奖的数量，不乘以倍投
	 * @param zjNum
	 * @param playMode
	 * @param crsTzDetail
	 * @param crsTzTimes
	 * @return
	 */
	private RecordZjInfo getXuan1ZjInfoInRecord(String zjNum,String playMode,String crsTzDetail){
		String[] zjNumAry = JStringToolkit.splitString(zjNum, ",");
		int times = 0;
		
		int prizeMoney = 0;
		String rule = "";
		String zjPostion1 = zjNumAry[0];
		if((zjPostion1.equals("19") || zjPostion1.equals("20")) && playMode.equals(ExbConstant.X1_MODE_HONGTOU)){//进行红投判断
			//如果是红投中奖，则不论19/20，都视为中奖，一注中有几个号码，就是中奖几注
			String[] strTimes = JStringToolkit.splitString(crsTzDetail, "|");
			times = strTimes.length;
			
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X1, ExbConstant.X1_MODE_HONGTOU);
			rule = ExbConstant.X1_PRIZE_LIMIT_HONGTOU + ":" + times + ";";//格式为"规则:中奖注数"
		}else{
			times = JStringToolkit.getSubNum(crsTzDetail,"|"+zjPostion1+"|");//投注号码与开奖号码中按开奖顺序出现的第一个位置数字号码相符，即中奖。
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X1, ExbConstant.X1_MODE_SHUTOU);
			rule = ExbConstant.X1_PRIZE_LIMIT_SHUTOU + ":" + times + ";";//格式为"规则:中奖注数"
		}
			
//			if(playMode.equals(ExbConstant.X1_MODE_SHUTOU)){//数投
//				//prizeMoney = prizeMap.get(ExbConstant.X1_MODE_SHUTOU);
//				prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X1, ExbConstant.X1_MODE_SHUTOU);
//				rule = ExbConstant.X1_PRIZE_LIMIT_SHUTOU + ":" + times + ";";//格式为"规则:中奖注数"
//			}else if(playMode.equals(ExbConstant.X1_MODE_HONGTOU)){//红投
//	//			prizeMoney = prizeMap.get(ExbConstant.X1_MODE_HONGTOU);
//				prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X1, ExbConstant.X1_MODE_HONGTOU);
//				rule = ExbConstant.X1_PRIZE_LIMIT_HONGTOU + ":" + times + ";";//格式为"规则:中奖注数"
//			}
//		}
		int totalZjMoney = prizeMoney * times;
		
		RecordZjInfo info = new RecordZjInfo();
		info.setZjMoneyOneDetail(totalZjMoney);
		info.setZjZhushuOneDetail(times);
		info.setZjRule(rule);
		info.setMoneyOfPlayType(prizeMoney);
		
		return info;
	}
	
	/**
	 * 判断选2玩法中奖的信息,只判断当前投注号码中奖的数量，不乘以倍投
	 * @param zjNum
	 * @param playMode
	 * @param crsTzDetail
	 * @param crsTzTimes
	 * @return
	 */
	private RecordZjInfo getXuan2ZjInfoInRecord(String zjNum,String playMode,String crsTzDetail){
		int times = 0;
		int prizeMoney = 0;
		String rule = "";

		//2.选2 任选 任选胆拖
		int[] intZjNumAry = JStringToolkit.splitStrToInt(zjNum, ",");
		List<String> xuan2RenxuanList = zuhe.combineWrap(intZjNumAry, 2);//选二任选，选二任选中二：投注号码与当期开奖号码中任意两个位置的号码相符，即中奖
		
		//将开奖号码分解成连续两位的数
		String[] xuan2TwoNumAry =  JStringToolkit.splitStringByCount(zjNum,",",2);
	  
		//选2 任选 任选胆拖
		if(playMode.equals(ExbConstant.X2_MODE_RENXUAN) || playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){
			//判断设置的中奖号码中在投注记录中出现的次数
			for(int i=0; i<xuan2RenxuanList.size();i++){
				times = times + JStringToolkit.getSubNum(crsTzDetail, xuan2RenxuanList.get(i));
			}
			if(times != 0){
				rule = ExbConstant.X2_PRIZE_LIMIT_RENXUAN + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X2_PRIZE_LIMIT_RENXUAN);

			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X2, ExbConstant.X2_MODE_RENXUAN);
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){
			//选二连直：指对开奖号码中按开奖顺序出现的两个连续位置按位相符的投注
			for(int i=0; i<xuan2TwoNumAry.length;i++){
				times = times + JStringToolkit.getSubNum(crsTzDetail, xuan2TwoNumAry[i]);
			}
			if(times != 0){
				rule = ExbConstant.X2_PRIZE_LIMIT_LIANZHI + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X2_PRIZE_LIMIT_LIANZHI);

			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X2, ExbConstant.X2_MODE_LIANZHI);
		}else	if(playMode.equals(ExbConstant.X2_MODE_LIANZU) || playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){
			//选2  连组 连组胆拖 选二连组中二：投注号码与当期开奖号码中按开奖顺序出现的两个连续位置的号码相符（顺序不限） 
		
			//将分解后的连续两位的数按照从小到大的顺序排列
			String[] xuan2TwoNumOrderAry = new String[xuan2TwoNumAry.length];
			for(int i=0; i<xuan2TwoNumAry.length;i++){
				xuan2TwoNumOrderAry[i] = JStringToolkit.strSort(xuan2TwoNumAry[i],",");//将数组中的值由小到大进行排序；
			}
			
			for(int i=0; i<xuan2TwoNumOrderAry.length;i++){
				times = times + JStringToolkit.getSubNum(crsTzDetail, xuan2TwoNumOrderAry[i]);
			}
			if(times != 0){
				rule = ExbConstant.X2_PRIZE_LIMIT_LIANZU + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X2_PRIZE_LIMIT_LIANZU);
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X2, ExbConstant.X2_MODE_LIANZU);
		}
		
		int totalZjMoney = prizeMoney * times;//当前号码中总的中奖注数		
		RecordZjInfo info = new RecordZjInfo();
		info.setZjMoneyOneDetail(totalZjMoney);
		info.setZjZhushuOneDetail(times);
		info.setZjRule(rule);
		info.setMoneyOfPlayType(prizeMoney);
		return info;
	}
	
	/**
	 * 判断选3玩法中奖的信息,只判断当前投注号码中奖的数量，不乘以倍投
	 * @param zjNum
	 * @param playMode
	 * @param crsTzDetail
	 * @param crsTzTimes
	 * @return
	 */
	private RecordZjInfo getXuan3ZjInfoInRecord(String zjNum,String playMode,String crsTzDetail){
		int times = 0;
		int prizeMoney = 0;
		String rule = "";

		//选3 任选 任选胆拖
		int[] intZjNumAry = JStringToolkit.splitStrToInt(zjNum, ",");
		
		String strZjNumOrder =  JStringToolkit.strSort(zjNum,",");//将数组中的值由小到大进行排序；
		int[] intZjNumOrderAry = JStringToolkit.splitStrToInt(strZjNumOrder, ",");
		List<String> xuan3RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 3);//选3任选，选3任选中3：投注号码与当期开奖号码中任意3个位置的号码相符，即中奖
		String beforeThreeNum = intZjNumAry[0] + "," + intZjNumAry[1] + "," + intZjNumAry[2];
	  
		//选3 任选 任选胆拖
		if(playMode.equals(ExbConstant.X3_MODE_RENXUAN) || playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){
			//判断设置的中奖号码中在投注记录中出现的次数
			for(int i=0; i<xuan3RenxuanList.size();i++){
				logger.info("crsTzDetail="+crsTzDetail+",list="+xuan3RenxuanList.get(i));
				times = times + JStringToolkit.getSubNum(crsTzDetail, "|"+xuan3RenxuanList.get(i)+"|");
			}
			if(times != 0){
				rule = ExbConstant.X3_PRIZE_LIMIT_RENXUAN + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X3_PRIZE_LIMIT_RENXUAN);
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X3, ExbConstant.X3_MODE_RENXUAN);
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){
			//选三前直中三：投注号码与当期开奖号码中按开奖顺序出现的前三个连续位置的号码按位相符，即中奖。
			times = times + JStringToolkit.getSubNum(crsTzDetail, beforeThreeNum);
			if(times != 0){
				rule = ExbConstant.X3_PRIZE_LIMIT_QIANZHI + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X3_PRIZE_LIMIT_QIANZHI);
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X3, ExbConstant.X3_MODE_QIANZHI);
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU) || playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){
			//选三前组中三(前组3，前组3胆拖)：投注号码与当期开奖号码中按开奖顺序出现的前三个连续位置的号码相符（顺序不限），即中奖
			String beforeThreeNumOrder = JStringToolkit.strSort(beforeThreeNum,",");//将数组中的值由小到大进行排序；
			times = times + JStringToolkit.getSubNum(crsTzDetail, beforeThreeNumOrder);
			if(times != 0){
				rule = ExbConstant.X3_PRIZE_LIMIT_QIANZU + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X3_PRIZE_LIMIT_QIANZU);
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X3, ExbConstant.X3_MODE_QIANZU);
		}
		
		int totalZjMoney = prizeMoney * times;//当前号码中总的中奖注数		
		RecordZjInfo info = new RecordZjInfo();
		info.setZjMoneyOneDetail(totalZjMoney);
		info.setZjZhushuOneDetail(times);
		info.setZjRule(rule);
		info.setMoneyOfPlayType(prizeMoney);
		return info;
	}
	
	/**
	 * 判断选4玩法中奖的信息,只判断当前投注号码中奖的数量，不乘以倍投
	 * @param zjNum
	 * @param playMode
	 * @param crsTzDetail
	 * @param crsTzTimes
	 * @return
	 */
	private RecordZjInfo getXuan4ZjInfoInRecord(String zjNum,String playMode,String crsTzDetail){
		int times = 0;
		int prizeMoney = 0;
		String rule = "";

		//选4 任选 任选胆拖
		
		String strZjNumOrder =  JStringToolkit.strSort(zjNum,",");//将数组中的值由小到大进行排序；
		int[] intZjNumOrderAry = JStringToolkit.splitStrToInt(strZjNumOrder, ",");
		
//		int[] intZjNumAry = JStringToolkit.splitStrToInt(zjNum, ",");
		List<String> xuan4RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 4);//选4任选，选4任选中4：投注号码与当期开奖号码中任意3个位置的号码相符，即中奖
	  
		//选4 任选 任选胆拖
		if(playMode.equals(ExbConstant.X4_MODE_RENXUAN) || playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){
			//判断设置的中奖号码中在投注记录中出现的次数
			for(int i=0; i<xuan4RenxuanList.size();i++){
				times = times + JStringToolkit.getSubNum(crsTzDetail, xuan4RenxuanList.get(i));
			}
			if(times != 0){
				rule = ExbConstant.X4_PRIZE_LIMIT_RENXUAN + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X4_PRIZE_LIMIT_RENXUAN);
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X4, ExbConstant.X4_MODE_RENXUAN);
		} 
		
		int totalZjMoney = prizeMoney * times;//当前号码中总的中奖注数		
		RecordZjInfo info = new RecordZjInfo();
		info.setZjMoneyOneDetail(totalZjMoney);
		info.setZjZhushuOneDetail(times);
		info.setZjRule(rule);
		info.setMoneyOfPlayType(prizeMoney);
		return info;
	}

	/**
	 * 判断选4玩法中奖的信息,只判断当前投注号码中奖的数量，不乘以倍投
	 * @param zjNum
	 * @param playMode
	 * @param crsTzDetail
	 * @param crsTzTimes
	 * @return
	 */
	private RecordZjInfo getXuan5ZjInfoInRecord(String zjNum,String playMode,String crsTzDetail){
		int times = 0;
		int prizeMoney = 0;
		String rule = "";

		//选5 任选 任选胆拖
		
		String strZjNumOrder =  JStringToolkit.strSort(zjNum,",");//将数组中的值由小到大进行排序；
		int[] intZjNumOrderAry = JStringToolkit.splitStrToInt(strZjNumOrder, ",");
		
//		int[] intZjNumAry = JStringToolkit.splitStrToInt(zjNum, ",");
		List<String> xuan5RenxuanList = zuhe.combineWrap(intZjNumOrderAry, 5);//选5任选，选5任选中5：投注号码与当期开奖号码中任意3个位置的号码相符，即中奖
	  
		//选5 任选 任选胆拖
		if(playMode.equals(ExbConstant.X5_MODE_RENXUAN) || playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){
			//判断设置的中奖号码中在投注记录中出现的次数
			for(int i=0; i<xuan5RenxuanList.size();i++){
				times = times + JStringToolkit.getSubNum(crsTzDetail, xuan5RenxuanList.get(i));
			}
			if(times != 0){
				rule = ExbConstant.X5_PRIZE_LIMIT_RENXUAN + ":" + times + ";";//格式为"规则:中奖注数"
			}
//			prizeMoney = prizeMap.get(ExbConstant.X5_PRIZE_LIMIT_RENXUAN);
			prizeMoney = ExbTools.getExbConfigPrize(ExbConstant.PLAY_TYPE_X5, ExbConstant.X5_MODE_RENXUAN);
		} 
		
		int totalZjMoney = prizeMoney * times;//当前号码中总的中奖注数		
		RecordZjInfo info = new RecordZjInfo();
		info.setZjMoneyOneDetail(totalZjMoney);
		info.setZjZhushuOneDetail(times);
		info.setZjRule(rule);
		info.setMoneyOfPlayType(prizeMoney);
		return info;
	}
	/**
	 * 查询PId为该ID 并且 have_zhuitou != 1的记录的所有的子记录
	 * @param tzId 中奖期的投注ID
	 * @param issueNum 中奖期的发行期号
	 * @param userId 用户id
	 * @param isFirst 是否是第一条投注
	 * @param idList 存放要删除的中奖记录ID
	 * @param moneyMap 存放要返还的用户及相应的金额
	 * @return
	 */
	public void getChildIssueIdList(String tzId,String issueNum,String userId,boolean isFirst,List<String> idList,Map<String,Integer> moneyMap){
		String sql = "";
		if(isFirst){
			sql = "select id,user_id,touzhu_money from exb_tz_info where user_id='"+userId+"' and parent_id='"+tzId+"' and issue_num != '"+issueNum+"' and have_zt_flag='0'";
		}else{
			sql = "select id,user_id,touzhu_money from exb_tz_info where  user_id='"+userId+"' and parent_id='"+tzId + "' and issue_num != '"+issueNum+"' and ((issue_num+0) > ('"+issueNum+"'+0))";
		}
		logger.info("快乐十分:查询中奖期号的投注Id sql="+sql);
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{			 
			while(crs.next()){
				String tempId = crs.getString("id");
				String tempUserId = crs.getString("user_id");
				int tzMoney = crs.getInt("touzhu_money");
				
				idList.add(tempId);
				Integer tempTzMoney = moneyMap.get(tempUserId);
				if(tempTzMoney ==null){
					moneyMap.put(tempUserId,tzMoney);
				}else{
					tempTzMoney  = tempTzMoney.intValue() + tzMoney;
					moneyMap.put(tempUserId,tempTzMoney);
				}
			}
		}catch(Exception e){
			logger.error("查询待删除中奖信息错误",e);
		}
	}
	/**
	 * 根据发行期号，删除中奖号码信息，同时删除数据库中已存在的用户的中奖信息
	 * @param issueNum
	 * @return
	 */
	public boolean delZhongjianghaoma(String issueNum){
		boolean flag = false;
		try{
			flag = dao.delZhongjianghaoma(issueNum);
		}catch(DAOException e){
			logger.error("快乐十分删除中奖号码错误：" ,e);
		}
		return flag;		
	}
	/**
	 * 判断List中的每个号码在字符串中出现的次数，如果List中的号码与给定的字符串相等，则不进行判断
	 * @param list 包含整型数组，包含
	 * @param numDetail 投注的号码分解
	 * @param giveNum 给定的号码，指4中4,3中3，2中2
	 * @return
	 */
	private int howTimesInNum(List list,String numDetail,String giveNum){
		int times = 0;
		for (int i = 0; i < list.size(); i++) {
			int[] tempIntAry = (int[]) list.get(i);
			String tempNum = "";
			for(int m : tempIntAry){
				if(tempNum.equals("")){
					tempNum = String.valueOf(m);
				}else{
					tempNum = tempNum + "," +  String.valueOf(m);
				}
			}
			if(!tempNum.equals(giveNum)){
				int intCount = JStringToolkit.getSubNum(numDetail,"|"+tempNum+"|");
				times = times + intCount;
			}
		}
		return times;
	}
	/**
	 * 删除指定用户某一个中奖号码信息，同时删除数据库中已存在的用户的投注信息
	 * 同时将用户的账户中扣除相应的奖金（目前先不做)
	 * @param zjId
	 * @
	 * @return
	 */
	public boolean delUserZjInfo(String tzId,String userId){
		boolean flag = false;
		try{
			flag = exbZjInfoDao.delUserZjInfo(tzId, userId);
		}catch(DAOException e){
			logger.error("快乐十分:删除指定用户中奖信息错误：" ,e);
		}
		return flag;
	}
	/**
	 * 查询指定用户总的投注注数及投注金额
	 * @param issueNum
	 * @return
	 */
	public ExbTzInfo getUserTotalTzInfo(String issueNum,String userId){
		ExbTzInfo info = new ExbTzInfo();
		try{
			info = exbTzInfoDao.getUserTotalTzInfo(issueNum,userId);
		}catch(Exception e){
			logger.error("快乐十分:查询指定用户投注信息错误：",e);
		}
		return info;
	}
	/**
	 * 查询指定用户总的中奖注数及中奖金额
	 * @param issueNum
	 * @return
	 */
	public ExbZjInfo getUserTotalZjInfo(String issueNum,String userId){
		ExbZjInfo info = new ExbZjInfo();
		try{
			info = exbZjInfoDao.getUserTotalZjInfo(null,userId);
		}catch(DAOException e){
			logger.error("快乐十分:查询指定用户" + userId + "中奖信息错误：" + e.toString());
		}
		return info;
	}
	/**
	 * 针对任选六，任选七，任选8
	 * @param zjNum开奖号码，格式"1,2,3,4,5",五位数,以逗号分隔
	 * @param playType 玩法名称，
	 * @param num 个数(玩法与
	 * @return
	 */
	public List<String> getInsertString(String zjNum,int playNumCount){
		if(zjNum == null || zjNum.equals("")){
			return null;
		}
		List<String> strList  = new ArrayList<String>();
		String allStr = "1,2,3,4,5,6,7,8,9,10,11";
		int[] intZjNumAry =  JStringToolkit.splitStrToInt(zjNum, ",");
		String[] allStrAry = JStringToolkit.splitString(allStr, ",");
		
		//计算未用到的数字
		String unUsedStr = "";//6个未用到的数字(11个数字中，除了5个中奖号码外的数字)
		for(String temp : allStrAry){
			if((","+zjNum+",").indexOf("," + temp+",") == -1){
				unUsedStr = unUsedStr + temp + ",";
			}
		}
			
		if(!unUsedStr.equals("")){
			int[] intAry = JStringToolkit.splitStrToInt(unUsedStr, ",");
			//根据玩法类型，对未用到的号码进行组合
			List list  = new ArrayList();
			int num = playNumCount - 5;//与5个中奖数的差值
			ZuheArith za = new ZuheArith();
			list = za.combine(intAry, num);
			
			// 整理结果
			for (int i = 0; i < list.size(); i++) {
				int[] t = (int[]) list.get(i);
				int[] xx = new int[t.length + intZjNumAry.length];
				for(int j = 0; j < t.length; j++){
					xx[j] = t[j];
				}
				for(int k = t.length; k < xx.length; k++){
					xx[k] = intZjNumAry[k-t.length];
				}
				JStringToolkit.intOrder(xx, 1);
				String temp = "";
				for(int nn : xx){
					temp = temp + nn + ",";
				}
				if(!temp.equals("")){
					temp = temp.substring(0,temp.length()-1);
				}
				strList.add(temp);
				logger.info("ren xuan 7="+temp);
			}
			return strList;
		}
		return null;
		
	}
	private class RecordZjInfo{
		private String zjRule;
		private Integer zjZhushuOneDetail;
		private Integer zjMoneyOneDetail;//在一个投注明细中的的中奖金额，不计算倍投
		private Integer moneyOfPlayType;//系统配置中的每个类型的中奖金额
		public String getZjRule() {
			return zjRule;
		}

		 

		public Integer getMoneyOfPlayType() {
			return moneyOfPlayType;
		}



		public void setMoneyOfPlayType(Integer moneyOfPlayType) {
			this.moneyOfPlayType = moneyOfPlayType;
		}



		public void setZjRule(String zjRule) {
			this.zjRule = zjRule;
		}

		public Integer getZjZhushuOneDetail() {
			return zjZhushuOneDetail;
		}

		public void setZjZhushuOneDetail(Integer zjZhushuOneDetail) {
			this.zjZhushuOneDetail = zjZhushuOneDetail;
		}

		public Integer getZjMoneyOneDetail() {
			return zjMoneyOneDetail;
		}

		public void setZjMoneyOneDetail(Integer zjMoneyOneDetail) {
			this.zjMoneyOneDetail = zjMoneyOneDetail;
		}

		 
		
	}
}
