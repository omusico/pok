package util;

import java.util.List;

import junit.framework.TestCase;

/**
 * @Title:
 * @Description: 
 * @Create on: Aug 12, 2010 11:50:16 AM
 * @Author:LJY
 * @Version:1.0
 */
public class ZuheArithTest extends TestCase {
	
	ZuheArith za;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		za = new ZuheArith();
	}
	
	public void testCombine(){
		int[] a = new int[]{0,2,3,4,5,6,7,8,9}; 
		List list = za.combine(a,3);
		
		for(int i = 0; i < list.size(); i++){
			int[] t = (int[])list.get(i);
			for(int m : t){
				System.out.print(m);
			}
			System.out.print("\n");
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	private void print(Object obj){
		System.out.println(obj.toString());
	}
	private void print(int t){
		System.out.println(t);
	}
}
