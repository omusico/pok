package com.onmyway.sxw.manage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.sxw.manage.model.SxwBaseInfoConfig;
import com.onmyway.sxw.manage.model.SxwMoneyAndLimit;

import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:12x5，相应玩法的奖金及限号
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwMoneyLimitDaoImpl extends GenericEntityDAO<SxwMoneyAndLimit,String> implements ISxwMoneyLimitDao{

	DBTool dbTool = DBAccess.getDBTool();
	
	/**
	 * 查询所有的限号信息
	 * @return
	 */
	public List<SxwMoneyAndLimit> getAllLimitInfo(){
		SxwBaseInfoConfig info = new SxwBaseInfoConfig();		 
		String hsql = "from SxwMoneyAndLimit t where t.isValid='1'";
		List<SxwMoneyAndLimit> list = null;
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
		List<SxwMoneyAndLimit> list =  getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(SxwMoneyAndLimit info : list){
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
		String hsql = "update SxwMoneyAndLimit t set t.isValid='0' where t.rule='" + rule + "'";
//		String sql = "update ssc_money_and_limit t set t.is_valid='0' where t.rule='" + rule + "'";
		try{
			update(hsql);
//			flag = dbTool.updateSql(ConstantSymbol.dbSource, sql);
		}catch(Exception e){
			flag = false;
			logger.error("更新12选5奖金及限号信息错误 :"+e.toString());
		}
		return flag;
	}
	
	
}
