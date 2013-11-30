package com.onmyway.common;

import org.apache.commons.lang.StringUtils;

public class ConverterUtils {
	/**
	 * 将String转换为Long
	 * 
	 * @param s
	 * @return
	 */
	public static Long s2l(String s) {
		Long l = null;
		if (StringUtils.isNotBlank(s)) {
			l = Long.valueOf(s);
		}

		return l;
	}
	
	/**
	 * 将String转换为Double
	 * 
	 * @param s
	 * @return
	 */
	public static Double s2d(String s) {
		Double d = null;
		if (StringUtils.isNotBlank(s)) {
			d = Double.valueOf(s);
		}

		return d;
	}

	/**
	 * 将Long转换为String
	 * 
	 * @param l
	 * @return
	 */
	public static String l2s(Long l) {
		if (null != l){
			String s = "";
			if (StringUtils.isNotBlank(l.toString())) {
				s = String.valueOf(l);
			}
			return s;
		}else{
			return "";
		}
	}

	/**
	 * 将String[]转换为Long[]
	 * 
	 * @param sArr
	 * @return
	 */
	public static Long[] sa2la(String[] sArr) {
		Long[] lArr = new Long[sArr.length];

		for (int i = 0; i < sArr.length; i++) {
			if (StringUtils.isNotBlank(sArr[i])) {
				lArr[i] = Long.valueOf(sArr[i]);
			} else {
				lArr[i] = (Long) null;
			}
		}

		return lArr;
	}
}
