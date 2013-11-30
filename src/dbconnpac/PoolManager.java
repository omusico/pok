package dbconnpac;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

public class PoolManager {

	private Logger log = Logger.getLogger(PoolManager.class);
	static private PoolManager instance;
	static private int clients;


	/*	服务器  
	static private String poolName = ConstantSymbol.dbSource;
	static private String driverClass = "com.mysql.jdbc.Driver";
	static private String url = "jdbc:mysql://211.147.221.2:3306/magic_lottery";
	static private String user = "magic_lottery_f";
	static private String password = "~123qwe";
	*/

	/*	localhost */

	static private String poolName = ConstantSymbol.dbSource;
	static private String driverClass = "com.mysql.jdbc.Driver";
	static private String url = "jdbc:mysql://localhost:3306/lottery";
	static private String user = "root";
	static private String password = "password";
 
	
	static private String maxconns = "30";
	static private String initconns = "5";
	static private String timeout = "300";
	static private String connectiontimeout = "300";
	static private String logfile = "conlogfile.log";
	static private String loglevel = "1";



	private Vector drivers = new Vector();
	private Hashtable pools = new Hashtable();

	private PoolManager() {
		init();
	}

	static synchronized public PoolManager getInstance() {
		if (instance == null) {
			instance = new PoolManager();
		}
		clients++;
		return instance;
	}

	private void init() {

		Map map = new HashMap();

		try {

			map.put("driverClass", driverClass);
			map.put(poolName + ".url", url);
			map.put(poolName + ".user", user);
			map.put(poolName + ".password", password);
			map.put(poolName + ".maxconns", maxconns);
			map.put(poolName + ".initconns", initconns);
			map.put(poolName + ".timeout", timeout);
			map.put(poolName + ".connectiontimeout", connectiontimeout);
			map.put("logfile", logfile);
			map.put(poolName + ".loglevel", loglevel);
			log.info("init poolManager-- OK");
		} catch (Exception e) {
			 log.error("Can't load the properties file. Make sure db.properties is in the CLASSPATH");
			return;
		}

		String logFile = (String) map.get("logfile");

		log.info(map.size());


		loadDrivers(map);
		log.info("驱动加载完毕 OK! ");
		log.info("开始创建连接池...");
		createPools(map);
		log.info("连接池创建完毕 OK!");
	}

	private void loadDrivers(Map map){
		String driverClasses = (String) map.get("driverClass");
		StringTokenizer st = new StringTokenizer(driverClasses, ",");
		
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver) Class.forName(driverClassName)
						.newInstance();

				drivers.addElement(driver);
				
			} catch (Exception e) {
				log.error("Can't register JDBC driver: " + driverClassName);
			}
		}
	}

	private void createPools(Map map) {
		Set set = map.keySet();
		Iterator iterator = set.iterator();

		// while (propNames.hasMoreElements())
		while (iterator.hasNext()) {
			// String name = (String) propNames.nextElement();
			String name = (String) iterator.next();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));

				String url = (String) map.get(poolName + ".url");
				if (url == null) {
					log.error("No URL specified for " + poolName);
					continue;
				}


				String user = (String) map.get(poolName + ".user");

				String password = (String) map.get(poolName + ".password");

				String maxConns = (String) map.get(poolName + ".maxconns");
				if (maxConns == null || maxConns.equals(""))
					maxConns = "0";
				int max;
				try {
					max = Integer.valueOf(maxConns).intValue();
				} catch (NumberFormatException e) {
					log.error("Invalid maxconns value " + maxConns + " for "
							+ poolName);
					max = 0;
				}

				String initConns = (String) map.get(poolName + ".initconns");
				if (initConns == null || initConns.equals(""))
					initConns = "0";
				int init;
				try {
					init = Integer.valueOf(initConns).intValue();
				} catch (NumberFormatException e) {
					log.error("Invalid initconns value " + initConns + " for "
							+ poolName);
					init = 0;
				}


				String loginTimeOut = (String) map.get(poolName + ".timeout");
				if (loginTimeOut == null || loginTimeOut.equals(""))
					loginTimeOut = "5";
				int timeOut;
				try {
					timeOut = Integer.valueOf(loginTimeOut).intValue();
				} catch (NumberFormatException e) {
					log.error("Invalid logintimeout value " + loginTimeOut
							+ " for " + poolName);
					timeOut = 5;
				}

				String conTimeOut = (String) map.get(poolName
						+ ".connectiontimeout");
				if (conTimeOut == null || conTimeOut.equals(""))
					conTimeOut = "10";
				long iConTimeOut;
				try {
					iConTimeOut = Integer.valueOf(conTimeOut).intValue();
				} catch (NumberFormatException e) {
					log.error("Invalid logintimeout value " + conTimeOut
							+ " for " + poolName);
					iConTimeOut = 10;
				}

				String lf = System.getProperty("line.separator");
				if (lf != null) {
//					log.info(lf + " url=" + url + lf + " user=" + user + lf
//							+ " password=" + password + lf + " initconns="
//							+ init + lf + " maxconns=" + max + lf
//							+ " logintimeout=" + timeOut
//							+ " connectiontimeout=" + iConTimeOut);
					// log.log(getStats(), log.DEBUG);
				}

				ConnectionPool pool = new ConnectionPool(poolName, url, user,
						password, max, init, timeOut, iConTimeOut);

				pools.put(poolName, pool);
			}
		}
	}

	public Connection getConnection(String name) {
		Connection conn = null;

		ConnectionPool pool = (ConnectionPool) pools.get(name);

		if (pool != null) {
			try {
				conn = pool.getConnection();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				log.error("Exception getting connection from " + name);
			}
		} else {
			log.warn("************pool empty***********");
		}

		return conn;
	}

	public synchronized void freeConnection(String name, Connection con) {
		ConnectionPool pool = (ConnectionPool) pools.get(name);
		if (pool != null) {

			pool.freeConnection(con);
		}
		// pool.printLog();
	}

	public synchronized int getCurConnNum(String name) {
		ConnectionPool pool = (ConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getCheckedOut();
		}
		return 0;
	}

	public synchronized void release() {
		// Wait until called by the last client
		if (--clients != 0) {
			return;
		}

		Enumeration allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			ConnectionPool pool = (ConnectionPool) allPools.nextElement();
			pool.release();
		}

		Enumeration allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);

			} catch (SQLException e) {
				log.error("Couldn't deregister JDBC driver: " + e.toString());
			}
		}
	}

	public Map getConInfo() {
		Hashtable info = new Hashtable();
		Set info1 = pools.keySet();
		Iterator iterator = info1.iterator();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			ConnectionPool pool = (ConnectionPool) pools.get(name);
			info.put(name, pool.getConInfo());
		}
		return info;
	}

	public static int getClients() {
		return clients;
	}
}
