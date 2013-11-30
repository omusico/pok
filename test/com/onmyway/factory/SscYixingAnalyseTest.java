package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscYixingAnalyseTest extends TestCase {

	SscYixingAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscYixingAnalyse();
	}

	public void testFushi(){
		//String playNum = "-,-,-,-,1234";
		String playNum = "1234";
		print(sse.fusi(playNum));
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
