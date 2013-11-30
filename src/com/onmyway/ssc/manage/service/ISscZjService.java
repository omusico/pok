package com.onmyway.ssc.manage.service;
/**
 * @Title:中奖信息设置
 * @Description: 
 * @Create on: Sep 19, 2010 11:01:32 AM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISscZjService {
	/**
	 * 设置中奖信息
	 * @param issueNum
	 * @param zjNum
	 * @return
	 */
	public String setZjInfo(String issueNum,String zjNum);
}
