package com.onmyway.util;

import junit.framework.TestCase;
import util.JStringToolkit;

/**
 * @Title:
 * @Description: 
 * @Create on: Feb 28, 2011 5:53:51 PM
 * @Author:LJY
 * @Version:1.0
 */
public class StringTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	
	} 
	
	public void notestSubString(){
		String str = " aaa <span style='font-size: 0pt;color:'#FFFFFF'' >  iG]zib@ </span> bbb<br>";
		String result = "";
		System.out.println(str.indexOf("span  style='font-size: 0pt;color:'#FFFFFF''"));
		while(str.indexOf("span  style='font-size: 0pt;color:'#FFFFFF'") != -1){
			int index = str.indexOf("<span  style='font-size: 0pt;color:'#FFFFFF'' >");
			String head = str.substring(0,index-1);
			String tempLast = str.substring(index,str.length());
			int lastIndex = str.indexOf("</span>");
			String last = tempLast.substring(lastIndex,tempLast.length());
			
			str = head + last;
		}
		System.out.println(str);
	}
	public void notestGetSubString(){
		String a = "3,";
		String b = "3,";
		long start=System.currentTimeMillis();
		System.out.println(JStringToolkit.getSubNum(a,b));
		long end1 = System.currentTimeMillis();
		System.out.println("用时:"+(end1-start));
		long end2 = System.currentTimeMillis();
		System.out.println(JStringToolkit.getSubNum_old(a,b));
		long end3 = System.currentTimeMillis();
		System.out.println("用时:"+(end3-end2));
	}
	public void notestTwoIntAry(){
		int[] a = {1,2,3};
		int[] b = {5,6,7,8};
		int[] x = new int[a.length + b.length];
		for(int i = 0; i < a.length; i++){
			x[i] = a[i];
		}
		for(int i = a.length; i < x.length; i++){
			x[i] = b[i-a.length];
		}
		for(int i : x){
			System.out.print(i);
		}
	}
	public void testSplitString(){
		String str = "1,2,3|2,4,5|6,7,9";
		String limitStr1 = "|";
		String limitStr2 = ",";
		boolean flag = true;
		String[][] ary = JStringToolkit.splitString(str, limitStr1, limitStr2,flag);
		
		for(int i = 0; i < ary.length; i++){
			for(int j = 0; j < ary[i].length; j++){
				System.out.print(ary[i][j] + ",");
			}
			System.out.print("\n");
		}
	}
}
