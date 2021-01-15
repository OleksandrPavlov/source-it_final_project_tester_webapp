package tester.model;

public class AnswerModel {
Long id;
Long questionId;
String answerName;
Boolean right;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getQuestionId() {
	return questionId;
}
public void setQuestionId(Long questionId) {
	this.questionId = questionId;
}
public String getAnswerName() {
	return answerName;
}
public void setAnswerName(String answerName) {
	this.answerName = answerName;
}
public Boolean getRight() {
	return right;
}
public void setRight(Boolean right) {
	this.right = right;
}
}
