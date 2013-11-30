package com.onmyway.factory;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Title:投注号码分析 
 * @Description: 按照相应的玩法及方式，对已投注的号码进行分析，得出所有的下注号码
 * @Create on: Aug 10, 2010 10:45:47 AM
 * @Author:LJY
 * @Version:1.0
 */
public abstract class PlayNumAnalyseFactory {
	protected final Log logger = LogFactory.getLog(getClass());
	/**
	 * 复式投注玩法
	 * @param playNum 下注号码
	 * @return
	 */
	abstract public String fusi(String playNum);
	/**
	 * 单式投注玩法
	 * @param playNum 下注号码
	 * @return
	 */
	abstract public String dansi(String playNum);
	/**
	 * 组选玩法
	 * @param playNum 下注号码
	 * @return
	 */
	abstract public String zuxuan(String playNum);
	
	/**
	 * 胆拖玩法
	 * @param playNum 下注号码
	 * @return
	 */
	abstract public String dantuo(String playNum);
	
	/**
	 * 包胆玩法
	 * @param playNum 下注号码
	 * @return
	 */
	abstract public String baodan(String playNum);
	/**
	 * 和值玩法
	 * 和值中包含：直选(豹子）:号码全中；三星时有：组3，组6；二星时有：其他
	 * @param playNum 下注号码
	 * @return
	 */
	abstract public Map<String,String> hezhi(String playNum);
	
	
}
