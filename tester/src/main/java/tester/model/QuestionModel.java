package tester.model;

import java.util.List;

public class QuestionModel {
	private Long id;
	private Long testId;
	private String questionName;
	private List<AnswerModel> answerList;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public List<AnswerModel> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerModel> answerList) {
		this.answerList = answerList;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

}
