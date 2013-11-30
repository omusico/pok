package com.onmyway.ssc.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;
import util.ConvertToolkit;
import util.JDateToolkit;
import util.JStringToolkit;

import com.onmyway.common.IdBuilder;
import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.factory.SscRenxuanAnalyse;
import com.onmyway.init.SscLimitInfoInit;
import com.onmyway.ssc.manage.dao.ISscBaseInfoConfigDao;
import com.onmyway.ssc.manage.dao.ISscMoneyLimitDao;
import com.onmyway.ssc.manage.dao.ISscZjInfoDao;
import com.onmyway.ssc.manage.dao.ISscZjNumConfigDao;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
import com.onmyway.ssc.manage.model.SscMoneyAndLimit;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.manage.model.SscZjNumConfig;
import com.onmyway.ssc.play.dao.ISscTzInfoDao;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.manage.model.SscZjNum;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:时时彩-后台管理
 * @Description: 
 * @Create on: Aug 14, 2010 5:30:25 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscManageServiceImpl implements ISscManageService {	

	private Log logger = LogFactory.getLog(SscManageServiceImpl.class);

	DBTool dbTool = DBAccess.getDBTool();
	private ISscZjNumConfigDao dao;
	private ISscBaseInfoConfigDao sscBaseInfoConfigDao;
	private ISscMoneyLimitDao sscMoneyLimitDao;
	private ISscZjInfoDao sscZjInfoDao;
	private ISscTzInfoDao sscTzInfoDao;
	
	//投注DAO
	public ISscTzInfoDao getSscTzInfoDao() {
		return sscTzInfoDao;
	}

	public void setSscTzInfoDao(ISscTzInfoDao sscTzInfoDao) {
		this.sscTzInfoDao = sscTzInfoDao;
	}

	/**
	 * 中奖用户信息查询
	 * @return
	 */
	public ISscZjInfoDao getSscZjInfoDao() {
		return sscZjInfoDao;
	}

	public void setSscZjInfoDao(ISscZjInfoDao sscZjInfoDao) {
		this.sscZjInfoDao = sscZjInfoDao;
	}

	/**
	 * 奖金设置及限制倍数
	 * @return
	 */
	public ISscMoneyLimitDao getSscMoneyLimitDao() {
		return sscMoneyLimitDao;
	}

	public void setSscMoneyLimitDao(ISscMoneyLimitDao sscMoneyLimitDao) {
		this.sscMoneyLimitDao = sscMoneyLimitDao;
	}

	/**
	 * 中奖号码DAO
	 * @return
	 */
	public ISscZjNumConfigDao getSscNumConfigDao() {
		return dao;
	}

	public void setSscNumConfigDao(ISscZjNumConfigDao dao) {
		this.dao = dao;
	}
	/**
	 * 基本信息dao
	 * @return
	 */
	public ISscBaseInfoConfigDao getSscBaseInfoConfigDao() {
		return sscBaseInfoConfigDao;
	}
	public void setSscBaseInfoConfigDao(ISscBaseInfoConfigDao baseInfoDao) {
		this.sscBaseInfoConfigDao = baseInfoDao;
	}
	
	public SscZjNumConfig get(String id) throws AppException {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	/**
	 * 得到当天的基本配置信息
	 * @param date
	 * @return
	 */
	public SscBaseInfoConfig getConfigInfoOfToday(){
		SscBaseInfoConfig info = new SscBaseInfoConfig();
		try{
			String today = JDateToolkit.getNowDate2();
			List<SscBaseInfoConfig> list = sscBaseInfoConfigDao.getConfigInfoOfDay(today);
			if(list != null && !list.isEmpty()){
				info = list.get(0);
			}
		}catch(Exception e){
			logger.error("查询时时彩当天配置信息错误！"+e.toString());
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

	public void save(SscZjNumConfig config) throws AppException {
		try{
			dao.save(config);
		}catch(DAOException e){
			logger.error("保存中奖号码错误"+e.toString());
		}
		
	}
	
	public void update(SscZjNumConfig config) throws AppException {
		// TODO Auto-generated method stub
		
	}
	public SscBaseInfoConfig getBaseInfo(String id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 得到给定期号的中奖信息
	 * @return
	 * @throws AppException
	 */
	public SscZjNumConfig getZjNumConfig(String issueNum) throws AppException{
		SscZjNumConfig info = new SscZjNumConfig();
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
	 * 得到最新的中奖信息
	 * @return
	 * @throws AppException
	 */
	public SscZjNumConfig getCurrentZjNum() throws AppException{
		SscZjNumConfig info = new SscZjNumConfig();
		try{
			info =  dao.getCurrentZjNum();
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
	 * 得到最新的中奖信息,除了当前期
	 * @return
	 * @throws AppException
	 */
	public SscZjNumConfig getCurrentZjNum(String currentIssue) throws AppException{
		SscZjNumConfig info = new SscZjNumConfig();
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
	 * 查询所有的中奖信息
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SscZjNumConfig> getAllZjNum() throws AppException{
		List<SscZjNumConfig> list = new ArrayList<SscZjNumConfig>();
		try{
			list = dao.getAllZjNum();
		}catch(Exception e){
			logger.error("查询时时彩信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 查询所有的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SscZjNumConfig> getAllZjNum(String currentIssue) throws AppException{
		List<SscZjNumConfig> list = new ArrayList<SscZjNumConfig>();
		try{
			list = dao.getAllZjNum(currentIssue);
		}catch(Exception e){
			logger.error("查询时时彩信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 查询相应时间段内的的中奖信息,除了当前正在发行的期别
	 * @param issueNum
	 * @return
	 * @throws AppException
	 */
	public List<SscZjNumConfig> getZjNumBetweenDate(String beginDate,String endDate,String currentIssue) throws AppException{
		List<SscZjNumConfig> list = new ArrayList<SscZjNumConfig>();
		try{
			list = dao.getZjNumBetweenDate(beginDate,endDate,currentIssue);
		}catch(Exception e){
			logger.error("查询时时彩信息异常：" + e.toString());
		}
		return list;
	}
	/**
	 * 基本信息保存
	 */
	public void saveBaseInfo(SscBaseInfoConfig info) throws AppException {
		try{
			sscBaseInfoConfigDao.save(info);
		}catch(DAOException e){
			logger.error("保存中奖号码错误"+e.toString());
		}
		
	}

	public void updateBaseInfo(SscBaseInfoConfig info) throws AppException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 奖金及限号信息设置
	 * @param info
	 * @throws AppException
	 */
	public void saveLimitInfo(SscMoneyAndLimit info) throws AppException{
		boolean flag = true;
		try{
			//先将该种玩法先前的限号信息都置为无效
			 flag = sscMoneyLimitDao.updateLimitByRule("ssc", info.getRule());
			//再保持现在的限号信息
			if(flag){
				sscMoneyLimitDao.save(info);
				//同时更新系统中的限号配置信息
				//同时更新系统中的限号配置信息
				SscLimitInfoInit.sscLimitMap = getLimitInfoMap();
				SscLimitInfoInit.sscPrizeMap = getPrizeInfoMap();	
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
		List<SscMoneyAndLimit> list =  sscMoneyLimitDao.getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(SscMoneyAndLimit info : list){
				String rule = info.getRule();
				String limit = info.getLimitOneIssue();
				logger.info("rule="+rule+",limit="+limit);
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
		List<SscMoneyAndLimit> list =  sscMoneyLimitDao.getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(SscMoneyAndLimit info : list){
				String rule = info.getRule();
				String money = info.getMoney();
				logger.info("rule="+rule+",prize="+money);
				map.put(rule, money);
			}
		}
		return map;
	}
	/**
	 * 中奖信息保存，同时从数据库中查询已经投注的号码的中奖情况，并将其保存到数据中
	 * @param config
	 * @return
	 * @throws AppException
	 */
	public boolean saveZjInfo(SscZjNumConfig config) throws AppException {
		boolean flag = true;
		String isResetIssueInfo = config.getIsResetIssueInfo();//是否重新设置中奖号码 1：重新设置；0：不重新设置
		
		String issueNum = config.getIssueNum();

		if(isResetIssueInfo.equals("1")){//先删除原有已设定的期号的信息
			try{	
				boolean temp = dao.deleteZjNum(issueNum);
				logger.warn("删除"+issueNum + "期原有中奖号码：" + temp);
			}catch(DAOException e){
				flag = false;
				throw new AppException("删除"+issueNum + "期原有中奖号码错误"+e.toString());
			}
		}
		try{	
			//重新保存
			dao.save(config);
		}catch(DAOException e){
			flag = false;
			logger.error("保存中奖号码错误"+e.toString());
			throw new AppException("保存中奖号码错误"+e.toString());
		}
		//定义容器
		List<SscZjInfo> zjInfolist = new ArrayList<SscZjInfo>();
		List<String> idList = new ArrayList<String>();//存放要删除的中奖记录ID
		Map<String,Integer> moneyMap = new HashMap<String,Integer>();//存放要返还的用户及相应的金额	
		
		String zjNum = config.getWan()+","+config.getQian()+","+config.getBai()+","+config.getShi()+","+config.getGe();
		setZjInfo(zjInfolist,idList,moneyMap,issueNum,zjNum);
			
		try{
			dao.jdbcSaveListInfo(zjInfolist, idList,moneyMap);//如果不抛出异常，就是保存成功
		}catch(DAOException ee){
			logger.error("中奖信息保存DAO异常:" + ee.toString());

			throw new AppException("保存中奖记录错误"+ee.toString());
		}
	
		 
		return flag;
	}
	/**
	 * 将组3，按照大小顺序进行排列，并只取两位数
	 * 将组6按照大小顺序排列，并只取两位数
	 * 先将表里已存在的相应期号的中奖号码信息删除，然后再重新进行中奖号码的设置
	 */
 
	public void setZjInfo(List<SscZjInfo> zjInfolist,List<String> idList,Map<String,Integer> moneyMap,String issueNum, String zjNum) throws AppException{
		//分解号码
		if(issueNum == null || issueNum.equals("") || zjNum == null || zjNum.equals("")){
			throw new AppException("时时彩期号或中奖号码为空，请重新设置");
		}
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		int len = aryZjNum.length;
		if(len < 5){
			throw new AppException("时时彩开奖号码设置有误!请重新设置");
		} 
		
		try{
			sscZjInfoDao.deleteHistoryZjInfo(issueNum);
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
		//五星号码
		String wuxing = strWan + strQian + strBai + strShi + strGe;//五星
		String wuxingQian3 =  strWan + strQian + strBai;//前三
		String wuxingHou3 =  strBai + strShi + strGe;//后三
		String wuxingQian2 = strWan + strQian;//前2
		String wuxingHou2 =  strShi + strGe;//后2
		
		//四星号码
		String sixing = strQian + strBai + strShi + strGe;//四星
		//三星号码
		String sanxing = strBai + strShi + strGe;//三星
		String[] aaaa = {strBai , strShi , strGe};
		String tempSanxing = JStringToolkit.strSort(aaaa);//将三星从小到大排序，为组选准备
		//判断开奖号码后三位是否是组3(有两个数值相等，但3个不全等)
		boolean zu3Flag = false;
		if((strBai.equals(strShi) || strShi.equals(strGe) || strBai.equals(strGe)) 
				&& !(strBai.equals(strShi) && strBai.equals(strGe))){
			zu3Flag = true;
		}
		if((intBai == intShi || intShi == intGe || intBai == intGe) && !(intBai == intShi && intBai == intGe)){
			zu3Flag = true;
		}
		String sanxingZu31 = "";
		String sanxingZu32 = "";
		if(zu3Flag){
			if(intBai == intShi){
				sanxingZu31 = strBai+strGe;
				sanxingZu32 = strGe + strBai;
			}
			if(intBai == intGe){
				sanxingZu31 = strBai+strShi;
				sanxingZu32 = strShi + strBai;
			}
			if(intShi == intGe){
				sanxingZu31 = strShi+strBai;
				sanxingZu32 = strBai + strShi;
			}
		}
		
		//判断开奖号码后三位是否是组6(3个值都不等)
		boolean zu6Flag = false;
		if(intBai != intShi && intShi != intGe && intBai != intGe){
			zu6Flag = true;
		}
		String sanxingZu6 = "";
		if(zu6Flag){			
			sanxingZu6 = tempSanxing;//此处的组六是将后三位由小到大排列后的值
		}
		//不需要得和值了，在程序中，将每个和值分解成直选，组3，组6了
		//不需要得包胆了，在程序中，将每个包胆分解成直选，组3，组6了
		/***************************************
		//得到后三位的和值 
		int intSanxingHezhi = intBai + intShi + intGe;
		String strSanxingHezhi = String.valueOf(intSanxingHezhi);
		//得到包一胆的值（直接为每个位置上的值）
		String bao1dan1 = strBai;
		String bao1dan2 = strShi;
		String bao1dan3 = strGe;
		//得到包二胆的值
		String bao2dan1 = strBai + strShi;
		String bao2dan2 = strBai + strGe;
		String bao2dan3 = strShi + strGe;
		**************************************/
		//二星号码
		String erxing = strShi + strGe;//二星直选
		
		String erxingZx1 = strShi + strGe;//二星组选1
		String erxingZx2 = strGe + strShi;//二星组选2
		
		//一星号码
		String yixing = strGe;//一星
		
		//大小单双号码
		//10个自然数按“大”、“小”或“单”、“双”性质分为两组，0-4为小号，5-9为大号，0、2、4、6、8为双号，1、3、5、7、9为单号
		String shiDaxiao = "";//判断十位属于大还是小
		String shiDanshuang = "";//判断十位属于单还是双
		String geDaxiao = "";//判断个位属于大还是小
		String geDanshuang = "";//判断个位属于单还是双
		//十位
		if(intShi<5){
			shiDaxiao = "l";//小 little
		}else{
			shiDaxiao = "b";//大 big
		}
		if(intShi%2 == 1){
			shiDanshuang = "s";//单 single
		}else{
			shiDanshuang = "d";//双 double
		}
		//个位
		if(intGe<5){
			geDaxiao = "l";//小 little
		}else{
			geDaxiao = "b";//大 big
		}
		if(intGe%2 == 1){
			geDanshuang = "s";//单 single
		}else{
			geDanshuang = "d";//双 double
		}
		String dxds1 = shiDaxiao+geDaxiao;	
		String dxds2 = shiDaxiao+geDanshuang;	
		String dxds3 = shiDanshuang+geDanshuang;	
		String dxds4 = shiDanshuang+geDaxiao;	
		
		SscRenxuanAnalyse renxuanAnalyse = new SscRenxuanAnalyse();
		String strRenxuan1 = renxuanAnalyse.renxuan1(zjNum);
		String strRenxuan2 = renxuanAnalyse.renxuan2(zjNum);
		String strRenxuan3 = renxuanAnalyse.renxuan3(zjNum);
		
		//五星通选中的一等奖
		String wxTxSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,'wuxingtx' as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='wuxing' and type_name='tx'  and t.num_detail like  '%," + wuxing+",%'";
		//五星通选中的二等奖(排除已经中了一等奖的记录)
		String wxTxQianHou3Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,'wuxingqian3' as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
					   + " and (t.num_detail like  '%," + wuxingQian3 + "__,%' or t.num_detail like  '%,__" + wuxingHou3 + ",%')"
					   + " and t.id not in ( select t.id  from ssc_tz_detail t where t.issue_num='"+issueNum + "' and t.play_type='wuxing' and type_name='tx'  and t.num_detail like  '%," + wuxing+",%')";
		//五星通选中的三等奖(排除已经中了二等奖的记录，同时也排除了已中一等奖的记录))
		String wxTxQianHou2Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,'wuxingqian2' as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
					  + " and (t.num_detail like  '%," + wuxingQian2 + "___,%' or t.num_detail like  '%,___" + wuxingHou2 + ",%')"
					  + " and id not in  (select t.id  from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
					   + "                 and (t.num_detail like  '%," + wuxingQian3 + "__,%' or t.num_detail like  '%,__" + wuxingHou3 + ",%'))";
	
		//五星直选五星
		String wxZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			+ "' and t.play_type='wuxing' and play_mode='zhixuan' and t.num_detail like  '%," + wuxing+",%'";
		
		String wxZu120Sql = "select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='wuxing' and play_mode='zu120' and t.num_detail like  '%," + wuxing+",%'";
		String wxZu60Sql = "select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='wuxing' and play_mode='zu60' and t.num_detail like  '%," + wuxing+",%'";
		String wxZu30Sql = "select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='wuxing' and play_mode='zu30' and t.num_detail like  '%," + wuxing+",%'";
		String wxZu20Sql = "select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='wuxing' and play_mode='zu20' and t.num_detail like  '%," + wuxing+",%'";
		String wxZu10Sql = "select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='wuxing' and play_mode='zu10' and t.num_detail like  '%," + wuxing+",%'";
		String wxZu5Sql = "select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='wuxing' and play_mode='zu5' and t.num_detail like  '%," + wuxing+",%'";
		//判读五星重号全包
		//aryZjNum;;
		Set<String> tempCountSet = new HashSet<String>();//开奖号码中有多少个不重复的数字
		for(String x : aryZjNum){
			tempCountSet.add(x);
		}
		List<ZjNumAndCount> countList = new ArrayList<ZjNumAndCount>();
		Iterator<String> countIt = tempCountSet.iterator();
		boolean zuchong2Flag = false;
		boolean zuchong3Flag = false;
		boolean zuchong4Flag = false;
		boolean zuchong5Flag = false;
		List<String> zuchong2List = new ArrayList<String>();//2重号全包,可能有（2位重号，3位重号）或者（2个两位重号）的特殊情况，因此设置为List
		String zuchong3Num = "";//3重号全包,因为只有五位，不可能有2个三位重号，因此只需记录3重号的数字
		String zuchong4Num = "";//4重号全包
		String zuchong5Num = "";//5重号全包
		while(countIt.hasNext()){
			String numVal = countIt.next();
			int tCount = JStringToolkit.getSubNum(zjNum,numVal);
			if(tCount == 2){
				zuchong2Flag = true;
				countList.add(new ZjNumAndCount(numVal,tCount));
				zuchong2List.add(numVal);
			}
			if(tCount == 3){
				zuchong2Flag = true;
				zuchong3Flag = true;
				countList.add(new ZjNumAndCount(numVal,tCount));
				zuchong2List.add(numVal);
				zuchong3Num = numVal;
			}
			if(tCount == 4){
				zuchong2Flag = true;
				zuchong3Flag = true;
				zuchong4Flag = true;
				countList.add(new ZjNumAndCount(numVal,tCount));
				zuchong2List.add(numVal);
				zuchong3Num = numVal;
				zuchong4Num = numVal;
			}
			if(tCount == 5){
				zuchong2Flag = true;
				zuchong3Flag = true;
				zuchong4Flag = true;
				zuchong5Flag = true;
				countList.add(new ZjNumAndCount(numVal,tCount));
				zuchong2List.add(numVal);
				zuchong3Num = numVal;
				zuchong4Num = numVal;
				zuchong5Num = numVal;
			}
		}
		//为了防止一组号码中，有2个为重号，3个为重号的情况，做以下处理
		List<String> zuchong2SqlList = new ArrayList<String>();
		if(zuchong2Flag){
			for(String num : zuchong2List){
				String ss =  " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
				   + "' and t.play_type='wuxing' and play_mode='zuchong2' and t.num_detail like  '%," + num+",%'";
				zuchong2SqlList.add(ss);
			}
		}
		//三重号全包 
		String wuxingZuchong3Sql = "";
		if(zuchong3Flag){
			wuxingZuchong3Sql =  " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
				   + "' and t.play_type='wuxing' and play_mode='zuchong3' and t.num_detail like  '%," + zuchong3Num+",%'";
		}
		//四重号全包  2011-10-11
		String wuxingZuchong4Sql = "";
		if(zuchong4Flag){
			wuxingZuchong4Sql =  " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
				   + "' and t.play_type='wuxing' and play_mode='zuchong4' and t.num_detail like  '%," + zuchong4Num+",%'";
		}
		//四星
		String sixingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='sixing' and play_mode='zhixuan' and t.num_detail like  '%," + sixing+",%'";

		//四星zu24
		String sixingZu24Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='sixing' and play_mode='zu24' and t.num_detail like  '%," + sixing+",%'";

		//四星zu12
		String sixingZu12Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='sixing' and play_mode='zu12' and t.num_detail like  '%," + sixing+",%'";

		//四星zu6
		String sixingZu6Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='sixing' and play_mode='zu6' and t.num_detail like  '%," + sixing+",%'";
		//四星zu4
		String sixingZu4Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='sixing' and play_mode='zu4' and t.num_detail like  '%," + sixing+",%'";

		
		
		//三星
		String sanxingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='sanxing' and play_mode='zhixuan' and t.num_detail like  '%," + sanxing+",%'";
	
		//三星组3,
		String sanxingZu3Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='sanxing' and t.play_mode='zu3' and( t.num_detail like  '%," + sanxingZu31+",%' or t.num_detail like  '%," + sanxingZu32+",%')";
		//三星组6
		String sanxingZu6Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='sanxing' and t.play_mode='zu6' and t.num_detail like  '%," + sanxingZu6+",%'";
		
		//二星直选
		String erxingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='erxing' and play_mode='zhixuan' and t.num_detail like  '%," + erxing+",%'";
		//二星组选
		String erxingZxSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='erxing' and play_mode='zuxuan' and (t.num_detail like  '%," + erxingZx1+",%' or t.num_detail like  '%," + erxingZx2 + ",%')";
		//一星直选
		String yixingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='yixing' and play_mode='zhixuan' and t.num_detail like  '%," + yixing+",%'";
	
		//大小单双
		String dxdsSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='dxds' and play_mode='zhixuan' and (t.num_detail like  '%," + dxds1+",%' or t.num_detail like  '%," + dxds2+",%'  or t.num_detail like  '%," + dxds3+",%'  or t.num_detail like  '%," + dxds4+",%')  ";
		
		String tempSql = wxZhiSql + " union " + wxTxSql + " union " + wxTxQianHou3Sql + " union " + wxTxQianHou2Sql + " union " + wxZu120Sql + " union " + wxZu60Sql + " union " + wxZu30Sql + " union " + wxZu20Sql + " union " + wxZu10Sql+ " union " + wxZu5Sql;//五星
		//五星组选2重号处理
		if(zuchong2Flag){
			for(String ss:zuchong2SqlList){
				tempSql = tempSql + " union " + ss;
			}
		}
		//五星组选三重号处理
		if(zuchong3Flag){
			if(!wuxingZuchong3Sql.equals("")){
				tempSql = tempSql + " union " + wuxingZuchong3Sql;
			}
		}
		//五星组选4重号处理 2011-10-11
		if(zuchong4Flag){
			if(!wuxingZuchong4Sql.equals("")){
				tempSql = tempSql + " union " + wuxingZuchong4Sql;
			}
		}
		//四星相关的中奖查询sql
		tempSql = tempSql + " union " + sixingZhiSql + " union " + sixingZu24Sql + " union " + sixingZu12Sql + " union " + sixingZu6Sql + " union " + sixingZu4Sql;
		
		//三星相关的中奖查询sql
		tempSql = tempSql + " union " + sanxingZhiSql;
		
		
		if(zu3Flag){
			tempSql = tempSql + " union " + sanxingZu3Sql;
		}
		if(zu6Flag){
			tempSql = tempSql + " union " + sanxingZu6Sql;
		}
		//任选1,2,3,
		//任选1
		String renxuan1Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan1' and play_mode='renxuan1'";// and (t.num_detail like  '%," + dxds1+",%' or t.num_detail like  '%," + dxds2+",%'  or t.num_detail like  '%," + dxds3+",%'  or t.num_detail like  '%," + dxds4+",%')  ";
		if(strRenxuan1 != null && !strRenxuan1.equals("")){
			String[] aryRx1 = JStringToolkit.splitString(strRenxuan1, ",");
			for(String s : aryRx1){
				String tSql = renxuan1Sql + " and t.num_detail like  '%," + s + ",%'";
				tempSql = tempSql + " union " + tSql;
			}
		}
		//任选2
		String renxuan2Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan2' and play_mode='renxuan2'";// and (t.num_detail like  '%," + dxds1+",%' or t.num_detail like  '%," + dxds2+",%'  or t.num_detail like  '%," + dxds3+",%'  or t.num_detail like  '%," + dxds4+",%')  ";
		if(strRenxuan2 != null && !strRenxuan2.equals("")){
			String[] aryRx2 = JStringToolkit.splitString(strRenxuan2, ",");
			for(String s : aryRx2){
				String tSql = renxuan2Sql + " and t.num_detail like  '%," + s + ",%'";
				tempSql = tempSql + " union " + tSql;
			}
		}
		//任选1
		String renxuan3Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_detail,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
		+ "' and t.play_type='renxuan3' and play_mode='renxuan3'";// and (t.num_detail like  '%," + dxds1+",%' or t.num_detail like  '%," + dxds2+",%'  or t.num_detail like  '%," + dxds3+",%'  or t.num_detail like  '%," + dxds4+",%')  ";
		if(strRenxuan3 != null && !strRenxuan3.equals("")){
			String[] aryRx3 = JStringToolkit.splitString(strRenxuan3, ",");
			for(String s : aryRx3){
				String tSql = renxuan3Sql + " and t.num_detail like  '%," + s + ",%'";
				tempSql = tempSql + " union " + tSql;
			}
		}
		
		
		
		tempSql = tempSql + " union " + erxingZhiSql + " union " + erxingZxSql + " union " + yixingZhiSql + " union " + dxdsSql;
		
		String sql = "select a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,a.touzhu_num,a.parent_issue_num,a.parent_id,a.is_zhuitou,a.have_zt_flag,b.* from ssc_tz_info a,(" + tempSql + ")b where a.id=b.p_id and a.is_valid='1'";
		logger.info("查询中奖sql=" + sql);
		

		List<SscZjNum> zjNumList = new ArrayList<SscZjNum>();
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{
			Map<String,String> prizeMap = SscLimitInfoInit.getSscPrizeMap();
			Set<String> zjUserSet = new HashSet<String>();//记录该期中奖的用户2011-02-10
			
		
			
			while(crs.next()){
//					t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou 
//					a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,
				SscZjInfo zjInfo = new SscZjInfo();
				String tempId = IdBuilder.getId();//生成主键ID
				String tempIssueNum = crs.getString("issue_num");
				String tempTzId = crs.getString("id");
				String tempUserId = crs.getString("user_id");
				
				String tempDetailId = crs.getString("c_id");
				String tempUserName = crs.getString("user_name");
				String tempPlayType = crs.getString("play_type");//主表中的play_type
				String tempPlayMode = crs.getString("play_mode");//主表中的play_mode					
				String tempTzNum = crs.getString("touzhu_num");//主表中的touzhu_num
				String tempTzTimes = crs.getString("num_times");
				String tempIsZhuitou = crs.getString("is_zhuitou");
				String tempParentIssueNum = crs.getString("parent_issue_num");
				String tempOpTime = JDateToolkit.getNowDate1();//得到当前操作时间
				String tempZjType = crs.getString("type_name");//子表中玩法类型
				String cPlayType = crs.getString("c_play_type");//子表中的play_type
				String cPlayMode = crs.getString("c_play_mode");//子表中的play_mode
				String tempNumDetail = crs.getString("num_detail");//子表中分解后的投注号码
				
				//判断中奖后删除其余的投注信息用的
				String tempHaveZhuitou = crs.getString("have_zt_flag");//是否有追投
				String tempParentId = crs.getString("parent_id");//info表中的投注父ID
				String tempInfoIsZhuitou = crs.getString("is_zhuitou");//info表中的是否是追投
				
				
				zjUserSet.add(tempUserId);//记录该期中奖的用户2011-02-10
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
				int zjZhushu = 0;//中奖的注数(如投：12345,12345，则注数是2
				int tzTimes = Integer.parseInt(tempTzTimes);//得到投注的倍数
				int totalZjZhushu = 0;//总的中奖注数=单注中奖注数*倍数
				int totalZjMoney = 0;//总的中奖金额
				if(cPlayType.equals("wuxing")){					
					if(tempZjType.equals("tx")){
						//通选
						int ttWxTxMoney = Integer.parseInt(prizeMap.get(ConstantSymbol.SSC_5X_TX));
						int ttWxTxJiang2Money = Integer.parseInt(prizeMap.get(ConstantSymbol.SSC_5X_TX_QIAN3HOU3));//前三后三 对应二等奖
						int ttWxTxJiang3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.SSC_5X_TX_QIAN2HOU2));//前二后二 对应三等奖
						
						int perMoney = 0;//每个单注的总奖金
						if(cPlayMode.equals(ConstantSymbol.SSC_5X_TX)){
							int jiang1Zhushu = 0; 
							int jiang2QianZhushu = 0; 
							int jiang2HouZhushu = 0; 
							int jiang3QianZhushu = 0; 
							int jiang3HouZhushu = 0; 
								
							int jiang1Money = ttWxTxMoney;	
							int jiang2Money = ttWxTxJiang2Money;	
							int jiang3Money = ttWxTxJiang3Money;	
							//计算一等奖	
							jiang1Zhushu = JStringToolkit.getSubNum(tempNumDetail,","+wuxing+",");//当前记录中，中一等奖的数量
							//同时兼中2个二等奖和2个三等奖
							if(jiang1Zhushu > 0){
								jiang2QianZhushu = jiang2QianZhushu + jiang1Zhushu; 
								jiang2HouZhushu = jiang2HouZhushu + jiang1Zhushu; 
								jiang3QianZhushu =  jiang3QianZhushu + jiang1Zhushu; 
								jiang3HouZhushu =  jiang3HouZhushu + jiang1Zhushu;  
							}

							//计算二等奖，同时兼中1个三等奖
							int tempJiang2QianZhushu = JStringToolkit.getWuxingTxSubNum(tempNumDetail,",",","+wuxingQian3,wuxing);//当前记录中，除了一等奖外，中二等奖的注数
							int tempJiang2HouZhushu = JStringToolkit.getWuxingTxSubNum(tempNumDetail,",",wuxingHou3+",",wuxing);//当前记录中，除了一等奖外，中二等奖的注数
 
							if(tempJiang2QianZhushu > 0){
								jiang2QianZhushu =  jiang2QianZhushu + tempJiang2QianZhushu; //二等奖的注数增加
								jiang3QianZhushu =  jiang3QianZhushu + tempJiang2QianZhushu; //三等奖等奖的注数增加
							}
							if(tempJiang2HouZhushu > 0){
								jiang2HouZhushu =  jiang2HouZhushu + tempJiang2HouZhushu; 
								jiang3HouZhushu =  jiang3HouZhushu + tempJiang2HouZhushu; 
							}
							//计算三等奖
							int tempJiang3QianZhushu = JStringToolkit.getWuxingTxSubNum(tempNumDetail,",",","+wuxingQian2,","+wuxingQian3);//当前记录中，除了一等奖外，中二等奖的注数
							int tempJiang3HouZhushu = JStringToolkit.getWuxingTxSubNum(tempNumDetail,",",wuxingHou2+",",wuxingHou3+",");//当前记录中，除了一等奖外，中二等奖的注数

							if(tempJiang3QianZhushu > 0){
								jiang3QianZhushu =  jiang3QianZhushu + tempJiang3QianZhushu; //三等奖等奖的注数增加
							}
							if(tempJiang3HouZhushu > 0){
								jiang3HouZhushu =  jiang3HouZhushu + tempJiang3HouZhushu; //三等奖等奖的注数增加
							}
							//计算中奖总注数及每注的总金额
							zjZhushu = jiang1Zhushu + jiang2QianZhushu + jiang2HouZhushu + jiang3QianZhushu + jiang3HouZhushu;	
							perMoney =  jiang1Money * jiang1Zhushu + jiang2Money * (jiang2QianZhushu + jiang2HouZhushu) + jiang3Money * (jiang3QianZhushu + jiang3HouZhushu);//每个单注的奖金(一等+二等奖+三等奖）
						}
						
						if(cPlayMode.equals(ConstantSymbol.SSC_5X_TX_QIAN3HOU3)){
							int jiang2QianZhushu = 0; 
							int jiang2HouZhushu = 0; 
							int jiang3QianZhushu = 0; 
							int jiang3HouZhushu = 0; 
								
							int jiang2Money = ttWxTxJiang2Money;	
							int jiang3Money = ttWxTxJiang3Money;	
							
							//-------------计算当前记录中，二等奖的数量-------------------
							jiang2QianZhushu = JStringToolkit.getSubNum(tempNumDetail,","+wuxingQian3);
							jiang2HouZhushu = JStringToolkit.getSubNum(tempNumDetail,wuxingHou3+",");	//2011-10-04前三后三改成五星通选2等奖，统一用wuxingqian3类型
							//二等奖，同时兼中1个三等奖
							if(jiang2QianZhushu > 0){
								jiang3QianZhushu =  jiang3QianZhushu + jiang2QianZhushu; //三等奖等奖的注数增加
							}
							if(jiang2HouZhushu > 0){
								jiang3HouZhushu =  jiang3HouZhushu + jiang2HouZhushu; 
							}
							//-------------计算当前记录中，三等奖的数量-------------------
							int tempJiang3QianZhushu = JStringToolkit.getWuxingTxSubNum(tempNumDetail,",",","+wuxingQian2,wuxingQian3+",");//当前记录中，除了一等奖外，中二等奖的注数
							int tempJiang3HouZhushu = JStringToolkit.getWuxingTxSubNum(tempNumDetail,",",","+wuxingHou2,wuxingHou3+",");//当前记录中，除了一等奖外，中二等奖的注数
							
							if(tempJiang3QianZhushu > 0){
								jiang3QianZhushu =  jiang3QianZhushu + tempJiang3QianZhushu; //三等奖等奖的注数增加
							}
							if(tempJiang3HouZhushu > 0){
								jiang3HouZhushu =  jiang3HouZhushu + tempJiang3HouZhushu; //三等奖等奖的注数增加
							}
							
							//计算中奖总注数及每注的总金额
							zjZhushu = jiang2QianZhushu + jiang2HouZhushu + jiang3QianZhushu + jiang3HouZhushu;
							perMoney = jiang2Money*(jiang2QianZhushu + jiang2HouZhushu) + jiang3Money * (jiang3QianZhushu + jiang3HouZhushu);//每注的奖金
						}
						if(cPlayMode.equals(ConstantSymbol.SSC_5X_TX_QIAN2HOU2)){
							////当前记录中，中奖的数量
							int qian2Zhushu = JStringToolkit.getSubNum(tempNumDetail,","+wuxingQian2);
							int hou2Zhushu = JStringToolkit.getSubNum(tempNumDetail,wuxingHou2+",");//2011-10-04前2后2改成五星通选3等奖，统一用wuxingqian2类型
							//总的中奖注数
							zjZhushu = qian2Zhushu + hou2Zhushu;
							//每注的奖金
							perMoney =  ttWxTxJiang3Money * zjZhushu;//每个单注的奖金(只有三等奖）
						}
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数							
						totalZjMoney = perMoney * tzTimes;//总奖金
						
												
					}else if(tempZjType.equals("zuxuan")){
						//组选
						int wuxingMoney = 0;//Integer.parseInt(prizeMap.get("wuxingzhixuan"))*intZjZhushu;//每个单注的奖金
						
						if(cPlayMode.equals("zuchong2")){
								//intZjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+wuxing+",");
								int totalCount_1 = 0;
								for(String chongNum : zuchong2List){
									totalCount_1 = totalCount_1 + JStringToolkit.getSubNum(tempNumDetail,","+chongNum+",");
								}
								zjZhushu = totalCount_1;
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzuchong2"))*zjZhushu;//每个单注的奖金
								
						}else if(cPlayMode.equals("zuchong3")){
							zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+zuchong3Num+",");;
							wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzuchong3"))*zjZhushu;//每个单注的奖金
							
					    }else if(cPlayMode.equals("zuchong4")){
							zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+zuchong4Num+",");;
							wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzuchong4"))*zjZhushu;//每个单注的奖金
							
					    }else{
							zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+wuxing+",");
							
							if(cPlayMode.equals("zu120")){
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzu120"))*zjZhushu;//每个单注的奖金
							}
							if(cPlayMode.equals("zu60")){
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzu60"))*zjZhushu;//每个单注的奖金
							}
							if(cPlayMode.equals("zu30")){
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzu30"))*zjZhushu;//每个单注的奖金
							}
							if(cPlayMode.equals("zu20")){
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzu20"))*zjZhushu;//每个单注的奖金
							}
							if(cPlayMode.equals("zu10")){
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzu10"))*zjZhushu;//每个单注的奖金
							}
							if(cPlayMode.equals("zu5")){
								wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzu5"))*zjZhushu;//每个单注的奖金
							}
						}
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = wuxingMoney * tzTimes;//总奖金
						
					}else{
						//直选
						zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+wuxing+",");
						int wuxingMoney = Integer.parseInt(prizeMap.get("wuxingzhixuan"))*zjZhushu;//每个单注的奖金
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = wuxingMoney * tzTimes;//总奖金
					}
				}
				if(cPlayType.equals("sixing")){
					//if(tempZjType.equals("zhixuan")){
					if(tempZjType.indexOf("zhixuan")!= -1 || tempZjType.indexOf("zuhe")!= -1){//对直选或者5星4星组合
						zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+sixing+",");
						int sixingMoney = Integer.parseInt(prizeMap.get("sixingzhixuan"))*zjZhushu;//每个单注的奖金
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = sixingMoney * tzTimes;//总奖金
					}else if(tempZjType.equals("zuxuan")){
						int sixingMoney = 0;
						zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+sixing+",");
						
						if(cPlayMode.equals("zu24")){
							sixingMoney = Integer.parseInt(prizeMap.get("sixingzu24"))*zjZhushu;//每个单注的奖金
						}
						if(cPlayMode.equals("zu12")){
							sixingMoney = Integer.parseInt(prizeMap.get("sixingzu12"))*zjZhushu;//每个单注的奖金
						}
						if(cPlayMode.equals("zu6")){
							sixingMoney = Integer.parseInt(prizeMap.get("sixingzu6"))*zjZhushu;//每个单注的奖金
						}
						if(cPlayMode.equals("zu4")){
							sixingMoney = Integer.parseInt(prizeMap.get("sixingzu4"))*zjZhushu;//每个单注的奖金
						}
					
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = sixingMoney * tzTimes;//总奖金
					}
				
				}
				if(cPlayType.equals("sanxing")){
					if(cPlayMode.equals("zhixuan")){
						zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+sanxing+",");
						int sanxinggMoney = Integer.parseInt(prizeMap.get("sanxingzhixuan"))*zjZhushu;//每个单注的奖金
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = sanxinggMoney * tzTimes;//总奖金
					}else if(cPlayMode.equals("zu3")){
						int zu31Times = JStringToolkit.getSubNum(tempNumDetail,","+sanxingZu31+",");
						int zu31Money = Integer.parseInt(prizeMap.get("sanxingzu3"))*zu31Times;
						
						int zu32Times = JStringToolkit.getSubNum(tempNumDetail,","+sanxingZu32+",");
						int zu32Money = Integer.parseInt(prizeMap.get("sanxingzu3"))*zu32Times;
						
						zjZhushu = zu31Times + zu32Times;
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						int intEachMoney = zu31Money + zu32Money ;//每个单注的总奖金
						totalZjMoney = intEachMoney * tzTimes;//总奖金
					}else if(cPlayMode.equals("zu6")){
						zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+sanxingZu6+",");
						int zu6Money = Integer.parseInt(prizeMap.get("sanxingzu6"))*zjZhushu;
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = zu6Money * tzTimes;//总奖金
					}						
				}
				
				if(cPlayType.equals("erxing")){
					if(cPlayMode.equals("zhixuan")){
						zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+erxing+",");
						
						int erMoney = Integer.parseInt(prizeMap.get("erxingzhixuan"))*zjZhushu;							
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						totalZjMoney = erMoney * tzTimes;//总奖金
					}else if(cPlayMode.equals("zuxuan")){
						int erxingZx1Times = JStringToolkit.getSubNum(tempNumDetail,","+erxingZx1+",");
						int er1Money = Integer.parseInt(prizeMap.get("erxingzuxuan"))*erxingZx1Times;	
						
						int erxingZx2Times = JStringToolkit.getSubNum(tempNumDetail,","+erxingZx2+",");
						int er2Money = Integer.parseInt(prizeMap.get("erxingzuxuan"))*erxingZx2Times;	

						zjZhushu = erxingZx1Times + erxingZx2Times;
						
						totalZjZhushu = zjZhushu * tzTimes;//总注数
						int intEachMoney = er1Money + er2Money ;//每个单注的总奖金
						totalZjMoney = intEachMoney * tzTimes;//总奖金
					}
				}
				if(cPlayType.equals("yixing")){
					zjZhushu = JStringToolkit.getSubNum(tempNumDetail,","+yixing+",");
					
					int yiMoney = Integer.parseInt(prizeMap.get("yixingzhixuan"))*zjZhushu;							
					totalZjZhushu = zjZhushu * tzTimes;//总注数
					totalZjMoney = yiMoney * tzTimes;//总奖金
				}
				if(cPlayType.equals("dxds")){
					int dxds1Times = JStringToolkit.getSubNum(tempNumDetail,","+dxds1+",");
					int dxds1Money = Integer.parseInt(prizeMap.get("dxdszhixuan"))*dxds1Times;	
					
					int dxds2Times = JStringToolkit.getSubNum(tempNumDetail,","+dxds2+",");
					int dxds2Money = Integer.parseInt(prizeMap.get("dxdszhixuan"))*dxds2Times;	
					
					int dxds3Times = JStringToolkit.getSubNum(tempNumDetail,","+dxds3+",");
					int dxds3Money = Integer.parseInt(prizeMap.get("dxdszhixuan"))*dxds3Times;	
					
					int dxds4Times = JStringToolkit.getSubNum(tempNumDetail,","+dxds4+",");
					int dxds4Money = Integer.parseInt(prizeMap.get("dxdszhixuan"))*dxds4Times;	
					
					zjZhushu = dxds1Times + dxds2Times + dxds3Times + dxds4Times;
					int intEachMoney = dxds1Money + dxds2Money + dxds3Money + dxds4Money;
					totalZjZhushu = zjZhushu * tzTimes;//总注数
					totalZjMoney = intEachMoney * tzTimes;//总奖金
				}
				if(cPlayType.equals("renxuan1")){
					int intEachMoney = 0;
					if(strRenxuan1 != null && !strRenxuan1.equals("")){
						String[] aryRx1 = JStringToolkit.splitString(strRenxuan1, ",");
						for(String s : aryRx1){
							int renxuan1Times = JStringToolkit.getSubNum(tempNumDetail,","+s+",");
							int renxuan1Money = Integer.parseInt(prizeMap.get("renxuan1"))*renxuan1Times;	
							zjZhushu = zjZhushu + renxuan1Times;
							intEachMoney = intEachMoney + renxuan1Money;
						}
					}
					totalZjZhushu = zjZhushu * tzTimes;//总注数
					totalZjMoney = intEachMoney * tzTimes;//总奖金
				}
				if(cPlayType.equals("renxuan2")){
					int intEachMoney = 0;
					if(strRenxuan2 != null && !strRenxuan2.equals("")){
						String[] aryRx2 = JStringToolkit.splitString(strRenxuan2, ",");
						for(String s : aryRx2){
							int renxuan2Times = JStringToolkit.getSubNum(tempNumDetail,","+s+",");
							int renxuan2Money = Integer.parseInt(prizeMap.get("renxuan2"))*renxuan2Times;	
							zjZhushu = zjZhushu + renxuan2Times;
							intEachMoney = intEachMoney + renxuan2Money;
						}
					}
					totalZjZhushu = zjZhushu * tzTimes;//总注数
					totalZjMoney = intEachMoney * tzTimes;//总奖金
				}
				if(cPlayType.equals("renxuan3")){
					int intEachMoney = 0;
					if(strRenxuan3 != null && !strRenxuan3.equals("")){
						String[] aryRx3 = JStringToolkit.splitString(strRenxuan3, ",");
						for(String s : aryRx3){
							int renxuan3Times = JStringToolkit.getSubNum(tempNumDetail,","+s+",");
							int renxuan3Money = Integer.parseInt(prizeMap.get("renxuan3"))*renxuan3Times;	
							zjZhushu = zjZhushu + renxuan3Times;
							intEachMoney = intEachMoney + renxuan3Money;
						}
					}
					totalZjZhushu = zjZhushu * tzTimes;//总注数
					totalZjMoney = intEachMoney * tzTimes;//总奖金
				}
				
				
				zjInfo.setZjZhushu(String.valueOf(zjZhushu));//设置中奖注数
						
				String tempZjMoney = "";//奖金
				String tempMoneyOfZjType = "";//当前玩法中奖的奖金设置

				//根据玩法,得到相应的奖金
				String tempKey = "";
				if(cPlayType.equals("renxuan1") || cPlayType.equals("renxuan2") || cPlayType.equals("renxuan3")){
					tempKey = cPlayType;
				}else{
					tempKey = cPlayType+cPlayMode;
				}
				
				String strPrizeMoney = prizeMap.get(tempKey);//相应玩法对应的中奖奖金设置
				tempZjMoney = String.valueOf(totalZjMoney);//总的中奖金额
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
				zjInfo.setZjType(tempZjType);
				zjInfo.setZjMoney(tempZjMoney);
				zjInfo.setTotalZjZhushu(String.valueOf(totalZjZhushu));
				zjInfo.setMoneyOfZjType(tempMoneyOfZjType);
				
				zjInfolist.add(zjInfo);					
			}
		}catch(Exception e){
			logger.error("中奖信息保存异常:" + e.toString());
			e.printStackTrace();
		}
		//多期投注的情况下，如果其中有一期中奖，那么中奖后的其余期数投注号码要自动删除，删除后用户余额与所投金额要相应增加与扣除。（参考快乐扑克）
		if(zjNumList != null && !zjNumList.isEmpty()){
			
//					List<String> idList = new ArrayList<String>();//存放要删除的中奖记录ID
//					Map<String,Integer> moneyMap = new HashMap<String,Integer>();//存放要返还的用户及相应的金额
			for(int i = 0; i < zjNumList.size(); i++){
				List<String> tempList = new ArrayList<String>();
				SscZjNum info = zjNumList.get(i);
				if(info.isFirstZj() ){
					if(info.getHaveZhuitou().equals("1")){
						//查询PId为该ID 并且 have_zhuitou != 1的记录的所有的子记录
						getPidList(info.getTzId(),info.getIssueNum(),info.getUserId(),true,idList,moneyMap);
					}
				}else{
					//查询所有PID为该PID的子ID，如果不是第一个投注，则PID应该是当前投注记录的PID
					 getPidList(info.getParentId(),info.getIssueNum(),info.getUserId(),false,idList,moneyMap);
				}
			}
		}
			
		
//		}else{
//			String temp = "中奖信息删除失败!";
//			logger.info(temp);
//			return temp;
//		} 
	}
	/**
	 * 得到相应的用户应返还的金额
	 * @param ids
	 * @return
	 */
	public Map<String,Integer> getReturnMoney(String ids){
		if(ids == null || ids.equals("")){
			return null;
		}
		String sql = "";
		sql = "select user_id,user_name,sum(touzhu_money) as touzhu_money from ssc_tz_info "
			+ " where id in ("+ids+")"
			+ " group by user_id,user_name ";
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		Map<String,Integer> map = new HashMap<String,Integer>();
		try{			 
			while(crs.next()){
				String userId = crs.getString("user_id");
				String userName = crs.getString("user_name");
				int touzhuMoney = crs.getInt("touzhu_money");
				map.put(userId, touzhuMoney);
				
			}
		}catch(Exception e){
			logger.error("查询应返还金额信息错误"+e.toString());
		}
		return map;
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
	public void getPidList(String tzId,String issueNum,String userId,boolean isFirst,List<String> idList,Map<String,Integer> moneyMap){
		String sql = "";
		if(isFirst){
			sql = "select id,user_id,touzhu_money from ssc_tz_info where user_id='"+userId+"' and parent_id='"+tzId+"' and issue_num != '"+issueNum+"' and have_zt_flag='0'";
		}else{
			sql = "select id,user_id,touzhu_money from ssc_tz_info where  user_id='"+userId+"' and parent_id='"+tzId + "' and issue_num != '"+issueNum+"' and ((issue_num+0) > ('"+issueNum+"'+0))";
		}
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
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SscZjInfo> getZjInfo(String issueNum,String userName) {
		List<SscZjInfo> list = new ArrayList<SscZjInfo>();
		List<SscZjInfo> tempList = new ArrayList<SscZjInfo>();
		 
 		
		try{	
			tempList = sscZjInfoDao.getZjInfo(issueNum, userName);
			//将里面的字母转化为中文解释
			if(tempList != null){
				for(SscZjInfo info : tempList){
					String playType = info.getPlayType();//玩法类型
					String typeName = info.getZjType();
					String playMode = info.getPlayMode();//玩法模式
					String zjRule = info.getZjType();//中奖规则
					
					String tempPlayType = ConvertToolkit.sscPlayTypeConvert(playType,typeName);
					String tempPlayMode = ConvertToolkit.sscPlayModeConvert(playMode);
					String tempZjRule = ConvertToolkit.sscZjRuleConvert(zjRule);
					 
					 
					info.setPlayType(tempPlayType);
					info.setPlayMode(tempPlayMode);
					info.setZjType(tempZjRule);
					
					list.add(info);
				}
			}
		}catch(DAOException ee){
				logger.error("中奖信息查询异常:" + ee.toString());
		}
		return list;
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
			logger.error("时时彩删除中奖号码错误：" + e.toString());
		}
		return flag;		
	}
	/**
	 * 得到指定用户的中奖信息
	 * admin可以查询所有的信息，普通用户只可以查询本人的中奖信息
	 * @return
	 * @throws DAOException
	 */
	public List<SscZjInfo> getZjInfo(String issueNum,String userName,String userId) {
		List<SscZjInfo> list = new ArrayList<SscZjInfo>();
		List<SscZjInfo> tempList = new ArrayList<SscZjInfo>();
		
		try{	
			tempList = sscZjInfoDao.getZjInfo(issueNum, userName,userId);
			//将里面的字母转化为中文解释
			if(tempList != null){
				for(SscZjInfo info : tempList){
					String playType = info.getPlayType();//玩法类型
					String typeName = info.getZjType();
					String playMode = info.getPlayMode();//玩法模式
					String zjRule = info.getZjType();//中奖规则
					
					String tempPlayType = ConvertToolkit.sscPlayTypeConvert(playType,typeName);
					String tempPlayMode = ConvertToolkit.sscPlayModeConvert(playMode);
					String tempRule = ConvertToolkit.sscZjRuleConvert(zjRule);
					 
					 
					info.setPlayType(tempPlayType);
					info.setPlayMode(tempPlayMode);
					info.setZjType(tempRule);
					
					list.add(info);
				}
			}
			//list = sscZjInfoDao.getZjInfo(issueNum, userName,userId);
		}catch(DAOException ee){
			logger.error("得到指定用户的中奖信息异常:" + ee.toString());
		}
		return list;
	}
	/**
	 * 删除指定用户某一个中奖号码信息，同时删除数据库中已存在的用户的投注信息
	 * 同时将用户的账户中扣除相应的奖金（目前先不做)
	 * @param issueNum
	 * @return
	 */
	public boolean delUserZjInfo(String tzId,String userId){
		boolean flag = false;
		try{
			flag = sscZjInfoDao.delUserZjInfo(tzId, userId);
		}catch(DAOException e){
			logger.error("时时彩删除指定用户中奖信息错误：" + e.toString());
		}
		return flag;
	}
	/**
	 * 查询指定用户总的投注注数及投注金额
	 * @param issueNum
	 * @return
	 */
	public SscTzInfo getUserTotalTzInfo(String userId){
		SscTzInfo info = new SscTzInfo();
		try{
			info = sscTzInfoDao.getUserTotalTzInfo(userId);
		}catch(DAOException e){
			logger.error("时时彩查询指定用户投注信息错误：" + e.toString());
		}
		return info;
	}
	/**
	 * 查询指定用户总的中奖注数及中奖金额
	 * @param issueNum
	 * @return
	 */
	public SscZjInfo getUserTotalZjInfo(String issueNum,String userId){
		SscZjInfo info = new SscZjInfo();
		try{
			info = sscZjInfoDao.getUserTotalZjInfo(null,userId);
		}catch(DAOException e){
			logger.error("时时彩查询指定用户总的中奖信息错误：" + e.toString());
		}
		return info;
	}
	class ZjNumAndCount{
		private String num;//数字
		private int count;//次数
		
		public ZjNumAndCount(String strNum,int ttCount){
			this.num = strNum;
			this.count = ttCount;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	}
}
