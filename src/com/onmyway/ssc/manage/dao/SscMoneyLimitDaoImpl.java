package com.onmyway.ssc.manage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
import com.onmyway.ssc.manage.model.SscMoneyAndLimit;

import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:时时彩，相应玩法的奖金及限号
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscMoneyLimitDaoImpl extends GenericEntityDAO<SscMoneyAndLimit,String> implements ISscMoneyLimitDao{

	DBTool dbTool = DBAccess.getDBTool();
	
	/**
	 * 查询所有的限号信息
	 * @return
	 */
	public List<SscMoneyAndLimit> getAllLimitInfo(){
		SscBaseInfoConfig info = new SscBaseInfoConfig();		 
		String hsql = "from SscMoneyAndLimit t where t.isValid='1'";
		List<SscMoneyAndLimit> list = null;
		try{
			list = getList(hsql);
		}catch(Exception e){
			logger.error("查询时时限号信息错误:"+e.toString());
		}
		return list;
	}
	/**
	 * 将限号信息放到map中
	 * @return
	 */
	public Map<String,String> getLimitInfoMap(){
		Map<String,String> map = new HashMap<String,String>();
		List<SscMoneyAndLimit> list =  getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(SscMoneyAndLimit info : list){
				map.put(info.getRule(), info.getLimitOneIssue());
			}
		}
		return map;
	}
	/**
	 * 更新规则，
	 * @param playType
	 * @param rule
	 */
	public boolean updateLimitByRule(String playType,String rule){
		boolean flag = true;
		String hsql = "update SscMoneyAndLimit t set t.isValid='0' where t.rule='" + rule + "'";
//		String sql = "update ssc_money_and_limit t set t.is_valid='0' where t.rule='" + rule + "'";
		try{
		update(hsql);
//			flag = dbTool.updateSql(ConstantSymbol.dbSource, sql);
		}catch(Exception e){
			flag = false;
			logger.error("更新时时彩奖金及限号信息错误:"+e.toString());
		}
		return flag;
	}
	
	
}
