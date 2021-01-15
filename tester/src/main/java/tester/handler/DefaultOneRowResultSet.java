package tester.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class DefaultOneRowResultSet<T> implements ResultSetHandler<T> {
	private ResultSetHandler<T> oneRowResultSetHandler;

	public DefaultOneRowResultSet(ResultSetHandler<T> oneRowResultSetHandler) {
		this.oneRowResultSetHandler = oneRowResultSetHandler;
	}
	public T handle(ResultSet rs) throws SQLException {
		if(rs.next()){
			return oneRowResultSetHandler.handle(rs);
		}
		return null;
	}

}
