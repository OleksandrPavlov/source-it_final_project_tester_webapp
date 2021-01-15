<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="card">
  <div class="card-header">
    Question Editor
  </div>
  <div class="card-body">
   <form action="/tutor/home/test/question-edit" method="POST">
   <div class="form-group">
    <label for="formGroupExampleInput">Example label</label>
    <input type="text" class="form-control" id="formGroupExampleInput" value='${TUTOR_DATA.getQuestionName()}' name="questionName">
    <input type="hidden" name="idTest" value="${TUTOR_DATA.getTestId()}">
    <input type="hidden" name="id" value="${TUTOR_DATA.getId() }">
    <input type="hidden" name="testName" value="${testName}">
    
  </div>
    <button type="submit" class="btn btn-success">save</button>
   </form>
  </div>
</div>