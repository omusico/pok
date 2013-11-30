package com.onmyway.init;

import java.util.Map;

import com.onmyway.exb.manage.service.IExbManageService;

/**
 * @Title:12x5-初始化限号信息
 * @Description: 初始化后，放在Map对象中，供以后使用
 * @Create on: Sep 8, 2010 9:40:46 AM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbLimitInfoInit{

	public static Map<String,Integer> exbLimitMap;//限号
	public static Map<String,Integer> exbPrizeMap;//奖金
	private IExbManageService slService;

	public IExbManageService getExbManageService() {
		return slService;
	}

	public void setExbManageService(IExbManageService exbManageService) {
		this.slService = exbManageService;
	}
	
	 
//	public void afterPropertiesSet(){
//		sscLimitMap = slService.getLimitInfoMap();
//		Iterator<String> it = sscLimitMap.keySet().iterator();
//		while(it.hasNext()){
//			String key = it.next();
//			System.out.println(key + ":" + sscLimitMap.get(key));
//		}
//	}

	public static Map<String, Integer> getExbLimitMap() {
		return exbLimitMap;
	}

	public void setExbLimitMap() {
		exbLimitMap = slService.getLimitInfoMap();		
	}
	
	public static Map<String, Integer> getExbPrizeMap() {
		return exbPrizeMap;
	}

	public void setExbPrizeMap() {
		exbPrizeMap = slService.getPrizeInfoMap();		
	}
	/**
	 * 初始化map
	 */
	public void initMap(){
		setExbLimitMap();
		setExbPrizeMap();
	}
}
