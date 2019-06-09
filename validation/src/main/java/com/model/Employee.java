package com.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Employee {
	
	@Size(min=1,message="required")
	private String name;
	
	@Min(value=1000,message="salary must be greater than 1000")
	private int salary;
	
	private String design;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
}
