package com.onmyway.factory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import util.JStringToolkit;

/**
 * @Title:时时彩 任选1,任选2,任选3
 * @Description: 
 * @Create on: Oct 12, 2011 7:17:29 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscRenxuanAnalyse {

	/**
	 * 任选一的号码分解,只对一组号码，
	 * 任选一：指投注者从0-9的数字号码中任意选择一个，对个位、十位、百位、千位、万位其中任意一位进行投注。
	 * 中奖规则：投注号码与当期开奖号码对应位置的号码相符。例如，开奖号码为12345，则当投注号码为3且位置在百位时，即中奖。
	 * 或投注号码为1且位置在万位时，即中奖。
	 * 将数字按照","进行拆分，如果不为"-",就在对应的位置前后添加符号"-",最后组成一个类似"---3-"的数字
	 * @param playNum 投注号码，格式如"3,-,59,8"
	 * @return "--3--" 
	 */
	public String renxuan1(String playNum){
		String result = "";
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		for(int i = 0 ;i < ary.length;i++){
			for(int j = 0; j < ary[i].length; j++){
				if(!ary[i][j].equals("-")){//如果不为"-"，则进行组合
					String temp = "";
					switch(i){
					case 0://万位
						temp = ary[i][j] + "----";//补4位
						break;
					case 1://千位
						temp = "-" + ary[i][j] + "---";//前补1位，后补3位
						break;
					case 2://百位
						temp = "--" + ary[i][j] + "--";//前补2位，后补2位
						break;
					case 3://十位
						temp = "---" + ary[i][j] + "-";//前补3位，后补1位
						break;
					case 4://个位
						temp = "----" + ary[i][j];//前补4位
						break;
					}
					
					if(!temp.equals("")){
						result = result + temp + ",";
					}
				}
			} 
		}
		if(!result.equals("")){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	/**
	 * 任选二：指投注者从0-9的数字号码中任意选择二个，对个位、十位、百位、千位、万位其中任意二位进行投注。
	 * 中奖规则：投注号码与当期开奖号码对应位置的号码相符。
	 * 例如，开奖号码为12345，则当投注号码为1、3且位置分别在万位和百位时，即中奖。或投注号码为2、5且位置分别在千位和个位时，即中奖。
	 * 将数字按照","进行拆分，如果不为"-",就在对应的位置前后添加符号"-",最后组成一个类似"-6-3-"的数字
	 * @param playNum 投注号码，格式如"3,-,59,8"
	 * @return "--3--" 
	 */
	public String renxuan2(String playNum){
		Set<String> set = new HashSet<String>();
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String comStr = JStringToolkit.combAryStr(ary, ",");
		String[] resultAry = JStringToolkit.splitString(comStr, ",");
		for(String str : resultAry){
			String[] temp = JStringToolkit.splitEveryCharacter(str);

			for(int i = 0; i < temp.length-1; i++){
				if(temp[i].equals("-")){
					continue;
				}else{
					String t = "";
					switch(i){
						case 0://万位
							t = temp[i];//循环后4位
							for(int j = 1;j<5;j++){
								if(!temp[j].equals("-")){
									String kk = t + temp[j];//得到临时的值
									kk = kk + getSymbol("-",5-(j+1));//填充
									set.add(kk);
								}
								t = t + "-";//将当前位补位，供下一次应用
							}
							break;
						case 1://千位
							t = "-"+temp[i];//循环后4位
							for(int j = 2;j<5;j++){//从第3位开始循环
								if(!temp[j].equals("-")){
									String kk = t + temp[j];//得到临时的值
									kk = kk + getSymbol("-",5-(j+1));//填充
									set.add(kk);
								}
								t = t + "-";//将当前位补位，供下一次应用
							}
							break;
						case 2://百位
							t = "--"+temp[i];//循环后4位
							for(int j = 3;j<5;j++){//从第3位开始循环
								if(!temp[j].equals("-")){
									String kk = t + temp[j];//得到临时的值
									kk = kk + getSymbol("-",5-(j+1));//填充
									set.add(kk);
								}
								t = t + "-";//将当前位补位，供下一次应用
							}
							break;
						case 3://十位
							t = "---" + temp[i];//前补3位
							if(!temp[4].equals("-")){
								String kk = t + temp[4];//得到临时的值 
								set.add(kk);
							}
							break;
						case 4://个位
							//t = "----" + temp[i];//前补4位
						
					}
				}
			}
		}
		 
		String result = "";
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			result = result + it.next() + ",";
		}
		if(!result.equals("")){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	/**
	 * 任选二：指投注者从0-9的数字号码中任意选择二个，对个位、十位、百位、千位、万位其中任意二位进行投注。
	 * 中奖规则：投注号码与当期开奖号码对应位置的号码相符。
	 * 例如，开奖号码为12345，则当投注号码为1、3且位置分别在万位和百位时，即中奖。或投注号码为2、5且位置分别在千位和个位时，即中奖。
	 * 将数字按照","进行拆分，如果不为"-",就在对应的位置前后添加符号"-",最后组成一个类似"-6-3-"的数字
	 * @param playNum 投注号码，格式如"3,-,59,8"
	 * @return "--3--" 
	 */
	//9,-,3,5,0
	//9-35-,--350,9-3-0,9-3--50
	public String renxuan3(String playNum){
		String ss  = "";
		Set<String> set = new HashSet<String>();
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String comStr = JStringToolkit.combAryStr(ary, ",");
		String[] resultAry = JStringToolkit.splitString(comStr, ",");
		for(String str : resultAry){
			String[] temp = JStringToolkit.splitEveryCharacter(str);

			for(int i = 0; i < temp.length-1; i++){
				if(temp[i].equals("-")){
					continue;
				}else{
					String t = "";
					switch(i){
						case 0://万位
							t = temp[i];//前1位
							for(int j = 1;j<(5-1);j++){ //第2行
								String t2 = "";
								if(!temp[j].equals("-")){
									t2 = t+temp[j];//前2两位
									for(int k = j+1; k < 5; k++){//第3行
										if(!temp[k].equals("-")){
											String kk = t2 + temp[k];//得到临时的值
											kk = kk + getSymbol("-",5-(k+1));//填充
											set.add(kk);

											//System.out.println("------1111--kkkk="+kk);
											ss = ss + kk + ",";
										}
										t2 = t2 + "-";
									}
								}
								t = t + "-";//将当前位补位，供下一次应用
								
							}
							break;
						case 1://千位
							t = "-"+temp[i];//循环后4位
							for(int j = 2;j<(5-1);j++){//第3行
								String t2 = "";
								if(!temp[j].equals("-")){
									t2 = t+temp[j];//前2两位
									for(int k = j+1; k < 5; k++){//第3行
										if(!temp[k].equals("-")){
											String kk = t2 + temp[k];//得到临时的值
											kk = kk + getSymbol("-",5-(k+1));//填充
											set.add(kk);
											ss = ss + kk + ",";
											//System.out.println("------2222--kkkk="+kk);
										}
										t2 = t2 + "-";
									}
								}
								t = t + "-";//将当前位补位，供下一次应用
								
							}
							break;
						case 2://百位
							t = "--"+temp[i];//循环后4位
							if(!temp[3].equals("-") && !temp[4].equals("-")){
								String kk = t + temp[3] + temp[4];//得到临时的值
								set.add(kk);
								ss = ss + kk + ",";
								//System.out.println("------333--kkkk="+kk);
							}
							break;
						case 3://十位
							//t = "---" + temp[i];//前补3位							
						case 4://个位
							//t = "----" + temp[i];//前补4位
						
					}
				}
			}
		}
		System.out.println("--------sssss="+ss);
		String result = "";
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			result = result + it.next() + ",";
		}
		if(!result.equals("")){
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	/**
	 * 总的方法
	 * @param playNum 
	 * @param playType 类型
	 * @return
	 */
	public String renxuan(String playNum,String playType){
		if(playType.equals("renxuan1")){
			return renxuan1(playNum);
		}
		if(playType.equals("renxuan2")){
			return renxuan2(playNum);
		}
		if(playType.equals("renxuan3")){
			return renxuan3(playNum);
		}
		return null;
	}
	private String getSymbol(String symbol,int totalCount){
		String s = "";
		for(int i = 0;i<totalCount;i++){
			s = s + symbol;
		}
		return s;
	}
}
