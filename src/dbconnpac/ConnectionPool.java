package dbconnpac;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company: cyber
 * @author
 * @version 1.0
 */

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

public class ConnectionPool{

  private String name;
  private String URL;
  private String user;
  private String password;
  private int maxConns;
  private int timeOut;
  private long connectiontimeout;
//  private log log;

  private Logger log = Logger.getLogger(ConnectionPool.class);

  private int checkedOut = 0;
  private Vector freeConnections = new Vector();
  private Vector lsnrConnections = new Vector();

  public ConnectionPool(String name, String URL, String user,
                        String password, int maxConns, int initConns,
                        int timeOut,long contimeout) {

    this.name = name;
    this.URL = URL;
    this.user = user;
    this.password = password;
    this.maxConns = maxConns;
    this.timeOut = timeOut > 0 ? timeOut : 5;
    this.connectiontimeout= contimeout > 0 ? contimeout : 10;

    log.info("正在初始化连接池...");
    initPool(initConns);
    log.info("初始化连接池 OK!");
    
    startLsnCon();
    log.info("连接池监控线程启动 OK!");
  }

  private void initPool(int initConns) {
    for (int i = 0; i < initConns; i++) {
      try {
        Connection pc = newConnection();

        log.info("初始化第" + i + " 个新的连接 OK! ");
        freeConnections.addElement(pc);
      }
      catch (SQLException e) {        
    	    log.error("创建连接池 Error!"+e.toString());
      }
    }
  }

  public Connection getConnection() throws SQLException {
    //log.log("Request for connection received", log.DEBUG);
    try {
      Connection conn = getConnection(timeOut * 1000);
      ConnectionWrapper conwap=new ConnectionWrapper(conn, this);
      addLsnrConnection(conwap);
      return conwap;
      //return conn;
    }
    catch (SQLException e) {
      log.error("Exception getting connection");
      throw e;
    }
  }

  synchronized void wrapperClosed(Connection conn) {
    freeConnections.addElement(conn);
    checkedOut--;
    notifyAll();
  }

  synchronized void realConClosed(Connection realcon) throws Exception{
      checkedOut--;
      realcon.close();
      log.info(name+"连接池连接超时未关闭 被真关闭掉  FreeCheckedOut" + checkedOut);
  }

  private synchronized Connection getConnection(long timeout) throws
      SQLException {

    // Get a pooled Connection from the cache or a new one.
    // Wait if all are checked out and the max limit has
    // been reached.
    long startTime = System.currentTimeMillis();
    long remaining = timeout;
    Connection conn = null;
    while ( (conn = getPooledConnection()) == null) {
    	//System.out.println("循环得到连接！！！！");
      try {
        wait(remaining);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      remaining = timeout - (System.currentTimeMillis() - startTime);
      if (remaining <= 0) {
        throw new SQLException("getConnection() timed-out");
      }
    }

    // Check if the Connection is still OK
    if (!isConnectionOK(conn)) {
      return getConnection(remaining);
    }
    checkedOut++;
    return conn;
  }

  private boolean isConnectionOK(Connection conn) {
    Statement testStmt = null;
    try {
      if (!conn.isClosed()) {
        // Try to createStatement to see if it's really alive
        testStmt = conn.createStatement();
        testStmt.close();
      }
      else {
        return false;
      }
    }
    catch (SQLException e) {
      if (testStmt != null) {
        try {
          testStmt.close();
        }
        catch (SQLException se) {
          log.error("isConnectionOK error" + se.getMessage());
        }
      }
      return false;
    }
    return true;
  }

  private Connection getPooledConnection() throws SQLException {
    Connection conn = null;

    if (freeConnections.size() > 0) {
      // Pick the first Connection in the Vector
      // to get round-robin usage
      conn = (Connection) freeConnections.firstElement();
      freeConnections.removeElementAt(0);
      if (conn == null||conn.isClosed()||this.isRealClosed(conn)) {
        conn = newConnection();
      } else if (!conn.getAutoCommit()) {
    	  try {
    		  conn.close();
    	  } catch (Exception e) {
          if (conn != null || !conn.isClosed()) {
            try {
              conn.close();
            }
            catch (SQLException se) {
              log.error("真关闭连接 error" + se.getMessage());
            }

          }

          e.printStackTrace();
        }
        conn = newConnection();
      }else {
     	// System.out.println("从连接池获得的连接！");
      }
    } else if (maxConns == 0 || checkedOut < maxConns) {
      conn = newConnection();
    } else {
    	 //System.out.println("连接池没有连接了！");
    }
    return conn;
  }

  private boolean isRealClosed(Connection conn) {
	  Statement stmt;
	try {
		stmt = conn.createStatement();
		if (null != stmt) {
			stmt.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
		return true;
	} 
	  return false;
  }
  
  private Connection newConnection() throws SQLException {
    Connection conn = null;
    String tempUrl = URL + "?user=" + this.user + "&password=" + this.password + "&useUnicode=true&characterEncoding=gb2312";
    log.info("newConnection()_正在获得数据库连接...");
    conn = DriverManager.getConnection(tempUrl);
    log.info("获得数据库连接 OK!");
    return conn;
  }

  public synchronized void freeConnection(Connection conn) {
    // Put the connection at the end of the Vector
    freeConnections.addElement(conn);
    checkedOut--;
    //System.out.println("FreeCheckedOut" + checkedOut);
    notifyAll();
    //log.log("Returned connection to pool", log.INFO);
    //log.log(getStats(), log.DEBUG);
  }

  public synchronized void release() {
    Enumeration allConnections = freeConnections.elements();
    while (allConnections.hasMoreElements()) {
      Connection con = (Connection) allConnections.nextElement();
      try {
        con.close();
        //log.log("Closed connection", log.INFO);
      }
      catch (SQLException e) {
        log.error( "Couldn't close connection"+e.toString());
      }
    }
    freeConnections.removeAllElements();
  }

  private String getStats() {
    return "Total connections: " +
        (freeConnections.size() + checkedOut) +
        " Available: " + freeConnections.size() +
        " Checked-out: " + checkedOut;
  }

  public void printLog() {
    //log.log("还有连结数："+freeConnections.size(), log.INFO);
    //log.log("还没释放连结数："+checkedOut, log.INFO);
  }

  public Map getConInfo() {
    Hashtable info = new Hashtable();
    info.put("最大连结数", maxConns + "");
    info.put("当前还有连结数", freeConnections.size() + "");
    info.put("还没释放连结数", checkedOut + "");
    return info;
  }

  private void addLsnrConnection(ConnectionWrapper conwap){
    conwap.setInittime(System.currentTimeMillis());
    lsnrConnections.add(conwap);
  }

  private void startLsnCon(){
    LsnCon lc=new LsnCon();
    Thread t=new Thread(lc);
    t.start();
  }

  class LsnCon implements Runnable {

    public void run() {
      while (true) {
        try {
          Thread.currentThread().sleep(1000);
          Object[] objs = lsnrConnections.toArray();
          //Message.println("开始监控"+name+"数据库连接,监控连接数=" + objs.length);
          for (int i = 0; i < objs.length; i++) {
            ConnectionWrapper con = (ConnectionWrapper) objs[i];
            if (!con.isClosed()) {
              if (isTimeout(con)) {
                //Message.println("真关闭连接");
                con.realClose();
                lsnrConnections.removeElement(con);
              }
            }
            else {
              lsnrConnections.removeElement(con);
            }
          }
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    private boolean isTimeout(ConnectionWrapper con){
      boolean IStimeout=false;
      long t=System.currentTimeMillis()-con.getInittime();
      if(t>(connectiontimeout*1000)) IStimeout=true;
      return IStimeout;
    }

  }

public int getCheckedOut() {
	return checkedOut;
}
}
