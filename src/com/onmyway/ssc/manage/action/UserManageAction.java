package com.onmyway.ssc.manage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import util.JDateToolkit;

import com.onmyway.common.BaseAction;
import com.onmyway.common.spring.SpringContextUtil;
import com.onmyway.ssc.manage.model.SscBaseInfoConfig;
import com.onmyway.ssc.manage.model.SscZjNumConfig;
import com.onmyway.ssc.manage.service.ISscManageService;

/**
 * @Title:用户管理
 * @Description: 
 * @Create on: Aug 14, 2010 2:20:01 PM
 * @Author:LJY
 * @Version:1.0
 */
public class UserManageAction extends BaseAction{

	public ActionForward showPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		return mapping.findForward("success");
	}
	
}
