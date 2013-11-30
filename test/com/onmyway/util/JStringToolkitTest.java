package com.onmyway.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import util.JStringToolkit;

import com.onmyway.factory.SscRenxuanAnalyse;

/**
 * @Title:
 * @Description: 
 * @Create on: Sep 19, 2010 9:03:40 AM
 * @Author:LJY
 * @Version:1.0
 */
public class JStringToolkitTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	
	}
	public void notestInsert(){
		String str = "12345";
		//String str = "1,2,3,4,5";
		String oldDelimiter = "";
		String delimiter = ",";
		print(JStringToolkit.insertDelimiter(str, oldDelimiter, delimiter));
	}
	public void notestSum(){
		String tempNumDetail = "|5,6,7,8,9|5,7,8,9,11|6,7,8,9,11|5,7,8,9,12|6,7,8,9,12|7,8,9,11,12|";
		String wuzhong5 = "5,6,7,8,9";
		int ttt = JStringToolkit.getSubNum(tempNumDetail,"|"+wuzhong5+"|");
		print("::::::"+ttt);
	}
	public void notestPaixu(){
		String[] str = {"2","2","2"};
		//String str = "1,2,3,4,5";
		String oldDelimiter = "";
		String delimiter = ",";
		print(JStringToolkit.strSort(str));
	}
	public void notestIntOrder(){
		int[] array = {9,8,5,10,7};
		for(int k : array){
			print(k);
		}
		System.out.println("");
		JStringToolkit.intOrder(array, 1);
		for(int k : array){
			print(k);
		}
		System.out.println("");
	}
	public void notestInteger(){
		String s = "201009240001";
		print(s.substring(2,s.length()));
		int t = 2010033333;
		int tt = 1009240001;
	}
	public void notestString(){
		String s = "12345";
		print(s.substring(0,3));
		print(s.substring(2,5));
	}
	public void notestCishu(){
		String a = ",12345,45231,12789,12345,";
		
		String wuxing = ",12345,";
		String qian3 = ",123";
		String hou3 = "345,";
		String qian2 = ",12";
		String hou2 = "45,";
		print("wuxing:"+JStringToolkit.getSubNum(a, wuxing));
		print("qian3:"+JStringToolkit.getSubNum(a, qian3));
		print("hou3:"+JStringToolkit.getSubNum(a, hou3));
		print("qian2:"+JStringToolkit.getSubNum(a, qian2));
		print("hou2:"+JStringToolkit.getSubNum(a, hou2));
	}
	public void notestSort(){
		String[] s = new String[]{"5","9","3","6"};
		String str = JStringToolkit.strSort(s, ",");
		print(str);
	}
	public void notestMap(){
		Map<String,String> m = new HashMap<String,String>();
		m.put("a", "a");
		System.out.println(m.get("b"));
	}
	
	
	public void notestYu(){
		float a = 7.3f;
		int b = 9;
		System.out.println("a%b="+a%b);
		System.out.println("a/b="+a/b);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
	private void print(int obj){
		System.out.print(obj+",");
	}
	
	public void notestSplitString(){
		print("-----------testSplitString-----------");
//		String a = "8,-,-,65,9";
		String a = "8,-,-,67,-";
		String[][] t = JStringToolkit.splitString(a, ",", "",false);
		
		for(int i = 0 ;i < t.length;i++){
			for(int j = 0; j < t[i].length; j++){
				//System.out.print(t[i][j]+",");
			}
			System.out.println("");
		}
		String x = JStringToolkit.combAryStr(t, ",");
		//print(x);
		String y = JStringToolkit.combAryStr(t, ",","-");
		//print(y);
	}
	public void notestRx2(){
		SscRenxuanAnalyse sa = new SscRenxuanAnalyse();
		String playNum = "-,96,3,5,0";
		String a = sa.renxuan2(playNum);
		//print(a);
	}
	public void notestRx3(){
		SscRenxuanAnalyse sa = new SscRenxuanAnalyse();
		String playNum = "1,58,39,-,0";
		print("***********" + playNum);
		String a = sa.renxuan3(playNum);
		print(a);
	}
	public void notestSplitEveryCharacter(){
		//SscRenxuanAnalyse sa = new SscRenxuanAnalyse();
		String playNum = "6--8-";
		String[] a = JStringToolkit.splitEveryCharacter(playNum);
		for(String x : a){
			//print(x);
			
		}
	}
	public void testSplitStr(){
		
		String str = "1,13,12,8,6,11,15,3";
		
		String[] a = JStringToolkit.splitStringByCount(str, ",", 2);
		for(String s : a){
			print(s);
		}
	}
	public void testOrderStr(){
		String str = "1,12,3,14,5";
		String res = JStringToolkit.strSort(str, ",");
		print(res);
	}
}
