package com.onmyway.factory;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @Title:五星组合示例，如购买：4+5+6+7+8，该票共8元，由以下4注：45678(五星)、678(三星)、78(二星)、8(一星)构成
 * 三星组合示例，如购买：6+7+8，该票共6元，由以下3注：678(三星)、78(二星)、8(一星)构成。
 * 二星组合示例，如购买：7+8，该票共4元，由以下2注：78(二星)、8(一星)构成。
 * @Description: 
 * @Create on: Aug 13, 2010 6:56:08 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscZuheAnalyseTest extends TestCase {

	SscZuheAnalyse sse;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sse = new SscZuheAnalyse();
	}

	public void notestWuxing(){
		String playNum = "1,2,2,3,34";
//		print(sse.wuxing(playNum));
		
		Map<String,String> map = sse.wuxing(playNum);
		print("5xing5:"+map.get("zuheWuxing5"));
		print("5xing4:"+map.get("zuheWuxing4"));
		print("5xing3:"+map.get("zuheWuxing3"));
		print("5xing2:"+map.get("zuheWuxing2"));
		print("5xing1:"+map.get("zuheWuxing1"));
		
	}
	public void notestZuhe(){
		String playNum = "2,1,2,34";
		int num = 4;
		sse.zuhe(playNum,num);		
	}
	public void notestZuheList(){
		String playNum = "6,7,8";
		int num = 3;
		
		List<PlayNumInfo>  resultList  = sse.zuheList(playNum,num);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getTypeName()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	public void notestWuxingZuheList(){
		String playNum = "93,4,5,6,7";
		int num = 3;
		
		List<PlayNumInfo>  resultList  = sse.wuxingList(playNum);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	//四星
	public void notestSixingZuheList(){
		String playNum = "43,5,68,7";
		
		List<PlayNumInfo>  resultList  = sse.sixingList(playNum);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	//三星
	public void notestSanxingZuheList(){
		String playNum = "5,68,79";
		
		List<PlayNumInfo>  resultList  = sse.sanxingList(playNum);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	//二星
	public void testErxingZuheList(){
		String playNum = "68,79";
		
		List<PlayNumInfo>  resultList  = sse.erxingList(playNum);
		for(PlayNumInfo info : resultList){
			print(info.getGameType()+":" + info.getPlayType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
		}
	}
	//二星
	public void testDxds(){
		String playNum = "大,小";
		print(sse.dxds(playNum));
//		List<PlayNumInfo>  resultList  = sse.erxingList(playNum);
//		for(PlayNumInfo info : resultList){
//			print(info.getGameType()+":" + info.getPlayType()+":" + info.getPlayMode() + ":" + info.getBaodanType()+":"+info.getPlayNum());
//		}
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
