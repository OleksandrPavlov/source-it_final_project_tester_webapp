<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="card">
	<div class="card-header">Change duration</div>
	<div class="card-body">
		<form action="${pageContext.request.contextPath}/tutor/home/test/question-edit/change-duration" method="POST">
				<input type="text" class="form-control" id="formGroupExampleInput"
							value='${currentDuration}' name="currentDuration">
							<input type="hidden" name="idTest" value="${idTest}">
			<button type="submit" class="btn btn-success">save</button>
		</form>
		
	</div>
</div>