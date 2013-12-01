package com.onmyway.util;

import com.alibaba.fastjson.JSON;

import junit.framework.TestCase;

/**
 * @Title:fastjson 的测试类
 * @Description: 
 * @Create on: 2013年11月30日 下午7:59:43
 * @Author:ljy
 * @Version:1.0
 */
public class FastJsonTest extends TestCase{

    public void testAry(){
        int[] ary = {2013,11,30,2,33,36};
        String str = (String)JSON.toJSONString(ary);
        System.out.println(str);
        
        
    }
    
}
