package tester.service;

import java.util.List;

import tester.converter.UneversalConverter;
import tester.exception.DeleteAccountException;
import tester.exception.EditException;
import tester.exception.GetUserFromDataBaseException;
import tester.exception.ValidationException;
import tester.model.AccountModel;

public interface AdminService {
 List<AccountModel> findAllAccounts()throws ValidationException;
 Boolean Enable(Long id);
 Boolean EnableDisable(Long id,String active);
 boolean insertNewAccount(AccountModel accountModel)throws ValidationException;
 UneversalConverter<?, ?> getConverter();
 void deleteAccount(Long id);
 AccountModel findById(Long id) throws ValidationException;
 void editAccount(AccountModel accountModel)throws EditException;
 List<AccountModel> getUsersFromDB(int offset,int limit,String role,int sortParam)throws GetUserFromDataBaseException;
 Long rowQuantity();
}
