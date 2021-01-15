package tester.service;
import java.util.List;

import tester.converter.UneversalConverter;
import tester.exception.GetTestException;
import tester.model.TestAndAuthorModel;
import tester.model.TestModel;
public interface StudentService {
public List<TestModel> getAllTests() throws GetTestException;
public TestAndAuthorModel getTestAndAuthorModel(Long idTest)throws GetTestException;
public UneversalConverter getQuestionConverter();
}
