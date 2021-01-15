<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${FILTER_SELECTED_ROLE_ERROR!=null}">
	<div class="alert alert-danger fade show" role="alert">
		<strong>${FILTER_SELECTED_ROLE_ERROR}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<div class="container main">
	<div class="row ">
		<div class="col-3"></div>
		<div class="col-12">
			<form name="loginform" method="POST" action="/tester/login">
				<div class="card">
					<h5 class="card-header">Login Form</h5>
					<div class="card-body logincard">
						<div>
							<p>Login</p>
							<input type="text" name="login" class="form-control mb-2 mr-sm-2"
								id="inlineFormInputName2" placeholder="Login:">
						</div>
						<div>
							<p>Password</p>
							<input type="password" id="inputPassword5" class="form-control"
								placeholder="Password:" name="password"
								aria-describedby="passwordHelpBlock"> <small
								id="passwordHelpBlock" class="form-text text-muted">
								Your password must be 8-20 characters long </small>
						</div>

						<div class="col-auto my-1">
							<label class="mr-sm-2" for="inlineFormCustomSelect">Role</label>
							<select class="custom-select mr-sm-2" id="inlineFormCustomSelect"
								name="role">
								<option selected value="student">Student</option>
								<option value="administrator">Admin</option>
								<option value="tutor">Tutor</option>
								<option value="advanced tutor">Advanced Tutor</option>
							</select>
						</div>

						<div class="form-check mb-2 mr-sm-2">
							<input class="form-check-input" type="checkbox"
								id="inlineFormCheck" name="remember"> <label
								class="form-check-label" for="inlineFormCheck"> Remember
								me </label>
						</div>

						<div align="center">
							<input type="submit" class="btn btn-primary" value="Send">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="col"></div>
</div>

