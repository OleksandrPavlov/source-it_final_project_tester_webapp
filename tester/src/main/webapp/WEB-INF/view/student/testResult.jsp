<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="alert alert-primary" role="alert">
	<h4 class="alert-heading">Result:</h4>
	<p>
		Dear: <strong>${UserName}</strong>
	</p>
	<p>
		You have just passed test:
		<strong>${testName}</strong>
	</p>
	<p>
		
Total number of questions: <strong>${quantityQuestion}</strong> 
	</p>
	<p>Quantity of right answers:
		<strong>${quantityR}</strong>
	</p>
	<p>
		Test on <strong>${percent }</strong> complited!
	</p>
	<hr>
	<p>Are you happy with this test?</p>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="mode"
			id="exampleRadios1" value="offline"checked> <label
			class="form-check-label" for="exampleRadios1" > yes </label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="mode"
			id="exampleRadios2" value="online"> <label
			class="form-check-label" for="exampleRadios2"> no </label>
	</div>
	
	</div>
</div>

<c:if test="${rightTest!=null}">
<h5>Right answers:</h5>
<c:forEach var="rightTest" items="${rightTest}">
<div class="alert alert-success" role="alert">
	
		<div class="list-group" style="margin-bottom: 10px;">

			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">${rightTest.getQuestionName()}</h5>
			</div>

			<div class="form-check">
				<c:forEach var="answerModel" items="${rightTest.getAnswerList()}">
					<div class="row" style="margin-bottom: 10px;">

						<div class="col-4">
							<input class="form-check-input position-static" type="checkbox"
								id="blankCheckbox"  aria-label="..." <c:if test="${ answerModel.getRight()}">checked</c:if>>${answerModel.getAnswerName()}
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	
	</div>

</c:forEach>
</c:if>
<c:if test="${badTest!=null}">
<h5>Wrong answers:</h5>
<c:forEach var="badTest" items="${badTest}">
<div class="alert alert-danger" role="alert">
	
		<div class="list-group" style="margin-bottom: 10px;">

			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">${badTest.getQuestionName()}</h5>
			</div>

			<div class="form-check">
				<c:forEach var="answerModel" items="${badTest.getAnswerList()}">
					<div class="row" style="margin-bottom: 10px;">

						<div class="col-4">
							<input class="form-check-input position-static" type="checkbox"
								id="blankCheckbox"  aria-label="..." <c:if test="${ answerModel.getRight()}">checked</c:if>>${answerModel.getAnswerName()}
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	
	</div>

</c:forEach>
</c:if>


