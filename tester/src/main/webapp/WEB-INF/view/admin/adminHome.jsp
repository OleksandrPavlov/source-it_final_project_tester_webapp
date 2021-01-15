<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<div>
	<a href="${pageContext.request.contextPath}/admin/add" type="button" class="btn btn-info">Add</a>
</div>

<c:if test="${ADMIN_SUCCESS!=null }">
	<div class="alert alert-success fade show" role="alert">
		<strong>${ADMIN_SUCCESS}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>

</c:if>

<c:if test="${ADMIN_ERROR!=null}">
	<div class="alert alert-warning fade show" role="alert">
		<strong>${ADMIN_ERROR}</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>


<c:if test="${ADMIN_DATA!=null}">
	<table class="table table-striped textpadp20 ">
		<thead>
			<tr>
				<th scope="col">id</th>
				<th scope="col">Name</th>
				<th scope="col">Surname</th>
				<th scope="col">Mid.Name</th>
				<th scope="col">Password</th>
				<th scope="col">Login</th>
				<th scope="col">Email</th>
				<th scope="col">Created</th>
				<th scope="col">Activity</th>
				<th scope="col">Roles</th>
				<th scope="col">Options</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="AccountModel" items="${ADMIN_DATA}">
				<tr>
					<th scope="row"><c:out value="${AccountModel.getId()}"></c:out></th>
					<td><c:out value="${AccountModel.getFirstName()}"></c:out></td>
					<td><c:out value="${AccountModel.getLastName()}"></c:out></td>
					<td><c:out value="${AccountModel.getMiddleName()}"></c:out></td>
					<td><c:out value="${AccountModel.getPassword()}"></c:out></td>
					<td><c:out value="${AccountModel.getLogin()}"></c:out></td>
					<td><c:out value="${AccountModel.getEmail()}"></c:out></td>
					<td><c:out value="${AccountModel.getCreated()}"></c:out></td>
					<td><c:out value="${AccountModel.getActive()}"></c:out></td>
					<td><c:out value="${AccountModel.getRole()}">
						</c:out></td>
					<td><a type="button"
						href="${pageContext.request.contextPath}/admin/enable?id=${AccountModel.getId()}&currentActivity=${AccountModel.getActive()}"
						class="btn btn-outline-dark"><c:if
								test="${AccountModel.getActive()==true}">disable</c:if> <c:if
								test="${AccountModel.getActive()==false}">enable</c:if></a> <a
						href="${pageContext.request.contextPath}/admin/edit?id=${AccountModel.getId()}" type="button"
						class="btn btn-outline-warning">edit</a> <a
						href="${pageContext.request.contextPath}/admin/delete?id=${AccountModel.getId()}" type="button"
						class="btn btn-outline-danger">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<c:if test="${QUANTITY_PAGE>0}">
	<nav aria-label="...">
		<ul class="pagination">
			<li class="page-item "><a class="page-link" href="${pageContext.request.contextPath}/admin/home?koef=${PREVIOUSE}"
				tabindex="-1" aria-disabled="true">Previous</a></li>
			<c:forEach var="i" begin="1" end="${QUANTITY_PAGE}">
			<li class="page-item  <c:if test="${CURRENT_PAGE==i}">active</c:if>" aria-current="page"><a
				class="page-link" href="${pageContext.request.contextPath}/admin/home?koef=${i}"> ${i} </a>
			</li>
			</c:forEach>
			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/admin/home?koef=${NEXT}">Next</a></li>
		</ul>
	</nav>
</c:if>
</c:if>
	