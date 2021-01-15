package tester.dao;

import java.util.List;

import tester.model.AnswerModel;
import tester.model.QuestionModel;
import tester.model.TestModel;

public interface TutorDao {
	public List<TestModel> getAllTestById(Long id, int limit, int offset);

	public Long testQuantity(Long id);
/*
 * 
 * 
 * allOrOne - if true- to get all questions -if false to get one
 * 
 */
	public List<QuestionModel> getQuestionById(Long id, boolean allOrOne); 
	public void deleteAnswerById(Long id);
	public boolean editQuestionNameById(Long id,String name);
	public boolean editAnswer(Boolean right,String name,Long id);
    public boolean addAnswer(Boolean right,String name,Long id);
    public boolean addQuestion(List<AnswerModel> answerName,String questionName,Long testId);
	public Short getDuration(Long idTest);
	public void setDuration(Long idTest,Short duration);
}
