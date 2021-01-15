<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="card">
	<div class="card-header">Add question form</div>
	<div class="card-body">
		<form action="${pageContext.request.contextPath}/tutor/home/test/add-question" method="POST">
			<div class="form-group">


				<div class="row" style="margin-bottom: 20px">
					<div class="col-2">
						<h6>Question:</h6>
					</div>
					<div class="col-10">
						<input type="text" class="form-control" id="formGroupExampleInput"
							name="questionName">
					</div>
				</div>

				<div>
					<h6>Answers:</h6>
				</div>





				<c:forEach var="counter" begin="1" end="${answerCounter}" step="1">
					<div class="row" style="margin-bottom: 20px;">
						<div class="col-1">
							<div align="center">
								<input class="form-check-input" type="checkbox" id="gridCheck"
									name="right${counter}">
							</div>
						</div>
						<div class="col-11">

							<input type="text" class="form-control"
								id="formGroupExampleInput" name="answerName${counter}">
						</div>
					</div>

				</c:forEach>





			</div>


			<div class="row">
				<div class="col-2">
					<a
						href="${pageContext.request.contextPath}/tutor/home/test/add-question?answerCounter=${answerCounter}&idTest=${idTest}&testName=${testName}">
						<button type="button" class="btn btn-warning">add answer</button>
					</a>
				</div>
				<div class="col-2">
					<div align="left">
						<a
							href="${pageContext.request.contextPath}/tutor/home/test/add-question?answerCounter=${answerCounter-2}&idTest=${idTest}&testName=${testName}">
							<button type="button" class="btn btn-warning">remove answer</button>
						</a>
					</div>
				</div>
				<div class="col-8">
					<div align="right">
						<button type="submit" class="btn btn-success">save</button>
					</div>
				</div>
			</div>

			<input type="hidden" name="idTest" value="${idTest}"> <input
				type="hidden" name="testName" value="${testName}"> <input
				type="hidden" name="answerCounter" value="${answerCounter}">
		</form>

	</div>
</div>