package com.jbd.service;

import java.util.List;

import com.jbd.entity.Employee;
import com.jbd.exception.JbdException;

public interface EmployeeService {

	List<Employee> getAllEmployees() throws JbdException;

	Employee getEmployeeById(int id) throws JbdException;

	boolean deleteEmployee(int id) throws JbdException;

	Employee updateEmployee(Employee employee) throws JbdException;

	boolean insertEmployee(Employee employee) throws JbdException;
}
