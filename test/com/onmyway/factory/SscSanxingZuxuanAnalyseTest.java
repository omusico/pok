package com.onmyway.factory;

import java.util.List;
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
public class SscSanxingZuxuanAnalyseTest extends TestCase {
	SscSanxingZuxuanAnalyse sz;
	protected void setUp() throws Exception {
		sz = new SscSanxingZuxuanAnalyse();
	}
	/**
	 * 组三算法
	 */
	public void notestZu3(){
		//int[] a = {1,2,3,4};
		String playNum = "2,3,4";
		print(sz.zu3(playNum));
		//assertEquals(12,JStringToolkit.splitString(sz.zu3(playNum),",").length);
//		String[] s = JStringToolkit.splitString(sz.zu3(playNum),",");
//		for(String t : s){
//			print(t);
//		}
	}
	
	/**
	 * 组6算法zu6
	 */
	public void notestZu6(){
		//int[] a = {1,2,3,4};
		String playNum = "2,3,4,5,6";
		print(sz.zu6(playNum));
		assertEquals(4,JStringToolkit.splitString(sz.zu6(playNum),",").length);
	}
	
//	public void testCharu(){
//		int[] a = {0,2,3,4};
//		int t = 5;
//		int[] aaa = sz.chaRu(x)t, a);
//		for(int m : aaa){
//			print(m);
//		}
//	}
	
	/**
	 * 组选：包一胆测试
	 */
	public void notestBaodan1(){
		String playNum = "3";
		Map<String,String> map = sz.baodan1(playNum);
//		assertEquals(1,JStringToolkit.splitString(map.get("baodan1$Zhixuan"),"|").length);
//		assertEquals(18,JStringToolkit.splitString(map.get("baodan1$Zu3"),"|").length);
//		assertEquals(36,JStringToolkit.splitString(map.get("baodan1$Zu6"),"|").length);
		print(map.get("baodan1$Zhixuan"));
		print(map.get("baodan1$Zu3"));
		print(map.get("baodan1$Zu6"));
		
	}
	
	public void notestBaodan1List(){
		String playNum = "1";
		List<PlayNumInfo>  resultList  = sz.baodan1List(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	public void notestBaodan2List(){
		String playNum = "2,2";
		List<PlayNumInfo>  resultList  = sz.baodan2List(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	public void notestHezhiList(){
		String playNum = "6";
		List<PlayNumInfo>  resultList  = sz.hezhiList(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	/**
	 * 组选：包二胆测试
	 */
	public void testBaodan2(){
		String playNum = "7,5";
//		String result = sz.baodan2(playNum);
		List<PlayNumInfo>  resultList  = sz.baodan2List(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	/**
	 * 和值测试
	 */
	public void notestHezhi(){
		String playNum = "8,7";
		Map<String,String> map = sz.hezhi(playNum);
		print(map.get("hezhiZhixuan"));
		print(map.get("hezhiZu3"));
		print(map.get("hezhiZu6"));
		
	}
	/**
	 * 组选：和值测试
	 */
	public void testHezhiList(){
		String playNum = "6,5";
//		String result = sz.baodan2(playNum);
		List<PlayNumInfo>  resultList  = sz.hezhiList(playNum);
//		print(result);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
	}
	/**
	 * 组选：三星组选单式
	 */
	public void testDanshiList(){
		String playNum = "888";
//		String result = sz.baodan2(playNum);
		List<PlayNumInfo>  resultList  = sz.danshiList(playNum);
		print("----------三星组选单式----------");
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
		
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
