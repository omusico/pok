package com.onmyway.ssc.play.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pubmethpac.IssInfoCal;
import util.JDateToolkit;
import beanpac.SingleIssueInfo;

import com.onmyway.ssc.manage.dao.ISscBaseInfoConfigDao;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 16, 2010 9:05:32 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SscIssuePublicServiceImpl implements ISscIssuePublicService{
	
	private Log logger = LogFactory.getLog(SscIssuePublicServiceImpl.class);
	
	private ISscBaseInfoConfigDao sscBaseInfoConfigDao;
	
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
	
	/**
	 * 得到当天的开奖信息
	 * @return
	 */
	public SscBaseInfoConfig getTodayConfig(){
		SscBaseInfoConfig info = new SscBaseInfoConfig();
		String today = JDateToolkit.getNowDate2();
		List<SscBaseInfoConfig> list = sscBaseInfoConfigDao.getConfigInfoOfDay(today);
		if(list != null && !list.isEmpty()){
			info = list.get(0);
		}else{
			info.setBeginHour("0");
			info.setBeginIssue("0");
			info.setBeginMin("0");
			info.setDayIssueTimes("0");
			info.setSellMin("0");
			info.setWinMin("0");
			info.setBeginDate("0");
			
			info.setOffsetFlag("+");
			info.setOffsetTime("0");
		}
		return info;
	}

	/**
	 * 得到当天其他的发行期号
	 * @return
	 */
	public List<SingleIssueInfo> getTodayOtherIssue(){
		
		List<SingleIssueInfo> list  = new ArrayList<SingleIssueInfo>();
		
		SscBaseInfoConfig info = getTodayConfig();
		//当天的设置信息
		String beginDate = info.getBeginDate();
		String beginIssue = info.getBeginIssue();
		String beginHour = info.getBeginHour();
		String beginMin = info.getBeginMin();
		String sellMin = info.getSellMin();
		String winMin = info.getWinMin();
		String dayIssueTimes= info.getDayIssueTimes();
		String operTime = info.getOperTime();
		
		if( !beginDate.equals("0") ){
			 int intBeginIssue = Integer.parseInt(beginIssue);
			 int intBeginHour = Integer.parseInt(beginHour);
			 int intBeginMin = Integer.parseInt(beginMin);
			 int intSellMin = Integer.parseInt(sellMin);
			 int intWinMin = Integer.parseInt(winMin);
			 int intDayIssueTimes = Integer.parseInt(dayIssueTimes);
			 
			 int intBeginTimSec = intBeginHour*60*60+intBeginMin*60;
			 int intSellSec = intSellMin*60;
			 int intWinSec = intWinMin*60;
			 int intEveryIssueSec = intSellSec+intWinSec;
			 
			 IssInfoCal objIssInfCal = new IssInfoCal();
			 int intCurIss = objIssInfCal.getCurIss(intBeginTimSec,intEveryIssueSec,intDayIssueTimes);
			 
			 //服务器时间
			 Date sysDate=new Date();
			 SimpleDateFormat sysDateFormat=new SimpleDateFormat("yyyyMMdd");
			 String strSysDate=sysDateFormat.format(sysDate);
			 int currentIssue = intBeginIssue + intCurIss;//当期的期数
			 int issueLength = intDayIssueTimes - intCurIss;//当前期以0开始，0为第一期。如果当前期大于总期数，为负，不执行循环，vector.size为0
			 //计算当天剩余为开奖的发行期数
			 for(int i=0;i<issueLength;i++){
				 int issNumTemp=intCurIss+i;
				 String strIssNum = String.valueOf(intBeginIssue + issNumTemp);
				 int wagEndTimSec = intBeginTimSec + (issNumTemp + 1) * intEveryIssueSec - intEveryIssueSec;
				 int winEndTimeSec = wagEndTimSec+intEveryIssueSec;
				 
				 SingleIssueInfo objSingleIssueInfo=new SingleIssueInfo();
				 objSingleIssueInfo.setIssueNumber(strIssNum);
				 objSingleIssueInfo.setWagerEndTime(objIssInfCal.getStrTime(wagEndTimSec, sysDate));
				 objSingleIssueInfo.setWinPrizeTime(objIssInfCal.getStrTime(winEndTimeSec, sysDate));
				 
				 list.add(objSingleIssueInfo);
			 }
		}
		
		 
		return list;
	}
	/**
	 * 得到当前正在发行的期数
	 * @return
	 */
	public String getCurrentIssue(){
		
		String str = "";
		
		SscBaseInfoConfig info = getTodayConfig();
		//当天的设置信息
		String beginDate = info.getBeginDate();
		String beginIssue = info.getBeginIssue();
		String beginHour = info.getBeginHour();
		String beginMin = info.getBeginMin();
		String sellMin = info.getSellMin();
		String winMin = info.getWinMin();
		
		String offsetFlag = info.getOffsetFlag();//偏移量
		String offsetTime = info.getOffsetTime();//偏移时间 
		
		String dayIssueTimes= info.getDayIssueTimes();
		String operTime = info.getOperTime();
		
		if(!beginDate.equals("0")){
			 int intBeginIssue = Integer.parseInt(beginIssue);
			 int intBeginHour = Integer.parseInt(beginHour);
			 int intBeginMin = Integer.parseInt(beginMin);
			 int intSellMin = Integer.parseInt(sellMin);
			 int intWinMin = Integer.parseInt(winMin);
			 int intDayIssueTimes = Integer.parseInt(dayIssueTimes);
			 
			 int intBeginTimSec = intBeginHour*60*60+intBeginMin*60;
			 
				//偏移量
			 if(offsetFlag.equals("+")){
				 intBeginTimSec = intBeginTimSec + Integer.parseInt(offsetTime);
			 }
			 if(offsetFlag.equals("-")){
				 intBeginTimSec = intBeginTimSec - Integer.parseInt(offsetTime);
			 }
			 
			 int intSellSec = intSellMin*60;
			 int intWinSec = intWinMin*60;
			 int intEveryIssueSec = intSellSec+intWinSec;
			 
			 IssInfoCal objIssInfCal = new IssInfoCal();
			 int intCurIss = objIssInfCal.getCurIss(intBeginTimSec,intEveryIssueSec,intDayIssueTimes);
			 
			 //服务器时间
			 Date sysDate=new Date();
			 SimpleDateFormat sysDateFormat=new SimpleDateFormat("yyyyMMdd");
			 String strSysDate=sysDateFormat.format(sysDate);
			 int currentIssue = intBeginIssue + intCurIss;
			 str = String.valueOf(currentIssue);
		}
		
		 
		return str;
	}
}
