package com.onmyway.chankao;

/**
 * @Title:
 * @Description:
 * @Create on: Aug 11, 2010 10:01:32 PM
 * @Author:LJY
 * @Version:1.0
 */
public class Caipiao {
	/**
	 * 几选几，如22选5
	 * @param max，最大值
	 * @param n，选几
	 * @return
	 */
	public int[] getValue(int max, int n) {
		int[] return_val = new int[n];
		int i = 1;
		int j = 0;
		return_val[0] = (int) (Math.random() * max) + 1;
		while (i < n) {
			int r = (int) (Math.random() * max) + 1;
			for (j = 0; j < i; j++) {
				if (r == return_val[j])
					break;
			}
			if (i != j)
				continue;
			return_val[i] = r;
			i++;
		}
		return return_val;
	}

	public static void main(String[] args) {
		int[] v = new Caipiao().getValue(22, 5);
		for (int i = 0; i < v.length; i++) {
			System.out.print(v[i] + " ");
		}
	}
}
