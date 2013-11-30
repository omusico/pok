package com.onmyway.sxw.play.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;
import util.JStringToolkit;

import com.onmyway.factory.AnalyseBuilder;
import com.onmyway.factory.PlayNumInfo;
import com.onmyway.init.SxwLimitInfoInit;
import com.onmyway.sxw.play.model.SxwTzInfo;

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
public class SxwLimitServiceImpl implements ISxwLimitService{

	Logger log = Logger.getLogger(SxwLimitServiceImpl.class);
	
	DBTool dbTool = DBAccess.getDBTool();
	/**
	 * 当前投注的号码是否被限号
	 * @param info
	 * @return
	 */
	public boolean isLimitNum(SxwTzInfo info){
		boolean flag = true;
		String playType = info.getPlayType();
		String playMode = info.getPlayMode();
		String issueNum = info.getIssueNum();
		String playNum  = info.getTouzhuNum();
		String haveZhuitou = info.getHaveZhuitou();
		String zhuitouInfo = info.getZhuitouInfo();
		
		//得到当前投注号码的详细信息
		AnalyseBuilder numBuilder = new AnalyseBuilder();//号码分析器
		List<PlayNumInfo> tempList = numBuilder.builderSxwNumList(playNum, playType,playMode);
		
		//如果有追投信息，则需要判断追投的号码是否超出限号
		if(haveZhuitou.equals("1")){//表示有追投信息
			String[] aryZhuitou = JStringToolkit.splitString(zhuitouInfo, "$");//将追投信息进行分割，得到每期的信息
			for(int i = 0; i < aryZhuitou.length; i++){
				String tempIssueNum = aryZhuitou[i];
				String[] aryIssueInfo = JStringToolkit.splitString(tempIssueNum, "|");//将每期信息进行分割，得到期号及相应的倍数
				String tempIssue = aryIssueInfo[0];//期号
				String tempTimes = aryIssueInfo[1];//倍数
				
				//对一期的号码进行
				boolean f = judgeOneNumLimit(playType, playMode,playNum,tempIssue,tempTimes,tempList);
				
				if(!f){
					flag = false;
					break;
				}
			}
		}
		if(haveZhuitou.equals("0")){//表示没有追投信息
			//对一期的号码进行
			boolean f = judgeOneNumLimit(playType,  playMode,playNum,issueNum,"1",tempList);
			
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
	public boolean judgeOneNumLimit(String playType,String playMode,String playNum,String tempIssue,String thisTimes,List<PlayNumInfo> tempList){
		boolean flag = true;
		String sql = "";

		//在12选5中，对每个号码都是一样的，
		int dangqianTimes = 1;//当前投的倍数(默认为1倍)
		for(PlayNumInfo numInfo : tempList){
			String tempPlayType = numInfo.getPlayType();
			String tempTypeName = numInfo.getTypeName();
			String tempPlayMode = numInfo.getPlayMode();
			String tempPlayNum  = numInfo.getPlayNum();
			
			String[] aryPlayNum = JStringToolkit.splitString(tempPlayNum, "|");
			for(int i=0; i<aryPlayNum.length; i++){
				String tempSql = "";
				//任选8
				if(tempPlayType.equals(ConstantSymbol.RX8)){
					tempSql = renxuan8LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				//任选7
				if(tempPlayType.equals(ConstantSymbol.RX7)){
					tempSql = renxuan7LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				//任选6
				if(tempPlayType.equals(ConstantSymbol.RX6)){
					tempSql = renxuan6LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				
				//任选5
				if(tempPlayType.equals(ConstantSymbol.RX5)){
					tempSql = renxuan5LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				//任选4
				if(tempPlayType.equals(ConstantSymbol.RX4)){
					tempSql = renxuan4LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				//任选3
				if(tempPlayType.equals(ConstantSymbol.RX3)){
					if(playMode.equals("zhiqian3")){
						//直前3
						tempSql = renxuan3ZhiQian3LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}else if(playMode.equals("zuqian3")|| playMode.equals("zuqian3dantuo")){
						//组前3
						tempSql = renxuan3ZuQian3LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}else{
						//复式，胆拖
						tempSql = renxuan3LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}
				}
				
				//任选2
				if(tempPlayType.equals(ConstantSymbol.RX2)){
//					if(playMode.equals("zhiqian2") || playMode.equals("zuqian2")|| playMode.equals("zuqian2dantuo")){
					if(playMode.equals("zhiqian2")){
						//直选前2
						tempSql = renxuan2ZhiQian2LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}else if(playMode.equals("zuqian2")|| playMode.equals("zuqian2dantuo")){
						//组选前2
						tempSql = renxuan2ZuQian2LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}else{
						tempSql = renxuan2LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
					}
				}
				
				//任选1
				if(tempPlayType.equals(ConstantSymbol.RX1)){
					tempSql = renxuan1LimitSql(tempPlayType,tempTypeName,tempPlayMode,tempIssue,aryPlayNum[i]);
				}
				 
				//将多个sql进行拼接
				if(sql.equals("")){
					sql = tempSql;
				}else{
					sql = sql + " union " + tempSql;
				}
			}
		}
		log.info("11选5查询限号sql=" + sql);
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{
			Map<String,String> limitMap = SxwLimitInfoInit.getSxwLimitMap();
			
			if(crs != null && crs.size() > 0){
				while(crs.next()){
					String crsPlayType = crs.getString("play_type");
					String crsTempPlayNum = crs.getString("temp_play_num");//当前判断的号码，如何需要返回时哪个号码超出限号，则将此值返回即可
					float crsNumTimes = crs.getFloat("num_times");//已投注的相应号码的注数，现在改为先计算每个投注号码里有注中奖，再乘以倍数，
					String crsTempPlayMode = crs.getString("temp_play_mode");
					
					String strConfigTimes = limitMap.get(crsPlayType);//系统设置的倍数
					int configTimes = 0;//配置的限制注数
					
					int yitouZhushu = 0;//已投的注数
					
					try{
						yitouZhushu = (int)crsNumTimes;//已投的注数
						dangqianTimes = Integer.parseInt(thisTimes);
						configTimes = Integer.parseInt(strConfigTimes);
					}catch(NumberFormatException ex){
						log.error("12x5限号：数字格式化异常:" + ex.toString());
					}
					//判断当前号码中有多少注
					for(PlayNumInfo numInfo : tempList){
						String tempPlayType = numInfo.getPlayType();
						String tempTypeName = numInfo.getTypeName();
						String tempPlayMode = numInfo.getPlayMode();
						String tempTotalPlayNum  = numInfo.getPlayNum();
						
						String[] aryOnePlayNum = JStringToolkit.splitString(tempTotalPlayNum, "|");
						for(int i=0; i<aryOnePlayNum.length; i++){
							int danqianZhushu = getEveryTimesInCurPlayNum(tempPlayType,tempPlayMode,tempTotalPlayNum,aryOnePlayNum[i]);//当前号码在投注的号码中奖的注数
	//						//任选8
	//						if(tempPlayType.equals(ConstantSymbol.RX8)){
	//							danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");	
	//						}
	//						//任选7
	//						if(tempPlayType.equals(ConstantSymbol.RX7)){
	//							danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");	
	//						}
	//						//任选6
	//						if(tempPlayType.equals(ConstantSymbol.RX6)){
	//							danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");	
	//						}
	//						
	//						//任选5
	//						if(tempPlayType.equals(ConstantSymbol.RX5)){
	//							danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");	
	//						}
	//						//任选4
	//						if(tempPlayType.equals(ConstantSymbol.RX4)){
	//							danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");
	//						}
	//						//任选3
	//						if(tempPlayType.equals(ConstantSymbol.RX3)){
	//							if(tempPlayMode.equals("zhiqian3") || tempPlayMode.equals("zuqian3") || tempPlayMode.equals("zuqian3dantuo")){
	//								danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");
	//							}else{
	//								danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");
	//							}
	//						}
	//						
	//						//任选2
	//						if(tempPlayType.equals(ConstantSymbol.RX2)){
	//							if(tempPlayMode.equals("zhiqian2") || tempPlayMode.equals("zuqian2") || tempPlayMode.equals("zuqian2dantuo")){
	//								danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");
	//							}else{
	//								danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");
	//							}
	//						}
	//						
	//						//任选1
	//						if(tempPlayType.equals(ConstantSymbol.RX1)){
	//							danqianZhushu = JStringToolkit.getSubNum(tempPlayNum,"|"+aryPlayNum[i]+"|");
	//						}
							
							log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",当前判断的号码:" + crsTempPlayNum +",已投注数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",当前每注中奖数：:" + danqianZhushu + ",配置倍数="+configTimes);
							if(yitouZhushu + danqianZhushu*dangqianTimes > configTimes){
								log.info("查询限号：已超出限制!");
								flag = false;
								break;
							}	
						}
						if(!flag){
							break;
						}
					}
				//log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",当前判断的号码:" + crsTempPlayNum +",已投倍数=" + yitouTimes + ",当前投注倍数="+dangqianTimes+",配置倍数="+configTimes);
				}
			}else{
				//表示数据库中没有当前号码的记录，则只需判断当前投注记录的倍数是否超出配置的值即可
				String strConfigTimes = limitMap.get(playType);//系统设置的倍数
				int configTimes = 0;//配置的限制注数				
				int yitouZhushu = 0;//已投的注数
				
				try{
					dangqianTimes = Integer.parseInt(thisTimes);
					configTimes = Integer.parseInt(strConfigTimes);
				}catch(NumberFormatException ex){
					log.error("11x5限号：数字格式化异常:" + ex.toString());
				}
				//判断当前号码中有多少注
				for(PlayNumInfo numInfo : tempList){
					String tempPlayType = numInfo.getPlayType();
					String tempTypeName = numInfo.getTypeName();
					String tempPlayMode = numInfo.getPlayMode();
					String tempTotalPlayNum  = numInfo.getPlayNum();
					String[] aryOnePlayNum = JStringToolkit.splitString(playNum, "|");
					for(int i=0; i<aryOnePlayNum.length; i++){
						int danqianZhushu = getEveryTimesInCurPlayNum(tempPlayType,tempPlayMode,tempTotalPlayNum,aryOnePlayNum[i]);//当前号码在投注的号码中奖的注数
						log.info("查询限号：当前投注号码在之前号码中没有限制，直接判读当前投注记录的倍数是否超出配置 playType=" + playType + ",crsTempPlayMode="+playMode + ",当前判断的号码:" + playNum +",已投注数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",当前每奖数：:" + danqianZhushu + ",配置倍数="+configTimes);
						if(yitouZhushu + danqianZhushu*dangqianTimes > configTimes){
							log.info("查询限号：已超出限制!");
							flag = false;
							break;
						}	
					}
					if(!flag){
						break;
					}
				}
					
			}
		}catch(Exception e){
			log.error("查询限号信息异常:" + e.toString());
		}
		return flag;
	}
	/**
	 * 得到一个号码在一组投注号码中出现的次数
	 * @param playType 玩法类型
	 * @param totalPlayNum 当前全部的投注号码
	 * @param onePlayNum 当前投注号码中分其中一个号码
	 * @return 一个号码在总的投注串中出现的次数
	 */
	public int getEveryTimesInCurPlayNum(String playType,String playMode,String totalPlayNum,String onePlayNum){
		int danqianZhushu = 1;
		//任选8
		if(playType.equals(ConstantSymbol.RX8)){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");	
		}
		//任选7
		if(playType.equals(ConstantSymbol.RX7)){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");	
		}
		//任选6
		if(playType.equals(ConstantSymbol.RX6)){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");	
		}
		
		//任选5
		if(playType.equals(ConstantSymbol.RX5)){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");	
		}
		//任选4
		if(playType.equals(ConstantSymbol.RX4)){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");
		}
		//任选3
		if(playType.equals(ConstantSymbol.RX3)){
			if(playMode.equals("zhiqian3") || playMode.equals("zuqian3") || playMode.equals("zuqian3dantuo")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");
			}else{
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");
			}
		}
		
		//任选2
		if(playType.equals(ConstantSymbol.RX2)){
			if(playMode.equals("zhiqian2") || playMode.equals("zuqian2") || playMode.equals("zuqian2dantuo")){
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");
			}else{
				danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");
			}
		}
		
		//任选1
		if(playType.equals(ConstantSymbol.RX1)){
			danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");
		}
		return danqianZhushu;
	}
	/**
	 * 任选8，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan8LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX8 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选7，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan7LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX7 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选6，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan6LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX6 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选5，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan5LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX5 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选4，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan4LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX4 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选3，每个sql只需查询类型及模式，包括（复式和胆拖)
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan3LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX3 + "' and issue_num='" + issueNum + "' and play_mode in ('fushi','dantuo') and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选3，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan3ZhiQian3LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX3 + "'  and issue_num='" + issueNum + "' and play_mode='" + playMode + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选3，组选前3，每个sql只需查询类型及模式,对应的模式包括（组选前3，组选前3胆拖）,同时查询这两种方式的
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan3ZuQian3LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX3 + "'  and issue_num='" + issueNum + "' and play_mode in ('zuqian3','zuqian3dantuo') and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选2，每个sql只需查询类型及模式，包括（复式和胆拖)
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan2LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX2 + "' and issue_num='" + issueNum + "' and play_mode in('fushi','dantuo') and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选2组选前2，每个sql只需查询类型及模式,对应的模式包括（组选前2，组选前2胆拖）,同时查询这两种方式的
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan2ZuQian2LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
		 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX2 + "' and play_mode in ('zuqian2','zuqian2dantuo') and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选2直选前2，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan2ZhiQian2LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
		 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX2 + "' and play_mode='" + playMode + "' and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
	/**
	 * 任选1，每个sql只需查询类型及模式
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param issueNum
	 * @param playNum
	 * @return
	 */
	public String renxuan1LimitSql(String playType,String typeName,String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type,sum((length(replace(num_detail,'"+playNum+"',concat('"+playNum+"','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from sxw_tz_detail where play_type='"+ConstantSymbol.RX1 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}
}
