package com.onmyway.sxw.manage.service;

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
import com.onmyway.init.SxwLimitInfoInit;
import com.onmyway.sxw.manage.dao.ISxwBaseInfoConfigDao;
import com.onmyway.sxw.manage.dao.ISxwMoneyLimitDao;
import com.onmyway.sxw.manage.dao.ISxwZjInfoDao;
import com.onmyway.sxw.manage.dao.ISxwZjNumConfigDao;
import com.onmyway.sxw.manage.model.SscZjNum;
import com.onmyway.sxw.manage.model.SxwBaseInfoConfig;
import com.onmyway.sxw.manage.model.SxwMoneyAndLimit;
import com.onmyway.sxw.manage.model.SxwZjInfo;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;
import com.onmyway.sxw.play.dao.ISxwTzInfoDao;
import com.onmyway.sxw.play.model.SxwTzInfo;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:12选5-后台管理
 * @Description: 
 * @Create on: Aug 14, 2010 5:30:25 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwManageServiceImpl implements ISxwManageService {	

	private Log logger = LogFactory.getLog(SxwManageServiceImpl.class);
	private ISxwZjNumConfigDao dao;
	private ISxwBaseInfoConfigDao sxwBaseInfoConfigDao;
	private ISxwMoneyLimitDao sxwMoneyLimitDao;
	private ISxwZjInfoDao sxwZjInfoDao;
	private ISxwTzInfoDao sxwTzInfoDao;

	DBTool dbTool = DBAccess.getDBTool();
	/**中奖信息****/
	public ISxwZjInfoDao getSxwZjInfoDao() {
		return sxwZjInfoDao;
	}

	public void setSxwZjInfoDao(ISxwZjInfoDao sxwZjInfoDao) {
		this.sxwZjInfoDao = sxwZjInfoDao;
	}
	
	public ISxwTzInfoDao getSxwTzInfoDao() {
		return sxwTzInfoDao;
	}

	public void setSxwTzInfoDao(ISxwTzInfoDao sxwTzInfoDao) {
		this.sxwTzInfoDao = sxwTzInfoDao;
	}

	/**
	 * 奖金设置及限制倍数
	 * @return
	 */
	public ISxwMoneyLimitDao getSxwMoneyLimitDao() {
		return sxwMoneyLimitDao;
	}

	public void setSxwMoneyLimitDao(ISxwMoneyLimitDao sxwMoneyLimitDao) {
		this.sxwMoneyLimitDao = sxwMoneyLimitDao;
	}

	/**
	 * 中奖号码DAO
	 * @return
	 */
	public ISxwZjNumConfigDao getSxwNumConfigDao() {
		return dao;
	}

	public void setSxwNumConfigDao(ISxwZjNumConfigDao dao) {
		this.dao = dao;
	}
	/**
	 * 基本信息dao
	 * @return
	 */
	public ISxwBaseInfoConfigDao getSxwBaseInfoConfigDao() {
		return sxwBaseInfoConfigDao;
	}
	public void setSxwBaseInfoConfigDao(ISxwBaseInfoConfigDao baseInfoDao) {
		this.sxwBaseInfoConfigDao = baseInfoDao;
	}
	
	public SxwZjNumConfig get(String id) throws AppException {
		// TODO Auto-generated method stub
		
		return null;
	}

	public void save(SxwZjNumConfig config) throws AppException {
		try{
			dao.save(config);
		}catch(DAOException e){
			logger.error("保存中奖号码错误"+e.toString());
		}
		
	}

	public void update(SxwZjNumConfig config) throws AppException {
		// TODO Auto-generated method stub
		
	}
	public SxwBaseInfoConfig getBaseInfo(String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * 得到当天的基本配置信息
	 * @param date
	 * @return
	 */
	public SxwBaseInfoConfig getConfigInfoOfToday(){
		SxwBaseInfoConfig info = new SxwBaseInfoConfig();
		try{
			String today = JDateToolkit.getNowDate2();
			List<SxwBaseInfoConfig> list = sxwBaseInfoConfigDao.getConfigInfoOfDay(today);
			if(list != null && !list.isEmpty()){
				info = list.get(0);
			}
		}catch(Exception e){
			logger.error("查询12选5当天配置信息错误！"+e.toString());
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
	public SxwZjNumConfig getCurrentZjNum() throws AppException{
		SxwZjNumConfig info = new SxwZjNumConfig();
		try{
			info =  dao.getCurrentZjNum();
		}catch(DAOException ee){
			logger.error("查询中奖信息异常："+ee.toString());
			info.setWan("0");
			info.setQian("0");
			info.setBai("0");
			info.setShi("0");
			info.setGe("0");
		}
		return info;
	}
	/**
	 * 查询所有的中奖信息
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SxwZjNumConfig> getAllZjNum() throws AppException{
		List<SxwZjNumConfig> list = new ArrayList<SxwZjNumConfig>();
		try{
			list = dao.getAllZjNum();
		}catch(Exception e){
			logger.error("查询12x5信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 得到给定期号的中奖信息
	 * @return
	 * @throws AppException
	 */
	public SxwZjNumConfig getZjNumConfig(String issueNum) throws AppException{
		SxwZjNumConfig info = new SxwZjNumConfig();
		try{
			info =  dao.getZjNum(issueNum);
		}catch(DAOException ee){
			logger.error("查询中奖信息异常："+ee.toString());
			info.setIssueNum("-");
			info.setWan("-");
			info.setQian("-");
			info.setBai("-");
			info.setShi("-");
			info.setGe("-");
		}
		return info;
	}
	/**
	 * 基本信息保存
	 */
	public void saveBaseInfo(SxwBaseInfoConfig info) throws AppException {
		try{
			sxwBaseInfoConfigDao.save(info);
		}catch(DAOException e){
			logger.error("保存中奖号码错误"+e.toString());
		}
		
	}

	public void updateBaseInfo(SxwBaseInfoConfig info) throws AppException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 奖金及限号信息设置
	 * @param info
	 * @throws AppException
	 */
	public void saveLimitInfo(SxwMoneyAndLimit info) throws AppException{
		boolean flag = true;
		try{
			//先将该种玩法先前的限号信息都置为无效
			 flag = sxwMoneyLimitDao.updateLimitByRule("sxw", info.getRule());
			//再保持现在的限号信息
			if(flag){
				sxwMoneyLimitDao.save(info);
				//同时更新系统中的限号配置信息
				SxwLimitInfoInit.sxwLimitMap = getLimitInfoMap();
				SxwLimitInfoInit.sxwPrizeMap = getPrizeInfoMap();			 
			}
			
		}catch(Exception e){
			flag = false;
			logger.error("保存限号信息错误"+e.toString());
		}
		
	}
	
	/**
	 * 将限号信息放到map中
	 * @return
	 */
	public Map<String,String> getLimitInfoMap(){
		Map<String,String> map = new HashMap<String,String>();
		List<SxwMoneyAndLimit> list =  sxwMoneyLimitDao.getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(SxwMoneyAndLimit info : list){
				String rule = info.getRule();
				String limit = info.getLimitOneIssue();
				logger.info("12x5:rule="+rule+",limit="+limit);
				map.put(rule, limit);
			}
		}
		return map;
	}
	/**
	 * 将奖金信息放到map中
	 * @return
	 */
	public Map<String,String> getPrizeInfoMap(){
		Map<String,String> map = new HashMap<String,String>();
		List<SxwMoneyAndLimit> list =  sxwMoneyLimitDao.getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(SxwMoneyAndLimit info : list){
				String rule = info.getRule();
				String money = info.getMoney();
				logger.info("12x5:rule="+rule+",prize="+money);
				map.put(rule, money);
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
	public List<SxwZjNumConfig> getAllZjNum(String currentIssue) throws AppException{
		List<SxwZjNumConfig> list = new ArrayList<SxwZjNumConfig>();
		try{
			list = dao.getAllZjNum(currentIssue);
		}catch(Exception e){
			logger.error("查询12x5信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SxwZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws AppException{
		List<SxwZjNumConfig> list = new ArrayList<SxwZjNumConfig>();
		try{
			list = dao.getZjNumBetweenDate(beginDate,endDate,currentIssue);
		}catch(Exception e){
			logger.error("查询12x5信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws AppException
	 */
	public SxwZjNumConfig getCurrentZjNum(String currentIssue) throws AppException{
		SxwZjNumConfig info = new SxwZjNumConfig();
		try{
			info =  dao.getCurrentZjNum(currentIssue);
		}catch(DAOException ee){
			logger.error("查询中奖信息异常："+ee.toString());
			info.setWan("-");
			info.setQian("-");
			info.setBai("-");
			info.setShi("-");
			info.setGe("-");
		}
		return info;
	}
	
	/**
	 * 中奖信息保存，同时从数据库中查询已经投注的号码的中奖情况，并将其保存到数据中
	 * @param config
	 * @return
	 * @throws AppException
	 */
	public boolean saveZjInfo(SxwZjNumConfig config) throws AppException {
		boolean flag = true;
		String isResetIssueInfo = config.getIsResetIssueInfo();//是否重新设置中奖号码 1：重新设置；0：不重新设置
		
		//定义容器
		List<SxwZjInfo> zjInfolist = new ArrayList<SxwZjInfo>();//存放中奖的记录的容器
		List<String> idDelList = new ArrayList<String>();//存放要删除的中奖记录ID
		Map<String,Integer> moneyMap = new HashMap<String,Integer>();//存放要返还的用户及相应的金额
		
		String issueNum = config.getIssueNum();

		if(isResetIssueInfo.equals("1")){//先删除原有已设定的期号的信息
			try{
				dao.deleteZjNum(issueNum);
//				logger.warn("删除"+issueNum + "期原有中奖号码：" + temp);
			}catch(DAOException ee){
				logger.error("删除"+issueNum + "期原有中奖号码异常:" + ee.toString());
				throw new AppException("删除"+issueNum + "期原有中奖号码异常:" + ee.toString());
			} 
		}
		try{
			//重新保存
			dao.save(config);
		}catch(DAOException ee){
			logger.error("保存中奖号码"+issueNum + "异常:" + ee.toString());
			throw new AppException("保存中奖号码"+issueNum + "异常:" + ee.toString());
		} 	
		String zjNum = config.getWan()+","+config.getQian()+","+config.getBai()+","+config.getShi()+","+config.getGe();
			
		setZjInfo(zjInfolist,idDelList,moneyMap,issueNum,zjNum);
 
		try{
			dao.jdbcSaveListInfo(zjInfolist, idDelList,moneyMap);//如果不抛出异常，就是保存成功
		}catch(DAOException ee){
			logger.error("中奖信息保存异常:" + ee.toString());
			throw new AppException("中奖信息保存异常:" + ee.toString());
		} 
		return flag;
	}
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SxwZjInfo> getZjInfo(String issueNum,String userName) {
		List<SxwZjInfo> list = new ArrayList<SxwZjInfo>();
		List<SxwZjInfo> tempList = new ArrayList<SxwZjInfo>();
		
		try{	
			tempList = sxwZjInfoDao.getZjInfo(issueNum, userName);
			//将里面的字母转化为中文解释
			if(tempList != null){
				for(SxwZjInfo info : tempList){
					String playType = info.getPlayType();//玩法类型
					String playMode = info.getPlayMode();//玩法模式
					String zjRule = info.getZjType();//中奖规则
					
					String tempPlayType = ConvertToolkit.sxwPlayTypeConvert(playType);
					String tempPlayMode = ConvertToolkit.sxwPlayModeConvert(playMode);
					String tempZjRule = "";
					String[] aryRule = JStringToolkit.splitString(zjRule,";");
					for(String sstr : aryRule){
						String[] aryZj = JStringToolkit.splitString(sstr,":");
						String ttype = aryZj[0];
						String ttzhushu = aryZj[1];
						String tempRule = ConvertToolkit.sxwZjTypeConvert(ttype);
						
						tempZjRule = tempZjRule + tempRule + ":" + ttzhushu +"注;";
					}
					info.setPlayType(tempPlayType);
					info.setPlayMode(tempPlayMode);
					info.setZjType(tempZjRule);
					
					
					list.add(info);
				}
			}
		}catch(DAOException ee){
			logger.error("中奖信息保存异常:" + ee.toString());
		}
		return list;
	}
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SxwZjInfo> getZjInfo(String issueNum,String userName,String userId) {
		List<SxwZjInfo> list = new ArrayList<SxwZjInfo>();
		List<SxwZjInfo> tempList = new ArrayList<SxwZjInfo>();
		
		try{	
			tempList =  sxwZjInfoDao.getZjInfo(issueNum, userName,userId);
			//将里面的字母转化为中文解释
			if(tempList != null){
				for(SxwZjInfo info : tempList){
					String playType = info.getPlayType();//玩法类型
					String playMode = info.getPlayMode();//玩法模式
					String zjRule = info.getZjType();//中奖规则
					
					String tempPlayType = ConvertToolkit.sxwPlayTypeConvert(playType);
					String tempPlayMode = ConvertToolkit.sxwPlayModeConvert(playMode);
					String tempZjRule = "";
					String[] aryRule = JStringToolkit.splitString(zjRule,";");
					if(aryRule != null && !aryRule.equals("")){
						for(String sstr : aryRule){
							String[] aryZj = JStringToolkit.splitString(sstr,":");
							String ttype = aryZj[0];
							String ttzhushu = aryZj[1];
							String tempRule = ConvertToolkit.sxwZjTypeConvert(ttype);
							
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
//			list = sxwZjInfoDao.getZjInfo(issueNum, userName,userId);
//			
		}catch(DAOException ee){
			logger.error("中奖信息查询异常:" + ee.toString());
		}
		return list;
	}
	/**
	 * 先将表里已存在的相应期号的中奖号码信息删除，然后再重新进行中奖号码的设置
	 * V2.0 任选2，任选3，任选4，规则适用新的规则，但是算法使用之前的5中2，5中3，5中4的规则
	 */
	public void setZjInfo(List<SxwZjInfo> zjInfolist,List<String> idDelList,Map<String,Integer> moneyMap,String issueNum, String zjNum) throws AppException{
		// TODO Auto-generated method stub
		//分解号码
		if(zjNum == null || zjNum.equals("")){
			throw new AppException("11x5 当前的中奖号码不能为空，请重新设置");
		}
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		int len = aryZjNum.length;
		if(len < 5){
			throw new AppException("11x5 开奖号码设置有误!请重新设置");
			
		}
//		String delSql = "delete from sxw_zj_info where issue_num='" + issueNum + "'";
//		boolean delFlag = dbTool.executeSql(ConstantSymbol.dbSource, delSql);
		try{
			sxwZjInfoDao.deleteHistoryZjInfo(issueNum);
		}catch(DAOException e){
			throw new AppException("该期的历史中奖信息删除失败!"+e.toString());
		}
		//分别设置整型和数值型各个位置上的值，主要是为了写着方便,记着好记
		int intWan = Integer.parseInt(aryZjNum[0]);//万位
		int intQian = Integer.parseInt(aryZjNum[1]);//千位
		int intBai = Integer.parseInt(aryZjNum[2]);//百位
		int intShi = Integer.parseInt(aryZjNum[3]);//十位
		int intGe = Integer.parseInt(aryZjNum[4]);//个位
		
		String strWan = aryZjNum[0];//万位
		String strQian = aryZjNum[1];//千位
		String strBai = aryZjNum[2];//百位
		String strShi = aryZjNum[3];//十位
		String strGe = aryZjNum[4];//个位
 
		int[] tempAry = {intWan,intQian,intBai,intShi,intGe};
		int[] intAry = JStringToolkit.intOrder(tempAry, 1);//将开奖号码按从小到大排列
		ZuheArith za = new ZuheArith();
		//中5
//		String wuzhong5 = strWan + "," + strQian +  "," + strBai +  "," + strShi +  "," + strGe;//任选5
		String wuzhong5 = JStringToolkit.strSort(new String[]{strWan ,strQian, strBai, strShi,strGe},",");//任选5
		
		//中4
//		String sizhong4 = strWan + "," + strQian +  "," + strBai +  "," + strShi;//任选4中4
		//String sizhong4 = JStringToolkit.strSort(new String[]{strWan ,strQian, strBai, strShi},",");
		List sizhong4List = za.combine(intAry, 4);//任选四中4：投注的4个号码与当期摇出的5个号码中的任4个号码相同，则中奖。
		
		//中3
		List sanzhong3List = za.combine(intAry, 3);//任选3中3，//投注的3个号码与当期摇出的5个号码中的任3个号码相同，则中奖
		String sanzhong3Qian3Zu = JStringToolkit.strSort(new String[]{strWan ,strQian, strBai},",");//选前三组选:投注的3个号码与当期顺序摇出的5个号码中的前3个号码相同，则中奖
		String sanzhong3Qian3Zhi = strWan + "," + strQian + "," + strBai;//选3直选前3:投注的3个号码与当期顺序摇出的5个号码中的前3个号码相同且顺序一致，则中奖。
		
		//中2
		List erzhong2List = za.combine(intAry, 2);//任选2中2，以便对2中2进行中奖判断			
		String erzhong2Qian2Zu = JStringToolkit.strSort(new String[]{strWan ,strQian},",");//选2前2组选
		String erzhong2Qian2Zhi = strWan + "," + strQian;//选2直选前2,
		
		//中1
		String yizhong1 = strWan;//任选1中1
		
		//---------------------------任选8 begin-----------------------------------------------------------------
		//任选8
		List<String> rx8List = getInsertString(zjNum,8);
		String sql8Zhong5 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='renxuan8' ";
		String tempSql85 = "";//  
		for(String tempRx8 : rx8List){
			tempSql85 = tempSql85 + " or t.num_detail like  '%|" + tempRx8 +"|%'" ;
		}
		if(!tempSql85.equals("")){
			sql8Zhong5 = sql8Zhong5 + " and (1 = 2 " + tempSql85 + " )";
		}
		//---------------------------任选7 begin-----------------------------------------------------------------
		//任选7
		List<String> rx7List = getInsertString(zjNum,7);
		String sql7Zhong5 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='renxuan7' ";
		String tempSql75 = "";//  
		for(String tempRx7 : rx7List){
			tempSql75 = tempSql75 + " or t.num_detail like  '%|" + tempRx7 +"|%'" ;
		}
		if(!tempSql75.equals("")){
			sql7Zhong5 = sql7Zhong5 + " and (1 = 2 " + tempSql75 + " )";
		}
		//---------------------------任选6 begin-----------------------------------------------------------------
		//任选6
		List<String> rx6List = getInsertString(zjNum,6);
		String sql6Zhong5 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='renxuan6' ";
		String tempSql65 = "";//sql6Zhong5 = sql6Zhong5 + " 
		for(String tempRx6 : rx6List){
			tempSql65 = tempSql65 + " or t.num_detail like  '%|" + tempRx6 +"|%'" ;
		}
		if(!tempSql65.equals("")){
			sql6Zhong5 = sql6Zhong5 + " and (1 = 2 " + tempSql65 + " )";
		}
		//---------------------------任选5 begin-----------------------------------------------------------------
		//任选5中5
		String sqlWuzhong5 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='renxuan5'  and t.num_detail like  '%|" + wuzhong5+"|%'";
		//---------------------------任选4 begin-----------------------------------------------------------------
		//任选4中4
		//2011-04-23 任选4中4的规则改变，变成“投注的4个号码与当期摇出的4个号码中的任4个号码相同，即中奖”，与原来的5中4规则相同，现废弃此sql
//			String sqlSizhong4 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
//						   + "' and t.play_type='renxuan4'  and t.num_detail like  '%|" + sizhong4+"|%'";
		//任选4中4
		String sqlSizhong4 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='renxuan4' ";
		// 任选4中4 组合条件
		String tempSql4 = "";
		for (int i = 0; i < sizhong4List.size(); i++) {
			int[] tempIntAry = (int[]) sizhong4List.get(i);
			String tempNum = "";
			for(int m : tempIntAry){
				if(tempNum.equals("")){
					tempNum = String.valueOf(m);
				}else{
					tempNum = tempNum + "," +  String.valueOf(m);
				}
			}
			if(tempSql4.equals("")){
				tempSql4 = " t.num_detail like '%|" + tempNum+"|%' ";
			}else{
				tempSql4 = tempSql4 + " or " + " t.num_detail like '%|" + tempNum+"|%' ";
			}
		}
		if(!tempSql4.equals("")){
			sqlSizhong4 = sqlSizhong4 + " and (" + tempSql4 + ")";
		}
		//---------------------------任选3 begin----------------------------------------------------------------- 
		//任选3中3
		//2011-04-23 任选3中3的规则改变，变成“投注的3个号码与当期摇出的5个号码中的任3个号码相同，即中奖”，与原来的5中3规则相同，现废弃此sql
//			String sqlSanzhong3 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
//						   + "' and t.play_type='renxuan3'  and play_mode not in('zhiqian3','zuqian3')  and t.num_detail like  '%|" + sanzhong3+"|%'";
//			//任选3,直选前3及组选前3 2011-10-04 废弃
//			String sqlSanzhong3Qian3 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
//						   + "' and t.play_type='renxuan3'  and play_mode in('zhiqian3','zuqian3')  and t.num_detail like  '%|" + sanzhong3Qian3+"|%'";
	
		//选前三直选 SQL
		String sqlSanzhong3Qian3Zhi = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan3'  and play_mode='zhiqian3'  and t.num_detail like  '%|" + sanzhong3Qian3Zhi+"|%'";
		
		//选前三组选 SQL；包括量(选前三组选 选前三组选胆拖 )两种玩法 
		String sqlSanzhong3Qian3Zu = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan3'  and play_mode in ('zuqian3','zuqian3dantuo')  and t.num_detail like  '%|" + sanzhong3Qian3Zu+"|%'";

//			//任选三中3  2011-10-04 废弃
//			//2011-04-23 和之前的3中3规则相同，现采用此算法
//			String sqlWuzhong3 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
//						   + "' and t.play_type='renxuan3'  and play_mode not in('zhiqian3','zuqian3') ";
		
		//任选三中3:只有复式，胆拖两种玩法
		String sqlSanzhong3 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan3'  and play_mode  in('dantuo','fushi') ";
		// 任选三中3 组合条件
		String tempSql3 = "";
		for (int i = 0; i < sanzhong3List.size(); i++) {
			int[] tempIntAry = (int[]) sanzhong3List.get(i);
			String tempNum = "";
			for(int m : tempIntAry){
				if(tempNum.equals("")){
					tempNum = String.valueOf(m);
				}else{
					tempNum = tempNum + "," +  String.valueOf(m);
				}
			}
			if(tempSql3.equals("")){
				tempSql3 = " t.num_detail like '%|" + tempNum+"|%' ";
			}else{
				tempSql3 = tempSql3 + " or " + " t.num_detail like '%|" + tempNum+"|%' ";
			}
		}
		if(!tempSql3.equals("")){
			sqlSanzhong3 = sqlSanzhong3 + " and (" + tempSql3 + ")";
		}
		//---------------------------任选2 begin-----------------------------------------------------------------
		//任选2中2,
		//2011-04-23 任选2中2的规则改变，变成“投注的2个号码与当期摇出的5个号码中的任2个号码相同，即中奖”，与原来的5中2规则相同，现废弃此sql
//			String sqlErzhong2 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
//						   + "' and t.play_type='renxuan2' and play_mode not in('zhiqian2','zuqian2') and t.num_detail like  '%|" + erzhong2+"|%'";

//			任选二中2：投注的2个号码与当期摇出的5个号码中的任2个号码相同，则中奖。
//			选前二组选：投注的2个号码与当期顺序摇出的5个号码中的前2个号码相同，则中奖。
//			选前二直选：投
		
		//选前二直选：
		String sqlErzhong2Qian2Zhi = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='renxuan2' and play_mode ='zhiqian2' and t.num_detail like  '%|" + erzhong2Qian2Zhi+"|%'";
		//选前二组选：
		String sqlErzhong2Qian2Zu = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan2' and play_mode in('zuqian2','zuqian2dantuo') and t.num_detail like  '%|" + erzhong2Qian2Zu+"|%'";

//			//任选二中2
//			String sqlWuzhong2 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
//						   + "' and t.play_type='renxuan2' and play_mode not in('zhiqian2','zuqian2','zuqian2dantuo') ";
		//任选二中2 包括(复式 胆拖)两种玩法
		String sqlErzhong2 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan2' and play_mode in('dantuo','fushi')";
		// 任选二中2 组合条件
		String tempSql2 = "";
		for (int i = 0; i < erzhong2List.size(); i++) {
			int[] tempIntAry = (int[]) erzhong2List.get(i);
			String tempNum = "";
			for(int m : tempIntAry){
				if(tempNum.equals("")){
					tempNum = String.valueOf(m);
				}else{
					tempNum = tempNum + "," +  String.valueOf(m);
				}
			}
			if(tempSql2.equals("")){
				tempSql2 = " t.num_detail like '%|" + tempNum+"|%' ";
			}else{
				tempSql2 = tempSql2 + " or " + " t.num_detail like '%|" + tempNum+"|%' ";
			}
		}
		if(!tempSql2.equals("")){
			sqlErzhong2 = sqlErzhong2 + " and (" + tempSql2 + ")";
		}
		//---------------------------任选1 begin-----------------------------------------------------------------
		//任选1中1
		String sqlYizhong1 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='renxuan1'  and t.num_detail like  '%|" + yizhong1+"|%'";
	
		String tempSql = "";
		tempSql = tempSql + sql8Zhong5 ;
		tempSql = tempSql + " union " + sql7Zhong5 ;
		tempSql = tempSql + " union " + sql6Zhong5 ;
//			tempSql = tempSql + " union " + sqlWuzhong5 + " union " + sqlSizhong4 + " union " + sqlWuzhong4 ;
		tempSql = tempSql + " union " + sqlWuzhong5;
		tempSql = tempSql + " union " + sqlSizhong4 ;
//			tempSql = tempSql + " union " + sqlSanzhong3 + " union " + sqlSanzhong3Qian3  + " union " + sqlWuzhong3;
		tempSql = tempSql + " union " + sqlSanzhong3Qian3Zhi  + " union " + sqlSanzhong3Qian3Zu + " union " + sqlSanzhong3;
//			tempSql = tempSql + " union " + sqlErzhong2 + " union " + sqlErzhong2Qian2 + " union " + sqlWuzhong2 + " union " + sqlSanzhong1;
		tempSql = tempSql + " union " + sqlErzhong2Qian2Zhi +  " union " + sqlErzhong2Qian2Zu + " union " + sqlErzhong2;
		tempSql = tempSql + " union " + sqlYizhong1;
		//logger.info("12选5:保存中奖号码:"+tempSql);
		
		String sql = "select a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,a.touzhu_num,a.parent_issue_num,a.parent_id,a.is_zhuitou,a.have_zt_flag,b.* from sxw_tz_info a,(" + tempSql + ")b where a.id=b.p_id";
		logger.info("12选5:保存中奖号码:"+sql);
		
		
		List<SscZjNum> zjNumList = new ArrayList<SscZjNum>();
		
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{
			Map<String,String> prizeMap = SxwLimitInfoInit.getSxwPrizeMap();
			
			
			while(crs.next()){
//					t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou 
//					a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,
				SxwZjInfo zjInfo = new SxwZjInfo();
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
//					String cPlayType = crs.getString("c_play_type");//子表中的play_type
				String cPlayMode = crs.getString("c_play_mode");//子表中的play_mode
				String tempNumDetail = crs.getString("num_detail");//子表中分解后的投注号码
				

				String tempParentIssueNum = crs.getString("parent_issue_num");
				
				//判断中奖后删除其余的投注信息用的
				String tempHaveZhuitou = crs.getString("have_zt_flag");//是否有追投
				String tempParentId = crs.getString("parent_id");//info表中的投注父ID
				String tempInfoIsZhuitou = crs.getString("is_zhuitou");//info表中的是否是追投
				
				SscZjNum zjNumInfo = new SscZjNum();
				zjNumInfo.setId(tempId);
				zjNumInfo.setIssueNum(issueNum);
				zjNumInfo.setParentIssueNum(tempParentIssueNum);
				zjNumInfo.setUserId(tempUserId);
				zjNumInfo.setUserName(tempUserName);
				zjNumInfo.setTzId(tempTzId);//投注ID
				zjNumInfo.setHaveZhuitou(tempHaveZhuitou);
				zjNumInfo.setIsZhuitou(tempInfoIsZhuitou);
				zjNumInfo.setParentId(tempParentId);
				
				zjNumList.add(zjNumInfo);
				
			
				//判断中奖注数，根据玩法及相应的投注号码，查询中奖的注数。判断中奖号码在相应的投注号码出现的次数即为中奖的注数
				int intZjZhushu = 0;//中奖的注数(如投：12345,12345，则注数是2
				int intTzTimes = Integer.parseInt(tempTzTimes);//得到投注的倍数
				int intTotalZjZhushu = 0;//总的中奖注数=单注中奖注数*倍数
				int intTotalMoney = 0;//总的中奖金额
				String zjRule = "";
				
				//根据玩法,得到相应的奖金
				String tempPrizeMoneyKey = tempPlayType;
				
				//任选8
				if(tempPlayType.equals(ConstantSymbol.RX8)){
					
					//计算一条记录中的中奖数
					int zjCountInOneRecord = 0;
					for(String tempRx8 : rx8List){
						zjCountInOneRecord +=  JStringToolkit.getSubNum(tempNumDetail,"|"+tempRx8+"|");
					}
					//一条记录中的中奖金额;
					int zjMoneyInOneRecord = Integer.parseInt(prizeMap.get(ConstantSymbol.RX8))*zjCountInOneRecord;
					
					intZjZhushu = zjCountInOneRecord;
					intTotalZjZhushu = intZjZhushu * intTzTimes;//一条记录中总的中奖注数
					intTotalMoney = zjMoneyInOneRecord * intTzTimes;//一条记录中总的中奖金额
					
					//中奖规则，备用
					if(intZjZhushu > 0){
						zjRule = ConstantSymbol.RX8 + ":" + intZjZhushu + ";";//格式为"规则:中奖注数"
					}
				}
				//任选7
				if(tempPlayType.equals(ConstantSymbol.RX7)){
					//计算一条记录中的中奖数
					int zjCountInOneRecord = 0;
					for(String tempRx7 : rx7List){
						zjCountInOneRecord +=  JStringToolkit.getSubNum(tempNumDetail,"|"+tempRx7+"|");
					}
					//一条记录中的中奖金额;
					int zjMoneyInOneRecord = Integer.parseInt(prizeMap.get(ConstantSymbol.RX7))*zjCountInOneRecord;
					
					intZjZhushu = zjCountInOneRecord;
					intTotalZjZhushu = intZjZhushu * intTzTimes;//一条记录总的中奖注数
					intTotalMoney = zjMoneyInOneRecord * intTzTimes;//一条记录总的中奖金额
					
					//中奖规则，备用
					if(intZjZhushu > 0){
						zjRule = ConstantSymbol.RX7 + ":" + intZjZhushu + ";";//格式为"规则:中奖注数"
					}
				}
				
				//任选6
				if(tempPlayType.equals(ConstantSymbol.RX6)){
					
					//计算一条记录中的中奖数
					int zjCountInOneRecord = 0;
					for(String tempRx6 : rx6List){
						zjCountInOneRecord +=  JStringToolkit.getSubNum(tempNumDetail,"|"+tempRx6+"|");
					}
					//一条记录中的中奖金额;
					int zjMoneyInOneRecord = Integer.parseInt(prizeMap.get(ConstantSymbol.RX6))*zjCountInOneRecord;
					
					intZjZhushu = zjCountInOneRecord;
					intTotalZjZhushu = intZjZhushu * intTzTimes;//一条记录总的中奖注数
					intTotalMoney = zjMoneyInOneRecord * intTzTimes;//一条记录总的中奖金额
					
					//中奖规则，备用
					if(intZjZhushu > 0){
						zjRule = ConstantSymbol.RX6 + ":" + intZjZhushu + ";";//格式为"规则:中奖注数"
					}
				}
				
				if(tempPlayType.equals(ConstantSymbol.RX5)){//5选5中5 
					int wuzhong5Times = JStringToolkit.getSubNum(tempNumDetail,"|"+wuzhong5+"|");
					int wuzhong5Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX5))*wuzhong5Times;
					
					intZjZhushu = wuzhong5Times;
					intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
					
					int intEachMoney = wuzhong5Money;//每个单注的总奖金
					intTotalMoney = intEachMoney * intTzTimes;//总奖金
					
					//中奖规则，备用
					if(intZjZhushu > 0){
						zjRule = ConstantSymbol.RX5 + ":" + intZjZhushu + ";";//格式为"规则:中奖注数"
					}
				}
				if(tempPlayType.equals(ConstantSymbol.RX4)){//任选4中4
					//选4中4 的中奖注数及金额
					//现废除2011-4-23
//						int sizhong4Times = JStringToolkit.getSubNum(tempNumDetail,"|"+sizhong4+"|");
//						int sizhong4Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX4))*sizhong4Times;
					
					//4中4的中奖注数及金额
					int sizhong4Times = howTimesInNum(sizhong4List,tempNumDetail,"");
//						int wuzhong4Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX54))*wuzhong4Times;
					int wuzhong4Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX4))*sizhong4Times;
					
					//
//						intZjZhushu = sizhong4Times + wuzhong4Times;//每个单注中奖的注数						
					intZjZhushu = sizhong4Times;//每个单注中奖的注数						
					intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
					
					//改变此
//						int intEachMoney = sizhong4Money + wuzhong4Money ;//每个单注的总奖金
					int intEachMoney = wuzhong4Money ;//每个单注的总奖金
					intTotalMoney = intEachMoney * intTzTimes;//总奖金
					
					//中奖规则，备用
//						if(sizhong4Times > 0){
//							zjRule = zjRule +  ConstantSymbol.RX4 + ":" + sizhong4Times + ";";//格式为"规则:中奖注数;"
//						}
					if(sizhong4Times > 0){
						zjRule = zjRule +  ConstantSymbol.RX4 + ":" + sizhong4Times + ";";//格式为"规则:中奖注数"
					}
				}
				if(tempPlayType.equals(ConstantSymbol.RX3)){//选3
					if(tempPlayMode.equals("zhiqian3")){
						//直选前3
						//选3中3直选前3 的中奖注数及金额
						int sanzhong3Qian3Times = JStringToolkit.getSubNum(tempNumDetail,"|"+sanzhong3Qian3Zhi+"|");
						int sanzhong3Qian3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX3ZHIQIAN3))*sanzhong3Qian3Times;
						
						intZjZhushu = sanzhong3Qian3Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;
						
						int intEachMoney = sanzhong3Qian3Money;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;
						
						tempPrizeMoneyKey = tempPrizeMoneyKey + "zhiqian3";
						if(sanzhong3Qian3Times > 0){
							zjRule = zjRule + ConstantSymbol.RX3ZHIQIAN3 + ":" + sanzhong3Qian3Times + ";";//格式为"规则:中奖注数"
						}
					}else if(tempPlayMode.equals("zuqian3") || tempPlayMode.equals("zuqian3dantuo")  ){
						//组选前3
						String tempPrize = "";
//							2011-10-04废除 组选前3及组选前3胆拖都属于组选前3
//							if(tempPlayMode.equals("zuqian3") ){
//								tempPrize = ConstantSymbol.RX3ZUQIAN3;
//							}
//							if(tempPlayMode.equals("zuqian3dantuo") ){
//								tempPrize = ConstantSymbol.RX3ZUQIAN3DANTUO;
//							}
						tempPrize = ConstantSymbol.RX3ZUQIAN3;
						//选3中3组选前3 的中奖注数及金额
						int sanzhong3Qian3Times = JStringToolkit.getSubNum(tempNumDetail,"|"+sanzhong3Qian3Zu+"|");
						int sanzhong3Qian3Money = Integer.parseInt(prizeMap.get(tempPrize))*sanzhong3Qian3Times;
						
						intZjZhushu = sanzhong3Qian3Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;
						
						int intEachMoney = sanzhong3Qian3Money;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;

						//tempPrizeMoneyKey = tempPrizeMoneyKey + "zuqian3";
						tempPrizeMoneyKey = tempPrize;
						if(sanzhong3Qian3Times > 0){
							zjRule = zjRule + ConstantSymbol.RX3ZUQIAN3 + ":" + sanzhong3Qian3Times + ";";//格式为"规则:中奖注数"
						}
					}else{
						//任选三中3
						//选3中3 的中奖注数及金额
						//2011-4-23先 用之前的5中3代替3中3
//							int sanzhong3Times = JStringToolkit.getSubNum(tempNumDetail,"|"+sanzhong3+"|");
//							int sanzhong3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX3))*sanzhong3Times;
						
						//任选三中3 中奖注数及金额
						int sanzhong3Times = howTimesInNum(sanzhong3List,tempNumDetail,"");
//							int wuzhong3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX53))*wuzhong3Times;
						int sanzhong3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX3))*sanzhong3Times;
						
//							intZjZhushu = sanzhong3Times + wuzhong3Times;//每个单注中奖的注数						
						intZjZhushu = sanzhong3Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
						
//							int intEachMoney = sanzhong3Money + wuzhong3Money ;//每个单注的总奖金
						int intEachMoney = sanzhong3Money ;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;//总奖金
						
						//中奖规则，备用
//							if(sanzhong3Times > 0){
//								zjRule = zjRule + ConstantSymbol.RX3 + ":" + sanzhong3Times + ";";//格式为"规则:中奖注数;"
//							}
						if(sanzhong3Times > 0){
//								zjRule = zjRule + ConstantSymbol.RX53 + ":" + wuzhong3Times + ";";//格式为"规则:中奖注数"
							zjRule = zjRule + ConstantSymbol.RX3 + ":" + sanzhong3Times + ";";//格式为"规则:中奖注数"
						}
					}
				}

				if(tempPlayType.equals(ConstantSymbol.RX2)){//选2
					if(tempPlayMode.equals("zhiqian2")){
						// 直选前2 的中奖注数及金额,
						int erzhong2Qian2Times = JStringToolkit.getSubNum(tempNumDetail,"|"+erzhong2Qian2Zhi+"|");
						int erzhong2Qian2Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX2ZHIQIAN2))*erzhong2Qian2Times;
						
						intZjZhushu = erzhong2Qian2Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;
						
						int intEachMoney = erzhong2Qian2Money;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;
						
						tempPrizeMoneyKey = tempPrizeMoneyKey + "zhiqian2";
						if(erzhong2Qian2Times > 0){
							zjRule = zjRule + ConstantSymbol.RX2ZHIQIAN2 + ":" + erzhong2Qian2Times + ";";//格式为"规则:中奖注数"
						}
					}else if(tempPlayMode.equals("zuqian2") || tempPlayMode.equals("zuqian2dantuo")){//组选前2及组选前2胆拖
						//组选前2 (组选前2及组选前2胆拖)
						String tempPrize = "";
//							2011-10-04 废弃 组选前2 中包括 (组选前2及组选前2胆拖)
//							if(tempPlayMode.equals("zuqian2")){
//								tempPrize = ConstantSymbol.RX2ZUQIAN2;
//							}
//							if(tempPlayMode.equals("zuqian2dantuo")){
//								tempPrize = ConstantSymbol.RX2ZUQIAN2DANTUO;
//							}
						tempPrize = ConstantSymbol.RX2ZUQIAN2;
						//选2中2 的中奖注数及金额
						int erzhong2Qian2Times = JStringToolkit.getSubNum(tempNumDetail,"|"+erzhong2Qian2Zu+"|");
						int erzhong2Qian2Money = Integer.parseInt(prizeMap.get(tempPrize))*erzhong2Qian2Times;
						
						intZjZhushu = erzhong2Qian2Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;
						
						int intEachMoney = erzhong2Qian2Money;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;

//							tempPrizeMoneyKey = tempPrizeMoneyKey + "zuqian2";
						tempPrizeMoneyKey =  tempPrize;
						if(erzhong2Qian2Times > 0){
							zjRule = zjRule + ConstantSymbol.RX2ZUQIAN2 + ":" + erzhong2Qian2Times + ";";//格式为"规则:中奖注数"
						}
					}else{
						//选2中2 的中奖注数及金额
						//2011-4-23先 用之前的5中2代替2中2 
						
						//5中2的中奖注数及金额
						int wuzhong2Times = howTimesInNum(erzhong2List,tempNumDetail,"");
//							int wuzhong2Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX52))*wuzhong2Times;
						int wuzhong2Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX2))*wuzhong2Times;
						
//							intZjZhushu = erzhong2Times + wuzhong2Times;//每个单注中奖的注数						
						intZjZhushu = wuzhong2Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
						
//							int intEachMoney = erzhong2Money + wuzhong2Money ;//每个单注的总奖金
						int intEachMoney = wuzhong2Money ;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;//总奖金
						
						//中奖规则，备用
//							if(erzhong2Times > 0){
//								zjRule = zjRule + ConstantSymbol.RX2 + ":" + erzhong2Times + ";";//格式为"规则:中奖注数;"
//							}
						if(wuzhong2Times > 0){
//								zjRule = zjRule + ConstantSymbol.RX52 + ":" + wuzhong2Times + ";";//格式为"规则:中奖注数"
							zjRule = zjRule + ConstantSymbol.RX2 + ":" + wuzhong2Times + ";";//格式为"规则:中奖注数"
						}
					}
				}
				if(tempPlayType.equals(ConstantSymbol.RX1)){//1选1中1
					int yizhong1Times = JStringToolkit.getSubNum(tempNumDetail,"|"+yizhong1+"|");
					int yizhong1Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX1))*yizhong1Times;
					
					intZjZhushu = yizhong1Times;
					intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
					
					int intEachMoney = yizhong1Money;//每个单注的总奖金
					intTotalMoney = intEachMoney * intTzTimes;//总奖金
					
					//中奖规则，备用
					if(yizhong1Times > 0){
						zjRule = ConstantSymbol.RX1 + ":" + yizhong1Times + ";";//格式为"规则:中奖注数;"
					}
				}
				
				zjInfo.setZjZhushu(String.valueOf(intZjZhushu));//设置中奖注数
				
						
				String tempZjMoney = "";//奖金
				String tempMoneyOfZjType = "";//当前玩法中奖的奖金设置
				

				//根据玩法,得到相应的奖金
				String strPrizeMoney = prizeMap.get(tempPrizeMoneyKey);//相应玩法对应的中奖奖金设置
				tempZjMoney = String.valueOf(intTotalMoney);//总的中奖金额
				tempMoneyOfZjType = strPrizeMoney;
				
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
				zjInfo.setZjMoney(tempZjMoney);
				zjInfo.setTotalZjZhushu(String.valueOf(intTotalZjZhushu));
				zjInfo.setMoneyOfZjType(tempMoneyOfZjType);
				
				zjInfolist.add(zjInfo);					
			}
		}catch(Exception e){
			logger.error("中奖信息保存异常:" + e.toString());
			throw new AppException("中奖信息保存异常:" + e.toString());
		}
		
		//多期投注的情况下，如果其中有一期中奖，那么中奖后的其余期数投注号码要自动删除，删除后用户余额与所投金额要相应增加与扣除。（参考快乐扑克）
		if(zjNumList != null && !zjNumList.isEmpty()){
			
			for(int i = 0; i < zjNumList.size(); i++){
				SscZjNum info = zjNumList.get(i);
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
			sql = "select id,user_id,touzhu_money from sxw_tz_info where user_id='"+userId+"' and parent_id='"+tzId+"' and issue_num != '"+issueNum+"' and have_zt_flag='0'";
		}else{
			sql = "select id,user_id,touzhu_money from sxw_tz_info where  user_id='"+userId+"' and parent_id='"+tzId + "' and issue_num != '"+issueNum+"' and ((issue_num+0) > ('"+issueNum+"'+0))";
		}
		logger.info("--查询中奖期号的投注Id sql="+sql);
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
			logger.error("查询待删除中奖信息错误"+e.toString());
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
			logger.error("12x5删除中奖号码错误：" + e.toString());
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
			flag = sxwZjInfoDao.delUserZjInfo(tzId, userId);
		}catch(DAOException e){
			logger.error("12x5删除指定用户中奖信息错误：" + e.toString());
		}
		return flag;
	}
	/**
	 * 查询指定用户总的投注注数及投注金额
	 * @param issueNum
	 * @return
	 */
	public SxwTzInfo getUserTotalTzInfo(String issueNum,String userId){
		SxwTzInfo info = new SxwTzInfo();
		try{
			info = sxwTzInfoDao.getUserTotalTzInfo(issueNum,userId);
		}catch(Exception e){
			logger.error("12x5查询指定用户投注信息错误：" + e.toString());
		}
		return info;
	}
	/**
	 * 查询指定用户总的中奖注数及中奖金额
	 * @param issueNum
	 * @return
	 */
	public SxwZjInfo getUserTotalZjInfo(String issueNum,String userId){
		SxwZjInfo info = new SxwZjInfo();
		try{
			info = sxwZjInfoDao.getUserTotalZjInfo(null,userId);
		}catch(DAOException e){
			logger.error("12x5查询指定用户中奖信息错误：" + e.toString());
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
}
