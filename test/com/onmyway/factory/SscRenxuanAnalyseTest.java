package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscRenxuanAnalyseTest extends TestCase {

	SscRenxuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscRenxuanAnalyse();
	}

	public void testRenxuan1(){
		String playNum = "1,2,3,4,5";
		print(sse.renxuan1(playNum));
	}
	public void testRenxuan2(){

		String playNum = "1,2,3,4,5";
		print(sse.renxuan2(playNum));
	}
	public void testRenxuan3(){

		String playNum = "1,2,3,4,5";
		print(sse.renxuan3(playNum));
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
