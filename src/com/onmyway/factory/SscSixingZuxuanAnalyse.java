package com.onmyway.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.JStringToolkit;
import util.PailieArith;

/**
 * @Title:四星组选
 * @Description: 千位百位十位个位有值
 * @Create on: April 3, 2011 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSixingZuxuanAnalyse extends PlayNumAnalyseFactory{
	SscSixingZuxuanAnalyse sse;
	public static SscSixingZuxuanAnalyse getInstance(){
		return new SscSixingZuxuanAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String dansi(String playNum) {
		 
		return null;
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fusi(String playNum) {
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return  touzhu + ","; 
		
	}

	@Override
	public  Map<String,String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String zuxuan(String playNum){
		return null;
	}
	
	public String zuxuan(String playNum,int numGeshu) {
		int geshu = numGeshu;
		if (playNum == null) {
			return null;
		}
		
		// 将其分割，分割成一维数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		List<String> list = new ArrayList<String>();
		PailieArith pl = new PailieArith();
		pl.permutation(list,intAry, geshu);

		String result = "";
 
		Set<String> set = new HashSet<String>();
		for (String str : list) {
			set.add(str);
		}
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			result = result + it.next() + ",";//后加分隔符
		}
		return result;
	}
	/**
	 * 四星组选二十四
	 * 如果一注号码的四个数字各不相同，则有24种不同的排列方式，有24个中奖机会，这种投注方式称为“四星组选二十四”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan24(String playNum){
		int geshu = 4;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 四星组选十二：
	 * 如果一注号码的四个数字中有两个数字相同，其它的均不同，即“二重二单”相组合，则有12种不同的排列方式，有12个中奖机会，
	 * 这种投注方式称为“四星组选十二”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan12(String playNum){
		int geshu = 4;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 四星组选六
	 * 如果一注号码的四个数字中有两对两两相同，即“二重二重”相组合，则有6种不同的排列方式，有6个中奖机会，这种投注方式称为“四星组选六”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan6(String playNum){
		int geshu = 4;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 四星组选四：
	 * 如果一注号码的四个数字中有三个数字相同，剩余一个不同，即“三重一单”相组合，则有4种不同的排列方式，有4个中奖机会，这种方式称为“四星组选四”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan4(String playNum){
		int geshu = 4;
		String result = zuxuan(playNum,geshu);
		return result;
	}
}
