package tester.model;

public class RawQuestionModel {
Long id;
Long testId;
String name;
Long answerId;
Long questionId;
String answerName;
Boolean right;

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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getAnswerId() {
	return answerId;
}
public void setAnswerId(Long answerId) {
	this.answerId = answerId;
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
