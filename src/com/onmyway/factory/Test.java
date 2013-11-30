package com.onmyway.factory;

import util.JStringToolkit;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 10, 2010 1:08:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class Test {

	public void testComb(String[][] str,String limitStr){
		//JStringToolkit.combString(str,",");
		System.out.println(JStringToolkit.combAryStr(str,","));
	}
	public void testSplitStr(String str){
		String[][] ary = JStringToolkit.splitString(str,",",null,false);
		int len = ary.length;
		for(int i = 0; i < len; i++){
			int len2 = ary[i].length;
			for(int j = 0; j < len2; j++){
				System.out.print(ary[i][j]+"  ");
			}
			System.out.print("\n");
		}
	}
	public static void main(String[] args){
		Test test = new Test();
		String str = "12,7,34,5,68";
//		testSplitStr(str);
		String[][] ary = JStringToolkit.splitString(str,",",null,false);
		test.testComb(ary, ",");
	}
}
