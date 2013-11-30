package com.onmyway.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import util.JStringToolkit;

/**
 * @Title:根据不同的玩法，得到不同的号码
 * @Description: 
 * @Create on: Sep 6, 2010 10:55:01 PM
 * @Author:LJY
 * @Version:1.0
 */
public class AnalyseBuilder {
	
	Logger log = Logger.getLogger(AnalyseBuilder.class);
	public Map builderNum(String tzNum,String playType,String playMode,String zuhe){
		Map<String,String> map = new HashMap<String,String>();
		if(playType.equals("wuxingTx")){
			SscWuxingTongxuanAnalyse wxtxAnly = new SscWuxingTongxuanAnalyse();
			if(playMode.equals("fushi")){
				String str = wxtxAnly.fusi(tzNum);
				map.put("wuxingTx", str);
			}
		}
		//五星
		if(playType.equals("wuxing")){
			SscWuxingAnalyse wxAnly = new SscWuxingAnalyse();
			if(playMode.equals("fushi")){
				String str = wxAnly.fusi(tzNum);
				map.put("wuxing", str);
			}
			if(playMode.equals("danshi")){
				String str = wxAnly.dansi(tzNum);
				map.put("wuxing", str);
			}
		}
		//三星直选星
		if(playType.equals("sanxingZhixuan")){
			SscSanxingZhixuanAnalyse sxzxAnly = new SscSanxingZhixuanAnalyse();
			if(playMode.equals("fushi")){
				String str = sxzxAnly.fusi(tzNum);
				map.put("sanxingZhixuan", str);
			}
			if(playMode.equals("danshi")){
				String str = sxzxAnly.dansi(tzNum);
				map.put("sanxingZhixuan", str);
			}
			if(playMode.equals("zuheFushi")){
				String str = sxzxAnly.zuheFushi(tzNum);
				map.put("sanxingZhixuan", str);
			}
		}
		//三星组选
		if(playType.equals("sanxingZuxuan")){
			SscSanxingZuxuanAnalyse sxzu = new SscSanxingZuxuanAnalyse();
			if(playMode.equals("zu3")){
				String str =  sxzu.zu3(tzNum);
				map.put("sanxingZuxuan", str);
			}
			if(playMode.equals("zu6")){
				String str =  sxzu.zu6(tzNum);
				map.put("sanxingZuxuan", str);
			}
			
		}
		return map;
	}
	/**
	 * 只针对相应的一个玩法中含有多个玩法的，如三星包胆，和值，五星组合  三星组合  二星组合等
	 * @param tzNum
	 * @param playType
	 * @param playMode
	 * @param zuhe
	 * @return
	 */
	public List<PlayNumInfo> builderNumList(String tzNum,String playType,String typeName,String playMode,String zuhe){
		List<PlayNumInfo> resultList = new ArrayList<PlayNumInfo>();
		
		///////////////////////////////////////////////////五星/////////////////////////////////////////////////////////////////////////
		if(playType.equals("wuxing")){
			//五星通选
			if(typeName.equals("tx")){
				
				SscWuxingTongxuanAnalyse wxtxAnly = new SscWuxingTongxuanAnalyse();
//				String str = wxtxAnly.fusi(tzNum);
//				resultList.add(new PlayNumInfo("ssc","wuxing","tx","fushi",str,"","",""));

				String str = ",";
				if(playMode.equals("fushi")){
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = wxtxAnly.fusi(tempNum);
							if(temp != null && !temp.equals("")){
//								if(str.equals("")){
//									str = str + temp;
//								}else{
//									str = str + "," + temp;
//								}
								str = str + temp;
							}
						}
					}else{
						str = str + wxtxAnly.fusi(tzNum);
					}
				}
				resultList.add(new PlayNumInfo("ssc","wuxing","tx","fushi",str,"","",""));
			}
			//五星直选
			if(typeName.equals("zhixuan")){
				SscWuxingAnalyse wxAnly = new SscWuxingAnalyse();
				if(playMode.equals("fushi")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + wxAnly.fusi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zhixuanfushi","zhixuan",str,"","",""));
				}
				if(playMode.equals("danshi")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxAnly.dansi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + wxAnly.dansi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zhixuandanshi","zhixuan",str,"","",""));
				}
			}
			//五星组选
			if(typeName.equals("zuxuan")){
				SscWuxingZuxuanAnalyse wxzxAnly = new SscWuxingZuxuanAnalyse();
				//五星组选120
				if(playMode.equals("zu120")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo120 = wxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo120, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										
										if(kkk.indexOf(",") == -1){
											kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										}
										String temp = wxzxAnly.zuxuan120(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo120 = wxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo120, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								if(kkk.indexOf(",") == -1){
									kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								}
								String temp = wxzxAnly.zuxuan120(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu120",str,"","",""));
				}
				//五星组选60
				if(playMode.equals("zu60")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo60 = wxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo60, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										//zu60为两位重复的，系统中设置在最后一位重复，因此添加最后一位
										kkk = kkk + kkk.substring(kkk.length()-1,kkk.length());
										if(kkk.indexOf(",") == -1){
											kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										}
										String temp = wxzxAnly.zuxuan60(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
								 
							}
					}else{
						String tempTo60 = wxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo60, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								//zu60为两位重复的，系统中设置在最后一位重复，因此添加最后一位
								kkk = kkk + kkk.substring(kkk.length()-1,kkk.length());
								if(kkk.indexOf(",") == -1){
									kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								}
								String temp = wxzxAnly.zuxuan60(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu60",str,"","",""));
				}
				//五星组选30
				if(playMode.equals("zu30")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo30 = wxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo30, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										//zu30为两位重复的，系统中设置在最后一位重复，因此添加最后一位
										kkk = makeWuxingZuxuanString(kkk,"zu30");
										//if(kkk.indexOf(",") == -1){
											kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										//}
										String temp = wxzxAnly.zuxuan30(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo30 = wxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo30, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								//zu30为两位重复的，系统中设置在最后一位重复，因此添加最后一位
								kkk = makeWuxingZuxuanString(kkk,"zu30");
								//if(kkk.indexOf(",") == -1){
									kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								//}
								String temp = wxzxAnly.zuxuan30(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu30",str,"","",""));
				}
				//五星组选20
				if(playMode.equals("zu20")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo20 = wxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo20, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										//zu30为两位重复的，系统中设置在最后一位重复，因此添加最后一位
										kkk = makeWuxingZuxuanString(kkk,"zu20");
										//if(kkk.indexOf(",") == -1){
											kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										//}
										String temp = wxzxAnly.zuxuan20(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo20 = wxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo20, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								//zu30为两位重复的，系统中设置在最后一位重复，因此添加最后一位
								kkk = makeWuxingZuxuanString(kkk,"zu20");
								//if(kkk.indexOf(",") == -1){
									kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								//}
								String temp = wxzxAnly.zuxuan20(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu20",str,"","",""));
				}
				//五星组选10
				if(playMode.equals("zu10")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo10 = wxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo10, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										kkk = makeWuxingZuxuanString(kkk,"zu10");
										kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										String temp = wxzxAnly.zuxuan10(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo10 = wxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo10, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								kkk = makeWuxingZuxuanString(kkk,"zu10");
								kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								String temp = wxzxAnly.zuxuan10(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu10",str,"","",""));
				}
				//五星组选5
				if(playMode.equals("zu5")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo5 = wxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo5, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										kkk = makeWuxingZuxuanString(kkk,"zu5");
										kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										String temp = wxzxAnly.zuxuan5(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo5 = wxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo5, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								kkk = makeWuxingZuxuanString(kkk,"zu5");
								kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								String temp = wxzxAnly.zuxuan5(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu5",str,"","",""));
				}
				//五星2重号全包
				if(playMode.equals("zuchong2")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String[] temp = tempNum.split("");
								for(String s : temp){
									if(s != null && !s.equals("")){
										str = str + s+",";
									}
								}
							}
					}else{
						//String[] temp = JStringToolkit.splitString(tzNum, "");
						String[] temp = tzNum.split("");
						for(String s : temp){
							if(s != null && !s.equals("")){
								str = str + s+",";
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zuchong2",str,"","",""));
				}
				//五星3重号全包
				if(playMode.equals("zuchong3")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String[] temp = tempNum.split("");
								for(String s : temp){
									if(s != null && !s.equals("")){
										str = str + s+",";
									}
								}
							}
					}else{
						String[] temp = tzNum.split("");
						for(String s : temp){
							if(s != null && !s.equals("")){
								str = str + s+",";
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zuchong3",str,"","",""));
				}
				//五星4重号全包 2011-10-11
				if(playMode.equals("zuchong4")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String[] temp = tempNum.split("");
								for(String s : temp){
									if(s != null && !s.equals("")){
										str = str + s+",";
									}
								}
							}
					}else{
						String[] temp = tzNum.split("");
						for(String s : temp){
							if(s != null && !s.equals("")){
								str = str + s+",";
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zuchong4",str,"","",""));
				}
			}
		}
		///////////////////////////////////////////////////四星/////////////////////////////////////////////////////////////////////////
		if(playType.equals("sixing")){
			//四星
			if(typeName.equals("zhixuan")){
				SscSixingZhixuanAnalyse sxAnly = new SscSixingZhixuanAnalyse();
				if(playMode.equals("fushi")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
//									if(str.equals("")){
//										str = str + temp;
//									}else{
//										str = str + "," + temp;
//									}
									str = str + temp;
								}
							}
					}else{
							str = str + sxAnly.fusi(tzNum);
					}
//					resultList.add(new PlayNumInfo("ssc","sixing","zhixuan","fushi",str,"","",""));
					resultList.add(new PlayNumInfo("ssc","sixing","zhixuanfushi","zhixuan",str,"","",""));
				}
				if(playMode.equals("danshi")){
					
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxAnly.dansi(tempNum);
								if(temp != null && !temp.equals("")){
//									if(str.equals("")){
//										str = str + temp;
//									}else{
//										str = str + "," + temp;
//									}
									str = str + temp;
								}
							}
					}else{
							str = str + sxAnly.dansi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","sixing","zhixuandanshi","zhixuan",str,"","",""));
				}
			}
			if(typeName.equals("zuxuan")){
				SscSixingZuxuanAnalyse sxzxAnly = new SscSixingZuxuanAnalyse();
				//四星组选24
				if(playMode.equals("zu24")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo120 = sxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo120, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										
										if(kkk.indexOf(",") == -1){
											kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										}
										String temp = sxzxAnly.zuxuan24(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo120 = sxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo120, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								if(kkk.indexOf(",") == -1){
									kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								}
								String temp = sxzxAnly.zuxuan24(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu24",str,"","",""));
				}
				//四星组选12
				if(playMode.equals("zu12")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo12 = sxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo12, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										kkk = makeSixingZuxuanString(kkk,"zu12");
										kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										String temp = sxzxAnly.zuxuan12(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo12 = sxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo12, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								kkk = makeSixingZuxuanString(kkk,"zu12");
								kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								String temp = sxzxAnly.zuxuan12(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu12",str,"","",""));
				}
				//四星组选6
				if(playMode.equals("zu6")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo6 = sxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo6, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										kkk = makeSixingZuxuanString(kkk,"zu6");
										kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										String temp = sxzxAnly.zuxuan6(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo6 = sxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo6, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								kkk = makeSixingZuxuanString(kkk,"zu6");
								kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								String temp = sxzxAnly.zuxuan6(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu6",str,"","",""));
				}
				//四星组选4
				if(playMode.equals("zu4")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String tempTo4 = sxzxAnly.fusi(tempNum);
								String[] tempAry = JStringToolkit.splitString(tempTo4, ",");
								for(int j = 0; j < tempAry.length; j++){
									String kkk = tempAry[j];
									if(kkk != null && !kkk.equals("")){
										kkk = makeSixingZuxuanString(kkk,"zu4");
										kkk = JStringToolkit.insertDelimiter(kkk, "",",");
										String temp = sxzxAnly.zuxuan4(kkk);
										if(temp != null && !temp.equals("")){
											str = str + temp;
										}
									}
								}
							}
					}else{
						String tempTo4 = sxzxAnly.fusi(tzNum);
						String[] tempAry = JStringToolkit.splitString(tempTo4, ",");
						for(int j = 0; j < tempAry.length; j++){
							String kkk = tempAry[j];
							if(kkk != null && !kkk.equals("")){
								kkk = makeSixingZuxuanString(kkk,"zu4");
								kkk = JStringToolkit.insertDelimiter(kkk, "",",");
								String temp = sxzxAnly.zuxuan4(kkk);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
						}
					}
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu4",str,"","",""));
				}
			}
		}
		
		
		///////////////////////////////////////////////////三星/////////////////////////////////////////////////////////////////////////
		if(playType.equals("sanxing")){			
			//三星直选
			if(typeName.equals("zhixuan")){
				SscSanxingZhixuanAnalyse sxzxAnly = new SscSanxingZhixuanAnalyse();
				if(playMode.equals("fushi")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + sxzxAnly.fusi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","sanxing","zhixuanfushi","zhixuan",str,"","",""));
				}
				if(playMode.equals("danshi")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxzxAnly.dansi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + sxzxAnly.dansi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","sanxing","zhixuandanshi","zhixuan",str,"","",""));
				}
				if(playMode.equals("zuheFushi")){
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = sxzxAnly.zuheFushi(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
						str = str + sxzxAnly.zuheFushi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","sanxing","zhixuanzuhefushi","zhixuan",str,"","",""));
				}
				
			}
			//三星组选
			if(typeName.equals("zuxuan")){
				SscSanxingZuxuanAnalyse sxzu = new SscSanxingZuxuanAnalyse();
				if(playMode.equals("zu3")){
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = sxzu.zu3(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
							str = str + sxzu.zu3(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu3",str,"","","1"));
				}
				//组6
				if(playMode.equals("zu6")){
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = sxzu.zu6(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
							str = str + sxzu.zu6(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu6",str,"","","1"));
				}	
				//包胆
				if(playMode.equals("baodan")){
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){					
							String tempNum = aryNum[i];
							if(tempNum.indexOf(",") != -1){//包2胆
								List<PlayNumInfo> tempList = sxzu.baodan2List(tempNum);		
								for(PlayNumInfo pni : tempList){
									resultList.add(pni);
								}
							}else{
								//包胆1中包括了三种玩法，三星直选，组3，组6，因此做此限制
								//resultList =  sxzu.baodan1List(tempNum);	
								List<PlayNumInfo> tempList = sxzu.baodan1List(tempNum);		
								for(PlayNumInfo pni : tempList){
									resultList.add(pni);
								}
							}
						}
					}else{
						if(tzNum.indexOf(",") != -1){//包2胆
							List<PlayNumInfo> tempList = sxzu.baodan2List(tzNum);		
							for(PlayNumInfo pni : tempList){
								resultList.add(pni);
							}
						}else{
							//包胆1中包括了三种玩法，三星直选，组3，组6，因此做此限制
							//resultList =  sxzu.baodan1List(tempNum);	
							List<PlayNumInfo> tempList = sxzu.baodan1List(tzNum);		
							for(PlayNumInfo pni : tempList){
								resultList.add(pni);
							}
						}
					}
				}	
				//和值
				if(playMode.equals("hezhi")){		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){					
							String tempNum = aryNum[i];
							List<PlayNumInfo> tempList = sxzu.hezhiList(tempNum);		
							for(PlayNumInfo pni : tempList){
								resultList.add(pni);
							}
						}
					}else{
						resultList = sxzu.hezhiList(tzNum);
					}
				}
				//三星组选单式
				if(playMode.equals("danshi")){		
					String str = ",";	
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						
						for(int i = 0; i < aryNum.length; i++){					
							String tempNum = aryNum[i];
							
							List<PlayNumInfo> tempList = sxzu.danshiList(tempNum);		
							for(PlayNumInfo pni : tempList){
								resultList.add(pni);
							}
							/*							
							String temp = sxzu.dansi(tempNum);
							if(temp != null && !temp.equals("")){
//								if(str.equals("")){
//									str = str + temp;
//								}else{
//									str = str + "," + temp;
//								}
								str = str + temp;
							}
							*/
						}
					}else{
						//str = str + sxzu.dansi(tzNum);
						resultList = sxzu.danshiList(tzNum);
					}
					//resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zuxuan",str,"","","1"));
				}
			}
		}
//		//三星组选
//		if(playType.equals("sanxingZuxuan")){
//			SscSanxingZuxuanAnalyse sxzu = new SscSanxingZuxuanAnalyse();
//			if(playMode.equals("zu3")){
//				String str =  sxzu.zu3(tzNum);
//				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu3",str,"","","1"));
//			}
//			if(playMode.equals("zu6")){
//				String str =  sxzu.zu6(tzNum);
//				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu6",str,"","","1"));
//			}		
//			if(playMode.equals("baodan")){
//				if(tzNum.indexOf(",") != -1){//包2胆
//					resultList = sxzu.baodan2List(tzNum);					
//				}else{
//					//包胆1中包括了三种玩法，三星直选，组3，组6，因此做此限制
//					resultList =  sxzu.baodan1List(tzNum);					
//				}
//			}
//		}
//		//三星和值
//		if(playType.equals("sanxingZuxuan")){
//			SscSanxingZuxuanAnalyse sxzu = new SscSanxingZuxuanAnalyse();			
//			if(playMode.equals("hezhi")){			 
//				resultList = sxzu.hezhiList(tzNum);			
//			}
//		}
		
		///////////////////////////////////////////////////二星/////////////////////////////////////////////////////////////////////////
		if(playType.equals("erxing")){			
			//二星直选
			if(typeName.equals("zhixuan")){
				SscErxingZhixuanAnalyse erxzhi = new SscErxingZhixuanAnalyse();
				if(playMode.equals("fushi")){
					
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = erxzhi.fusi(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
						str = str + erxzhi.fusi(tzNum);
					}
					
					resultList.add(new PlayNumInfo("ssc","erxing","zhixuanfushi","zhixuan",str,"","",""));
					
				}
				if(playMode.equals("danshi")){
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = erxzhi.dansi(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
						str = str + erxzhi.dansi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","erxing","zhixuandanshi","zhixuan",str,"","",""));
				}
			}
			//二星组选
			if(typeName.equals("zuxuan")){
				SscErxingZuxuanAnalyse erxzu = new SscErxingZuxuanAnalyse();
				if(playMode.equals("fushi")){
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = erxzu.fusi(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
						str = str + erxzu.fusi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","erxing","zuxuanfushi","zuxuan",str,"","","1"));
				}
 
				if(playMode.equals("danshi")){
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = erxzu.dansi(tempNum);
							if(temp != null && !temp.equals("")){
								str = str + temp;
							}
						}
					}else{
						str = str + erxzu.dansi(tzNum);
					}
					resultList.add(new PlayNumInfo("ssc","erxing","zuxuandanshi","zuxuan",str,"","","1"));
				}
				//二星和值
				if(playMode.equals("hezhi")){
//					String str = erxzu.hezhi(tzNum);
//					resultList.add(new PlayNumInfo("ssc","erxing","zuxuan","danshi",str,"","","1"));
					//resultList = erxzu.hezhiList(tzNum);
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){					
							String tempNum = aryNum[i];
							List<PlayNumInfo> tempList = erxzu.hezhiList(tempNum);		
							for(PlayNumInfo pni : tempList){
								resultList.add(pni);
							}
						}
					}else{
						resultList = erxzu.hezhiList(tzNum);
					}
				}
			}
		}
		////////////////////////////////////////////一星//////////////////////////////////////////////////////////////////////////////////
		if(playType.equals("yixing")){			
			//一星直选
			if(typeName.equals("zhixuan")){
				SscYixingAnalyse yizhi = new SscYixingAnalyse();
				if(playMode.equals("fushi")){
					//String str = erxzhi.fusi(tzNum);
					
					String str = ",";		
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = yizhi.fusi(tempNum);
							if(temp != null && !temp.equals("")){
//								if(str.equals("")){
//									str = str + temp;
//								}else{
//									str = str + "," + temp;
//								}
								str = str + temp;
							}
						}
					}else{
						str = str + yizhi.fusi(tzNum);
					}
					
					resultList.add(new PlayNumInfo("ssc","yixing","yixingzhixuan","zhixuan",str,"","",""));
					
				}
			}
		}
		
		////////////////////////////////////////////组合//////////////////////////////////////////////////////////////////////////////////
		if(typeName.equals("zuhe")){
			SscZuheAnalyse zh = new SscZuheAnalyse();			
			if(tzNum.indexOf("$") != -1){
				String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
				for(int i = 0; i < aryNum.length; i++){					
					String tempNum = aryNum[i];					
					//组合五星
					if(playType.equals("wuxing")){
						//resultList = zh.zuheList(tzNum, num)					
						List<PlayNumInfo> tempList = zh.zuheList(tempNum, 5);	
						for(PlayNumInfo pni : tempList){
							resultList.add(pni);
						}
					}
					//组合四星
					if(playType.equals("sixing")){
						//resultList = zh.zuheList(tzNum, num)					
						List<PlayNumInfo> tempList = zh.zuheList(tempNum, 4);	
						for(PlayNumInfo pni : tempList){
							resultList.add(pni);
						}
					}
					//组合三星
					if(playType.equals("sanxing")){
						//resultList = zh.zuheList(tzNum, num)					
						List<PlayNumInfo> tempList = zh.zuheList(tempNum, 3);	
						for(PlayNumInfo pni : tempList){
							resultList.add(pni);
						}
					}
					//组合二星
					if(playType.equals("erxing")){
						//resultList = zh.zuheList(tzNum, num)					
						List<PlayNumInfo> tempList = zh.zuheList(tempNum, 2);	
						for(PlayNumInfo pni : tempList){
							resultList.add(pni);
						}
					}
				}
			}else{
				if(playType.equals("wuxing")){
					resultList = zh.zuheList(tzNum, 5);						
				}
				//组合四星
				if(playType.equals("sixing")){
					resultList = zh.zuheList(tzNum, 4);	
				}
				//组合三星
				if(playType.equals("sanxing")){
					resultList = zh.zuheList(tzNum, 3);	
				}
				//组合二星
				if(playType.equals("erxing")){
					resultList = zh.zuheList(tzNum, 2);	
				}
			}
		}
		////////////////////////////////////大小单双///////////////////////////////
		if(playType.equals("dxds")){
			SscZuheAnalyse zh = new SscZuheAnalyse();	
			String str = ",";
			if(tzNum.indexOf("$") != -1){
				String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
				for(int i = 0; i < aryNum.length; i++){					
					String tempNum = aryNum[i];					
					//大小单双
					String temp = zh.dxds(tempNum);
					if(temp != null && !temp.equals("")){
//						if(str.equals("")){
//							str = str + temp;
//						}else{
//							str = str  + temp+ ",";
//						}
						str = str + temp;
					}
				}
			}else{
				str = str + zh.dxds(tzNum);
			}
			resultList.add(new PlayNumInfo("ssc","dxds","","zhixuan",str,"","",""));
		}
		////////////////////////////////////任选///////////////////////////////
		if(playType.equals("renxuan1") || playType.equals("renxuan2") || playType.equals("renxuan3")){
			SscRenxuanAnalyse rx = new SscRenxuanAnalyse();	
			String str = ",";
			if(tzNum.indexOf("$") != -1){
				String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
				for(int i = 0; i < aryNum.length; i++){					
					String tempNum = aryNum[i];					
					//任选
					String temp = rx.renxuan(tempNum,playType);
					if(temp != null && !temp.equals("")){
						str = str + temp;
					}
				}
			}else{

				str = str + rx.renxuan(tzNum,playType);
			}
			str = str + ",";
			resultList.add(new PlayNumInfo("ssc",playType,"",playType,str,"","",""));
		}
		return resultList;
	}
	/**
	 * 生成12x5的号码
	 * @param tzNum
	 * @param playType
	 * @param zuhe
	 */
	public List<PlayNumInfo> builderSxwNumList(String tzNum,String playType,String playMode){
		List<PlayNumInfo> resultList = new ArrayList<PlayNumInfo>();
		SxwAnalyse sxwAnly = new SxwAnalyse();
		String str = "|";					
		if(tzNum.indexOf("$") != -1){
			String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
			for(int i = 0; i < aryNum.length; i++){				
				String tempNum = aryNum[i];
				if(tempNum.indexOf("-") != -1){//为胆拖投注
					
					String temp = sxwAnly.sxwDantuo(tempNum, playType,playMode);
					if(temp != null && !temp.equals("")){
						str = str + temp + "|";
					}
				}else{
					String temp = sxwAnly.sxwRenxuan(tempNum, playType,playMode);
					if(temp != null && !temp.equals("")){
						str = str + temp + "|";
					}
				}
			}
		}else{
			if(tzNum.indexOf("-") != -1){//为胆拖投注
				
				String temp = sxwAnly.sxwDantuo(tzNum, playType,playMode);
				if(temp != null && !temp.equals("")){
					str = str + temp + "|";
				}
			}else{
				String temp = sxwAnly.sxwRenxuan(tzNum, playType,playMode);
				if(temp != null && !temp.equals("")){
					str = str + temp + "|";
				}
			}
		}
		resultList.add(new PlayNumInfo("sxw",playType,"",playMode,str,"","",""));
		return resultList;
	}

	/**
	 * 生成20x8的号码
	 * @param playType
	 * @param playMode
	 * @param tzNum
	 * @return 返回格式如"|2,3,4|1,3,10|"
	 */
	public List<PlayNumInfo> builderExbNumList(String playType,String playMode,String tzNum){
		List<PlayNumInfo> resultList = new ArrayList<PlayNumInfo>();
		ExbAnalyse exbAnly = new ExbAnalyse();
		String str = "|";					
		if(tzNum.indexOf("$") != -1){//如果一次投了多个号码，则进行分割			
			String[] aryNum = JStringToolkit.splitString(tzNum, "$");
			for(int i = 0; i < aryNum.length; i++){	
				String temp = exbAnly.builderNumber(playType, playMode, aryNum[i]);
				if(temp != null && !temp.equals("")){
					str = str + temp + "|";
				}
			}
		}else{
			String temp = exbAnly.builderNumber(playType, playMode, tzNum);
			if(temp != null && !temp.equals("")){
				str = str + temp + "|";
			}
		}
		resultList.add(new PlayNumInfo("exb",playType,playMode,str));
		return resultList;
	}
	/***
	 * 仅供限号计算时使用
	 * @param tzNum
	 * @param playType
	 * @param typeName
	 * @param playMode
	 * @param zuhe
	 * @return
	 */
	public List<PlayNumInfo> builderZuxuanNumList(String tzNum,String playType,String typeName,String playMode,String zuhe){

		List<PlayNumInfo> resultList = new ArrayList<PlayNumInfo>();
		
		/////###################################五星/##############################//////////////////////
		if(playType.equals("wuxing")){
			 
			//五星组选
			if(typeName.equals("zuxuan")){
				SscWuxingZuxuanAnalyse wxzxAnly = new SscWuxingZuxuanAnalyse();
				//五星组选120
				if(playMode.equals("zu120")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = wxzxAnly.fusi(tempNum);
							str = str + temp;
							
						}
					}else{
						String temp = wxzxAnly.fusi(tzNum);						 
						str = str + temp;								  
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu120",str,"","",""));
				}
				//五星组选60
				if(playMode.equals("zu60")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxzxAnly.fusi(tempNum);
								str = str + temp;
							}
					}else{
						String temp = wxzxAnly.fusi(tzNum);
						str = str + temp;
					}
					str = makeWuxingZuxuanString(str,"zu60");
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu60",str,"","",""));
				}
				//五星组选30
				if(playMode.equals("zu30")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + wxzxAnly.fusi(tzNum);
					}
					str = makeWuxingZuxuanString(str,"zu30");
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu30",str,"","",""));
				}
				//五星组选20
				if(playMode.equals("zu20")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + wxzxAnly.fusi(tzNum);
					}
					str = makeWuxingZuxuanString(str,"zu20");
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu20",str,"","",""));
				}
				//五星组选10
				if(playMode.equals("zu10")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + wxzxAnly.fusi(tzNum);
					}
					str = makeWuxingZuxuanString(str,"zu10");
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu10",str,"","",""));
				}
				//五星组选5
				if(playMode.equals("zu5")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = wxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + wxzxAnly.fusi(tzNum);
					}
					str = makeWuxingZuxuanString(str,"zu5");
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zu5",str,"","",""));
				}
				//五星2重号全包
				if(playMode.equals("zuchong2")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];								
								String temp = JStringToolkit.insertDelimiter(tempNum, "",",");
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
						String temp = JStringToolkit.insertDelimiter(tzNum, "",",");
						if(temp != null && !temp.equals("")){
							str = str + temp;
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zuchong2",str,"","",""));
				}
				//五星3重号全包
				if(playMode.equals("zuchong3")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];								
								String temp = JStringToolkit.insertDelimiter(tempNum, "",",");
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
						String temp = JStringToolkit.insertDelimiter(tzNum, "",",");
						if(temp != null && !temp.equals("")){
							str = str + temp;
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zuchong3",str,"","",""));
				}
				//五星4重号全包 2011-10-11
				if(playMode.equals("zuchong4")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];								
								String temp = JStringToolkit.insertDelimiter(tempNum, "",",");
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
						String temp = JStringToolkit.insertDelimiter(tzNum, "",",");
						if(temp != null && !temp.equals("")){
							str = str + temp;
						}
					}
					resultList.add(new PlayNumInfo("ssc","wuxing","zuxuan","zuchong4",str,"","",""));
				}
			}
			
		}
		if(playType.equals("sixing")){
			 
			//四星组选
			if(typeName.equals("zuxuan")){
				SscSixingZuxuanAnalyse sxzxAnly = new SscSixingZuxuanAnalyse();
				//四星组选24
				if(playMode.equals("zu24")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
						String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
						for(int i = 0; i < aryNum.length; i++){				
							String tempNum = aryNum[i];
							String temp = sxzxAnly.fusi(tempNum);
							str = str + temp;
							
						}
					}else{
						String temp = sxzxAnly.fusi(tzNum);						 
						str = str + temp;								  
					}
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu24",str,"","",""));
				}
				//四星组选12
				if(playMode.equals("zu12")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + sxzxAnly.fusi(tzNum);
					}
					str = makeSixingZuxuanString(str,"zu12");
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu12",str,"","",""));
				}
				//四星组选6
				if(playMode.equals("zu6")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + sxzxAnly.fusi(tzNum);
					}
					str = makeSixingZuxuanString(str,"zu6");
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu6",str,"","",""));
				}
				//四星组选4
				if(playMode.equals("zu4")){
					String str = ",";					
					if(tzNum.indexOf("$") != -1){
							String[] aryNum = JStringToolkit.splitString(tzNum, "$");//如果一次投了多个号码，则进行分割
							for(int i = 0; i < aryNum.length; i++){				
								String tempNum = aryNum[i];
								String temp = sxzxAnly.fusi(tempNum);
								if(temp != null && !temp.equals("")){
									str = str + temp;
								}
							}
					}else{
							str = str + sxzxAnly.fusi(tzNum);
					}
					str = makeSixingZuxuanString(str,"zu4");
					resultList.add(new PlayNumInfo("ssc","sixing","zuxuan","zu4",str,"","",""));
				}
			}
		}
		return resultList;
	}
	/**
	 * 根据玩法类型，生成相应
	 * @param str，传入的值"12344,63128"
	 * @param type
	 * @return 传回的值为“1,2,3,4,5”，
	 */
	private String makeWuxingZuxuanString(String str,String type){
		String result = ",";
		String[] ary = JStringToolkit.splitString(str, ",");
		for(int i = 0; i < ary.length; i++){
			String temp = ary[i];
			if(temp != null && !temp.equals("")){
				//zu60为4位数，其中最后一个数字重复2次
				if(type.equals("zu60")){
					temp = temp + temp.substring(temp.length()-1,temp.length());
				}
				//zu30为3位数，其中最后二位数字重复2次
				if(type.equals("zu30")){
					temp = temp + temp.substring(temp.length()-2,temp.length());
				}
				//zu20为3位数，其中最后一个数字重复3次
				if(type.equals("zu20")){
					temp = temp + temp.substring(temp.length()-1,temp.length()) + temp.substring(temp.length()-1,temp.length());
				}
				//zu10为2位数，其中第一个数重复2次，最后一个数字重复3次
				if(type.equals("zu10")){
					temp = temp + temp + temp.substring(temp.length()-1,temp.length());
				}
				//zu5为2位数，其中最后一个数字重复4次
				if(type.equals("zu5")){
					temp = temp +  temp.substring(temp.length()-1,temp.length()) + temp.substring(temp.length()-1,temp.length()) + temp.substring(temp.length()-1,temp.length());
				}
			}
			result = result + temp + ",";
		}
		
		return result;
		
	}
	/**
	 * 根据玩法类型，生成相应
	 * @param str，传入的值"123"
	 * @param type
	 * @return 传回的值为“1,2,3,3”，
	 */
	private String makeSixingZuxuanString(String str,String type){
		String result = ",";
		String[] ary = JStringToolkit.splitString(str, ",");
		for(int i = 0; i < ary.length; i++){
			String temp = ary[i];
			if(temp != null && !temp.equals("")){
				
				//zu12为3位数，其中最后一位数字重复2次
				if(type.equals("zu12")){
					temp = temp + temp.substring(temp.length()-1,temp.length());
				}
				//zu6为2位数，其中第一个数重复2次，最后一个数字重复2次
				if(type.equals("zu6")){
					temp = temp + temp ;
				}
				//zu5为2位数，其中最后一个数字重复3次
				if(type.equals("zu4")){
					temp = temp +  temp.substring(temp.length()-1,temp.length()) + temp.substring(temp.length()-1,temp.length());
				}
			}
			result = result + temp + ",";
		}
		
		return result;
		
	}
}
