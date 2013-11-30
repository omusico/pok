package com.onmyway.factory;

import java.util.Map;

import util.JStringToolkit;

/**
 * @Title:二星直选
 * @Description: 只有十位个位有值
 * 【玩法：】
 *  复式:每行至少选1个号码，奖金100元
 *  单式:从00~99中选一个二位数为一个投注号码，奖金100元
 *  【投注：】
 *  复式:[二星复式]-,-,-,1,23(2注）
 *  单式:[二星单式]-,-,-,1,2(1注）
 * @Create on: Aug 10, 2010 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscErxingZhixuanAnalyse extends PlayNumAnalyseFactory{
	
	SscErxingZhixuanAnalyse sse;
	public static SscErxingZhixuanAnalyse getInstance(){
		return new SscErxingZhixuanAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * return "82,"
	 */
	@Override
	public String dansi(String playNum) {
		if(playNum == null ){
			return "";
		}
		String str = "";
		if(playNum.indexOf(",") != -1){
			String[] temp = playNum.split(",");
			for(String ss : temp){
				str = str + ss;
			}
		}else{
			str = playNum;
		}
		return str+","; 
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * return "23,45,"
	 */
	@Override
	public String fusi(String playNum) {
		if(playNum == null){
			return null;
		}
		//截取十位个位
		if(playNum.indexOf("-") != -1){
			playNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
		}
		
		
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return touzhu + ","; 
	}

	@Override
	public Map<String,String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
