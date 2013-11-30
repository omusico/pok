package com.onmyway.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import util.JStringToolkit;
import util.PailieArith;
import util.ZuheArith;
import dbconnpac.ConstantSymbol;

/**
 * @Title:12选5投注号码计算
 * 包含了任选1,任选2,任选3,任选4,任选5,及任选二至任选五加入胆拖投注
 * @Description: 胆拖投注注数计算公式：玩法投注个数为k,胆码个数为n(n=1)，拖码个数为m（k-n≤m≤11），则此胆拖投注的注数个数为：combin(m,k-n) 
 * @Create on: Oct 19, 2010 2:40:05 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SxwAnalyse {
	private Logger log = Logger.getLogger(SxwAnalyse.class);
	/**
	 * 
	 * @param playNum 投注号码，每个号码以","分割，格式如:1,2,3,4,5
	 * @param playType 玩法名称，任选1，任选2，任选3，任选4，任选5
	 * @return 整理后的投注号码，格式如"1,2|1,3|1,4|3,5"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 */
	public String sxwRenxuan(String playNum,String playType){
		if(playNum == null || playNum.equals("")){
			log.warn("12x5，投注号码为空,请检查!");
			return null;
		}
		
		ZuheArith za = new ZuheArith();
		// 将投注号码转化为整型数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		String result = "";
		List list  = new ArrayList();
		//任选1
		if(playType.equals(ConstantSymbol.RX1)){
			list = za.combine(intAry, 1);
		}else if(playType.equals(ConstantSymbol.RX2)){
			list = za.combine(intAry, 2);
		}else if(playType.equals(ConstantSymbol.RX3)){
			list = za.combine(intAry, 3);
		}else if(playType.equals(ConstantSymbol.RX4)){
			list = za.combine(intAry, 4);
		}else if(playType.equals(ConstantSymbol.RX5)){
			list = za.combine(intAry, 5);
		}
		
		// 整理结果
		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			String temp = "";
			for (int m : t) {
				if (temp.equals("")) {
					temp = String.valueOf(m);
				} else {
					temp = temp + "," + m;
				}
			}
			if (result.equals("")) {
				result = result + temp;
			} else {
				result = result + "|" + temp;
			}
		}
			
		return result;
	}
	/**
	 * 
	 * @param playNum 投注号码，每个号码以","分割，格式如:1,2,3,4,5
	 * @param playType 玩法名称，任选1，任选2，任选3，任选4，任选5
	 * @return 整理后的投注号码，格式如"1,2|1,3|1,4|3,5"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 */
	public String sxwRenxuan(String playNum,String playType,String playMode){
		if(playNum == null || playNum.equals("")){
			log.warn("12x5，投注号码为空,请检查!");
			return null;
		}
		
		ZuheArith za = new ZuheArith();
		PailieArith pl = new PailieArith();
		// 将投注号码转化为整型数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		String result = "";
		List list  = new ArrayList();
		//任选1
		if(playType.equals(ConstantSymbol.RX1)){
			list = za.combine(intAry, 1);
		}else if(playType.equals(ConstantSymbol.RX2)){
			if(playMode.equals("fushi")){
				list = za.combine(intAry, 2);
			}else if(playMode.equals("zhiqian2")){
				//直选前2是按照顺序，不允许进行排列组合
				//list = za.combine(intAry, 2);
				String[][] ary = JStringToolkit.splitString(playNum,"|",",",true);
				String touzhu = JStringToolkit.combAryStr(ary, ",","|",true);
				return touzhu;
				
			}else if(playMode.equals("zuqian2")){
				//组选前2是不按照顺序，需要进行排列组合
				List<String> tempList = new ArrayList<String>();
				pl.permutation(tempList,intAry, 2,"1");
				list = strToIntList(tempList);
			}
		}else if(playType.equals(ConstantSymbol.RX3)){
			
			if(playMode.equals("fushi")){
				list = za.combine(intAry, 3);
			}else if(playMode.equals("zhiqian3")){ 
				//直选前3是按照顺序，不允许进行排列组合
				//list = za.combine(intAry, 2);
				String[][] ary = JStringToolkit.splitString(playNum,"|",",",true);
				String touzhu = JStringToolkit.combAryStr(ary, ",","|",true);
				return touzhu;
			}else if(playMode.equals("zuqian3")){
				//组选前3是不按照顺序，需要进行排列组合
				List<String> tempList = new ArrayList<String>();
				pl.permutation(tempList,intAry, 3,"1");
				list = strToIntList(tempList);
			}
		}else if(playType.equals(ConstantSymbol.RX4)){
			list = za.combine(intAry, 4);
		}else if(playType.equals(ConstantSymbol.RX5)){
			list = za.combine(intAry, 5);
		}else if(playType.equals(ConstantSymbol.RX6)){
			list = za.combine(intAry, 6);
		}else if(playType.equals(ConstantSymbol.RX7)){
			list = za.combine(intAry, 7);
		}else if(playType.equals(ConstantSymbol.RX8)){
			list = za.combine(intAry, 8);
		}
		
		// 整理结果
		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			String temp = "";
			for (int m : t) {
				if (temp.equals("")) {
					temp = String.valueOf(m);
				} else {
					temp = temp + "," + m;
				}
			}
			if (result.equals("")) {
				result = result + temp;
			} else {
				result = result + "|" + temp;
			}
		}
			
		return result;
	}
	public List strToIntList(List<String> list){
		List resultList = new ArrayList();
		for(String str : list){
			String[] aa = str.split(",");
			int[] intStr = new int[aa.length];
			for(int i = 0; i < aa.length; i++){
				intStr[i] = Integer.parseInt(aa[i]);
			}
			resultList.add(intStr);
		}
		return resultList;
	}
	/**
	 * 12x5胆拖计算
	 * 胆拖投注注数计算公式：玩法投注个数为k,胆码个数为n(n=1)，拖码个数为m（k-n≤m≤10），则此胆拖投注的注数个数为：combin(m,k-n)
	 * @param playNum：5-2,3,8,9,胆码和拖码中以"-"分割
	 * @param playType:玩法名称，任选2，任选3，任选4，任选5（任选一没有拖胆玩法）
	 * @return 整理后的投注号码，格式如"5,2|5,3|5,8|5,9"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 */
	public String sxwDantuo(String playNum,String playType){
		if(playNum == null || playNum.equals("")){
			log.warn("12x5，胆拖投注号码为空,请检查!");
			return null;
		}
		//将号码进行分解，得到胆码和拖码，
		String danma = playNum.substring(1,playNum.indexOf("-"));
		String tuoma = playNum.substring(playNum.indexOf("-")+2,playNum.length());
		ZuheArith za = new ZuheArith();
		// 将拖码转化为整型数组
		int[] intDanmaAry = JStringToolkit.splitStrToInt(danma, ",");//胆码也可能有多个
		int danmaAryLen = intDanmaAry.length;
		int[] intTuomaAry = JStringToolkit.splitStrToInt(tuoma, ",");
		String result = "";
		List list  = new ArrayList();
		//只有任选2，任选3，任选4，任选5才有拖胆玩法
		if(playType.equals(ConstantSymbol.RX2)){
			list = za.combine(intTuomaAry, 2-danmaAryLen);//拖码中任选一个与胆码进行组合
		}else if(playType.equals(ConstantSymbol.RX3)){
			list = za.combine(intTuomaAry, 3-danmaAryLen);//拖码中任选n个与胆码进行组合
		}else if(playType.equals(ConstantSymbol.RX4)){
			list = za.combine(intTuomaAry, 4-danmaAryLen);
		}else if(playType.equals(ConstantSymbol.RX5)){
			list = za.combine(intTuomaAry, 5-danmaAryLen);
		}else if(playType.equals(ConstantSymbol.RX6)){
			list = za.combine(intTuomaAry, 6-danmaAryLen);			
		}else if(playType.equals(ConstantSymbol.RX7)){
			list = za.combine(intTuomaAry, 7-danmaAryLen);			
		}else if(playType.equals(ConstantSymbol.RX8)){
			list = za.combine(intTuomaAry, 8-danmaAryLen);			
		}
		
		// 整理结果
		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			//将胆码与拖码进行合并，合并成新的数组
			int[] tempAry = new int[t.length+danmaAryLen];
			for(int k = 0; k < t.length; k++){
				tempAry[k] = t[k];
			}
			int tempCount = 0;
			for(int k = t.length; k < tempAry.length; k++){
				tempAry[k] = intDanmaAry[tempCount++];
			}
			//tempAry[tempAry.length-1] = Integer.parseInt(danma);
			//对新数组进行从大到小排序
			JStringToolkit.intOrder(tempAry, 1);
			
			String temp = "";
			
			for (int m : tempAry) {
				if (temp.equals("")) {
					temp = String.valueOf(m);
				} else {
					temp = temp + "," + m;
				}
			}
			if (result.equals("")) {
				result = result + temp;
			} else {
				result = result + "|" + temp;
			}
		}
		return result;
	}
	
	/**
	 * 12x5胆拖计算,增加了playMode参数
	 * 胆拖投注注数计算公式：玩法投注个数为k,胆码个数为n(n=1)，拖码个数为m（k-n≤m≤10），则此胆拖投注的注数个数为：combin(m,k-n)
	 * @param playNum：5-2,3,8,9,胆码和拖码中以"-"分割
	 * @param playType:玩法名称，任选2，任选3，任选4，任选5（任选一没有拖胆玩法）
	 * @return 整理后的投注号码，格式如"5,2|5,3|5,8|5,9"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 */
	public String sxwDantuo(String playNum,String playType,String playMode){
		if(playNum == null || playNum.equals("")){
			log.warn("12x5，胆拖投注号码为空,请检查!");
			return null;
		}
		//将号码进行分解，得到胆码和拖码，
		String danma = playNum.substring(1,playNum.indexOf("-"));
		String tuoma = playNum.substring(playNum.indexOf("-")+2,playNum.length());
		ZuheArith za = new ZuheArith();
		// 将拖码转化为整型数组
		int[] intDanmaAry = JStringToolkit.splitStrToInt(danma, ",");//胆码也可能有多个
		int danmaAryLen = intDanmaAry.length;
		int[] intTuomaAry = JStringToolkit.splitStrToInt(tuoma, ",");
		String result = "";
		List list  = new ArrayList();
		//只有任选2，任选3，任选4，任选5才有拖胆玩法
		if(playType.equals(ConstantSymbol.RX2)){			
			list = za.combine(intTuomaAry, 2-danmaAryLen);//拖码中任选一个与胆码进行组合			
		}else if(playType.equals(ConstantSymbol.RX3)){
			list = za.combine(intTuomaAry, 3-danmaAryLen);//拖码中任选n个与胆码进行组合
		}else if(playType.equals(ConstantSymbol.RX4)){
			list = za.combine(intTuomaAry, 4-danmaAryLen);
		}else if(playType.equals(ConstantSymbol.RX5)){
			list = za.combine(intTuomaAry, 5-danmaAryLen);
		}else if(playType.equals(ConstantSymbol.RX6)){
			list = za.combine(intTuomaAry, 6-danmaAryLen);			
		}else if(playType.equals(ConstantSymbol.RX7)){
			list = za.combine(intTuomaAry, 7-danmaAryLen);			
		}else if(playType.equals(ConstantSymbol.RX8)){
			list = za.combine(intTuomaAry, 8-danmaAryLen);			
		}
		
		// 整理结果
		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			//将胆码与拖码进行合并，合并成新的数组
			int[] tempAry = new int[t.length+danmaAryLen];
			for(int k = 0; k < t.length; k++){
				tempAry[k] = t[k];
			}
			int tempCount = 0;
			for(int k = t.length; k < tempAry.length; k++){
				tempAry[k] = intDanmaAry[tempCount++];
			}
			//tempAry[tempAry.length-1] = Integer.parseInt(danma);
			//对新数组进行从大到小排序
			JStringToolkit.intOrder(tempAry, 1);
			
			String temp = "";
			//任选2中的组选前2有胆拖玩法，与普通的胆拖算法不同，应该先见所有的胆码与拖码分别进行组合后，再将组合后的值分别进行排列
			//如果是任选2并且是组选前2胆拖，则进行重新计算如果是任选2并且是组选前2胆拖，则进行重新计算
			if(playType.equals(ConstantSymbol.RX2) && playMode.equals("zuqian2dantuo")){//如果是任选2并且是组选前2胆拖，则进行重新计算
				List<String> tList = new ArrayList<String>();
				PailieArith pl = new PailieArith();
				pl.permutation(tList,tempAry, 2,"1");
				for(String ss : tList){
					if (result.equals("")) {
						result = result + ss;
					} else {
						result = result + "|" + ss;
					}
				}				
			}else if(playType.equals(ConstantSymbol.RX3) && playMode.equals("zuqian3dantuo")){//如果是任选3并且是组选前3胆拖，则进行重新计算
				List<String> tList = new ArrayList<String>();
				PailieArith pl = new PailieArith();
				pl.permutation(tList,tempAry, 3,"1");
				for(String ss : tList){
					if (result.equals("")) {
						result = result + ss;
					} else {
						result = result + "|" + ss;
					}
				}
			}else{
				for (int m : tempAry) {
					if (temp.equals("")) {
						temp = String.valueOf(m);
					} else {
						temp = temp + "," + m;
					}
				}
				if (result.equals("")) {
					result = result + temp;
				} else {
					result = result + "|" + temp;
				}
			}
		}
		return result;
	}
}
