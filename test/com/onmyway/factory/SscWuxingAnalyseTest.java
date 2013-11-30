package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscWuxingAnalyseTest extends TestCase {

	SscRenxuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscRenxuanAnalyse();
	}

	public void testRenxuan1(){
		String playNum = "1,2,3,-,-";
		print(sse.renxuan1(playNum));
	}
	public void testRenxuan2(){
		String playNum = "67890,21345";
		//print(sse.dansi(playNum));
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
