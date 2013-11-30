package com.onmyway.factory;

import java.util.Map;

/**
 * @Title:一星
 * @Description: 复式
 * 【玩法：】
 * 从0~9任意选择1个号码为一个投注号码，奖金10元
 * 
 * 【投注：】
 * [一星复式]-,-,-,-,1234(4注）
 * @Create on: Aug 10, 2010 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscYixingAnalyse extends PlayNumAnalyseFactory{

	SscYixingAnalyse sse;
	public static SscYixingAnalyse getInstance(){
		return new SscYixingAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dansi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 一星复式
	 * 先进行字符串截取，再进行投注注数的分解
	 * return "1,2,3,4,"
	 */
	@Override
	public String fusi(String playNum) {
		if(playNum == null){
			return null;
		}
		String strNum = "";
		if(playNum.indexOf("-") != -1){
			strNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
		}else{
			strNum = playNum;
		}
		char[] temp = strNum.toCharArray();
		int[] t = new int[temp.length];
		for(int i = 0; i < temp.length; i ++){
			t[i] = Integer.parseInt(Character.toString(temp[i]));
		}
		String result = "";		
		for(int m : t){
			result = result + m + ",";
		}
//		if( !result.equals("")){
//			result = result.substring(0,result.length()-1);
//		}
		
		return result;
	}

	@Override
	public  Map<String,String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
