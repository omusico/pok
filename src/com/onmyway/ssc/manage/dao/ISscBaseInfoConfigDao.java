package com.onmyway.ssc.manage.dao;

import java.util.List;

import com.onmyway.common.dao.EntityDAO;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
 

/**
 * @Title:时时彩-后台管理-数据操作
 * @Description: 
 * @Create on: Aug 14, 2010 5:36:59 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISscBaseInfoConfigDao extends  EntityDAO<SscBaseInfoConfig,String>{
	/**
	 * 得到当天的开奖信息
	 * @date 需要查询的日期
	 * @return
	 */
	public List<SscBaseInfoConfig> getConfigInfoOfDay(String date);

}
