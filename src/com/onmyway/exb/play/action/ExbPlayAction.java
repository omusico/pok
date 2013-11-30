package com.onmyway.exb.play.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beanpac.RemindInfo;

import com.onmyway.common.BaseAction;
import com.onmyway.common.exb.ExbCommonTool;
import com.onmyway.common.exception.AppException;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.exb.play.model.ExbTzInfo;
import com.onmyway.exb.play.service.IExbLimitService;
import com.onmyway.exb.play.service.IExbTzInfoService;
import com.onmyway.factory.ExbConstant;
import com.onmyway.ssc.manage.model.UserInfo;
import com.onmyway.ssc.manage.service.IUserManageService;

import dbconnpac.ConstantSymbol;

/**
 * @Title:
 * @Description:
 * @Create on: Aug 16, 2010 3:29:03 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbPlayAction extends BaseAction {

	
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
		ExbCommonTool commonTool = (ExbCommonTool) SpringContextUtil.getBean("exbCommonTool");

		//从request中获得信息
		HttpSession session = request.getSession();
		//登陆信息
		RemindInfo remInfo = (RemindInfo) session.getAttribute("loginmes");
		//其他信息
		String strMarkIss = request.getParameter("markiss");// 期号
		String strIssueNumber = request.getParameter("getissuenumber");// 期号,多期投注及各期的数量"9009310|1$9009311|1$9009312|1"

		//String strWagerTotal = request.getParameter("wagertotal");// 投注的号码
		String strWagerTotal = new String(request.getParameter("wagertotal").getBytes("ISO8859_1"),"GB2312").trim();//投注的号码
		String strWagerNum = request.getParameter("wagernum");// 总注数
		String strSelling = request.getParameter("selling");// 是否销售

		/****************原有系统 end***************/
		
		String playType = request.getParameter("playType");//玩法
		String typeName = request.getParameter("typeName");//玩法小项名称
		String playMode = request.getParameter("playMode");// 方式
		String issueType = request.getParameter("issueType");//投注种类，时时彩、快乐十分等
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
			String issStat = commonTool.comparePlayAndServerTime("exb");
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
			ExbCommonTool exbTool = new ExbCommonTool();
			String serverIssueTime = exbTool.getExbNowIssueNumOnServer();
			logger.info("exb the page issueNum="+strMarkIss+",and the server issueNum is:" + serverIssueTime);
			if(!serverIssueTime.equals(strMarkIss)){
				flag = false;
				request.setAttribute("wagrem", "投注期号已经过期，请刷新页面后重新投注!");// 没有选择投注号码
			}
		}
		//判断该投注的期号是否与服务器目前进行的期号一致，主要为解决页面停止导致投注期号不一致的情况
		if(flag){
			logger.info("exb the zhuitou page ztPageCurIssueNum="+ztPageCurIssueNum+",and the touzhu page issueNum is:" + strMarkIss);
			if(!ztPageCurIssueNum.equals(strMarkIss)){
				flag = false;
				request.setAttribute("wagrem", "投注期号异常，请刷新页面后重新投注!");// 没有选择投注号码
			}
		}
		//设置表单信息			 
		ExbTzInfo info = new ExbTzInfo();
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
		
		IExbTzInfoService exbTzService = (IExbTzInfoService)SpringContextUtil.getBean("exbTzInfoService");
		IExbLimitService exbLimitService = (IExbLimitService)SpringContextUtil.getBean("exbLimitService");

		//判断用户账号中是否有余额购买
		if(flag){
			logger.info("判断当前用户是否可以购买...");
			String result = exbTzService.isCanTouzhu(info);
			if(!result.equals("")){
				request.setAttribute("wagrem", result);//当前用户是否能购买
				flag = false;
			}
		}
		if(flag){
			//进行限号计算
			logger.info("todo 进行限号计算！");
			flag = exbLimitService.isLimitNum(info);
			if(!flag){
				request.setAttribute("wagrem", "当前投注超出限制倍数!");//保存成功，提示信息
			}
		}
		
		if(flag){
			//都没有问题，则进行保存
			try{
				exbTzService.saveTouzhuInfo(info);
			
				IUserManageService ums =  (IUserManageService) SpringContextUtil.getBean("userManageService");
				UserInfo ui = ums.getUserInfoByName(remInfo.getLoginUserName());
				String money = ui.getTotalmoney();
				request.setAttribute("wagrem", "投注成功,您的当前余额为" + money + "元");//保存成功，提示信息
			
			}catch(AppException e){
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
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){
			if(playMode.equals(ExbConstant.X1_MODE_SHUTOU)){
				forward="xuan1shutou";
			}
			if(playMode.equals(ExbConstant.X1_MODE_HONGTOU)){
				forward="xuan1hongtou";
			} 
		}
		//任选2
		if(playType.equals(ExbConstant.PLAY_TYPE_X2)){			
			if(playMode.equals(ExbConstant.X2_MODE_RENXUAN)){
				forward="xuan2renxuan";
			}
			if(playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){
				forward="xuan2renxuandantuo";
			}

			if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){
				forward="xuan2lianzhi";
			}
			if(playMode.equals(ExbConstant.X2_MODE_LIANZU)){
				forward="xuan2lianzu";
			}
			if(playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){
				forward="xuan2lianzudantuo";
			}
		}
		//任选3
		if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			if(playMode.equals(ExbConstant.X3_MODE_RENXUAN)){
				forward="xuan3renxuan";
			}
			if(playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){
				forward="xuan3renxuandantuo";
			}
			if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){
				forward="xuan3qianzhi";
			}
			if(playMode.equals(ExbConstant.X3_MODE_QIANZU)){
				forward="xuan3qianzu";
			}
			if(playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){
				forward="xuan3qianzudantuo";
			}
		}
		//任选4
		if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			if(playMode.equals(ExbConstant.X4_MODE_RENXUAN)){
				forward="xuan4renxuan";
			}
			if(playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){
				forward="xuan4dantuo";
			}
		}
		//任选5
		if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			if(playMode.equals(ExbConstant.X5_MODE_RENXUAN)){
				forward="xuan5renxuan";
			}
			if(playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){
				forward="xuan5dantuo";
			}
		}
		 
		return forward;
	}
}
