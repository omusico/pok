package com.onmyway.sxw.play.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.ConvertToolkit;
import util.JDateToolkit;
import util.JStringToolkit;

import com.onmyway.common.IdBuilder;
import com.onmyway.common.exception.AppException;
import com.onmyway.common.exception.DAOException;
import com.onmyway.factory.AnalyseBuilder;
import com.onmyway.factory.PlayNumInfo;
import com.onmyway.ssc.manage.dao.IUserManageDao;
import com.onmyway.ssc.manage.model.UserInfo;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.play.dao.ISxwTzDetailDao;
import com.onmyway.sxw.play.dao.ISxwTzInfoDao;
import com.onmyway.sxw.play.model.SxwTzDetail;
import com.onmyway.sxw.play.model.SxwTzInfo;

/**
 * @Title:12x5-投注信息管理
 * @Description: 
 * @Create on: Aug 20, 2010 5:46:21 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwTzInfoServiceImpl implements ISxwTzInfoService{

	private Log logger = LogFactory.getLog(SxwTzInfoServiceImpl.class);
	//投注DAO
	private ISxwTzInfoDao tzInfoDao;
 
	public ISxwTzInfoDao getSxwTzInfoDao() {
		return tzInfoDao;
	}

	public void setSxwTzInfoDao(ISxwTzInfoDao tzInfoDao) {
		this.tzInfoDao = tzInfoDao;
	}
	
	//投注详细信息
	private ISxwTzDetailDao sxwTzDetailDao;

	public ISxwTzDetailDao getSxwTzDetailDao() {
		return sxwTzDetailDao;
	}

	public void setSxwTzDetailDao(ISxwTzDetailDao sxwTzDetailDao) {
		this.sxwTzDetailDao = sxwTzDetailDao;
	}
	//用户信息
	private IUserManageDao userManageDao;

	public IUserManageDao getUserManageDao() {
		return userManageDao;
	}

	public void setUserManageDao(IUserManageDao userInfoDao) {
		this.userManageDao = userInfoDao;
	}

	/**
	 * 信息保存
	 * @throws AppException
	 */
	public boolean saveTouzhuInfo(SxwTzInfo info) throws AppException {
		boolean flag = true;
		String userName = info.getUserName();
		UserInfo userInfo = new UserInfo();
		try{
			userInfo = userManageDao.getUserInfoByName(userName);
		}catch(DAOException de){
			logger.error("11x5查询用户信息异常：" + de.toString());
		}
		String userId = userInfo.getId().toString();
		String issueNum = info.getIssueNum();//当前期号
		String touzhuNum = info.getTouzhuNum(); //投注号码
		String haveZhuitou = info.getHaveZhuitou();//是否有追投
		String zhuitouInfo = info.getZhuitouInfo();//追投的期号及倍数
		String playType = info.getPlayType();//玩法类型
		String typeName = info.getTypeName();//玩法小项名称
		String playMode = info.getPlayMode();//玩法模式
		
		int touzhuMoney = 0;//记录投注的金额
		
		AnalyseBuilder numBuilder = new AnalyseBuilder();//号码分析器
		List<SxwTzInfo> list = new ArrayList<SxwTzInfo>();
		if(haveZhuitou.equals("0")){

			String strId = IdBuilder.getId();//生成主键ID
			
			//查询当前用户投注的序号 2011-01-08
			String maxSeq = tzInfoDao.getUserTzMaxSeq(issueNum, userId);
			if( maxSeq != null && !maxSeq.equals("")){
				if(maxSeq.equals("0")){
					maxSeq = "1";
				}else{
					maxSeq = String.valueOf((Integer.parseInt(maxSeq)) + 1);
				}
			}else{
				logger.warn("查询当前用户的投注的最大顺序也许有错误!");
			}
			
			//没有追投，只是当前
			info.setIsZhuitou("0");//只有当期，不是追投
			info.setTouzhuTimes(1);//当期，只有一倍
			info.setTouzhuCount(Integer.parseInt(info.getCurTotalTzCount()));//投注数量
			info.setTouzhuMoney(Integer.parseInt(info.getCurTotalTzMoney()));//投注金额
			info.setTouzhuTime(JDateToolkit.getNowDate1());//得到当前操作时间
			info.setIsValid("1");//设置为有效
			info.setId(strId);//在此生成ID
			info.setUserId(userId);//填入用户ID add 2010-11-03
			
			//add 2011-01-08增加追投标志，及投注序号
			info.setHaveZtFlag("0");//当前没有追投
			info.setParentIssueNum(issueNum);//如果没有追投，则默认他的父ID及期号为自身
			info.setParentId(strId);//如果没有追投，则默认他的父ID及期号为自身
			info.setTzSeq(Integer.parseInt(maxSeq));//最大序号
			
			touzhuMoney = Integer.parseInt(info.getCurTotalTzMoney());
			//设置每注的详细玩法,在组合中，一注对应多种玩法
	
			List<PlayNumInfo> resultList = numBuilder.builderSxwNumList(touzhuNum, playType,playMode);
			
			for(PlayNumInfo pni : resultList){	
				String strId2 = IdBuilder.getId();//生成主键ID
				String tempPlayType = pni.getPlayType();
				String tempTypeName = pni.getTypeName();
				String tempPlayMode = pni.getPlayMode();
				String numDetail = pni.getPlayNum();
				
				SxwTzDetail detail = new SxwTzDetail();
				detail.setId(strId2);//主键
				detail.setPId(strId);//外键
				detail.setIssueNum(issueNum);
				detail.setIsZhuitou("1"); 
				detail.setPlayType(tempPlayType);
				detail.setTypeName(tempTypeName);
				detail.setPlayMode(tempPlayMode);
				detail.setNumTimes("1");
				detail.setNumDetail(numDetail);
				
				info.getDetailList().add(detail);
			}
			
			list.add(info);
			try{
				flag = tzInfoDao.jdbcSaveListInfo(list);
			}catch(Exception e){
				flag = false;
				logger.error("保存投注信息异常：" + e.toString());
			}
		}
		////////////////////////////////////////处理有追投的情况///////////////////////////////////////////////////////////////
		if(haveZhuitou.equals("1")){
			//有追投
			String nowTime = JDateToolkit.getNowDate1();
			String[] aryIssue = JStringToolkit.splitString(zhuitouInfo,"$");
			
			String firstId = "";
			for(int i = 0; i < aryIssue.length; i++){
				String strId = IdBuilder.getId();//生成主键ID
				
				
				String tempIssue = aryIssue[i];
				String[] ztIssueInfo = JStringToolkit.splitString(tempIssue,"|");
				String ztIssueNum = ztIssueInfo[0];//追投的期数
				String ztTimes = ztIssueInfo[1];//追投的倍数
				
				String strTouzhuNum = info.getTouzhuNum();
				String strPlayType = info.getPlayType();
				String strTypeName = info.getTypeName();
				String srtPlayMode = info.getPlayMode();
				//String strTouzhuNum = info.getTouzhuNum();
				SxwTzInfo ti = new SxwTzInfo();
				ti.setIssueNum(ztIssueNum);//期号
				ti.setTouzhuNum(strTouzhuNum);//投注号码
				ti.setUserName(info.getUserName());
				ti.setUserId(userId);//填入用户ID add 2010-11-03
				
				ti.setPlayType(strPlayType);
				ti.setTypeName(strTypeName);
				ti.setPlayMode(srtPlayMode);
				
				//查询当前用户投注的序号 2011-01-08
				String maxSeq = tzInfoDao.getUserTzMaxSeq(ztIssueNum, userId);
				if( maxSeq != null && !maxSeq.equals("")){
					if(maxSeq.equals("0")){
						maxSeq = "1";
					}else{
						maxSeq = String.valueOf((Integer.parseInt(maxSeq)) + 1);
					}
				}else{
					logger.warn("查询当前用户的投注的最大顺序也许有错误!");
				}
				
				ti.setId(strId);//主键
				//设置是否是追投，当期的不设置为追投
				if(!ztIssueNum.equals(info.getIssueNum())){
					ti.setIsZhuitou("1");
					ti.setParentIssueNum(info.getIssueNum());//设置追投的父期ID为当期的ID
					ti.setParentId(firstId);
					
					ti.setTzSeq(Integer.parseInt(maxSeq));//最大序号
					ti.setHaveZtFlag("0");//该期没追投
				}else{
					ti.setIsZhuitou("0");
					firstId = strId;
					
					//add 2011-01-08增加追投标志，及投注序号
					ti.setHaveZtFlag("1");//当前有追投
					ti.setParentIssueNum(issueNum);//设置他的父ID及期号为自身
					ti.setParentId(strId);//设置他的父ID及期号为自身
					ti.setTzSeq(Integer.parseInt(maxSeq));//最大序号
				}
				
				int tempTzTimes = Integer.parseInt(ztTimes);
				int tempTotalTzCount = Integer.parseInt(info.getCurTotalTzCount());
				ti.setTouzhuTimes(tempTzTimes);//追投的倍数
				ti.setTouzhuCount(tempTotalTzCount);//投注数量
				ti.setTouzhuMoney(tempTzTimes*tempTotalTzCount*2);//投注金额 注数*倍数
				ti.setTouzhuTime(nowTime);//当前操作时间
				ti.setIsValid("1");//设置为有效
				
				touzhuMoney = touzhuMoney + tempTzTimes*tempTotalTzCount*2;
				//设置每注的详细玩法,在组合中，一注对应多种玩法
				//先分解投注的号码的每一组
				 
				List<PlayNumInfo> resultList = numBuilder.builderSxwNumList(touzhuNum, playType,playMode);
					
				for(PlayNumInfo pni : resultList){	
					String strId2 = IdBuilder.getId();//生成主键ID
					String tempPlayType = pni.getPlayType();
					String tempTypeName = pni.getTypeName();
					String tempPlayMode = pni.getPlayMode();
					String numDetail = pni.getPlayNum();
					
					SxwTzDetail detail = new SxwTzDetail();
					detail.setId(strId2);//主键
					detail.setPId(strId);//外键
					detail.setIssueNum(ztIssueNum);
					detail.setIsZhuitou("1"); 
					detail.setPlayType(tempPlayType);
					detail.setTypeName(tempTypeName);
					detail.setPlayMode(tempPlayMode);
					detail.setNumTimes(ztTimes);//倍数
					detail.setNumDetail(numDetail);
					
					ti.getDetailList().add(detail);
					
				}
								
				list.add(ti);
			}
			
			//保存各个追投的信息
			try{
				//flag = tzInfoDao.jdbcSaveListInfo(list);
				flag = tzInfoDao.jdbcSaveListInfo(list);
				
			}catch(Exception e){
				flag = false;
				logger.error("保存投注信息异常：" + e.toString());
			}
			
		}
		if(flag){
			try{
				flag = userManageDao.updateUserMoney(userName, touzhuMoney);
			}catch(DAOException e){
				flag = false;
				logger.error("更新用户余额错误：" + e.toString());
				//Todo 
				logger.info("todo...此处需要加上回滚,余额扣款失败则需要将投注的信息都置为无效!");
			}
		}
		return flag;
	}
	/**
	 * 判断用户余额是否满足投注条件
	 * @param userMoney
	 * @param taobalTzNum
	 * @return
	 */
	public boolean haveEnoughMoney(String userMoney,String taobalTzNum){
		 int intUserMoney=Integer.parseInt(userMoney);
		 int intTzMoney = Integer.parseInt(taobalTzNum)*2;
		
		 if(intUserMoney > intTzMoney){
			 return true;
		 }else{
			 return false;
		 }
	}
	/**
	 * 投注的长度限制，不能超过设定的值
	 * @param wagTot
	 * @return
	 */
	public boolean touzhuLength(String wagTot){
		boolean isTooLong=false;
		String[] arrWagTot=wagTot.split("");
		if(arrWagTot.length>1000){
			isTooLong=true;
		}
		return isTooLong;
	}
	
	/**
	 * 判断是否可以投注
	 * @param Info
	 * @return
	 */
	public String isCanTouzhu(SxwTzInfo info){
		String strReturn="";
		
		String isSelling = info.getIsSell();
		String userName = info.getUserName();
		String totalTzNum = info.getTotalTzCount();
		String touzhuNum = info.getTouzhuNum();
		UserInfo userInfo = new UserInfo();
		try{
			userInfo = userManageDao.getUserInfoByName(userName);
		}catch(DAOException e){
			logger.error("查询用户信息异常:" + e.toString());
			strReturn = "系统内部异常，异常代码：UserInfoByName";
		}
		
		String isFree = userInfo.getFreeze();//是否被冻结
		String userMoney = userInfo.getTotalmoney();//得到用户账户里的钱
		
		//判断是否可以进行购买
		if(isSelling.equals("0")){
			strReturn="停止销售,不能投注!";
		}else if(!isFree.equals("0")){
			strReturn="您的帐户已经冻结";
		}else if(totalTzNum.equals("0")){
			strReturn="至少选择一注!";
		}else if(haveEnoughMoney(userMoney,totalTzNum)==false){
			strReturn="您的余额不足，请您续费。请减少投注数。如果是多期投注，请减少倍数。";
		}else if(touzhuLength(touzhuNum)==true){
			strReturn="投注过多，请减少投注数!";
		}
		return strReturn;
	}
	
	/**
	 * 得到指定用户的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public List<SxwTzInfo> getUserTzInfo(String issueNum,String userId) {
		List<SxwTzInfo> list =  new ArrayList<SxwTzInfo>();
		List<SxwTzInfo> tempList = new ArrayList<SxwTzInfo>();
		
		tempList = tzInfoDao.getUserTzInfo(issueNum, userId);
			//将里面的字母转化为中文解释
		if(tempList != null){
			for(SxwTzInfo info : tempList){
				String playType = info.getPlayType();//玩法类型
				String playMode = info.getPlayMode();//玩法模式
				String haveZtFlag = info.getHaveZtFlag();//是否有追投
				
				String tempPlayType = ConvertToolkit.sxwPlayTypeConvert(playType);
				String tempPlayMode = ConvertToolkit.sxwPlayModeConvert(playMode);
				String tempHaveZtFlag = haveZtFlag.equals("0") ? "无" : "有";
				String tempZjRule = "";
			
				info.setPlayType(tempPlayType);
				info.setPlayMode(tempPlayMode);
				info.setHaveZtFlag(tempHaveZtFlag);
				
				
				list.add(info);
			}
		}
		return list;
	}
	/**
	 * 得到指定用户的总的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public SxwTzInfo getUserTotalTzInfo(String issueNum,String userId) {
		SxwTzInfo info = tzInfoDao.getUserTotalTzInfo(issueNum,userId);
		
		return info;
	}
	/**
	 * 删除指定用户的投注信息
	 * @param tzId
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public boolean delUserTzInfo(String tzId,String userId){
		boolean flag = false;
		try{
			flag = tzInfoDao.delUserTzInfo(tzId, userId);
		}catch(DAOException e){
			logger.error("12x5删除用户投注号码错误：" + e.toString());
		}
		return flag;	
	}
}
