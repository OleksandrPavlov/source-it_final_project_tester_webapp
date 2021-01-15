package tester.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import tester.Constants;
import tester.dao.AccountDao;
import tester.dao.service.models.ServiceObjectToInsertRole;
import tester.exception.ApplicationException;
import tester.exception.IncorrectParameters;
import tester.factory.ConnectionUtils;
import tester.factory.ResultSetFactory;
import tester.handler.DefaultListResultSetHandler;
import tester.handler.DefaultOneRowResultSet;
import tester.model.AccountModel;

public class AccountDaoImpl implements AccountDao {

	private final QueryRunner queryRunner = new QueryRunner();
	private ResultSetHandler<AccountModel> resultSetHandler = new DefaultOneRowResultSet<AccountModel>(
			ResultSetFactory.RESULT_SET_ACCOUNT_HANDLER);
	private ResultSetHandler<List<String>> resultSetRoleHandler = new DefaultListResultSetHandler<String>(
			ResultSetFactory.RESULT_SET_ROLE_HANDLER);
	private ResultSetHandler<List<AccountModel>> resultSetAllAccountsHandler = new DefaultListResultSetHandler<AccountModel>(
			ResultSetFactory.RESULT_SET_ACCOUNT_HANDLER);
	private ResultSetHandler<List<AccountModel>> resultSetAllAccountsRoleHandler = new DefaultListResultSetHandler<AccountModel>(
			ResultSetFactory.RESULT_SET_ACCOUNT_ROLE_HANDLER);
	private ResultSetHandler<Map<Long, List<String>>> resultSetRoles = ResultSetFactory.RESULT_SET_ROLES;

	public AccountModel findById(long id) {

		Connection con = ConnectionUtils.getCurrentConnection();
		AccountModel accountModel = null;
		List<String> roleList = null;
		try {

			accountModel = queryRunner.query(con, "SELECT * FROM account WHERE id=?", resultSetHandler, id);
			if (accountModel != null) {
				roleList = queryRunner.query(con, "SELECT * FROM account_role WHERE id_account=?", resultSetRoleHandler,
						accountModel.getId());
			}
			if (accountModel != null) {
				accountModel.setRole(roleList);
			}

			return accountModel;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public AccountModel findByLogin(String login) {
		// We already went to dataBase and collected account
		Connection con = ConnectionUtils.getCurrentConnection();
		AccountModel accountModel = null;
		List<String> roleList = null;
		try {

			accountModel = queryRunner.query(con, "SELECT * FROM account WHERE login=?", resultSetHandler, login);
			if (accountModel != null) {
				roleList = queryRunner.query(con, "SELECT * FROM account_role WHERE id_account=?", resultSetRoleHandler,
						accountModel.getId());
			}
			if (accountModel != null) {
				accountModel.setRole(roleList);
			}
			con.close();
			ConnectionUtils.clearCurrentConnection();
			return accountModel;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

	}

	public List<AccountModel> findAllAccounts() {
		Connection con = ConnectionUtils.getCurrentConnection();
		Statement statement = null;
		List<AccountModel> accountList = null;
		try {
			statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM account AS acc, account_role AS accr WHERE acc.id=accr.id_account");
			accountList = resultSetAllAccountsRoleHandler.handle(resultSet);
			ConnectionUtils.clearCurrentConnection();
			con.close();
			return accountList;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

	}

	public Boolean Enable(Long id) {
		Connection con = ConnectionUtils.getCurrentConnection();
		try {
			int a = queryRunner.update(con, "UPDATE account SET active=true WHERE id=?", id);
			if (a > 0) {
				return true;
			}

		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
		return false;
	}

	public String insertNewAccount(AccountModel accountModel) {
		Connection con = ConnectionUtils.getCurrentConnection();

		try {

			ServiceObjectToInsertRole ser = QueryToAddingRole(accountModel);
			PreparedStatement preparedStatement = con
					.prepareStatement("INSERT INTO account (id,login,password,first_name,last_name,middle_name,email,"
							+ "active,created)VALUES(nextval('account_seq'),?,?,?,?," + "?,?,?,now());"
							+ ser.getQuery());
			preparedStatement.setString(1, accountModel.getLogin());
			preparedStatement.setString(2, accountModel.getPassword());
			preparedStatement.setString(3, accountModel.getFirstName());
			preparedStatement.setString(4, accountModel.getLastName());
			preparedStatement.setString(5, accountModel.getMiddleName());
			preparedStatement.setString(6, accountModel.getEmail());
			preparedStatement.setBoolean(7, accountModel.getActive());
			for (int i = 8; i < 8 + ser.getRoleKod().size(); i++) {
				preparedStatement.setLong(i, ser.getRoleKod().get(i - 8));
			}
			preparedStatement.execute();
			return "OK";

		} catch (SQLException e) {
			return "NOT OK";
		}

	}

	private ServiceObjectToInsertRole QueryToAddingRole(AccountModel accountModel) {
		final String query = "INSERT INTO account_role(id,id_account,id_ro"
				+ "le)VALUES(nextval('account_role_seq'), currval('account_seq'),?);";
		ServiceObjectToInsertRole ser = new ServiceObjectToInsertRole();
		ser.setQuantity(accountModel.getRole().size());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ser.getQuantity(); i++) {
			sb.append(query);
		}
		ser.setQuery(sb.toString());
		List<Long> rk = new ArrayList<>();
		for (String role : accountModel.getRole()) {
			if (role.equals("administrator")) {
				rk.add(3L);
			}
			if (role.equals("tutor")) {
				rk.add(2L);
			}
			if (role.equals("student")) {
				rk.add(1L);
			}
			if (role.equals("advancedTutor")) {
				rk.add(4L);
			}
		}
		ser.setRoleKod(rk);

		return ser;
	}

	@Override
	public void deleteAccount(Long id) {
		String sql = "DELETE FROM account WHERE id=?;";
		Connection con = ConnectionUtils.getCurrentConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setLong(1, id);
			prepareStatement.execute();
		} catch (SQLException e) {
			throw new ApplicationException();
		}

	}

	@Override
	public boolean editAccount(AccountModel accountModel) {
		// Transactional

		Connection con = ConnectionUtils.getCurrentConnection();

		String updateAccountSql = "UPDATE account SET first_name=?,last_name=?,middle_name=?,login=?,password=?,created=now(),email=?,active=? WHERE id=?";
		String deleteRolesSql = "DELETE FROM account_role WHERE id_account=?";
		String insertRole = "INSERT INTO account_role (id,id_account,id_role) VALUES(nextval('account_role_seq'),?,?)";
		// Savepoint sp=null;
		try {
			// con.setAutoCommit(false);
			// con.setSavepoint("beforeInsert");
			queryRunner.update(con, updateAccountSql, accountModel.getFirstName(), accountModel.getLastName(),
					accountModel.getMiddleName(), accountModel.getLogin(), accountModel.getPassword(),
					accountModel.getEmail(), accountModel.getActive(), accountModel.getId());
			// deleting roles from DB
			queryRunner.update(con, deleteRolesSql, accountModel.getId());
			// inserting roles to DB
			PreparedStatement ps = con.prepareStatement(insertRole);
			ps.setLong(1, accountModel.getId());
			for (String role : accountModel.getRole()) {
				if (role.equals("administrator")) {
					ps.setLong(2, 3L);
				}
				if (role.equals("tutor")) {
					ps.setLong(2, 2L);
				}
				if (role.equals("advancedTutor")) {
					ps.setLong(2, 4L);
				}
				if (role.equals("student")) {
					ps.setLong(2, 1L);
				}
				ps.execute();
			}

			// con.commit();

		} catch (SQLException e) {
			/*
			 * try { //con.rollback(sp); } catch (SQLException e1) {
			 */return false;
		}

		return true;
	}

	@Override
	public List<AccountModel> getUsersFromDB(int offset, int limit, String role, int sortParam) {
		Connection con = ConnectionUtils.getCurrentConnection();
		List<AccountModel> accountList = null;
		if (offset < 0 || limit < 0) {
			throw new IncorrectParameters("You passed incorrect offset or limit");
		}
		String sortParamSql = "";
		if (sortParam == Constants.PARAM_SORT_BY_ID) {
			sortParamSql = "ORDER BY id";
		} else if (sortParam == Constants.PARAM_SORT_BY_NAME) {
			sortParamSql = "ORDER BY first_name";
		}
		String sqlAccounts = "SELECT * FROM account " + " ORDER BY created " + " LIMIT " + limit + " " + " OFFSET "
				+ offset + " " + " ";

		try {
			accountList = queryRunner.query(con, sqlAccounts, resultSetAllAccountsHandler);

			if (accountList != null) {
				PreparedStatement ps = con.prepareStatement(buildQueryForExtractingRoles(accountList));
				for (int i = 0; i < accountList.size(); i++) {
					ps.setLong(i + 1, accountList.get(i).getId());
				}
				ResultSet rs = ps.executeQuery();
				Map<Long, List<String>> map = resultSetRoles.handle(rs);
				accountList = mergeRoleAndAccounts(accountList, map);

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;

	}

	private String buildQueryForExtractingRoles(List<AccountModel> listAccount) {
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT * FROM account_role WHERE id_account=?";
		sb.append(sql);
		for (int i = 0; i < listAccount.size() - 1; i++) {
			sb.append(" OR id_account=? ");
		}

		return sb.toString();
	}

	private List<AccountModel> mergeRoleAndAccounts(List<AccountModel> accountList, Map<Long, List<String>> roleMap) {

		List<AccountModel> accountListTemp = new ArrayList<>();
		for (AccountModel accountM : accountList) {
			accountM.setRole(roleMap.get(accountM.getId()));
			accountListTemp.add(accountM);
		}
		return accountListTemp;
	}

	@Override
	public Long rowQuantity() {
		Connection con = ConnectionUtils.getCurrentConnection();
		Long c = null;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM account");
			if (rs.next()) {
				c = rs.getLong("count");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Boolean EnableDisable(Long id, String active) {
		Connection con = ConnectionUtils.getCurrentConnection();
		try {
			int a = queryRunner.update(con, "UPDATE account SET active=" + active + " WHERE id=?", id);
			if (a > 0) {
				return true;
			}

		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
		return false;
	}

}
