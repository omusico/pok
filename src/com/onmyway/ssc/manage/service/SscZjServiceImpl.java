package com.onmyway.ssc.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;
import util.JDateToolkit;
import util.JStringToolkit;

import com.onmyway.common.IdBuilder;
import com.onmyway.init.SscLimitInfoInit;
import com.onmyway.ssc.manage.model.SscZjInfo;

import dbconnpac.ConstantSymbol;
import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:设置中奖号码，并查询中奖信息
 * @Description: 
 * @Create on: Sep 19, 2010 11:17:23 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SscZjServiceImpl implements ISscZjService {

	Logger log = Logger.getLogger(SscZjServiceImpl.class);
	
	DBTool dbTool = DBAccess.getDBTool();
	
	/**
	 * 将组3，按照大小顺序进行排列，并只取两位数
	 * 将组6按照大小顺序排列，并只取两位数
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
			return "开奖号码设置有误!";
		}
		String delSql = "delete from ssc_zj_info where issue_num='" + issueNum + "'";
		boolean delFlag = dbTool.executeSql(ConstantSymbol.dbSource, delSql);
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
			//五星通选中的五星
			String wxZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='wuxing' and play_mode='zhixuan' and t.num_detail like  '%," + wuxing+",%'";
	
			String wxQianHou3Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
						   + " and (t.num_detail like  '%," + wuxingQian3 + "__,%' or t.num_detail like  '%,__" + wuxingHou3 + ",%')";
			String wxQianHou2Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
						  + " and (t.num_detail like  '%," + wuxingQian2 + "___,%' or t.num_detail like  '%,___" + wuxingHou2 + ",%')";
			
			//四星
			String sixingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
						   + "' and t.play_type='sixing' and play_mode='zhixuan' and t.num_detail like  '%," + sixing+",%'";
			//三星
			String sanxingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='sanxing' and play_mode='zhixuan' and t.num_detail like  '%," + sanxing+",%'";
		
			//三星组3,
			String sanxingZu3Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='sanxing' and t.play_mode='zu3' and( t.num_detail like  '%," + sanxingZu31+",%' or t.num_detail like  '%," + sanxingZu32+",%')";
			//三星组6
			String sanxingZu6Sql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='sanxing' and t.play_mode='zu6' and t.num_detail like  '%," + sanxingZu6+",%'";
			
			//二星直选
			String erxingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			   + "' and t.play_type='erxing' and play_mode='zhixuan' and t.num_detail like  '%," + erxing+",%'";
			//二星组选
			String erxingZxSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			+ "' and t.play_type='erxing' and play_mode='zuxuan' and (t.num_detail like  '%," + erxingZx1+",%' or t.num_detail like  '%," + erxingZx2 + ",%')";
			//一星直选
			String yixingZhiSql = " select t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou  from ssc_tz_detail t where t.issue_num='"+issueNum 
			+ "' and t.play_type='yixing' and play_mode='zhixuan' and t.num_detail like  '%," + yixing+",%'";
		
			String tempSql = wxZhiSql + " union " + wxQianHou3Sql + " union " + wxQianHou2Sql + " union " + sixingZhiSql + " union " + sanxingZhiSql;
			if(zu3Flag){
				tempSql = tempSql + " union " + sanxingZu3Sql;
			}
			if(zu6Flag){
				tempSql = tempSql + " union " + sanxingZu6Sql;
			}
			tempSql = tempSql + " union " + erxingZhiSql + " union " + erxingZxSql + " union " + yixingZhiSql;
			
			String sql = "select a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,a.touzhu_num,b.* from ssc_tz_info a,(" + tempSql + ")b where a.id=b.p_id and a.is_valid='1'";
			System.out.println(sql);
			
			List<SscZjInfo> list = new ArrayList<SscZjInfo>();
			
			CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
			try{
				Map<String,String> prizeMap = SscLimitInfoInit.getSscPrizeMap();
				while(crs.next()){
//					t.id as c_id,t.p_id,t.issue_num,t.play_type as c_play_type,t.type_name,t.play_mode as c_play_mode,t.num_times,t.is_zhuitou 
//					a.ID,a.user_id,a.user_name,a.play_type,a.play_mode,
					SscZjInfo zjInfo = new SscZjInfo();
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
					String tempZjMoney = "";//奖金
					String tempMoneyOfZjType = "";//当前玩法中奖的奖金设置
					
					String cPlayType = crs.getString("c_play_type");//子表中的play_type
					String cPlayMode = crs.getString("c_play_mode");//子表中的play_mode
					//根据玩法,得到相应的奖金
					String tempKey = cPlayType+cPlayMode;
					String strPrizeMoney = prizeMap.get(tempKey);//相应玩法对应的中奖奖金设置
					int intZjMoney = 0;
					try{
						intZjMoney = Integer.parseInt(strPrizeMoney)*Integer.parseInt(tempTzTimes);
					}catch(NumberFormatException ex){
						log.error("设置奖金金额 格式化错误: " + ex.toString());
						return "开奖号码设置异常!";
					}
					tempZjMoney = String.valueOf(intZjMoney);
					tempMoneyOfZjType = tempKey;
					
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
					zjInfo.setMoneyOfZjType(tempMoneyOfZjType);
					
					list.add(zjInfo);					
				}
			}catch(Exception e){
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
	 * 设置
	 * @param issueNum 开奖期号
	 * @param zjNum 开奖号码 格式为2,3,4,5,6,必须为5位
	 * @deprecated
	 */
	public String setZjInfoOld(String issueNum, String zjNum) {
		// TODO Auto-generated method stub
		//分解号码
		if(zjNum == null || !zjNum.equals("")){
			return null;
		}
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		int len = aryZjNum.length;
		if(len < 5){
			return "开奖号码设置有误!";
		}
		//五星号码
		String wuxing = aryZjNum[0] + aryZjNum[1] + aryZjNum[2] + aryZjNum[3] + aryZjNum[4];//五星
		String wuxingQian3 =  aryZjNum[0] + aryZjNum[1] + aryZjNum[2];//前三
		String wuxingHou3 =  aryZjNum[2] + aryZjNum[3] + aryZjNum[4];//后三
		String wuxingQian2 = aryZjNum[0] + aryZjNum[1];//前2
		String wuxingHou2 =  aryZjNum[3] + aryZjNum[4];//后2
		
		//四星号码
		String sixing = aryZjNum[1] + aryZjNum[2] + aryZjNum[3] + aryZjNum[4];//四星
		//三星号码
		String sanxing = aryZjNum[2] + aryZjNum[3] + aryZjNum[4];//三星
		String tempSanxing = JStringToolkit.strSort(sanxing.split(""));//将三星从小到大排序，为组选准备
		//判断开奖号码后三位是否是组3(有两个数值相等，但3个不全等)
		boolean zu3Flag = false;
		if((aryZjNum[2].equals(aryZjNum[3]) || aryZjNum[3].equals(aryZjNum[4]) || aryZjNum[2].equals(aryZjNum[4])) 
				&& !(aryZjNum[2].equals(aryZjNum[3]) && aryZjNum[2].equals(aryZjNum[4]))){
			zu3Flag = true;
		}
		String sanxingZu31 = "";
		String sanxingZu32 = "";
		String sanxingZu33 = "";
		if(zu3Flag){
			if(aryZjNum[2].equals(aryZjNum[3])){
				sanxingZu31 = aryZjNum[2]+aryZjNum[2]+aryZjNum[4];
				sanxingZu32 = aryZjNum[2]+aryZjNum[4]+aryZjNum[2];
				sanxingZu33 = aryZjNum[4]+aryZjNum[2]+aryZjNum[2];
			}
			if(aryZjNum[2].equals(aryZjNum[4])){
				sanxingZu31 = aryZjNum[2]+aryZjNum[2]+aryZjNum[3];
				sanxingZu32 = aryZjNum[2]+aryZjNum[3]+aryZjNum[2];
				sanxingZu33 = aryZjNum[3]+aryZjNum[2]+aryZjNum[2];
			}
			if(aryZjNum[3].equals(aryZjNum[4])){
				sanxingZu31 = aryZjNum[2]+aryZjNum[3]+aryZjNum[3];
				sanxingZu32 = aryZjNum[3]+aryZjNum[2]+aryZjNum[3];
				sanxingZu33 = aryZjNum[3]+aryZjNum[3]+aryZjNum[2];
			}
		}
		
		//判断开奖号码后三位是否是组6(3个值都不等)
		boolean zu6Flag = false;
		if( !aryZjNum[2].equals(aryZjNum[3]) &&  !aryZjNum[3].equals(aryZjNum[4]) && !aryZjNum[2].equals(aryZjNum[4])){ 
			zu6Flag = true;
		}
		String sanxingZu61 = "";
		String sanxingZu62 = "";
		String sanxingZu63 = "";
		String sanxingZu64 = "";
		String sanxingZu65 = "";
		String sanxingZu66 = "";
		if(zu6Flag){			
			sanxingZu61 = aryZjNum[2]+aryZjNum[3]+aryZjNum[4];
			sanxingZu62 = aryZjNum[2]+aryZjNum[4]+aryZjNum[3];
			sanxingZu63 = aryZjNum[3]+aryZjNum[2]+aryZjNum[4];
			sanxingZu64 = aryZjNum[3]+aryZjNum[4]+aryZjNum[2];
			sanxingZu65 = aryZjNum[4]+aryZjNum[2]+aryZjNum[3];
			sanxingZu66 = aryZjNum[4]+aryZjNum[3]+aryZjNum[2];			
		}
		//得到后三位的和值
		int intSanxingHezhi = Integer.parseInt(aryZjNum[2]) + Integer.parseInt(aryZjNum[3]) + Integer.parseInt(aryZjNum[4]);
		String sanxingHezhi = String.valueOf(intSanxingHezhi);
		//得到包一胆的值（直接为每个位置上的值）
		String bao1dan1 = aryZjNum[2];
		String bao1dan2 = aryZjNum[3];
		String bao1dan3 = aryZjNum[4];
		//得到包二胆的值
		String bao2dan1 = aryZjNum[2] + aryZjNum[3];
		String bao2dan2 = aryZjNum[2] + aryZjNum[4];
		String bao2dan3 = aryZjNum[3] + aryZjNum[4];
		
		//二星号码
		String erxing = aryZjNum[3] + aryZjNum[4];//二星
		
		//一星号码
		String yixing = aryZjNum[4];//一星
		//五星通选中的五星
		String wxZxSql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='wuxing' and play_mode='zhixuan' and t.num_detail like  '%," + wuxing+",%'";
//		String wxTxSql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum 
//					   + "' and t.play_type='wuxing' and type_name='tx' and t.num_detail like  '%," + wuxing+",%'";
		String wxQianHou3Sql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
					   + " and (t.num_detail like  '%," + wuxingQian3 + "__,% or t.num_detail like  '%,__" + wuxingHou3 + ",%)";
		String wxQianHou2Sql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum  + "' and t.play_type='wuxing' and type_name='tx' "
					  + " and (t.num_detail like  '%," + wuxingQian2 + "___,% or t.num_detail like  '%,___" + wuxingHou2 + ",%)";
		
		//四星
		String sixingZhiSql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum 
					   + "' and t.play_type='sixing' and play_mode='zhixuan' and t.num_detail like  '%," + sixing+",%'";
		//三星
		String sanxingZhiSql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='sanxing' and play_mode='zhixuan' and t.num_detail like  '%," + sanxing+",%'";
	
		//三星组3
		String sanxingZu3Sql = " select * from ssc_tz_detail t where t.issue_num='"+issueNum 
		   + "' and t.play_type='sanxing' and t.play_mode='zu3' and( t.num_detail like  '%," + sanxing+",%' or t.num_detail like  '%," + sanxing+",%";

		return null;
	}
	/**
	 * 将组3，按照大小顺序进行排列，并只取两位数
	 * 将组6按照大小顺序排列，并只取两位数
	 * @deprecated
	 */
	public String setZjInfoOld2(String issueNum, String zjNum) {
		// TODO Auto-generated method stub
		//分解号码
		if(zjNum == null || zjNum.equals("")){
			return null;
		}
		String[] aryZjNum = JStringToolkit.splitString(zjNum,",");
		int len = aryZjNum.length;
		if(len < 5){
			return "开奖号码设置有误!";
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
		//五星通选中的五星
		String wxZhiSql = " select * from ssc_tz_detail a where a.issue_num='"+issueNum 
					   + "' and a.play_type='wuxing' and play_mode='zhixuan' and a.num_detail like  '%," + wuxing+",%'";

		String wxQianHou3Sql = " select * from ssc_tz_detail b where b.issue_num='"+issueNum  + "' and b.play_type='wuxing' and type_name='tx' "
					   + " and (b.num_detail like  '%," + wuxingQian3 + "__,%' or b.num_detail like  '%,__" + wuxingHou3 + ",%')";
		String wxQianHou2Sql = " select * from ssc_tz_detail c where c.issue_num='"+issueNum  + "' and c.play_type='wuxing' and type_name='tx' "
					  + " and (c.num_detail like  '%," + wuxingQian2 + "___,%' or c.num_detail like  '%,___" + wuxingHou2 + ",%')";
		
		//四星
		String sixingZhiSql = " select * from ssc_tz_detail d where d.issue_num='"+issueNum 
					   + "' and d.play_type='sixing' and play_mode='zhixuan' and d.num_detail like  '%," + sixing+",%'";
		//三星
		String sanxingZhiSql = " select * from ssc_tz_detail e where e.issue_num='"+issueNum 
		   + "' and e.play_type='sanxing' and play_mode='zhixuan' and e.num_detail like  '%," + sanxing+",%'";
	
		//三星组3,
		String sanxingZu3Sql = " select * from ssc_tz_detail f where f.issue_num='"+issueNum 
		   + "' and f.play_type='sanxing' and f.play_mode='zu3' and( f.num_detail like  '%," + sanxingZu31+",%' or f.num_detail like  '%," + sanxingZu32+",%')";
		//三星组6
		String sanxingZu6Sql = " select * from ssc_tz_detail g where g.issue_num='"+issueNum 
		   + "' and g.play_type='sanxing' and g.play_mode='zu6' and g.num_detail like  '%," + sanxingZu6+",%'";
		
		//二星直选
		String erxingZhiSql = " select * from ssc_tz_detail h where h.issue_num='"+issueNum 
		   + "' and h.play_type='erxing' and play_mode='zhixuan' and h.num_detail like  '%," + erxing+",%'";
		//二星组选
		String erxingZxSql = " select * from ssc_tz_detail m where m.issue_num='"+issueNum 
		+ "' and m.play_type='erxing' and play_mode='zuxuan' and (m.num_detail like  '%," + erxingZx1+",%' or m.num_detail like  '%," + erxingZx2 + ",%')";
		//一星直选
		String yixingZhiSql = " select * from ssc_tz_detail n where n.issue_num='"+issueNum 
		+ "' and n.play_type='yixing' and play_mode='zhixuan' and n.num_detail like  '%," + yixing+",%'";
	
		String sql = wxZhiSql + " union " + wxQianHou3Sql + " union " + wxQianHou2Sql + " union " + sixingZhiSql + " union " + sanxingZhiSql;
		if(zu3Flag){
			sql = sql + " union " + sanxingZu3Sql;
		}
		if(zu6Flag){
			sql = sql + " union " + sanxingZu6Sql;
		}
		sql = sql + " union " + erxingZhiSql + " union " + erxingZxSql + " union " + yixingZhiSql;
		System.out.println(sql);
		
		CachedRowSet crs = dbTool.querySql(ConstantSymbol.dbSource, sql);
		try{
			
		}catch(Exception e){
			
		}
		return null;
	}
}
