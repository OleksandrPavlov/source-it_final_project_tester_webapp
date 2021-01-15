package tester.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import tester.dao.StudentDao;
import tester.factory.ConnectionUtils;
import tester.factory.ResultSetFactory;
import tester.handler.DefaultListResultSetHandler;
import tester.handler.DefaultOneRowResultSet;
import tester.model.TestModel;

public class StudentDaoImpl implements StudentDao {

	QueryRunner queryRunner = new QueryRunner();
	private ResultSetHandler<List<TestModel>> resultSetAllTest = new DefaultListResultSetHandler<TestModel>(
			ResultSetFactory.RESULT_SET_TEST_HANDLER);
	private ResultSetHandler<TestModel> resultSetTest = new DefaultOneRowResultSet<TestModel>(
			ResultSetFactory.RESULT_SET_TEST_HANDLER);

	@Override
	public List<TestModel> getAllTests() {
		Connection con = ConnectionUtils.getCurrentConnection();
		List<TestModel> testModelList = null;
		try {
			testModelList = queryRunner.query(con, "SELECT * FROM test", resultSetAllTest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testModelList;
	}

	@Override
	public Long getQuestionQuantity(Long idTest) {
		Connection con = ConnectionUtils.getCurrentConnection();
		Long result = null;

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM question WHERE id_test=" + idTest);
			if (rs.next()) {
				result = rs.getLong("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getAuthorById(Long id_account) {
		Connection con = ConnectionUtils.getCurrentConnection();
		String result = null;
		StringBuilder sb = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT first_name, last_name FROM account AS a ,test AS t WHERE a.id=t.id_account AND t.id="
							+ id_account);
			sb = new StringBuilder();
			if (rs.next()) {
				sb.append(rs.getString("first_name"));
				sb.append(" " + rs.getString("last_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	@Override
	public TestModel getTestModelById(Long idTest) {
		Connection con = ConnectionUtils.getCurrentConnection();
		TestModel result = null;
		try {
			result = queryRunner.query(con, "SELECT * FROM test  WHERE id=?", resultSetTest, idTest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
