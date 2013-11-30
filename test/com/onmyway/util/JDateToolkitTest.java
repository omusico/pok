package com.onmyway.util;

import junit.framework.TestCase;
import util.JDateToolkit;

/**
 * @Title:
 * @Description: 
 * @Create on: Sep 18, 2010 12:42:18 PM
 * @Author:LJY
 * @Version:1.0
 */
public class JDateToolkitTest extends TestCase {
	JDateToolkit t;
	protected void setUp() throws Exception {
		super.setUp();
		t  = new JDateToolkit();
	}
	public void notestGetDate(){
		print(t.getNowDate1());
		print(t.getNowDate2());
		print(t.getNowDate3());
		print(t.getNowDate4());
		print(t.getNowDate5());
	}
	public void testDiffDate(){
		String  strDaynum = "1";
		int dayNum = Integer.parseInt("-"+strDaynum);
		print(t.getBeforeAfterDate(dayNum+1));
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
}
