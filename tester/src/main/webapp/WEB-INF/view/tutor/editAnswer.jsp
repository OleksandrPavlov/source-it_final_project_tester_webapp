<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="card">
	<div class="card-header">Answer Editor</div>
	<div class="card-body">
		<form action="${pageContext.request.contextPath}/tutor/home/test/answer-edit" method="POST">
			<div class="form-group">
				<label for="formGroupExampleInput">Correct:</label>


				<div class="row">
					<div class="col-1">
						<div align="center">
							<input class="form-check-input" type="checkbox" id="gridCheck"
								name="right" <c:if test="${right==true}">checked</c:if>>
						</div>
					</div>
					<div class="col-11">

						<input type="text" class="form-control" id="formGroupExampleInput"
							value='${answerName}' name="answerName">
					</div>
				</div>


				<input type="hidden" name="idTest" value="${idTest}"> <input
					type="hidden" name="id" value="${id}"> <input type="hidden"
					name="testName" value="${testName}">
			</div>
			<button type="submit" class="btn btn-success">save</button>
		</form>
		<!--  <p>answerID=${id}</p>
		<p>idTest=${idTest}</p>
		<p>right=${right}</p>
		<p>testName${testName}</p>
		<p>idTEst=${idTest}</p>
-->

	</div>
</div>