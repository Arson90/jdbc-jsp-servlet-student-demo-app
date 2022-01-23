<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Student List</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student List</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Add Student" 
					onclick="window.location.href='add-student-form.jsp'; return false;"
					class="add-student-button">
			<table border="1">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Student Book Number</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${students}">
						
						<!-- set up a link for each student -->
						<c:url var="loadLink" value="student-controller-servlet">
							<c:param name="command" value="LOAD"></c:param>
							<c:param name="studentId" value="${student.idStudent}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="student-controller-servlet">
							<c:param name="command" value="DELETE"></c:param>
							<c:param name="studentId" value="${student.idStudent}"></c:param>
						</c:url>
						
						<tr>
							<td>${student.firstName}</td>
							<td>${student.lastName}</td>
							<td>${student.email}</td>
							<td>${student.studentBookNumber}</td>
							<td><a href="${loadLink}">Update</a> 
								| 
								<a href="${deleteLink}" onclick="if(!(confirm('Do you want to delete this Student ?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>