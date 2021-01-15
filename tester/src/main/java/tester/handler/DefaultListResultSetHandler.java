package tester.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class DefaultListResultSetHandler<T> implements ResultSetHandler<List<T>> {

	private ResultSetHandler<T> oneRowResultSetHandler;

	public DefaultListResultSetHandler(ResultSetHandler<T> oneRowResultSetHandler) {
		this.oneRowResultSetHandler = oneRowResultSetHandler;
	}

	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list = new ArrayList<T>();
		while (rs.next()) {
			list.add(oneRowResultSetHandler.handle(rs));
		}
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

}
