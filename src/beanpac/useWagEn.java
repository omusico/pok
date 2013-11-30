package beanpac;

import java.util.List;

import org.apache.log4j.Logger;

import util.JStringToolkit;
import dbconnpac.DataBaseConn;

public class useWagEn{
	private Logger log = Logger.getLogger("huacaizx");
	public String getWagNumTot(String issType,String wagNum,String issNum){//扑克，时时乐
		String wagNumTot="";
		if(issType.equals("singleissue")){
			wagNumTot=wagNum;
		}else{
			String[] arrIssueInfo=issNum.split("\\$");
			int intTimes=0;
			for(int i=0;i<arrIssueInfo.length;i++){
				 String[] arrIssueTimes=arrIssueInfo[i].split("\\|");
//				 intTimes=intTimes+Integer.valueOf(arrIssueTimes[1]);//alter by ljy on 2009-05-25
				 intTimes=intTimes+Integer.valueOf(arrIssueTimes[1]).intValue();
			}
//			wagNumTot=String.valueOf(Integer.valueOf(wagNum)*intTimes);//alter by ljy on 2009-05-25
			wagNumTot=String.valueOf((Integer.valueOf(wagNum).intValue())*intTimes);//alter by ljy on 2009-05-25
		}
		return wagNumTot;
	}
/**
 * 
 *@Description:
 *@Author:LJY
 *@E-mail:lijiyongcn@sohu.com
 *@Create on May 25, 2009 4:55:25 PM
 * @param username 当前登陆人员
 * @param wagerNum　投注总金额
 * @param wagerTotal　投注内容
 * @param selling
 * @return
 */
	public String useWagEnable(String username,String wagerNum,String wagerTotal,String selling){
		String strRem="false";
		if(selling.equals("false")){
			strRem="停止销售,不能投注!";
		}else if(isFreeze(username)==true){
			strRem="您的帐户已经冻结";
		}else if(wagerNum.equals("0")){
			strRem="至少选择一注!";
		}else if(hasMoney(username,wagerNum)==false){
			strRem="您的余额不足，请您续费。请减少投注数。如果是多期投注，请减少倍数。";
		}else if(valWagerLength(wagerTotal)==true){
			strRem="投注过多，请减少投注数!";
		}else{
			strRem="true";
		}
		return strRem;
	}
/**
 * 
 *@Description:添加游戏类型（poker:扑克,laugh：时时乐,hap:福 彩 3D ,pth:排列3)
 *@Author:LJY
 *@E-mail:lijiyongcn@sohu.com
 *@Create on May 31, 2009 9:24:18 AM
 * @param issueNum:发行的期号
 * @param issueNumAndTimes:发行的期号及其相应的倍数
 * @param username
 * @param wagerNum
 * @param wagerTotal
 * @param selling
 * @param gameType :游戏类型（poker,laugh,hap:福 彩 3D| ,pth:排列3)
 * @param playType :玩法类型（任选一 任选二 任选三 选四直选 组选单式 组选复式 组选胆拖 组合投注） 
 * @param playMode：玩法模式（复式 单式 ）
 * @return
 */
	public String useWagEnable(String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String selling,String gameType,String playType,String playMode){
		String strRem="false";
		if(selling.equals("false")){
			strRem="停止销售,不能投注!";
		}else if(isFreeze(username)==true){
			strRem="您的帐户已经冻结";
		}else if(wagerNum.equals("0")){
			strRem="至少选择一注!";
		}else if(hasMoney(username,wagerNum)==false){
			strRem="您的余额不足，请您续费。请减少投注数。如果是多期投注，请减少倍数。";
		}else if(valWagerLength(wagerTotal)==true){
			strRem="投注过多，请减少投注数!";
		}else{
			//进行限号计算　
			CompareLimitNum cln = new CompareLimitNum();
			if(gameType.equals("poker") && playType.equals("组合投注")){
				 //如果是扑克，并并且是组合投注,则进行特殊处理
				strRem = dealZuheLimitInfo(issueNum,issueNumAndTimes,username,wagerNum,wagerTotal,selling,gameType,playType,playMode);

			}else{
				//查询限号情况
				int limitNum = cln.getLimitNum(gameType,playType,playMode);
	//			int limitNum = 100;
				//判断当前的投注是否已经超过限号限号情况
				log.info("todo 判断当前的投注是否已经超过限号情况");
				if(issueNumAndTimes.indexOf("|") == -1){//表示只投了当期的1注，没有倍投
					//将如果为0时，不能再继续投注的情况取消 2009.08.01
					if(limitNum <= 0){
	//					strRem = "限额已满，投注无效!";
	//					return strRem;
						strRem = "true";
						return strRem;
					}
				}else if(issueNumAndTimes.indexOf("$") == -1){//表示只投了当期的，有倍投情况

					String curTimes = issueNumAndTimes.substring(issueNumAndTimes.lastIndexOf("|")+1,issueNumAndTimes.length());
					int intCurTimes = Integer.valueOf(curTimes).intValue();
					if(limitNum < intCurTimes){
						strRem = "限额已满，投注无效!";
						return strRem;
					}
				}else{//"9001656 |1$9001657 |12"
					//如果有多期倍投的时候,任何一期超出，都要提示限额
					boolean limitFlag = true;
					String[] aryCurIssueNumAndTimes = JStringToolkit.splitString(issueNumAndTimes,"$");
					for(int i = 0; i < aryCurIssueNumAndTimes.length; i++){
						String temp = aryCurIssueNumAndTimes[i];
						String curTimes = temp.substring(temp.lastIndexOf("|")+1,temp.length());
						int intCurTimes = Integer.valueOf(curTimes).intValue();
						if(limitNum < intCurTimes){
							strRem = "限额已满，投注无效!";
							limitFlag = false;
							break;
							//return strRem;
						}
					}
					if(!limitFlag){
						return strRem;
					}
				}
			
				
				//得到当期该玩法的投注投注情况
				List list = cln.getCurWagerInfo(issueNum,gameType,playType,playMode,"");
				//判断当前的投注与已投注之和是否已经超过限号限号情况
				log.info("todo 判断当前的投注与已投注之和是否已经超过限号情况");
				boolean flag = cln.judgeWagerNumOfLimit(limitNum,list, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, playType, playMode);
				if(!flag){
					strRem = "限额已满，投注无效!";
					return strRem;
				}
				strRem="true";
			}
		}
		return strRem;
	}
/**
 * 
 *@Description:对限号进行处理
 *@Author:LJY
 *@E-mail:lijiyongcn@sohu.com
 *@Create on Aug 16, 2009 8:38:17 PM
 * @param issueNum
 * @param issueNumAndTimes
 * @param username
 * @param wagerNum
 * @param wagerTotal
 * @param selling
 * @param gameType
 * @param playType
 * @param playMode
 * @return
 */
	public String dealZuheLimitInfo(String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String selling,String gameType,String playType,String playMode){
		String strRem="false";
		//进行限号计算　
		CompareLimitNum cln = new CompareLimitNum();
		//查询限号情况
		//查询限号情况
		int limitNum1= cln.getLimitNum(gameType,"任选一",playMode);
		int limitNum2 = cln.getLimitNum(gameType,"任选二",playMode);
		int limitNum3 = cln.getLimitNum(gameType,"任选三",playMode);
		int limitNum4 = cln.getLimitNum(gameType,"选四直选",playMode);
//			int limitNum = 100;
		//判断当前的投注是否已经超过限号限号情况
		log.info("todo 判断当前的投注是否已经超过限号情况");
		if(issueNumAndTimes.indexOf("|") == -1){//表示只投了当期的1注，没有倍投
			//将如果为0时，不能再继续投注的情况取消 2009.08.01
			if(limitNum1 <= 0 || limitNum2 <= 0 || limitNum3 <= 0 || limitNum4 <= 0 ){
//					strRem = "限额已满，投注无效!";
//					return strRem;
				strRem = "true";
				return strRem;
			}
		}else if(issueNumAndTimes.indexOf("$") == -1){//表示只投了当期的，有倍投情况
			//String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String curTimes = issueNumAndTimes.substring(issueNumAndTimes.lastIndexOf("|")+1,issueNumAndTimes.length());
			int intCurTimes = Integer.valueOf(curTimes).intValue();

			if( intCurTimes > limitNum1 || intCurTimes > limitNum2 || intCurTimes > limitNum3 ||intCurTimes > limitNum4){
				strRem = "限额已满，投注无效!";
				return strRem;
			}
		}else{//"9001656 |1$9001657 |12"
			//如果有多期倍投的时候,任何一期超出，都要提示限额
			boolean limitFlag = true;
			String[] aryCurIssueNumAndTimes = JStringToolkit.splitString(issueNumAndTimes,"$");
			for(int i = 0; i < aryCurIssueNumAndTimes.length; i++){
				String temp = aryCurIssueNumAndTimes[i];
				String curTimes = temp.substring(temp.lastIndexOf("|")+1,temp.length());
				int intCurTimes = Integer.valueOf(curTimes).intValue();
				if( intCurTimes > limitNum1 || intCurTimes > limitNum2 || intCurTimes > limitNum3 ||intCurTimes > limitNum4){
					strRem = "限额已满，投注无效!";
					log.info("组合倍投时超出限值");
					limitFlag = false;
					break;
					//return strRem;
				}
			}
			if(!limitFlag){
				return strRem;
			}
		}
	
		
		//得到当期该玩法的投注投注情况
		List list = cln.getCurWagerInfo(issueNum,gameType,playType,playMode,"");
		//判断当前的投注与已投注之和是否已经超过限号限号情况
		log.info("todo 判断当前的投注与已投注之和是否已经超过限号情况");
		boolean flag = cln.judgeWagerNumOfLimit(limitNum1,list, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType,playType,  playMode);
		
//		if(flag){
//			flag = cln.judgeWagerNumOfLimit(limitNum2,list, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, "任选二",  playMode);
//			if(flag){
//				flag =  cln.judgeWagerNumOfLimit(limitNum3,list, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal,  gameType,"任选三",  playMode);
//				if(flag){
//					flag =  cln.judgeWagerNumOfLimit(limitNum4,list, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, "选四直选",  playMode);
//					
//				}
//			}
//		}
		
		if(!flag){
			strRem = "限额已满，投注无效!";
			return strRem;
		}
		strRem="true";

		return strRem;
	}
	
	public boolean hasMoney(String usernameTemp,String wagerNumTemp){
		 DataBaseConn hasMoneyDBC=new DataBaseConn();
		 hasMoneyDBC.execQuery("select * from lotteryuser where username='"+usernameTemp+"';");
		 hasMoneyDBC.rsNext();
		 int intTotalMoney=Integer.parseInt(hasMoneyDBC.rsGetString("totalmoney"));
		 int intWagerMoney=Integer.parseInt(wagerNumTemp)*2;
		 hasMoneyDBC.connClose();
		 if(intWagerMoney>intTotalMoney){
			 return false;
		 }else{
			 return true;
		 }
	 }
	
	public boolean isFreeze(String usernameTemp){
		boolean isFr=false;
		DataBaseConn isFreezeDBC=new DataBaseConn();
		isFreezeDBC.execQuery("select * from lotteryuser where username='"+usernameTemp+"';");
		isFreezeDBC.rsNext();
		String strFr=isFreezeDBC.rsGetString("freeze");
		if(!strFr.equals("0")){
			isFr=true;
		}
		isFreezeDBC.connClose();
		return isFr;
	}
	
	public boolean valWagerLength(String wagTot){
		boolean isTooLong=false;
		String[] arrWagTot=wagTot.split("");
		if(arrWagTot.length>3000){
			isTooLong=true;
		}
		return isTooLong;
	}


	
}
