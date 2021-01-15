<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*"  %>
<div>
	<a href="${pageContext.request.contextPath}/tutor/home/test-add" type="button" class="btn btn-info">Add test</a>
</div>

<c:if test="${TUTOR_SUCCESS!=null }">
	<div class="alert alert-success fade show" role="alert">
		<strong>${ADMIN_SUCCESS}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>
<c:if test="${TUTOR_ERROR!=null}">
	<div class="alert alert-warning fade show" role="alert">
		<strong>${TUTOR_ERROR}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>




<c:if test="${TUTOR_DATA!=null}">
<div class="list-group">
<c:forEach var="TEST" items="${TUTOR_DATA}">
 <a href="${pageContext.request.contextPath}/tutor/home/test?idTest=${TEST.getId() }&testName=${TEST.getName()}" class="list-group-item list-group-item-action ">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">${TEST.getName()}</h5>
      <small>time on question: <strong>${TEST.getDuration()} seconds</strong></small>
    </div>
    <p class="mb-1">${TEST.getDescription()}</p>
  </a>
</c:forEach>
 
</div>

<c:if test="${QUANTITY_PAGE>0}">
	<nav aria-label="...">
		<ul class="pagination">
			<li class="page-item "><a class="page-link" href="${pageContext.request.contextPath}/tutor/home?koef=${PREVIOUSE}"
				tabindex="-1" aria-disabled="true">Previous</a></li>
				
			<c:forEach var="i" begin="1" end="${QUANTITY_PAGE}">
			<li class="page-item  <c:if test="${CURRENT_PAGE==i}">active</c:if>" aria-current="page"><a
				class="page-link" href="${pageContext.request.contextPath}/tutor/home?koef=${i}"> ${i} </a>
			</li>
			</c:forEach>
			
			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/tutor/home?koef=${NEXT}">Next</a></li>
		</ul>
	</nav>
</c:if>

</c:if>

 































