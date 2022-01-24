<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	    <div class="text-center">
    		<h1>Student List</h1>
    	</div>
    	<div>
    	    <h2>Update Student</h2>
    	</div>
		<form action="student-servlet" method="get">
			<input type="hidden" name="command" value="UPDATE">
			<input type="hidden" name="studentId" value="${student.idStudent}">
			<table class="table table-dark table-striped">
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
		    <a class="btn btn-dark btn-lg position-absolute top-50 start-50 translate-middle"
        		href="student-servlet"
        		role="button">Back To Student List
        	</a>
	</div>
</body>
</html>