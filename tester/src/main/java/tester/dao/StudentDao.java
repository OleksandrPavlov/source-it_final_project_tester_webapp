package tester.dao;
import java.util.List;

import tester.model.*;
public interface StudentDao {
public List<TestModel> getAllTests();
public Long getQuestionQuantity(Long idTest);
public String getAuthorById(Long id_account);
public TestModel getTestModelById(Long idTest);
}
