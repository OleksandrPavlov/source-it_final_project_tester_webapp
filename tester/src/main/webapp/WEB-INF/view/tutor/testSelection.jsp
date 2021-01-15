<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div align="center" style="color: #6183bf;">
	<p>
		<strong> ${testName}</strong>
	</p>
</div>

<c:if test="${TUTOR_ERROR!=null }">
	<div class="alert alert-danger" role="alert">
		<h4 class="alert-heading">${TUTOR_ERROR}</h4>
	</div>
</c:if>

<c:if test="${TUTOR_SUCCESS!=null }">
	<div class="alert alert-success" role="alert">
		<h4 class="alert-heading">${TUTOR_SUCCESS}</h4>
	</div>
</c:if>


<c:if test="${TUTOR_DATA!=null}">

	<hr class="greyRow">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm">
				<div align="center">
					<a href="${pageContext.request.contextPath}/tutor/home/test/add-question?idTest=${TUTOR_DATA.get(0).getTestId()}&testName=${testName}"><button type="button" class="btn btn-primary">Add
							question</button></a>
				</div>
			</div>
			<div class="col-sm">
				<div align="center">
					<a href="${pageContext.request.contextPath}/tutor/home/test/question-edit/change-duration?idTest=${TUTOR_DATA.get(0).getTestId()}"><button type="button" class="btn btn-info">Edit
							duration</button></a>
				</div>
			</div>
			<div class="col-sm">
				<div align="center">
					<a href="${pageContext.request.contextPath}/unavailableFeature"><button type="button" class="btn btn-danger">Delete
							Test</button></a>
				</div>
			</div>
			<div class="col-sm">
				<div align="center">
					<a href="${pageContext.request.contextPath}/unavailableFeature"><button type="button" class="btn btn-info">Rename
							Test</button></a>
				</div>
			</div>
		</div>
	</div>
	<hr class="greyRow">

	<c:forEach var="questionModel" items="${TUTOR_DATA}">
		<hr class="greyRow">

		<div class="list-group" style="margin-bottom: 10px;"">

			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">${questionModel.getQuestionName()}</h5>
				<a
					href="${pageContext.request.contextPath}/tutor/home/test/question-edit?id=${questionModel.getTestId()}&questionId=${questionModel.getId()}&testName=${testName}"><button
						type="button" class="btn btn-warning">edit question</button></a>
			</div>
			<div class="form-check">
				<c:forEach var="answerList" items="${questionModel.getAnswerList()}">
					<div class="row" style="margin-bottom: 10px;">

						<div class="col-4">
							<input class="form-check-input position-static" type="checkbox"
								id="blankCheckbox" value="option1" aria-label="..."
								<c:if test="${answerList.getRight()==true}"> checked </c:if>
								disabled> ${ answerList.getAnswerName()}
						</div>
						<div class="col-2">
							<a
								href="${pageContext.request.contextPath}/tutor/home/test/answer-edit?id=${answerList.getId()}&idTest=${questionModel.getTestId()}&right=${answerList.getRight()}&answerName=${answerList.getAnswerName()}&testName=${testName}"><button
									type="button" class="btn btn-warning">edit answer</button></a>
						</div>
						<div class="col-2">
						<form action="${pageContext.request.contextPath}/tutor/home/test/question-edit/delete-answer" method="POST">
							
								<input type="hidden" name="id" value="${answerList.getId()}">
								<input type="hidden" name="idTest" value="${questionModel.getTestId()}">
								<input type="hidden" name="testName" value="${testName}">
								<input type="hidden" name="lost" value="${questionModel.getAnswerList().size() }">
								
		
									<button type="submit" class="btn btn-danger">delete</button>
									</form>
						</div>
					</div>

				</c:forEach>
			</div>
			<a
				href="${pageContext.request.contextPath}/tutor/home/test/add-answer?questionId=${questionModel.getId()}&idTest=${questionModel.getTestId()}&testName=${testName}"><button
					type="button" class="btn btn-info">add answer</button></a>
		</div>

		<hr class="greyRow">
	</c:forEach>

</c:if>
