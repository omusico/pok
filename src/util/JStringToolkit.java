package util;


import java.util.Vector;

import org.apache.log4j.Logger;


public class JStringToolkit {
	private static Logger log = Logger.getLogger("huacaizx");
    public JStringToolkit() {
    }
    /**
     * 将一个字符串以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
     *   @param  str  被分隔的字符串
     *   @param  delimiter  分隔符
     *   @return  分隔的结果
     */
    public static final String[] splitString(String str, char delimiter) {
        return splitString(str, 0, delimiter);
    }

    /**
     * 将一个字符串以两个字符字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
     *   @param  str  被分隔的字符串
     *   @param  delimiter1  分隔符1
     *   @param  delimiter2  分隔符2
     *   @return  分隔的结果
     */

    public static final String[][] splitString(String str, char delimiter1,
                                               char delimiter2) {
        String[] a1 = splitString(str, delimiter1);
        if (a1 == null) {
            return null;
        }
        String a2[][] = new String[a1.length][];
        for (int i = 0; i < a1.length; i++) {
            a2[i] = splitString(a1[i], delimiter2);
        }
        return a2;
    }

    /**
     *   将一个字符串从某位置开始以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
     *  <blockquote><pre>
     *     String list[] = Utilities.splitString("AAAA,BBBB,CCCC,DDDDD",0,',')
     *     // list 为  { "AAAA","BBBB","CCCC","DDDD" }
     *   </pre></blockquote>
     *   @param  str  被分隔的字符串
     *   @param  istart 开始位置
     *   @param  delimiter  分隔符
     *   @return  分隔的结果
     */
    public static final String[] splitString(String str, int istart,
                                             char delimiter) {
        if (str == null) {
            return null;
        }
        int sl = str.length();
        int n = 0;
        for (int i = istart; i < sl; i++) {
            if (str.charAt(i) == delimiter) {
                n++;
            }
        }
        String[] sa = new String[n + 1];
        int i = istart, j = 0;
        for (; i < sl; ) {
            int iend = str.indexOf(delimiter, i);
            if (iend < 0) {
                break;
            }
            sa[j++] = str.substring(i, iend);
            i = iend + 1;
        }
        sa[j++] = str.substring(i);
        return sa;
    }

    public static final String[] splitString(String str, String limiterStr) {
        if (str == null || str.equals("")) {
            return null;
        }
        if (limiterStr.equals("") || str.indexOf(limiterStr) == -1) {
            return new String[] {
                str};
        }
        Vector vector = new Vector();
        String strTemp = str;
        while (strTemp.indexOf(limiterStr) != -1) {
            int start = strTemp.indexOf(limiterStr);
            String strTempTemp = strTemp.substring(0, start);
            strTemp = strTemp.substring(start + limiterStr.length(),
                                        strTemp.length());
            if (strTempTemp != null && !strTempTemp.equals("")) {
                vector.add(strTempTemp);
            }
            if (strTemp.indexOf(limiterStr) == -1 && !strTemp.equals("")) {
                vector.add(strTemp);
            }
        }
        String[] strArr = new String[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            strArr[i] = vector.elementAt(i).toString();
        }
        return strArr;
    }
    
    
    public static final String[] splitChar(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String s = "";
        for(int i = 0; i < str.length(); i++){
        	s = s + str.substring(i,i+1) + ",";
        }
        if( !s.equals("")) s = s.substring(0,s.length()-1);
        return splitString(s,",");
    }
    /**
     * 统计一个字符串在另一个字符串中出现的次数,
     * 字符串b在字符串a中出现的次数
     * @param a 源字符串
     * @param b 给定的字符串
     * @return 
     */
    public static int getSubNum(String a,String b){
    	  int num=0;
    	  String str=a;
    	  int index=a.indexOf(b);
    	  while(index!=-1){
    	   num++;
    	   if(index != (str.length()-1)){
	    	   str=str.substring(index+1);
	    	   index=str.indexOf(b);
    	   }else{
    		   break;
    	   }
    	  }
    	  return num;
    }
    /**
     * 统计一个字符串在另一个字符串中出现的次数,主要为了五星通选
     * 字符串b在字符串a中出现的次数
     * @param a 源字符串,以固定的符号分割，
     * @param b 给定的字符串
     * @param exceptStr 不相同的，
     * @return 
     */
    public static int getWuxingTxSubNum(String a,String splitSymbol,String b,String exceptStr){
    	  int num=0;
    	  String[] ary = splitString(a,splitSymbol);
    	  for(String s : ary){
    		  if((","+s+",").indexOf(exceptStr) != -1){
    			  continue;
    		  }else if((","+s+",").indexOf(b) != -1){
    			  num ++;    			  
    		  }
    	  }
    	  return num;
    }

    /**
     * 将字符串数组已分割符分割成相应的整型数组，
     * @param str 传入的字符串类似"1,2,3"
     * @param splitStr
     * @return
     */
    public static final int[] splitStrToInt(String str,String splitStr){
    	//将其分割，分割成一维数组
		String[] strAry = JStringToolkit.splitString(str,splitStr);
		int len = strAry.length;
		int[] intAry = new int[len];
		try{
			for(int i = 0; i < len; i++){
			
				intAry[i] = parseInt(strAry[i]);
			}
		}catch(NumberFormatException e){
			log.error("字符串分割整型数组异常："+e.toString());
		}
    	
		return intAry;
    }
    /**
     * 将已有的字符串,按新的分割符组合
     * @param str
     * @param oldDelimiter
     * @param delimiter
     * @return
     */
    public static final String insertDelimiter(String str,String oldDelimiter,String delimiter){
    	if(str == null){
    		return null;
    	}
    	String result = "";
    	String[] ary = null;
    	if(oldDelimiter == null){
    		ary = str.split("");
    	}else{
    		ary = str.split(oldDelimiter);
    	}
    	for(String s : ary){
    		if(result.equals("")){
    			result = result + s;
    		}else{
    			result = result + delimiter + s;
    		}
    	}
    	return result;
    }
    /**
     * 将字符串分割成二维数组；如果有第二个分隔符，就按照第二个分隔符进行分割；如果没有，就将其按字母分割
     * @param str 源字符串
     * @param limitStr1 分隔符1
     * @param limitStr2 分隔符2
     * @param flag true:使用第二个分隔符；false:不使用第二个分隔符
     * @return 二维数组
     */
    public static final String[][] splitString(String str,String limitStr1,String limitStr2,boolean flag){
//    	String[] a1 = str.split(limitStr1);
    	String[] a1 = splitString(str,limitStr1);
    	
    	if(a1 == null){
    		return null;
    	}
    	int len = a1.length;
    	String[][] a2 = new String[len][];
    	if(flag){
    		for(int i = 0; i < len; i++){
    			a2[i] = splitString(a1[i],limitStr2);
    		}
    	}else{
    		for(int i = 0; i < len; i++){
    			char[] aryChar = a1[i].toCharArray();
    			int tempLen = aryChar.length;
    			String[] tempAry = new String[tempLen];
    			for(int j = 0; j < tempLen; j++){
    				tempAry[j] = Character.toString(aryChar[j]);
    			}
    			a2[i] = tempAry;
    		}
    	}
    	return a2;
    }
       
	/**
	 * 二维数组组合算法
	 * 将二维数组组合成一个字符串，每个组合之间以给定的分隔符进行分隔
	 * @param ary
	 * @param limitStr
	 * @return
	 */
    public static final String combAryStr(String[][] ary,String limitStr){
    	if(ary == null ){
    		return null;
    	}
    	String str = "";
    	int len = ary.length;
   	
    	int max = 1;
        for(int i = 0; i < len; i++){
            max *= ary[i].length;
        }
        
        for(int i = 0; i < max; i++){
            String s = "";
            int temp = 1;      //注意这个temp的用法。
            for(int j = 0; j < ary.length; j++){
            	int len2 = ary[j].length;
                temp *= len2;
                s += ary[j][i / (max / temp) % len2];
            }
            str += s + limitStr;
        }

         if(!str.equals("")){
        	str = str.substring(0,str.length()-1);
        }
        return str;
    }
   /**
    * 二维数组组合算法
	* 将二维数组组合成一个字符串，每个组合之间以给定的分隔符进行分隔,如果有多组数值，则各组数值之间以第二个分隔符分割
    * @param ary 二维数组
    * @param limitStr
    * @param secondLimitStr
    * @param flag 是否使用第二个分隔符;true:使用第二个分隔符(如："1,2,3|2,3,4,5|3,6,8");false:不使用第二个分隔符(如:"123,234,368");区别在于如果大于10的数，使用一个分割符的时候会出错
    * @return
    */
    public static final String combAryStr(String[][] ary,String limitStr,String secondLimitStr,boolean flag){
    	if(ary == null ){
    		return null;
    	}
    	String str = "";
    	
    	int len = ary.length;
   	
    	int max = 1;
        for(int i = 0; i < len; i++){
            max *= ary[i].length;
        }
        
        for(int i = 0; i < max; i++){
            String s = "";
            int temp = 1;      //注意这个temp的用法。
            for(int j = 0; j < ary.length; j++){
            	int len2 = ary[j].length;
                temp *= len2;
                if(flag){
                	s = s + ary[j][i / (max / temp) % len2] + limitStr;
                }else{
                	s += ary[j][i / (max / temp) % len2];
                }
            }
            if(flag){
            	 if(!s.equals("")){
                 	s = s.substring(0,s.length()-1);
                 }
            	str += s + secondLimitStr;
        	}else{
        		str += s + limitStr;
        	}
        }

         if(!str.equals("")){
        	str = str.substring(0,str.length()-1);
        }
        return str;
    }
	/**
	 * 二维数组组合算法,加上前缀
	 * 将二维数组组合成一个字符串，每个组合之间以给定的分隔符进行分隔，并且加上前缀，如组合是3位数，为了补足5五位，加上前缀前缀是"-,-,",
	 * @param ary
	 * @param limitStr
	 * @return
	 */
    public static final String combAryStr(String[][] ary,String limitStr,String preString){
    	if(ary == null ){
    		return null;
    	}
    	String str = "";
    	int len = ary.length;
   	
    	int max = 1;
        for(int i = 0; i < len; i++){
            max *= ary[i].length;
        }
        
        for(int i = 0; i < max; i++){
            String s = "";
            int temp = 1;      //注意这个temp的用法。
            for(int j = 0; j < ary.length; j++){
            	int len2 = ary[j].length;
                temp *= len2;
                s += ary[j][i / (max / temp) % len2];
            }
            str += preString + s + limitStr;
        }

         if(!str.equals("")){
        	str = str.substring(0,str.length()-1);
        }
        return str;
    }
    
//    public static final String pailieStr2(String[] ary){
//    	if(ary == null){
//    		return null;
//    	}
//    	int kk = 0;    	
//    	int len = ary.length;
//    	int k = 0; 
//
//    	return null;
//    }

	/**
	 * 对数组进行排序,从小到大
	 * @param data 数组里全为数字
	 * @return
	 */
	public static String strSort(String[] data) { 
		String result = "";
		int[] d = new int[data.length];
		for(int i = 0; i < data.length; i++){
			d[i] = Integer.parseInt(data[i]);
		}
		
		for (int i = 0; i < d.length; i++) { 
			for (int j = d.length - 1; j > i; j--) { 
				if (d[j] < d[j - 1]) { 
					swap(d, j, j - 1); 
				} 
			} 
		} 
		for(int i = 0; i < data.length; i++){
			result += d[i];
		}
		return result; 
	} 
	/**
	 * 对数组进行排序,从小到大
	 * @param data 数组里全为数字
	 * @return
	 */
	public static String strSort(String[] data,String splitStr) { 
		String result = "";
		int[] d = new int[data.length];
		for(int i = 0; i < data.length; i++){
			d[i] = Integer.parseInt(data[i]);
		}
		
		for (int i = 0; i < d.length; i++) { 
			for (int j = d.length - 1; j > i; j--) { 
				if (d[j] < d[j - 1]) { 
					swap(d, j, j - 1); 
				} 
			} 
		} 
		for(int i = 0; i < data.length; i++){
			//result += d[i];
			if(result.equals("")){
				result = String.valueOf(d[i]);
			}else{
				result = result + splitStr + d[i];
			}
		}
		return result; 
	} 
	/**
	 * 对以符号分割的数字进行排序,从小到大
	 * @param str 分割后里全为数字
	 * @return
	 */
	public static String strSort(String str,String splitStr) { 
		String result = "";
		String[] data = splitString(str,splitStr);
		int[] d = new int[data.length];
		for(int i = 0; i < data.length; i++){
			d[i] = Integer.parseInt(data[i]);
		}
		
		for (int i = 0; i < d.length; i++) { 
			for (int j = d.length - 1; j > i; j--) { 
				if (d[j] < d[j - 1]) { 
					swap(d, j, j - 1); 
				} 
			} 
		} 
		for(int i = 0; i < data.length; i++){
			//result += d[i];
			if(result.equals("")){
				result = String.valueOf(d[i]);
			}else{
				result = result + splitStr + d[i];
			}
		}
		return result; 
	} 
	/** 
	* 交换数据顺序 
	* 
	* @param data 
	* @param i 
	* @param j 
	*/ 
	private static void swap(int[] data, int i, int j) { 
		int temp = data[i]; 
		data[i] = data[j]; 
		data[j] = temp; 
	} 
	
	/**冒泡排序方法  
     * 原理：从最后一个开始将小的或大的逐渐冒出  
     * @param array  
     * @param method  1:从小到大排列；2：从大到小排列
     * @return  
     */  
    public static int[] intOrder(int[] array,int method)   
    {   
        for(int i=0;i<array.length;i++)   
        {   
            for (int j=array.length -1 ;j>i;j--)   
            {   
                if (method==2)   
                {   
                    if (array[i] < array[j])   
                        swap(array,i,j);   
                }else if (method==1)   
                    if (array[i] > array[j])   
                        swap(array,i,j);   
            }   
        }   
        return array;   
    }   

    public static void main(String[] args){
    	String s = "123";
    	//String[] t = JStringToolkit.splitChar(s);
    	String[] t = JStringToolkit.splitString("22|ss","$");
    	for(int i = 0; i < t.length; i++){
    		System.out.print(t[i]+",");
    	}
    }
    /**
     * 字符串s在字符串y出现的次数
     * @param y 目标字符串
     * @param s 源字符串
     * @return
     * @deprecated
     */
    public static int getSubNum_old(String s,String y){    //统计方法 
    	  int count=0; 
    	  String [] k=s.split(y);                      //将字符串通过s断开返回数组k 
    	  if(s.lastIndexOf(y)==(s.length()-y.length())) //如果y最后一个包含s的索引等于y的长度-要的长度，那么出现的次数就等于k的长度 
    	   count=k.length; 
    	  else 
    	   count=k.length-1;//否则k长度-1，因为s不是单字符是多个 
    	  
    	   if(count==0) {
    	   System.out.println ("字符串\""+y+"\"在字符串\""+s+"\"没有出现过"); 
    	  } else{ 
    	   return count;
    	  }
    	  return 0; 
    	 
    	} 
    /**
     * 分割字符串
     * @param str
     * @return
     */
    public static String[] splitEveryCharacter(String str){
    	if(str == null || str.equals("")){
    		return null;
    	}
    	String[] ary = new String[str.length()];
    	for(int i = 0; i < ary.length; i++){
    		ary[i] = str.substring(i,i+1);
    	}
    	return ary;
    }
    /**
     * 将一个字符串截成连续的几个数（如:1,2,3,4,5,要将其截为连续两位的数组，返回值为[1,2],[2,3],[3,4],[4,5]
     * @param str 要截取的字符串1,2,3,4,5
     * @param symbolStr 
     * @param count
     * @return
     */
    public static String[] splitStringByCount(String str,String symbolStr,int count){
    	if(str == null){
    		return null;
    	}
    	
    	String[] ary = splitString(str,symbolStr);
    	String[] result = new String[ary.length-(count-1)];//
    	for(int i=0;i<ary.length-(count-1);i++){
    		String temp = "";
    		for(int j=0;j<count;j++){
    			if(j == 0){
    				temp = ary[i+j];
    			}else{
    				temp = temp + "," + ary[i+j];
    			}
    		}
    		result[i] = temp;
    	}
    	return result;
    }
    
    private static int parseInt(String val){
    	try{
    		return Integer.parseInt(val);
    	}catch(NumberFormatException e){
    		System.out.println("数字格式化异常");
    		return 0;
    	}
    }

}
