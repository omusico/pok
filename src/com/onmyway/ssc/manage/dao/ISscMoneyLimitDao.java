package com.onmyway.ssc.manage.dao;

import java.util.List;
import java.util.Map;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
import com.onmyway.ssc.manage.model.SscMoneyAndLimit;
 

/**
 * @Title:时时彩-后台管理-数据操作
 * @Description: 
 * @Create on: Aug 14, 2010 5:36:59 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISscMoneyLimitDao extends  EntityDAO<SscMoneyAndLimit,String>{
	/**
	 * 查询所有的限号信息
	 * @return
	 */
	public List<SscMoneyAndLimit> getAllLimitInfo();
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
