package com.itsoftware.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private StudentDataUtil studentDataUtil;
    
    @Resource(name="jdbc/web_student")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			this.studentDataUtil = new StudentDataUtil(this.dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			String commandFromAddStudentForm = request.getParameter("command");
			
			if(commandFromAddStudentForm == null) {
				commandFromAddStudentForm = "LIST";
			}
			
			switch (commandFromAddStudentForm) {
				case "ADD":
					addStudent(request, response);
					break;
				case "LIST":
					listStudents(request, response);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		int studentId = Integer.valueOf(request.getParameter("studentId"));
		studentDataUtil.deleteStudentById(studentId);
		
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		int studentId = Integer.valueOf(request.getParameter("studentId"));
		Student student = studentDataUtil.loadStudent(studentId);
		request.setAttribute("student", student);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		int studentId = Integer.valueOf(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int studentBookNumber = Integer.valueOf(request.getParameter("studentBookNumber"));
		
		Student tempStudent = new Student(studentId, firstName, lastName, email, studentBookNumber);
		studentDataUtil.updateStudentById(tempStudent);
		
		listStudents(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int studentBookNumber = Integer.valueOf(request.getParameter("studentBookNumber"));
		
		Student tempStudent = new Student(firstName, lastName, email, studentBookNumber);
		
		studentDataUtil.insertStudent(tempStudent);
		
		listStudents(request, response);
	}
	
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		List<Student> student = studentDataUtil.getStudentList();
		
		request.setAttribute("students", student);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view-student.jsp");
		dispatcher.forward(request, response);
	}
}
