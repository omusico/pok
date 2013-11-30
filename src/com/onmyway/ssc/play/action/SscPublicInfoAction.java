package com.onmyway.ssc.play.action;

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
import com.onmyway.ssc.manage.model.SscZjNumConfig;
import com.onmyway.ssc.manage.service.ISscManageService;
import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.ssc.play.service.ISscIssuePublicService;
import com.onmyway.ssc.play.service.ISscTzInfoService;
import com.onmyway.sxw.manage.model.SxwZjNumConfig;
import com.onmyway.sxw.manage.service.ISxwManageService;
import com.onmyway.sxw.play.service.ISxwTzInfoService;

/**
 * @Title:模块下，公共信息
 * @Description: 
 * @Create on: Aug 16, 2010 3:59:57 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscPublicInfoAction  extends BaseAction{
	
	/**
	 * 显示时时彩首页
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
 	  
		return mapping.findForward("sscIndex");
	}
	/**
	 * 在首页显示时时彩中奖信息列表
	 * 应去除当前期别的
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSscZjNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//如果indexFlag 有值，表示是开奖时刷新的值，要显示当前期别的，否则就不显示当前期别的信息
		String indexFlag = request.getParameter("indexFlag");
		
		ISscIssuePublicService sscIssuePublicService = (ISscIssuePublicService)SpringContextUtil.getBean("sscIssuePublicService");
		String currentIssue = sscIssuePublicService.getCurrentIssue();
		
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		//List<SscZjNumConfig> list = sscManageService.getAllZjNum(currentIssue);
		
		List<SscZjNumConfig> list = new ArrayList<SscZjNumConfig>();
		
		if(indexFlag != null && !indexFlag.equals("") && indexFlag.equals("indexPage")){
			list = sscManageService.getAllZjNum();
		}else{
			list = sscManageService.getAllZjNum(currentIssue);
		}
		request.setAttribute("zjNumList", list);
		
		return mapping.findForward("showZjNumList");
	}
	
	/**
	 * 显示时时彩的追投页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSscZhuitou(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISscIssuePublicService sscIssuePublicService = (ISscIssuePublicService)SpringContextUtil.getBean("sscIssuePublicService");

		//当天其他的发行期限
		List<SingleIssueInfo> list = sscIssuePublicService.getTodayOtherIssue();		
		request.setAttribute("otherList", list);
		
		return mapping.findForward("showZhuitouPage");
	}
	/**
	 * 显示时时彩的开奖动画
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSscWinMovie(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ISscIssuePublicService sscIssuePublicService = (ISscIssuePublicService)SpringContextUtil.getBean("sscIssuePublicService");
		String currentIssue = sscIssuePublicService.getCurrentIssue();
		
		
		//如果当天没有开奖，则置为空
		SscZjNumConfig info = null;
		if(currentIssue==null || currentIssue.equals("")){
			info = new SscZjNumConfig();
			info.setIssueNum("");
			info.setWan("-");
			info.setQian("-");
			info.setBai("-");
			info.setShi("-");
			info.setGe("-"); 
		}else{ 
		
			ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
			
			//得到当期的中奖号码
			 info = sscManageService.getCurrentZjNum(currentIssue);
		}
		//如果小于10，则加上“0”
		
		/*
		String wan = info.getWan();
		if(wan != null && !wan.equals("")){
			if(!wan.equals("-")){
				if(Integer.parseInt(wan) < 10 ){
					wan = "0"+wan;
				}
			}else{
				wan = "";
			}	
		}
		
		String qian = info.getQian();
		if(qian != null && !qian.equals("")){
			if(!qian.equals("-")){
				if(Integer.parseInt(qian) < 10){
					qian = "0"+qian;
				}
			}else{
				qian = "";
			}
		}
		
		String bai = info.getBai();
		if(bai != null && !bai.equals("")){
			if(!bai.equals("-")){
				if(Integer.parseInt(bai) < 10){
					bai = "0"+bai;
				}
			}else{
				bai = "";
			}
		}
		
		String shi = info.getShi();
		if(shi != null && !shi.equals("")){
			if(!shi.equals("-")){
				if(Integer.parseInt(shi) < 10){
					shi = "0"+shi;
				}
			}else{
				shi = "";
			}	
		}
		
		String ge = info.getGe();
		if(ge != null && !ge.equals("")){
			if(!ge.equals("-")){
				if(Integer.parseInt(ge) < 10){
					ge = "0"+ge;
				}
			}else{
				ge = "";
			}	
		}
		*/
		
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
	 * 显示时时彩中奖的走势图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showSscZoushi(ActionMapping mapping, ActionForm form,
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
		ISscIssuePublicService sscIssuePublicService = (ISscIssuePublicService)SpringContextUtil.getBean("sscIssuePublicService");
		String currentIssue = sscIssuePublicService.getCurrentIssue();		
		
		ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
		List<SscZjNumConfig> list = sscManageService.getZjNumBetweenDate(beginDate,endDate,currentIssue);
		
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
		ISscTzInfoService tzInfoService = (ISscTzInfoService)SpringContextUtil.getBean("sscTzInfoService");
		
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
				 List<SscTzInfo> list =  tzInfoService.getUserTzInfo(issueNum, userId);
				 request.setAttribute("tzInfoList", list);
				 //所投总注数…..注;所投总金额…..元
				 SscTzInfo info = tzInfoService.getUserTotalTzInfo(null,userId);
				 Integer totalTzZhushu = info.getTouzhuCount();
				 Integer totalTzMoney  = info.getTouzhuMoney();
				 
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
				 List<SscZjInfo> list = sscManageService.getZjInfo(issueNum, null,userId);
				 request.setAttribute("userZjList", list);
				 
				 //中奖总注数…..注;中奖总金额..元
				 SscZjInfo info = sscManageService.getUserTotalZjInfo(null,userId);
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
		ISscTzInfoService tzInfoService = (ISscTzInfoService)SpringContextUtil.getBean("sscTzInfoService");
		
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
		 ISscManageService sscManageService = (ISscManageService)SpringContextUtil.getBean("sscManageService");
	
		boolean flag = sscManageService.delUserZjInfo(tzId, userId);
		
		return viewUserZjInfo(mapping,form,request,response);
	
	}
}
