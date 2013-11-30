package dbconnpac;

import java.io.BufferedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;



public class DBAccess implements DBTool {
	
	private Logger log = Logger.getLogger(DBAccess.class);
	private static DBAccess dbaccess = new DBAccess(); 
//	private static PoolManager poolManager = PoolManager.getInstance();
	//连接池改为proxool
	private ProxoolPoolManager poolManager = new ProxoolPoolManager();

	private DBAccess() {
	}

	public static DBAccess getDBTool() {		
		return dbaccess;
	}

	/**
	 * 根据查询语句得到查询结果
	 * 
	 * @param dbSource
	 *            数据源名称
	 * @param sql
	 *            sql语句
	 * @return CachedRowSet结果集
	 */
	public CachedRowSet querySql(String dbSource, String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			CachedRowSet crs = new CachedRowSet();
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
			}
			// Message.println("conn:" + conn);
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			// Message.println("stmt:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			crs.populate(rs);
			rs.close(); 
			this.close(conn, stmt);
			return crs;
		} catch (Exception ex) { 
			this.close(conn, stmt);
			// ex.printStackTrace();
			log.error(ex.toString());
			return null;
		} finally {
			this.close(conn, stmt);
		}
	}


	/**
	 * 根据查询语句得到查询结果,并保存日志记录；
	 * 
	 * @param dbSource
	 *            数据源名称
	 * @param sql
	 *            sql语句
	 * @return CachedRowSet结果集
	 */
	public CachedRowSet queryRzSql(String dbSource, String sql) {
		// return this.querySql(dbSource, sql);
		Connection conn = null;
		Statement stmt = null;

		try {
			CachedRowSet crs = new CachedRowSet();
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
			}

			if (stmt == null) {
				stmt = conn.createStatement();
			}

			ResultSet rs = stmt.executeQuery(sql);
			crs.populate(rs);
			rs.close();
			this.close(conn, stmt);


			return crs;
		} catch (Exception ex) {
			this.close(conn, stmt);
			ex.printStackTrace();
			log.error(ex.toString());
			return null;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行insert、update语句
	 * 
	 * @param dbSource
	 *            数据源名称
	 * @param sql
	 *            sql语句
	 * @param retMaxSeqence
	 *            无论为true或false，均表示要求返回值为整型
	 * @return 如果返回大于等于0，update语句成功执行，如果返回大于0，insert语句成功执行，返回值为插入的记录的唯一id值
	 */
	public String executeSql(String dbSource, String sql, boolean retMaxSeqence) {

		Connection conn = null;
		Statement stmt = null;
		try {
			String retId = "";

			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}

			stmt.executeUpdate(sql);
			this.close(conn, stmt);
			return retId;

		} catch (Exception ex) {
			this.close(conn, stmt);
			log.error(ex.toString());
			return "";
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行update、insert语句，
	 * 
	 * @param dbSource
	 *            数据源名称
	 * @param sql
	 *            sql语句
	 * @return
	 */
	public boolean executeSql(String dbSource, String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {

			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}

			stmt.executeUpdate(sql);
			this.close(conn, stmt);
			return true;

		} catch (Exception ex) {
			this.close(conn, stmt);
			ex.printStackTrace();
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行不带主键管理的insert语句
	 * 
	 * @param dbSource
	 * @param sql
	 * @return
	 */
	public boolean insertSql(String dbSource, String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			stmt.executeUpdate(sql);
			this.close(conn, stmt);
			return true;
		} catch (Exception ex) {
			this.close(conn, stmt);
			ex.printStackTrace();
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行update、insert语句，对insert语句作主键管理
	 * 
	 * @param dbSource
	 * @param sql
	 * @return
	 */
	public boolean executeSql(String dbSource, Vector sql) {
		Connection conn = null;
		Statement stmt = null;
		try {

			Vector vecUpIn = new Vector();
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
				conn.setAutoCommit(false);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			for (int i = 0; i < sql.size(); i++) {
				String mysql = sql.elementAt(i).toString();

				vecUpIn.add(mysql);

			}
			try {
				for (int i = 0; i < vecUpIn.size(); i++) {
					System.out.println(vecUpIn.elementAt(i).toString().trim());
					stmt.executeUpdate(vecUpIn.elementAt(i).toString().trim());
				}
				conn.commit();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return true;
			} catch (SQLException ex) {

				ex.printStackTrace();
				// Message.println("事务失败！！！");
				conn.rollback();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.close(conn, stmt);
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行insert语句，没有主键管理
	 * 
	 * @param dbSource
	 * @param sql
	 * @return
	 */
	public boolean insertSql(String dbSource, Vector sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
				conn.setAutoCommit(false);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			try {
				for (int i = 0; i < sql.size(); i++) {
					stmt.executeUpdate(sql.elementAt(i).toString().trim());
				}
				conn.commit();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return true;
			} catch (SQLException ex) {
				ex.printStackTrace();
				// Message.println("事务失败！！！");
				conn.rollback();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.close(conn, stmt);
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行update、insert语句，insert语句不带有主键管理
	 * 
	 * @param dbSource
	 * @param sql
	 * @return
	 */
	public boolean executeSql(String dbSource, String[] sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// SeqMang seqMang = SeqManager.getSeqMang();
			Vector vecUpIn = new Vector();
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
				conn.setAutoCommit(false);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			for (int i = 0; i < sql.length; i++) {
				String mysql = sql[i].trim();

				vecUpIn.add(mysql);

			}
			try {
				for (int i = 0; i < vecUpIn.size(); i++) {
					stmt.executeUpdate(vecUpIn.elementAt(i).toString().trim());
				}
				conn.commit();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return true;
			} catch (SQLException ex) { 
				conn.rollback();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return false;
			}
		} catch (Exception ex) {
			this.close(conn, stmt);
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行insert语句，没有主键管理
	 * 
	 * @param dbSource
	 * @param sql
	 * @return
	 */
	public boolean insertSql(String dbSource, String[] sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
				conn.setAutoCommit(false);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			try {
				for (int i = 0; i < sql.length; i++) {
					stmt.executeUpdate(sql[i].trim());
				}
				conn.commit();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				
				return true;
			} catch (SQLException ex) {
				// Message.println("事务失败！！！");
				conn.rollback();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return false;
			}
		} catch (Exception ex) {
			this.close(conn, stmt);
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	public void close(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (!(conn == null || conn.isClosed())) {
				conn.close();
			}
//			log.info("关闭连接,close(conn,stm)");
		} catch (Exception ex) {
			log.error(ex.toString()); 
		}
	}
	 
	private Connection getConnection(String dbSource) {
		try {
			return poolManager.getConnection(dbSource);

		} catch (Exception ex) {
			log.error(ex.toString());
			return null;
		}
	}

	public boolean updateSql(String dbSource, String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			stmt.executeUpdate(sql);
			this.close(conn, stmt);
			return true;
		} catch (Exception ex) {
			this.close(conn, stmt);
			log.error(ex.toString());
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}

	/**
	 * 执行update isnert语句,对insert语句不作主健管理
	 * 
	 * @param dbSource
	 *            数据源
	 * @param sql
	 * @return
	 */
	public boolean executeVecSql(String dbSource, Vector sql) {
		boolean flag = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			// SeqMang seqMang = SeqManager.getSeqMang();
			if (conn == null || conn.isClosed()) {
				conn = getConnection(dbSource);
				conn.setAutoCommit(false);
			}
			if (stmt == null) {
				stmt = conn.createStatement();
			}
			try {
				for (int i = 0; i < sql.size(); i++) {
					String s = sql.elementAt(i).toString();
					// Message.println("execute sql="+s);
					stmt.executeUpdate(s);
					// stmt.executeUpdate(sql.elementAt(i).toString().trim());
				}
				conn.commit();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return true;
			} catch (SQLException ex) {

				ex.printStackTrace();
				// Message.println("事务失败！！！");
				conn.rollback();
				conn.setAutoCommit(true);
				this.close(conn, stmt);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.close(conn, stmt);
			return false;
		} finally {
			this.close(conn, stmt);
		}
	}
}
