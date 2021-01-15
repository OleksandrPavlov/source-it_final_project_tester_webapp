<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "tester.model.*"  %>

<div align="center" style="color: #6183bf;">
	<p>
		<strong> ${testName}</strong>
	</p>
</div>

<c:if test="${STUDENT_ERROR!=null }">
	<div class="alert alert-danger" role="alert">
		<h4 class="alert-heading">${STUDENT_ERROR}</h4>



	</div>
</c:if>

<c:if test="${STUDENT_SUCCESS!=null }">
	<div class="alert alert-success" role="alert">
		<h4 class="alert-heading">${STUDENT_SUCCESS}</h4>
	</div>
</c:if>


<c:if test="${STUDENT_DATA!=null}">
<div>
<h4 style="color: #6cc0c1;">
Get to work!
</h4>
</div>
<form action="${pageContext.request.contextPath}/student/home/proceed/offline-test/check" method="POST">
	<c:forEach var="questionModel" items="${STUDENT_DATA}">
		<hr class="greyRow">

		<div class="list-group" style="margin-bottom: 10px;">

			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">"${questionModel.getQuestionName()}"</h5>
			</div>

			<div class="form-check">
				<c:forEach var="answerModel" items="${questionModel.getAnswerList()}">
					<div class="row" style="margin-bottom: 10px;">

						<div class="col-4">
							<input class="form-check-input position-static" type="checkbox"
								id="blankCheckbox"  aria-label="..."name="Box${answerModel.getId()}"> ${answerModel.getAnswerName()}
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<hr class="greyRow">
	</c:forEach>
	<input type="hidden" name="idTest" value="${idTest}">
	<div>
	<button type="submit" class="btn btn-outline-success">Check the test</button>
	</div>
</form>
</c:if>






