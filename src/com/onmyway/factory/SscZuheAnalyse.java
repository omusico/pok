package com.onmyway.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JStringToolkit;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 14, 2010 8:34:57 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SscZuheAnalyse extends PlayNumAnalyseFactory{
	
	SscZuheAnalyse sse;
	public static SscZuheAnalyse getInstance(){
		return new SscZuheAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dansi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fusi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 五星组合 必须有5位数，每位上可以有多个
	 * 一个五星组合包括五星复选，4星复选，三星复选，2星复选，1星复选等
	 * 【五星组合】1,2,2,3,34
	 * @param playNum
	 * @deprecated 用zuhe(playNum,num)方法
	 */
	public Map<String,String> wuxing(String playNum){
		//5星复式
		SscWuxingAnalyse wuxing = SscWuxingAnalyse.getInstance();
		String resultWuxing = wuxing.fusi(playNum);
		
		//4星复式
		String playNumSixing = playNum.substring(playNum.indexOf(",")+1,playNum.length());
		SscSixingZhixuanAnalyse sixing = SscSixingZhixuanAnalyse.getInstance();
		String resultSixing = sixing.fusi(playNumSixing);
		
		
		//3星复式
		String playNumSanxing = playNumSixing.substring(playNumSixing.indexOf(",")+1,playNumSixing.length());
		SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
		String resultSanxing = sanxing.fusi(playNumSanxing);
		
		//2星复式
		String playNumErxing = playNumSanxing.substring(playNumSanxing.indexOf(",")+1,playNumSanxing.length());
		SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
		String resultErxing = erxing.fusi(playNumErxing);
		//1星复式
		String playNumYixing = playNumErxing.substring(playNumErxing.indexOf(",")+1,playNumErxing.length());
		SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
		String resultYixing = yixing.fusi(playNumYixing);
		
//		System.out.println("5xing:" + resultWuxing);
//		System.out.println("4xing:" + resultSixing);
//		System.out.println("3xing:" + resultSanxing);
//		System.out.println("2xing:" + resultErxing);
//		System.out.println("1xing:" + resultYixing);
		Map<String,String> map = new HashMap<String,String>();
		map.put("zuheWuxing5", resultWuxing);
		map.put("zuheWuxing4", resultSixing);
		map.put("zuheWuxing3", resultSanxing);
		map.put("zuheWuxing2", resultErxing);
		map.put("zuheWuxing1", resultYixing);
		
		return map;
	}
	

	/**
	 * 组合，
	 * @param playNum
	 * @param num 几星玩法。五星：5；四星：4
	 * @return
	 */
	public Map<String,String> zuhe(String playNum,int num){
		if(playNum == null){
			return null;
		}
		if(num < 5){
			if(playNum.indexOf("-") != -1){
				playNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
			}
		}
		//5星复式
		String resultWuxing = "";
		String playNumWuxing = "";
		if(num == 5){			
			playNumWuxing = playNum;
			SscWuxingAnalyse wuxing = SscWuxingAnalyse.getInstance();
			resultWuxing = wuxing.fusi(playNumWuxing);
		}
		
		//4星复式
		String resultSixing = "";
		String playNumSixing = "";
		if(num >= 4){
			if(!playNumWuxing.equals("")){
				playNumSixing = playNum.substring(playNum.indexOf(",")+1,playNum.length());
			}else{
				playNumSixing = playNum;
			}
			SscSixingZhixuanAnalyse sixing = SscSixingZhixuanAnalyse.getInstance();
			resultSixing = sixing.fusi(playNumSixing);
		}
		
		
		//3星复式
		String resultSanxing = "";
		String playNumSanxing = "";
		if(num >= 3){
			if(!playNumSixing.equals("")){
				playNumSanxing = playNumSixing.substring(playNumSixing.indexOf(",")+1,playNumSixing.length());
			}else{
				playNumSanxing = playNum;
			}
			SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
			resultSanxing = sanxing.fusi(playNumSanxing);
		}
		
		//2星复式
		String resultErxing = "";
		String playNumErxing = "";
		if(num >= 2){
			if(!playNumSanxing.equals("")){
				playNumErxing = playNumSanxing.substring(playNumSanxing.indexOf(",")+1,playNumSanxing.length());
			}else{
				playNumErxing = playNum;
			}
			SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
			resultErxing = erxing.fusi(playNumErxing);
		}
		//1星复式
		String resultYixing = "";
		String playNumYixing = "";
		if(num >= 1){
			if(!playNumErxing.equals("")){	
				playNumYixing = playNumErxing.substring(playNumErxing.indexOf(",")+1,playNumErxing.length());
			}else{
				playNumYixing = playNum;
			}
			SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
			resultYixing = yixing.fusi(playNumYixing);
		}
		
		System.out.println("5xing:" + resultWuxing);
		System.out.println("4xing:" + resultSixing);
		System.out.println("3xing:" + resultSanxing);
		System.out.println("2xing:" + resultErxing);
		System.out.println("1xing:" + resultYixing);
		
		//如果玩法是组合5星以下，则放入空值
		Map<String,String> map = new HashMap<String,String>();
		map.put("zuheWuxing5", resultWuxing);
		map.put("zuheWuxing4", resultSixing);
		map.put("zuheWuxing3", resultSanxing);
		map.put("zuheWuxing2", resultErxing);
		map.put("zuheWuxing1", resultYixing);
		
		return map;
	}
	
	/**
	 * 组合，
	 * @param playNum
	 * @param num 几星玩法。五星：5；四星：4
	 * @return
	 */
	public List<PlayNumInfo> zuheListOld(String playNum,int num){
		if(playNum == null){
			return null;
		}
		
		List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();
		
		if(num < 5){
			if(playNum.indexOf("-") != -1){
				playNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
			}
		}
		//5星复式
		String resultWuxing = "";
		String playNumWuxing = "";
		if(num == 5){			
			playNumWuxing = playNum;
			SscWuxingAnalyse wuxing = SscWuxingAnalyse.getInstance();
			resultWuxing = wuxing.fusi(playNumWuxing);
//			list.add(new PlayNumInfo("ssc","wuxing","zuhe","fushi",","+resultWuxing,"","",""));
			list.add(new PlayNumInfo("ssc","wuxing","wuxingzuhe","zhixuan",","+resultWuxing,"","",""));
		}
		
		//4星复式
		String resultSixing = "";
		String playNumSixing = "";
		if(num >= 4){
			if(!playNumWuxing.equals("")){
				playNumSixing = playNum.substring(playNum.indexOf(",")+1,playNum.length());
			}else{
				playNumSixing = playNum;
			}
			SscSixingZhixuanAnalyse sixing = SscSixingZhixuanAnalyse.getInstance();
			resultSixing = sixing.fusi(playNumSixing);
//			list.add(new PlayNumInfo("ssc","sixing","zuhe","fushi",","+resultSixing,"","",""));
			list.add(new PlayNumInfo("ssc","sixing","sixingzuhe","zhixuan",","+resultSixing,"","",""));
		}
		
		
		//3星复式
		String resultSanxing = "";
		String playNumSanxing = "";
		if(num >= 3){
			if(!playNumSixing.equals("")){
				playNumSanxing = playNumSixing.substring(playNumSixing.indexOf(",")+1,playNumSixing.length());
			}else{
				playNumSanxing = playNum;
			}
			SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
			resultSanxing = sanxing.fusi(playNumSanxing);
//			list.add(new PlayNumInfo("ssc","sanxing","zuhe","fushi",","+resultSanxing,"","",""));
			list.add(new PlayNumInfo("ssc","sanxing","sanxingzuhe","zhixuan",","+resultSanxing,"","",""));
		}
		
		//2星复式
		String resultErxing = "";
		String playNumErxing = "";
		if(num >= 2){
			if(!playNumSanxing.equals("")){
				playNumErxing = playNumSanxing.substring(playNumSanxing.indexOf(",")+1,playNumSanxing.length());
			}else{
				playNumErxing = playNum;
			}
			SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
			resultErxing = erxing.fusi(playNumErxing);
//			list.add(new PlayNumInfo("ssc","erxing","zuhe","fushi",","+resultErxing,"","",""));
			list.add(new PlayNumInfo("ssc","erxing","erxingzuhe","zhixuan",","+resultErxing,"","",""));
		}
		//1星复式
		String resultYixing = "";
		String playNumYixing = "";
		if(num >= 1){
			if(!playNumErxing.equals("")){	
				playNumYixing = playNumErxing.substring(playNumErxing.indexOf(",")+1,playNumErxing.length());
			}else{
				playNumYixing = playNum;
			}
			SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
			resultYixing = yixing.fusi(playNumYixing);
			list.add(new PlayNumInfo("ssc","yixing","zuhe","fushi",","+resultYixing,"","",""));
		}
		
//		System.out.println("5xing:" + resultWuxing);
//		System.out.println("4xing:" + resultSixing);
//		System.out.println("3xing:" + resultSanxing);
//		System.out.println("2xing:" + resultErxing);
//		System.out.println("1xing:" + resultYixing);
		
//		//如果玩法是组合5星以下，则放入空值
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("zuheWuxing5", resultWuxing);
//		map.put("zuheWuxing4", resultSixing);
//		map.put("zuheWuxing3", resultSanxing);
//		map.put("zuheWuxing2", resultErxing);
//		map.put("zuheWuxing1", resultYixing);
		
		return list;
	}
	/**
	 * 组合，
	 * @param playNum
	 * @param num 几星玩法。五星：5；四星：4
	 * @return
	 */
	public List<PlayNumInfo> zuheList(String playNum,int num){
		 List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();
		if(num == 5){
			list = wuxingList(playNum);			
		}else if(num == 4){
			list = sixingList(playNum);			
		}else if(num == 3){
			list = sanxingList(playNum);			
		}else if(num == 2){
			list = erxingList(playNum);			
		}
		return list;
	}
	/**
	 * 组合，
	 * @param playNum
	 * @param num 几星玩法。五星：5；四星：4
	 * @return
	 */
	public List<PlayNumInfo> wuxingList(String playNum){
		if(playNum == null){
			return null;
		}
		List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();

		SscWuxingAnalyse wuxing = SscWuxingAnalyse.getInstance();
		SscSixingZhixuanAnalyse sixing = SscSixingZhixuanAnalyse.getInstance();
		SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
		SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
		SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
				
		//5星复式
		String playNumWuxing = playNum;
		String resultWuxing = wuxing.fusi(playNumWuxing);
		list.add(new PlayNumInfo("ssc","wuxing","wuxingzuhe","zhixuan",","+resultWuxing,"","",""));
		
		String[] aryResult = JStringToolkit.splitString(resultWuxing, ",");
		for(int i = 0; i < aryResult.length;i++){
			String tempPlayNum = JStringToolkit.insertDelimiter(aryResult[i],"",",");
		
			//4星复式
			String playNumSixing = tempPlayNum.substring(tempPlayNum.indexOf(",")+1,tempPlayNum.length());		
			String resultSixing = sixing.fusi(playNumSixing);
			list.add(new PlayNumInfo("ssc","sixing","wuxingzuhe","zhixuan",","+resultSixing,"","",""));
			
			//3星复式
			String playNumSanxing  = playNumSixing.substring(playNumSixing.indexOf(",")+1,playNumSixing.length());
			String	resultSanxing = sanxing.fusi(playNumSanxing);
			list.add(new PlayNumInfo("ssc","sanxing","wuxingzuhe","zhixuan",","+resultSanxing,"","",""));
			
			
			//2星复式
			String playNumErxing  = playNumSanxing.substring(playNumSanxing.indexOf(",")+1,playNumSanxing.length());
			String resultErxing = erxing.fusi(playNumErxing);
			list.add(new PlayNumInfo("ssc","erxing","wuxingzuhe","zhixuan",","+resultErxing,"","",""));
			
			//1星复式
			String playNumYixing = playNumErxing.substring(playNumErxing.indexOf(",")+1,playNumErxing.length());
			String resultYixing = yixing.fusi(playNumYixing);
			list.add(new PlayNumInfo("ssc","yixing","wuxingzuhe","zhixuan",","+resultYixing,"","",""));
		}
		return list;
	}
	/**
	 * 四星组合，
	 * @param playNum
	 * @return
	 */
	public List<PlayNumInfo> sixingList(String playNum){
		if(playNum == null){
			return null;
		}
		List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();

		SscSixingZhixuanAnalyse sixing = SscSixingZhixuanAnalyse.getInstance();
		SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
		SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
		SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
				
		//4星复式
		String playNumSixing = playNum;
		String resultSixing = sixing.fusi(playNumSixing);
		list.add(new PlayNumInfo("ssc","sixing","sixingzuhe","zhixuan",","+resultSixing,"","",""));
		
		
		String[] aryResult = JStringToolkit.splitString(resultSixing, ",");
		for(int i = 0; i < aryResult.length;i++){
			String tempPlayNum = JStringToolkit.insertDelimiter(aryResult[i],"",",");
	
			//3星复式
			String playNumSanxing  = tempPlayNum.substring(tempPlayNum.indexOf(",")+1,tempPlayNum.length());
			String	resultSanxing = sanxing.fusi(playNumSanxing);
			list.add(new PlayNumInfo("ssc","sanxing","sixingzuhe","zhixuan",","+resultSanxing,"","",""));
			
			
			//2星复式
			String playNumErxing  = playNumSanxing.substring(playNumSanxing.indexOf(",")+1,playNumSanxing.length());
			String resultErxing = erxing.fusi(playNumErxing);
			list.add(new PlayNumInfo("ssc","erxing","sixingzuhe","zhixuan",","+resultErxing,"","",""));
			
			//1星复式
			String playNumYixing = playNumErxing.substring(playNumErxing.indexOf(",")+1,playNumErxing.length());
			String resultYixing = yixing.fusi(playNumYixing);
			list.add(new PlayNumInfo("ssc","yixing","sixingzuhe","zhixuan",","+resultYixing,"","",""));
		}
		return list;
	}
	/**
	 * 三星组合，
	 * @param playNum
	 * @return
	 */
	public List<PlayNumInfo> sanxingList(String playNum){
		if(playNum == null){
			return null;
		}
		List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();

		SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
		SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
		SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
				
		//3星复式
		String playNumSanxing = playNum;
		String resultSanxing = sanxing.fusi(playNumSanxing);
		list.add(new PlayNumInfo("ssc","sanxing","sanxingzuhe","zhixuan",","+resultSanxing,"","",""));
		
		
		String[] aryResult = JStringToolkit.splitString(resultSanxing, ",");
		for(int i = 0; i < aryResult.length;i++){

			String tempPlayNum = JStringToolkit.insertDelimiter(aryResult[i],"",",");
	
			//2星复式
			String playNumErxing  = tempPlayNum.substring(tempPlayNum.indexOf(",")+1,tempPlayNum.length());
			String resultErxing = erxing.fusi(playNumErxing);
			list.add(new PlayNumInfo("ssc","erxing","sanxingzuhe","zhixuan",","+resultErxing,"","",""));
			
			//1星复式
			String playNumYixing = playNumErxing.substring(playNumErxing.indexOf(",")+1,playNumErxing.length());
			String resultYixing = yixing.fusi(playNumYixing);
			list.add(new PlayNumInfo("ssc","yixing","sanxingzuhe","zhixuan",","+resultYixing,"","",""));
		}
		return list;
	}
	/**
	 * 二星组合，
	 * @param playNum
	 * @return
	 */
	public List<PlayNumInfo> erxingList(String playNum){
		if(playNum == null){
			return null;
		}
		List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();

		SscSanxingZhixuanAnalyse sanxing = SscSanxingZhixuanAnalyse.getInstance();
		SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
		SscYixingAnalyse yixing = SscYixingAnalyse.getInstance();
				
		//3星复式
		String playNumErxing = playNum;
		String resultErxing = sanxing.fusi(playNumErxing);
		list.add(new PlayNumInfo("ssc","erxing","erxingzuhe","zhixuan",","+resultErxing,"","",""));
		
		
		String[] aryResult = JStringToolkit.splitString(resultErxing, ",");
		for(int i = 0; i < aryResult.length;i++){

			String tempPlayNum = JStringToolkit.insertDelimiter(aryResult[i],"",",");
	
//			//2星复式
//			String playNumErxing  = tempPlayNum.substring(tempPlayNum.indexOf(",")+1,tempPlayNum.length());
//			String resultErxing = erxing.fusi(playNumErxing);
//			list.add(new PlayNumInfo("ssc","erxing","sixingzuhe","zhixuan",resultErxing,"","",""));
			
			//1星复式
			String playNumYixing = tempPlayNum.substring(tempPlayNum.indexOf(",")+1,tempPlayNum.length());
			String resultYixing = yixing.fusi(playNumYixing);
			list.add(new PlayNumInfo("ssc","yixing","erxingzuhe","zhixuan",","+resultYixing,"","",""));
		}
		return list;
	}
	/**
	 * 大小单双 大小单双是单独限号的，不用考虑太多
	 * @param playNum
	 * @return "dx,ds,"
	 */
	public String dxds(String playNum){
		if(playNum == null){
			return null;
		}
		List<PlayNumInfo> list = new ArrayList<PlayNumInfo>();
		//截取
		if(playNum.indexOf("-") > -1){
			playNum = playNum.substring(playNum.lastIndexOf("-")+2,playNum.length());
		}
		
		String[] temp = JStringToolkit.splitString(playNum,",");
		String result = "";
		for(int i=0;i<temp.length;i++){
			String t = toChinese(temp[i]);
			if(result.equals("")){
				result = t;
			}else{
				result = result + "" + t;
			}
		}
//		for(int i=0;i<temp.length;i++){
//			String t = toNum(temp[i]);
//			if(result.equals("")){
//				result = t;
//			}else{
//				result = result + "," + t;
//			}
//		}
//		SscErxingZhixuanAnalyse erxing = SscErxingZhixuanAnalyse.getInstance();
//		String str = erxing.fusi(result);
		return result+",";
		
	}
	
	private String toChinese(String name){
		String str = "";
		if(name.equals("大")){
			str = "b";//big
		}
		if(name.equals("小")){
			str = "l";//little ，为了与单(single)的英文区分
		}
		if(name.equals("单")){
			str = "s";//single
		}
		if(name.equals("双")){
			str = "d";//double
		}
		return str;
	}
	private String toNum(String name){
		String str = "";
		if(name.equals("大")){
			str = "56789";
		}
		if(name.equals("小")){
			str = "01234";
		}
		if(name.equals("单")){
			str = "13579";//为了与"大”区分，写成"n",取最后一个数
		}
		if(name.equals("双")){
			str = "02468";
		}
		return str;
	}
}
