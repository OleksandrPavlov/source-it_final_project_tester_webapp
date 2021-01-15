<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="alert alert-success" role="alert">
	<h4 class="alert-heading">Proceed to the test</h4>
	<p>
		<strong>Name: </strong>${STUDENT_DATA.getTestModel().getName() }
	</p>
	<p>
		<strong>Description:</strong>
		${STUDENT_DATA.getTestModel().getDescription()}
	</p>
	<p>
		<strong>Duration: </strong>${STUDENT_DATA.getTestModel().getDuration()}
		seconds
	</p>
	<p>
		<strong>Quantity of questions: </strong>${STUDENT_DATA.getQuantityQuestion()}
	</p>
	<p>
		<strong>Author: </strong>${STUDENT_DATA.getAuthor()}
	</p>
	<hr>
	<form action="${pageContext.request.contextPath}/student/home/proceed/mode-distributor" method="GET">
	<p class="mb-0">Choose mode of testing:</p>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="mode"
			id="exampleRadios1" value="offline"checked> <label
			class="form-check-label" for="exampleRadios1" > Offline </label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="mode"
			id="exampleRadios2" value="online"> <label
			class="form-check-label" for="exampleRadios2"> Online </label>
	</div>
	<hr>
	<p>Are you want to pass the test?</p>
	<div class="row">
		<div class="col-2">
			<input type="submit" class="btn btn-info" value="YES">
		</div>
		<input type="hidden" name="idTest" value="${idTest}">
		</form>
		<div class="col-2">
			<button type="button" class="btn btn-secondary">NO</button>
		</div>
	</div>
</div>