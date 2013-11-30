package com.onmyway.sxw.manage.action;

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
import com.onmyway.sxw.manage.model.SxwBaseInfoConfig;
import com.onmyway.sxw.manage.model.SxwMoneyAndLimit;
import com.onmyway.sxw.manage.model.SxwZjInfo;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;
import com.onmyway.sxw.manage.service.ISxwManageService;

/**
 * @Title:12选五
 * @Description: 
 * @Create on: Aug 14, 2010 2:20:01 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwManageAction extends BaseAction{

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		
		SxwBaseInfoConfig info = sxwManageService.getConfigInfoOfToday();	
		request.setAttribute("sxwConfigInfo", info);		
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
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
			
		SxwZjNumConfig zjInfo = new SxwZjNumConfig();		
		 
		bindEntity(form, zjInfo);
		zjInfo.setIssueNum(zjIssueNum);//中奖号码的期号
		String nowDate = JDateToolkit.getNowDate1();//含时分秒
		zjInfo.setIssueDate(nowDate.substring(0,nowDate.indexOf(" ")));
		zjInfo.setOperTime(nowDate);
		zjInfo.setIsResetIssueInfo(isResetIssueInfo);
		boolean flag = sxwManageService.saveZjInfo(zjInfo);
		if(flag){
			//saveMessage2Session(request, "中奖号码保存成功");
			request.setAttribute("saveZhongjianghaoma", "中奖号码保存成功");
		}else{
			//saveMessage2Session(request, "中奖号码保存失败");
			request.setAttribute("saveZhongjianghaoma", "中奖号码保存失败");
		}
		//得到当天的配置信息
		SxwBaseInfoConfig info = sxwManageService.getConfigInfoOfToday();	
		request.setAttribute("sxwConfigInfo", info);
		
		return mapping.findForward("success");
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
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		
		SxwBaseInfoConfig info = new SxwBaseInfoConfig();		
	 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate4();//含时分秒
		info.setBeginDate(nowDate.substring(0,nowDate.indexOf(" ")));
		info.setOperTime(nowDate);
		sxwManageService.saveBaseInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
		//saveTextMessage(request, "基本信息保存成功");
		request.setAttribute("saveBaseInfo", "启动成功");
		//得到当天的配置信息
		SxwBaseInfoConfig info2 = sxwManageService.getConfigInfoOfToday();	
		request.setAttribute("sxwConfigInfo", info2);
		
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
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		
		SxwMoneyAndLimit info = new SxwMoneyAndLimit();		
		 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate1();//含时分秒
		info.setOperTime(nowDate);
		info.setIsValid("1");
		sxwManageService.saveLimitInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
		//saveTextMessage(request, "奖金及限号信息保存成功");

		request.setAttribute("saveLimitInfo", "奖金及限号信息保存成功");
		//得到当天的配置信息
		SxwBaseInfoConfig info2 = sxwManageService.getConfigInfoOfToday();	
		request.setAttribute("sxwConfigInfo", info2);
		
		return mapping.findForward("success");
	}
	/**
	 * 查看 12x5中奖号码信息
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
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		
		//SscZjNumConfig zjInfo = new SscZjNumConfig();		
	 
		List<SxwZjNumConfig> list = sxwManageService.getAllZjNum();
		request.setAttribute("zjhmList", list);
	
		
		return mapping.findForward("showZjNum");
	}
	/**
	 * 删除 12x5中奖号码信息
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
		log.info("12x5 删除"+issueNum);
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		sxwManageService.delZhongjianghaoma(issueNum);
		//SscZjNumConfig zjInfo = new SscZjNumConfig();		
	 
//		List<SxwZjNumConfig> list = sxwManageService.getAllZjNum();
//		request.setAttribute("zjhmList", list);
	
		return showZhongjianghaoma(mapping,form,request,response);
		//return mapping.findForward("showZjNum");
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

		 ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		 if(obj == null){
			 request.setAttribute("haveLogin", "0");
		 }else{
			 RemindInfo remindInfo = (RemindInfo)obj;		 
			 String userName = remindInfo.getLoginUserName();
			 
			 if(userName == null || userName.equals("")){
				 request.setAttribute("haveLogin", "0");
			 }else{
				 request.setAttribute("haveLogin", "1"); 
				 List<SxwZjInfo> list = sxwManageService.getZjInfo(issueNum, userName);
				 request.setAttribute("zjInfoList", list);
			 }
		 }
		
		 request.setAttribute("issueNum", issueNum);
		
		return mapping.findForward("showZjUserInfo");
	}

}
