package com.onmyway.factory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

/**
 * @Title:五星组合
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscWuxingZuheAnalyseTest extends TestCase {

	SscWuxingZuxuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscWuxingZuxuanAnalyse();
	}

	public void notestFushi(){
		String playNum = "678,2,3,12,234";
		print(sse.fusi(playNum));
	}
	public void notestDanshi(){
		String playNum = "67890,21345";
		print(sse.dansi(playNum));
	}
	public void testZuhe120(){
		long start = System.currentTimeMillis();
		String playNum = "6,3,5,8,7";
		String result = sse.zuxuan(playNum,5);
		long end = System.currentTimeMillis();
		print("120计算时间:"+(end-start)+",result="+result);
	}
	public void testZuhe60(){
		long start = System.currentTimeMillis();
		String playNum = "6,3,5,6,7";
		String result = sse.zuxuan(playNum,5);
		long end = System.currentTimeMillis();
		print("60计算时间:"+(end-start)+",result="+result);
	}
	public void testZuhe30(){
		long start = System.currentTimeMillis();
		String playNum = "6,3,3,6,7";
		String result = sse.zuxuan(playNum,5);
		long end = System.currentTimeMillis();
		print("30计算时间:"+(end-start)+",result="+result);
	}
	public void testZuhe20(){
		long start = System.currentTimeMillis();
		String playNum = "6,3,3,3,7";
		String result = sse.zuxuan(playNum,5);
		long end = System.currentTimeMillis();
		print("20计算时间:"+(end-start)+",result="+result);
	}
	public void testZuhe10(){
		long start = System.currentTimeMillis();
		String playNum = "6,3,6,3,3";
		String result = sse.zuxuan(playNum,5);
		long end = System.currentTimeMillis();
		print("10计算时间:"+(end-start)+",result="+result);
	}
	public void testZuhe5(){
		long start = System.currentTimeMillis();
		String playNum = "3,3,6,3,3";
		String result = sse.zuxuan(playNum,5);
		long end = System.currentTimeMillis();
		print("5计算时间:"+(end-start)+",result="+result);
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
