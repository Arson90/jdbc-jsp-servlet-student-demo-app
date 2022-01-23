package com.itsoftware.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDataUtil {
	private String dbURL = "jdbc:mysql://localhost:3306/web_student?useSSL=false";
	private String dbUserName = "webstudent";
	private String dbPasswd = "webstudent";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";

	protected Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(dbURL, dbUserName, dbPasswd);
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return connection;
	}

	public List<Student> getStudentList() throws ClassNotFoundException, SQLException {
		String selectQuerySQL = "SELECT * FROM student";
		List<Student> students = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectQuerySQL);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				int idStudent = resultSet.getInt("id_student");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				int studentBookNumber = resultSet.getInt("student_book_number");
				Student tempStudent = new Student(idStudent, firstName, lastName, email, studentBookNumber);
				students.add(tempStudent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return students;
	}
	
	public void insertStudent(Student student) throws  ClassNotFoundException, SQLException {
		String insertQuerySQL = "INSERT INTO student (first_name, last_name, email, student_book_number) " +
				"VALUES (?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertQuerySQL);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getEmail());
			preparedStatement.setInt(4, student.getStudentBookNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		} 
	}
	
	public Student loadStudent(int studentId) throws ClassNotFoundException, SQLException {
		String selectByIdQuerySQL = "SELECT * FROM student WHERE id_student = ?;";
		Student tempStudent = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectByIdQuerySQL);
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				int studentBookNumber = resultSet.getInt("student_book_number");
				
				tempStudent = new Student(studentId,firstName, lastName, email, studentBookNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return tempStudent;
	}
	
	public void updateStudentById(Student student) throws ClassNotFoundException, SQLException {
		String updateStudentByIdQuerySQL = "UPDATE student SET first_name = ?, last_name = ?, email = ?, student_book_number = ? WHERE id_student = ?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(updateStudentByIdQuerySQL);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getEmail());
			preparedStatement.setInt(4, student.getStudentBookNumber());
			preparedStatement.setInt(5, student.getIdStudent());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
	}
	
	public void deleteStudentById(int studentId) throws SQLException, ClassNotFoundException {
		String deleteStudentByIdQuerySQL = "DELETE FROM student WHERE id_student = ?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteStudentByIdQuerySQL);
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
	}
	
	private static void closeResultSet(ResultSet resultSet) throws ClassNotFoundException, SQLException{
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                Logger.getLogger(StudentDataUtil.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }

    private static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException{
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlException) {
                Logger.getLogger(StudentDataUtil.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }

    private  static void closeConnection(Connection connection) throws ClassNotFoundException, SQLException{
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                Logger.getLogger(StudentDataUtil.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }
}
