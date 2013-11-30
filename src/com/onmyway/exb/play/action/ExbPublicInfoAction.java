package com.onmyway.exb.play.action;

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
import com.onmyway.ssc.manage.model.SscZjInfo;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.exb.manage.model.ExbZjInfo;
import com.onmyway.exb.manage.model.ExbZjNumConfig;
import com.onmyway.exb.manage.service.IExbManageService;
import com.onmyway.exb.play.model.ExbTzInfo;
import com.onmyway.exb.play.service.IExbIssuePublicService;
import com.onmyway.exb.play.service.IExbTzInfoService;

/**
 * @Title:快乐十分 20选8 模块下，公共信息
 * @Description: 
 * @Create on: Aug 16, 2010 3:59:57 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbPublicInfoAction  extends BaseAction{
	
	/**
	 * 显示快乐十分首页
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
 	  
		return mapping.findForward("exbIndex");
	}
	 
	/**
	 * 在首页显示快乐十分中奖信息列表
	 * 应去除当前期别的
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showExbZjNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//如果indexFlag 有值，表示是开奖时刷新的值，要显示当前期别的，否则就不显示当前期别的信息
		String indexFlag = request.getParameter("indexFlag");
		IExbIssuePublicService exbIssuePublicService = (IExbIssuePublicService)SpringContextUtil.getBean("exbIssuePublicService");
		
		String currentIssue = exbIssuePublicService.getCurrentIssue();
		
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		
		List<ExbZjNumConfig> list = new ArrayList<ExbZjNumConfig>();
		
		if(indexFlag != null && !indexFlag.equals("") && indexFlag.equals("indexPage")){
			list = exbManageService.getAllZjNum();
		}else{
			list = exbManageService.getAllZjNum(currentIssue);
		}
		request.setAttribute("zjNumList", list);
		
		return mapping.findForward("showZjNumList");
	}
	/**
	 * 显示快乐十分的追投页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showExbZhuitou(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IExbIssuePublicService exbIssuePublicService = (IExbIssuePublicService)SpringContextUtil.getBean("exbIssuePublicService");

		//当天其他的发行期限
		List<SingleIssueInfo> list = exbIssuePublicService.getTodayOtherIssue();		
		request.setAttribute("otherList", list);
		
		return mapping.findForward("showZhuitouPage");
	}
	/**
	 * 显示快乐十分的开奖动画
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showExbWinMovie(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		IExbIssuePublicService exbIssuePublicService = (IExbIssuePublicService)SpringContextUtil.getBean("exbIssuePublicService");
		String currentIssue = exbIssuePublicService.getCurrentIssue();
		
		ExbZjNumConfig info = null;
		//如果当天没有开奖，则置为空
		if(currentIssue==null || currentIssue.equals("")){
			info = new ExbZjNumConfig();
			info.setIssueNum("");
			info.setPosition1("-");
			info.setPosition2("-");
			info.setPosition3("-");
			info.setPosition4("-");
			info.setPosition5("-");
			info.setPosition6("-");
			info.setPosition7("-");
			info.setPosition8("-");
		}else{
		    IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		    //得到当期的中奖号码
		    info = exbManageService.getCurrentZjNum(currentIssue);
		}
		//如果小于10，则加上“0”
		String position1 = formatMovieNum(info.getPosition1());
		String position2 = formatMovieNum(info.getPosition2());
		String position3 = formatMovieNum(info.getPosition3());
		String position4 = formatMovieNum(info.getPosition4());
		String position5 = formatMovieNum(info.getPosition5());
		String position6 = formatMovieNum(info.getPosition6());
		String position7 = formatMovieNum(info.getPosition7());
		String position8 = formatMovieNum(info.getPosition8()); 
	 
		
		request.setAttribute("position1", position1);
		request.setAttribute("position2", position2);
		request.setAttribute("position3", position3);
		request.setAttribute("position4", position4);
		request.setAttribute("position5", position5);
		request.setAttribute("position6", position6);
		request.setAttribute("position7", position7);
		request.setAttribute("position8", position8);
		
		request.setAttribute("curWinIssueNum",info.getIssueNum()==null?"":info.getIssueNum() );//最近的中奖期号
		
		return mapping.findForward("showWinMovie");
	}
	
	/**
	 * 显示快乐十分中奖的走势图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showExbZoushi(ActionMapping mapping, ActionForm form,
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
		IExbIssuePublicService exbIssuePublicService = (IExbIssuePublicService)SpringContextUtil.getBean("exbIssuePublicService");
		String currentIssue = exbIssuePublicService.getCurrentIssue();		
		
		IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
		List<ExbZjNumConfig> list = exbManageService.getZjNumBetweenDate(beginDate,endDate,currentIssue);
		
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
		IExbTzInfoService tzInfoService = (IExbTzInfoService)SpringContextUtil.getBean("exbTzInfoService");
		
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
				 List<ExbTzInfo> list =  tzInfoService.getUserTzInfo(issueNum, userId);
				 request.setAttribute("tzInfoList", list);
				 //所投总注数…..注;所投总金额…..元
				 ExbTzInfo info = tzInfoService.getUserTotalTzInfo(null,userId);
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
				 List<ExbZjInfo> list = exbManageService.getZjInfo(issueNum, null,userId);
				 request.setAttribute("userZjList", list);
				 
				 //中奖总注数…..注;中奖总金额..元
				 ExbZjInfo info = exbManageService.getUserTotalZjInfo(null,userId);
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
		IExbTzInfoService tzInfoService = (IExbTzInfoService)SpringContextUtil.getBean("exbTzInfoService");
		
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
		 IExbManageService exbManageService = (IExbManageService)SpringContextUtil.getBean("exbManageService");
	
		boolean flag = exbManageService.delUserZjInfo(tzId, userId);
		
		return viewUserZjInfo(mapping,form,request,response);
	
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
}
