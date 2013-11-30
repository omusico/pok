package com.onmyway.sxw.manage.dao;

import java.util.List;

import com.onmyway.common.dao.GenericEntityDAO;
import com.onmyway.sxw.manage.model.SxwBaseInfoConfig;

/**
 * @Title:12选5，每期 保存 
 * @Description: 
 * @Create on: Aug 14, 2010 5:37:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwBaseInfoConfigDaoImpl extends GenericEntityDAO<SxwBaseInfoConfig,String> implements ISxwBaseInfoConfigDao{

	/**
	 * 得到当天的开奖信息
	 * @return
	 */
	public List<SxwBaseInfoConfig> getConfigInfoOfDay(String date){
		SxwBaseInfoConfig info = new SxwBaseInfoConfig();
		
//		String hsql = "from SxwBaseInfoConfig where beginDate=?";
		String hsql = "from SxwBaseInfoConfig where beginDate =? order by operTime desc";
		List<SxwBaseInfoConfig> list = null;
		try{
			list = getList(hsql,date);
		}catch(Exception e){
			logger.error("查询12选5配置信息错误 :"+e.toString());
		}
		
	
		return list;
	}
}
