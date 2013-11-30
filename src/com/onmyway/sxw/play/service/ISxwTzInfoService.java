package com.onmyway.sxw.play.service;

import java.util.List;

import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.sxw.play.model.SxwTzInfo;

/**
 * @Title:12x5-投注信息管理
 * @Description: 
 * @Create on: Aug 20, 2010 5:46:04 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISxwTzInfoService {
	/**
	 * 信息保存
	 * @throws AppException
	 */
	public boolean saveTouzhuInfo(SxwTzInfo info) throws AppException;
	
	/**
	 * 判断是否可以投注
	 * @param Info
	 * @return
	 */
	public String isCanTouzhu(SxwTzInfo info);
	
	/**
	 * 得到指定用户的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public List<SxwTzInfo> getUserTzInfo(String issueNum,String userId) ;
	/**
	 * 得到指定用户的总的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public SxwTzInfo getUserTotalTzInfo(String issueNum,String userId) ;
	/**
	 * 删除指定用户的投注信息
	 * @param tzId
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public boolean delUserTzInfo(String tzId,String userId);
		
}
