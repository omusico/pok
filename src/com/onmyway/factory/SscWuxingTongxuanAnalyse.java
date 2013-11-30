package com.onmyway.factory;

import java.util.Map;

import util.JStringToolkit;

/**
 * @Title:时时彩-五星通选
 * @Description: 五星则代表5位全部（即万位、千位、百位、十位、个位），必须5位上都有数字
 * 有复式1种
 * 格式如：[五星通选]12,34,5,66,3
 * @Create on: Aug 10, 2010 10:52:59 AM
 * @Author:LJY
 * @Version:1.0
 */
public class SscWuxingTongxuanAnalyse extends PlayNumAnalyseFactory {

	SscWuxingTongxuanAnalyse sse;
	public static SscWuxingTongxuanAnalyse getInstance(){
		return new SscWuxingTongxuanAnalyse();
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

	/**
	 * 复式
	 * playNum：格式，[五星通选复式]1,12,34,5,6
	 * 必须选中五个位置的号码，每个位置号码可以多选
	 * return： "12345,"
	 */
	@Override
	public String fusi(String playNum) {
		String[][] ary = JStringToolkit.splitString(playNum,",",null,false);
		String touzhu = JStringToolkit.combAryStr(ary, ",");
		return  touzhu + ",";
	}

	@Override
	public String zuxuan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String baodan(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  Map<String,String> hezhi(String playNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
