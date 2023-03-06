package com.jaydeep.ems.dto;


public class EmployeeDTO {
	private String firstName;
	private String lastName;
	private String dept;
	private double salary;
	private String email;
	
	public EmployeeDTO(String firstName, String lastName, String dept, double salary, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dept = dept;
		this.salary = salary;
		this.email = email;
	}

	public EmployeeDTO() {
		super();
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeDTO ["+ "firstName=" + firstName + ", lastName=" + lastName + ", dept=" + dept
				+ ", salary=" + salary + ", email=" + email +"]";
	}
	
	
	
}
