package com.onmyway.exb.play.service;

import java.util.List;

import beanpac.SingleIssueInfo;

import com.onmyway.exb.manage.model.ExbBaseInfoConfig;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 16, 2010 9:05:23 AM
 * @Author:LJY
 * @Version:1.0
 */
public interface IExbIssuePublicService {
	/**
	 * 得到当天的开奖信息
	 * @return
	 */
	public ExbBaseInfoConfig getTodayConfig();
	
	/**
	 * 得到当天其他的发行期号
	 * @return
	 */
	public List<SingleIssueInfo> getTodayOtherIssue();
	/**
	 * 得到当前正在发行的期数
	 * @return
	 */
	public String getCurrentIssue();
}
