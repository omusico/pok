package dbconnpac;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
 

 
public class DataSourceAdapter implements DataSource {

	/**
	 * 
	 */
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	/**
	 * 
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = (Connection) threadLocal.get();
		if (conn == null || conn.isClosed()) {
			conn = PoolManager.getInstance().getConnection(ConstantSymbol.dbSource);
			threadLocal.set(conn);
		}
		return conn;
	}
	
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLoginTimeout(int seconds) throws SQLException {
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
	}
	public boolean isWrapperFor(java.lang.Class<?> iface) throws java.sql.SQLException{
    	return true;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
	
}
