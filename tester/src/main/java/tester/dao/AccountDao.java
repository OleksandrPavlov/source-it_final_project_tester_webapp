package tester.dao;

import java.util.List;

import tester.model.AccountModel;

public interface AccountDao {
public AccountModel findById(long id);
public AccountModel findByLogin(String login);
public List<AccountModel> findAllAccounts();
public Boolean Enable(Long id);
public Boolean EnableDisable(Long id,String active);
public String insertNewAccount(AccountModel accountModel);
public void deleteAccount(Long id);
public boolean editAccount(AccountModel accountModel);
public List<AccountModel> getUsersFromDB(int offset,int limit,String role,int sortParam);
public Long rowQuantity();
}


