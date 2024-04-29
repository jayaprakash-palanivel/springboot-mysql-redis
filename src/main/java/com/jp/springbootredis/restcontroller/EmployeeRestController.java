package com.jp.springbootredis.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.springbootredis.entity.Employee;
import com.jp.springbootredis.exception.EmployeeNotFoundException;
import com.jp.springbootredis.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping("/allEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return ResponseEntity.ok(employeeService.getAllEmployee());
	}

	@GetMapping("/getEmployee/{id}")
	public Employee getEmployee(@PathVariable Integer id) throws EmployeeNotFoundException {
		return employeeService.getEmployee(id);
	}

	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
		try {
			employee = employeeService.updateEmployee(employee, id);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		try {
			employeeService.deleteEmployee(id);
			
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		return "Employee with id: " + id + " Deleted !";
	}

}
