package com.onmyway.ssc.play.service;

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
import com.onmyway.factory.SscErxingZhixuanAnalyse;
import com.onmyway.factory.SscErxingZuxuanAnalyse;
import com.onmyway.factory.SscSanxingZhixuanAnalyse;
import com.onmyway.factory.SscSanxingZuxuanAnalyse;
import com.onmyway.factory.SscWuxingTongxuanAnalyse;
import com.onmyway.factory.SscYixingAnalyse;
import com.onmyway.init.SscLimitInfoInit;
import com.onmyway.ssc.manage.dao.IUserManageDao;
import com.onmyway.ssc.manage.model.UserInfo;
import com.onmyway.ssc.play.dao.ISscTzDetailDao;
import com.onmyway.ssc.play.dao.ISscTzInfoDao;
import com.onmyway.ssc.play.model.SscTzDetail;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.play.model.SxwTzInfo;

/**
 * @Title:时时彩-投注信息管理
 * @Description: 
 * @Create on: Aug 20, 2010 5:46:21 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscTzInfoServiceImpl implements ISscTzInfoService{

	private Log logger = LogFactory.getLog(SscTzInfoServiceImpl.class);
	//投注DAO
	private ISscTzInfoDao tzInfoDao;
 
	public ISscTzInfoDao getSscTzInfoDao() {
		return tzInfoDao;
	}

	public void setSscTzInfoDao(ISscTzInfoDao tzInfoDao) {
		this.tzInfoDao = tzInfoDao;
	}
	
	//投注详细信息
	private ISscTzDetailDao sscTzDetailDao;

	public ISscTzDetailDao getSscTzDetailDao() {
		return sscTzDetailDao;
	}

	public void setSscTzDetailDao(ISscTzDetailDao sscTzDetailDao) {
		this.sscTzDetailDao = sscTzDetailDao;
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
	public boolean saveTouzhuInfo(SscTzInfo info) throws AppException {
		boolean flag = true;
		String userName = info.getUserName();
		UserInfo userInfo = new UserInfo();
		try{
			userInfo = userManageDao.getUserInfoByName(userName);
		}catch(DAOException de){
			logger.error("时时彩查询用户信息异常：" + de.toString());
			throw new AppException("投注失败 query user error:" + de.toString());
		}
		String userId = userInfo.getId().toString();
		String issueNum = info.getIssueNum();//当前期号
		String touzhuNum = info.getTouzhuNum(); //投注号码
		String haveZhuitou = info.getHaveZhuitou();//是否有追投
		String zhuitouInfo = info.getZhuitouInfo();//追投的期号及倍数
		String playType = info.getPlayType();//玩法类型
		String typeName = info.getTypeName();//玩法小项名称
		String playMode = info.getPlayMode();//玩法模式
		String isZuhe = info.getIsZuhe();//是否为组合
		
		
		
		
		int touzhuMoney = 0;//记录投注的金额
		
		AnalyseBuilder numBuilder = new AnalyseBuilder();//号码分析器
		List<SscTzInfo> list = new ArrayList<SscTzInfo>();
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
			info.setIsZuhe(isZuhe);//是否为组合  0:不是;1:是
			info.setId(strId);//在此生成ID
			info.setUserId(userId);//填入用户ID add 2010-11-03
			
			//add 2011-01-08增加追投标志，及投注序号
			info.setHaveZtFlag("0");//当前没有追投
			info.setParentIssueNum(issueNum);//如果没有追投，则默认他的父ID及期号为自身
			info.setParentId(strId);//如果没有追投，则默认他的父ID及期号为自身
			info.setTzSeq(Integer.parseInt(maxSeq));//最大序号
			
			touzhuMoney = Integer.parseInt(info.getCurTotalTzMoney());
			//设置每注的详细玩法,在组合中，一注对应多种玩法
		//	String[] aryNumDetail = {"1,2,3,4,5","2,3,4,5,6","5,6,7,8,9"};
			//设置每注的详细玩法,在组合中，一注对应多种玩法
			//先分解投注的号码的每一组
			/*
			Map<String,String> map = numBuilder.builderNum(touzhuNum, playType, playMode, "");
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			
			while(it.hasNext()){
				String key = it.next();
				//主要为了包胆及组合时的玩法，进行拆分
				String tempPlayType = "";
				String tempPlayMode = "";
				if(key.indexOf("$") != -1){
					String[] ary = JStringToolkit.splitString(key,"$");
					tempPlayType = ary[0];
					tempPlayMode = ary[1];
				}else{
					tempPlayType = info.getPlayType();
					tempPlayMode = info.getPlayMode();
				}
			*/
			List<PlayNumInfo> resultList = numBuilder.builderNumList(touzhuNum, playType,typeName, playMode, "");
			
			for(PlayNumInfo pni : resultList){	
				String strId2 = IdBuilder.getId();//生成主键ID
				String tempPlayType = pni.getPlayType();
				String tempTypeName = pni.getTypeName();
				String tempPlayMode = pni.getPlayMode();
				String numDetail = pni.getPlayNum();
				
				SscTzDetail detail = new SscTzDetail();
				detail.setId(strId2);//主键
				detail.setPId(strId);//外键
				detail.setIssueNum(issueNum);
				detail.setIsZhuitou("0"); 
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
				SscTzInfo ti = new SscTzInfo();
				ti.setIssueNum(ztIssueNum);//期号
				ti.setTouzhuNum(strTouzhuNum);//投注号码
				ti.setUserName(info.getUserName());
				ti.setUserId(userId);//填入用户ID add 2010-11-03
				ti.setPlayType(strPlayType);
				ti.setTypeName(strTypeName);
				ti.setPlayMode(srtPlayMode);
				
				ti.setId(strId);//主键
				

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
				
				//设置是否是追投，当期的不设置为追投
				String tempIsZhuitouFlag = "1";
				if(!ztIssueNum.equals(info.getIssueNum())){					
					ti.setIsZhuitou("1");
					ti.setParentIssueNum(info.getIssueNum());//设置追投的父期ID为当期的ID
					ti.setParentId(firstId);

					ti.setTzSeq(Integer.parseInt(maxSeq));//最大序号
					ti.setHaveZtFlag("0");//该期是追投的，但是必须没有再追投
				}else{
					tempIsZhuitouFlag = "0";
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
				ti.setIsZuhe(isZuhe);//是否为组合  0:不是;1:是
				
				touzhuMoney = touzhuMoney + tempTzTimes*tempTotalTzCount*2;
				//设置每注的详细玩法,在组合中，一注对应多种玩法
				//先分解投注的号码的每一组
				/*
				Map<String,String> map = numBuilder.builderNum(touzhuNum, playType, playMode, "");
				Set<String> set = map.keySet();
				Iterator<String> it = set.iterator();

				while(it.hasNext()){
					//String tempPlayType = it.next();
					String key = it.next();
					//主要为了包胆及组合时的玩法，进行拆分
					String tempPlayType = "";
					String tempPlayMode = "";
					if(key.indexOf("$") != -1){
						String[] ary = JStringToolkit.splitString(key,"$");
						tempPlayType = ary[0];
						tempPlayMode = ary[1];
					}else{
						tempPlayType = info.getPlayType();
						tempPlayMode = info.getPlayMode();
					}
					
					
					String numDetail = map.get(key);
					*/
				List<PlayNumInfo> resultList = numBuilder.builderNumList(touzhuNum, playType,typeName, playMode, "");
					
				for(PlayNumInfo pni : resultList){	
					String strId2 = IdBuilder.getId();//生成主键ID
					String tempPlayType = pni.getPlayType();
					String tempTypeName = pni.getTypeName();
					String tempPlayMode = pni.getPlayMode();
					String numDetail = pni.getPlayNum();
					
					SscTzDetail detail = new SscTzDetail();
					detail.setId(strId2);//主键
					detail.setPId(strId);//外键
					detail.setIssueNum(ztIssueNum);
					detail.setIsZhuitou(tempIsZhuitouFlag); 
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
	public String isCanTouzhu(SscTzInfo info){
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
	 * 查询某种玩法已经投注的情况
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @return
	 */
	public boolean isInLimit(String playType,String typeName,String playMode,String issueNum,String playNum,String haveZhuitou,String zhuitouInfo){
		logger.info("todo 限号必须再次检查.............");
		boolean flag = true;
		
		if(haveZhuitou.equals("1")){//表示有追投信息
			String[] aryZhuitou = JStringToolkit.splitString(zhuitouInfo, "$");//将追投信息进行分割，得到每期的信息
			for(int i = 0; i < aryZhuitou.length; i++){
				String tempIssueNum = aryZhuitou[i];
				String[] aryIssueInfo = JStringToolkit.splitString(tempIssueNum, "|");//将每期信息进行分割，得到期号及相应的倍数
				String tempIssue = aryIssueInfo[0];//期号
				String tempTimes = aryIssueInfo[1];//倍数
				
				String[] aryNum = JStringToolkit.splitString(playNum, "$");//如果一次投了多个号码，则进行分割
				for(int m = 0; m < aryNum.length; m++){
					String tempNum = aryNum[m];
					flag = isInLimitOfTz(playType,typeName,playMode,tempIssue,tempNum,tempTimes);
					if(!flag){
						break;
					}
				}	
			}
				
		}
		if(haveZhuitou.equals("0")){//表示没有追投信息
			String tempTimes = "1";//没有追投，则表示倍数为1
			String[] aryNum = JStringToolkit.splitString(playNum, "$");//如果一次投了多个号码，则进行分割
			for(int i = 0; i < aryNum.length; i++){
				String tempNum = aryNum[i];
				flag = isInLimitOfTz(playType,typeName,playMode,issueNum,tempNum,tempTimes);
				if(!flag){
					break;
				}
			}			
		}
		
		return flag;
	}
	/**
	 * 只对每一注号码进行判断是否超出限号，如:1,23,4,56,7
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @param times
	 * @return
	 */
	private boolean isInLimitOfTz(String playType,String typeName,String playMode,String issueNum,String playNum,String times){
		boolean flag = true;
		List<PlayNumInfo> tempList = new ArrayList<PlayNumInfo>();
		String tempPlayNum = "";
		//根据相应的玩法，得到相应的投注分析
		if(playType.equals("wuxing")){
			//五星通选
			if(typeName.equals("tx")){
				SscWuxingTongxuanAnalyse wxtx = new SscWuxingTongxuanAnalyse();
				tempPlayNum = wxtx.fusi(playNum);
				tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
			}
		}
		if(playType.equals("sanxing")){
			//三星直选
			if(typeName.equals("zhixuan")){
				SscSanxingZhixuanAnalyse sxzx = new SscSanxingZhixuanAnalyse();
				if(playMode.equals("zuheFushi")){
					tempPlayNum = sxzx.zuheFushi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
			}
			//三星组选
			if(typeName.equals("zuxuan")){
				SscSanxingZuxuanAnalyse sxzu = new SscSanxingZuxuanAnalyse();
				if(playMode.equals("zu3")){
					tempPlayNum = sxzu.zu3(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
				if(playMode.equals("zu6")){
					tempPlayNum = sxzu.zu6(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
				if(playMode.equals("baodan")){
					if(playNum.indexOf(",") != -1){					
						tempList = sxzu.baodan2List(playNum);
					}else{
						//包胆1中包括了三种玩法，三星直选，组3，组6，
						tempList = sxzu.baodan1List(playNum);						
					}
				}
				if(playMode.equals("hezhi")){
					//和值中包括了三种玩法，三星直选，组3，组6，
					tempList = sxzu.hezhiList(playNum);
				}
			}
		}
//		//三星组选
//		if(playType.equals("sanxingZuxuan")){
//			SscSanxingZuxuanAnalyse sxzu = new SscSanxingZuxuanAnalyse();
//			if(playMode.equals("zu3")){
//				tempPlayNum = sxzu.zu3(playNum);
//				tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
//			}
//			if(playMode.equals("zu6")){
//				tempPlayNum = sxzu.zu6(playNum);
//				tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
//			}
//			if(playMode.equals("baodan")){
//				if(playNum.indexOf(",") != -1){					
//					tempList = sxzu.baodan2List(playNum);
//				}else{
//					//包胆1中包括了三种玩法，三星直选，组3，组6，
//					tempList = sxzu.baodan2List(playNum);						
//				}
//			}
//			if(playMode.equals("hezhi")){
//				//和值中包括了三种玩法，三星直选，组3，组6，
//				tempList = sxzu.hezhiList(playNum);
//			}
//		}
		////二星
		if(playType.equals("erxing")){
			//二星直选
			if(typeName.equals("zhixuan")){
				SscErxingZhixuanAnalyse exzx = new SscErxingZhixuanAnalyse();
				if(playMode.equals("fushi")){
					tempPlayNum = exzx.fusi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
				if(playMode.equals("danshi")){
					tempPlayNum = exzx.dansi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
			}
			//二星组选
			if(typeName.equals("zuxuan")){
				SscErxingZuxuanAnalyse exzu = new SscErxingZuxuanAnalyse();
				if(playMode.equals("fushi")){
					tempPlayNum = exzu.fusi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
				if(playMode.equals("danshi")){
					tempPlayNum = exzu.dansi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
				if(playMode.equals("hezhi")){
					tempList = exzu.hezhiList(playNum);
					//tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
			}
		}
			
		////一星
		if(playType.equals("yixing")){
			//一星直选
			if(typeName.equals("zhixuan")){
				SscYixingAnalyse yxzx = new SscYixingAnalyse();
				if(playMode.equals("fushi")){
					tempPlayNum = yxzx.fusi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
			}
		}
	////大小单双
		if(playType.equals("daxds")){
			//一星直选
			if(typeName.equals("zhixuan")){
				SscYixingAnalyse yxzx = new SscYixingAnalyse();
				if(playMode.equals("fushi")){
					tempPlayNum = yxzx.fusi(playNum);
					tempList.add(new PlayNumInfo("ssc",playType,typeName,playMode,tempPlayNum,"","",""));
				}
			}
		}
		//进行限号查询
		if(tempList != null && !tempList.isEmpty()){
			for(PlayNumInfo ni : tempList){
				String tempPlayType = ni.getPlayType();
				String tempPlayMode = ni.getPlayMode();
				String tempTypeName = ni.getTypeName();
				String tempTzNum = ni.getPlayNum();
							
				String[] aryTempNum = JStringToolkit.splitString(tempTzNum, ",");
				for(int i = 0; i < aryTempNum.length; i++){
					String tempNum = aryTempNum[i];
					flag = isInLimitOfOneNum(tempPlayType,tempTypeName,tempPlayMode,issueNum,tempNum,times);
					if(!flag){
						break;
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 只对每一一个号码进行判断是否超出限号,如：1,2,3,4,5
	 * @param playType
	 * @param playMode
	 * @param issueNum 期号
	 * @param playNum 号码
	 * @param times
	 * @return
	 */
	private boolean isInLimitOfOneNum(String playType,String typeName,String playMode,String issueNum,String playNum,String times){
		boolean flag = true;
		try{
			//限制的数量
			//int limitTimes = Integer.parseInt(SscLimitInfoInit.getSscLimitMap().get(getLimitRule(playType)));
			String strLimitTimes = SscLimitInfoInit.getSscLimitMap().get(getLimitRule(playType,typeName,playMode));
			if(strLimitTimes == null){//如果不存在，就默认为没有进行限号设置
				return flag;
			}
			int limitTimes = Integer.parseInt(strLimitTimes);
			//已投的注数
			int haveTzTimes = sscTzDetailDao.getCountOfNum(playType,typeName,playMode, issueNum,playNum);
			//当前投注的数量
			int tzTimes = Integer.parseInt(times);
			if((haveTzTimes + tzTimes > limitTimes)){
				flag = false;
			}
		}catch(DAOException e){
			logger.error("判断限号异常：" + e.toString());			
		}catch(NumberFormatException ee){
			logger.error("判断限号数据格式化异常：" + ee.toString());	
		}
		return flag;
	}
	/**
	 * 查询某种玩法已经投注的情况
	 * @param playType
	 * @param playMode
	 * @param issueNum
	 * @return
	 */
	public boolean isInLimit(SscTzInfo info){
		String playType = info.getPlayType();
		String typeName = info.getTypeName();
		String playMode = info.getPlayMode();
		String issueNum = info.getIssueNum();
		String playNum  = info.getIssueNum();
		String haveZhuitou = info.getHaveZhuitou();
		String zhuitouInfo = info.getZhuitouInfo();
		logger.info("todo 限号必须再次检查.............");
		boolean flag = true;
		
		if(haveZhuitou.equals("1")){//表示有追投信息
			String[] aryZhuitou = JStringToolkit.splitString(zhuitouInfo, "$");//将追投信息进行分割，得到每期的信息
			for(int i = 0; i < aryZhuitou.length; i++){
				String tempIssueNum = aryZhuitou[i];
				String[] aryIssueInfo = JStringToolkit.splitString(tempIssueNum, "|");//将每期信息进行分割，得到期号及相应的倍数
				String tempIssue = aryIssueInfo[0];//期号
				String tempTimes = aryIssueInfo[1];//倍数
				
				String[] aryNum = JStringToolkit.splitString(playNum, "$");//如果一次投了多个号码，则进行分割
				for(int m = 0; m < aryNum.length; m++){
					String tempNum = aryNum[m];
					flag = isInLimitOfTz(playType,typeName,playMode,tempIssue,tempNum,tempTimes);
					if(!flag){
						break;
					}
				}	
			}
				
		}
		if(haveZhuitou.equals("0")){//表示没有追投信息
			String tempTimes = "1";//没有追投，则表示倍数为1
			String[] aryNum = JStringToolkit.splitString(playNum, "$");//如果一次投了多个号码，则进行分割
			for(int i = 0; i < aryNum.length; i++){
				String tempNum = aryNum[i];
				flag = isInLimitOfTz(playType,typeName,playMode,issueNum,tempNum,tempTimes);
				if(!flag){
					break;
				}
			}			
		}
		
		return flag;
	}
	private String getLimitRule(String playType,String typeName,String playMode){
		String str = "";
		String tempType = playType + typeName;
		if(tempType.equals("wuxingtx") || tempType.equals("wuxing") ){
			str = "wuxingZhixuan";
		}
		if(tempType.equals("sixing") ){
			str = "sixingZhixuan";
		}
		if(tempType.equals("sanxing") ){
			if(typeName.equals("zhixuan")){
				//if(playMode.equals("fushi") || playMode.equals("danshi") || playMode.equals("zuheFushi")){
					str = "sanxingZhixuan";
				//}
			}
			if(typeName.equals("zuxuan")){
				//if(playMode.equals("zu3") || playMode.equals("zu6") || playMode.equals("baodan")){
					str = "sanxingZuxuan";
				//}
			}
		}
		return str;
	}
	
	/**
	 * 得到指定用户的投注信息
	 * @param issueNum:期号，可以为空
	 * @param userId:用户ID
	 * @return
	 * @throws DAOException
	 */
	public List<SscTzInfo> getUserTzInfo(String issueNum,String userId) {
		//List<SscTzInfo> list =  tzInfoDao.getUserTzInfo(issueNum, userId);
		List<SscTzInfo> list = new ArrayList<SscTzInfo>();
		List<SscTzInfo> tempList = new ArrayList<SscTzInfo>();
		
		tempList = tzInfoDao.getUserTzInfo(issueNum, userId);
			//将里面的字母转化为中文解释
		if(tempList != null){
			for(SscTzInfo info : tempList){
				String playType = info.getPlayType();//玩法类型
				String playMode = info.getPlayMode();//玩法模式
				String typeName = info.getTypeName();
				String haveZtFlag = info.getHaveZtFlag();//是否有追投
				
				String tempPlayType = ConvertToolkit.sscPlayTypeConvert(playType,typeName);
				String tempPlayMode = ConvertToolkit.sscPlayModeConvert(playMode);
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
	public SscTzInfo getUserTotalTzInfo(String issueNum,String userId) {
		SscTzInfo info = tzInfoDao.getUserTotalTzInfo(issueNum,userId);
		
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
			logger.error("时时彩删除用户投注号码错误：" + e.toString());
		}
		return flag;	
	}
}
