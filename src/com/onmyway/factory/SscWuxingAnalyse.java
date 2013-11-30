package com.onmyway.factory;

import java.util.Map;

import util.JStringToolkit;

/**
 * @Title:五星 
 * @Description: 五星则代表5位全部（即万位、千位、百位、十位、个位），必须5位上都有数字
 * 有复式，单式2中
 * 格式如：[五星单式]1,3,4,5,6
 *       [五星复式]12,34,5,66,3
 * @Create on: Aug 10, 2010 5:42:09 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscWuxingAnalyse extends PlayNumAnalyseFactory{

	SscWuxingAnalyse sse;
	public static SscWuxingAnalyse getInstance(){
		return new SscWuxingAnalyse();
	}
	
	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 单式，只有一注
	 * @return "12345,"
	 */
	@Override
	public String dansi(String playNum) {
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		//return  touzhu + ","; 
		return  touzhu + ",";
	}

	@Override
	public String dantuo(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 复式
	 * playNum：格式，[五星通选复式]1,12,34,5,6
	 * 必须选中五个位置的号码，每个位置号码可以多选
	 * return："12312,12313,12314,12322,12323,12324,"
	 */
	@Override
	public String fusi(String playNum) {
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		
		return  touzhu + ","; 
	}

	@Override
	public  Map<String,String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
