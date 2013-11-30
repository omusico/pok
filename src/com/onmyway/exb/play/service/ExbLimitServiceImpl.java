package com.onmyway.exb.play.service;

import java.util.List;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;
import util.JStringToolkit;

import com.onmyway.exb.play.model.ExbTzInfo;
import com.onmyway.exb.utils.ExbTools;
import com.onmyway.factory.AnalyseBuilder;
import com.onmyway.factory.ExbConstant;
import com.onmyway.factory.PlayNumInfo;

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
public class ExbLimitServiceImpl implements IExbLimitService{

	Logger log = Logger.getLogger(ExbLimitServiceImpl.class);
	
	DBTool dbTool = DBAccess.getDBTool();
	/**
	 * 当前投注的号码是否被限号
	 * @param info
	 * @return
	 */
	public boolean isLimitNum(ExbTzInfo info){
		boolean flag = true;
		String playType = info.getPlayType();
		String playMode = info.getPlayMode();
		String issueNum = info.getIssueNum();
		String playNum  = info.getTouzhuNum();
		String haveZhuitou = info.getHaveZhuitou();
		String zhuitouInfo = info.getZhuitouInfo();
		
		//得到当前投注号码的详细信息
		AnalyseBuilder numBuilder = new AnalyseBuilder();//号码分析器
		List<PlayNumInfo> tempList = numBuilder.builderExbNumList(playType,playMode,playNum);
		
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

		//在快乐十分中，对每个号码都是一样的，
		int dangqianTimes = 1;//当前投的倍数(默认为1倍)
		for(PlayNumInfo numInfo : tempList){
			String tempPlayType = numInfo.getPlayType();
			String tempPlayMode = numInfo.getPlayMode();
			String tempPlayNum  = numInfo.getPlayNum();
			
			String[] aryPlayNum = JStringToolkit.splitString(tempPlayNum, "|");
			for(int i=0; i<aryPlayNum.length; i++){
				String tempSql = buildSql(tempPlayType,tempPlayMode,tempIssue,aryPlayNum[i]);
	  
				//将多个sql进行拼接
				if(sql.equals("")){
					sql = tempSql;
				}else{
					sql = sql + " union " + tempSql;
				}
			}
		}
		log.info("20x8 limit info query sql=" + sql);
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
	
		try{
			
			if(crs != null && crs.size() > 0){
				while(crs.next()){
					String crsPlayType = crs.getString("play_type");
					String crsTempPlayNum = crs.getString("temp_play_num");//当前判断的号码，如何需要返回时哪个号码超出限号，则将此值返回即可
					float crsNumTimes = crs.getFloat("num_times");//已投注的相应号码的注数，现在改为先计算每个投注号码里有注中奖，再乘以倍数，
					String crsTempPlayMode = crs.getString("temp_play_mode");
					
					int configTimes = ExbTools.getExbConfigTime(crsPlayType,crsTempPlayMode);//系统设置的倍数,playType 和 playMode 和起来，可以得到数据库存的类型
					
					int yitouZhushu = 0;//已投的注数
					
					try{
						yitouZhushu = (int)crsNumTimes;//已投的注数
						dangqianTimes = Integer.parseInt(thisTimes);
					}catch(NumberFormatException ex){
						log.error("20x8：number format exception:" + ex.toString());
					}
					//判断当前号码中有多少注
					for(PlayNumInfo numInfo : tempList){
						String tempPlayType = numInfo.getPlayType();
						String tempTypeName = numInfo.getTypeName();
						String tempPlayMode = numInfo.getPlayMode();
						String tempTotalPlayNum  = numInfo.getPlayNum();
						
						String[] aryOnePlayNum = JStringToolkit.splitString(tempTotalPlayNum, "|");
						for(int i=0; i<aryOnePlayNum.length; i++){
							//改组号码中每一个号码在改组中出现的注数
							int danqianZhushu = getEveryTimesInCurPlayNum(tempPlayType,tempPlayMode,tempTotalPlayNum,aryOnePlayNum[i]);//当前号码在投注的号码中奖的注数
							
							log.info("exb query limit info：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",now query number is :" + crsTempPlayNum +",Have touzhu number=" + yitouZhushu + ",cur touzhu times="+dangqianTimes+",prize number of cur touzu number ：:" + danqianZhushu + ",the server config times="+configTimes);
							log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",当前判断的号码:" + crsTempPlayNum +",已投注数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",当前每注中奖数：:" + danqianZhushu + ",配置倍数="+configTimes);
							
							if(yitouZhushu + danqianZhushu*dangqianTimes > configTimes){
								log.info("exb query limit info ：out of the limit config超出限制倍数,禁止投注!");
								flag = false;
								break;
							}	
						}
						if(!flag){
							break;
						}
					}
//					log.info("查询限号：playType=" + crsPlayType + ",crsTempPlayMode="+crsTempPlayMode + ",当前判断的号码:" + crsTempPlayNum +",已投倍数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",配置倍数="+configTimes);
				}
			}else{
				//表示数据库中没有当前号码的记录，则只需判断当前投注记录的倍数是否超出配置的值即可
				int configTimes = 0;//配置的限制注数				
				int yitouZhushu = 0;//已投的注数
				
				configTimes = ExbTools.getExbConfigTime(playType,playMode);//系统设置的倍数,playType 和 playMode 和起来，可以得到数据库存的类型
				
				try{
					dangqianTimes = Integer.parseInt(thisTimes);
				}catch(NumberFormatException ex){
					log.error("exb query limit：number format excption :" + ex.toString());
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
						log.info("exb query limit：当前投注号码在之前号码中没有限制，直接判读当前投注记录的倍数是否超出配置 playType=" + playType + ",crsTempPlayMode="+playMode + ",当前判断的号码:" + playNum +",已投注数=" + yitouZhushu + ",当前投注倍数="+dangqianTimes+",当前每奖数：:" + danqianZhushu + ",配置倍数="+configTimes);
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
			log.error("exb 查询限号信息异常:" + e.toString());
		}
		return flag;
	}
	/**
	 * 根据类型生成SQL
	 * @return
	 */
	private String buildSql(String playType,String playMode,String issueNum,String tzNum){
		String sql = "";
		//任选1
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){//选一数投
			sql = buildXuan1LimitSql(playMode,issueNum,tzNum);
		}
		//任选2
		if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			sql = buildXuan2LimitSql(playMode,issueNum,tzNum);
		}
		//任选3
		if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			sql = buildXuan3LimitSql(playMode,issueNum,tzNum);
		}

		//任选4
		if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			sql = buildXuan4LimitSql(playMode,issueNum,tzNum);
		}
		//任选5
		if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			sql = buildXuan5LimitSql(playMode,issueNum,tzNum);
		}
		return sql;
	}
	/**
	 * 得到一个号码在一组投注号码中出现的次数|1,2|3,5|1,2|中"1,2"出现的次数为2
	 * @param playType 玩法类型
	 * @param totalPlayNum 当前全部的投注号码
	 * @param onePlayNum 当前投注号码中分其中一个号码
	 * @return 一个号码在总的投注串中出现的次数
	 */
	public int getEveryTimesInCurPlayNum(String playType,String playMode,String totalPlayNum,String onePlayNum){
		int danqianZhushu = 1;
		danqianZhushu = JStringToolkit.getSubNum(totalPlayNum,"|"+onePlayNum+"|");	
		/* 2012-01-04 del
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
		*/
		return danqianZhushu;
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
	public String buildXuan5LimitSql(String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X5 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
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
	public String buildXuan4LimitSql(String playMode,String issueNum,String playNum){
	 
		String sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X4 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
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
	public String buildXuan3LimitSql(String playMode,String issueNum,String playNum){
	 
		String sql = "";
		
		if(playMode.equals(ExbConstant.X3_MODE_RENXUAN)|| playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){//任选、任选胆拖
			sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X3 + "' and issue_num='" + issueNum + "' and play_mode in ('"+ExbConstant.X3_MODE_RENXUAN+"','"+ExbConstant.X3_MODE_RENXUAN_DANTUO+"') and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){//前直
			sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X3 + "'  and issue_num='" + issueNum + "' and play_mode='" + playMode + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU)|| playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){//前组、前组胆拖
			sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X3 + "'  and issue_num='" + issueNum + "' and play_mode in  ('"+ExbConstant.X3_MODE_QIANZU+"','"+ExbConstant.X3_MODE_QIANZU_DANTUO+"')  and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;	
		}
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
	public String buildXuan2LimitSql(String playMode,String issueNum,String playNum){
		 
		String sql = "";
		//任选2
		if(playMode.equals(ExbConstant.X2_MODE_RENXUAN)|| playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){//任选、任选胆拖
			sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X2 + "' and issue_num='" + issueNum + "' and play_mode in('"+ExbConstant.X2_MODE_RENXUAN+"','"+ExbConstant.X2_MODE_RENXUAN_DANTUO+"') and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){//连直
			sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X2 + "' and play_mode='" + playMode + "' and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU)|| playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){//连组、连组胆拖
			sql = "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X2 + "' and play_mode in ('"+ExbConstant.X2_MODE_LIANZU+"','"+ExbConstant.X2_MODE_LIANZU_DANTUO+"') and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
			
		}
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
	public String buildXuan1LimitSql(String playMode,String issueNum,String playNum){
	 
		String sql =  "select play_type, sum((length(replace(num_detail,'|"+playNum+"|',concat('|"+playNum+"|','#')))-length(num_detail))*num_times) as num_times,'"+playNum + "' as temp_play_num,'" + playMode + "' as temp_play_mode from exb_tz_detail where play_type='"+ExbConstant.PLAY_TYPE_X1 + "'  and issue_num='" + issueNum + "' and num_detail like '%|"+playNum + "|%' group by play_type,temp_play_mode,temp_play_num" ;
		
		return sql;
	}

}
