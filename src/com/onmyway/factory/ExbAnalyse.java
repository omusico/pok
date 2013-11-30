package com.onmyway.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dbconnpac.ConstantSymbol;

import util.JStringToolkit;
import util.PailieArith;
import util.ZuheArith;

/**
 * @Title:20选8
 * @Description: 快乐十分
 * @Create on: Dec 2, 2011 9:07:48 PM
 * @Author:LJY
 * @Version:1.0
 */
/**
 * 
xuan1  hongtou
	shutou
xuan2  
	renxuan
	renxuandantuo			
	lianzhi
	lianzu
	lianzudantuo

xuan3  
	renxuan
	renxuandantuo
	qianzhi
	qianzu
	qianzudantuo
xuan4
	renxuan
	renxuandantuo
xuan5
	renxuan
	renxuandantuo
 * 
 */
public class ExbAnalyse {

	private Logger log = Logger.getLogger(ExbAnalyse.class);
	ZuheArith zuhe = new ZuheArith();
	PailieArith pailie = new PailieArith();
	
	/**
	 * 对外的总的接口
	 * @param tzNum
	 * @param playType
	 * @param playMode
	 * @return
	 */
	public String builderNumber(String playType,String playMode,String tzNum){
		String result = "";
		if(playType.equals(ExbConstant.PLAY_TYPE_X1)){
			result = xuan1NumberBuilder(tzNum);
		}
		if(playType.equals(ExbConstant.PLAY_TYPE_X2)){
			result = xuan2NumberBuilder(tzNum,playMode);
		}

		if(playType.equals(ExbConstant.PLAY_TYPE_X3)){
			result = xuan3NumberBuilder(tzNum,playMode);
		}

		if(playType.equals(ExbConstant.PLAY_TYPE_X4)){
			result = xuan4NumberBuilder(tzNum,playMode);
		}

		if(playType.equals(ExbConstant.PLAY_TYPE_X5)){
			result = xuan5NumberBuilder(tzNum,playMode);
		}
		return result;
	}
	/**
	 * 选1的号码进行计算
	 * @param playNum 投注号码，每个号码以","分割，格式如:1,2,3,4,5 
	 * @return 整理后的投注号码，格式如"2|3|4|15"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 * 在此计算的最终结果，前后都不加"|",在AnalyseBuilder中将各个结果连接起来时，才在前后增加"|"
	 * 红投，数投都是如此
	 */
	public String xuan1NumberBuilder(String tzNum){
		if(tzNum == null || tzNum.equals("")){
			log.warn("快乐十分，任选1投注号码为空,请检查!");
			return null;
		}
		String[] aryResult = JStringToolkit.splitString(tzNum, ",");
		
		String result = "";
		for (String temp : aryResult) {
		 
			if (result.equals("")) {
				result = result + temp;
			} else {
				result = result + "|" + temp;
			}
		}
		return result;
	}
	/**
	 * 选2的号码进行计算
	 * @param playNum 投注号码，每个号码以","分割，格式如:1,2,3,4,5 
	 * @return 整理后的投注号码，格式如"2|3|4|15"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 * 在此计算的最终结果，前后都不加"|",在AnalyseBuilder中将各个结果连接起来时，才在前后增加"|"
	 * 红投，数投都是如此
	 */
	public String xuan2NumberBuilder(String tzNum,String playMode){
		if(tzNum == null || tzNum.equals("")){
			log.warn("快乐十分，任选2投注号码为空,请检查!");
			return null;
		}
		// 将投注号码转化为整型数组
		List list  = new ArrayList();
		if(playMode.equals(ExbConstant.X2_MODE_RENXUAN)){//任选，传入的格式为1,2,3,4,5,6,7
			int[] intAry = JStringToolkit.splitStrToInt(tzNum, ",");
			list = zuhe.combine(intAry, 2);
		}else if(playMode.equals(ExbConstant.X2_MODE_RENXUAN_DANTUO)){//任选胆拖 传入的格式为 胆:1-拖:3,4,5,6,7,8
			//将号码进行分解，得到胆码和拖码，
			String danma = tzNum.substring(1,tzNum.indexOf("-"));
			String tuoma = tzNum.substring(tzNum.indexOf("-")+2,tzNum.length());
			// 将拖码转化为数组
			String[] strDanmaAry = JStringToolkit.splitString(danma, ",");//任选2中，胆码只能有1个 
			String[] strTuomaAry = JStringToolkit.splitString(tuoma, ",");
			String[][] tempAry = {strDanmaAry,strTuomaAry};
			
			String tempResult = JStringToolkit.combAryStr(tempAry, ",","|",true);//数组的第一行与第二行进行组合
			return tempResult;//结果无需整理，直接返回
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZHI)){//连直 传入的格式为 1,2,3,4,5,6|3,4,5,6,7,8
			//直选前2是按照顺序，不允许进行排列组合
			String[][] ary = JStringToolkit.splitString(tzNum,"|",",",true);//将号码分成二维数组
			String tempResult = JStringToolkit.combAryStr(ary, ",","|",true);//数组的第一行与第二行进行组合
			return tempResult;//结果无需整理，直接返回
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU)){//连组 传入的格式为 1,2,3,4,5,6
			//组选前2是不按照顺序，需要进行排列组合
			int[] intAry = JStringToolkit.splitStrToInt(tzNum, ",");
			list = zuhe.combine(intAry, 2);
		}else if(playMode.equals(ExbConstant.X2_MODE_LIANZU_DANTUO)){//连组胆拖 传入的格式为 胆:1-拖:3,4,5,6,7,8
			//将号码进行分解，得到胆码和拖码，
			String danma = tzNum.substring(1,tzNum.indexOf("-"));
			String tuoma = tzNum.substring(tzNum.indexOf("-")+2,tzNum.length());
			// 将拖码转化为数组
			String[] strDanmaAry = JStringToolkit.splitString(danma, ",");//任选2中，胆码只能有1个 
			String[] strTuomaAry = JStringToolkit.splitString(tuoma, ",");
			String[][] tempAry = {strDanmaAry,strTuomaAry};
			
			String tempResult = JStringToolkit.combAryStr(tempAry, ",","|",true);//数组的第一行与第二行进行组合		
			return tempResult;//结果无需整理，直接返回;
		}
		String result = "";
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
	 * 选2的号码进行计算
	 * @param playNum 投注号码，每个号码以","分割，格式如:1,2,3,4,5 
	 * @return 整理后的投注号码，格式如"2|3|4|15"等，因为有10,11,12等3个两位数，所以每个投注号码之间以“，”分割
	 * 在此计算的最终结果，前后都不加"|",在AnalyseBuilder中将各个结果连接起来时，才在前后增加"|"
	 * 红投，数投都是如此
	 */
	public String xuan3NumberBuilder(String tzNum,String playMode){
		if(tzNum == null || tzNum.equals("")){
			log.warn("快乐十分，任选3投注号码为空,请检查!");
			return null;
		}
		// 将投注号码转化为整型数组
		List list  = new ArrayList();
		if(playMode.equals(ExbConstant.X3_MODE_RENXUAN)){//任选，传入的格式为1,2,3,4,5,6,7
			int[] intAry = JStringToolkit.splitStrToInt(tzNum, ",");
			list = zuhe.combine(intAry, 3);
		}else if(playMode.equals(ExbConstant.X3_MODE_RENXUAN_DANTUO)){//任选胆拖 传入的格式为 "胆1-拖3,4,5"或者"胆1,2-拖3,5,6"
			//将号码进行分解，得到胆码和拖码，
			String danma = tzNum.substring(1,tzNum.indexOf("-"));
			String tuoma = tzNum.substring(tzNum.indexOf("-")+2,tzNum.length());
			// 将拖码转化为整型数组
			int[] intDanmaAry = JStringToolkit.splitStrToInt(danma, ",");//胆码也可能有多个
			int danmaAryLen = intDanmaAry.length;
			int[] intTuomaAry = JStringToolkit.splitStrToInt(tuoma, ",");
			
			list = zuhe.combine(intTuomaAry, 3-danmaAryLen);//拖码中任选一个与胆码进行组合		
			
			return  combDanTuo(list,intDanmaAry,3);
			
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZHI)){//连直 传入的格式为 1,2,3,4,5,6|3,4,5,6,7,8|11,13,14
			//直选前3是按照顺序，不允许进行排列
			String[][] ary = JStringToolkit.splitString(tzNum,"|",",",true);//将号码分成二维数组
			String tempResult = JStringToolkit.combAryStr(ary, ",","|",true);//数组的第1行与第2行与第3行进行组合
			return tempResult;//结果无需整理，直接返回
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU)){//连组 传入的格式为 1,2,3,4,5,6
			//组选前3是不按照顺序，需要进行排列组合
			int[] intAry = JStringToolkit.splitStrToInt(tzNum, ",");
			list = zuhe.combine(intAry, 3);
		}else if(playMode.equals(ExbConstant.X3_MODE_QIANZU_DANTUO)){//连组胆拖 传入的格式为 胆:1-拖:3,4,5,6,7,8
			//将号码进行分解，得到胆码和拖码，
			String danma = tzNum.substring(1,tzNum.indexOf("-"));
			String tuoma = tzNum.substring(tzNum.indexOf("-")+2,tzNum.length());
			// 将拖码转化为整型数组
			int[] intDanmaAry = JStringToolkit.splitStrToInt(danma, ",");//胆码也可能有多个
			int danmaAryLen = intDanmaAry.length;
			int[] intTuomaAry = JStringToolkit.splitStrToInt(tuoma, ",");
			
			list = zuhe.combine(intTuomaAry, 3-danmaAryLen);//拖码中任选一个与胆码进行组合		
			
			return  combDanTuo(list,intDanmaAry,3);
		}
		String result = reDealResult(list);
	
		return result;
	}
	/**
	 * 任选4
	 * @param tzNum
	 * @param playMode
	 * @return
	 */
	public String xuan4NumberBuilder(String tzNum,String playMode){
		if(tzNum == null || tzNum.equals("")){
			log.warn("快乐十分，任选4投注号码为空,请检查!");
			return null;
		}
		List list  = new ArrayList();
		if(playMode.equals(ExbConstant.X4_MODE_RENXUAN)){//任选，传入的格式为1,2,3,4,5,6,7
			int[] intAry = JStringToolkit.splitStrToInt(tzNum, ",");
			list = zuhe.combine(intAry, 4);
		}else if(playMode.equals(ExbConstant.X4_MODE_RENXUAN_DANTUO)){//任选，传入的格式为1,2,3,4,5,6,7
			//将号码进行分解，得到胆码和拖码，
			String danma = tzNum.substring(1,tzNum.indexOf("-"));
			String tuoma = tzNum.substring(tzNum.indexOf("-")+2,tzNum.length());
			// 将拖码转化为整型数组
			int[] intDanmaAry = JStringToolkit.splitStrToInt(danma, ",");//胆码也可能有多个
			int danmaAryLen = intDanmaAry.length;
			int[] intTuomaAry = JStringToolkit.splitStrToInt(tuoma, ",");
			
			list = zuhe.combine(intTuomaAry, 4-danmaAryLen);//拖码中任选一个与胆码进行组合		
			
			return  combDanTuo(list,intDanmaAry,4);
		}
		
		String result = reDealResult(list);
		
		return result;
	}
	/**
	 * 任选5
	 * @param tzNum
	 * @param playMode
	 * @return
	 */
	public String xuan5NumberBuilder(String tzNum,String playMode){
		if(tzNum == null || tzNum.equals("")){
			log.warn("快乐十分，任选5投注号码为空,请检查!");
			return null;
		}
		List list  = new ArrayList();
		if(playMode.equals(ExbConstant.X5_MODE_RENXUAN)){//任选，传入的格式为1,2,3,4,5,6,7
			int[] intAry = JStringToolkit.splitStrToInt(tzNum, ",");
			list = zuhe.combine(intAry, 5);
		}else if(playMode.equals(ExbConstant.X5_MODE_RENXUAN_DANTUO)){//任选，传入的格式为1,2,3,4,5,6,7
			//将号码进行分解，得到胆码和拖码，
			String danma = tzNum.substring(1,tzNum.indexOf("-"));
			String tuoma = tzNum.substring(tzNum.indexOf("-")+2,tzNum.length());
			// 将拖码转化为整型数组
			int[] intDanmaAry = JStringToolkit.splitStrToInt(danma, ",");//胆码也可能有多个
			int danmaAryLen = intDanmaAry.length;
			int[] intTuomaAry = JStringToolkit.splitStrToInt(tuoma, ",");
			
			list = zuhe.combine(intTuomaAry, 5-danmaAryLen);//拖码中任选一个与胆码进行组合		
			
			return  combDanTuo(list,intDanmaAry,5);
		}
		
		String result = reDealResult(list);
		
		return result;
		
	}
	/**
	 * 整理结果，将结果中每组数之间以"|"分割
	 * @param list
	 * @return 1,2,3|3,4,1|1,9,10
	 */
	private String reDealResult(List<int[]> list){
		String result = "";
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
	 * 将包含拖码的list与胆码进行组合，
	 * @param list 包含拖码的list
	 * @param intDanmaAry 胆码数组
	 * @param playTypeLen 玩法的长度，如任选3：长度为3
	 * @return 胆码与拖码合并进行排列
	 */
	private String combDanTuo(List<int[]> list,int[] intDanmaAry,int playTypeLen){
		String result = "";
		int danmaAryLen = intDanmaAry.length;
		// 整理结果
		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			//将胆码与拖码进行合并，合并成新的数组
			int[] combNewAry = new int[t.length+danmaAryLen];
			for(int k = 0; k < t.length; k++){
				combNewAry[k] = t[k];
			}
			int tempCount = 0;
			for(int k = t.length; k < combNewAry.length; k++){
				combNewAry[k] = intDanmaAry[tempCount++];
			}
			//tempAry[tempAry.length-1] = Integer.parseInt(danma);
			//对新数组进行从小到大排序
			JStringToolkit.intOrder(combNewAry, 1);
			
			//对结果进行组合即可，在计算时，将开奖结果按照相应的顺序进行组合即可
			String tempStr = "";
			for(int temp : combNewAry){
				if(tempStr.equals("")){
					tempStr = String.valueOf(temp);
				}else{
					tempStr = tempStr + "," + temp;
				}
			}
			
			
			if (result.equals("")) {
				result = result + tempStr;
			} else {
				result = result + "|" + tempStr;
			}
			/*
			String temp = "";
			//应该先见所有的胆码与拖码分别进行组合后，再将组合后的值分别进行排列
			//如果是任选2并且是组选前2胆拖，则进行重新计算如果是任选2并且是组选前2胆拖，则进行重新计算
			List<String> tList = new ArrayList<String>();
		
			pailie.permutation(tList,combNewAry, playTypeLen,"1");
			for(String ss : tList){
				if (result.equals("")) {
					result = result + ss;
				} else {
					result = result + "|" + ss;
				}
			}	
			*/
		}
		return result;
	}
}
