package com.onmyway.ssc.play.service;

import com.onmyway.ssc.play.model.SscTzInfo;

/**
 * @Title:投注限号
 * @Description: 
 * @Create on: Oct 4, 2010 8:54:05 AM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISscLimitService {
	/**
	 * 当前投注的号码是否被限号
	 * @param info
	 * @return
	 */
	public boolean isLimitNum(SscTzInfo info);
}
