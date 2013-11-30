package dbconnpac;


import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;



public class ConnectionWrapper implements Connection {

	private Connection realConn;

	private ConnectionPool pool;

	private boolean isClosed = false;

	private long inittime = 0;

	public ConnectionWrapper(Connection realConn, ConnectionPool pool) {
		this.realConn = realConn;
		this.pool = pool;
	}

	public void close() throws SQLException {
		if (!isClosed) {
			// System.out.println("&&&&&&&&");
			pool.wrapperClosed(realConn);
			isClosed = true;
		}
	}

	// 真关闭连接,处理超时未关闭的情况
	public void realClose() {
		try {
			// Message.println("isClosed:"+isClosed);
			if (!isClosed) {
				pool.realConClosed(realConn);
				isClosed = true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public boolean isClosed() throws SQLException {
		return isClosed;
	}

	public void clearWarnings() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.clearWarnings();
	}

	public void commit() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.commit();
	}

	public Statement createStatement() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new StatementWrapper(realConn.createStatement());
	}

	public boolean getAutoCommit() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getAutoCommit();
	}

	public String getCatalog() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getCatalog();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getMetaData();
	}

	public int getTransactionIsolation() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getWarnings();
	}

	public boolean isReadOnly() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.isReadOnly();
	}

	public String nativeSQL(String sql) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.nativeSQL(sql);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.prepareCall(sql);
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		
		return new PreparedStatementWrapper(realConn.prepareStatement(sql), sql);
	}

	public void rollback() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.rollback();
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.setAutoCommit(autoCommit);
	}

	public void setCatalog(String catalog) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.setCatalog(catalog);
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.setReadOnly(readOnly);
	}

	public void setTransactionIsolation(int level) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.setTransactionIsolation(level);
	}

	public void setTypeMap(Map map) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.setTypeMap(map);
	}

	public Map getTypeMap() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getTypeMap();
	}

	public CallableStatement prepareCall(String s, int i, int j)
			throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.prepareCall(s);
	}

	public PreparedStatement prepareStatement(String s, int i, int j)
			throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new PreparedStatementWrapper(realConn.prepareStatement(s, i, j),
				s);
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new StatementWrapper(realConn.createStatement(resultSetType, resultSetConcurrency));
	}

	public void setHoldability(int int0) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.setHoldability(int0);
	}

	public int getHoldability() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.getHoldability();
	}

	public Savepoint setSavepoint() throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}

		return realConn.setSavepoint();
	}

	public Savepoint setSavepoint(String string) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.setSavepoint(string);
	}

	public void rollback(Savepoint savepoint) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.rollback(savepoint);
	}

	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		realConn.releaseSavepoint(savepoint);
	}

	public Statement createStatement(int int0, int int1, int int2)
			throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new StatementWrapper(realConn.createStatement(int0, int1, int2));
	}

	public PreparedStatement prepareStatement(String string, int int1,
			int int2, int int3) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new PreparedStatementWrapper(realConn.prepareStatement(string,
				int1, int2, int3), string);
	}

	public CallableStatement prepareCall(String string, int int1, int int2,
			int int3) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return realConn.prepareCall(string, int1, int2, int3);
	}

	public PreparedStatement prepareStatement(String string, int int1)
			throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new PreparedStatementWrapper(realConn.prepareStatement(string,
				int1), string);

	}

	public PreparedStatement prepareStatement(String string, int[] intArray)
			throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new PreparedStatementWrapper(realConn.prepareCall(string),
				string);

	}

	public PreparedStatement prepareStatement(String string,
			String[] stringArray) throws SQLException {
		if (isClosed) {
			throw new SQLException("Pooled connection is closed");
		}
		return new PreparedStatementWrapper(realConn.prepareStatement(string,
				stringArray), string);
	}

	public long getInittime() {
		return inittime;
	}

	public void setInittime(long inittime) {
		this.inittime = inittime;
	}

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Clob createClob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setClientInfo(String name, String value)
            throws SQLClientInfoException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setClientInfo(Properties properties)
            throws SQLClientInfoException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
