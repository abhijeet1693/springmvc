package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.EmployeeDao;
import com.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao dao;
	
	@RequestMapping("/add")
	public String showForm(Model model) {
		model.addAttribute("emp",new Employee());
		return "AddEmployee";
	}
	
	
	@RequestMapping("/submit")
	public String submitForm(@Valid @ModelAttribute("emp") Employee emp,BindingResult br) {
		System.out.println("Error : "+br.getErrorCount());
		if(br.hasErrors()) {
			return "AddEmployee";
		}
		else {
			int updated = dao.insertRecord(emp);
			System.out.println("REcord added : "+updated);
			return "redirect:/view";
		}
	}
	
	@RequestMapping("/view")
	public String showEmployee(Model model){
		List<Employee> empList = dao.getAllEmployees();
		model.addAttribute("empList",empList);
		return "ViewEmployee";
	}
	
	@RequestMapping("/editform/{id}")
	public String showEditForm(@PathVariable("id") int id,Model model) {
		Employee emp = dao.getEmployeeById(id);
		model.addAttribute("emp",emp);
		return "EditEmployee";
	}
	
	
	@RequestMapping("/update")
	public String updateRecord(@ModelAttribute("emp") Employee emp) {
		int update = dao.updateRecord(emp);
		System.out.println("Updated : "+update);
		return "redirect:/view";
	}
	
	
	@RequestMapping("/deleteform/{id}")
	public String deleteRecord(@PathVariable("id") int id) {
		int update = dao.deleteRecord(id);
		System.out.println("Updated : "+update);
		return "redirect:/view";
	}
	
}
