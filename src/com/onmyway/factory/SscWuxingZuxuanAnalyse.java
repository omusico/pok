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
 * @Title:五星选选
 * @Create on: Aug 10, 2010 5:42:09 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscWuxingZuxuanAnalyse extends PlayNumAnalyseFactory{

	SscWuxingZuxuanAnalyse sse;
	public static SscWuxingZuxuanAnalyse getInstance(){
		return new SscWuxingZuxuanAnalyse();
	}
	
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 单式，只有一注
	 * @return "12345,"
	 */
	@Override
	public String dansi(String playNum) {
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		//return  touzhu + ","; 
		return  touzhu + ",";
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 复式
	 * playNum：格式，[五星通选复式]1,12,34,5,6
	 * 必须选中五个位置的号码，每个位置号码可以多选
	 * return："12312,12313,12314,12322,12323,12324,"
	 */
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
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 五星组选一百二十：如果一注号码的五个数字各不相同，则有120种不同的排列方式，有120个中奖机会，这种投注方式称为“五星组选一百二十”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan(String playNum,int numGeshu){
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
	 * 五星组选一百二十:6,3,5,8,7
	 * 如果一注号码的五个数字各不相同，则有120种不同的排列方式，有120个中奖机会，这种投注方式称为“五星组选一百二十”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan120(String playNum){
		int geshu = 5;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	
	/**
	 * 五星组选六十:6,3,5,6,7
	 * 如果一注号码的五个数字中有两个数字相同，其它的均不同，即“二重三单”相组合，则有60种不同的排列方式，有60个中奖机会，这种投注方式称为“五星组选六十”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan60(String playNum){
		int geshu = 5;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 五星组选三十:6,3,3,6,7
	 * 如果一注号码的五个数字中有两对两两相同，剩余的一个不同，即“二重二重一单”相组合，则有30种不同的排列方式，
	 * 有30个中奖机会，这种投注方式称为“五星组选三十”。	 
	 * @param playNum
	 * @return
	 */
	public String zuxuan30(String playNum){
		int geshu = 5;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 五星组选二十:6,3,3,3,7
	 * 如果一注号码的五个数字中有三个数字相同，其它的均不同，即“三重二单”相组合，则有20种不同的排列方式，有20个中奖机会，
	 * 这种投注方式称为“五星组选二十”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan20(String playNum){
		int geshu = 5;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 五星组选十:6,3,6,3,3
	 * 如果一注号码的五个数字中有三个数字相同，
	 * 剩余的两个数字两两相同，即“三重二重”相组合，则有10种不同的排列方式，有10个中奖机会，这种投注方式称为“五星组选十”。
 	 * @param playNum
	 * @return
	 */
	public String zuxuan10(String playNum){
		int geshu = 5;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 五星组选五:3,3,6,3,3
	 * 如果一注号码的五个数字中有四个数字相同，剩余的一个不同，即“四重一单”相组合，则有5种不同的排列方式，有5个中奖机会，
	 * 这种投注方式称为“五星组选五”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan5(String playNum){
		int geshu = 5;
		String result = zuxuan(playNum,geshu);
		return result;
	}
	/**
	 * 五星组选两重号全包
	 * 如果从0-9中任选一个数字进行两重号全包投注，即符合任意两个或两个以上位置的数字与之相符的组合形式，
	 * 则有8146种不同的排列方式，有8146个中奖机会，这种投注方式称为“五星组选两重号全包”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan2Chong(String playNum){
		
		return null;
	}
	/**
	 * 五星组选三重号全包
	 * 如果从0-9中任选一个数字进行三重号全包投注，即符合任意三个或三个以上位置的数字与之相符的组合形式，则有856种不同的排列方式，
	 * 有856个中奖机会，这种投注方式称为“五星组选三重号全包”。
	 * @param playNum
	 * @return
	 */
	public String zuxuan3Chong(String playNum){
		
		return null;
	}
	 
}
