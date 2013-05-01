package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Employee extends User implements Serializable{

	private static final long serialVersionUID = 2266212277802018650L;
	
	private Date hireDate;
	private Double salary;
	
	private Department department;
	
	public Employee() {
	}
	
	


	public Employee(String login, String password, String email, Date hireDate, Double salary) {
		super(login, password, email);
		this.hireDate = hireDate;
		this.salary = salary;
	}
	
	public Employee(String login, String password, String email) {
		super(login, password, email);
	}




	@ManyToOne
	@JoinColumn( name = "department_fk")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date")
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
