package com.onmyway.sxw.play.action;

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
import com.onmyway.common.sxw.SxwCommonTool;
import com.onmyway.ssc.manage.model.UserInfo;
import com.onmyway.ssc.manage.service.IUserManageService;
import com.onmyway.sxw.play.model.SxwTzInfo;
import com.onmyway.sxw.play.service.ISxwLimitService;
import com.onmyway.sxw.play.service.ISxwTzInfoService;

import dbconnpac.ConstantSymbol;

/**
 * @Title:
 * @Description:
 * @Create on: Aug 16, 2010 3:29:03 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwPlayAction extends BaseAction {

	
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
		SxwCommonTool commonTool = (SxwCommonTool) SpringContextUtil.getBean("sxwCommonTool");

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
		
		//2012-1-3判断追投页面显示的当前信息是否正确，修正对当前期进行倍投的时候，产生的错误
		String ztPageCurIssueNum = request.getParameter("ztPageCurIssueNum");//追投页面的当前期
		
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
			String issStat = commonTool.comparePlayAndServerTime("sxw");
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
			request.setAttribute("wagrem", "投注失败!");// 期号为空
			flag = false;
		}
		//判断是否有投注内容
		if(strWagerTotal == null || strWagerTotal.equals("")){
			request.setAttribute("wagrem", "请选择投注号码!");// 没有选择投注号码
			flag = false;
		}
		//判断该投注的期号是否与服务器目前进行的期号一致，主要为解决页面停止导致投注期号不一致的情况
		if(flag){
			SxwCommonTool sxwTool = new SxwCommonTool();
			String serverIssueTime = sxwTool.getSxwNowIssueNumOnServer();
			logger.info("sxw the page issueNum="+strMarkIss+",and the server issueNum is:" + serverIssueTime);
			if(!serverIssueTime.equals(strMarkIss)){
				flag = false;
				request.setAttribute("wagrem", "投注期号已经过期，请刷新页面后重新投注!");// 没有选择投注号码
			}
		}
		//判断该投注的期号是否与服务器目前进行的期号一致，主要为解决页面停止导致投注期号不一致的情况
		if(flag){
			logger.info("sxw the zhuitou page ztPageCurIssueNum="+ztPageCurIssueNum+",and the touzhu page issueNum is:" + strMarkIss);
			if(!ztPageCurIssueNum.equals(strMarkIss)){
				flag = false;
				request.setAttribute("wagrem", "投注期号异常，请刷新页面后重新投注!");// 没有选择投注号码
			}
		}
		//设置表单信息			 
		SxwTzInfo info = new SxwTzInfo();
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
			 
		}
		
		ISxwTzInfoService sxwTzService = (ISxwTzInfoService)SpringContextUtil.getBean("sxwTzInfoService");
		ISxwLimitService sxwLimitService = (ISxwLimitService)SpringContextUtil.getBean("sxwLimitService");

		//判断用户账号中是否有余额购买
		if(flag){
			logger.info("判断当前用户是否可以购买...");
			String result = sxwTzService.isCanTouzhu(info);
			if(!result.equals("")){
				request.setAttribute("wagrem", result);//当前用户是否能购买
				flag = false;
			}
		}
		if(flag){
			//进行限号计算
			logger.info("todo 进行限号计算！");
			flag = sxwLimitService.isLimitNum(info);
			if(!flag){
				request.setAttribute("wagrem", "当前投注超出限制倍数!");//保存成功，提示信息
			}
		}
		
		if(flag){
			//都没有问题，则进行保存
			boolean tempFlag = sxwTzService.saveTouzhuInfo(info);
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
		String forward = getForward(playType,playMode);
		
		return mapping.findForward(forward);
	}
	
	private String getForward(String playType, String playMode){
		String forward = "";
		//任选1
		if(playType.equals(ConstantSymbol.RX1)){
			if(playMode.equals("fushi")){
				forward="xuan1fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan1dantuo";
			} 
		}
		//任选2
		if(playType.equals(ConstantSymbol.RX2)){			
			if(playMode.equals("fushi")){
				forward="xuan2fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan2dantuo";
			} 

			if(playMode.equals("zhiqian2")){
				forward="xuan2zhiqian2";
			} 

			if(playMode.equals("zuqian2")){
				forward="xuan2zuqian2";
			} 
			if(playMode.equals("zuqian2dantuo")){
				forward="xuan2zuqian2dantuo";
			} 
		}
		//任选3
		if(playType.equals(ConstantSymbol.RX3)){
			if(playMode.equals("fushi")){
				forward="xuan3fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan3dantuo";
			} 
			if(playMode.equals("zhiqian3")){
				forward="xuan3zhiqian3";
			} 

			if(playMode.equals("zuqian3")){
				forward="xuan3zuqian3";
			} 
			if(playMode.equals("zuqian3dantuo")){
				forward="xuan3zuqian3dantuo";
			} 
		}
		//任选4
		if(playType.equals(ConstantSymbol.RX4)){
			if(playMode.equals("fushi")){
				forward="xuan4fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan4dantuo";
			} 
		}
		//任选5
		if(playType.equals(ConstantSymbol.RX5)){
			if(playMode.equals("fushi")){
				forward="xuan5fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan5dantuo";
			} 
		}
		//任选6
		if(playType.equals(ConstantSymbol.RX6)){
			if(playMode.equals("fushi")){
				forward="xuan6fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan6dantuo";
			} 
		}
		//任选7
		if(playType.equals(ConstantSymbol.RX7)){
			if(playMode.equals("fushi")){
				forward="xuan7fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan7dantuo";
			} 
		}
		//任选8
		if(playType.equals(ConstantSymbol.RX8)){
			if(playMode.equals("fushi")){
				forward="xuan8fushi";
			}
			if(playMode.equals("dantuo")){
				forward="xuan8dantuo";
			} 
		}
		return forward;
	}
}
