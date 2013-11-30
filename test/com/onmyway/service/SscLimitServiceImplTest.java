package com.onmyway.service;

import junit.framework.TestCase;

import com.onmyway.ssc.play.model.SscTzInfo;
import com.onmyway.ssc.play.service.SscLimitServiceImpl;

/**
 * @Title:
 * @Description: 
 * @Create on: Sep 20, 2010 3:53:01 PM
 * @Author:LJY
 * @Version:1.0
 */
public class SscLimitServiceImplTest extends TestCase {
	SscLimitServiceImpl ls ;
	protected void setUp() throws Exception {
		super.setUp();
		ls = new SscLimitServiceImpl();
	}


	public void testWuxingtx(){
		SscTzInfo info = new SscTzInfo();
		String playType ="wuxing";
		String typeName = "tx";
		String playMode = "fushi";
		String issueNum = "1010050001";
		String playNum  = "5,6,7,8,9";
		String haveZhuitou = "0";
		String zhuitouInfo = "0";
		
		info.setPlayType(playType);
		info.setTypeName(typeName);
		info.setPlayMode(playMode);
		info.setIssueNum(issueNum);
		info.setTouzhuNum(playNum);
		info.setHaveZhuitou(haveZhuitou);
		info.setZhuitouInfo(zhuitouInfo);
		ls.isLimitNum(info);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	private void print(Object obj){
		System.out.println(obj.toString());
	}
}
