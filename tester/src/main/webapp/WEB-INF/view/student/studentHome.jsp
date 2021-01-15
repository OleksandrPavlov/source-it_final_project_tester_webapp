<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*"  %>

<div>
<h5> Test List</h5>
</div>

<c:if test="${STUDENT_SUCCESS!=null }">
	<div class="alert alert-success fade show" role="alert">
		<strong>${STUDENT_SUCCESS}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>
<c:if test="${STUDENT_ERROR!=null}">
	<div class="alert alert-warning fade show" role="alert">
		<strong>${STUDENT_ERROR}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>


<c:if test="${STUDENT_DATA!=null}">
<div class="list-group">
<c:forEach var="TEST" items="${STUDENT_DATA}">
 <a href="${pageContext.request.contextPath}/student/home/proceed?idTest=${TEST.getId() }" class="list-group-item list-group-item-action ">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">${TEST.getName()}</h5>
      <small>time on question: <strong>${TEST.getDuration()} seconds</strong></small>
    </div>
    <p class="mb-1">${TEST.getDescription()}</p>
  </a>
</c:forEach>
 
</div>



</c:if>
