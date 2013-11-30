package com.onmyway.chankao;
/**
 * @Title:
 * @Description: 
 * @Create on: Aug 11, 2010 10:19:31 PM
 * @Author:LJY
 * @Version:1.0
 */
public class Pailie {
public static int i = 0;
	
	/**
	 * 排列
	 * @param ia
	 * @param n
	 */
	public static void permutation(int[] ia, int n) {
		permutation("", ia, n);
	}

	/**
	 * 排列
	 * @param ia
	 * @param n
	 */
	public static void permutation(String s, int[] ia, int n) {
		i++;
		if (n == 1) {
			for (int i = 0; i < ia.length; i++) {
				System.out.println(s + ia[i]);
			}
		} else {
			for (int i = 0; i < ia.length; i++) {
				String ss = "";
				ss = s + ia[i] + ", ";
				// 建立没有第i个元素的子数组
				int[] ii = new int[ia.length - 1];
				int index = 0;
				for (int j = 0; j < ia.length; j++) {
					if (j != i) {
						ii[index++] = ia[j];
					}
				}

				permutation(ss, ii, n - 1);
			}
		}
	}
	public static void main(String[] args) {

		int[] ia = { 1,2,3};
	        int n = 3;

	  		Long start = System.currentTimeMillis(); 
	        permutation(ia, n);

	  		Long end = System.currentTimeMillis(); 
	  		System.out.println("i="+i+",共耗时：" + (end - start) / 1000f + "秒！");    
	}
}
