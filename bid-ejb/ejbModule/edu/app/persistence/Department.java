package edu.app.persistence;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Department
 *
 */
@Entity
@Table(name="t_department")

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	private List<Employee> employees;
	
	public Department() {
	}
	
	
	
	public Department(String name) {
		this.name = name;
	}

	


	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="department")
	public List<Employee> getEmployees() {
		if(employees == null)
			employees = new ArrayList<Employee>();
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
   
}
