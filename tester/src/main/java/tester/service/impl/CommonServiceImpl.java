package tester.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import tester.dao.AccountDao;
import tester.exception.ApplicationException;
import tester.exception.ValidationException;
import tester.factory.ConnectionUtils;
import tester.model.AccountModel;
import tester.service.CommonService;

public class CommonServiceImpl implements CommonService {

	private AccountDao accountDao; // Object that able to going to the DataBase
	private DataSource dataSource; // Object that can provide Connection to
									// DataBase

	public CommonServiceImpl(AccountDao accountDao, DataSource dataSource) {
		this.accountDao = accountDao;
		this.dataSource = dataSource;
	}

	public AccountModel login(String login, String password) throws ValidationException {
		AccountModel accountModel;
		try {
			Connection c = dataSource.getConnection();
			ConnectionUtils.setConnection(c);
			accountModel = accountDao.findByLogin(login);
			if (accountModel == null) {
				throw new ValidationException("Account does not exist");
			} else if (!accountModel.getPassword().equals(password)) {
				throw new ValidationException("Password is not correct");
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

		return accountModel;
	}

}
