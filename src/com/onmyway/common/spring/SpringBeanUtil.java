package com.onmyway.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.onmyway.common.LogHelper;
 

 
public class SpringBeanUtil {
	
    private static LogHelper log = new LogHelper(SpringBeanUtil.class);
    
    public static Object getBean(String name){
        ApplicationContext context = ContextHolder.getInstance().getApplicationContext();
        try {
            return context.getBean(name);
        } catch (BeansException ex) {
        	log.exceptionLog().error("get bean failed",ex);
            return null;
        }
    }

    public static Object getBean(String name,Class clazz){
        ApplicationContext context = ContextHolder.getInstance().getApplicationContext();
        try {
            return context.getBean(name, clazz);
        } catch (BeansException ex) {
            log.exceptionLog().error("get bean failed",ex);
            return null;
        }
    }
}
