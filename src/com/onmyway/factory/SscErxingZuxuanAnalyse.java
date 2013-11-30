package com.onmyway.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JStringToolkit;
import util.ZuheArith;

/**
 * @Title:二星
 * @Description:
 * 【玩法：】
 * 复式：从0~9任意选择2个或以上的号码，奖金50元。（复式不包含对子 ）
 * 单式：从00~99中选一个二位数为一个投注号码，奖金50元
 * 和值：任意选择号码投注，奖金50元（对子奖金100元）
 * 【投注：】
 * 复式：[二星组选复式]1,2,3(3注）
 * 单式：[二星组选单式]1,2(1注）
 * 和值：[二星组选和值]6(4注） 
 * @Create on: Aug 10, 2010 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscErxingZuxuanAnalyse extends PlayNumAnalyseFactory{
	SscErxingZuxuanAnalyse sse;
	public static SscErxingZuxuanAnalyse getInstance(){
		return new SscErxingZuxuanAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * return "12,"
	 */
	@Override
	public String dansi(String playNum) {
		 
		if(playNum == null ){
			return "";
		}
		String str = "";
		if(playNum.indexOf(",") != -1){
//			String[] temp = JStringToolkit.splitString(playNum, ",");
			String[] t = playNum.split(",");
			//for(String ss : temp){
				//str = str + ss;
				//String[] t = ss.split("");//每个值为两位
				//char[] t = ss.toCharArray();
				if(Integer.parseInt(String.valueOf(t[0])) < Integer.parseInt(String.valueOf(t[1]))){
					str = str + t[0] + t[1] + ",";
				}else{
					str = str + t[1] + t[0] + ",";
				}
				
			//}
		}else{
			//str = playNum;
			char[] t = playNum.toCharArray();
			if(Integer.parseInt(String.valueOf(t[0])) < Integer.parseInt(String.valueOf(t[1]))){
				str = str + t[0] + t[1] + ",";
			}else{
				str = str + t[1] + t[0] + ",";
			}
		}
		return str;
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 二星复式，组合，从选择的数中任选2个进行组合
	 * return "28,91,"
	 */
	@Override
	public String fusi(String playNum) {

		if(playNum == null){
			return null;
		}
		int geshu = 2;//从选择的数中任选2个进行组合
		int intAry[] = JStringToolkit.splitStrToInt(playNum,",");
		// 进行组合运算
		ZuheArith za = new ZuheArith();
		List list = za.combine(intAry, geshu);
		// 整理结果
		String result = "";

		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			String temp = "";
			for (int m : t) {
				temp += m + "";
			}
//			if (!temp.equals("")) {
//				temp = temp.substring(0, temp.length() - 1);
//			}
//			if (result.equals("")) {
//				result = result + temp;
//			} else {
//				result = result + "," + temp;
//			}
				result = result+ temp + "," ;
		}
		return result;
		
	}
	/**
	 * 和值,分两种：两个都相同；两个各不相同
	 */
	@Override
	public  Map<String,String> hezhi(String playNum) {
		if(playNum == null){
			return null;
		}
		String result = "";
		String hezhiXiangtong = "";
		String hezhiButong = "";
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		for(int i = 0; i < intAry.length; i++){
			int num = intAry[i];
			//每次最大循环到9
			int tempNum = intAry[i];
			if(num > 9){
				tempNum = 9;
			}
//			for(int j = 0; j <= tempNum/2; j++){
			for(int j = 0; j <= tempNum; j++){
			
				for(int k = j; k <= tempNum; k++){
						String temp = "";
						if(j+k == num){
							temp = j + "," + k ;	
							//2个都相等 直选 豹子
							if(j == k ){
								hezhiXiangtong = hezhiXiangtong + temp + "|";
							}
							//两个不同，组3
							if(j != k ){
								hezhiButong = hezhiButong + temp + "|";
							}
//							//三个各不相等，组6
//							if((j != k && k != m && j != m)){
//								hezhiZu6 = hezhiZu6 + temp + "|";
//							}
							//result = result + temp + "|";
						}
					}
				}
			}
			
	
		if(!hezhiXiangtong.equals("")){
			hezhiXiangtong = hezhiXiangtong.substring(0,hezhiXiangtong.length()-1);
		}
		if(!hezhiButong.equals("")){
			hezhiButong = hezhiButong.substring(0,hezhiButong.length()-1);
		}
//		if(!hezhiZu6.equals("")){
//			hezhiZu6 = hezhiZu6.substring(0,hezhiZu6.length()-1);
//		}
		
//		System.out.println("hezhiXiangtong="+hezhiXiangtong);
//		System.out.println("hezhiButong="+hezhiButong);
	//	System.out.println("result="+result);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("hezhiXiangtong", hezhiXiangtong);
		map.put("hezhiButong", hezhiButong);

		return map;
	
	}
	public  List<PlayNumInfo> hezhiList(String playNum) {
		if(playNum == null){
			return null;
		}

		List<PlayNumInfo>  resultList = new ArrayList<PlayNumInfo>();
		
		String hezhiXiangtong = ",";
		String hezhiButong = ",";
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		for(int i = 0; i < intAry.length; i++){
			int num = intAry[i];
			//每次最大循环到9
			int tempNum = intAry[i];
			if(num > 9){
				tempNum = 9;
			}
//			for(int j = 0; j <= tempNum/2; j++){
			for(int j = 0; j <= tempNum; j++){
			
				for(int k = j; k <= tempNum; k++){
						String temp = "";
						if(j+k == num){
							temp = j + "" + k ;	
							//2个都相等 直选 豹子
							if(j == k ){
								hezhiXiangtong = hezhiXiangtong + temp + ",";
							}
							//两个不同，组3
							if(j != k ){
								hezhiButong = hezhiButong + temp + ",";
							}
//							//三个各不相等，组6
//							if((j != k && k != m && j != m)){
//								hezhiZu6 = hezhiZu6 + temp + "|";
//							}
							//result = result + temp + "|";
						}
					}
				}
			}
			
	
		if(!hezhiXiangtong.equals("")){
			hezhiXiangtong = hezhiXiangtong.substring(0,hezhiXiangtong.length()-1);
			//resultList.add(new PlayNumInfo("ssc","erxing","zuxuan","hezhi",hezhiXiangtong,"","1","1"));
		}
		if(!hezhiButong.equals("")){
			//hezhiButong = hezhiButong.substring(0,hezhiButong.length()-1);
			
		}
		String temp = hezhiXiangtong + hezhiButong;
		if(temp != null && !temp.equals(",")){
			resultList.add(new PlayNumInfo("ssc","erxing","hezhi","zuxuan",temp,"","1","1"));
		}
		return resultList;
	
	}
	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
