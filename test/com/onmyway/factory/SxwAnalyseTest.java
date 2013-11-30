package com.onmyway.factory;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwAnalyseTest extends TestCase {

	SxwAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SxwAnalyse();
	}

	public void testRx1(){
		print("----任选1----");
		String playNum = "2,3,6,7,10,12";
		String playType = "renxuan1";
		print(sse.sxwRenxuan(playNum, playType));
	}
	public void testRx2(){
		print("----任选2----");
		String playNum = "2,3,6,7,10,12";
		String playType = "renxuan2";
		print(sse.sxwRenxuan(playNum, playType));
	}
	public void testRxZhi2(){
		print("----任选直选前2----");
		String playNum = "2,7,10,11";
		String playType = "renxuan2";
		String playMode = "zhiqian2";
		print(sse.sxwRenxuan(playNum, playType,playMode));
	}
	public void testRxZu2(){
		print("----任选组选前2----");
		String playNum = "2,7,10,11";
		String playType = "renxuan2";
		String playMode = "zuqian2";
		print(sse.sxwRenxuan(playNum, playType,playMode));
	}
	public void testRx3(){
		print("----任选3----");
		String playNum = "2,3,6,7,10,12";
		String playType = "renxuan3";
		print(sse.sxwRenxuan(playNum, playType));
	}
	public void testRxZhi3(){
		print("----任选直选前3----");
		String playNum = "2,7,10,11";
		String playType = "renxuan3";
		String playMode = "zhiqian3";
		print(sse.sxwRenxuan(playNum, playType,playMode));
	}
	public void testRxZu3(){
		print("----任选组选前3----");
		String playNum = "2,7,10,11";
		String playType = "renxuan3";
		String playMode = "zuqian3";
		print(sse.sxwRenxuan(playNum, playType,playMode));
	}
	public void testRx4(){
		print("----任选4----");
		String playNum = "2,3,6,7,10,12";
		String playType = "renxuan4";
		print(sse.sxwRenxuan(playNum, playType));
	}
	public void testRx5(){
		print("----任选5----");
		String playNum = "2,3,6,7,10,12";
		String playType = "renxuan5";
		print(sse.sxwRenxuan(playNum, playType));
	}
	
	public void testSxwDantuo2(){
		print("----任选2胆拖----");
		String playNum = "胆9-拖3,7,10,12,6";
		String playType = "renxuan2";
		print(sse.sxwDantuo(playNum, playType));
	}
	public void testSxwDantuo3(){
		print("----任选3胆拖----");
		String playNum = "胆9,8-拖3,7,10,12,6";
		String playType = "renxuan3";
		print(sse.sxwDantuo(playNum, playType));
	}
	public void testSxwDantuo4(){
		print("----任选4胆拖----");
		String playNum = "胆9,5,11-拖5,7,10,12";
		String playType = "renxuan4";
		print(sse.sxwDantuo(playNum, playType));
	}
	public void testSxwDantuo5(){
		print("----任选5胆拖----");
		String playNum = "胆1,2,8,11-拖3,7,10,12,6";
		String playType = "renxuan5";
		print(sse.sxwDantuo(playNum, playType));
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
