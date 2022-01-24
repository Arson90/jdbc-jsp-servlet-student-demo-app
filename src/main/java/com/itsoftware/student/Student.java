package com.itsoftware.student;

public class Student {
	private int idStudent;
	private String firstName;
	private String lastName;
	private String email;
	private int studentBookNumber;

	public Student () {
	}

	public Student(String firstName, String lastName, String email, int studentBookNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.studentBookNumber = studentBookNumber;
	}
	
	public Student(int idStudent, String firstName, String lastName, String email, int studentBookNumber) {
		this.idStudent = idStudent;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.studentBookNumber = studentBookNumber;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setId_student(int idStudent) {
		this.idStudent = idStudent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStudentBookNumber() {
		return studentBookNumber;
	}

	public void setStudentBookNumber(int studentBookNumber) {
		this.studentBookNumber = studentBookNumber;
	}
}
