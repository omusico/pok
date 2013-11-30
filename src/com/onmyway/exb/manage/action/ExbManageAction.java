package com.onmyway.exb.manage.action;

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
import com.onmyway.common.exception.AppException;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.exb.manage.model.ExbBaseInfoConfig;
import com.onmyway.exb.manage.model.ExbMoneyAndLimit;
import com.onmyway.exb.manage.model.ExbZjInfo;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.exb.manage.service.IExbManageService;

/**
 * @Title:快乐十分
 * @Description: 
 * @Create on: 2011-12-02 
 * @Author:LJY
 * @Version:1.0
 */
public class ExbManageAction extends BaseAction{

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		
		ExbBaseInfoConfig info = exbManageService.getConfigInfoOfToday();	
		request.setAttribute("exbConfigInfo", info);		
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
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
			
		ExbZjNumConfig zjInfo = new ExbZjNumConfig();		
		 
		bindEntity(form, zjInfo);
		zjInfo.setIssueNum(zjIssueNum);//中奖号码的期号
		String nowDate = JDateToolkit.getNowDate1();//含时分秒
		zjInfo.setIssueDate(nowDate.substring(0,nowDate.indexOf(" ")));
		zjInfo.setOperTime(nowDate);
		zjInfo.setIsResetIssueInfo(isResetIssueInfo);
		try{
			exbManageService.saveZjInfo(zjInfo);
			request.setAttribute("saveZhongjianghaoma", "中奖号码保存成功");
		}catch(AppException e){
			request.setAttribute("saveZhongjianghaoma", "中奖号码保存失败");
		}
		 
		//得到当天的配置信息
		ExbBaseInfoConfig info = exbManageService.getConfigInfoOfToday();	
		request.setAttribute("exbConfigInfo", info);
		
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
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		
		ExbBaseInfoConfig info = new ExbBaseInfoConfig();		
	 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate4();//含时分秒
		info.setBeginDate(nowDate.substring(0,nowDate.indexOf(" ")));
		info.setOperTime(nowDate);
		exbManageService.saveBaseInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
		//saveTextMessage(request, "基本信息保存成功");
		request.setAttribute("saveBaseInfo", "启动成功");
		//得到当天的配置信息
		ExbBaseInfoConfig info2 = exbManageService.getConfigInfoOfToday();	
		request.setAttribute("exbConfigInfo", info2);
		
		return mapping.findForward("success");
	}
	
	/**
	 * @deprecated
	 * 保存基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public ActionForward saveOffsetTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String baseId = request.getParameter("baseId");
		String offsetFlag = request.getParameter("offsetFlag");
		String offsetTime = request.getParameter("offsetTime");
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		
		ExbBaseInfoConfig info = exbManageService.getBaseInfo(baseId);	
		 
	 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate4();//含时分秒
		info.setBeginDate(nowDate.substring(0,nowDate.indexOf(" ")));
		info.setOperTime(nowDate);
		exbManageService.saveBaseInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
		//saveTextMessage(request, "基本信息保存成功");
		request.setAttribute("saveBaseInfo", "启动成功");
		//得到当天的配置信息
		ExbBaseInfoConfig info2 = exbManageService.getConfigInfoOfToday();	
		request.setAttribute("exbConfigInfo", info2);
		
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
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		
		ExbMoneyAndLimit info = new ExbMoneyAndLimit();		
		 
		bindEntity(form, info);
		String nowDate = JDateToolkit.getNowDate1();//含时分秒
		info.setOperTime(nowDate);
		info.setIsValid("1");
		exbManageService.saveLimitInfo(info);
//		saveMessage2Session(request, "基本信息保存成功");
		//saveTextMessage(request, "奖金及限号信息保存成功");

		request.setAttribute("saveLimitInfo", "奖金及限号信息保存成功");
		//得到当天的配置信息
		ExbBaseInfoConfig info2 = exbManageService.getConfigInfoOfToday();	
		request.setAttribute("exbConfigInfo", info2);
		
		return mapping.findForward("success");
	}
	/**
	 * 查看 快乐十分中奖号码信息
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
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		
		//SscZjNumConfig zjInfo = new SscZjNumConfig();		
	 
		List<ExbZjNumConfig> list = exbManageService.getAllZjNum();
		request.setAttribute("zjhmList", list);
	
		
		return mapping.findForward("showZjNum");
	}
	/**
	 * 删除 快乐十分中奖号码信息
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
		log.info("快乐十分 删除"+issueNum);
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		exbManageService.delZhongjianghaoma(issueNum);
		//SscZjNumConfig zjInfo = new SscZjNumConfig();		
	 
//		List<ExbZjNumConfig> list = exbManageService.getAllZjNum();
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

		 IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		 if(obj == null){
			 request.setAttribute("haveLogin", "0");
		 }else{
			 RemindInfo remindInfo = (RemindInfo)obj;		 
			 String userName = remindInfo.getLoginUserName();
			 
			 if(userName == null || userName.equals("")){
				 request.setAttribute("haveLogin", "0");
			 }else{
				 request.setAttribute("haveLogin", "1"); 
				 List<ExbZjInfo> list = exbManageService.getZjInfo(issueNum, userName);
				 request.setAttribute("zjInfoList", list);
			 }
		 }
		
		 request.setAttribute("issueNum", issueNum);
		
		return mapping.findForward("showZjUserInfo");
	}

}
