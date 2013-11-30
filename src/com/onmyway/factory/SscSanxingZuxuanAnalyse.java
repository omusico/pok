package com.onmyway.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JStringToolkit;
import util.PailieArith;
import util.ZuheArith;

/**
 * @Title:三星组选，有 【组三，组六，包胆，和值，单式】 五种
 * @Description: 【玩法：】 组三：从0~9这10个号码里任选2个或以上的号码 组六：从0~9这10个号码里任选3个或以上的号码，奖金160元
 *               包胆：任选1~2个胆投注，奖金与开奖对应，豹子1000元 / 组三320元 / 组六160元
 *               和值：任意选择号码投注，奖金与开奖对应。豹子1000元 / 组三320元 / 组六160元
 *               单式：任意选择一个非豹子的三位数为一个投注号码，组三320元 / 组六160元。 【投注：】
 *               组三：[三星组三]1,2,3（6注） 组六：[三星组六]3,5,6，7（4注）
 *               包胆：[三星组选包胆]1(55注）,[三星组选包胆]1,2(10注) 和值：[三星组选和值]6(7注）
 *               单式：[三星组选单式]1,2,3(1注）
 * 
 * @Create on: Aug 10, 2010 8:43:12 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscSanxingZuxuanAnalyse extends PlayNumAnalyseFactory {
	SscSanxingZuxuanAnalyse sse;
	public static SscSanxingZuxuanAnalyse getInstance(){
		return new SscSanxingZuxuanAnalyse();
	}
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dansi(String playNum) {
		logger.info("三星组选单式--需要加上直选，组3，组6等判断!");
		if(playNum == null ){
			return "";
		}
		String str = "";
		if(playNum.indexOf(",") != -1){
			String[] temp = playNum.split(",");
//			for(String ss : temp){
//				str = str + ss;
//			}
			str = str + JStringToolkit.strSort(temp);
		}else{
			str = playNum;
			char[] temp = playNum.toCharArray();
			String[] t = new String[temp.length];
			for(int i = 0; i < temp.length; i++){
				t[i] = Character.toString(temp[i]);
			}
			str = str + JStringToolkit.strSort(t);
		}
		return str+",";
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

	/**
	 * 和值：
	 */
	@Override
	public Map<String,String> hezhi(String playNum) {
		
		if(playNum == null){
			return null;
		}
		String result = "";
		String hezhiZhixuan = "";
		String hezhiZu3 = "";
		String hezhiZu6 = "";
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		for(int i = 0; i < intAry.length; i++){
			int num = intAry[i];
			for(int j = 0; j <= num/2; j++){
				for(int k = j; k <= num/2; k++){
					for(int m = k; m <= num; m++){
						String temp = "";
						if(j+k+m == num){
							temp = j + "," + k + "," + m;	
							//三个都相等 直选 豹子
							if(j == k && k == m){
								hezhiZhixuan = hezhiZhixuan + temp + "|";
							}
							//有两个相等，有一个不同，组3
							if((j == k || k == m || j == m) && !(j == k && k == m)){
								hezhiZu3 = hezhiZu3 + temp + "|";
							}
							//三个各不相等，组6
							if((j != k && k != m && j != m)){
								hezhiZu6 = hezhiZu6 + temp + "|";
							}
						}
					}
				}
			}
			
		}
		if(!hezhiZhixuan.equals("")){
			hezhiZhixuan = hezhiZhixuan.substring(0,hezhiZhixuan.length()-1);
		}
		if(!hezhiZu3.equals("")){
			hezhiZu3 = hezhiZu3.substring(0,hezhiZu3.length()-1);
		}
		if(!hezhiZu6.equals("")){
			hezhiZu6 = hezhiZu6.substring(0,hezhiZu6.length()-1);
		}
		
//		System.out.println("zhixuan="+hezhiZhixuan);
//		System.out.println("zu3="+hezhiZu3);
//		System.out.println("zu6="+hezhiZu6);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("hezhiZhixuan", hezhiZhixuan);
		map.put("hezhiZu3", hezhiZu3);
		map.put("hezhiZu6", hezhiZu6);
		return map;
	}

	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 三星组选 组3算法(排列）
	 * 从选择的数中，任选出2个进行排列，其中一个为重复，如“2,3，4”，则有“233,244,322,344,422,433”等
	 * 只保存2个数
	 * @param playNum
	 * @return"23,56,"
	 */
	public String zu3(String playNum) {
		// 需要排列的个数,此中需要任选2个进行排列
		int geshu = 2;
		if (playNum == null) {
			return null;
		}
		
		// 将其分割，分割成一维数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		List<String> list = new ArrayList<String>();
		PailieArith pl = new PailieArith();
		pl.permutation(list,intAry, geshu);

		String result = "";
//		for (String str : list) {
//			//String temp = str	+ str.substring(str.lastIndexOf(","), str.length());
//			String temp = str	+ str.substring(str.length()-1,str.length());
//			//String temp = str;//不再保存分隔符“，” 2010-09-09
//			if (result.equals("")) {
//				result = result + temp;
//			} else {
//				result = result + "," + temp;
//			}
//		}
		//只保存两位数2010-09-20
		for (String str : list) {
//			if (result.equals("")) {
//				result = result + str;
//			} else {
//				result = result + "," + str;
//			}
			result = result + str + ",";//后加分隔符
		}

		return result;
	}

	/**
	 * 三星组选 组6算法（组合） 从选择的数中，任选出3个进行组合，不能重复，如“2,3，4，5”，则有“234,235,245,345”等4中组合
	 * 
	 * @param playNum
	 * @return "123,456,"
	 */
	public String zu6(String playNum) {
		int geshu = 3;
		if (playNum == null) {
			return null;
		}
		
		String result = "";
	
		// 转化为整型数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		// 进行组合运算
		ZuheArith za = new ZuheArith();
		List list = za.combine(intAry, geshu);
		// 整理结果
		//前后加分隔符"," 2010-09-20
		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			String temp = "";
			for (int m : t) {
				//temp += m + ",";
				temp += m;//每注之间去掉分割符","2010-09-09
			}

//			if (result.equals("")) {
//				result = result + temp;
//			} else {
//				result = result + "," + temp;
//			}
				result = result  + temp+ ",";
		}
		
		return result;
	}

	/**
	 * 三星组选 包一胆 【三星组选包胆】1;【三星组选包胆】3,4;
	 * 组选包胆，包一个胆要110元，即包含一注直选（三个数相同），18注组三（两个数相同），36注组六（三个数各不相同），
	 * 
	 * @param playNum，只有一个数字
	 * @return
	 */
	public Map<String,String> baodan1(String playNum) {
		Map<String,String> map = new HashMap<String,String>();
		// 如包胆1，则
		// 直选（三个数相同）111
		// 18注组3（两个数相同）001,110,112,221,113,331,114,441,115,551,116,661,117,771,118,881,119,991
		// 36注组6（各不相同）组合：C(9,2)=36
		int geshu = 3;
		if (playNum == null) {
			return null;
		}
		int intPlayNum = Integer.parseInt(playNum);
		int[] aryAll = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// ------直选------
//		String resultZhixuan = playNum + "," + playNum + "," + playNum;
		String resultZhixuan = playNum + "" + playNum + "" + playNum;//不要分割符
		
		map.put("baodan1$Zhixuan", resultZhixuan);
		// 得到其余的9个数的数组
		String resultZu3 = "";
		int[] aryNine = new int[9];
		int tempI = 0;
		for (int m : aryAll) {
			if (m != intPlayNum) {
				aryNine[tempI++] = m;
			}
		}

		// ----组3:当前包的胆码与其他的9个数进行组合------
		// 将胆码与数组9中中的每一个进行组3排列
		int geshu2 = 2;
		PailieArith pl = new PailieArith();
		for (int m : aryNine) {
			int[] tempAry = { intPlayNum, m };
			List<String> list = new ArrayList<String>();
			 pl.permutation(list,tempAry, geshu2);

			for (String str : list) {
//				String temp = str + str.substring(str.lastIndexOf(","), str.length());
				String temp = str + str.substring(str.length()-1,str.length());
				//String temp = str ;
				if (resultZu3.equals("")) {
					resultZu3 = resultZu3 + temp;
				} else {
					resultZu3 = resultZu3 + "," + temp;
				}
			}
		}

		map.put("baodan1$Zu3", resultZu3);
		//resultList.add(new PlayNumInfo("ssc","sanxing","zu3",resultZu3,"bao1dan","",""));

		// ----组6：当前包的胆码与其他的9个数进行排列------
		// 思路：首先对数组9中的任意2个数进行组合，再将胆码按照顺序加入到每组数据中
		// 进行组合运算
		ZuheArith za = new ZuheArith();
		List list = za.combine(aryNine, 2);
		// 整理结果
		String resultZu6 = "";

		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			// 将胆码按大小顺序插入数组中
			int[] resultAry = new int[t.length + 1];

			//String temp = "";
			for (int m = 0; m < t.length; m++) {
				resultAry[m] = t[m];
			}			
			resultAry[resultAry.length-1] = intPlayNum;
			//排序
			chaRu(resultAry);
			//整理
			
			// 整理结果
			//String result = "";

				
			String temp = "";
			for (int m : resultAry) {
//				temp += m + ",";
				temp += m ;//不要分割符
			}
//			if (!temp.equals("")) {
//				temp = temp.substring(0, temp.length() - 1);
//			}
			if (resultZu6.equals("")) {
				resultZu6 = resultZu6 + temp;
			} else {
				resultZu6 = resultZu6 + "," + temp;
			}
		
		}
		map.put("baodan1$Zu6", resultZu6);
		
//		System.out.println("zhixuan="+resultZhixuan);
//		System.out.println("zu3="+resultZu3);
//		System.out.println("zu6="+resultZu6);
		
		return map;
	}

	/**
	 * 三星组选 包2胆 【三星组选包胆】1;【三星组选包胆】3,4;
	 * 组选包胆，包一个胆要110元，即包含一注直选（三个数相同），18注组三（两个数相同），36注组六（三个数各不相同），
	 * 
	 * @param playNum，必须有两个数字
	 * @return
	 */
	public Map<String,String> baodan2(String playNum) {
		if(playNum == null){
			return null;
		}
		Map<String,String> map = new HashMap<String,String>();

		String hezhiZhixuan = "";
		String hezhiZu3 = "";
		String hezhiZu6 = "";
		// 转化为整型数组
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		//定义长度为3的数组
			
		String result = "";
		for(int i = 0; i <= 9; i++){
			int[] tt = new int[3];
			for(int j = 0; j < intAry.length; j++){
				tt[j] = intAry[j];
			}
			tt[tt.length-1] = i;
			chaRu(tt);
			//整理
			String temp = "";
			String temp1 = "";
			String temp2 = "";
			
			for(int m : tt){
				temp = temp + m + ",";
			}
			if(!temp.equals("")){
				temp = temp.substring(0,temp.length()-1);
			}
			
			if(tt[0] == tt[1] && tt[0] == tt[2]){
				hezhiZhixuan = hezhiZhixuan + temp + "|" ;
			}
			
			if((tt[0] == tt[1] || tt[0] == tt[2] || tt[1] == tt[2]) && ( tt[0] != tt[1] || tt[0] != tt[2] || tt[1] != tt[2])){
				hezhiZu3 = hezhiZu3  + temp + "|";
			}
			if(tt[0] != tt[1] && tt[0] != tt[2] && tt[1] != tt[2]){
				hezhiZu6 = hezhiZu6 + temp + "|" ;
			}
			
		}
		
		if(!hezhiZhixuan.equals("")){
			hezhiZhixuan = hezhiZhixuan.substring(0,hezhiZhixuan.length()-1);
		}
		if(!hezhiZu3.equals("")){
			hezhiZu3 = hezhiZu3.substring(0,hezhiZu3.length()-1);
		}
		if(!hezhiZu6.equals("")){
			hezhiZu6 = hezhiZu6.substring(0,hezhiZu6.length()-1);
		}
		map.put("baodan2$zhixuan", hezhiZhixuan);
		map.put("baodan2$zu3", hezhiZu3);
		map.put("baodan2$zu6", hezhiZu6);
		return map;
	}
	
	/**
	 * 三星组选 包一胆 【三星组选包胆】1;【三星组选包胆】3,4;
	 * 组选包胆，包一个胆要110元，即包含一注直选（三个数相同），18注组三（两个数相同），36注组六（三个数各不相同），
	 * 
	 * @param playNum，只有一个数字
	 * @return
	 */
	public List<PlayNumInfo> baodan1List(String playNum) {
		//Map<String,String> map = new HashMap<String,String>();
		List<PlayNumInfo>  resultList = new ArrayList<PlayNumInfo>();
		// 如包胆1，则
		// 直选（三个数相同）111
		// 18注组3（两个数相同）001,110,112,221,113,331,114,441,115,551,116,661,117,771,118,881,119,991
		// 36注组6（各不相同）组合：C(9,2)=36
		int geshu = 3;
		if (playNum == null) {
			return null;
		}
		int intPlayNum = Integer.parseInt(playNum);
		int[] aryAll = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// ------直选------
//		String resultZhixuan = playNum + "," + playNum + "," + playNum;
		String resultZhixuan = ","+playNum + "" + playNum + "" + playNum+",";//不要分割符 //前后加分隔符2010-09-20
		
		//map.put("baodan1$Zhixuan", resultZhixuan);
//		resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zhixuan",resultZhixuan,"bao1dan","",""));
		resultList.add(new PlayNumInfo("ssc","sanxing","bao1dan","zhixuan",resultZhixuan,"bao1dan","",""));
		// 得到其余的9个数的数组
		String resultZu3 = ",";
		int[] aryNine = new int[9];
		int tempI = 0;
		for (int m : aryAll) {
			if (m != intPlayNum) {
				aryNine[tempI++] = m;
			}
		}

		// ----组3:当前包的胆码与其他的9个数进行组合------
		// 将胆码与数组9中中的每一个进行组3排列
		int geshu2 = 2;
		PailieArith pl = new PailieArith();
		for (int m : aryNine) {
			int[] tempAry = { intPlayNum, m };
			List<String> list = new ArrayList<String>();
			 pl.permutation(list,tempAry, geshu2);

//			for (String str : list) {
////				String temp = str + str.substring(str.lastIndexOf(","), str.length());
//				String temp = str + str.substring(str.length()-1,str.length());
//				//String temp = str ;
//				if (resultZu3.equals("")) {
//					resultZu3 = resultZu3 + temp;
//				} else {
//					resultZu3 = resultZu3 + "," + temp;
//				}
//			}
			 //2010-09-20 前后加分隔符，并只保留2位
			 for (String str : list) {
					resultZu3 = resultZu3  + str + ",";
				}
		}

		//map.put("baodan1$Zu3", resultZu3);
//		resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu3",resultZu3,"bao1dan","",""));
		resultList.add(new PlayNumInfo("ssc","sanxing","bao1dan","zu3",resultZu3,"bao1dan","",""));

		// ----组6：当前包的胆码与其他的9个数进行排列------
		// 思路：首先对数组9中的任意2个数进行组合，再将胆码按照顺序加入到每组数据中
		// 进行组合运算
		ZuheArith za = new ZuheArith();
		List list = za.combine(aryNine, 2);
		// 整理结果 //前后加分隔符2010-09-20
		String resultZu6 = ",";

		for (int i = 0; i < list.size(); i++) {
			int[] t = (int[]) list.get(i);
			// 将胆码按大小顺序插入数组中
			int[] resultAry = new int[t.length + 1];

			//String temp = "";
			for (int m = 0; m < t.length; m++) {
				resultAry[m] = t[m];
			}			
			resultAry[resultAry.length-1] = intPlayNum;
			//排序
			chaRu(resultAry);
			//整理
			
			// 整理结果
			//String result = "";

				
			String temp = "";
			for (int m : resultAry) {
//				temp += m + ",";
				temp += m ;//不要分割符
			}
//			if (!temp.equals("")) {
//				temp = temp.substring(0, temp.length() - 1);
//			}
//			if (resultZu6.equals("")) {
//				resultZu6 = resultZu6 + temp;
//			} else {
//				resultZu6 = resultZu6 + "," + temp;
//			}
			resultZu6 = resultZu6 + temp + "," ;
		
		}
		//map.put("baodan1$Zu6", resultZu6);
//		resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu6",resultZu6,"bao1dan","",""));
		resultList.add(new PlayNumInfo("ssc","sanxing","bao1dan","zu6",resultZu6,"bao1dan","",""));

//		System.out.println("zhixuan="+resultZhixuan);
//		System.out.println("zu3="+resultZu3);
//		System.out.println("zu6="+resultZu6);
		
		return resultList;
	}

	/**
	 * 三星组选 包2胆 【三星组选包胆】1;【三星组选包胆】3,4;
	 * 组选包胆，包一个胆要110元，即包含一注直选（三个数相同），18注组三（两个数相同），36注组六（三个数各不相同），
	 * 
	 * @param playNum，必须有两个数字
	 * @return
	 */
	public List<PlayNumInfo> baodan2List(String playNum) {
		if(playNum == null){
			return null;
		}

		List<PlayNumInfo>  resultList = new ArrayList<PlayNumInfo>();
		String hezhiZhixuan = ",";
		String hezhiZu3 = ",";
		String hezhiZu6 = ",";
		// 转化为整型数组
		int[] intPlayNumAry = JStringToolkit.splitStrToInt(playNum, ",");
		//定义长度为3的数组
		//包2胆只有两个数
		// 进行组合运算
//		ZuheArith za = new ZuheArith();
//		List list = za.combine(intPlayNumAry, 2);
		//不想那么复杂了，只有两个数,不再进行组合运算
		if(intPlayNumAry[0] == intPlayNumAry[1]){
			//两个数相等，则有1个直选,9个组3//不会有组6
			hezhiZhixuan = hezhiZhixuan + intPlayNumAry[0]+intPlayNumAry[0]+intPlayNumAry[0] + "," ;
			for(int i = 0; i <=9; i++){
				if(i != intPlayNumAry[0]){
					if(i < intPlayNumAry[0]){
						hezhiZu3 = hezhiZu3 + i + intPlayNumAry[0] + ",";
					}else{
						hezhiZu3 = hezhiZu3 + intPlayNumAry[0]+ i  + ",";
					}
				}
			}
		}else{
			//两个数不相等，则有2个组3，8个组6
			//hezhiZu3 = hezhiZu3 + (intPlayNumAry[0]+intPlayNumAry[1]) + ","+ (intPlayNumAry[1]+intPlayNumAry[0]) + ",";
			//组六
			for(int i = 0; i <=9; i++){
				if(i != intPlayNumAry[0] &&  i != intPlayNumAry[1]){
					String s = i + "," + intPlayNumAry[0]+ "," + intPlayNumAry[1];
					String ttt = JStringToolkit.strSort(JStringToolkit.splitString(s,","));
					hezhiZu6 = hezhiZu6 + ttt  + ",";
				}else if(i == intPlayNumAry[0]){
					hezhiZu3 = hezhiZu3 + (String.valueOf(intPlayNumAry[0])+String.valueOf(intPlayNumAry[1])) + ",";
				}else if(i == intPlayNumAry[1]){
					hezhiZu3 = hezhiZu3 + (String.valueOf(intPlayNumAry[1])+String.valueOf(intPlayNumAry[0])) + ",";
				}
			}
		}
		
		
		
		
		
		
		/*
		
		String result = "";
		for(int i = 0; i <= 9; i++){
			int[] tt = new int[3];
			for(int j = 0; j < intPlayNumAry.length; j++){
				tt[j] = intPlayNumAry[j];
			}
			tt[tt.length-1] = i;
			chaRu(tt);
			//整理
			String temp = "";
			String temp1 = "";
			String temp2 = "";
			
			for(int m : tt){
				temp = temp + m + "";
			}
//			if(!temp.equals("")){
//				temp = temp.substring(0,temp.length()-1);
//			}
			
			if(tt[0] == tt[1] && tt[0] == tt[2]){
				hezhiZhixuan = hezhiZhixuan + temp + "," ;
			}
			
			if((tt[0] == tt[1] || tt[0] == tt[2] || tt[1] == tt[2]) && ( tt[0] != tt[1] || tt[0] != tt[2] || tt[1] != tt[2])){
				hezhiZu3 = hezhiZu3  + temp + ",";
			}
			if(tt[0] != tt[1] && tt[0] != tt[2] && tt[1] != tt[2]){
				hezhiZu6 = hezhiZu6 + temp + "," ;
			}
			
			
//			if(result.equals("")){
//				result = result + temp;
//			}else{
//				result = result + "|" + temp;
//			}
			
		}
		*/
		if(!hezhiZhixuan.equals("")){
//			hezhiZhixuan = hezhiZhixuan.substring(0,hezhiZhixuan.length()-1);
//			resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zhixuan",hezhiZhixuan,"bao2dan","","1"));
			if(!hezhiZhixuan.equals(",")){
				resultList.add(new PlayNumInfo("ssc","sanxing","bao2dan","zhixuan",hezhiZhixuan,"bao2dan","","1"));
			}
		}
		
		if(!hezhiZu3.equals("")){
//			hezhiZu3 = hezhiZu3.substring(0,hezhiZu3.length()-1);
//			resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu3",hezhiZu3,"bao2dan","","1"));
			if(!hezhiZu3.equals(",")){
				resultList.add(new PlayNumInfo("ssc","sanxing","bao2dan","zu3",hezhiZu3,"bao2dan","","1"));
			}
		}
		if(!hezhiZu6.equals("")){
//			hezhiZu6 = hezhiZu6.substring(0,hezhiZu6.length()-1);
//			resultList.add(new PlayNumInfo("ssc","sanxing","zuxuan","zu6",hezhiZu6,"bao2dan","","1"));
			if(!hezhiZu6.equals(",")){
				resultList.add(new PlayNumInfo("ssc","sanxing","bao2dan","zu6",hezhiZu6,"bao2dan","","1"));
			}
		}
		return resultList;
	}
	/**
	 * 和值：
	 */
	public List<PlayNumInfo> hezhiList(String playNum) {
		
		if(playNum == null){
			return null;
		} 

		List<PlayNumInfo>  resultList = new ArrayList<PlayNumInfo>();
		 
		String hezhiZhixuan = ",";
		String hezhiZu3 = ",";
		String hezhiZu6 = ",";
		int[] intAry = JStringToolkit.splitStrToInt(playNum, ",");
		for(int i = 0; i < intAry.length; i++){
			int num = intAry[i];
			int tempNum = num;
			if(num > 9){
				tempNum = 9;
			}
			for(int j = 0; j <= tempNum; j++){
				for(int k = j; k <= tempNum; k++){
					for(int m = k; m <= tempNum; m++){
						String temp = "";
						if(j+k+m == num){
							temp = j + "" + k + "" + m;	
							//三个都相等 直选 豹子
							if(j == k && k == m){
								hezhiZhixuan = hezhiZhixuan + temp + ",";
							}
							//有两个相等，有一个不同，组3
							if((j == k || k == m || j == m) && !(j == k && k == m)){
								///hezhiZu3 = hezhiZu3 + temp + ",";
								//hezhiZu3 = hezhiZu3 + temp + ",";
								if(j == k ){
									if(j < m){
										hezhiZu3 = hezhiZu3 + j + "" + m + ",";
									}else{
										hezhiZu3 = hezhiZu3 + m + "" + j + ",";
									}									
								}else if(j == m){
									if(j < k){
										hezhiZu3 = hezhiZu3 + j + "" + k + ",";
									}else{
										hezhiZu3 = hezhiZu3 + k + "" + j + ",";
									}
									
								}else if(k == m){
									if(k < m){
										hezhiZu3 = hezhiZu3 + k + "" + j + ",";
									}else{
										hezhiZu3 = hezhiZu3 + j + "" + k + ",";
									}
									
								}
								
							}
							//三个各不相等，组6
							if((j != k && k != m && j != m)){
								hezhiZu6 = hezhiZu6 + temp + ",";
							}
						}
					}
				}
			}
			
		}
		if(!hezhiZhixuan.equals("")){
			//hezhiZhixuan = hezhiZhixuan.substring(0,hezhiZhixuan.length()-1);
			if(!hezhiZhixuan.equals(",")){
				resultList.add(new PlayNumInfo("ssc","sanxing","hezhi","zhixuan",hezhiZhixuan,"","1","1"));
			}
		}
		if(!hezhiZu3.equals("")){
			//hezhiZu3 = hezhiZu3.substring(0,hezhiZu3.length()-1);
			if(!hezhiZu3.equals(",")){
				resultList.add(new PlayNumInfo("ssc","sanxing","hezhi","zu3",hezhiZu3,"","1","1"));
			}
		}
		if(!hezhiZu6.equals("")){
//			hezhiZu6 = hezhiZu6.substring(0,hezhiZu6.length()-1);
			if(!hezhiZu6.equals(",")){
				resultList.add(new PlayNumInfo("ssc","sanxing","hezhi","zu6",hezhiZu6,"","1","1"));
			}
		}

		return resultList;
	}
	/**
	 * 三星组选单式，包括直选，组3，组6
	 * @param playNum
	 * @return
	 */
	public List<PlayNumInfo>  danshiList(String playNum) {
		logger.info("三星组选单式--需要加上直选，组3，组6等判断!");
		if(playNum == null){
			return null;
		} 

		List<PlayNumInfo>  resultList = new ArrayList<PlayNumInfo>();
		
		if(playNum == null ){
			return null;
		}
		String str = ",";
		if(playNum.indexOf(",") != -1){
			String[] temp = playNum.split(",");
			//默认只有3位
			int bai = Integer.parseInt(temp[0]);
			int shi = Integer.parseInt(temp[1]);
			int ge = Integer.parseInt(temp[2]);
			
			str = str + JStringToolkit.strSort(temp) + ",";			
			if(bai == shi && bai == ge ){
				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zhixuan",str,"","1","1"));
			}else if((bai == shi && shi != ge ) || (bai == ge && bai != shi) || (shi == ge && bai != shi)){
				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zu3",str,"","1","1"));
			}else if(bai != shi && bai != ge && shi != ge ){
				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zu6",str,"","1","1"));
			}
			
			
//			for(String ss : temp){
//				str = str + ss;
//			}
		}else{
			//str = playNum;
			char[] temp = playNum.toCharArray();
			String[] t = new String[temp.length];
			
			for(int i = 0; i < temp.length; i++){
				t[i] = Character.toString(temp[i]);
			}
			str = str + JStringToolkit.strSort(t) + ",";
			//默认只有3位
			int bai = Integer.parseInt(t[0]);
			int shi = Integer.parseInt(t[1]);
			int ge = Integer.parseInt(t[2]);
			
			if(bai == shi && bai == ge ){
				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zhixuan",str,"","1","1"));
			}else if((bai == shi && shi != ge ) || (bai == ge && bai != shi) || (shi == ge && bai != shi)){
				if(bai == shi){
					if(bai < ge){
						str = String.valueOf(bai) + String.valueOf(ge);
					}else{
						str = String.valueOf(ge) + String.valueOf(bai);
					}
				}else if(bai == ge){
					if(shi < ge){
						str = String.valueOf(shi) + String.valueOf(ge);
					}else{
						str = String.valueOf(ge) + String.valueOf(shi);
					}
				}else if(shi == ge){
					if(bai < ge){
						str = String.valueOf(bai) + String.valueOf(ge);
					}else{
						str = String.valueOf(ge) + String.valueOf(bai);
					}
				}
				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zu3",str,"","1","1"));
			}else if(bai != shi && bai != ge && shi != ge ){
				resultList.add(new PlayNumInfo("ssc","sanxing","zuxuandanshi","zu6",str,"","1","1"));
			}

		}
		return resultList;
	}
	public  void chaRu(int[] x) {
		for (int i = 1; i < x.length; i++) {// i从一开始，因为第一个数已经是排好序的啦
			for (int j = i; j > 0; j--) {
				if (x[j] < x[j - 1]) {
					int temp = x[j];
					x[j] = x[j - 1];
					x[j - 1] = temp;
				}
			}
		}		
	}
}
