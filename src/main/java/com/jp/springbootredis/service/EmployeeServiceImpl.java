package com.jp.springbootredis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jp.springbootredis.entity.Employee;
import com.jp.springbootredis.exception.EmployeeNotFoundException;
import com.jp.springbootredis.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	@CachePut(value = "Employee", key = "#employeeId")
	public Employee updateEmployee(Employee employee, Integer employeeId) throws EmployeeNotFoundException {
		Employee emp = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setEmailAddress(employee.getEmailAddress());
		emp.setMobileNumber(employee.getMobileNumber());
		return employeeRepository.save(emp);
	}

	@Override
	@Cacheable(value = "Employee", key = "#employeeId")
	public Employee getEmployee(Integer employeeId) throws EmployeeNotFoundException {
		log.info("Get Employee Data from Database...");

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		return employee;
	}

	@Override
	@Cacheable(value = "Employee")
	public List<Employee> getAllEmployee() {
		log.info("Get All Employee Data from Database...");
		return employeeRepository.findAll();
	}

	@Override
	@CacheEvict(value = "Employee", key = "#employeeId")
	public void deleteEmployee(Integer employeeId) throws EmployeeNotFoundException {
		log.info("Get Particular Employee Data from Database...");

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
		employeeRepository.delete(employee);

	}

}
