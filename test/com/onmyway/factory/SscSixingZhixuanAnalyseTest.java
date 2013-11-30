package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSixingZhixuanAnalyseTest extends TestCase {

	SscSixingZhixuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscSixingZhixuanAnalyse();
	}

	public void notestFushi(){
		String playNum = "1,2,3,34";
		print(sse.fusi(playNum));
	}
	public void testDanshi(){
		String playNum = "1234,2556,3890";
		print(sse.dansi(playNum));
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
