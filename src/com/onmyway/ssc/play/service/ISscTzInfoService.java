package com.onmyway.ssc.play.service;

import java.util.List;

import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.play.model.SxwTzInfo;

/**
 * @Title:时时彩-投注信息管理
 * @Description: 
 * @Create on: Aug 20, 2010 5:46:04 PM
 * @Author:LJY
 * @Version:1.0
 */
public interface ISscTzInfoService {
	/**
	 * 信息保存
	 * @throws AppException
	 */
	public boolean saveTouzhuInfo(SscTzInfo info) throws AppException;
	
	/**
	 * 判断是否可以投注
	 * @param Info
	 * @return
	 */
	public String isCanTouzhu(SscTzInfo info);
	
	/**
	 * 查询某种玩法已经投注的情况
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @return
	 */
	public boolean isInLimit(String playType,String typeName,String playMode,String issueNum,String playNum,String haveZhuitou,String zhuitouInfo);
	/**
	 * 查询某种玩法已经投注的情况
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @return
	 */
	public boolean isInLimit(SscTzInfo info);
	/**
	 * 得到指定用户的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public List<SscTzInfo> getUserTzInfo(String issueNum,String userId) ;
	/**
	 * 得到指定用户的总的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public SscTzInfo getUserTotalTzInfo(String issueNum,String userId) ;
	/**
	 * 删除指定用户的投注信息
	 * @param tzId
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public boolean delUserTzInfo(String tzId,String userId);
		
}
