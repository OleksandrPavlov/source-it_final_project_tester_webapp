<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${ADMIN_ERROR!=null}">
<div class="alert alert-danger alert-dismissible fade show" role="alert">
  <strong>${ADMIN_ERROR}</strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
</c:if>
<c:if test="${ADMIN_SUCCESS!=null}">
<div class="alert alert-success alert-dismissible fade show" role="alert">
  <strong>${ADMIN_SUCCESS}</strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
</c:if>

<div class="col-sm mar20">
	<h5 align="left" class="greentext">
		<strong>Adding account form</strong>
	</h5>
</div>


<div class="container-fluid">
	<div class="row">
		<div class="col" style="margin-bottom: 20px;">
			<form action="${pageContext.request.contextPath}/admin/add" method="POST">
				<div class="card">
					<div class="card-header">Please fill all fields</div>
					<div class="card-body">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroup-sizing-default">Name:</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="name">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroup-sizing-default">Surname:</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="surname">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroup-sizing-default">Middle-name:</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="middlename">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroup-sizing-default">Email:</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="email">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroup-sizing-default">Login:</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="login">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroup-sizing-default">Password:</span>
							</div>
							<input type="text" class="form-control" aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="password">
						</div>
						
						<div style="margin-bottom: 15px;">
							<h6>Active:</h6>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									name="active" id="exampleRadios1" value="true"
									> <label class="form-check-label"
									for="exampleRadios1"> True </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio"
									name="active" id="exampleRadios2" value="false" checked>
								<label class="form-check-label" for="exampleRadios2" >
									False </label>
							</div>
						</div>

						<div style="margin-bottom: 15px;">
							<h6>Role:</h6>

							<div class="form-check form-check-inline">
								<input class="form-check-input" name="administrator" type="checkbox"
									id="inlineCheckbox1" value="administrator" > <label
									class="form-check-label" for="inlineCheckbox1">Administrator</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input"  name="tutor" type="checkbox"
									id="inlineCheckbox2" value="tutor"> <label
									class="form-check-label" for="inlineCheckbox2">Tutor</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" name="advancedTutor" type="checkbox"
									id="inlineCheckbox3" value="advancedTutor" > <label
									class="form-check-label" for="inlineCheckbox3" >Advanced
									Tutor </label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" name="student" type="checkbox"
									id="inlineCheckbox3" value="student" > <label
									class="form-check-label" for="inlineCheckbox3" >Student
								</label>
							</div>
						</div>

						 <button type="submit" class="btn btn-primary">ADD ACCOUNT</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col"></div>
	</div>
</div>

