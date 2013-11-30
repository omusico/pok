package util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;


/**
 * @Title:
 * @Description: 
 * @Create on: Aug 12, 2010 11:08:37 AM
 * @Author:LJY
 * @Version:1.0
 */
public class PailieArithTest extends TestCase {

	PailieArith pl ;
	protected void setUp() throws Exception {
		pl = new PailieArith();
	}

	public void testPermutation(){
		int[] a = {1,2,3,4};
		List list = new ArrayList();
		pl.permutation(list,a, 2);
		for(Object obj : list){
			System.out.println(obj.toString());
		}
		
	}
	protected void tearDown() throws Exception {
	}

}
