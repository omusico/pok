package com.onmyway.exb.manage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.common.exception.DAOException;
import com.onmyway.exb.manage.model.ExbBaseInfoConfig;
import com.onmyway.exb.manage.model.ExbMoneyAndLimit;

import dbconnpac.DBAccess;
import dbconnpac.DBTool;

/**
 * @Title:快乐十分，相应玩法的奖金及限号
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbMoneyLimitDaoImpl extends GenericEntityDAO<ExbMoneyAndLimit,String> implements IExbMoneyLimitDao{

	DBTool dbTool = DBAccess.getDBTool();
	
	/**
	 * 查询所有的限号信息
	 * @return
	 */
	public List<ExbMoneyAndLimit> getAllLimitInfo(){
		ExbBaseInfoConfig info = new ExbBaseInfoConfig();		 
		String hsql = "from ExbMoneyAndLimit t where t.isValid='1'";
		List<ExbMoneyAndLimit> list = null;
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
		List<ExbMoneyAndLimit> list =  getAllLimitInfo();
		if(list != null && !list.isEmpty()){
			for(ExbMoneyAndLimit info : list){
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
	public void updateLimitByRule(String playType,String rule) throws DAOException{
		boolean flag = true;
		String hsql = "update ExbMoneyAndLimit t set t.isValid='0' where t.rule='" + rule + "'";
//		String sql = "update ssc_money_and_limit t set t.is_valid='0' where t.rule='" + rule + "'";
		update(hsql);
//			flag = dbTool.updateSql(ConstantSymbol.dbSource, sql);
		
	}
	
	
}
