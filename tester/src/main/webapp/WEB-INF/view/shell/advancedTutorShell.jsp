<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Tester.EnterPage</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/Extra.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row textpad5" >
			<div class="col-sm ">
				<div>
					<a href="${pageContext.request.contextPath}/tutor/home"><img src="${pageContext.request.contextPath}/Tester.png" height="47" class="rounded " alt="Tester">
				</div>
			</div>
			<div class="col-sm textpadp5">

				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link disabled" href="#"><c:if
								test="${PAGE_HEADER!=null}">${PAGE_HEADER}</c:if></a></li>
					<li class="nav-item"><a class="nav-link enable" href="${pageContext.request.contextPath}/logout">Logout</a>
					</li>
				</ul>
			</div>
			<div class="col-sm textpadp5">
				<p align="right" class="mutable">Role: ${CURRENT_ROLE}</p>
				<p align="right" class="mutable">Hello ${CURRENT_ACCOUNT}</p>

			</div>
		</div>
		<div class="row">
			<div class="col-sm textpadp20">
				<div>
					<jsp:include page="${currentPage}" />
				</div>
			</div>
		</div>
		<div class="row grayf" style="margin-top: 300px;">
			<div class="col-5">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link disabled" href="#">Current</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
						role="button" aria-haspopup="true" aria-expanded="false">Choose
							Audience</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">Admins</a> <a
								class="dropdown-item" href="#">Students</a> <a
								class="dropdown-item" href="#">Tutors</a> <a
								class="dropdown-item" href="#">Advanced Tutors</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Show All</a>
						</div></li>
					<li class="nav-item"><a class="nav-link disabled" href="#">Logout</a>
					</li>
				</ul>
			</div>
			<div class="col-sm">
				<p align="right" class="mutable">Role: Administrator</p>
			</div>
		</div>
		<div class="row graylf">
			<div class="col-sm"></div>
			<div class="col-sm"></div>
			<div class="col-sm"></div>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="/static/js/popper.js"></script>
	<script src="/static/js/jquery-3.3.1.js"></script>
	<script src="/static/js/bootstrap.js"></script>
	<script src="/static/js/app.js"></script>
</body>
</html>