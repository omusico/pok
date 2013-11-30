package com.onmyway.factory;

import java.util.List;

import junit.framework.TestCase;

/**
 * @Title:快乐十分
 * @Description: 
 * @Create on: Sep 17, 2010 3:21:49 PM
 * @Author:LJY
 * @Version:1.0
 */
public class ExbAnalyseTest extends TestCase {

	ExbAnalyse ex;
	protected void setUp() throws Exception {
		super.setUp();
		ex = new ExbAnalyse();
	}
	public void notestXuan1renxuan(){
		String tzNum = "1,2,8,11,19";
		String playMode = "shutou";
//		String result = ex.xuan2NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X1,playMode,tzNum);
		print("选1 任选:" + result);
	}
	public void notestXuan2renxuan(){
		String tzNum = "1,2,8,11,19";
		String playMode = "renxuan";
//		String result = ex.xuan2NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X2,playMode,tzNum);
		print("选2 任选:" + result);
	}

	public void notestXuan2renxuandantuo(){
		String tzNum = "胆3-拖1,5,18";
		String playMode = "renxuandantuo";
//		String result = ex.xuan2NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X2,playMode,tzNum);
		print("选2 任选胆拖:" + result);
	}
	public void notestXuan2lianzhi(){
		String tzNum = "1,11,19|3,4,6";
		String playMode = "lianzhi";
//		String result = ex.xuan2NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X2,playMode,tzNum);
		print("选2 连直:" + result);
	}
	public void notestXuan2lianzu(){
		String tzNum = "1,8,11,19";
		String playMode = "lianzu";
//		String result = ex.xuan2NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X2,playMode,tzNum);
		print("选2 连组:" + result);
	}
	public void notestXuan2lianzudantuo(){
		String tzNum = "胆3-拖1,6,18";
		String playMode = "lianzudantuo";
//		String result = ex.xuan2NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X2,playMode,tzNum);
		print("选2 连组胆拖:" + result);
	}
	//*****************************xuan 3************
	public void notestXuan3renxuan(){
		String tzNum = "1,2,8,11,19";
		String playMode = "renxuan";
//		String result = ex.xuan3NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X3,playMode,tzNum);
		print("选3 任选:" + result);
	}

	public void notestXuan3renxuandantuo(){
		String tzNum = "胆3-拖1,5,18";
		String playMode = "renxuandantuo";
//		String result = ex.xuan3NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X3,playMode,tzNum);
		print("选3 任选胆拖:" + result);
	}
	public void notestXuan3qianzhi(){
		String tzNum = "1,11,19|3,4,6|8,12,15";
		String playMode = "qianzhi";
//		String result = ex.xuan3NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X3,playMode,tzNum);
		print("选3 前直:" + result);
	}
	public void notestXuan3qianzu(){
		String tzNum = "1,8,11,19";
		String playMode = "qianzu";
//		String result = ex.xuan3NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X3,playMode,tzNum);
		print("选3 前组:" + result);
	}
	public void notestXuan3qianzudantuo(){
		String tzNum = "胆3,5-拖1,6,18";
		String playMode = "qianzudantuo";
//		String result = ex.xuan3NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X3,playMode,tzNum);
		print("选3 前组胆拖:" + result);
	}
	
	public void notestXuan4renxuan(){
		String tzNum = "1,2,8,11,19";
		String playMode = "renxuan";
//		String result = ex.xuan4NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X4,playMode,tzNum);
		print("选4 任选:" + result);
	}

	public void notestXuan4renxuandantuo(){
		String tzNum = "胆3-拖1,5,18";
		String playMode = "renxuandantuo";
//		String result = ex.xuan4NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X4,playMode,tzNum);
		print("选4 任选胆拖:" + result);
	}
	
	public void notestXuan5renxuan(){
		String tzNum = "1,2,8,11,19";
		String playMode = "renxuan";
//		String result = ex.xuan5NumberBuilder(tzNum, playMode);

		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X5,playMode,tzNum);
		print("选5 任选:" + result);
	}

	public void notestXuan5renxuandantuo(){
		String tzNum = "胆3-拖1,5,18,19";
		String playMode = "renxuandantuo";
//		String result = ex.xuan5NumberBuilder(tzNum, playMode);
		String result = ex.builderNumber(ExbConstant.PLAY_TYPE_X5,playMode,tzNum);
		
		print("选5 任选胆拖:" + result);
	}
	
	public void testAnalyseBuilder(){
		AnalyseBuilder ab = new AnalyseBuilder();
		String tzNum = "胆3-拖1,5,18,19";
		String playMode = "renxuandantuo";
		List<PlayNumInfo> list = ab.builderExbNumList(ExbConstant.PLAY_TYPE_X3,playMode,tzNum);
		for(PlayNumInfo info : list){
			System.out.print(info.getPlayNum());
		}
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
}
