<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="card">
	<div class="card-header">Add answer form</div>
	<div class="card-body">
		<form action="/tutor/home/test/add-answer" method="POST">
			<div class="form-group">
				<label for="formGroupExampleInput">Correct:</label>


				<div class="row">
					<div class="col-1">
						<div align="center">
							<input class="form-check-input" type="checkbox" id="gridCheck"
								name="right" >
						</div>
					</div>
					<div class="col-11">

						<input type="text" class="form-control" id="formGroupExampleInput"
						 name="answerName">
					</div>
				</div>


				<input type="hidden" name="idTest" value="${idTest}"> <input
					type="hidden" name="id" value="${questionId}"> <input type="hidden"
					name="testName" value="${testName}">


			</div>
			<button type="submit" class="btn btn-success">save</button>
		</form>
		
	</div>
</div>