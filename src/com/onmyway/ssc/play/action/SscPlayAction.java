package com.onmyway.ssc.play.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beanpac.RemindInfo;

import com.onmyway.common.BaseAction;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.common.ssc.SscCommonTool;
import com.onmyway.ssc.manage.model.UserInfo;
import com.onmyway.ssc.manage.service.IUserManageService;
import com.onmyway.ssc.manage.service.UserManageServiceImpl;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.ssc.play.service.ISscLimitService;
import com.onmyway.ssc.play.service.ISscTzInfoService;

/**
 * @Title:
 * @Description:
 * @Create on: Aug 16, 2010 3:29:03 PM
 * @Author:LJY  FB32BCFC4B9003F37F763F04719798BB
 * @Version:1.0
 */
public class SscPlayAction extends BaseAction {

	/**
	 * 五星通选显示界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWuxingtongxuan(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ISscIssueTimeService issueTimeService =
		// (ISscIssueTimeService)SpringContextUtil.getBean("sscIssueTimeService");

		// SscBaseInfoConfig info = issueTimeService.getTodayConfig();

		return mapping.findForward("show");
	}

	/**
	 * @deprecated
	 * 五星通选投注信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public ActionForward saveWxtxPlayInfo_old(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SscCommonTool commonTool = (SscCommonTool) SpringContextUtil
				.getBean("sscCommonTool");

		//从request中获得信息
		HttpSession session = request.getSession();
		
		RemindInfo remInfo = (RemindInfo) session.getAttribute("loginmes");
		/****************原有系统 begin***************/
		String strMarkIss = request.getParameter("markiss");// 期号
		String strIssueType = request.getParameter("issuetype");// 类型singleissue
		String strIssueNumber = request.getParameter("getissuenumber");// 期号,多期投注及各期的数量"9009310|1$9009311|1$9009312|1"
//		String strPlayType = request.getParameter("playtype");// 类型，中文strPlayType=
//																// "任选一"
//		String strPlayMode = request.getParameter("playmode");// 模式,复式
		// String strWagTotBef = request.getParameter("wagtotbef");
		String strWagerTotal = request.getParameter("wagertotal");// 投注的号码
		String strWagerNum = request.getParameter("wagernum");// 总注数
		String strSelling = request.getParameter("selling");// 是否销售
		String strTabName = "pokwagerinfo";
		/****************原有系统 end***************/
		
		String playType = request.getParameter("playType");//玩法
		String typeName = request.getParameter("typeName");//玩法小项名称
		String playMode = request.getParameter("playMode");// 方式
		String issueType = request.getParameter("issueType");//投注种类，时时彩、12x5等
		String curIssueNum = request.getParameter("curIssueNum");// 当期投注的期号
		String curTotalTzMoney = request.getParameter("curTotalTzMoney");//当前期投注的金额
		String curTotalTzCount = request.getParameter("curTotalTzCount");//当前期投注的数量				
		String totalTzMoney = request.getParameter("totalTzMoney");//总共投注的金额
		String totalTzCount = request.getParameter("totalTzCount");//总共投注的数量
		String haveZhuitou = request.getParameter("haveZhuitou");//是否有追投
		String zhuitouInfo = request.getParameter("zhuitouInfo");//追投的信息
		String isZuhe = request.getParameter("isZuhe");//是否是组合	
		
		boolean flag = true;
		// 判断是否已经登录
		if (remInfo == null || remInfo.getLoginUserName() == null || remInfo.getLoginUserName().equals("")) {
			logger.info("判断用户是否登录...");
			request.setAttribute("wagrem", "您还没有登录，请先登录!");
			flag = false;
		} 
		
		
		// 判断是否已经超过销售时间
		if(flag){			
			logger.info("判断当前时间是否正在销售...");
			String issStat = commonTool.comparePlayAndServerTime("ssc");
			if (issStat.equals("0")) {
				request.setAttribute("wagrem", "今天停止销售!");// 今天停止销售 1:正在销售
				flag = false;											// 2:正在开奖
			} else if (issStat.equals("2")) {
				request.setAttribute("wagrem", "正在开奖,暂停销售!");// 1:正在销售 2:正在开奖
				flag = false;									// 2:正在开奖
			}
		}
	
		//设置表单信息			 
		SscTzInfo info = new SscTzInfo();
		if(flag){
			String strUserName = remInfo.getLoginUserName();
			//String userId = remInfo.get
			
			
			info.setUserName(strUserName);//当前登录用户名
			info.setIssueNum(strMarkIss);//当前发行的期号
			info.setPlayType(playType);//玩法
			info.setTypeName(typeName);//玩法小项名称
			info.setPlayMode(playMode);//方式
			info.setTouzhuNum(strWagerTotal);//投注的号码
			
			info.setIsSell(strSelling);
			
			info.setIssueType(issueType);//彩票种类
			info.setCurTotalTzMoney(curTotalTzMoney);//当期投注的金额
			info.setCurTotalTzCount(curTotalTzCount);//当期投注的注数
			info.setTotalTzMoney(totalTzMoney);//当前投注的总金额
			info.setTotalTzCount(totalTzCount);//当前投注的总数量
			info.setHaveZhuitou(haveZhuitou);//是否有追投
			info.setZhuitouInfo(zhuitouInfo);//追投的期号及倍数信息
			info.setIsZuhe(isZuhe);//是否组合
		}
		
		ISscTzInfoService sscTzService = (ISscTzInfoService)SpringContextUtil.getBean("sscTzInfoService");

		//判断用户账号中是否有余额购买
		if(flag){
			logger.info("判断当前用户是否可以购买...");
			String result = sscTzService.isCanTouzhu(info);
			if(!result.equals("")){
				request.setAttribute("wagrem", result);//当前用户是否能购买
				flag = false;
			}
		}
		if(flag){
			//进行限号计算
			logger.info("todo 进行限号计算！");
			flag = sscTzService.isInLimit(playType, typeName, playMode, strMarkIss, strWagerTotal, haveZhuitou, zhuitouInfo);

		}
		
		if(flag){
			//都没有问题，则进行保存
			boolean tempFlag = sscTzService.saveTouzhuInfo(info);
			if(tempFlag){
				request.setAttribute("wagrem", "投注成功!");//保存成功，提示信息
			}else{
				request.setAttribute("wagrem", "投注失败!");//保存成功，提示信息
				flag = false;
			}
		}
			
		

		return mapping.findForward("wuxingTx");
	}
	/**
	 * @deprecated
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveWxPlayInfoOld(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SscCommonTool commonTool = (SscCommonTool) SpringContextUtil.getBean("sscCommonTool");

		//从request中获得信息
		HttpSession session = request.getSession();
		//登陆信息
		RemindInfo remInfo = (RemindInfo) session.getAttribute("loginmes");
		//其他信息
		String strMarkIss = request.getParameter("markiss");// 期号
		String strIssueType = request.getParameter("issuetype");// 类型singleissue
		String strIssueNumber = request.getParameter("getissuenumber");// 期号,多期投注及各期的数量"9009310|1$9009311|1$9009312|1"

		// String strWagTotBef = request.getParameter("wagtotbef");
		String strWagerTotal = request.getParameter("wagertotal");// 投注的号码
		String strWagerNum = request.getParameter("wagernum");// 总注数
		String strSelling = request.getParameter("selling");// 是否销售
		String strTabName = "pokwagerinfo";
		/****************原有系统 end***************/
		
		String playType = request.getParameter("playType");//玩法
		String typeName = request.getParameter("typeName");//玩法小项名称
		String playMode = request.getParameter("playMode");// 方式
		String issueType = request.getParameter("issueType");//投注种类，时时彩、12x5等
		String curIssueNum = request.getParameter("curIssueNum");// 当期投注的期号
		String curTotalTzMoney = request.getParameter("curTotalTzMoney");//当前期投注的金额
		String curTotalTzCount = request.getParameter("curTotalTzCount");//当前期投注的数量				
		String totalTzMoney = request.getParameter("totalTzMoney");//总共投注的金额
		String totalTzCount = request.getParameter("totalTzCount");//总共投注的数量
		String haveZhuitou = request.getParameter("haveZhuitou");//是否有追投
		String zhuitouInfo = request.getParameter("zhuitouInfo");//追投的信息		
		String isZuhe = request.getParameter("isZuhe");//是否是组合		
		
		boolean flag = true;
		// 判断是否已经登录 curIssueNum strWagerTotal
		if (remInfo == null || remInfo.getLoginUserName() == null || remInfo.getLoginUserName().equals("")) {
			logger.info("判断用户是否登录...");
			request.setAttribute("wagrem", "您还没有登录，请先登录!");
			flag = false;
		} 
		
		
		// 判断是否已经超过销售时间
		if(flag){			
			logger.info("判断当前时间是否正在销售...");
			String issStat = commonTool.comparePlayAndServerTime("ssc");
			if (issStat.equals("0")) {
				request.setAttribute("wagrem", "今天停止销售!");// 今天停止销售 1:正在销售
				flag = false;											// 2:正在开奖
			} else if (issStat.equals("2")) {
				request.setAttribute("wagrem", "正在开奖,暂停销售!");// 1:正在销售 2:正在开奖
				flag = false;									// 2:正在开奖
			}
		}
	
		//设置表单信息			 
		SscTzInfo info = new SscTzInfo();
		if(flag){
			String strUserName = remInfo.getLoginUserName();
			//String userId = remInfo.get		
			
			info.setUserName(strUserName);//当前登录用户名
			info.setIssueNum(strMarkIss);//当前发行的期号
			info.setPlayType(playType);//玩法
			info.setTypeName(typeName);//玩法小项名称
			info.setPlayMode(playMode);//方式
			info.setTouzhuNum(strWagerTotal);//投注的号码
			
			info.setIsSell(strSelling);
			
			info.setIssueType(issueType);//彩票种类
			info.setCurTotalTzMoney(curTotalTzMoney);//当期投注的金额
			info.setCurTotalTzCount(curTotalTzCount);//当期投注的注数
			info.setTotalTzMoney(totalTzMoney);//当前投注的总金额
			info.setTotalTzCount(totalTzCount);//当前投注的总数量
			info.setHaveZhuitou(haveZhuitou);//是否有追投
			info.setZhuitouInfo(zhuitouInfo);//追投的期号及倍数信息
			info.setIsZuhe(isZuhe);//是否组合
		}
		
		ISscTzInfoService sscTzService = (ISscTzInfoService)SpringContextUtil.getBean("sscTzInfoService");

		//判断用户账号中是否有余额购买
		if(flag){
			logger.info("判断当前用户是否可以购买...");
			String result = sscTzService.isCanTouzhu(info);
			if(!result.equals("")){
				request.setAttribute("wagrem", result);//当前用户是否能购买
				flag = false;
			}
		}
		if(flag){
			//进行限号计算 
			logger.info("todo 进行限号计算！");
			flag = sscTzService.isInLimit(playType, typeName,playMode, strMarkIss, strWagerTotal, haveZhuitou, zhuitouInfo);
		}
		
		if(flag){
			//都没有问题，则进行保存
			boolean tempFlag = sscTzService.saveTouzhuInfo(info);
			if(tempFlag){
				request.setAttribute("wagrem", "投注成功!");//保存成功，提示信息				
			}else{
				request.setAttribute("wagrem", "投注失败!");//保存成功，提示信息
				flag = false;
			}
		}
		String forward = "";
		if(playType.equals("wuxing")){
			if(playMode.equals("fushi")){
				forward="wuxingFushi";
			}
			if(playMode.equals("danshi")){
				forward="wuxingDanshi";
			}
		}
		

		return mapping.findForward(forward);
	}
	/**
	 * 此为公用方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePlayInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SscCommonTool commonTool = (SscCommonTool) SpringContextUtil.getBean("sscCommonTool");

		//从request中获得信息
		HttpSession session = request.getSession();
		//登陆信息
		RemindInfo remInfo = (RemindInfo) session.getAttribute("loginmes");
		//其他信息
		String strMarkIss = request.getParameter("markiss");// 期号
		String strIssueType = request.getParameter("issuetype");// 类型singleissue
		String strIssueNumber = request.getParameter("getissuenumber");// 期号,多期投注及各期的数量"9009310|1$9009311|1$9009312|1"

		//String strWagerTotal = request.getParameter("wagertotal");// 投注的号码
		String strWagerTotal = new String(request.getParameter("wagertotal").getBytes("ISO8859_1"),"GB2312").trim();//投注的号码
		String strWagerNum = request.getParameter("wagernum");// 总注数
		String strSelling = request.getParameter("selling");// 是否销售

		/****************原有系统 end***************/
		
		String playType = request.getParameter("playType");//玩法
		String typeName = request.getParameter("typeName");//玩法小项名称
		String playMode = request.getParameter("playMode");// 方式
		String issueType = request.getParameter("issueType");//投注种类，时时彩、12x5等
		String curIssueNum = request.getParameter("curIssueNum");// 当期投注的期号
		String curTotalTzMoney = request.getParameter("curTotalTzMoney");//当前期投注的金额
		String curTotalTzCount = request.getParameter("curTotalTzCount");//当前期投注的数量				
		String totalTzMoney = request.getParameter("totalTzMoney");//总共投注的金额
		String totalTzCount = request.getParameter("totalTzCount");//总共投注的数量
		String haveZhuitou = request.getParameter("haveZhuitou");//是否有追投
		String zhuitouInfo = request.getParameter("zhuitouInfo");//追投的信息		
		String isZuhe = request.getParameter("isZuhe");//是否是组合		
		
		boolean flag = true;
		// 判断是否已经登录
		if (remInfo == null || remInfo.getLoginUserName() == null || remInfo.getLoginUserName().equals("")) {
			logger.info("判断用户是否登录...");
			request.setAttribute("wagrem", "您还没有登录，请先登录!");
			flag = false;
		} 
		
		
		// 判断是否已经超过销售时间
		if(flag){			
			logger.info("判断当前时间是否正在销售...");
			String issStat = commonTool.comparePlayAndServerTime("ssc");
			if (issStat.equals("0")) {
				request.setAttribute("wagrem", "今天停止销售!");// 今天停止销售 1:正在销售
				flag = false;											// 2:正在开奖
			} else if (issStat.equals("2")) {
				request.setAttribute("wagrem", "正在开奖,暂停销售!");// 1:正在销售 2:正在开奖
				flag = false;									// 2:正在开奖
			}
		}
		//判断期号是否为空,解决空期号的问题
		if(strMarkIss == null || strMarkIss.equals("")){
			request.setAttribute("wagrem", "当前没有销售信息!");// 期号为空
			flag = false;
		}
		//判断是否有投注内容
		if(strWagerTotal == null || strWagerTotal.equals("")){
			request.setAttribute("wagrem", "请选择投注号码!");// 没有选择投注号码
			flag = false;
		}
		//判断该投注的期号是否与服务器目前进行的期号一致，主要为解决页面停止导致投注期号不一致的情况
		if(flag){
			SscCommonTool sscTool = new SscCommonTool();
			String serverIssueTime = sscTool.getSscNowIssueNumOnServer();
			logger.info("the page issueNum="+strMarkIss+",and the server issueNum is:" + serverIssueTime);
			if(!serverIssueTime.equals(strMarkIss)){
				flag = false;
				request.setAttribute("wagrem", "投注期号已经过期，请刷新页面后重新投注!");// 当前投注期号和服务器期号不同，可能客户端与服务器端发生延迟，不允许投注
			}
		}
		//设置表单信息			 
		SscTzInfo info = new SscTzInfo();
		if(flag){
			String strUserName = remInfo.getLoginUserName();
			//String userId = remInfo.get		
			
			info.setUserName(strUserName);//当前登录用户名
			info.setIssueNum(strMarkIss);//当前发行的期号
			info.setPlayType(playType);//玩法
			info.setTypeName(typeName);//玩法小项名称
			info.setPlayMode(playMode);//方式
			info.setTouzhuNum(strWagerTotal);//投注的号码
			
			info.setIsSell(strSelling);
			
			info.setIssueType(issueType);//彩票种类
			info.setCurTotalTzMoney(curTotalTzMoney);//当期投注的金额
			info.setCurTotalTzCount(curTotalTzCount);//当期投注的注数
			info.setTotalTzMoney(totalTzMoney);//当前投注的总金额
			info.setTotalTzCount(totalTzCount);//当前投注的总数量
			info.setHaveZhuitou(haveZhuitou);//是否有追投
			info.setZhuitouInfo(zhuitouInfo);//追投的期号及倍数信息
			info.setIsZuhe(isZuhe);//是否为组合
		}
		
		ISscTzInfoService sscTzService = (ISscTzInfoService)SpringContextUtil.getBean("sscTzInfoService");
		ISscLimitService sscLimitService = (ISscLimitService)SpringContextUtil.getBean("sscLimitService");

		//判断用户账号中是否有余额购买
		if(flag){
			logger.info("判断当前用户是否可以购买...");
			String result = sscTzService.isCanTouzhu(info);
			if(!result.equals("")){
				request.setAttribute("wagrem", result);//当前用户是否能购买
				flag = false;
			}
		}
		if(flag){
			//进行限号计算
			logger.info("todo 进行限号计算！");
			//flag = sscTzService.isInLimit(playType, typeName,playMode, strMarkIss, strWagerTotal, haveZhuitou, zhuitouInfo);
			flag = sscLimitService.isLimitNum(info);
			if(!flag){
				request.setAttribute("wagrem", "当前投注超出限制倍数!");//保存成功，提示信息
			}
		}
		
		if(flag){
			//都没有问题，则进行保存
			boolean tempFlag = sscTzService.saveTouzhuInfo(info);
			if(tempFlag){
				IUserManageService ums =  (IUserManageService) SpringContextUtil.getBean("userManageService");
				UserInfo ui = ums.getUserInfoByName(remInfo.getLoginUserName());
				String money = ui.getTotalmoney();
				request.setAttribute("wagrem", "投注成功,您的当前余额为" + money + "元");//保存成功，提示信息
			}else{
				request.setAttribute("wagrem", "投注失败!");//保存成功，提示信息
				flag = false;
			}
		}
		String forward = getForward(playType,typeName,playMode);
		
		return mapping.findForward(forward);
	}
	
	private String getForward(String playType,String typeName,String playMode){
		String forward = "";
		//五星
		if(playType.equals("wuxing")){
			if(typeName.equals("tx")){
				//if(playMode.equals("fushi")){
					forward="wuxingTx";
				//}
			}
			if(typeName.equals("zhixuan")){
				if(playMode.equals("fushi")){
					forward="wuxingFushi";
				}			
				if(playMode.equals("danshi")){
					forward="wuxingDanshi";
				}
			}
			if(typeName.equals("zuhe")){
				if(playMode.equals("fushi")){
					forward="wuxingZuhe";
				}
			}
			if(typeName.equals("zuxuan")){
				if(playMode.equals("zu120")){
					forward="wuxingZu120";
				}
				if(playMode.equals("zu60")){
					forward="wuxingZu60";
				}
				if(playMode.equals("zu30")){
					forward="wuxingZu30";
				}
				if(playMode.equals("zu20")){
					forward="wuxingZu20";
				}
				if(playMode.equals("zu10")){
					forward="wuxingZu10";
				}
				if(playMode.equals("zu5")){
					forward="wuxingZu5";
				}

				if(playMode.equals("zuchong2")){
					forward="wuxingZuchong2";
				}

				if(playMode.equals("zuchong3")){
					forward="wuxingZuchong3";
				}

				if(playMode.equals("zuchong4")){
					forward="wuxingZuchong4";
				}
				if(playMode.equals("zu24")){
					forward="sixingZu24";
				}
				if(playMode.equals("zu12")){
					forward="sixingZu12";
				}
				if(playMode.equals("zu6")){
					forward="sixingZu6";
				}
				if(playMode.equals("zu4")){
					forward="sixingZu4";
				}
			}
		}
		////四星
		if(playType.equals("sixing")){			
			if(typeName.equals("zhixuan")){
				if(playMode.equals("fushi")){
					forward="sixingFushi";
				}
				if(playMode.equals("danshi")){
					forward="sixingDanshi";
				}
			}
			if(typeName.equals("zuhe")){
				if(playMode.equals("fushi")){
					forward="sixingZuhe";
				}
			}
			if(typeName.equals("zuxuan")){
				if(playMode.equals("zu24")){
					forward="sixingZu24";
				}
				if(playMode.equals("zu12")){
					forward="sixingZu12";
				}
				if(playMode.equals("zu6")){
					forward="sixingZu6";
				}
				if(playMode.equals("zu4")){
					forward="sixingZu4";
				}
			}
		}
		///三星
		if(playType.equals("sanxing")){
			if(typeName.equals("zhixuan")){
				if(playMode.equals("fushi")){
					forward="sanxingZhixuanFushi";
				}
				if(playMode.equals("danshi")){
					forward="sanxingZhixuanDanshi";
				}
				if(playMode.equals("zuheFushi")){
					forward="sanxingZhixuanZuheFushi";
				}
			}
			if(typeName.equals("zuxuan")){
				if(playMode.equals("zu3")){
					forward="sanxingZu3";
				}
				if(playMode.equals("zu6")){
					forward="sanxingZu6";
				}
				if(playMode.equals("baodan")){
					forward="sanxingBaodan";
				}
				if(playMode.equals("hezhi")){
					forward="sanxingHezhi";
				}
				if(playMode.equals("danshi")){
					forward="sanxingZuxuanDanshi";
				}
			}
			if(typeName.equals("zuhe")){
				if(playMode.equals("fushi")){
					forward="sanxingZuhe";
				}
			}
		}
		////二星
		if(playType.equals("erxing")){
			if(typeName.equals("zhixuan")){
				if(playMode.equals("fushi")){
					forward="erxingZhixuanFushi";
				}
				if(playMode.equals("danshi")){
					forward="erxingZhixuanDanshi";
				}
			}
			if(typeName.equals("zuxuan")){
				if(playMode.equals("fushi")){
					forward="erxingZuxuanFushi";
				}
				if(playMode.equals("danshi")){
					forward="erxingZuxuanDanshi";
				}
				if(playMode.equals("hezhi")){
					forward="erxingZuxuanHezhi";
				}
			}
			if(typeName.equals("zuhe")){
				if(playMode.equals("fushi")){
					forward="erxingZuhe";
				}
			}
		}
		/////一星
		if(playType.equals("yixing")){
			if(typeName.equals("zhixuan")){
				if(playMode.equals("fushi")){
					forward="yixingZhixuanFushi";
				}
			}
		}
		//大小单双
		if(playType.equals("dxds")){
			forward = "dxds";
		}
		//任选
		if(playType.equals("renxuan1")){
			forward = "renxuan1";
		}
		if(playType.equals("renxuan2")){
			forward = "renxuan2";
		}
		if(playType.equals("renxuan3")){
			forward = "renxuan3";
		}
		return forward;
	}
}
