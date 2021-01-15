package tester.service;

import tester.exception.ValidationException;
import tester.model.AccountModel;

public interface CommonService {
	public AccountModel login(String login,String password) throws ValidationException;
}
