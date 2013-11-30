package com.onmyway.common.sxw;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
import com.onmyway.ssc.play.service.ISscIssuePublicService;
import com.onmyway.sxw.play.service.ISxwIssuePublicService;
import com.onmyway.sxw.manage.model.SxwBaseInfoConfig;
/**
 * @Title: 时时彩的通用工具
 * @Description:
 * @Create on: Aug 18, 2010 6:59:43 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwCommonTool {


	private ISxwIssuePublicService sxwIssuePublicService;

	 

	String serverYear = "";
	String serverMonth = "";
	String serverDate = "";
	String serverHour = "";
	String serverMinute = "";
	String serverSecond = "";

	String beginDate = "0";// 开始时期
	String beginIssue = "0";// 开始期数
	String beginHour = "0";// 每天开始的小时
	String beginMin = "0";// 每天开始的分钟
	String sellMin = "0";// 每期销售时间
	String winMin = "0";// 每期开奖时间
	String dayIssueTimes = "0";// 每天销售多少期

	public String comparePlayAndServerTime(String gameType) {
		// 服务器当前时间
		GregorianCalendar calendar = new GregorianCalendar();
		Integer intYear = new Integer(calendar.get(Calendar.YEAR));
		Integer intMonth = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer intDay = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer intHour = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer intMinute = new Integer(calendar.get(Calendar.MINUTE));
		Integer intSecond = new Integer(calendar.get(Calendar.SECOND));
		serverYear = intYear.toString();
		serverMonth = intMonth.toString();
		serverDate = intDay.toString();
		serverHour = intHour.toString();
		serverMinute = intMinute.toString();
		serverSecond = intSecond.toString();

		// 得到相应的数据库设置的时间
		SxwBaseInfoConfig configinfo = sxwIssuePublicService.getTodayConfig();
		beginDate = configinfo.getBeginDate();// 开始日期
		beginIssue = configinfo.getBeginIssue();// 开始期数
		beginHour = configinfo.getBeginHour();// 每天开始的小时
		beginMin = configinfo.getBeginMin();// 每天开始的分钟
		sellMin = configinfo.getSellMin();// 每期销售时间
		winMin = configinfo.getWinMin();// 每期开奖时间
		dayIssueTimes = configinfo.getDayIssueTimes();// 每天销售多少期

		String strYMD = serverYear  + "-" + (Integer.parseInt(serverMonth) < 10 ? ("0" + serverMonth) : serverMonth) + "-" + (Integer.parseInt(serverDate) < 10 ? ("0" + serverDate)
						: serverDate);

		if (!strYMD.equals(beginDate)) {
			return "0";
		}

		String issStat = "0";
		// 每天开始销售的时间
		int intBegTime = Integer.parseInt(beginHour) * 60 * 60
				+ Integer.parseInt(beginMin) * 60;
		// 当前服务器时间
		int serverCurSec = Integer.parseInt(serverHour) * 60 * 60
				+ Integer.parseInt(serverMinute) * 60
				+ Integer.parseInt(serverSecond);
		// 每期总的时间，10分钟销售+5开奖
		int totalSec = Integer.parseInt(sellMin) + Integer.parseInt(winMin);//
		// //服务器时间与9:00的差
		int elapFromBeg = serverCurSec - intBegTime;

		// int issNum =
		// Math.floor(elapFromBeg/totalSec);//服务器时间与9:00的差，除以15分钟，加开始期，得到期号
		// 当前期过了多少时间
		int issElapSec = elapFromBeg % (totalSec * 60);// 除以15分钟取余数,求得当前期过了多少时间
		// 当整期还有多少 秒
		int issLeftSec = totalSec * 60 - issElapSec;
		// 一天的秒数
		int midNig = 24 * 60 * 60;
		//
		int endTime = totalSec * (Integer.parseInt(dayIssueTimes)) * 60
				+ intBegTime;

		if (serverCurSec > intBegTime && serverCurSec < endTime) {
			if (issLeftSec < Integer.parseInt(winMin) * 60) {
				// int winLeftSec=issLeftSec;
				// issStat="正在开奖";
				issStat = "2";
				// cacuCouDow(winLeftSec,couDowShow);
				// 当前期正在开奖
			} else {
				// issStat="正在销售";
				issStat = "1";
				// cacuCouDow(sellLeftSec,couDowShow);
			}
			// issShow=issNum+begIss;
		} else {
			// issStat="今天停止销售";
			issStat = "0";
		}
		return issStat;
	}
	/**
	 * 根据玩法类型，得到当前在服务器上的发行期号
	 * @param gameType
	 * @return
	 */
	public String getSxwNowIssueNumOnServer() {
		// 服务器当前时间
		GregorianCalendar calendar = new GregorianCalendar();
		Integer intYear = new Integer(calendar.get(Calendar.YEAR));
		Integer intMonth = new Integer(calendar.get(Calendar.MONTH) + 1);
		Integer intDay = new Integer(calendar.get(Calendar.DAY_OF_MONTH));
		Integer intHour = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer intMinute = new Integer(calendar.get(Calendar.MINUTE));
		Integer intSecond = new Integer(calendar.get(Calendar.SECOND));
		serverYear = intYear.toString();
		serverMonth = intMonth.toString();
		serverDate = intDay.toString();
		serverHour = intHour.toString();
		serverMinute = intMinute.toString();
		serverSecond = intSecond.toString();

		// 得到相应的数据库设置的时间

		ISxwIssuePublicService iService = (ISxwIssuePublicService)SpringContextUtil.getBean("sxwIssuePublicService");
		SxwBaseInfoConfig configinfo = iService.getTodayConfig();
		beginDate = configinfo.getBeginDate();// 开始日期
		beginIssue = configinfo.getBeginIssue();// 开始期数
		beginHour = configinfo.getBeginHour();// 每天开始的小时
		beginMin = configinfo.getBeginMin();// 每天开始的分钟
		sellMin = configinfo.getSellMin();// 每期销售时间
		winMin = configinfo.getWinMin();// 每期开奖时间
		dayIssueTimes = configinfo.getDayIssueTimes();// 每天销售多少期

		String strYMD = serverYear  + "-" + (Integer.parseInt(serverMonth) < 10 ? ("0" + serverMonth) : serverMonth) + "-" + (Integer.parseInt(serverDate) < 10 ? ("0" + serverDate)
						: serverDate);

		if (!strYMD.equals(beginDate)) {
			return "0";
		}

		String issStat = "0";
		// 每天开始销售的时间
		int intBegTime = Integer.parseInt(beginHour) * 60 * 60
				+ Integer.parseInt(beginMin) * 60;
		// 当前服务器时间
		int serverCurSec = Integer.parseInt(serverHour) * 60 * 60
				+ Integer.parseInt(serverMinute) * 60
				+ Integer.parseInt(serverSecond);
		// 每期总的时间，10分钟销售+5开奖
		int totalSec = Integer.parseInt(sellMin) + Integer.parseInt(winMin);//
		// //服务器时间与9:00的差
		int elapFromBeg = serverCurSec - intBegTime;

		 int tempIssNum =  (int)(elapFromBeg/(totalSec*60));//服务器时间与9:00的差，除以15分钟，加开始期，得到期号
		 int intBeginIssue = 0;
		 try{
			 intBeginIssue = Integer.parseInt(beginIssue);
		 }catch(Exception e){
			 //do nothing
		 }
		 
		 return (intBeginIssue + tempIssNum) + "";
	}
	public ISxwIssuePublicService getSxwIssuePublicService() {
		return sxwIssuePublicService;
	}

	public void setSxwIssuePublicService(
			ISxwIssuePublicService sxwIssuePublicService) {
		this.sxwIssuePublicService = sxwIssuePublicService;
	}

}
