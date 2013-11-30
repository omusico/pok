package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSixingZuxuanAnalyseTest extends TestCase {

	SscSixingZuxuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscSixingZuxuanAnalyse();
	}

	public void notestFushi(){
		String playNum = "1,2,3,34";
		print(sse.fusi(playNum));
	}
	public void testZuxuan24(){
		String playNum = "3,2,5,1";
		long start = System.currentTimeMillis();
		String result = sse.zuxuan24(playNum);
		
		long end = System.currentTimeMillis();
		print("24计算时间:"+(end-start)+",result="+result);
	}
	public void testZuxuan12(){
		String playNum = "3,2,5,2";
		long start = System.currentTimeMillis();
		String result = sse.zuxuan12(playNum);
		
		long end = System.currentTimeMillis();
		print("12计算时间:"+(end-start)+",result="+result);
	}
	public void testZuxuan6(){
		String playNum = "5,2,2,5";
		long start = System.currentTimeMillis();
		String result = sse.zuxuan6(playNum);
		
		long end = System.currentTimeMillis();
		print("6计算时间:"+(end-start)+",result="+result);
	}
	public void testZuxuan4(){
		String playNum = "3,3,5,3";
		long start = System.currentTimeMillis();
		String result = sse.zuxuan4(playNum);
		
		long end = System.currentTimeMillis();
		print("4计算时间:"+(end-start)+",result="+result);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
	private void print(int ii){
		System.out.println(ii);
	}
}
