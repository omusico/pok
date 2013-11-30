package util;

 
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Types;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//import com.borland.dx.dataset.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JUtilities {
  private static Calendar staticCal;
  public JUtilities() {
  }

  /**
   * 判别一个类型是否日期类型，类型在java.sql.Types中定义
   * @param type   类型
   * @return       是返回true，不是返回false
   */
  public static boolean isDate(int type) {
    return (type == Types.TIMESTAMP || type == Types.DATE || type == Types.TIME);
  }

  /**
   * 判别是否是整型和数值
   * @param type   类型
   * @return       是返回true，不是返回false
   */
  public static boolean isNumber(int type) {
    return isInteger(type) || isDecimal(type);
  }

  /**
   * 判别一个类型是否字符，类型在java.sql.Types中定义
   * @param type    类型
   * @return        是返回true，不是返回false
   */
  public static boolean isString(int type) {
    return (type == Types.VARCHAR || type == Types.CHAR ||
            type == Types.LONGVARCHAR);
  }

  /**
   * 判别一个类型是否数值，类型在java.sql.Types中定义
   * @param type         类型
   * @return             是返回true，不是返回false
   */
  public static boolean isDecimal(int type) {
    return (type == Types.NUMERIC || type == Types.DECIMAL ||
            type == Types.FLOAT || type == Types.DOUBLE);
  }

  /**
   * 判别一个类型是否数值，类型在java.sql.Types中定义
   * @param type    类型
   * @return        是返回true，不是返回false
   */
  public static boolean isInteger(int type) {
    /*判别一个类型是否数值，类型在java.sql.Types中定义*/
    return (type == Types.INTEGER || type == Types.SMALLINT ||
            type == Types.BIGINT);
  }

  /**
   * 从一个对象中得到字符串，对象可以是任何，包括流
   * @param ob    对象
   * @return      字符串
   */
  public static final String getAsString(Object ob) { // throws Exception
    if (ob == null) {
      return null;
    }
    if (ob instanceof String) {
      return (String) ob;
    }
    else if (ob instanceof byte[]) {
      return new String( (byte[]) ob);
    }
    else if (ob instanceof java.io.InputStream) {
      try {
        return getTextFromInputStream( (java.io.InputStream) ob);
      }
      catch (java.io.IOException ex) {}
    }
    return ob.toString();
  }

  /**
   *   读入输入流并转换成文本. 从InputStream对象(is)得到 java.io.InputStreamReader(InputStream is)
   *   对象再从中读出文本. 在一些存储大文本的数据库的字段中，从中得到的是InputStream输入流,
   *   使用该函数转换成需要的文本。例子：
   *  <blockquote><pre>
   *    ResultSet rs = stmt.executeQuery(sql);
   *    Object obj = rs.getObject(1);
   *	  if( obj instanceof InputStream )
   *		 return Utilities.getTextFromInputStream((InputStream)obj);
   *   </pre></blockquote>
   *   @param   is  从中转换成文本的输入流
   *   @return  转换成的文本
   */
  public static final String getTextFromInputStream(InputStream is) throws java.
      io.IOException {
    if (is == null) {
      return null;
    }
    try {
      is.reset();
    }
    catch (Exception ex) {}
    java.io.InputStreamReader r = new java.io.InputStreamReader(is);
    StringBuffer buf = new StringBuffer();
    for (; ; ) {
      int c = r.read();
      if (c <= 0) {
        break;
      }
      buf.append( (char) c);
    }
    return buf.toString();
  }

  /**
   * 数组中查找字符串
   * @param a    数组
   * @param o    要查找的字符串
   * @return     找到返回字符串在数组中的位置，否则返回－1
   */
  public static int findAtStringArray(String a[], Object o) {
    if (a != null) {
      for (int i = 0; i < a.length; i++) {
        if (o == a[i] || (o != null && o.equals(a[i]))) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * 数组中不区分大小写地查找字符串
   * @param a    数组
   * @param o    要查找的字符串
   * @return     找到返回字符串在数组中的位置，否则返回－1
   */
  public static int findAtStringArrayNoCase(String a[], String o) {
    if (a != null) {
      for (int i = 0; i < a.length; i++) {
        if (o == a[i] || (o != null && o.equalsIgnoreCase(a[i]))) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * 查找一个数组在另一个数组中的位置
   * @param fromArrays
   * @param findArrays
   * @param bNoCase
   * @param bFoundOnly
   * @return
   */
  public final static int[] findArrayIndices(String[] fromArrays,
                                             String[] findArrays,
                                             boolean bNoCase,
                                             boolean bFoundOnly) {
    int Indices[] = null;
    if (findArrays != null && findArrays.length > 0) {
      Indices = new int[findArrays.length];
      for (int i = 0; i < Indices.length; i++) {
        Indices[i] = -1;
        if (fromArrays != null) {
          for (int j = 0; j < fromArrays.length; j++) {
            if (findArrays[i] != null && fromArrays[j] != null &&
                ( (!bNoCase && fromArrays[j].equals(findArrays[i]) ||
                   (bNoCase && fromArrays[j].equalsIgnoreCase(findArrays[i]))))) {
              Indices[i] = j;
              break;
            }
          }
        }
      }
    }

    if (Indices != null && bFoundOnly) {
      int factNum = 0;
      int len1 = Indices.length;
      for (int i = 0; i < len1; i++) {
        if (Indices[i] >= 0) {
          factNum++;
        }
      }
      if (factNum < len1) {
        int factIndices[] = new int[factNum];
        int pos = 0;
        for (int i = 0; i < len1 && pos < factNum; i++) {
          if (Indices[i] >= 0) {
            factIndices[pos++] = Indices[i];
          }
        }
        return factIndices;
      }
    }
    return Indices;
  }

  /**
   * 数组中查找整数
   * @param a    数组
   * @param o    要查找的整数
   * @return     找到返回整数在数组中的位置，否则返回－1
   */
  public static int findAtIntArray(int a[], int o) {
    if (a != null) {
      for (int i = 0; i < a.length; i++) {
        if (o == a[i]) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * 读文件
   * @param fileName  文件名
   * @return          读出的字符串
   * @throws java.io.IOException
   */
  public static final String getTextFromFile(String fileName) throws java.io.
      IOException {
    FileInputStream fis = new FileInputStream(fileName);
    try {
      return getTextFromInputStream(fis);
    }
    finally {
      try {
        fis.close();
      }
      catch (Throwable ex) {}
    }
  }

  public static int startWithInt(String s, char charBefore, boolean negEnable) {
    int p = s.indexOf(charBefore);
    if (p <= 0) {
      return p;
    }
    int i = 0;
    if (negEnable && s.charAt(0) == '-') {
      i++;
    }
    for (; i < p; i++) {
      char c = s.charAt(i);
      if (c < '0' || c > '9') {
        return -1;
      }
    }
    return p;
  }

  /**
   *  分析一个字符串表示的整数. 0x作为前缀的看成16进制, 0b作为前缀的看成二进制.
   *  例如  parseInt("0x1F") 结果为 31, parseInt("0b1011011") 结果为 91
   */
  public static final int parseInt(String text) {
    text = text.trim();
    int start = 0, base = 10;
    if (text.startsWith("0x") || text.startsWith("0X")) {
      start = 2;
      base = 16;
    }
    else if (text.startsWith("0b") || text.startsWith("0B")) {
      start = 2;
      base = 2;
    }
    return Integer.parseInt(text.substring(start), base);
  }

  /**
   * 提供从CachedRowSet中取得数据的函数，如果为null，返回的为“”
   * @param crs   CachedRowSet
   * @param str   字段名称字符串
   * @return
   */
  public static String getString(sun.jdbc.rowset.CachedRowSet crs, String str) {
    try {
      String temp = crs.getString(str);
      if (temp != null && !temp.equals("null")) {
        return temp;
      }
      else {
        return "";
      }

    }
    catch (Exception ex) {
      return "";
    }
  }

  /**
   * 提供从CachedRowSet中取得数据的函数，如果为null，返回的为“”
   * @param crs       CachedRowSet
   * @param index     字段所处的位置
   * @return
   */
  public static String getString(sun.jdbc.rowset.CachedRowSet crs, int index) {
    try {
      String temp = crs.getString(index);
      if (temp != null && !temp.equals("null")) {
        return temp;
      }
      else {
        return "";
      }
    }
    catch (Exception ex) {
      return "";
    }
  }

  public static int getInt(sun.jdbc.rowset.CachedRowSet crs, String str) {
    try {
      return crs.getInt(str);
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static int getInt(sun.jdbc.rowset.CachedRowSet crs, int index) {
    try {
      return crs.getInt(index);
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static long getLong(sun.jdbc.rowset.CachedRowSet crs, String str) {
    try {
      return crs.getLong(str);
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static long getLong(sun.jdbc.rowset.CachedRowSet crs, int index) {
    try {
      return crs.getLong(index);
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static float getFloat(sun.jdbc.rowset.CachedRowSet crs, String str) {
    try {
      return crs.getFloat(str);
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static float getFloat(sun.jdbc.rowset.CachedRowSet crs, int index) {
    try {
      return crs.getFloat(index);
    }
    catch (Exception ex) {
      return 0;
    }
  }

  public static java.sql.Date getDate(sun.jdbc.rowset.CachedRowSet crs,
                                      String str) {
    try {
      return crs.getDate(str);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static java.sql.Date getDate(sun.jdbc.rowset.CachedRowSet crs,
                                      int index) {
    try {
      return crs.getDate(index);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static java.sql.Timestamp getTimestamp(sun.jdbc.rowset.CachedRowSet
                                                crs, String str) {
    try {
      return crs.getTimestamp(str);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static java.sql.Timestamp getTimestamp(sun.jdbc.rowset.CachedRowSet
                                                crs, int index) {
    try {
      return crs.getTimestamp(index);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Blob getBlob(sun.jdbc.rowset.CachedRowSet crs, String str) {
    try {
      return crs.getBlob(str);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Blob getBolb(sun.jdbc.rowset.CachedRowSet crs, int index) {
    try {
      return crs.getBlob(index);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Clob getClob(sun.jdbc.rowset.CachedRowSet crs, String str) {
    try {
      return crs.getClob(str);
    }
    catch (Exception ex) {
      return null;
    }
  }

  public static Clob getClob(sun.jdbc.rowset.CachedRowSet crs, int index) {
    try {
      return crs.getClob(index);
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * 从request中得到数值的函数，如果为null，返回“”
   * @param request
   * @param param
   * @return
   */
  public static String getParameter2(HttpServletRequest request, String param) {
    String str = request.getParameter(param);
    if (str != null) {
      return str.trim();
    }
    else {
      return "";
    }
  }

  /**
   * 从Session中取到变量的值，如果为null，则返回空
   * @param session HttpSession对象
   * @param sessionName Session名
   * @return 返回Session值
   */
  public static String getSession(HttpSession session, String sessionName) {
    String retStr = "";
    try {
      retStr = session.getAttribute(sessionName).toString();
    }
    catch (Exception e) {
      retStr = "";
    }
    return retStr;
  }

  public static void setMapToMap(HashMap toMap, HashMap fromMap) {
    Collection coll = fromMap.entrySet();
    Iterator it = coll.iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      if (entry.getKey() != null && !entry.getKey().toString().equals("")) {
        toMap.put(entry.getKey(), entry.getValue());
      }
    }
  }

  public static String getNumChiness(String numStr) {
    char[] charArr = numStr.toCharArray();
    String str = "";
    for (int i = 0; i < charArr.length; i++) {
      switch (charArr[i]) {
        case '0':
          if (i != 0) {
            str = str + "〇";
          }
          break;
        case '1':
          str = str + "一";
          break;
        case '2':
          str = str + "二";
          break;
        case '3':
          str = str + "三";
          break;
        case '4':
          str = str + "四";
          break;
        case '5':
          str = str + "五";
          break;
        case '6':
          str = str + "六";
          break;
        case '7':
          str = str + "七";
          break;
        case '8':
          str = str + "八";
          break;
        case '9':
          str = str + "九";
          break;
      }
    }
    return str;
  }

  public static String getNumChinesTwo(String numTwo) {
    char[] charArr = numTwo.toCharArray();
    int length = charArr.length;
    String str = "";
    for (int i = 0; i < length; i++) {
      String strTemp = "";
      if (length - i == 2) {
        strTemp = "十";
      }
      if (length - i == 3) {
        strTemp = "百";
      }
      if (length - i == 4) {
        strTemp = "千";
      }
      if (length - i == 5) {
        strTemp = "万";
      }

      switch (charArr[i]) {
        case '1':
          if (length == 2 && i == 0) {
            str = "十" + str;
          }
          else {
            str = str + "一" + strTemp;
          }
          break;
        case '2':
          str = str + "二" + strTemp;
          break;
        case '3':
          str = str + "三" + strTemp;
          break;
        case '4':
          str = str + "四" + strTemp;
          break;
        case '5':
          str = str + "五" + strTemp;
          break;
        case '6':
          str = str + "六" + strTemp;
          break;
        case '7':
          str = str + "七" + strTemp;
          break;
        case '8':
          str = str + "八" + strTemp;
          break;
        case '9':
          str = str + "九" + strTemp;
          break;
      }
    }
    return str;
  }

}
