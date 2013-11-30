package com.onmyway.ssc.play.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;
import util.JStringToolkit;

import com.onmyway.factory.AnalyseBuilder;
import com.onmyway.factory.PlayNumInfo;
import com.onmyway.init.SscLimitInfoInit;
import com.onmyway.ssc.play.model.SscTzInfo;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:
 * @Description: 
 * @Create on: Oct 4, 2010 8:54:38 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SscLimitServiceImpl implements ISscLimitService{

	Logger log = Logger.getLogger(SscLimitServiceImpl.class);
	
	DBTool dbTool = DBAccess.getDBTool();
	/**
	 * 当前投注的号码是否被限号
	 * @param info
	 * @return
	 */
	public boolean isLimitNum(SscTzInfo info){
		boolean flag = true;
		String playType = info.getPlayType();
		String typeName = info.getTypeName();
		String playMode = info.getPlayMode();
		String issueNum = info.getIssueNum();
		String playNum  = info.getTouzhuNum();
		String haveZhuitou = info.getHaveZhuitou();
		String zhuitouInfo = info.getZhuitouInfo();
		
		//得到当前投注号码的详细信息
		AnalyseBuilder numBuilder = new AnalyseBuilder();//号码分析器
		List<PlayNumInfo> tempList = new ArrayList<PlayNumInfo>();
		if(playMode.equals("zu120") || playMode.equals("zu60")|| playMode.equals("zu30") || playMode.equals("zu20") 
				|| playMode.equals("zu10") || playMode.equals("zu5")|| playMode.equals("zuchong2")|| playMode.equals("zuchong3")|| playMode.equals("zuchong4")
				|| playMode.equals("zu24")|| playMode.equals("zu12")|| playMode.equals("zu6")|| playMode.equals("zu4")){
			
			tempList = numBuilder.builderZuxuanNumList(playNum, playType, typeName, playMode,"");
		}else{
			tempList = numBuilder.builderNumList(playNum, playType, typeName, playMode,"");
			
		}
		
		//如果有追投信息，则需要判断追投的号码是否超出限号
		if(haveZhuitou.equals("1")){//表示有追投信息
			String[] aryZhuitou = JStringToolkit.splitString(zhuitouInfo, "$");//将追投信息进行分割，得到每期的信息
			for(int i = 0; i < aryZhuitou.length; i++){
				String tempIssueNum = aryZhuitou[i];
				String[] aryIssueInfo = JStringToolkit.splitString(tempIssueNum, "|");//将每期信息进行分割，得到期号及相应的倍数
				String tempIssue = aryIssueInfo[0];//期号
				String tempTimes = aryIssueInfo[1];//倍数
				
				//对一期的号码进行
				boolean f = judgeOneNumLimit(playType,typeName,playMode,playNum,tempIssue,tempTimes,tempList);
				
				if(!f){
					flag = false;
					break;
				}

			}
				
		}
		if(haveZhuitou.equals("0")){//表示没有追投信息
			
			//judgeOneNumLimit(playType,typeName,playMode,issueNum,"1",tempList);

			//对一期的号码进行
			boolean f = judgeOneNumLimit(playType,typeName,playMode,playNum,issueNum,"1",tempList);
			
			if(!f){
				flag = false;				
			}		
		}
		return flag;
	}
	/**
	 * 
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param playNum:对应的是原始的投注号码
	 * @param tempIssue
	 * @param theNum 仅对一个号码
	 * @param thisTimes 此号码对应的投注的倍数
	 * @return
	 */
	public boolean judgeOneNumLimit(String playType,String typeName,String playMode,String playNum,String tempIssue,String thisTimes,List<PlayNumInfo> tempList){
		boolean flag = true;
		String sql = "";

		
		String repeatZu3 = "";
		String repeatZu6 = "";
		//因为在判断zu3的时候，是将一注分解成了多组。而还未保存到数据数据库冲，所以如果投注的为相同的注数，则需要翻倍，
		//如：789已经投了8注，而限号时10注，如果投的组6为789,789，两注，且都为倍投2倍，则都保存到数据库中后为4注，则超出限倍，为弥补这个bug ，则作此限制
		
		Map<String,Integer> repeatZu3Map = new HashMap<String,Integer>();
		Map<String,Integer> repeatZu6Map = new HashMap<String,Integer>();
		Map<String,String> tempZu3Map = new HashMap<String,String>();
		Map<String,String> tempZu6Map = new HashMap<String,String>();
		
		int dangqianTimes = 1;//当前投的倍数(默认为1倍)
		for(PlayNumInfo numInfo : tempList){
			String tempPlayType = numInfo.getPlayType();
			String tempTypeName = numInfo.getTypeName();
			String tempPlayMode = numInfo.getPlayMode();
			String tempPlayNum  = numInfo.getPlayNum();
			
			String[] aryPlayNum = JStringToolkit.splitString(tempPlayNum, ",");
			for(int i=0; i<aryPlayNum.length; i++){
				//五星通选
				String tempSql = "";
				if(tempPlayType.equals("wuxing")){
					if(tempPlayMode.equals("zhixuan") || tempPlayMode.equals("fushi")){
						if(tempTypeName.equals("tx")){
							tempSql = wuxingTxLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
						}else {
							tempSql = wuxingLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
						}	
					}
					//五星组选
					if(tempPlayMode.equals("zu120") || tempPlayMode.equals("zu60") || tempPlayMode.equals("zu30") || tempPlayMode.equals("zu20") 
							|| playMode.equals("zu10") || playMode.equals("zu5") || playMode.equals("zuchong2")|| playMode.equals("zuchong3")|| playMode.equals("zuchong4")){
						tempSql = wuxingZuxuanLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i],tempPlayMode);
					}
				
				}
				if(tempPlayType.equals("sixing")){
					if(tempPlayMode.equals("zhixuan")){
						tempSql = sixingLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}
					//四星组选
					if(tempPlayMode.equals("zu24") || tempPlayMode.equals("zu12") || tempPlayMode.equals("zu6") || tempPlayMode.equals("zu4") ){
						tempSql = sixingZuxuanLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i],tempPlayMode);
					}
				}
				//三星
				if(tempPlayType.equals("sanxing")){
					if(tempPlayMode.equals("zhixuan")){
						tempSql = sanxingZhixuanLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}
					//if(typeName.equals("zuxuan")){
					//计算zu3，组6时，重复的次数
					if(tempPlayMode.equals("zu3")){
						tempSql = sanxingZu3LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
						//为修补zu3,zu6投注在一注中重复的bug
//						String tempAry = "," + aryPlayNum[i] + ",";
						String tempAry = aryPlayNum[i];
						if(tempZu3Map.get(tempAry) != null){
							if(repeatZu3Map.get(tempAry) != null){
								repeatZu3Map.put(tempAry, new Integer(repeatZu3Map.get(tempAry).intValue() + 1));
							}else{
								repeatZu3Map.put(tempAry, new Integer(2));//从2开始，如果进入这个map，则表示已经有2个重复的值了
							}							
						}else{
							tempZu3Map.put(tempAry, tempAry);
						}
					}
					if(tempPlayMode.equals("zu6")){
						tempSql = sanxingZu6LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
						//为修补zu3,zu6投注在一注中重复的bug
						String tempAry =  aryPlayNum[i] ;
						if(tempZu6Map.get(tempAry) != null){
							if(repeatZu6Map.get(tempAry) != null){
								repeatZu6Map.put(tempAry, new Integer(repeatZu6Map.get(tempAry).intValue() + 1));
							}else{
								repeatZu6Map.put(tempAry, new Integer(2));//从2开始，如果进入这个map，则表示已经有2个重复的值了
							}							
						}else{
							tempZu6Map.put(tempAry, tempAry);
						}
					}
					//}
					
				}
				if(tempPlayType.equals("erxing")){
					if(tempPlayMode.equals("zhixuan")){
						tempSql = erxingLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}
					if(tempPlayMode.equals("zuxuan")){
						tempSql = erxingZuxuanLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					} 
				}
				if(tempPlayType.equals("yixing")){
					tempSql = yixingLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				if(tempPlayType.equals("dxds")){
					tempSql = dxdsLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				if(tempPlayType.equals("renxuan1")){
					tempSql = renxuan1LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				if(tempPlayType.equals("renxuan2")){
					tempSql = renxuan2LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				if(tempPlayType.equals("renxuan3")){
					tempSql = renxuan3LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				//将多个sql进行拼接
				if(sql.equals("")){
					sql = tempSql;
				}else{
					sql = sql + " union " + tempSql;
				}
			}
		}
		log.info("查询限号sql=" + sql);
		//log.info("查询已投注的信息");
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{
			Map<String,String> limitMap = SscLimitInfoInit.getSscLimitMap();

			if(crs != null && crs.size() > 0){
				while(crs.next()){
	//				String crsId = crs.getString("id");
					String crsPlayType = crs.getString("play_type");
	//				String crsTypeName = crs.getString("type_name");
	//				String crsPlayMode = crs.getString("play_mode");
					String crsTempPlayNum = crs.getString("temp_play_num");//当前判断的号码，如何需要返回时哪个号码超出限号，则将此值返回即可
	//				float crsNumTimes = crs.getFloat("num_times");//已投注的相应号码的倍数
					float crsNumTimes = crs.getFloat("num_times");//已投注的相应号码的注数，现在改为先计算每个投注号码里有注中奖，再乘以倍数，
					String crsTempPlayType = crs.getString("temp_play_type");
					String crsTempPlayMode = crs.getString("temp_play_mode");
					
					// 类型和临时模式的组合，应该为配置的限号规则的名称，约定都为小写.如：play_type+temp_play_mode="wuxingqian2" wuxingqian2,wuxingqian3,wuxinghou3,wuxingzhixuan,sanxingzhixuan
					String temp = "";
					//2011-10-17 前三后三统一为二等奖;前二后二统一为三等奖
					if(crsTempPlayMode.equals("hou3")){
						temp = crsPlayType + "qian3";						
					}else if(crsTempPlayMode.equals("hou2")){
						temp = crsPlayType + "qian2";						
					}else if(crsTempPlayMode.equals("renxuan1") || crsTempPlayMode.equals("renxuan3") || crsTempPlayMode.equals("renxuan2") ){
						temp = crsPlayType;
					}else{
						temp = crsPlayType + crsTempPlayMode;						
					}
					
					//log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode);
					String strConfigTimes = limitMap.get(temp);//系统设置的倍数
					int configTimes = 0;//配置的限制注数
	 
					
					int yitouTimes = 0;//已投的倍数
					int yitouZhushu = 0;//已投的注数
					
					int timesInTzNumYitou = 0;//已投号码中，符合条件的注数
					int timesInTzNumDangqian = 0;//当前号码中，符合条件的注数
					log.info(temp + "的配置值是:" + strConfigTimes);
					try{
	//					yitouTimes = Integer.parseInt(crsNumTimes);
	//					yitouTimes = (int)crsNumTimes;
						yitouZhushu = (int)crsNumTimes;//已投的注数
						dangqianTimes = Integer.parseInt(thisTimes);
						configTimes = Integer.parseInt(strConfigTimes);
					}catch(NumberFormatException ex){
						log.error("限号：数字格式化异常:" ,ex);
					}
					
					
					
					//判断当前号码中有多少注
					for(PlayNumInfo numInfo : tempList){
						String tempPlayType = numInfo.getPlayType();
						String tempTypeName = numInfo.getTypeName();
						String tempPlayMode = numInfo.getPlayMode();
						String tempPlayNum  = numInfo.getPlayNum();
						
						String[] aryPlayNum = JStringToolkit.splitString(tempPlayNum, ",");
						for(int i=0; i<aryPlayNum.length; i++){
							int danqianZhushu = getEveryTimesInCurPlayNum(tempPlayType,tempPlayMode,crsTempPlayMode,tempTypeName,tempPlayNum,aryPlayNum[i],repeatZu3Map,repeatZu6Map);;//当前号码在投注的号码中奖的注数
							
 
							log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",当前判断的号码:" + crsTempPlayNum +",已投注数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",当前每注中奖数：:" + danqianZhushu + ",配置倍数="+configTimes);
							if((yitouZhushu + danqianZhushu*dangqianTimes) > configTimes){
								flag = false;
								break;
							}	
						}
					}
					
					//log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",当前判断的号码:" + crsTempPlayNum +",已投倍数=" + yitouTimes + ",当前投注倍数="+dangqianTimes+",配置倍数="+configTimes);
								
				}
			}else{
				
				//对五星通选进行特殊判断
				String temp = "";
				if(typeName.equals("tx") 
							|| (playType.equals("wuxing") && typeName.equals("zhixuan")) 
							|| (playType.equals("sixing") && typeName.equals("zhixuan")) 
							|| (playType.equals("sanxing") && typeName.equals("zhixuan"))
							|| (playType.equals("erxing") && typeName.equals("zhixuan"))
							|| (playType.equals("yixing") && typeName.equals("zhixuan"))
							
				){
							
					
					temp = playType + typeName;
				}else if(typeName.equals("renxuan1") || typeName.equals("renxuan2") || typeName.equals("renxuan3")){
					temp = playType;
				}else{
					temp = playType + playMode;
				}
				
					
				
				String strConfigTimes = limitMap.get(temp);//系统设置的倍数
				int configTimes = 0;//配置的限制注数
 
				
				int yitouTimes = 0;//已投的倍数
				int yitouZhushu = 0;//已投的注数
				
				int timesInTzNumYitou = 0;//已投号码中，符合条件的注数
				int timesInTzNumDangqian = 0;//当前号码中，符合条件的注数
				
				try{ 
					dangqianTimes = Integer.parseInt(thisTimes);
					if(strConfigTimes != null){
						configTimes = Integer.parseInt(strConfigTimes);
					}else{
						log.warn("时时彩：查询限号倍数为空");
					}
					
				}catch(NumberFormatException ex){
					log.warn("第一次投注限号：数字格式化异常:" ,ex);
				}
			
				//判断当前号码中有多少注
				for(PlayNumInfo numInfo : tempList){
					String tempPlayType = numInfo.getPlayType();
					String tempTypeName = numInfo.getTypeName();
					String tempPlayMode = numInfo.getPlayMode();
					String tempPlayNum  = numInfo.getPlayNum();
					
					if(typeName.equals("zuhe") || typeName.equals("zuxuan")){
						String tempConfigTimes = limitMap.get(tempPlayType+tempPlayMode);//对于“组合2，组合3，组合4，组合5”，因为里面是不同的玩法，要分别对应不同的值
						log.info("时时彩：组合玩法：拆分其中的玩法模式为：" + (tempPlayType+tempPlayMode) + ",新的限制倍数值为：" + tempConfigTimes);
						configTimes = Integer.parseInt(tempConfigTimes);
						
					}
					
					String[] aryPlayNum = JStringToolkit.splitString(tempPlayNum, ",");
					for(int i=0; i<aryPlayNum.length; i++){
						int danqianZhushu = getEveryTimesInCurPlayNum(tempPlayType,tempPlayMode,tempPlayMode,tempTypeName,tempPlayNum,aryPlayNum[i],repeatZu3Map,repeatZu6Map);;//当前号码在投注的号码中奖的注数
						
						log.info("该号码之前未投注，查询限号：playType=" + playType + ",crsTempPlayMode="+playMode + ",当前判断的号码:" + tempPlayNum +",已投注数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",当前每注中奖数：:" + danqianZhushu + ",配置倍数="+configTimes);
						if((yitouZhushu + danqianZhushu*dangqianTimes) > configTimes){
							flag = false;
							break;
						}	
					}
				}
						
			}
					
		}catch(Exception e){
			log.error("查询限号信息异常:" + e.toString());
		}
		return flag;
		/**
		 * 得到一个号码在一组投注号码中出现的次数
		 * @param playType 玩法类型
		 * @param totalPlayNum 当前全部的投注号码
		 * @param onePlayNum 当前投注号码中分其中一个号码
		 * @return 一个号码在总的投注串中出现的次数
		 */
	}
/**
 * 得到一个号码在一组投注号码中出现的次数
 * @param playType玩法类型
 * @param playMode
 * @param crsPlayMode
 * @param typeName 
 * @param totalPlayNum 当前全部的投注号码
 * @param onePlayNum 当前投注号码中分其中一个号码
 * @param repeatZu3Map
 * @param repeatZu6Map
 * @return 一个号码在总的投注串中出现的次数
 */
	 
	public int getEveryTimesInCurPlayNum(String playType,String playMode,String crsPlayMode,String typeName,String totalPlayNum,String onePlayNum,Map<String,Integer> repeatZu3Map,Map<String,Integer> repeatZu6Map){

		int danqianZhushu = 1;//当前号码在投注的号码的注数,至少为1
		//五星通选
		//String tempSql = "";
		if(playType.equals("wuxing")){
			if(playMode.equals("zhixuan")||playMode.equals("fushi")){
				if(typeName.equals("tx")){
					//前3后3
					String qian3 = onePlayNum.substring(0,3);
					
					String hou3  = onePlayNum.substring(2,5);
					//前2后2
					String qian2 = onePlayNum.substring(0,2);
					String hou2  = onePlayNum.substring(3,5);
					
					if(crsPlayMode.equals("qian3")){
						//qian3 
						danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+qian3);	
					}else if(crsPlayMode.equals("hou3")){
						//hou3
						danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,hou3+",");
					}else if(crsPlayMode.equals("qian2")){
						//qian2
						danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+qian2);
					}else if(crsPlayMode.equals("hou2")){
							//hou2
						danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,hou2+",");
					}else {
						danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
					}
					
				}else {
					//tempSql = wuxingLimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
				}	
			}
			if(playMode.equals("zu120")||playMode.equals("zu60")||playMode.equals("zu30") || playMode.equals("zu20") 
				|| playMode.equals("zu10") || playMode.equals("zu5") || playMode.equals("zuchong2")|| playMode.equals("zuchong3")|| playMode.equals("zuchong4")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
			}
		}
		//四星
		if(playType.equals("sixing")){
			if(playMode.equals("zhixuan")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
			}
			if(playMode.equals("zu24")||playMode.equals("zu12")||playMode.equals("zu6") || playMode.equals("zu4")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
			}
		}
		//三星
		if(playType.equals("sanxing")){
			if(playMode.equals("zhixuan")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
			}
			if(playMode.equals("zu3")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
				//为修补zu3,zu6投注在一注中重复的bug
				Integer ttt = repeatZu3Map.get(onePlayNum);
				if(ttt != null){
					danqianZhushu = ttt.intValue() * danqianZhushu;
				}
				
			}
			if(playMode.equals("zu6")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
				//为修补zu3,zu6投注在一注中重复的bug
				Integer ttt = repeatZu6Map.get(onePlayNum);
				if(ttt != null){
					danqianZhushu = ttt.intValue() * danqianZhushu;
				}
			}
		}
		if(playType.equals("erxing")){
			if(playMode.equals("zhixuan")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
			}
			if(playMode.equals("zuxuan")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
			} 
		}
		if(playType.equals("yixing")){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
		}
		if(playType.equals("dxds")){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,","+onePlayNum+",");
		}
		return danqianZhushu;
	}
	/**
	 * 判断五星通选，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String wuxingTxLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
		//前3后3
		String qian3 = playNum.substring(0,3);
		String hou3  = playNum.substring(2,5);
		//前2后2
		String qian2 = playNum.substring(0,2);
		String hou2  = playNum.substring(3,5);
//		String sql = "select play_type,num_times,'wuxingtx' as 'temp_play_type','zhixuan' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" 
//				   + " union " 
//				   + " select  play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','qian3' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and typeName='wuxingtx' and playNum like  '%,"+qian3 + "__,%'"
//				   + " union " 
//				   + " select  play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','hou3' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and typeName='wuxingtx' and playNum like  '%,__"+hou3 + ",%'"
//				   + " union " 
//				   + " select  play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','qian2' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and typeName='wuxingtx' and playNum like  '%,"+qian2 + "___,%'"
//				   + " union " 
//				   + " select  play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','hou2' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and typeName='wuxingtx' and playNum like  '%,___"+hou2 + ",%'";
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num, 'wuxingtx' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='wuxing' and play_mode='zhixuan' and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" 
//					+ " union " 
//					+ " select  play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','qian3' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,"+qian3 + "__,%' group by play_type,temp_play_type,temp_play_mode,temp_play_num"
//					+ " union " 
//					+ " select  play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','hou3' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,__"+hou3 + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num"
//					+ " union " 
//					+ " select  play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','qian2' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,"+qian2 + "___,%' group by play_type,temp_play_type,temp_play_mode,temp_play_num"
//					+ " union " 
//					+ " select  play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','hou2' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,___"+hou2 + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num";
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num, 'wuxingtx' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='wuxing' and play_mode='zhixuan' and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" 
		+ " union " 
		+ " select  play_type,sum((length(replace(num_detail,',"+qian3+"',concat(',"+qian3+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','qian3' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,"+qian3 + "__,%' group by play_type,temp_play_type,temp_play_mode,temp_play_num"
		+ " union " 
		+ " select  play_type,sum((length(replace(num_detail,'"+hou3+",',concat('"+hou3+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','hou3' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,__"+hou3 + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num"
		+ " union " 
		+ " select  play_type,sum((length(replace(num_detail,',"+qian2+"',concat(',"+qian2+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','qian2' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,"+qian2 + "___,%' group by play_type,temp_play_type,temp_play_mode,temp_play_num"
		+ " union " 
		+ " select  play_type,sum((length(replace(num_detail,'"+hou2+",',concat('"+hou2+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxingtx' as 'temp_play_type','hou2' as 'temp_play_mode'  from ssc_tz_detail where play_type='wuxing' and type_name='tx'  and issue_num='" + issueNum + "' and num_detail like  '%,___"+hou2 + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num";
		return sql;
	}
	/**
	 * 判断五星
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String wuxingLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='wuxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断五星120
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String wuxingZu120LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxing' as 'temp_play_type','zu120' as 'temp_play_mode' from ssc_tz_detail where play_type='wuxing' and play_mode='zu120'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断五星组选
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String wuxingZuxuanLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum,String type){	 
		String sql = "";
		sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'wuxing' as 'temp_play_type','"+type+"' as 'temp_play_mode' from ssc_tz_detail where play_type='wuxing' and play_mode='"+type+"'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		   return sql;
	}
	/**
	 * 判断四星
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String sixingLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='sixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='sixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'sixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='sixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 四星组选SQL
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String sixingZuxuanLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum,String type){	 
		String sql = "";

		sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'sixing' as 'temp_play_type','"+type+"' as 'temp_play_mode' from ssc_tz_detail where play_type='sixing' and play_mode='"+type+"'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断三星直选
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String sanxingZhixuanLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断三星zu3
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String sanxingZu3LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zu3' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zu3'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zu3' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zu3'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zu3' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zu3'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断三星zu6
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String sanxingZu6LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zu6' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zu6'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zu6' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zu6'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'sanxing' as 'temp_play_type','zu6' as 'temp_play_mode' from ssc_tz_detail where play_type='sanxing' and play_mode='zu6'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断二星直选
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String erxingLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'erxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='erxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'erxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='erxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'erxing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='erxing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断二星组选
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String erxingZuxuanLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'erxing' as 'temp_play_type','zuxuan' as 'temp_play_mode' from ssc_tz_detail where play_type='erxing' and play_mode='zuxuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'erxing' as 'temp_play_type','zuxuan' as 'temp_play_mode' from ssc_tz_detail where play_type='erxing' and play_mode='zuxuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'erxing' as 'temp_play_type','zuxuan' as 'temp_play_mode' from ssc_tz_detail where play_type='erxing' and play_mode='zuxuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断一星直选
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String yixingLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'yixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='yixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'yixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='yixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'yixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='yixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 判断大小单双
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String dxdsLimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
//		String sql = "select play_type,type_name,play_mode,num_detail,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'yixing' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='yixing' and play_mode='zhixuan'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%'" ;
//		String sql = "select play_type,sum(num_times) as num_times,'"+playNum + "' as temp_play_num,'dxds' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='dxds'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'dxds' as 'temp_play_type','zhixuan' as 'temp_play_mode' from ssc_tz_detail where play_type='dxds'  and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 任选1
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan1LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'renxuan1' as 'temp_play_type','renxuan1' as 'temp_play_mode' from ssc_tz_detail where play_type='renxuan1' and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 任选1
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan2LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'renxuan2' as 'temp_play_type','renxuan2' as 'temp_play_mode' from ssc_tz_detail where play_type='renxuan2' and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	/**
	 * 任选1
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan3LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){	 
		String sql = "select play_type,sum( (length(replace(num_detail,'"+playNum+",',concat('"+playNum+",','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'renxuan3' as 'temp_play_type','renxuan3' as 'temp_play_mode' from ssc_tz_detail where play_type='renxuan3' and issue_num='" + issueNum + "' and num_detail like '%,"+playNum + ",%' group by play_type,temp_play_type,temp_play_mode,temp_play_num" ;
		return sql;
	}
	
}
