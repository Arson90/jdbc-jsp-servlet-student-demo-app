<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student List</h2>
		</div>
	</div>
	<div id="container">
		<h3>Update Student</h3>
		<form action="student-controller-servlet" method="get">
			<input type="hidden" name="command" value="UPDATE">
			<input type="hidden" name="studentId" value="${student.idStudent}">
			<table>
				<tbody>
					<tr>
						<td><label>First Name</label></td>
						<td><input type="text" name="firstName" value="${student.firstName}"/></td>
					</tr>
						
					<tr>
						<td><label>Last Name</label></td>
						<td><input type="text" name="lastName" value="${student.lastName}"/></td>
					</tr>
					
					<tr>
						<td><label>Email</label></td>
						<td><input type="text" name="email" value="${student.email}"/></td>
					</tr>
					
					<tr>
						<td><label>Student Book Number</label></td>
						<td><input type="text" name="studentBookNumber" value="${student.studentBookNumber}"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input class="save" type="submit" value="Save"/></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p><a href="student-controller-servlet">Back_To_List</a></p>
	</div>
</body>
</html>