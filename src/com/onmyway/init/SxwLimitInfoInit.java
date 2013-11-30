package com.onmyway.init;

import java.util.Map;

import com.onmyway.sxw.manage.service.ISxwManageService;

/**
 * @Title:12x5-初始化限号信息
 * @Description: 初始化后，放在Map对象中，供以后使用
 * @Create on: Sep 8, 2010 9:40:46 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwLimitInfoInit{

	public static Map<String,String> sxwLimitMap;//限号
	public static Map<String,String> sxwPrizeMap;//奖金
	private ISxwManageService slService;

	public ISxwManageService getSxwManageService() {
		return slService;
	}

	public void setSxwManageService(ISxwManageService sxwManageService) {
		this.slService = sxwManageService;
	}
	
	 
//	public void afterPropertiesSet(){
//		sscLimitMap = slService.getLimitInfoMap();
//		Iterator<String> it = sscLimitMap.keySet().iterator();
//		while(it.hasNext()){
//			String key = it.next();
//			System.out.println(key + ":" + sscLimitMap.get(key));
//		}
//	}

	public static Map<String, String> getSxwLimitMap() {
		return sxwLimitMap;
	}

	public void setSxwLimitMap() {
		sxwLimitMap = slService.getLimitInfoMap();		
	}
	
	public static Map<String, String> getSxwPrizeMap() {
		return sxwPrizeMap;
	}

	public void setSxwPrizeMap() {
		sxwPrizeMap = slService.getPrizeInfoMap();		
	}
	/**
	 * 初始化map
	 */
	public void initMap(){
		setSxwLimitMap();
		setSxwPrizeMap();
	}
}
