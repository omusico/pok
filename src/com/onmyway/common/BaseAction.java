package com.onmyway.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.WebUtils;

import com.onmyway.common.spring.SpringBeanUtil;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 14, 2010 2:21:05 PM
 * @Author:LJY
 * @Version:1.0
 */
public class BaseAction extends DispatchAction {
	protected final Log logger = LogFactory.getLog(getClass());

	public static final String SUCCESS = "success";

	public static final String TEXT_MESSAGE_KEY = "messages.msg";

	/**
	 * 将FormBean中的内容通过BeanUtils的copyProperties()绑定到Object中.
	 * 因为BeanUtils中两个参数的顺序很容易搞错，因此封装此函数.
	 * 
	 * @param form
	 * @param entity
	 */
	protected void bindEntity(ActionForm form, Object entity) {
		if (form != null) {
			try {
				BeanUtils.copyProperties(entity, form);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}

	/**
	 * 将Object内容通过BeanUtils的copyProperties 复制到FormBean中.
	 * 因为BeanUtils中两个参数的顺序很容易搞错，因此封装此函数.
	 * 
	 * @param form
	 * @param entity
	 */
	protected void bindForm(ActionForm form, Object entity) {
		if (entity != null) {
			try {
				BeanUtils.copyProperties(form, entity);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}

	/**
	 * 保存单条ActionMessages到request的简化函数.
	 * 
	 * @param request
	 * @param key
	 * @param values
	 */
	protected void saveMessage(HttpServletRequest request, String key, String... values) {
		ActionMessages msgs = new ActionMessages();
		msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(key, values));
		saveMessages(request, msgs);
	}

	/**
	 * 直接保存文本ActionMessages(非i18n)到request的简化函数.
	 * 
	 * @param request
	 * @param message
	 *            文本信息
	 */
	protected void saveTextMessage(HttpServletRequest request, String message) {
		ActionMessages msgs = new ActionMessages();
		msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(TEXT_MESSAGE_KEY, message));
		saveMessages(request, msgs);
	}

	/**
	 * 向view层传递message时，将message放入httpSession的customMessages变量中.
	 * 放在session中能保证message即使redirect也不会消失.
	 * 需配合com.sinosoft.module.core.web.filter.MessageFilter使用.
	 * 
	 * @param request
	 * @param message
	 *            文本信息
	 */
	@SuppressWarnings("unchecked")
	protected void saveMessage2Session(HttpServletRequest request, String message) {
		if (StringUtils.isNotBlank(message)) {
			List customMessages = (List) WebUtils.getOrCreateSessionAttribute(request.getSession(), "customMessages",
					ArrayList.class);
			customMessages.add(message);
		}
	}

	/**
	 * 直接输出.
	 * 
	 * @param response
	 * @param text
	 * @param contentType 输出内容的类型，text,html,xml,json的值见后
	 */
	protected void render(HttpServletResponse response, String text, String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出纯字符串.
	 * 
	 * @param response
	 * @param text
	 */
	protected void renderText(HttpServletResponse response, String text) {
		render(response, text, "text/plain;charset=GBK");
	}

	/**
	 * 直接输出纯HTML.
	 * 
	 * @param response
	 * @param text
	 */
	protected void renderHTML(HttpServletResponse response, String text) {
		render(response, text, "text/html;charset=GBK");
	}

	/**
	 * 直接输出纯XML.
	 * 
	 * @param response
	 * @param text
	 */
	protected void renderXML(HttpServletResponse response, String text) {
		render(response, text, "text/xml;charset=GBK");
	}

	/**
	 * 直接输出纯JSON.
	 * 
	 * @param response
	 * @param text
	 */
	protected void renderJSON(HttpServletResponse response, String text) {
		render(response, text, "text/x-json;charset=GBK");
	}

	/**
	 * 通过SpringBeanUtil获取Spring管理的Bean.
	 * 
	 * @see SpringBeanUtil#getBean(String)
	 * @param beanName
	 * @return
	 */
	protected Object getBean(String beanName) {
		return SpringBeanUtil.getBean(beanName);
	}

	/**
	 * 将String[]转换为Long[].
	 * 
	 * @see ConverterUtils#sa2la(String[])
	 * @param sArr
	 * @return
	 */
	protected Long[] sa2la(String[] sArr) {
		return ConverterUtils.sa2la(sArr);
	}

	/**
	 * 将String转换为Long.
	 * 
	 * @see ConverterUtils#s2l(String)
	 * @param sId
	 * @return
	 */
	protected Long s2l(String s) {
		return ConverterUtils.s2l(s);
	}

	/**
	 * 将Long转换为String.
	 * 
	 * @see ConverterUtils#l2s(Long)
	 * @param strArr
	 * @return
	 */
	protected String l2s(Long l) {
		return ConverterUtils.l2s(l);
	}

}
