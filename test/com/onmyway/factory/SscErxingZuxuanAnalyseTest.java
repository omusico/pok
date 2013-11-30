package com.onmyway.factory;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscErxingZuxuanAnalyseTest extends TestCase {

	SscErxingZuxuanAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscErxingZuxuanAnalyse();
	}
	/**
	 * 2星复选
	 */
	public void notestFushi(){
		String playNum = "2,3,8,9";
		print(sse.fusi(playNum));
	}
	/**
	 * 单式
	 */
	public void notestDanshi(){
		String playNum = "53,33,89";
		String temp = sse.dansi(playNum);
		print22(temp);
	}
	/**
	 * 2星和值
	 */
	public void notestHezhi(){
		String playNum = "3";
		//sse.hezhi(playNum);
		//print(sse.fusi(playNum));
		Map<String,String> map = sse.hezhi(playNum);
		print(map.get("hezhiXiangtong"));
		print(map.get("hezhiButong"));
		
	}
	/**
	 * 组选：和值测试
	 */
	public void testHezhiList(){
		String playNum = "2";
//		String result = sz.baodan2(playNum);
		List<PlayNumInfo>  resultList  = sse.hezhiList(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	/**
	 * 2星和值
	 */
	public void notestHezhiList(){
		String playNum = "6";
		//sse.hezhi(playNum);
		//print(sse.fusi(playNum));
	
		List<PlayNumInfo>  resultList  = sse.hezhiList(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
	private void print22(String obj){
		System.out.println(obj);
	}
	private void print(int ii){
		System.out.println(ii);
	}
}
