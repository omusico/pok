package com.onmyway.factory;

import java.util.List;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Sep 17, 2010 3:21:49 PM
 * @Author:LJY
 * @Version:1.0
 */
public class AnalyseBuilderTest extends TestCase {

	AnalyseBuilder ab;
	protected void setUp() throws Exception {
		super.setUp();
		ab = new AnalyseBuilder();
	}

	public void notestBuild(){
		String playNum = "1,2,26,7,28$2,67,4,36,9$5,6,47,3,2";
		//int num = 5;
		String playType = "zuhe";
		String typeName = "wuxing";
		String playMode = "fushi";
		String zuhe = "0";
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testWuxing(){
		String playNum = "1,2,3,4,5";
		//int num = 5;
		String playType = "wuxing";
		String typeName = "tx";
		String playMode = "fushi";
		String zuhe = "0";
		print("------------------------五星直选---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testWuxingDanshi(){
		String playNum = "1,2,3,4,5$5,6,7,8,9";
		//int num = 5;
		String playType = "wuxing";
		String typeName = "zhixuan";
		String playMode = "danshi";
		String zuhe = "0";
		print("------------------------五星直选单式---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSixingDanshi(){
		String playNum = "6,7,8,9$6,7,8,9";
		//int num = 5;
		String playType = "sixing";
		String typeName = "zhixuan";
		String playMode = "fushi";
		String zuhe = "0";
		print("------------------------四星直选单式---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingzhixuan(){
//		String playNum = "3,4,5";
		String playNum = "3,4,5";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zhixuan";
		//String playMode = "fushi";
		String playMode = "danshi";
		String zuhe = "0";
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		print("------------------------三星直选单式---------------");
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingzu3(){
		String playNum = "3,4,5";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuxuan";
		String playMode = "zu3";
		String zuhe = "0";
		print("------------------------三星zu3---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingzu6(){
		String playNum = "3,4,5,8";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuxuan";
		String playMode = "zu6";
		String zuhe = "0";
		print("------------------------三星zu6---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingHezhi(){
		String playNum = "2";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuxuan";
		String playMode = "hezhi";
		String zuhe = "0";
		print("------------------------三星hezhi---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingBao1dan(){
		String playNum = "2";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuxuan";
		String playMode = "baodan";
		String zuhe = "0";
		print("------------------------三星包1胆--------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingBao2dan(){
//		String playNum = "2,3";
		String playNum = "8,2";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuxuan";
		String playMode = "baodan";
		String zuhe = "0";
		print("------------------------三星包2胆---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingzuxuanDanshi(){
//		String playNum = "2,3";
		String playNum = "885";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuxuan";
		String playMode = "danshi";
		String zuhe = "0";
		print("------------------------三星组选单式---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testErxingzhixuan(){
//		String playNum = "2,3";
		String playNum = "5,6$7,8";
		//int num = 5;
		String playType = "erxing";
		String typeName = "zhixuan";
		String playMode = "danshi";
		String zuhe = "0";
		print("------------------------二星直选单式---------------");
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testErxingzuxuan(){
//		String playNum = "2,3";
		String playNum = "5,6,8";
		//int num = 5;
		String playType = "erxing";
		String typeName = "zuxuan";
		String playMode = "fushi";
		String zuhe = "0";
		print("------------------------二星组选复式---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testErxingzuxuanDanshi(){
		String playNum = "23";
		//String playNum = "5,6$7,8";
		//int num = 5;
		String playType = "erxing";
		String typeName = "zuxuan";
		String playMode = "danshi";
		String zuhe = "0";
		print("------------------------二星组选单式---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testWuxingZuhe(){
//		String playNum = "2,3";
		String playNum = "8,56,2,3,7";
		//int num = 5;
		String playType = "wuxing";
		String typeName = "zuhe";
		String playMode = "wuxing";
		String zuhe = "0";
		print("------------------------五星组合---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSixingZuhe(){
//		String playNum = "2,3";
		String playNum = "56,2,3,7";
		//int num = 5;
		String playType = "sixing";
		String typeName = "zuhe";
		String playMode = "wuxing";
		String zuhe = "0";
		print("------------------------四星组合---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSanxingZuhe(){
//		String playNum = "2,3";
		String playNum = "2,3,7";
		//int num = 5;
		String playType = "sanxing";
		String typeName = "zuhe";
		String playMode = "sanxing";
		String zuhe = "0";
		print("------------------------三星组合---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testErxingZuhe(){
//		String playNum = "2,3";
		String playNum = "3,7";
		//int num = 5;
		String playType = "erxing";
		String typeName = "zuhe";
		String playMode = "erxing";
		String zuhe = "0";
		print("------------------------二星组合---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderNumList(playNum, playType, typeName, playMode, zuhe);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void testSxwBuildList(){
//		String playNum = "2,3";
//		String playNum = "3,5,7,8,9$9,10,12";
		String playNum = "胆3-拖5,6,7,8$胆5,12-拖9,10,11,8";
		//int num = 5;
		String playType = "renxuan3";
		String playMode = "fushi";
	
		String zuhe = "0";
		print("---------------------12选5---------------");
		
		List<PlayNumInfo>  resultList  = ab.builderSxwNumList(playNum, playType,playMode);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":"+ info.getPlayType() + ":" + info.getTypeName() + ":"+ info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
}
