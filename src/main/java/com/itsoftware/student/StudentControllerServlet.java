package com.itsoftware.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student-servlet")
public class StudentControllerServlet extends HttpServlet {
    private StudentDataUtil studentDataUtil;

	@Override
	public void init(){
		this.studentDataUtil = new StudentDataUtil();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			String commandFromJSP = request.getParameter("command");

			if(commandFromJSP  == null) {
				commandFromJSP  = "LIST";
			}
			
			switch (commandFromJSP) {
				case "ADD":
					addStudent(request, response);
					break;
				case "LOAD":
					loadStudent(request, response);
					break;
				case "UPDATE":
					updateStudent(request, response);
					break;
				case "DELETE":
					deleteStudent(request, response);
					break;
				default:
					listStudents(request, response);
					break;
			}
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int studentBookNumber = Integer.parseInt(request.getParameter("studentBookNumber"));

		Student tempStudent = new Student(firstName, lastName, email, studentBookNumber);
		studentDataUtil.insertStudent(tempStudent);
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		Student student = studentDataUtil.loadStudent(studentId);
		request.setAttribute("student", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		List<Student> student = studentDataUtil.getStudentList();
		request.setAttribute("students", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view-student.jsp");
		dispatcher.forward(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int studentBookNumber = Integer.parseInt(request.getParameter("studentBookNumber"));

		Student tempStudent = new Student(studentId, firstName, lastName, email, studentBookNumber);
		studentDataUtil.updateStudentById(tempStudent);
		listStudents(request, response);
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		studentDataUtil.deleteStudentById(studentId);
		listStudents(request, response);
	}
}
