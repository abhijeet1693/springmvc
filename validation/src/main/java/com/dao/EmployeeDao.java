package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.model.Employee;

public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	public int insertRecord(Employee emp) {
		return jdbc.update("insert into employee(name,salary,desig) values('"+emp.getName()+"','"+emp.getSalary()+"','"+emp.getDesign()+"') ");
	}
	
	public List<Employee> getAllEmployees(){
		return  jdbc.query("select * from employee", new ResultSetExtractor<List<Employee>>() {

			public List<Employee> extractData(ResultSet arg0) throws SQLException, DataAccessException {
				List<Employee> empList = new ArrayList<Employee>();
				while(arg0.next()){
					Employee emp = new Employee();
					emp.setId(arg0.getInt(1));
					emp.setName(arg0.getString(2));
					emp.setSalary(arg0.getInt(3));
					emp.setDesign(arg0.getString(4));
					empList.add(emp);
				}
				return empList;
			}
			
		});
	}

	public Employee getEmployeeById(int id) {
		return jdbc.query("select * from employee where id= '"+id+"' ", new ResultSetExtractor<Employee>() {
			public Employee extractData(ResultSet arg0) throws SQLException, DataAccessException {
				Employee emp = new Employee();
				while(arg0.next()) {
					emp.setId(arg0.getInt(1));
					emp.setName(arg0.getString(2));
					emp.setSalary(arg0.getInt(3));
					emp.setDesign(arg0.getString(4));
				}
				return emp;
			}
		});
	}
	
	public int updateRecord(Employee emp) {
		System.out.println(emp.getId()+"--"+emp.getName()+"===="+emp.getSalary());
		return jdbc.update("update employee set name='"+emp.getName()+"',salary='"+emp.getSalary()+"',"
				+ "desig='"+emp.getDesign()+"' where id = '"+emp.getId()+"' ");
	}
	
	public int deleteRecord(int id) {
		return jdbc.update("delete from employee where id = '"+id+"' ");
	}
}
