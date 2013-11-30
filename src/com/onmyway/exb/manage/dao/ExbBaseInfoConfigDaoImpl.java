package com.onmyway.exb.manage.dao;

import java.util.List;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.exb.manage.model.ExbBaseInfoConfig;

/**
 * @Title:快乐十分，每期 保存 
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbBaseInfoConfigDaoImpl extends GenericEntityDAO<ExbBaseInfoConfig,String> implements IExbBaseInfoConfigDao{

	/**
	 * 得到当天的开奖信息
	 * @return
	 */
	public List<ExbBaseInfoConfig> getConfigInfoOfDay(String date){
		ExbBaseInfoConfig info = new ExbBaseInfoConfig();
		
//		String hsql = "from ExbBaseInfoConfig where beginDate=?";
		String hsql = "from ExbBaseInfoConfig where beginDate =? order by operTime desc";
		List<ExbBaseInfoConfig> list = null;
		try{
			list = getList(hsql,date);
		}catch(Exception e){
			logger.error("查询快乐十分配置信息错误 :"+e.toString());
		}
		
	
		return list;
	}
}
