package com.onmyway.service;

import junit.framework.TestCase;

import com.onmyway.sxw.manage.service.SxwZjServiceImpl;

/**
 * @Title:
 * @Description: 
 * @Create on: Sep 20, 2010 3:53:01 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscZjServiceImplTest extends TestCase {
	SxwZjServiceImpl zj ;
	protected void setUp() throws Exception {
		super.setUp();
		zj = new SxwZjServiceImpl();
	}

	public void notestPaixu(){
		String[] temp = {"7","6","3"};
//		String s = zj.strSort(temp);
//			print(s);
	}
	public void testSetZjNum(){
		String issueNum = "1010210002";
		String num = "5,6,7,8,9";
		zj.setZjInfo(issueNum, num);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
}
