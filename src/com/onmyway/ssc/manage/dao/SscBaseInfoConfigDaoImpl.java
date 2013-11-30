package com.onmyway.ssc.manage.dao;

import java.util.List;

import util.JDateToolkit;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;

/**
 * @Title:时时彩，每期保存
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscBaseInfoConfigDaoImpl extends GenericEntityDAO<SscBaseInfoConfig,String> implements ISscBaseInfoConfigDao{

	/**
	 * 得到当天的开奖信息
	 * @return
	 */
	public List<SscBaseInfoConfig> getConfigInfoOfDay(String date){
		SscBaseInfoConfig info = new SscBaseInfoConfig();
		
//		String hsql = "from SscBaseInfoConfig where beginDate=?";
		String hsql = "from SscBaseInfoConfig where beginDate =? order by operTime desc";
		List<SscBaseInfoConfig> list = null;
		try{
			list = getList(hsql,date);
		}catch(Exception e){
			logger.error("查询时时彩配置信息错误:"+e.toString());
		}
		
	
		return list;
	}
}
