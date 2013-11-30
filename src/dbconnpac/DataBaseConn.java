//include required classes
package dbconnpac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

//==========================================
// Define Class databaseconn
//==========================================
public class DataBaseConn {
	private Logger log = Logger.getLogger("huacaizx");
	private DBTool dbTool =  DBAccess.getDBTool();
	private String dbSource = ConstantSymbol.dbSource;
	// private String pwd = "W2cvijTRRE";
	/*
	 * private String sqlDriver = "com.mysql.jdbc.Driver"; private String sqlUrl =
	 * "jdbc:mysql://localhost:3306"; private String sqlDBName = "huacaizx_com";
	 * private String user = "huacaizx_com"; private String pwd =
	 * "workerwork123";
	 */

	/* localhost */
//	private String sqlDriver = "com.mysql.jdbc.Driver";
//	private String sqlUrl = "jdbc:mysql://localhost:3306";// local
//	private String sqlDBName = "lottery";
//	private String user = "root";
//	private String pwd = "password";

	/** 服务器 old* */
	// private String sqlDriver = "com.mysql.jdbc.Driver";
	// private String sqlUrl = "jdbc:mysql://211.147.221.2:3306";//server
	// private String sqlDBName = "magic_lottery";
	// private String user = "magic_lottery_f";
	// private String pwd = "~123qwe";

	/** 服务器 new* */
	// private String sqlDriver = "com.mysql.jdbc.Driver";
	// private String sqlUrl = "jdbc:mysql://211.147.221.2:3306";//server
	// private String sqlDBName = "magic_lottery";
	// private String user = "magic_lottery_f";
	// private String pwd = "~123qwe";
	
	
	// private Connection conn = null;
	// private Statement stmt = null;
	// private ResultSet rs = null;
	private CachedRowSet crs = null;

	public DataBaseConn() {
		
		/*
		 * try{ Class.forName(this.sqlDriver);//加载数据库驱动程序 this.conn =
		 * DriverManager.getConnection(this.sqlUrl + "/" + this.sqlDBName +
		 * "?user=" + this.user + "&password=" + this.pwd +
		 * "&useUnicode=true&characterEncoding=gb2312"); this.stmt =
		 * this.conn.createStatement(); }catch(Exception e){
		 * log.error(e.toString()); try{ if(stmt != null){ stmt.close(); }
		 * if(conn != null){ conn.close(); } }catch(Exception ee){
		 * log.error("DataBaseConn close conn error!"); } }
		 */
	}

	public CachedRowSet execQuery(String sql) {
		this.crs = dbTool.querySql(dbSource, sql);
		return this.crs;
	}

	// 执行查询操作
//	public ResultSet execQuery_(String strSql) {
//		try {
//			this.rs = stmt.executeQuery(strSql);
//			return this.rs;
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return null;
//		} catch (NullPointerException e) {
//			log.error(e.toString());
//			return null;
//		}
//	}

	public boolean execute(String sql) {
		return dbTool.executeSql(dbSource, sql);
	}

	// 执行数据的插入、删除、修改操作
//	public boolean execute_(String strSql) {
//		try {
//			int intMark = this.stmt.executeUpdate(strSql);
//			// log.error(intMark);
//			if (intMark == 0) {
//				// System.out.println(intMark);
//				return false;
//			} else {
//				// System.out.println(intMark);
//				return true;
//			}
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return false;
//		} catch (NullPointerException e) {
//			log.error(e.toString());
//			return false;
//		}
//	}

	public boolean rsNext() {
		try {
			return crs.next();
		} catch (SQLException e) {
			log.error(e.toString());
			e.printStackTrace();
			return false;
		}
	}
	public int rsSize(){
		return crs.size();
	}
//	public boolean rsNext_() {
//		try {
//			return this.rs.next();
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return false;
//		}
//	}

	public boolean rsPrevious() {
		try {
			return this.crs.previous();
		} catch (SQLException e) {
			log.error(e.toString());
			return false;
		}
	}
//	public boolean rsPrevious_() {
//		try {
//			return this.rs.previous();
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return false;
//		}
//	}
	public boolean rsLast() {
		try {
			return this.crs.last();
		} catch (SQLException e) {
			log.error(e.toString());
			return false;
		}
	}

//	public boolean rsLast_() {
//		try {
//			return this.rs.last();
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return false;
//		}
//	}

//	public String rsGetString_(String column) {
//		try {
//			return this.rs.getString(column);
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return null;
//		}
//	}

	/**
	 * 提供从CachedRowSet中取得数据的函数，如果为null，返回的为“”
	 * 
	 * @param crs
	 *            CachedRowSet
	 * @param str
	 *            字段名称字符串
	 * @return
	 */
	public String rsGetString(String str) {
		try {
			String temp = crs.getString(str);
			if (temp != null && !temp.equals("null")) {
				return temp;
			} else {
				return "";
			}

		} catch (Exception ex) {
			return "";
		}
	}

//	public int rsGetInt_(int field) {
//		try {
//			return this.rs.getInt(field);
//		} catch (SQLException e) {
//			log.error(e.toString());
//			return -1;
//		}
//	}
	/**
	 * add ljy on 2009-09-03
	 * 
	 * @param crs
	 * @param str
	 * @return
	 */
	public int rsGetInt( int field) {
		try {
			return crs.getInt(field);
		} catch (Exception ex) {
			return 0;
		}
	}
	/**
	 * add ljy on 2009-09-03
	 * 
	 * @param crs
	 * @param str
	 * @return
	 */
	public int rsGetInt( String str) {
		try {
			return crs.getInt(str);
		} catch (Exception ex) {
			return 0;
		}
	}

	public void connClose(){
		//do nothing
	}
//	public void connClose_() {
//		try {
//			this.rs.close();
//			this.stmt.close();
//			this.conn.close();
//		} catch (SQLException e) {
//			log.error(e.toString());
//		}
//	}
	public void connCloseUpdate() {
		//do nothing
	}
//	public void connCloseUpdate_() {
//		try {
//			this.stmt.close();
//			this.conn.close();
//		} catch (SQLException e) {
//			log.error(e.toString());
//		}
//	}

}
