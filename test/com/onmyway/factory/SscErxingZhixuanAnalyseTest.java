package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscErxingZhixuanAnalyseTest extends TestCase {

	SscErxingZhixuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscErxingZhixuanAnalyse();
	}

	public void testFushi(){
		print("----二星复式----");
		String playNum = "52,234";
		print(sse.fusi(playNum));
	}
	public void testDanshi(){
		print("----二星单式----");
		String playNum = "5,2";
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
