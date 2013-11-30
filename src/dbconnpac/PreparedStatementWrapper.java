package dbconnpac;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PreparedStatementWrapper extends StatementWrapper implements
		PreparedStatement {

	private PreparedStatement stat;

	private List<String> sql = new ArrayList<String>();
	
	private boolean formFlag = false;

	public PreparedStatementWrapper(PreparedStatement stat, String sql) {
		super(stat);
		this.stat = stat;
		int start = 0;
		for (int i = sql.indexOf('?'); i != -1; start = i + 1, i = sql.indexOf(
				'?', start)) {
			this.sql.add(sql.substring(start, i));
			this.sql.add("");
		}
		this.sql.add(sql.substring(start));
	}
	
	public PreparedStatementWrapper(PreparedStatement stat, String sql, boolean formFlag) {
		super(stat);
		this.stat = stat;
		this.formFlag = formFlag;
		int start = 0;
		for (int i = sql.indexOf('?'); i != -1; start = i + 1, i = sql.indexOf(
				'?', start)) {
			this.sql.add(sql.substring(start, i));
			this.sql.add("");
		}
		this.sql.add(sql.substring(start));
	}

	private String getSql() {
		StringBuilder sb = new StringBuilder();
		for (String s : sql)
			sb.append(s);
		return sb.toString();
	}

	public void addBatch() throws SQLException {
		stat.addBatch();
		addSql(getSql());
	}

	public void clearParameters() throws SQLException {		
		stat.clearParameters();		
	}

	public boolean execute() throws SQLException {
		 
		return stat.execute();
	}

	public ResultSet executeQuery() throws SQLException {
		
		return stat.executeQuery();
	}

	public int executeUpdate() throws SQLException {
		 
		return stat.executeUpdate();
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return stat.getMetaData();
	}

	public ParameterMetaData getParameterMetaData() throws SQLException {
		return stat.getParameterMetaData();
	}

	public void setArray(int i, Array x) throws SQLException {
		stat.setArray(i, x);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		stat.setAsciiStream(parameterIndex, x, length);
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		stat.setBigDecimal(parameterIndex, x);
	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		stat.setBinaryStream(parameterIndex, x, length);
	}

	public void setBlob(int i, Blob x) throws SQLException {
		stat.setBlob(i, x);
	}

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		stat.setBoolean(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, x ? "'true'" : "'false'");
	}

	public void setByte(int parameterIndex, byte x) throws SQLException {
		stat.setByte(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, Byte.toString(x));
	}

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		stat.setBytes(parameterIndex, x);
	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		stat.setCharacterStream(parameterIndex, reader, length);
	}

	public void setClob(int i, Clob x) throws SQLException {
		stat.setClob(i, x);
	}

	public void setDate(int parameterIndex, Date x) throws SQLException {
		stat.setDate(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		stat.setDate(parameterIndex, x, cal);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setDouble(int parameterIndex, double x) throws SQLException {
		stat.setDouble(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, Double.toString(x));
	}

	public void setFloat(int parameterIndex, float x) throws SQLException {
		stat.setFloat(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, Float.toString(x));
	}

	public void setInt(int parameterIndex, int x) throws SQLException {
		stat.setInt(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'"+Integer.toString(x)+"'");
	}

	public void setLong(int parameterIndex, long x) throws SQLException {
		stat.setLong(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, Long.toString(x));
	}

	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		stat.setNull(parameterIndex, sqlType);
		sql.set(parameterIndex * 2 -1, "''");
	}

	public void setNull(int paramIndex, int sqlType, String typeName)
			throws SQLException {
		stat.setNull(paramIndex, sqlType, typeName);
		sql.set(paramIndex, "''");
	}

	public void setObject(int parameterIndex, Object x) throws SQLException {
		stat.setObject(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		stat.setObject(parameterIndex, x, targetSqlType);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scale) throws SQLException {
		stat.setObject(parameterIndex, x, targetSqlType, scale);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setRef(int i, Ref x) throws SQLException {
		stat.setRef(i, x);
	}

	public void setShort(int parameterIndex, short x) throws SQLException {
		stat.setShort(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'"+Short.toString(x)+"'");
	}

	public void setString(int parameterIndex, String x) throws SQLException {
		stat.setString(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setTime(int parameterIndex, Time x) throws SQLException {
		stat.setTime(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		stat.setTime(parameterIndex, x, cal);
	}

	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		stat.setTimestamp(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		stat.setTimestamp(parameterIndex, x, cal);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setURL(int parameterIndex, URL x) throws SQLException {
		stat.setURL(parameterIndex, x);
		sql.set(parameterIndex * 2 -1, "'" + x + "'");
	}

	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		stat.setUnicodeStream(parameterIndex, x, length);
	}

	/**
	 * @return the formFlag
	 */
	public boolean isFormFlag() {
		return formFlag;
	}

	/**
	 * @param formFlag the formFlag to set
	 */
	public void setFormFlag(boolean formFlag) {
		this.formFlag = formFlag;
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
    public boolean isClosed() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isPoolable() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNString(int parameterIndex, String value)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value,
            long length) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader,
            long length) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream)
            throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        // TODO Auto-generated method stub
        
    }
	
}
