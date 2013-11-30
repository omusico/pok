package com.onmyway.factory;

import java.util.Map;

import util.JStringToolkit;

/**
 * @Title:四星直选
 * @Description: 千位百位十位个位有值
 * 【玩法：】
 *  复式:每行至少选1个号码，奖金100元
 *  单式:从0000~9999中选一个二位数为一个投注号码，奖金100元
 *  【投注：】
 *  复式:[四星复式]-,2,3,1,23(？注）
 *  单式:[四星单式]-,4,4,1,2(？注）
 * @Create on: Aug 10, 2010 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSixingZhixuanAnalyse extends PlayNumAnalyseFactory{
	SscSixingZhixuanAnalyse sse;
	public static SscSixingZhixuanAnalyse getInstance(){
		return new SscSixingZhixuanAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * return "1234,"
	 */
	@Override
	public String dansi(String playNum) {
		if(playNum == null){
			return null;
		}
		//截取
		if(playNum.indexOf("-") > -1){
			playNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
		}
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return touzhu + ",";
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 复式
	 * 先截取，再进行分解
	 * @return "1234,2345,"
	 */
	@Override
	public String fusi(String playNum) {
		if(playNum == null){
			return null;
		}
		//截取
		if(playNum.indexOf("-") > -1){
			playNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
		}
		
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return touzhu + ","; 
		
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
