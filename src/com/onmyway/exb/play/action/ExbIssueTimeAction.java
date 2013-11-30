package com.onmyway.exb.play.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.onmyway.common.BaseAction;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.exb.manage.model.ExbBaseInfoConfig;
import com.onmyway.exb.play.service.IExbIssuePublicService;

/**
 * @Title:快乐十分-开奖时间显示
 * @Description: 
 * @Create on: Aug 16, 2010 8:59:36 AM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbIssueTimeAction extends BaseAction {
	/**
	 * 开奖时间展示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showIssueTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IExbIssuePublicService issueTimeService = (IExbIssuePublicService)SpringContextUtil.getBean("exbIssuePublicService");
		
		ExbBaseInfoConfig info = issueTimeService.getTodayConfig();
		
		request.setAttribute("configInfo", info);
		
		return mapping.findForward("show");
	}
}
