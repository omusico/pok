package com.onmyway.sxw.manage.dao;

import java.util.List;
import java.util.Map;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.sxw.manage.model.SxwMoneyAndLimit;
 

/**
 * @Title:12x5-后台管理-数据操作
 * @Description: 
 * @Create on: Aug 14, 2010 5:36:59 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISxwMoneyLimitDao extends  EntityDAO<SxwMoneyAndLimit,String>{
	/**
	 * 查询所有的限号信息
	 * @return
	 */
	public List<SxwMoneyAndLimit> getAllLimitInfo();
	/**
	 * 将限号信息放到map中
	 * @return
	 */
	public Map<String,String> getLimitInfoMap();

	/**
	 * 更新规则
	 * @param playType
	 * @param rule
	 */
	public boolean updateLimitByRule(String playType,String rule);
}
