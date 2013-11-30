package com.onmyway.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JStringToolkit;
import util.PailieArith;

/**
 * @Title:三星直选
 * 有 复式  组合复式  单式 三种 * 
 * @Description:组合复式：可从0-9中选择M（M>=3）个不重复数字组合成P（3，M）注不含对子和豹子的单式投注。 
 * 【玩法：】
 * 复式：每行至少选1个号码，奖金1000元
 * 组合复式：任选M（M≥3）个号码组合（投注号码不含对子和豹子），奖金1000元
 * 单式：从000~999中选一个三位数为一个投注号码，奖金1000元。
 * 【投注：】
 * 复式：[三星复式]-,-,1,2,34(2注）
 * 组合复式：[三星直选组合]2,3,4（6注）
 * 单式：[三星复式]-,-,1,2,3（1注）
 * @Create on: Aug 10, 2010 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSanxingZhixuanAnalyse extends PlayNumAnalyseFactory{
	SscSanxingZhixuanAnalyse sse;
	public static SscSanxingZhixuanAnalyse getInstance(){
		return new SscSanxingZhixuanAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * return "123,"
	 */
	@Override
	public String dansi(String playNum) {
		// TODO Auto-generated method stub
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return  touzhu + ",";
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [三星复式]-,-,1,2,34(2注）
	 * 先进行处理，去除第一第二位的“-”
	 * return "123,678,"
	 */
	@Override
	public String fusi(String playNum) {
		if(playNum == null){
			return null;
		}
		//截取百位十位个位
		playNum = playNum.substring(playNum.lastIndexOf("-")+1,playNum.length());
		
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return  touzhu + ",";
	}

	@Override
	public  Map<String,String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 组合复式：可从0-9中选择M（M>=3）个不重复数字组合成P（3，M）注不含对子和豹子的单式投注。 
	 * 组合复式：[三星直选组合]2,3,4（6注）
	 */
	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param playNum
	 * @return "123,456,"
	 */
	public String zuheFushi(String playNum) {
		int geshu = 3;//需要对给定数中的3个数进行排列组合
		if (playNum == null) {
			return null;
		}

		// 将其分割，分割成一维数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		List<String> list = new ArrayList<String>();
		PailieArith pl = new PailieArith();
		 pl.permutation(list,intAry, geshu);
		String result = "";

		for (String str : list) {
//			String temp = str + str.substring(str.lastIndexOf(","), str.length());
			String temp = str;
//			if (result.equals("")) {
//				result = result + temp;
//			} else {
//				result = result + "," + temp;
//			}
			result = result+ temp + "," ;
		}
		return result;
	}
}
