package beanpac;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import util.JStringToolkit;
import dbconnpac.DataBaseConn;

/**
 *@Description:
 *@Author:LJY
 *@E-mail:lijiyongcn@sohu.com
 *@Create on May 31, 2009 12:07:34 PM
 *@Ver:
 */
public class CompareLimitNum {
	
	private Logger log = Logger.getLogger("huacaizx");
	/**
	 * 
	 *@Description:
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 11:29:14 AM
	 * @param dbWagerNumList 数据库中已经投注的
	 * @param limitNum:限制的号数
	 * @param issueNum:发行的期号
	 * @param issueNumAndTimes:发行的期号及其相应的倍数:
	 * @param username
	 * @param wagerNum
	 * @param wagerTotal
	 * @param gameType :游戏类型（poker,laugh,hap:福 彩 3D| ,pth:排列3)
	 * @param playType :玩法类型（任选一 任选二 任选三 选四直选 组选单式 组选复式 组选胆拖 组合投注） 
	 * @param playMode：玩法模式（复式 单式 ）
	 */
	public boolean judgeWagerNumOfLimit(int limitNum,List dbWagerNumList,String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String gameType,String playType,String playMode){
		boolean flag = true;
		if(gameType.equals("poker")){
			log.info("快乐扑克");
			flag = dealPokerLimit(limitNum,dbWagerNumList, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, playType, playMode);
		}
		if(gameType.equals("laugh")){
			log.info("时时乐");
			flag = dealLaughLimit(limitNum,dbWagerNumList, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, playType, playMode);
		}
		if(gameType.equals("pth")){
			log.info("排列3");
			flag = dealPthLimit(limitNum,dbWagerNumList, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, playType, playMode);
		}		
		if(gameType.equals("hap")){
			log.info("福彩3D");
			flag = dealHapLimit(limitNum,dbWagerNumList, issueNum, issueNumAndTimes, username, wagerNum, wagerTotal, gameType, playType, playMode);
		}
		return flag;
	}
	/**
	 * 
	 *@Description:对福彩进行查询
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:21:19 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param issueNum
	 * @param issueNumAndTimes
	 * @param username
	 * @param wagerNum
	 * @param wagerTotal
	 * @param gameType
	 * @param playType
	 * @param playMode
	 */
	public boolean dealHapLimit(int limitNum,List dbWagerNumList,String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String gameType,String playType,String playMode){
		
		boolean flag = true;
		int curTimes = 0;
		if(issueNumAndTimes.indexOf("|") == -1){//表示只投了当期的1注，没有倍投
			curTimes = 1;
		}else if(issueNumAndTimes.indexOf("$") == -1){//表示只投了当期的，有倍投情况
			//String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = issueNumAndTimes.substring(issueNumAndTimes.lastIndexOf("|")+1,issueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
			
		}else{
			String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = curIssueNumAndTimes.substring(curIssueNumAndTimes.lastIndexOf("|")+1,curIssueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
		}
		 if(playType.equals("直选")){
			 flag = compareHapWager(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组选") && playMode.equals("组6")){
			 flag = compareHapZuxuanSix(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组选") && playMode.equals("组3")){
			 flag = compareHapZuxuanThree(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 return flag;
	}
	/**
	 * 
	 *@Description:福彩中的直选
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareHapWager(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		//如果是和值
		if(playMode.equals("和值")){
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,",");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				//将下注的数据与数据库中已存在的数据挨个进行对比
				// for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();								
								
								//如果是和值
								if(hisPlayMode.equals("和值")){
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(tempWager.equals(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
									//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
										for(int q = 0; q < arryHis.length; q++){
											    //计算每个组合的和值
											  	int r = 0;
											    String t = arryHis[q];
											    String[] tt = JStringToolkit.splitChar(t);
											    for(int kk = 0; kk < tt.length; kk++){
											    	int bb = Integer.parseInt(tt[kk]);
											    	r = r + bb;
											    }
											    
											  	
											    if(Integer.parseInt(tempWager) == r && !tempWager.equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}				
								}
				}			
								
		}else{
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				 
				 String[] arryCur = makeLaughDanxuanThree(tempWager);//当前下注的数组
				 //将下注的数据与数据库中已存在的数据挨个进行对比
				 for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();	
								//如果是和值
								//如果是和值
								if(hisPlayMode.equals("和值")){
									//计算每注的和值
									String[] arryHezhi = JStringToolkit.splitChar(arryCur[j]);
									
								    //计算每个组合的和值
								  	int r = 0;
								    for(int kk = 0; kk < arryHezhi.length; kk++){
								    	int bb = Integer.parseInt(arryHezhi[kk]);
								    	r = r + bb;
								    }
							
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(r == Integer.parseInt(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
								//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
										  for(int q = 0; q < arryHis.length; q++){
												if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}					 
							 }
						 }
				}
		}
		return true;
	
	}
	
	/**
	 * 
	 *@Description:神彩中的组选六
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareHapZuxuanSix(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanSix(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanSix(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:福彩中的组选六
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareHapZuxuanThree(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanThree(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							//log.info("历史投的一组数据:"+hisWagerTotal);
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanThree(tempHisWager);//历史下注的数组
								//log.info("历史投的单个数据:"+tempHisWager+",单个数据组合长度="+arryHis.length);
								  for(int q = 0; q < arryHis.length; q++){
									//  log.info("i="+i+",j="+j+",要对比的数组长度="+arryCur.length+",m="+m+",数据库中已有数组长度="+dbWagerNumList.size()+",n="+n+",要对比的历史数组长度="+arryHisWagerTotal.length+",q="+q+",cur="+arryCur[j]+",history="+arryHis[q]);
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:对排列三进行查询
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:21:19 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param issueNum
	 * @param issueNumAndTimes
	 * @param username
	 * @param wagerNum
	 * @param wagerTotal
	 * @param gameType
	 * @param playType
	 * @param playMode
	 */
	public boolean dealPthLimit(int limitNum,List dbWagerNumList,String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String gameType,String playType,String playMode){
		boolean flag = true;
		int curTimes = 0;
		if(issueNumAndTimes.indexOf("|") == -1){//表示只投了当期的1注，没有倍投
			curTimes = 1;
		}else if(issueNumAndTimes.indexOf("$") == -1){//表示只投了当期的，有倍投情况
			//String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = issueNumAndTimes.substring(issueNumAndTimes.lastIndexOf("|")+1,issueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
			
		}else{
			String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = curIssueNumAndTimes.substring(curIssueNumAndTimes.lastIndexOf("|")+1,curIssueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
		}
		 if(playType.equals("直选")){
			 flag = comparePthWager(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组选") && playMode.equals("组6")){
			 flag = comparePthZuxuanSix(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组选") && playMode.equals("组3")){
			 flag = comparePthZuxuanThree(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 return flag;
	}
	/**
	 * 
	 *@Description:排列三中的直选
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean comparePthWager(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		if(playMode.equals("和值")){
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,",");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				//将下注的数据与数据库中已存在的数据挨个进行对比
				// for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();								
								
								//如果是和值
								if(hisPlayMode.equals("和值")){
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(tempWager.equals(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
									//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
										for(int q = 0; q < arryHis.length; q++){
											    //计算每个组合的和值
											  	int r = 0;
											    String t = arryHis[q];
											    String[] tt = JStringToolkit.splitChar(t);
											    for(int kk = 0; kk < tt.length; kk++){
											    	int bb = Integer.parseInt(tt[kk]);
											    	r = r + bb;
											    }
											    
											  	
											    if(Integer.parseInt(tempWager) == r && !tempWager.equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}				
								}
				}			
								
		}else{
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				 
				 String[] arryCur = makeLaughDanxuanThree(tempWager);//当前下注的数组
				 //将下注的数据与数据库中已存在的数据挨个进行对比
				 for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();	
								//如果是和值
								//如果是和值
								if(hisPlayMode.equals("和值")){
									//计算每注的和值
									String[] arryHezhi = JStringToolkit.splitChar(arryCur[j]);
									
								    //计算每个组合的和值
								  	int r = 0;
								    for(int kk = 0; kk < arryHezhi.length; kk++){
								    	int bb = Integer.parseInt(arryHezhi[kk]);
								    	r = r + bb;
								    }
							
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(r == Integer.parseInt(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
								//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
										  for(int q = 0; q < arryHis.length; q++){
												if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}					 
							 }
						 }
				}
		}
		//===============
		//判断当前的投的注数是否是已经
//		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
//		for(int i = 0; i < arryCurWagerTotal.length; i++){
//			 String tempWager = arryCurWagerTotal[i];//当前投的数据
//			 
//			 String[] arryCur = makeLaughDanxuanThree(tempWager);//当前下注的数组
//			 //将下注的数据与数据库中已存在的数据挨个进行对比
//			 for(int j = 0; j < arryCur.length; j++){
//					 int count = curTimes;
//					 //数据库中已存在的数据
//					 for(int m = 0; m < dbWagerNumList.size(); m++){
//							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
//							String hisWagerTotal = info.getWagertotal();//已投注的
//							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
//							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
//							for(int n = 0; n < arryHisWagerTotal.length; n++){
//								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
//								String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
//								  for(int q = 0; q < arryHis.length; q++){
//										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
//											count = count + hisTimes;
//											if(count > limitNum){
//												return false;
//											}
//										}
//									 }									 
//								 }	
//							}					 
//						 }
//					 }
		return true;
	
	}
	
	/**
	 * 
	 *@Description:时时乐中的组选六
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean comparePthZuxuanSix(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanSix(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanSix(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的组选六
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean comparePthZuxuanThree(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanThree(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanThree(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:对时时乐进行查询
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:21:19 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param issueNum
	 * @param issueNumAndTimes
	 * @param username
	 * @param wagerNum
	 * @param wagerTotal
	 * @param gameType
	 * @param playType
	 * @param playMode
	 */
	public boolean dealLaughLimit(int limitNum,List dbWagerNumList,String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String gameType,String playType,String playMode){
		
		boolean flag = true;
		int curTimes = 0;
		if(issueNumAndTimes.indexOf("|") == -1){//表示只投了当期的1注，没有倍投
			curTimes = 1;
		}else if(issueNumAndTimes.indexOf("$") == -1){//表示只投了当期的，有倍投情况
			//String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = issueNumAndTimes.substring(issueNumAndTimes.lastIndexOf("|")+1,issueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
			
		}else{
			String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = curIssueNumAndTimes.substring(curIssueNumAndTimes.lastIndexOf("|")+1,curIssueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
		}
		 if(playType.equals("单选三")){
			 flag = compareLaughWagerThree(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组6")){
			 flag =  compareLaughZuxuanSix(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组3")){
			 flag =  compareLaughZuxuanThree(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("前一后一") && playMode.equals("前一")){
			 flag = compareLaughPreOne(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("前一后一") && playMode.equals("后一")){
			 flag =  compareLaughLastOne(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("前二后二") && playMode.equals("前二")){
			 flag = compareLaughPreTwo(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("前二后二") && playMode.equals("后二")){
			 flag = compareLaughLastTwo(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 return flag;
	}
	/**
	 * 
	 *@Description:时时乐中的单选三
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * 和值4,18,19
	 * @return
	 */
	public boolean compareLaughWagerThree(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		if(playMode.equals("和值")){
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,",");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				//将下注的数据与数据库中已存在的数据挨个进行对比
				// for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();								
								
								//如果是和值
								if(hisPlayMode.equals("和值")){
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(tempWager.equals(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
									//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
										for(int q = 0; q < arryHis.length; q++){
											    //计算每个组合的和值
											  	int r = 0;
											    String t = arryHis[q];
											    String[] tt = JStringToolkit.splitChar(t);
											    for(int kk = 0; kk < tt.length; kk++){
											    	int bb = Integer.parseInt(tt[kk]);
											    	r = r + bb;
											    }
											    
											  	
											    if(Integer.parseInt(tempWager) == r && !tempWager.equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}				
								}
				}			
								
		}else{
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				 
				 String[] arryCur = makeLaughDanxuanThree(tempWager);//当前下注的数组
				 //将下注的数据与数据库中已存在的数据挨个进行对比
				 for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();	
								//如果是和值
								//如果是和值
								if(hisPlayMode.equals("和值")){
									//计算每注的和值
									String[] arryHezhi = JStringToolkit.splitChar(arryCur[j]);
									
								    //计算每个组合的和值
								  	int r = 0;
								    for(int kk = 0; kk < arryHezhi.length; kk++){
								    	int bb = Integer.parseInt(arryHezhi[kk]);
								    	r = r + bb;
								    }
							
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(r == Integer.parseInt(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
								//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
										  for(int q = 0; q < arryHis.length; q++){
												if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}					 
							 }
						 }
				}
		}
		//===============
		
		//判断当前的投的注数是否是已经
//		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
//		for(int i = 0; i < arryCurWagerTotal.length; i++){
//			 String tempWager = arryCurWagerTotal[i];//当前投的数据
//			 
//			 String[] arryCur = makeLaughDanxuanThree(tempWager);//当前下注的数组
//			 //将下注的数据与数据库中已存在的数据挨个进行对比
//			 for(int j = 0; j < arryCur.length; j++){
//					 int count = curTimes;
//					 //数据库中已存在的数据
//					 for(int m = 0; m < dbWagerNumList.size(); m++){
//							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
//							String hisWagerTotal = info.getWagertotal();//已投注的
//							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
//							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
//							for(int n = 0; n < arryHisWagerTotal.length; n++){
//								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
//								String[] arryHis = makeLaughDanxuanThree(tempHisWager);//历史下注的数组
//								  for(int q = 0; q < arryHis.length; q++){
//										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
//											count = count + hisTimes;
//											if(count > limitNum){
//												return false;
//											}
//										}
//									 }									 
//								 }	
//							}					 
//						 }
//					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的组选六
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareLaughZuxuanSix(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		if(playMode.equals("和值")){
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,",");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				//将下注的数据与数据库中已存在的数据挨个进行对比
				// for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();								
								
								//如果是和值
								if(hisPlayMode.equals("和值")){
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(tempWager.equals(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
									//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughZuxuanSix(tempHisWager);//历史下注的数组
										for(int q = 0; q < arryHis.length; q++){
											    //计算每个组合的和值
											  	int r = 0;
											    String t = arryHis[q];
											    String[] tt = JStringToolkit.splitChar(t);
											    for(int kk = 0; kk < tt.length; kk++){
											    	int bb = Integer.parseInt(tt[kk]);
											    	r = r + bb;
											    }
											    
											  	
											    if(Integer.parseInt(tempWager) == r && !tempWager.equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}				
								}
				}			
								
		}else{
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				 
				 String[] arryCur = makeLaughZuxuanSix(tempWager);//当前下注的数组
				 //将下注的数据与数据库中已存在的数据挨个进行对比
				 for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();	
								//如果是和值
								//如果是和值
								if(hisPlayMode.equals("和值")){
									//计算每注的和值
									String[] arryHezhi = JStringToolkit.splitChar(arryCur[j]);
									
								    //计算每个组合的和值
								  	int r = 0;
								    for(int kk = 0; kk < arryHezhi.length; kk++){
								    	int bb = Integer.parseInt(arryHezhi[kk]);
								    	r = r + bb;
								    }
							
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(r == Integer.parseInt(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
								//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughZuxuanSix(tempHisWager);//历史下注的数组
										  for(int q = 0; q < arryHis.length; q++){
												if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}					 
							 }
						 }
				}
		}
		//===============
		
		
		//判断当前的投的注数是否是已经
//		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
//		for(int i = 0; i < arryCurWagerTotal.length; i++){
//			 String tempWager = arryCurWagerTotal[i];//当前投的数据
//			 
//			 String[] arryCur = makeLaughZuxuanSix(tempWager);//当前下注的数组
//			 //将下注的数据与数据库中已存在的数据挨个进行对比
//			 for(int j = 0; j < arryCur.length; j++){
//					 int count = curTimes;
//					 //数据库中已存在的数据
//					 for(int m = 0; m < dbWagerNumList.size(); m++){
//							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
//							String hisWagerTotal = info.getWagertotal();//已投注的
//							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
//							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
//							for(int n = 0; n < arryHisWagerTotal.length; n++){
//								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
//								String[] arryHis = makeLaughZuxuanSix(tempHisWager);//历史下注的数组
//								  for(int q = 0; q < arryHis.length; q++){
//										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
//											count = count + hisTimes;
//											if(count > limitNum){
//												return false;
//											}
//										}
//									 }									 
//								 }	
//							}					 
//						 }
//					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的组选三
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareLaughZuxuanThree(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		if(playMode.equals("和值")){
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,",");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				//将下注的数据与数据库中已存在的数据挨个进行对比
				// for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();								
								
								//如果是和值
								if(hisPlayMode.equals("和值")){
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(tempWager.equals(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
									//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughZuxuanThree(tempHisWager);//历史下注的数组
										for(int q = 0; q < arryHis.length; q++){
											    //计算每个组合的和值
											  	int r = 0;
											    String t = arryHis[q];
											    String[] tt = JStringToolkit.splitChar(t);
											    for(int kk = 0; kk < tt.length; kk++){
											    	int bb = Integer.parseInt(tt[kk]);
											    	r = r + bb;
											    }
											    
											  	
											    if(Integer.parseInt(tempWager) == r && !tempWager.equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}				
								}
				}			
								
		}else{
			String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			for(int i = 0; i < arryCurWagerTotal.length; i++){
				 String tempWager = arryCurWagerTotal[i];//当前投的数据
				 
				 String[] arryCur = makeLaughZuxuanThree(tempWager);//当前下注的数组
				 //将下注的数据与数据库中已存在的数据挨个进行对比
				 for(int j = 0; j < arryCur.length; j++){
						 int count = curTimes;
						 //数据库中已存在的数据
						 for(int m = 0; m < dbWagerNumList.size(); m++){
								WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
								String hisWagerTotal = info.getWagertotal();//已投注的
								int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
								String hisPlayMode = info.getPlaymode();	
								//如果是和值
								//如果是和值
								if(hisPlayMode.equals("和值")){
									//计算每注的和值
									String[] arryHezhi = JStringToolkit.splitChar(arryCur[j]);
									
								    //计算每个组合的和值
								  	int r = 0;
								    for(int kk = 0; kk < arryHezhi.length; kk++){
								    	int bb = Integer.parseInt(arryHezhi[kk]);
								    	r = r + bb;
								    }
							
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,",");
									for(int n = 0; n < arryHisWagerTotal.length; n++){	
									    String hisTempHisWager = arryHisWagerTotal[n];
										if(r == Integer.parseInt(hisTempHisWager) && !tempWager.equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									}
								}else{
								//如果不是和值
									String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
									for(int n = 0; n < arryHisWagerTotal.length; n++){
										String tempHisWager = arryHisWagerTotal[n];//历史投的数据
										String[] arryHis = makeLaughZuxuanThree(tempHisWager);//历史下注的数组
										  for(int q = 0; q < arryHis.length; q++){
												if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
													count = count + hisTimes;
													if(count > limitNum){
														return false;
													}
												}
											 }									 
										 }	
									}					 
							 }
						 }
				}
		}
		//===============
		
		
		//判断当前的投的注数是否是已经
//		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
//		for(int i = 0; i < arryCurWagerTotal.length; i++){
//			 String tempWager = arryCurWagerTotal[i];//当前投的数据
//			 
//			 String[] arryCur = makeLaughZuxuanThree(tempWager);//当前下注的数组
//			 //将下注的数据与数据库中已存在的数据挨个进行对比
//			 for(int j = 0; j < arryCur.length; j++){
//					 int count = curTimes;
//					 //数据库中已存在的数据
//					 for(int m = 0; m < dbWagerNumList.size(); m++){
//							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
//							String hisWagerTotal = info.getWagertotal();//已投注的
//							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
//							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
//							for(int n = 0; n < arryHisWagerTotal.length; n++){
//								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
//								String[] arryHis = makeLaughZuxuanThree(tempHisWager);//历史下注的数组
//								  for(int q = 0; q < arryHis.length; q++){
//										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
//											count = count + hisTimes;
//											if(count > limitNum){
//												return false;
//											}
//										}
//									 }									 
//								 }	
//							}					 
//						 }
//					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的前一
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareLaughPreOne(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanPreOne(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanPreOne(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的前一
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareLaughLastOne(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanLastOne(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanLastOne(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的前二
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareLaughPreTwo(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 
			 String[] arryCur = makeLaughZuxuanPreTwo(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanPreTwo(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:时时乐中的后二
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 1:09:06 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean compareLaughLastTwo(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据			 
			 String[] arryCur = makeLaughZuxuanLastTwo(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makeLaughZuxuanLastTwo(tempHisWager);//历史下注的数组
								  for(int q = 0; q < arryHis.length; q++){
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
		return true;
	
	}
	/**
	 * 
	 *@Description:对快乐扑克进行限号判断
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 12:37:11 PM
	 * @param dbWagerNumList
	 * @param issueNum
	 * @param issueNumAndTimes
	 * @param username
	 * @param wagerNum
	 * @param wagerTotal
	 * @param selling
	 * @param gameType
	 * @param playType
	 * @param playMode
	 */
	public boolean dealPokerLimit(int limitNum,List dbWagerNumList,String issueNum,String issueNumAndTimes,String username,String wagerNum,String wagerTotal,String gameType,String playType,String playMode){
		
		boolean flag = true;
		int curTimes = 0;
		if(issueNumAndTimes.indexOf("|") == -1){//表示只投了当期的1注，没有倍投
			curTimes = 1;
		}else if(issueNumAndTimes.indexOf("$") == -1){//表示只投了当期的，有倍投情况
			//String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = issueNumAndTimes.substring(issueNumAndTimes.lastIndexOf("|")+1,issueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
			
		}else{
			String curIssueNumAndTimes = issueNumAndTimes.substring(0,issueNumAndTimes.indexOf("$"));
			String strCurTimes = curIssueNumAndTimes.substring(curIssueNumAndTimes.lastIndexOf("|")+1,curIssueNumAndTimes.length());
			curTimes = Integer.valueOf(strCurTimes).intValue();
		}
		
		 if(playType.equals("任选一")){
			 flag = compareWagerOne(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("任选二")){
			 flag = compareWagerTwo(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("任选三")){
			 flag = compareWagerThree(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode,true);
		 }
		 if(playType.equals("选四直选")){
			 flag = compareWagerFour(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
//		 if(playType.equals("组选单式")){
		 if(playType.indexOf("组选") != -1){
			 
			 flag = comparePokerZuxuan(limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 if(playType.equals("组合投注")){
			 flag = comparePokerZuhe(issueNum,limitNum,dbWagerNumList,wagerTotal,curTimes,playType,playMode);
		 }
		 return flag;		
	}
	/**
	 * 
	 *@Description::扑克的组合投注
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Aug 16, 2009 10:53:37 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean comparePokerZuhe(String issueNum,int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){

		boolean flag = true;
		CompareLimitNum cln = new CompareLimitNum();
		//if(playType.equals("任选一")){
		//判断任选一是否超出限号
		List list1 = cln.getCurWagerInfo(issueNum,"poker","任选一",playMode,"");
//		for(int i = 0; i < dbWagerNumList.size();i++){
//			list1.add((WagerInfo)dbWagerNumList.get(i));
//		}
		int limitNum1= cln.getLimitNum("poker","任选一",playMode);
		 flag = compareWagerOne(limitNum1,list1,curWagerTotal,curTimes,playType,playMode);
		//判断任选2是否超出限号	
		 if(flag){
			 	if(numberOfStr(curWagerTotal,"=") <= 2){
			 		List list2 = cln.getCurWagerInfo(issueNum,"poker","任选二",playMode,"");
//					for(int i = 0; i < dbWagerNumList.size();i++){
//						list2.add((WagerInfo)dbWagerNumList.get(i));
//					}
					int limitNum2= cln.getLimitNum("poker","任选二",playMode);
					flag = compareWagerTwo(limitNum2,list2,curWagerTotal,curTimes,"任选二",playMode);
			 	}
				//判断任选3是否超出限号 
				if(flag){
					if(numberOfStr(curWagerTotal,"=") <= 1){
						List list3 = cln.getCurWagerInfo(issueNum,"poker","任选三",playMode,"");
//						for(int i = 0; i < dbWagerNumList.size();i++){
//							list3.add((WagerInfo)dbWagerNumList.get(i));
//						}
						int limitNum3= cln.getLimitNum("poker","任选三",playMode);
						flag = compareWagerThree(limitNum3,list3,curWagerTotal,curTimes,"任选三",playMode,false);
					}
						//判断任选四是否超出限号
					if(flag){
							if(curWagerTotal.indexOf("=") == -1){
								List list4 = cln.getCurWagerInfo(issueNum,"poker","选四直选",playMode,"");
//								for(int i = 0; i < dbWagerNumList.size();i++){
//									list4.add((WagerInfo)dbWagerNumList.get(i));
//								}
								int limitNum4= cln.getLimitNum("poker","选四直选",playMode);
								flag = compareWagerFour(limitNum4,list4,curWagerTotal,curTimes,"选四直选",playMode);
							}
							if(!flag){
								log.info("组合投注：任选四超出限值");
							}
					}else{
							 log.info("组合投注：任选三超出限值");
				    }
				}else{
						 log.info("组合投注：任选二超出限值");
			    }
			 }else{
				 log.info("组合投注：任选一超出限值");
			 }
		 
		
		
		
		return flag;
	}
	/**
	 * 
	 *@Description:扑克的组选判断
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 4, 2009 4:04:49 PM
	 * @param issueNum
	 * @param gameType
	 * @param limitNum
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean comparePokerComb(String issueNum,String gameType,int limitNum,String curWagerTotal,int curTimes,String playType,String playMode){
		
		//得到当期该玩法的投注投注情况
		String otherField = "and playmode='"+playMode+"'";		
		List dbWagerNumList = getCurWagerInfo(issueNum,gameType,playType,playMode,otherField);//已投的注数
		
		//得到当期的数组
		String[] arryCurWagerTotal = null;
		if(curWagerTotal.indexOf("f") != -1){
			String[] t = JStringToolkit.splitString(curWagerTotal, "f"); 
			String temp = "";
			for(int i = 0; i < t.length; i++){
				temp = temp + t[i].substring(t[i].indexOf("g")+1,t[i].length()) + "@";
			}
			temp = temp.substring(0,temp.length()-1);
			if(temp.indexOf("@") != -1){
				temp = temp.replace("@", "$");
			}
			arryCurWagerTotal = JStringToolkit.splitString(temp,"$");
		}else{
			if(curWagerTotal.indexOf("g") != -1){
				//String[] t = JStringToolkit.splitString(curWagerTotal, "f"); 
				arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal.substring(curWagerTotal.indexOf("g")+1),"$");
			}else{
				arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			}
		}
		
		
		
		//判断当前的投的注数是否是已经
	//	String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 //String[][] arryCur = makeFourArry(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 int count = curTimes;
			 //数据库中已存在的数据
			 for(int m = 0; m < dbWagerNumList.size(); m++){
					WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
					String hisWagerTotal = info.getWagertotal();//已投注的
					int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
					String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
					for(int n = 0; n < arryHisWagerTotal.length; n++){
						String tempHisWager = arryHisWagerTotal[n];//历史投的数据
						
						if(tempWager.equals(tempHisWager) && !tempWager.equals("=")){
							count = count + hisTimes;
							if(count > limitNum){
								return false;
							}
						}
					}
			 }
		}
		return true;
	}
	/**
	 * 
	 *@Description:任选一
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 5:07:46 PM
	 * @param limitNum:限制的号
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 */
	public boolean compareWagerOne(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 String[][] arryCur = makeFourArry(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
				 for(int k = 0; k < arryCur[j].length; k++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[][] arryHis = makeFourArry(tempHisWager);//历史下注的数组
								 for(int p = j; p <= j; p++){
									 for(int q = 0; q < arryHis[p].length; q++){
										if(arryCur[j][k].equals(arryHis[p][q]) && !arryCur[j][k].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
				 }
			}
		return true;
	}
	
	/**
	 * 
	 *@Description:任选二
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 5:07:46 PM
	 * @param limitNum:限制的号
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 */
	public boolean compareWagerTwo(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		
//		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		String[] arryCurWagerTotal = null;//new String[JStringToolkit.splitString(curWagerTotal,"$").length];
		if(curWagerTotal.indexOf("f") != -1){
			String[] tempGroup = JStringToolkit.splitString(curWagerTotal,"f");
			String tempStr = "";
			for(int i = 0; i < tempGroup.length; i++){
				String ss = tempGroup[i];
				if(ss.indexOf("g") != -1){
					String[] tempGroup2 = JStringToolkit.splitString(ss.substring(ss.indexOf("g")+1,ss.length()),"$");
					for(int j = 0; j < tempGroup2.length;j++){
						tempStr = tempStr + tempGroup2[j] + ",";
					}
				}
			}
			arryCurWagerTotal = JStringToolkit.splitString(tempStr,",");
		//	arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal.substring(curWagerTotal.indexOf("g")+1,curWagerTotal.length()),"$");
		}else{
			if(curWagerTotal.indexOf("g") != -1){
				arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal.substring(curWagerTotal.indexOf("g")+1,curWagerTotal.length()),"$");

			}else{			
				arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			}
		}
			
		//	JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 String[][] arryCur = makePokerFourToThreeArry(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
				 for(int k = 0; k < arryCur[j].length; k++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[][] arryHis = makePokerFourToThreeArry(tempHisWager);//历史下注的数组
//								 for(int p = j; p <= j; p++){
//									 for(int q = 0; q < arryHis[p].length; q++){
										 
								 for(int p = 0; p < arryHis.length; p++){
									 for(int q = 0; q < arryHis[p].length; q++){
										 String curStr = arryCur[j][k];
										 String hisStr = arryHis[p][q];
										 if(curStr.equals(hisStr) && !curStr.equals("=")){
										//if(arryCur[j][k].equals(arryHis[p][q]) && !arryCur[j][k].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
				 }
			}
		return true;
	}
	/**
	 * 
	 *@Description:任选三
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 5:07:46 PM
	 * @param limitNum:限制的号
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @param isFlag 是否进行任选三的判断
	 */
	public boolean compareWagerThree(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode,boolean isFlag){
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 String[][] arryCur = makePokerFourToTwoArry(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
				 for(int k = 0; k < arryCur[j].length; k++){
					 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[][] arryHis = makePokerFourToTwoArry(tempHisWager);//历史下注的数组
								 for(int p = j; p <= j; p++){
									 for(int q = 0; q < arryHis[p].length; q++){
										if(arryCur[j][k].equals(arryHis[p][q]) && !arryCur[j][k].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
						 }
					 }
				 }
			}
		return true;
	}
	
	/**
	 * 
	 *@Description:扑克的组选，进行判断。 在组选中，不论单式，还是复式，
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Aug 16, 2009 2:28:43 PM
	 * @param limitNum
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public boolean comparePokerZuxuan(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		//判断当前的投的注数是否是已经
		
		//得到当期的数组
		String[] arryCurWagerTotal = null;
		if(curWagerTotal.indexOf("f") != -1){
			String[] t = JStringToolkit.splitString(curWagerTotal, "f"); 
			String temp = "";
			for(int i = 0; i < t.length; i++){
				temp = temp + t[i].substring(t[i].indexOf("g")+1,t[i].length()) + "@";
			}
			temp = temp.substring(0,temp.length()-1);
			if(temp.indexOf("@") != -1){
				temp = temp.replace("@", "$");
			}
			arryCurWagerTotal = JStringToolkit.splitString(temp,"$");
		}else{
			if(curWagerTotal.indexOf("g") != -1){
				//String[] t = JStringToolkit.splitString(curWagerTotal, "f"); 
				arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal.substring(curWagerTotal.indexOf("g")+1),"$");
			}else{
				arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
			}
		}
	
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			// String[] arryCur = makePokerFourToOneArry(tempWager);//当前下注的数组
			 int count = curTimes;
			 //将下注的数据与数据库中已存在的数据挨个进行对比
				 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
						WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
						String hisWagerTotal = info.getWagertotal();//已投注的
						int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
						String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
						for(int n = 0; n < arryHisWagerTotal.length; n++){
							String tempHisWager = arryHisWagerTotal[n];//历史投的数据
							//String[] arryHis = makePokerFourToOneArry(tempHisWager);//历史下注的数组

							if(tempWager.equals(tempHisWager) && !tempWager.equals("=")){
										count = count + hisTimes;
										if(count > limitNum){
											return false;
									}
							}
						}
					}
					//System.out.println("1111111");
			}
		return true;
	}
	/**
	 * 
	 *@Description:任选四
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 5:07:46 PM
	 * @param limitNum:限制的号
	 * @param dbWagerNumList
	 * @param curWagerTotal
	 * @param curTimes
	 * @param playType
	 * @param playMode
	 */
	public boolean compareWagerFour(int limitNum,List dbWagerNumList,String curWagerTotal,int curTimes,String playType,String playMode){
		
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 String[] arryCur = makePokerFourToOneArry(tempWager);//当前下注的数组
			 //将下注的数据与数据库中已存在的数据挨个进行对比
			 for(int j = 0; j < arryCur.length; j++){
				 //for(int k = 0; k < arryCur[j].length; k++){
				 int count = curTimes;
					 //数据库中已存在的数据
					 for(int m = 0; m < dbWagerNumList.size(); m++){
							WagerInfo info = (WagerInfo)dbWagerNumList.get(m);
							String hisWagerTotal = info.getWagertotal();//已投注的
							int hisTimes = Integer.valueOf(info.getTimes()).intValue();//已投注的倍数
							String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
							for(int n = 0; n < arryHisWagerTotal.length; n++){
								//System.out.println("1.---cur=" + j + ";his num = " + n);
								String tempHisWager = arryHisWagerTotal[n];//历史投的数据
								String[] arryHis = makePokerFourToOneArry(tempHisWager);//历史下注的数组
//								 for(int p = j; p <= j; p++){
									 //for(int q = 0; q < arryHis[p].length; q++){
									 for(int q = 0; q < arryHis.length && j < arryHis.length; q++){
										// System.out.println("2.arryHis.length="+arryHis.length + ",curLength="+arryCur.length + ",---cur[" + j + "]" + arryCur[j] + ";his["+q+ "]=" + arryHis[q]);	
										if(arryCur[j].equals(arryHis[q]) && !arryCur[j].equals("=")){
											count = count + hisTimes;
											if(count > limitNum){
												return false;
											}
										}
									 }									 
								 }	
							}					 
					//	 }
					// }
			 	 if(count < limitNum){
			 		 //进行任选四中三的查询
			 		 int tempLimit = limitNum - count;
			 		 boolean tempFlag = compareWagerThree(limitNum,dbWagerNumList,tempWager,curTimes,playType,playMode,false);
			 		 if(!tempFlag){
			 			 return tempFlag;
			 		 }
			 	 }
			 }
			 
			}
		return true;
	}
	/**
	 *@deprecated 
	 *@Description:
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 8:38:59 AM
	 * @param curWagerTotal
	 * @param curTimes
	 * @param hisWagerTotal
	 * @param hisTimes
	 * @param playType
	 * @param playMode
	 */
	public void compareWagerTotal(String curWagerTotal,int curTimes,String hisWagerTotal,int hisTimes,String playType,String playMode){
		 
		//判断当前的投的注数是否是已经
		String[] arryCurWagerTotal = JStringToolkit.splitString(curWagerTotal,"$");
		
		for(int i = 0; i < arryCurWagerTotal.length; i++){
			 String tempWager = arryCurWagerTotal[i];//当前投的数据
			 String[][] arryCur = makeFourArry(tempWager);
			 String[] arryHisWagerTotal = JStringToolkit.splitString(hisWagerTotal,"$");
			 for(int j = 0; j < arryHisWagerTotal.length; i++){
				 String tempHisWager = arryHisWagerTotal[j];//历史投的数据
				 String[][] arryHis = makeFourArry(tempHisWager);		 
				 if(playType.equals("任选一")){
					 	
				 }
			 }
			 
		 }
		 
		
		
	}
	/**
	 * 
	 *@Description:生成数组，
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 1:52:12 PM
	 * @param str　形如：1,2|3|4,2,1|9
	 * @return
	 */
	public String[][] makeFourArry(String str){
		String[] sourceAry = JStringToolkit.splitString(str,"|");
		String[][] ary = new String[sourceAry.length][];
		
		for(int i = 0; i < sourceAry.length; i++){
			String temp = sourceAry[i];
//			if(temp.equals("=")){
//				ary[i] = new String[]{"0"};
//			}else{
				ary[i] = JStringToolkit.splitString(temp,",");
//			}
		 }
//		for(int k = 0; k < ary.length; k++){
//			for(int m = 0; m < ary[k].length; m ++){
//				String ss = ary[k][m]+"  ";
//				System.out.print("  "+ ss);
//			}
//			log.info("");
//		}
//		log.info("----------");
		return ary;
	}
	/**
	 * 
	 *@Description:根据时时乐,单选三
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str 0,1|0,1|0
	 * @return
	 */
	public String[] makeLaughDanxuanThree(String str){
		String[] sourceAry = JStringToolkit.splitString(str,"|");
		String[][] ary = new String[sourceAry.length][];
		
		for(int i = 0; i < sourceAry.length; i++){
			String temp = sourceAry[i];
			ary[i] = JStringToolkit.splitString(temp,",");
		 }
		
		String temp = "";
		for(int i = 0; i < ary.length-2;i++){
			for(int j = 0; j < ary[i].length;j++){
				//十位
				for(int m = i+1; m < ary.length-1;m++){
					for(int n = 0; n < ary[m].length;n++){
				//百位
						for(int p = m+1; p < ary.length;p++){
							for(int q = 0; q < ary[p].length;q++){
								temp = temp + ary[i][j]+ary[m][n]+ary[p][q]+",";
							}
						}
					}
				}
			}
		}
		String[] re = JStringToolkit.splitString(temp, ",");
		return re;
	}
	/**
	 * 
	 *@Description:根据时时乐,组选6
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str 1,2,3,4,5
	 * @return
	 */
	public String[] makeLaughZuxuanSix(String str){
		String[] sourceAry = JStringToolkit.splitString(str,",");
		String[] re ;
		if(sourceAry.length > 1){
			String temp = "";
			for(int i = 0; i < sourceAry.length-2; i++){
				for(int j = i+1; j < sourceAry.length-1; j++){
					for(int k = j+1; k < sourceAry.length; k++){
						temp = temp + sourceAry[i]+sourceAry[j]+sourceAry[k]+",";
					}
				}
			}
			
			re = JStringToolkit.splitString(str,",");
		}else{
			re = JStringToolkit.splitString(str,",");
		}
		return re;
		
	}
	/**
	 * 
	 *@Description:根据时时乐,前一
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str 1,2,3,4,5|=|=
	 * @return
	 */
	public String[] makeLaughZuxuanPreOne(String str){
		String temp = str.substring(0,str.indexOf("|"));
		String[] sourceAry = JStringToolkit.splitString(temp,",");		
		return sourceAry;	
	}
	/**
	 * 
	 *@Description:根据时时乐,后一
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str =|=|1,2,3,4,5
	 * @return
	 */
	public String[] makeLaughZuxuanLastOne(String str){
		String temp = str.substring(str.lastIndexOf("|")+1,str.length());
		String[] sourceAry = JStringToolkit.splitString(temp,",");		
		return sourceAry;	
	}
	/**
	 * 
	 *@Description:根据时时乐,前二
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str =|=|1,2,3,4,5
	 * @return
	 */
	public String[] makeLaughZuxuanPreTwo(String str){
		String temp = str.substring(0,str.lastIndexOf("|"));
		String[] sourceAry = JStringToolkit.splitString(temp,"|");
		String[][] ary = new String[sourceAry.length][];
		
		for(int i = 0; i < sourceAry.length; i++){
			String ss = sourceAry[i];
			ary[i] = JStringToolkit.splitString(ss,",");
		 }
		
		String tempStr = "";
		for(int i = 0; i < ary.length-1;i++){
			for(int j = 0; j < ary[i].length;j++){
				//十位
				for(int m = i+1; m < ary.length;m++){
					for(int n = 0; n < ary[m].length;n++){				
						tempStr = tempStr + ary[i][j]+ary[m][n]+",";						
					}
				}
			}
		}
		String[] re = JStringToolkit.splitString(tempStr, ",");
		return re;
	}
	/**
	 * 
	 *@Description:根据时时乐,前二
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str =|=|1,2,3,4,5
	 * @return
	 */
	public String[] makeLaughZuxuanLastTwo(String str){
		String temp = str.substring(str.indexOf("|")+1,str.length());
		String[] sourceAry = JStringToolkit.splitString(temp,"|");
		String[][] ary = new String[sourceAry.length][];
		
		for(int i = 0; i < sourceAry.length; i++){
			String ss = sourceAry[i];
			ary[i] = JStringToolkit.splitString(ss,",");
		 }
		
		String tempStr = "";
		for(int i = 0; i < ary.length-1;i++){
			for(int j = 0; j < ary[i].length;j++){
				//十位
				for(int m = i+1; m < ary.length;m++){
					for(int n = 0; n < ary[m].length;n++){				
						tempStr = tempStr + ary[i][j]+ary[m][n]+",";						
					}
				}
			}
		}
		String[] re = JStringToolkit.splitString(tempStr, ",");
		return re;
	}
	/**
	 * 
	 *@Description:根据时时乐,组选3,两个数，一个为重复的
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 2, 2009 12:33:27 PM
	 * @param str 1,2,3,4,5
	 * @return
	 */
	public String[] makeLaughZuxuanThree(String str){
		String[] sourceAry = JStringToolkit.splitString(str,",");
		String[] re ;
		String temp = "";
		if(sourceAry.length > 1){
			for(int i = 0; i < sourceAry.length-1; i++){
				for(int j = i+1; j < sourceAry.length; j++){			
					temp = temp + sourceAry[i]+sourceAry[i]+sourceAry[j]+",";
					temp = temp + sourceAry[i]+sourceAry[j]+sourceAry[i]+",";	
				}
			}
			re = JStringToolkit.splitString(temp,",");
		}else{
			re = JStringToolkit.splitString(str,",");
		}
		
		return re;
		
	}
	/**
	 * 
	 *@Description:将数组ary[4][]合并成ary[3][],4个中任选二
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 5:29:41 PM
	 * @param str 形如：1,2|3|4,2,1|9
	 * @return
	 */
	public String[][] makePokerFourToThreeArry(String str){
		//判断当前的投的注数是否是已经
		String[][] sourceAry = makeFourArry(str);
		String[][] returnAry = new String[sourceAry.length-1][];
		
		//如果第一组不为“=”时
		if(!sourceAry[0][0].equals("=")){
			String temp = "";
			for(int j = 0; j < sourceAry[0].length; j++){
				//int count = 0;
				//if(!sourceAry[1][0].equals("=")){
					
					for(int k = 0; k < sourceAry[1].length; k++){
						if(!sourceAry[1][k].equals("=")){
							temp = temp + sourceAry[0][j] + sourceAry[1][k] + "00,"; 
						}
					}
					for(int k = 0; k < sourceAry[2].length; k++){
						if(!sourceAry[2][k].equals("=")){
							temp = temp + sourceAry[0][j] + "0"+sourceAry[2][k] + "0,";
						}
					}
					for(int k = 0; k < sourceAry[3].length; k++){
						if(!sourceAry[3][k].equals("=")){
							temp = temp + sourceAry[0][j] + "00"+sourceAry[3][k] + ",";
						}
					}
//				}else if(!sourceAry[2][0].equals("=")){
//					for(int k = 0; k < sourceAry[2].length; k++){
//						temp = temp + sourceAry[0][j] + "0" + sourceAry[2][k] + "0,"; 
//					}
//				}else if(!sourceAry[3][0].equals("=")){
//					for(int k = 0; k < sourceAry[3].length; k++){
//						temp = temp + sourceAry[0][j] + "00" + sourceAry[3][k] + ","; 
//					}
//				}
			}
			if(temp.equals("")) temp = "=";
			returnAry[0] = JStringToolkit.splitString(temp, ",");
		}else{
			returnAry[0] = new String[]{"="};
		}
		//第二组
		if(!sourceAry[1][0].equals("=")){
			String temp = "";
			for(int j = 0; j < sourceAry[1].length; j++){
				//int count = 0;
				
				for(int k = 0; k < sourceAry[2].length; k++){
					if(!sourceAry[2][k].equals("=")){
						temp = temp +  "0" + sourceAry[1][j] + sourceAry[2][k] + "0,";
					}
				}
				for(int k = 0; k < sourceAry[3].length; k++){
					if(!sourceAry[3][k].equals("=")){
						temp = temp +  "0" + sourceAry[1][j] + "0"+sourceAry[3][k] + ",";
					}
				}
				
//				if(!sourceAry[2][0].equals("=")){
//					for(int k = 0; k < sourceAry[2].length; k++){
//						temp = temp + "0" + sourceAry[1][j] + sourceAry[2][k] + "0,"; 
//					}
//				}else if(!sourceAry[3][0].equals("=")){
//					for(int k = 0; k < sourceAry[3].length; k++){
//						temp = temp + "0"+sourceAry[1][j] + "0" + sourceAry[3][k] + ","; 
//					}
//				}
			}
			if(temp.equals("")) temp = "=";
			returnAry[1] = JStringToolkit.splitString(temp, ",");
		}else{
			returnAry[1] = new String[]{"="};
		}
		//第三组
		if(!sourceAry[2][0].equals("=")){
			String temp = "";
			for(int j = 0; j < sourceAry[2].length; j++){
				//int count = 0;
//				if(!sourceAry[3][0].equals("=")){
//					for(int k = 0; k < sourceAry[3].length; k++){
//						temp = temp + "00" + sourceAry[2][j] + sourceAry[3][k] + ","; 
//					}
//				}
				for(int k = 0; k < sourceAry[3].length; k++){
					if(!sourceAry[3][k].equals("=")){
						temp = temp +  "00" + sourceAry[2][j] + sourceAry[3][k] + ",";
					}
				}
			}
			if(temp.equals("")) temp = "=";
			returnAry[2] = JStringToolkit.splitString(temp, ",");
		}else{
			returnAry[2] = new String[]{"="};
		}
			
		return returnAry;
	}
	/**
	 * 
	 *@Description:将数组ary[4][]合并成ary[2][],4个中任选三
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 1, 2009 5:29:41 PM
	 * @param str 形如：1,2|3|4,2,1|9
	 * @return
	 */
	public String[][] makePokerFourToTwoArry(String str){
		String[][] sourceAry = makeFourArry(str);
		String[][] returnAry = new String[sourceAry.length-2][];
		//判断当前的投的注数是否是已经
		if(str.indexOf("=") != -1){//表示只选择了三种花色
			if(sourceAry[0].length == 1 && sourceAry[0][0].equals("=")){//如果第一位是"="
				returnAry[0] =  new String[]{"="};				
			
				for(int i = 0; i < sourceAry.length-3; i++){
					String temp = "";
					for(int j = 0; j < sourceAry[i].length; j++){

						//第二维					
						for(int k = i+1; k < sourceAry.length-2; k++){
							for(int m = 0; m < sourceAry[k].length; m ++){

						//第三维
								for(int p = k+1; p < sourceAry.length-1; p++){
									for(int q = 0; q < sourceAry[p].length; q ++){

										//第四组维
										for(int a = p+1; a < sourceAry.length; a++){
											for(int b = 0; b < sourceAry[a].length; b ++){
												if(sourceAry[i][j].equals("=")){
													sourceAry[i][j] = "0";
												}
												if(sourceAry[k][m].equals("=")){
													sourceAry[k][m] = "0";
												}
												if(sourceAry[p][q].equals("=")){
													sourceAry[p][q] = "0";
												}
												if(sourceAry[a][b].equals("=")){
													sourceAry[a][b] = "0";
												}
												temp = temp + sourceAry[i][j]+sourceAry[k][m]+sourceAry[p][q]+sourceAry[a][b]+",";
											}
										}
									}
								}
							}					
						}
					}
					returnAry[i+1] = JStringToolkit.splitString(temp,",");
				}
			}else{//如果第一位不是"="
				for(int i = 0; i < sourceAry.length-3; i++){
					String temp = "";
					for(int j = 0; j < sourceAry[i].length; j++){

						//第二维					
						for(int k = i+1; k < sourceAry.length-2; k++){
							for(int m = 0; m < sourceAry[k].length; m ++){

						//第三维
								for(int p = k+1; p < sourceAry.length-1; p++){
									for(int q = 0; q < sourceAry[p].length; q ++){

										//第四组维
										for(int a = p+1; a < sourceAry.length; a++){
											for(int b = 0; b < sourceAry[a].length; b ++){
												if(sourceAry[i][j].equals("=")){
													sourceAry[i][j] = "0";
												}
												if(sourceAry[k][m].equals("=")){
													sourceAry[k][m] = "0";
												}
												if(sourceAry[p][q].equals("=")){
													sourceAry[p][q] = "0";
												}
												if(sourceAry[a][b].equals("=")){
													sourceAry[a][b] = "0";
												}
												temp = temp + sourceAry[i][j]+sourceAry[k][m]+sourceAry[p][q]+sourceAry[a][b]+",";
											}
										}
									}
								}
							}					
						}
					}
					returnAry[0] = JStringToolkit.splitString(temp,",");
				}
				returnAry[1] = new String[] {"="};
			}
		}else{
			for(int i = 0; i < sourceAry.length-2; i++){
				String temp = "";
				for(int j = 0; j < sourceAry[i].length; j++){
					//第二维
					for(int k = i+1; k < sourceAry.length-1; k++){
						for(int m = 0; m < sourceAry[k].length; m ++){
					//第三维
							for(int p = k+1; p < sourceAry.length; p++){
								for(int q = 0; q < sourceAry[p].length; q ++){
									if(i==0){
										if(k == 1){
											if(p == 2){
												temp = temp + sourceAry[i][j]+sourceAry[k][m]+sourceAry[p][q]+"0,";
	
											}
											if(p == 3){
												temp = temp + sourceAry[i][j]+sourceAry[k][m]+"0"+sourceAry[p][q]+",";
											}
										}
										if(k == 2){
											if(p == 3){
												temp = temp + sourceAry[i][j]+"0"+sourceAry[k][m]+sourceAry[p][q]+",";
											}
										}							
									}
									if(i==1){
										if(k == 2){
											if(p == 3){
												temp = temp + "0"+sourceAry[i][j]+sourceAry[k][m]+sourceAry[p][q]+",";
											}
										}
										
									}
								}
							}
						}					
					}
				}
				returnAry[i] = JStringToolkit.splitString(temp,",");
			}
		}
		result:
	    return returnAry;
	}
	/**
	 * 
	 *@Description:四选四
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Jun 3, 2009 8:38:29 PM
	 * @param str
	 * @return
	 */
	public String[] makePokerFourToOneArry(String str){
		String[][] sourceAry = makeFourArry(str);
		String[] returnAry = new String[1];
		//判断当前的投的注数是否是已经
		for(int i = 0; i < sourceAry.length-3; i++){
			String temp = "";
			for(int j = 0; j < sourceAry[i].length; j++){
				//第二组
				for(int m = i+1; m < sourceAry.length-2; m++){
					for(int n = 0; n < sourceAry[m].length; n++){
						//第三组
						for(int p = m+1; p < sourceAry.length-1; p++){
							for(int q = 0; q < sourceAry[p].length; q++){
						
								
								//第四组
								for(int a = p+1; a < sourceAry.length; a++){
									for(int b = 0; b < sourceAry[a].length; b++){
								
										temp = temp + sourceAry[i][j] + sourceAry[m][n] + sourceAry[p][q] + sourceAry[a][b] + ",";
									}
								}
							}
						}
					}
				}
			}
			returnAry = JStringToolkit.splitString(temp,",");
		}
		return returnAry;
	}
	/**
	 * 
	 *@Description:查询当前玩法的限号情况
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on May 31, 2009 10:20:56 AM
	 * @param gameType：游戏类型 　poker:扑克,laugh：时时乐,hap:福 彩 3D ,pth:排列3
	 * @param playType：玩法类型
	 * @param otherField 其它条件
	 * @return 返回当前玩法的限号情况，如果为空，则默认为0
	 */
	public int getLimitNum(String gameType,String playType,String playMode){
		int limitNum = 0; 
		String strRule = getRuleField(gameType,playType,playMode);
		String strTable = getPrMoneyTable(gameType);
		String sql = "select limit_degree from " + strTable + " where 1=1 and rule='" + strRule + "'" ;
		DataBaseConn conn=new DataBaseConn();
		conn.execQuery(sql);
		conn.rsNext();
		String strLimit = conn.rsGetString("limit_degree");
		if(strLimit == null || strLimit.equals("")){
			limitNum = 0;
		}else{
			limitNum = Integer.parseInt(strLimit);
		}
		return limitNum;
	}
	/**
	 * 
	 *@Description: 根据游戏类型及相应的玩法，返回相应的规则字段
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on May 31, 2009 10:47:11 AM
	 * @param gameType
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public String getRuleField(String gameType,String playType,String playMode){
		String strRule = "";
		
		//快乐扑克
		if(gameType.equals("poker")){
		
			if(playType.equals("任选一")){  strRule = "pokone";}    	
			if(playType.equals("任选二")){  strRule = "poktwo";}    	
			if(playType.equals("任选三")){  strRule = "pokthrthr";} 	
//			if(playType.equals("任选三中3")){  strRule = "pokthrthr";} 	
//			if(playType.equals("任选三中2")){  strRule = "pokthrtwo";} 	
			if(playType.equals("选四直选")){  strRule = "pokfourfour" ;}  	
//			if(playType.equals("选四直选中4")){  strRule = "pokfourfour" ;}  	
//			if(playType.equals("选四直选中3")){  strRule = "pokfourthr"  ;}  	

			if(playMode.equals("组4")){  strRule = "pokgrfour";} 	
			if(playMode.equals("组6")){  strRule = "pokgrsix";}  	
			if(playMode.equals("组12")){  strRule = "pokgrtwelve";}    	
			if(playMode.equals("组24")){  strRule = "pokgrtwfour" ;}
//			if(playType.equals("选四组选4")){  strRule = "pokgrfour";} 	
//			if(playType.equals("选四组选6")){  strRule = "pokgrsix";}  	
//			if(playType.equals("选四组选12 ")){  strRule = "pokgrtwelve";}    	
//			if(playType.equals("选四组选24 ")){  strRule = "pokgrtwfour" ;}
			
		}
		//时 时 乐
		if(gameType.equals("laugh")){
			if(playType.equals("单选三")){  strRule    = "lausingthr";}  
			 
			if(playMode.equals("前二")){  strRule       = "laufrontwo";}   
			if(playMode.equals("后二")){  strRule       = "laubacktwo";}   
			if(playMode.equals("前一")){  strRule       = "laufronone";}   
			if(playMode.equals("后一")){  strRule       = "laubackone";}   
			
//			if(playType.equals("前二")){  strRule       = "laufrontwo";}   
//			if(playType.equals("后二")){  strRule       = "laubacktwo";}   
//			if(playType.equals("前一")){  strRule       = "laufronone";}   
//			if(playType.equals("后一")){  strRule       = "laubackone";}   
			if(playType.equals("组3")){  strRule       = "laugrthr";}     
			if(playType.equals("组6")){  strRule       = "laugrsix";}     
		}
        //排 列 三
		if(gameType.equals("pth")){
			if(playType.equals("直选")){  strRule       = "pthsingthr";}   
			if(playMode.equals("组3")){  strRule       = "pthgrthr";}     
			if(playMode.equals("和值") || playMode.equals("组6")){  strRule       = "pthgrsix";}    
		}
		
		// 福 彩 3D 
		if(gameType.equals("hap")){
			if(playType.equals("直选")){  strRule       = "hapsingthr";}   
			if(playMode.equals("组3")){  strRule       = "hapgrthr";}     
			if(playMode.equals("组6")){  strRule       = "hapgrsix";}    
		}
		
		
		return strRule;

	}
	
	/**
	 * 
	 *@Description:根据游戏类型，返回相应的规则表名
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on May 31, 2009 10:45:13 AM
	 * @param gameType　poker:扑克,laugh：时时乐,hap:福 彩 3D ,pth:排列3
	 * @return
	 */
	public String getPrMoneyTable(String gameType){
		String strTable = "";
		if(gameType.equals("poker")){  strTable       = "pokprmoney";}   
		if(gameType.equals("laugh")){  strTable       = "lauprmoney";}     
		if(gameType.equals("hap")){  strTable       = "happrmoney";}    
		if(gameType.equals("pth")){  strTable       = "pthprmoney";}    
		return strTable;
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
	public String getWagerInfoTable(String gameType){
		String strTable = "";
		if(gameType.equals("poker")){  strTable       = "pokwagerinfo";}   
		if(gameType.equals("laugh")){  strTable       = "lauwagerinfo";}     
		if(gameType.equals("hap")){  strTable       = "hapwagerinfo";}    
		if(gameType.equals("pth")){  strTable       = "pthwagerinfo";}    
		return strTable;
	}
	/**
	 * 
	 *@Description:查询当期当前玩法的已投注情况
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on May 31, 2009 12:05:17 PM
	 * @param issueNum
	 * @param gameType
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public List getCurWagerInfo(String issueNum,String gameType,String playType,String playMode,String otherField){
		List list = new ArrayList();
		String wagerInfoTable = getWagerInfoTable(gameType);
		String sql = "";
		//如果是组选，则查询playMode
		if(gameType.equals("poker")){
			if(playType.indexOf("组选") != -1){
				sql = "select * from " + wagerInfoTable + " where issuenum='" + issueNum + "' and playmode='" + playMode+ "'"+otherField;
	//		}else if(playType.equals("组合投注")){
	//			sql = "select * from " + wagerInfoTable + " where issuenum='" + issueNum + "' and playtype='" + playType+ "'"+otherField;
			}else{
				sql = "select * from " + wagerInfoTable + " where issuenum='" + issueNum + "' and (playtype='" + playType+ "' or playtype='组合投注' )"+otherField;
			}
		}else{
			sql = "select * from " + wagerInfoTable + " where issuenum='" + issueNum + "' and playtype='" + playType+ "' "+otherField;
		}
		DataBaseConn hasMoneyDBC=new DataBaseConn();
		ResultSet rs = hasMoneyDBC.execQuery(sql);
		try{
			while(rs.next()){
				WagerInfo info = new WagerInfo();
				info.setUserName(rs.getString("userName"));
				info.setMarkiss(rs.getString("markiss"));
				info.setMarkissseq(rs.getString("markissseq"));
				info.setIssuenum(rs.getString("issuenum"));
				info.setHasfol(rs.getString("hasfol"));
				info.setValid(rs.getString("valid"));
				info.setPlaytype(rs.getString("playtype"));
				info.setPlaymode(rs.getString("playmode"));
				//是poker时，有此字段
				if(gameType.equals("poker")){
					info.setWagtotbef(rs.getString("wagtotbef"));
				}
				
				info.setWagertotal(rs.getString("wagertotal"));
				info.setWagernum(rs.getString("wagernum"));
				info.setTimes(rs.getString("times"));
				
				list.add(info);

			}	
		}catch(Exception e){
			log.error("查询投注情况时出错：useWagEn.java 130行:" + e.toString());
		}
		return list;
		
	}
	/**
	 * 判断一个字符在别一个中出现的次数
	 *@Description:
	 *@Author:LJY
	 *@E-mail:lijiyongcn@sohu.com
	 *@Create on Aug 22, 2009 10:49:51 PM
	 * @param str
	 * @param con
	 * @return
	 */
	public int numberOfStr(String str, String con){ 
		str = " "+str; 
		if(str.indexOf(con) == -1){
			return 0;
		}
		if(str.endsWith(con)){ 
			return str.split(con).length; 
		}else{ 
			return str.split(con).length - 1; 
		} 
	}
	
	public static void main(String[] args){
		String str = "1,2|3|4,2,1|9";
		CompareLimitNum cln = new CompareLimitNum();
		String poker3 = "7|8,9|10,11|10,11";
//		String[][] temp = cln.makeFourToThreeArry(str);
//		String[][] tempPoker = cln.makePokerFourToThreeArry(poker3);
		
		String kk = "4|5|6|7";
		String kk2 = "4|5|=|7";
		String kk3 = "4,5|6,7|7,9|8,10";
       
		//String[][] tempPoker = cln.makePokerFourToTwoArry(kk2);
		String[][] tempPoker = cln.makePokerFourToTwoArry(kk2);
		System.out.println("len="+cln.numberOfStr(kk,"="));
//		System.out.println("len="+tempPoker.length);
		
		for(int k = 0; k < tempPoker.length; k++){
			for(int m = 0; m < tempPoker[k].length; m ++){
				String ss = tempPoker[k][m]+"  ";
				//String ss = tempPoker[k];
				System.out.print("  "+ ss);
			}
			System.out.println("  ");
		}
		String laugh = "3,4|5|6|=";
//		String laugh = "1,2,3,4,5,6,7,8,9";
//		String[] temp = cln.makeThreeArry(laugh);
		//String[] temp = cln.makeLaughZuxuanSix(laugh);
//		String[] temp = cln.makeLaughZuxuanThree(laugh);
		
//		String laugh = "1,2,3,4,5|=|=";
//		String laugh = "=|=|1,2,3,4,5";
//		String laugh = "=|2,3|2,3,4,5,6,7,8,9";
//		String[] temp = cln.makeLaughZuxuanPreOne(laugh);
//		String[] temp = cln.makeLaughZuxuanLastOne(laugh);
//		String[] temp = cln.makeLaughZuxuanPreTwo(laugh);
//		String[] temp = cln.makeLaughZuxuanLastTwo(laugh);
		String[] temp = cln.makePokerFourToOneArry(kk3);
		//System.out.println("len="+temp.length);
		for(int k = 0; k < temp.length; k++){
			
				String ss = temp[k];
				System.out.print("  "+ ss);
			
			
		}
		
		
	}
}
