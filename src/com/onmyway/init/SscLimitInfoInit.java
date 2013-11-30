package com.onmyway.init;

import java.util.Map;

import com.onmyway.ssc.manage.service.ISscManageService;

/**
 * @Title:时时彩-初始化限号信息
 * @Description: 初始化后，放在Map对象中，供以后使用
 * @Create on: Sep 8, 2010 9:40:46 AM
 * @Author:LJY
 * @Version:1.0
 */
//public class SscLimitInfoInit implements InitializingBean{
public class SscLimitInfoInit{

	public static Map<String,String> sscLimitMap;//限号
	public static Map<String,String> sscPrizeMap;//奖金
	private ISscManageService slService;//= new SscMoneyLimitDaoImpl();

	public ISscManageService getSscManageService() {
		return slService;
	}

	public void setSscManageService(ISscManageService sscManageService) {
		this.slService = sscManageService;
	}
	
	 
//	public void afterPropertiesSet(){
//		sscLimitMap = slService.getLimitInfoMap();
//		Iterator<String> it = sscLimitMap.keySet().iterator();
//		while(it.hasNext()){
//			String key = it.next();
//			System.out.println(key + ":" + sscLimitMap.get(key));
//		}
//	}

	public static Map<String, String> getSscLimitMap() {
		return sscLimitMap;
	}

	public void setSscLimitMap() {
		sscLimitMap = slService.getLimitInfoMap();		
	}
	
	public static Map<String, String> getSscPrizeMap() {
		return sscPrizeMap;
	}

	public void setSscPrizeMap() {
		sscPrizeMap = slService.getPrizeInfoMap();		
	}
	/**
	 * 初始化map
	 */
	public void initMap(){
		setSscLimitMap();
		setSscPrizeMap();
	}
}
