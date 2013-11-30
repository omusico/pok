package com.onmyway.exb.manage.service;
/**
 * @Title:快乐十分中奖信息设置
 * @Description: 
 * @Create on: Sep 19, 2010 11:01:32 AM
 * @Author:LJY
 * @Version:1.0
 * @deprecated
 */
public interface IExbZjService {
	/**
	 * 设置中奖信息
	 * @param issueNum
	 * @param zjNum
	 * @return
	 */
	public String setZjInfo(String issueNum,String zjNum);
}
