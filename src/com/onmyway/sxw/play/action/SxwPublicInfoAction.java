package com.onmyway.sxw.play.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import util.JDateToolkit;
import beanpac.RemindInfo;
import beanpac.SingleIssueInfo;

import com.onmyway.common.BaseAction;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.sxw.manage.model.SxwZjInfo;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;
import com.onmyway.sxw.manage.service.ISxwManageService;
import com.onmyway.sxw.play.model.SxwTzInfo;
import com.onmyway.sxw.play.service.ISxwIssuePublicService;
import com.onmyway.sxw.play.service.ISxwTzInfoService;

/**
 * @Title:模块下，公共信息
 * @Description: 
 * @Create on: Aug 16, 2010 3:59:57 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwPublicInfoAction  extends BaseAction{
	
	/**
	 * 显示12x5首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 	  
		return mapping.findForward("sxwIndex");
	}
	 
	/**
	 * 在首页显示12x5中奖信息列表
	 * 应去除当前期别的
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSxwZjNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//如果indexFlag 有值，表示是开奖时刷新的值，要显示当前期别的，否则就不显示当前期别的信息
		String indexFlag = request.getParameter("indexFlag");
		ISxwIssuePublicService sxwIssuePublicService = (ISxwIssuePublicService)SpringContextUtil.getBean("sxwIssuePublicService");
		
		String currentIssue = sxwIssuePublicService.getCurrentIssue();
		
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		
		List<SxwZjNumConfig> list = new ArrayList<SxwZjNumConfig>();
		
		if(indexFlag != null && !indexFlag.equals("") && indexFlag.equals("indexPage")){
			list = sxwManageService.getAllZjNum();
		}else{
			list = sxwManageService.getAllZjNum(currentIssue);
		}
		request.setAttribute("zjNumList", list);
		
		return mapping.findForward("showZjNumList");
	}
	/**
	 * 显示12x5的追投页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSxwZhuitou(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISxwIssuePublicService sxwIssuePublicService = (ISxwIssuePublicService)SpringContextUtil.getBean("sxwIssuePublicService");

		//当天其他的发行期限
		List<SingleIssueInfo> list = sxwIssuePublicService.getTodayOtherIssue();		
		request.setAttribute("otherList", list);
		
		return mapping.findForward("showZhuitouPage");
	}
	/**
	 * 显示12x5的开奖动画
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSxwWinMovie(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ISxwIssuePublicService sxwIssuePublicService = (ISxwIssuePublicService)SpringContextUtil.getBean("sxwIssuePublicService");
		String currentIssue = sxwIssuePublicService.getCurrentIssue();
		
		SxwZjNumConfig info = null;
		//如果当天没有开奖，则置为空
		if(currentIssue==null || currentIssue.equals("")){
			info = new SxwZjNumConfig();
			info.setIssueNum("");
			info.setWan("-");
			info.setQian("-");
			info.setBai("-");
			info.setShi("-");
			info.setGe("-"); 
		}else{ 
		    ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		    //得到当期的中奖号码
		    info = sxwManageService.getCurrentZjNum(currentIssue);
		}
	  
		//如果小于10，则加上“0”
		String wan = formatMovieNum(info.getWan());
		String qian = formatMovieNum(info.getQian());
		String bai = formatMovieNum(info.getBai());
		String shi = formatMovieNum(info.getShi());
		String ge = formatMovieNum(info.getGe()); 
		
		
		request.setAttribute("wan", wan);
		request.setAttribute("qian", qian);
		request.setAttribute("bai", bai);
		request.setAttribute("shi",shi);
		request.setAttribute("ge", ge);
		
		request.setAttribute("curWinIssueNum",info.getIssueNum()==null?"":info.getIssueNum() );//最近的中奖期号
		
		return mapping.findForward("showWinMovie");
	}
	
	/**
	 * 如果当前数字不为空，如果小于10,则前面补0
	 * @param num
	 * @return
	 */
	private String formatMovieNum(String num){
		if(num != null && !num.equals("")){
			if(!num.equals("-")){
				if(Integer.parseInt(num) < 10){
					num = "0"+num;
				}
			}else{
				num = "";
			}	
		}
		return num;
	}
	
	/**
	 * 显示12x5中奖的走势图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSxwZoushi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String strDaynum = request.getParameter("daynum");
		int intDayNum = 0;
		int dayNum = -3;
		try{
			dayNum = Integer.parseInt("-"+strDaynum);
			intDayNum = Integer.parseInt(strDaynum);
		}catch(NumberFormatException e){
			logger.error("查询走势图时，数字格式化异常：" + e.toString());
		}
		String beginDate = "";
		String endDate = "";
		
		if(intDayNum ==1 ){//近一天，只指昨天
			beginDate = JDateToolkit.getBeforeAfterDate(dayNum);
			endDate = beginDate;
		}else{//其余截止到当天
			beginDate = JDateToolkit.getBeforeAfterDate(dayNum+1);
			endDate = JDateToolkit.getNowDate2();
		}
		ISxwIssuePublicService sxwIssuePublicService = (ISxwIssuePublicService)SpringContextUtil.getBean("sxwIssuePublicService");
		String currentIssue = sxwIssuePublicService.getCurrentIssue();		
		
		ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
		List<SxwZjNumConfig> list = sxwManageService.getZjNumBetweenDate(beginDate,endDate,currentIssue);
		
		request.setAttribute("zjNumList", list);
		request.setAttribute("queryDaynum", strDaynum);
		
		return mapping.findForward("showZjZoushi");
	}
	/**
	 * 查看指定用户的投注记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewUserTzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String userId = request.getParameter("userId");
		String issueNum = request.getParameter("issueNum");
		ISxwTzInfoService tzInfoService = (ISxwTzInfoService)SpringContextUtil.getBean("sxwTzInfoService");
		
		HttpSession session = request.getSession(false);
		 //判断是否已经登陆	  
		 Object obj =  session.getAttribute("loginmes");

		 if(obj == null){
			 request.setAttribute("haveLogin", "0");
		 }else{
			 RemindInfo remindInfo = (RemindInfo)obj;		 
			 String userName = remindInfo.getLoginUserName();
			 
			 if(userName == null || userName.equals("")){
				 request.setAttribute("haveLogin", "0");
			 }else{
				 request.setAttribute("haveLogin", "1"); 
				 List<SxwTzInfo> list =  tzInfoService.getUserTzInfo(issueNum, userId);
				 request.setAttribute("tzInfoList", list);
				 //所投总注数…..注;所投总金额…..元
				 SxwTzInfo info = tzInfoService.getUserTotalTzInfo(null,userId);
				 Integer totalTzZhushu = info.getTouzhuCount();
				 Integer totalTzMoney  = info.getTouzhuMoney();
				 
//				 request.setAttribute("totalTzZhushu", totalTzZhushu.toString());
//				 request.setAttribute("totalTzMoney", totalTzMoney.toString());

				 request.setAttribute("totalTzZhushu", totalTzZhushu == null ? "0" : totalTzZhushu.toString());
				 request.setAttribute("totalTzMoney", totalTzMoney == null ? "0" : totalTzMoney.toString());
			 
		 

			 }
		 }

		return mapping.findForward("viewUserTzInfo");
		
	}
	/**
	 * 查看指定用户的中奖记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewUserZjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String userId = request.getParameter("userId");
		String issueNum = request.getParameter("issueNum");
		
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
				 List<SxwZjInfo> list = sxwManageService.getZjInfo(issueNum, null,userId);
				 request.setAttribute("userZjList", list);
				 
				 //中奖总注数…..注;中奖总金额..元
				 SxwZjInfo info = sxwManageService.getUserTotalZjInfo(null,userId);
				 String totalZjZhushu = info.getZjZhushu();
				 String totalZjMoney  = info.getZjMoney();
				 
				 
				 request.setAttribute("totalZjZhushu", totalZjZhushu);
				 request.setAttribute("totalZjMoney", totalZjMoney); 	
			 }
		 }
		
		 request.setAttribute("issueNum", issueNum);
		
		return mapping.findForward("viewUserZjInfo");
		
	}
	/**
	 * 删除指定用户的投注记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delUserTzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String tzId = request.getParameter("tzId");
		ISxwTzInfoService tzInfoService = (ISxwTzInfoService)SpringContextUtil.getBean("sxwTzInfoService");
		
		boolean flag = tzInfoService.delUserTzInfo(tzId, userId);
		
		return viewUserTzInfo(mapping,form,request,response);
	
	}
	/**
	 * 删除指定用户的中奖记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delUserZjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String tzId = request.getParameter("tzId");
		 ISxwManageService sxwManageService = (ISxwManageService)SpringContextUtil.getBean("sxwManageService");
	
		boolean flag = sxwManageService.delUserZjInfo(tzId, userId);
		
		return viewUserZjInfo(mapping,form,request,response);
	
	}
}
