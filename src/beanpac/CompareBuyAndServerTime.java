package beanpac;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *@Description: 在下注时，将销售时间服务器的时间进行比较，判断是否已经停止销售
 *@Author:LJY
 *@E-mail:lijiyongcn@sohu.com
 *@Create on Aug 9, 2009 2:14:55 PM
 *@Ver:
 */
public class CompareBuyAndServerTime {
	
	String serverYear = "";
	String serverMonth = "";
	String serverDate = "";
	String serverHour = "";
	String serverMinute = "";
	String serverSecond = "";
		
	String begDate = "0";//开始时期
	String begIss = "0";//开始期数
	String begHour = "0";//每天开始的小时
	String begMin = "0";//每天开始的分钟
	String sellMin = "0";//每期销售时间
	String winMin = "0";//每期开奖时间
	String howManyIss = "0";//每天销售多少期
	
	//得到相应的数据库时间
	public CompareBuyAndServerTime(String gameType){
		//服务器当前时间
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
		
		//得到相应的数据库时间
		String tableName = getIssConInfoTable(gameType);
		
		IssConInfo obj=new IssConInfo();
		obj.getIssCon(tableName);		
		begDate = obj.getStrBegDate();//开始日期
		begIss = obj.getStrBegIss();//开始期数
		begHour = obj.getStrBegHour();//每天开始的小时
		begMin = obj.getStrBegMin();//每天开始的分钟
		sellMin = obj.getStrDurSell();//每期销售时间
		winMin = obj.getStrDurWin();//每期开奖时间
		howManyIss = obj.getStrHMIss();//每天销售多少期
		
		//测试代码
//		begDate = "20090809";//开始日期
//		begIss = "9001555";//开始期数
//		begHour = "7";//每天开始的小时
//		begMin = "00";//每天开始的分钟
//		sellMin = "8";//每期销售时间
//		winMin = "1";//每期开奖时间
//		howManyIss = "2";//每天销售多少期
			
	}
	
	/**
	 * 
	 *@Description:根据游戏类型，返回相应的投注情况表
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on May 31, 2009 11:31:39 AM
	 * @param gameType
	 * @return
	 */
	public String getIssConInfoTable(String gameType){
		String strTable = "";
		if(gameType.equals("poker")){  strTable       = "pokisscon";}   
		if(gameType.equals("laugh")){  strTable       = "lauisscon";}     
		if(gameType.equals("hap")){  strTable       = "hapisscon";}    
		if(gameType.equals("pth")){  strTable       = "pthisscon";}    
		return strTable;
	}
	
	/**
	 * 
	 *@Description:
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Aug 9, 2009 5:09:12 PM
	 * @param beginIssue 开始期数
	 * @param beginHour 开始时间的小时值
	 * @param beginMin  开始时间的分钟值
	 * @param sellMin 每期销售时间
	 * @param winMin 每期开奖时间
	 * @param dayIssueTimes 每天销售多少期
	 * @return issStat:销售状态 0:今天停止销售 1:正在销售 2:正在开奖
	 */
//	public String  caculTime(String begIss,String begHour,String begMin,String sellMin,String winMin,String howManyIss){//,issShow,issStat,couDowShow){
	public String  caculTime(String curIssueNum){
	  
	  String strYMD=serverYear+ (Integer.parseInt(serverMonth)<10 ? ("0"+serverMonth) : serverMonth) + (Integer.parseInt(serverDate)<10 ? ("0" + serverDate) : serverDate);
	  
	  if( !strYMD.equals(begDate) ){
		  return "0";
	  }
	  
	  String issStat = "0";
	  //每天开始销售的时间	
	  int intBegTime= Integer.parseInt(begHour)*60*60+Integer.parseInt(begMin)*60;
	  //当前服务器时间
	  int serverCurSec=Integer.parseInt(serverHour)*60*60+Integer.parseInt(serverMinute)*60+Integer.parseInt(serverSecond);
	  //每期总的时间，10分钟销售+5开奖
	  int totalSec=Integer.parseInt(sellMin)+Integer.parseInt(winMin);//
	  ////服务器时间与9:00的差
	  int elapFromBeg=serverCurSec-intBegTime;
	  
	  //int issNum = Math.floor(elapFromBeg/totalSec);//服务器时间与9:00的差，除以15分钟，加开始期，得到期号
	  //当前期过了多少时间
	  int issElapSec= elapFromBeg%(totalSec*60);//除以15分钟取余数,求得当前期过了多少时间
	  //当整期还有多少 秒
	  int issLeftSec = totalSec*60-issElapSec;
	  //一天的秒数
	  int midNig=24*60*60;
	  //
	  int endTime=totalSec*(Integer.parseInt(howManyIss))*60+intBegTime;
	  //alert("glotime:serverCurSec="+serverCurSec+",begin="+intBegTime+",end="+endTime);
	  
	  //判断当前投注的期号是否和服务器上正在期号相同，为了解决延迟的问题 2011-11-27
	  int tempIssNum =  (int)(elapFromBeg/(totalSec*60));//服务器时间与9:00的差，除以15分钟，加开始期，得到期号
	  int intBeginIssue = 0;
	  try{
		 intBeginIssue = Integer.parseInt(begIss);
	  }catch(Exception e){
		 //do nothing
	  }
	  int curServerIssueNum =  intBeginIssue + tempIssNum;
	  
	  if(serverCurSec>intBegTime && serverCurSec<endTime){
		  if((String.valueOf(curServerIssueNum)).equals(curIssueNum)){//如果当前投注期号和服务器期号相同，则进行下一步判读
			  if(issLeftSec<Integer.parseInt(winMin)*60){
			   // int winLeftSec=issLeftSec;
			   // issStat="正在开奖";
			    issStat = "2";
			    //cacuCouDow(winLeftSec,couDowShow);
			    //当前期正在开奖
			  }else{		    
			   // issStat="正在销售";
			    issStat = "1";
			    //正在销售，判断
			   // cacuCouDow(sellLeftSec,couDowShow);
			  }
		  }else{
			  issStat = "3";//期号不同，可能客户端与服务器端发生延迟，不允许投注2011-11-27
		  }
		 // issShow=issNum+begIss;
	  }else{	      
	     // issStat="今天停止销售";
		    issStat = "0";
	  }
	  return issStat;
	}

	public static void main(String[] args){
		CompareBuyAndServerTime st = new CompareBuyAndServerTime("test");
//		String issStat = st.caculTime("20101");
//		System.out.println("issStat="+ issStat);
	}
}
