package com.onmyway.ssc.manage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import util.JDateToolkit;
import beanpac.RemindInfo;

import com.onmyway.common.BaseAction;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
import com.onmyway.ssc.manage.model.SscMoneyAndLimit;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.manage.model.SscZjNumConfig;
import com.onmyway.ssc.manage.service.ISscManageService;
import com.onmyway.sxw.manage.service.ISxwManageService;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 14, 2010 2:20:01 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscManageAction extends BaseAction{

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		//查询当天的配置信息
		SscBaseInfoConfig info = sscManageService.getConfigInfoOfToday();	
		request.setAttribute("sscConfigInfo", info);	
		return mapping.findForward("success");
	}
	/**
	 * 保存中奖号码信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZhongjianghaoma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zjIssueNum = request.getParameter("zjIssueNum");
		String isResetIssueInfo = request.getParameter("isResetIssueInfo");//是否重新设置中奖号码 1：重新设置；0：不重新设置
//		ISscManageService sscManageService = (ISscManageService)getBean("sscManageService");
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		
		SscZjNumConfig zjInfo = new SscZjNumConfig();		
	 
		bindEntity(form, zjInfo);
		zjInfo.setIssueNum(zjIssueNum);//中奖号码的期号
		String nowDate = JDateToolkit.getNowDate1();//含时分秒
		zjInfo.setIssueDate(nowDate.substring(0,nowDate.indexOf(" ")));
		zjInfo.setOperTime(nowDate);
		zjInfo.setIsResetIssueInfo(isResetIssueInfo);
//		sscManageService.save(zjInfo);
		boolean flag = sscManageService.saveZjInfo(zjInfo);
		if(flag){
			request.setAttribute("saveZhongjianghaoma", "中奖号码保存成功");
		}else{
			//saveMessage2Session(request, "中奖号码保存失败");
			request.setAttribute("saveZhongjianghaoma", "中奖号码保存失败");
		}
		
		//查询当天的配置信息
		SscBaseInfoConfig info = sscManageService.getConfigInfoOfToday();	
		request.setAttribute("sscConfigInfo", info);	
		
		return mapping.findForward("success");
	}
	/**
	 * 查看 中奖号码信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZhongjianghaoma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("zjhmId");
//		ISscManageService sscManageService = (ISscManageService)getBean("sscManageService");
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		
		//SscZjNumConfig zjInfo = new SscZjNumConfig();		
	 
		List<SscZjNumConfig> list = sscManageService.getAllZjNum();
		request.setAttribute("zjhmList", list);
		//查询当天的配置信息
//		SscBaseInfoConfig info = sscManageService.getConfigInfoOfToday();	
//		request.setAttribute("sscConfigInfo", info);	
		
		return mapping.findForward("showZjNum");
	}
	/**
	 * 删除 时时彩中奖号码信息
	 * 删除中奖号码，同时将该中奖号码对应的投注期数的所有用户中奖信息删除（因为每一期只有一个中奖号码）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZhongjianghaoma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("zjhmId");
		String issueNum = request.getParameter("issueNum");//中奖期号
		log.info("时时彩 删除："+issueNum);
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		sscManageService.delZhongjianghaoma(issueNum);
		
		return showZhongjianghaoma(mapping,form,request,response);

	}
	/**
	 * 保存基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBaseInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("zjhmId");
//		ISscManageService sscManageService = (ISscManageService)getBean("sscManageService");
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		
		SscBaseInfoConfig info = new SscBaseInfoConfig();		
	 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate4();//含时分秒
		info.setBeginDate(nowDate.substring(0,nowDate.indexOf(" ")));
		info.setOperTime(nowDate);
		sscManageService.saveBaseInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
//		saveTextMessage(request, "基本信息保存成功");

		request.setAttribute("saveBaseInfo", "启动成功");
		//查询当天的配置信息
		SscBaseInfoConfig info2 = sscManageService.getConfigInfoOfToday();	
		request.setAttribute("sscConfigInfo", info2);	
		
		return mapping.findForward("success");
	}
	/**
	 * 奖金及限号设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveLimitInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("zjhmId");
//		ISscMana
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		
		SscMoneyAndLimit info = new SscMoneyAndLimit();		
		 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate1();//含时分秒
		info.setOperTime(nowDate);
		info.setIsValid("1");
		sscManageService.saveLimitInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
//		saveTextMessage(request, "奖金及限号信息保存成功");

		request.setAttribute("saveLimitInfo", "奖金及限号信息保存成功");
		//查询当天的配置信息
		SscBaseInfoConfig info2 = sscManageService.getConfigInfoOfToday();	
		request.setAttribute("sscConfigInfo", info2);	
		
		return mapping.findForward("success");
	}
	/**
	 * 管理员及相关用户查询中奖信息
	 * 管理员可以查看相应期号所有的中奖信息
	 * 普通用户只可以查看本人的中奖信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findZjUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String issueNum = request.getParameter("queryIssueNum");
		HttpSession session = request.getSession(false);
		 //判断是否已经登陆	 
		 Object obj =  session.getAttribute("loginmes");

		 ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		 if(obj == null){
			 request.setAttribute("haveLogin", "0");
		 }else{
			 RemindInfo remindInfo = (RemindInfo)obj;		 
			 String userName = remindInfo.getLoginUserName();
			 
			 if(userName == null || userName.equals("")){
				 request.setAttribute("haveLogin", "0");
			 }else{
				 request.setAttribute("haveLogin", "1"); 
				 List<SscZjInfo> list = sscManageService.getZjInfo(issueNum, userName);
				 request.setAttribute("zjInfoList", list);
			 }
		 }
		
		 request.setAttribute("issueNum", issueNum);
		//查询当天的配置信息
//		SscBaseInfoConfig info2 = sscManageService.getConfigInfoOfToday();	
//		request.setAttribute("sscConfigInfo", info2);	
		
		return mapping.findForward("showZjUserInfo");
	}
}
