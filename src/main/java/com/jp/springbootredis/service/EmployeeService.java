package com.jp.springbootredis.service;

import java.util.List;

import com.jp.springbootredis.entity.Employee;
import com.jp.springbootredis.exception.EmployeeNotFoundException;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public Employee updateEmployee(Employee employee, Integer employeeId) throws EmployeeNotFoundException;

	public Employee getEmployee(Integer employeeId) throws EmployeeNotFoundException;

	public List<Employee> getAllEmployee();

	public void deleteEmployee(Integer employeeId) throws EmployeeNotFoundException;
}
