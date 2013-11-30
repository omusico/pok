package dbconnpac;

import java.util.Vector;
import sun.jdbc.rowset.*;
import java.util.*;
import sun.jdbc.rowset.CachedRowSet;


public interface DBTool {

  public CachedRowSet querySql(String dbSource,String sql);

  public String executeSql(String dbSource, String sql,boolean retMaxSeqence);

  public boolean executeSql(String dbSource,String sql);

  public boolean executeSql(String dbSource,Vector sql);

  public boolean executeSql(String dbSource,String[] sql);

  public boolean insertSql(String dbSource,String sql);

  public boolean insertSql(String dbSource,Vector sql);

  public boolean insertSql(String dbSource,String[] sql);

  public boolean updateSql(String dbSource,String sql);
  //不作主键管理
  public boolean executeVecSql(String dbSource,Vector sql);
}
