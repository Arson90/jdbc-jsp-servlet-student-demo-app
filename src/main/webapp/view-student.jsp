<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Student List</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div>
            <h1 class="text-center">Student List</h1>
        </div>
        <br>
        <div class="position-relative">
			<input type="button" value="Add Student"
					onclick="window.location.href='add-student-form.jsp'; return false;"
					class="btn btn-dark btn-lg position-absolute top-0 start-50 translate-middle">

		</div>
		<br><br>
        <table class="table table-dark table-striped">
            <thead>
					<tr>
					    <th>Id_Student</th>
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
						<c:url var="loadLink" value="student-servlet">
							<c:param name="command" value="LOAD"></c:param>
							<c:param name="studentId" value="${student.idStudent}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="student-servlet">
							<c:param name="command" value="DELETE"></c:param>
							<c:param name="studentId" value="${student.idStudent}"></c:param>
						</c:url>

						<tr>
						    <td>${student.idStudent}</td>
							<td>${student.firstName}</td>
							<td>${student.lastName}</td>
							<td>${student.email}</td>
							<td>${student.studentBookNumber}</td>
							<td><a class="btn btn-outline-light btn-sm" href="${loadLink}" role="button">Update</a>
								|
								<a class="btn btn-outline-light btn-sm" href="${deleteLink}" role="button" onclick="if(!(confirm('Do you want to delete this Student ?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
        </table>
    </div>
</body>
</html>