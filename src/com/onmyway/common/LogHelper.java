package com.onmyway.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 
public class LogHelper {
	
	private Log log;
	/**
	 * 
	 * @param className 需要被做日志的类
	 */
	public LogHelper(Class className) {
		log = LogFactory.getLog(className);
	}
	/**
	 * 
	 * @param loggerName 配置文件中自定义的日志记录器名称
	 */
	public LogHelper(String loggerName) {
		log = LogFactory.getLog(loggerName);
		
	}
	public Log exceptionLog() {
		return log;
	}

	public void debug(String msg) {
		if (log.isDebugEnabled()) {
			log.debug(msg);
		}

	}
	public void info(String msg) {
		if (log.isInfoEnabled()) {
			log.info(msg);
		}

	}
	public void warn(String msg) {
		if (log.isWarnEnabled()) {
			log.warn(msg);
		}

	}
	public void error(String msg) {
		if (log.isErrorEnabled()) {
			log.error(msg);
		}
	}
	public void fatal(String msg) {
		if (log.isFatalEnabled()) {
			log.fatal(msg);
		}
	}
}
