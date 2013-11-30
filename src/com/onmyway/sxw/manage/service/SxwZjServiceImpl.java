package com.onmyway.sxw.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;
import util.JDateToolkit;
import util.JStringToolkit;
import util.ZuheArith;

import com.onmyway.common.IdBuilder;
import com.onmyway.common.exception.DAOException;
import com.onmyway.init.SxwLimitInfoInit;
import com.onmyway.sxw.manage.model.SxwZjInfo;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:12x5设置中奖号码，并查询中奖信息
 * @Description: 
 * @Create on: Sep 19, 2010 11:17:23 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwZjServiceImpl implements ISxwZjService {

	Logger log = Logger.getLogger(SxwZjServiceImpl.class);
	
	DBTool dbTool = DBAccess.getDBTool();
	
	/**

	 * 先将表里已存在的相应期号的中奖号码信息删除，然后再重新进行中奖号码的设置
	 */
	public String setZjInfo(String issueNum, String zjNum) {
		// TODO Auto-generated method stub
		//分解号码
		if(zjNum == null || zjNum.equals("")){
			return null;
		}
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		int len = aryZjNum.length;
		if(len < 5){
			return "12选5 开奖号码设置有误!";
		}
		String delSql = "delete from sxw_zj_info where issue_num='" + issueNum + "'";
		//boolean delFlag = dbTool.executeSql(ConstantSymbol.dbSource, delSql);
		boolean delFlag = true;
		if(delFlag){
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
			/*
			 * 任选一（1中1）：投注的1个号码与当期顺序摇出的5个号码中的第1个号码相同，则中奖。
任选二（2中2）：投注的2个号码与当期顺序摇出的5个号码中的前2个号码相同，则中奖；
任选二（5中2）：投注的2个号码与当期摇出的5个号码中的任2个号码相同，则中奖。
任选三（3中3）：投注的3个号码与当期顺序摇出的5个号码中的前3个号码相同，则中奖；
任选三（5中3）：投注的3个号码与当期摇出的5个号码中的任3个号码相同，则中奖。
任选四（4中4）：投注的4个号码与当期顺序摇出的5个号码中的前4个号码相同，则中奖；
任选四（5中4）：投注的4个号码与当期摇出的5个号码中的任4个号码相同，则中奖。
任选五（5中5）：投注的5个号码与当期摇出的5个号码相同，则中奖。
*/
			int[] intAry = {intWan,intQian,intBai,intShi,intGe};
			ZuheArith za = new ZuheArith();
			//alter on 2010-11-03中奖号码是无序排列，而投注号码都是按照从小到大进行排列，所以直中的方式中，将相应的位数按照从小到大排列
			//中5
//			String wuzhong5 = strWan + "," + strQian +  "," + strBai +  "," + strShi +  "," + strGe;//任选5
			String wuzhong5 = JStringToolkit.strSort(new String[]{strWan ,strQian, strBai, strShi,strGe},",");//任选5
			
			//中4
//			String sizhong4 = strWan + "," + strQian +  "," + strBai +  "," + strShi;//任选4中4
			String sizhong4 = JStringToolkit.strSort(new String[]{strWan ,strQian, strBai, strShi},",");
			List list4 = za.combine(intAry, 4);//对5中任4个进行组合，以便对5中4进行中奖判断
			//中3
//			String sanzhong3 = strWan + "," + strQian +  "," + strBai;//任选3中3
			String sanzhong3 = JStringToolkit.strSort(new String[]{strWan ,strQian, strBai},",");//任选3中3
			List list3 = za.combine(intAry, 3);//对5中任3个进行组合，以便对5中3进行中奖判断
			//中2
//			String erzhong2 = strWan + "," + strQian;//任选2中2
			String erzhong2 = JStringToolkit.strSort(new String[]{strWan ,strQian},",");//任选2中2
			List list2 = za.combine(intAry, 2);//对5中任2个进行组合，以便对5中2进行中奖判断
			//中1
			String yizhong1 = strWan;//任选1中1

			 
			
			//任选5中5
			String sqlWuzhong5 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan5'  and t.num_detail like  '%|" + wuzhong5+"|%'";
	
			//任选4中4
			String sqlSizhong4 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan4'  and t.num_detail like  '%|" + sizhong4+"|%'";
			//5中4
			String sqlWuzhong4 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan4'  ";
			// 5中4 组合条件
			String tempSql4 = "";
			for (int i = 0; i < list4.size(); i++) {
				int[] tempIntAry = (int[]) list4.get(i);
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
				sqlWuzhong4 = sqlWuzhong4 + " and (" + tempSql4 + ")";
			}
			 
			//任选3中3
			String sqlSanzhong3 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan3'  and t.num_detail like  '%|" + sanzhong3+"|%'";
			//5中3
			String sqlWuzhong3 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan3'  ";
			// 5中3 组合条件
			String tempSql3 = "";
			for (int i = 0; i < list3.size(); i++) {
				int[] tempIntAry = (int[]) list3.get(i);
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
				sqlWuzhong3 = sqlWuzhong3 + " and (" + tempSql3 + ")";
			}

			//任选2中2
			String sqlSanzhong2 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan2'  and t.num_detail like  '%|" + erzhong2+"|%'";
			//5中2
			String sqlWuzhong2 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan2'  ";
			// 5中2 组合条件
			String tempSql2 = "";
			for (int i = 0; i < list2.size(); i++) {
				int[] tempIntAry = (int[]) list2.get(i);
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
				sqlWuzhong2 = sqlWuzhong3 + " and (" + tempSql2 + ")";
			}
			//任选1中1
			String sqlSanzhong1 = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from sxw_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='renxuan1'  and t.num_detail like  '%|" + yizhong1+"|%'";
		
			String tempSql = "";
			tempSql = sqlWuzhong5 + " union " + sqlSizhong4 + " union " + sqlWuzhong4 + " union " + sqlSanzhong3 + " union " + sqlWuzhong3 + " union " + sqlSanzhong2 + " union " + sqlWuzhong2 + " union " + sqlSanzhong1;
			System.out.println(tempSql);
			
			String sql = "select a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,a.touzhu_num,b.* from sxw_tz_info a,(" + tempSql + ")b where a.id=b.p_id and a.is_valid='1'";
			System.out.println(sql);
			
			List<SxwZjInfo> list = new ArrayList<SxwZjInfo>();
			
			CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
			try{
				Map<String,String> prizeMap = SxwLimitInfoInit.getSxwPrizeMap();
				while(crs.next()){
//					t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou 
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
				
					//判断中奖注数，根据玩法及相应的投注号码，查询中奖的注数。判断中奖号码在相应的投注号码出现的次数即为中奖的注数
					int intZjZhushu = 0;//中奖的注数(如投：12345,12345，则注数是2
					int intTzTimes = Integer.parseInt(tempTzTimes);//得到投注的倍数
					int intTotalZjZhushu = 0;//总的中奖注数=单注中奖注数*倍数
					int intTotalMoney = 0;//总的中奖金额
					String zjRule = "";
					if(tempPlayType.equals(ConstantSymbol.RX5)){//5选5中5
						int wuzhong5Times = JStringToolkit.getSubNum(tempNumDetail,","+wuzhong5+",");
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
					if(tempPlayType.equals(ConstantSymbol.RX4)){//选4
						//选4中4 的中奖注数及金额
						int sizhong4Times = JStringToolkit.getSubNum(tempNumDetail,","+sizhong4+",");
						int sizhong4Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX4))*sizhong4Times;
						
						//5中4的中奖注数及金额
						int wuzhong4Times = howTimesInNum(list4,tempNumDetail,sizhong4);
						int wuzhong4Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX54))*wuzhong4Times;
						
						intZjZhushu = sizhong4Times + wuzhong4Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
						
						int intEachMoney = sizhong4Money + wuzhong4Money ;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;//总奖金
						
						//中奖规则，备用
						if(sizhong4Times > 0){
							zjRule = zjRule +  ConstantSymbol.RX4 + ":" + sizhong4Times + ";";//格式为"规则:中奖注数;"
						}
						if(wuzhong4Times > 0){
							zjRule = zjRule +  ConstantSymbol.RX54 + ":" + wuzhong4Times + ";";//格式为"规则:中奖注数"
						}
					}
					if(tempPlayType.equals(ConstantSymbol.RX3)){//选3
						//选3中3 的中奖注数及金额
						int sanzhong3Times = JStringToolkit.getSubNum(tempNumDetail,","+sanzhong3+",");
						int sanzhong3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX3))*sanzhong3Times;
						
						//5中3的中奖注数及金额
						int wuzhong3Times = howTimesInNum(list3,tempNumDetail,sanzhong3);
						int wuzhong3Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX53))*wuzhong3Times;
						
						intZjZhushu = sanzhong3Times + wuzhong3Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
						
						int intEachMoney = sanzhong3Money + wuzhong3Money ;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;//总奖金
						
						//中奖规则，备用
						if(sanzhong3Times > 0){
							zjRule = zjRule + ConstantSymbol.RX3 + ":" + sanzhong3Times + ";";//格式为"规则:中奖注数;"
						}
						if(wuzhong3Times > 0){
							zjRule = zjRule + ConstantSymbol.RX53 + ":" + wuzhong3Times + ";";//格式为"规则:中奖注数"
						}
					}

					if(tempPlayType.equals(ConstantSymbol.RX2)){//选2
						//选2中2 的中奖注数及金额
						int erzhong2Times = JStringToolkit.getSubNum(tempNumDetail,","+erzhong2+",");
						int erzhong2Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX2))*erzhong2Times;
						
						//5中2的中奖注数及金额
						int wuzhong2Times = howTimesInNum(list3,tempNumDetail,erzhong2);
						int wuzhong2Money = Integer.parseInt(prizeMap.get(ConstantSymbol.RX52))*wuzhong2Times;
						
						intZjZhushu = erzhong2Times + wuzhong2Times;//每个单注中奖的注数						
						intTotalZjZhushu = intZjZhushu * intTzTimes;//总注数
						
						int intEachMoney = erzhong2Money + wuzhong2Money ;//每个单注的总奖金
						intTotalMoney = intEachMoney * intTzTimes;//总奖金
						
						//中奖规则，备用
						if(erzhong2Times > 0){
							zjRule = zjRule + ConstantSymbol.RX2 + ":" + erzhong2Times + ";";//格式为"规则:中奖注数;"
						}
						if(wuzhong2Times > 0){
							zjRule = zjRule + ConstantSymbol.RX52 + ":" + wuzhong2Times + ";";//格式为"规则:中奖注数"
						}
					}
					if(tempPlayType.equals(ConstantSymbol.RX1)){//1选1中1
						int yizhong1Times = JStringToolkit.getSubNum(tempNumDetail,","+yizhong1+",");
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
					String tempKey = tempPlayType;
					String strPrizeMoney = prizeMap.get(tempKey);//相应玩法对应的中奖奖金设置
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
					
					list.add(zjInfo);					
				}
				
				//boolean flag = dao.jdbcSaveListInfo(list);//如果不抛出异常，就是保存成功
			} catch(Exception e){
				log.error("中奖信息保存异常:" + e.toString());
			}
		}else{
			String temp = "中奖信息删除失败!";
			log.info(temp);
			return temp;
		}
		return "中奖号码设置成功!";
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
}
