package tester.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import tester.converter.UneversalConverter;
import tester.dao.AccountDao;
import tester.exception.ApplicationException;
import tester.exception.ConvertException;
import tester.exception.DeleteAccountException;
import tester.exception.EditException;
import tester.exception.GetUserFromDataBaseException;
import tester.exception.ValidationException;
import tester.factory.ConnectionUtils;
import tester.model.AccountModel;
import tester.model.QuestionModel;
import tester.model.RawQuestionModel;
import tester.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private AccountDao accountDao;
	private DataSource dataSource;
	private UneversalConverter<AccountModel, HttpServletRequest> fromHttpRquestToAccountModel = new UneversalConverter<AccountModel, HttpServletRequest>() {

		@Override
		public AccountModel convertThis(HttpServletRequest req) throws ConvertException {
			if (!checkFormOnIntegrity(req)) {
				throw new ConvertException("You missed fields!");
			} else {
				AccountModel accountModel = new AccountModel();
				accountModel.setEmail(req.getParameter("email"));
				accountModel.setFirstName(req.getParameter("name"));
				accountModel.setLastName(req.getParameter("surname"));
				accountModel.setMiddleName(req.getParameter("middlename"));
				accountModel.setLogin(req.getParameter("login"));
				accountModel.setPassword(req.getParameter("password"));
				if (req.getParameter("active").equals("true")) {
					accountModel.setActive(true);
				} else {
					accountModel.setActive(false);
				}
				List<String> roleList = new ArrayList<String>();
				if (req.getParameter("administrator") != null) {
					roleList.add("administrator");
				}
				if (req.getParameter("tutor") != null) {
					roleList.add("tutor");
				}
				if (req.getParameter("advancedTutor") != null) {
					roleList.add("advancedTutor");
				}
				if (req.getParameter("student") != null) {
					roleList.add("student");
				}
				accountModel.setRole(roleList);
				return accountModel;
			}
		}

		private boolean checkFormOnIntegrity(HttpServletRequest req) {

			String name = req.getParameter("name");
			String surname = req.getParameter("surname");
			String middlename = req.getParameter("middlename");
			String login = req.getParameter("login");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String active = req.getParameter("active");
			String administrator = req.getParameter("administrator");
			String tutor = req.getParameter("tutor");
			String advancedTutor = req.getParameter("advancedTutor");
			String student = req.getParameter("student");

			if (Objects.isNull(name) || Objects.isNull(surname) || Objects.isNull(middlename) || Objects.isNull(login)
					|| Objects.isNull(password)) {

				return false;
			} else {
				if (Objects.isNull(administrator) && Objects.isNull(tutor) && Objects.isNull(advancedTutor)
						&& Objects.isNull(student)) {
					return false;
				}
			}
			return true;
		}

	};

	public AdminServiceImpl(AccountDao accountDao, DataSource dataSource) {
		this.dataSource = dataSource;
		this.accountDao = accountDao;
	}

	public UneversalConverter<AccountModel, HttpServletRequest> getConverter() {
		return fromHttpRquestToAccountModel;
	}

	public List<AccountModel> findAllAccounts() throws ValidationException {
		List<AccountModel> accountList = null;
		try {
			Connection con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			accountList = accountDao.findAllAccounts();
			if (accountList == null) {
				throw new ValidationException("There is no one account yet!");
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

		return accountList;
	}

	public Boolean Enable(Long id) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			boolean suc = accountDao.Enable(id);

			return suc;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}

	}

	public boolean insertNewAccount(AccountModel accountModel) throws ValidationException {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			if (accountDao.insertNewAccount(accountModel).equals("NOT OK")) {
				con.close();
				ConnectionUtils.clearCurrentConnection();
				throw new ValidationException("Data Base Error!");
			}
			return true;

		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new ApplicationException(e);
			}
			ConnectionUtils.clearCurrentConnection();
		}

	}

	@Override
	public void deleteAccount(Long id) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			accountDao.deleteAccount(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}
	}

	@Override
	public AccountModel findById(Long id) throws ValidationException {
		Connection con = null;
		AccountModel accountModel = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			accountModel = accountDao.findById(id);
			if (accountModel == null) {
				throw new ValidationException("The user with such id has not been found!");
			}
		} catch (SQLException e) {

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();

		}

		return accountModel;
	}

	@Override
	public void editAccount(AccountModel accountModel) throws EditException {
		Connection con = null;

		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			if (!accountDao.editAccount(accountModel)) {
				throw new EditException("Account not has been edited!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();

		}

	}

	@Override
	public List<AccountModel> getUsersFromDB(int offset, int limit, String role, int sortParam)
			throws GetUserFromDataBaseException {
		Connection con = null;
		List<AccountModel> accountList = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			accountList = accountDao.getUsersFromDB(offset, limit, role, sortParam);
			if (Objects.isNull(accountList)) {
				throw new GetUserFromDataBaseException("There is no one account yet!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();

		}
		return accountList;
	}

	@Override
	public Long rowQuantity() {
		Connection con = null;
		Long c = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			c = accountDao.rowQuantity();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}
		return c;
	}

	@Override
	public Boolean EnableDisable(Long id, String active) {
		//Connection con = null;
		try(Connection con = dataSource.getConnection()){
			//con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			boolean suc = accountDao.EnableDisable(id, active);
			return suc;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {	
			ConnectionUtils.clearCurrentConnection();
		}

	}

}
