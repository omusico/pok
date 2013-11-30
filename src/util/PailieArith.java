package util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 12, 2010 10:59:40 AM
 * @Author:LJY
 * @Version:1.0
 */
public class PailieArith {
	
	//List<String> list = new ArrayList<String>();
	/**
	 * 排列 
	 * @param ia 整数数组
	 * @param n 进行几个的排列
	 */
	public  List permutation(List<String> list, int[] ia, int n) {
		
		permutation(list,"", ia, n,"0");
		return list;
	}
	/**
	 * 排列 
	 * @param ia 整数数组
	 * @param n 进行几个的排列
	 * @param addFlag 是否添加逗号 "0"不添加逗号，"1"添加逗号
	 */
	public  List permutation(List<String> list, int[] ia, int n,String addFlag) {
		
		permutation(list,"", ia, n,addFlag);
		return list;
	}
	/**
	 * 排列
	 * @param ia
	 * @param n
	 * @param addFlag 是否添加逗号
	 */
	public  void permutation(List<String> list ,String s, int[] ia, int n,String addFlag) {
		
		if (n == 1) {
			for (int i = 0; i < ia.length; i++) {
				//System.out.println(s + ia[i]);
				list.add(s+ia[i]);
			}
		} else {
			for (int i = 0; i < ia.length; i++) {
				String ss = "";
				if(addFlag.equals("1")){
					ss = s + ia[i] + ",";
				}else if(addFlag.equals("0")){
					ss = s + ia[i];//去掉中间的分隔符“，”，直接显示数值
				}
				// 建立没有第i个元素的子数组
				int[] ii = new int[ia.length - 1];
				int index = 0;
				for (int j = 0; j < ia.length; j++) {
					if (j != i) {
						ii[index++] = ia[j];
					}
				}

				permutation(list,ss, ii, n - 1,addFlag);
			}
		}
	}
}
