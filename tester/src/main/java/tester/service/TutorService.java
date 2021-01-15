package tester.service;

import java.util.List;

import tester.exception.EditException;
import tester.exception.GetTestException;
import tester.model.AnswerModel;
import tester.model.QuestionModel;
import tester.model.TestModel;

public interface TutorService {
public List<TestModel> getAllTestById(Long id,int limit,int offset)throws GetTestException;
public Long testQuantity(Long id);
public List<QuestionModel> getQuestionById(Long id, boolean allOrOne) throws GetTestException; 
public void deleteAnswerById(Long id);
public void editQuestionNameById(Long id,String name) throws EditException;
public void editAnswer(Boolean right,String name,Long id)throws EditException;
public void addAnswer(Boolean right,String name, Long id)throws EditException;
public boolean addQuestion(List<AnswerModel> answerName, String questionName, Long testId);
public Short getDuration(Long idTest);
public void setDuration(Long idTest,Short duration);
}
