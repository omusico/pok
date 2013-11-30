package com.onmyway.factory;

import java.util.Map;

import junit.framework.TestCase;
import util.JStringToolkit;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 12, 2010 11:29:20 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSanxingZhixuanAnalyseTest extends TestCase {
	SscSanxingZhixuanAnalyse sz;
	protected void setUp() throws Exception {
		sz = new SscSanxingZhixuanAnalyse();
	}
	/**
	 * 组和复式算法
	 */
	public void notestZuheFushi(){
		//int[] a = {1,2,3,4};
		String playNum = "2,3,4";
		print(sz.zuheFushi(playNum));
		//assertEquals(12,JStringToolkit.splitString(sz.zu3(playNum),"|").length);
		
	}
	/**
	 *单式
	 */
	public void notestDansi(){
		//int[] a = {1,2,3,4};
		String playNum = "232,443,455";
		print(sz.dansi(playNum));
		//assertEquals(12,JStringToolkit.splitString(sz.zu3(playNum),"|").length);
		
	}
	/**
	 *单式
	 */
	public void testFusi(){
		//int[] a = {1,2,3,4};
		String playNum = "26,345,8";
		print(sz.fusi(playNum));
		//assertEquals(12,JStringToolkit.splitString(sz.zu3(playNum),"|").length);
		
	}
	
	
	protected void tearDown() throws Exception {
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
	private void print(int obj){
		System.out.println(obj);
	}
}
