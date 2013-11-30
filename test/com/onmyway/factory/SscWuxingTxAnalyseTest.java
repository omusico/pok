package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscWuxingTxAnalyseTest extends TestCase {

	SscWuxingTongxuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscWuxingTongxuanAnalyse();
	}

	public void testFushi(){
		String playNum = "1,2,3,12,234";
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
