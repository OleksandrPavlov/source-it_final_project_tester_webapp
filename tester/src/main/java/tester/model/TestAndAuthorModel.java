package tester.model;

public class TestAndAuthorModel {
private TestModel testModel;
private String author;
private Long quantityQuestion;
public TestModel getTestModel() {
	return testModel;
}
public void setTestModel(TestModel testModel) {
	this.testModel = testModel;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public Long getQuantityQuestion() {
	return quantityQuestion;
}
public void setQuantityQuestion(Long quantityQuestion) {
	this.quantityQuestion = quantityQuestion;
}
}
