<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css"/>
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/Extra.css"/>
    <title>Login</title>
  </head>
  <body >
<header class="container-fluid head">
         <div >
		    <div class="logo">
			<a href="${pageContext.request.contextPath}/login"><img src="${pageContext.request.contextPath}/Tester.png" height="47" class="rounded " alt="Tester"></a>
			</div>
		 </div>
</header>

    <jsp:include page="${currentPage}"/>

<footer class="footer">
	<p>Footer</p>
	</footer>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="/static/js/jquery-3.3.1.js"></script>
    <script src="/static/js/popper.js" ></script>
    <script src="/static/js/bootstrap.js"></script>
  </body>
</html>