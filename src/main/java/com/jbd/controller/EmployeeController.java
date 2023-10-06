package com.jbd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbd.config.Response;
import com.jbd.entity.Employee;
import com.jbd.exception.JbdException;
import com.jbd.service.EmployeeService;

@RestController
@Validated
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequestMapping("employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/api/v1")
	public ResponseEntity<Response> getAllEmployees() throws JbdException {

		List<Employee> employeeList = employeeService.getAllEmployees();

		if (employeeList == null || employeeList.isEmpty()) {
			logger.info("No data found or list is empty");
			throw new JbdException("No data found", HttpStatus.OK, employeeList);
		} else {
			logger.info("Received employee list with size : " + employeeList.size());
			return new ResponseEntity<Response>(new Response("success", employeeList, null), HttpStatus.OK);
		}

	}

	@GetMapping("/api/v1/{id}")
	public ResponseEntity<Response> getEmployeeById(@PathVariable int id) throws JbdException {

		Employee employee = employeeService.getEmployeeById(id);

		if (employee == null || employee.getEmployeeId() <= 0) {
			logger.info("No data found or list is empty");
			throw new JbdException("No data found", HttpStatus.OK, employee);
		} else {
			logger.info("Received employee with employee id : " + employee.getEmployeeId());
			return new ResponseEntity<Response>(new Response("success", employee, null), HttpStatus.OK);
		}

	}

	@DeleteMapping("/api/v1/{id}")
	public boolean deleteEmployee(@PathVariable("id") int id) throws JbdException {

		logger.info("deleted Employee with id = " + id);
		return employeeService.deleteEmployee(id);
	}

	@PutMapping("/api/v1/update/{id}")
	public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id)
			throws JbdException {

		logger.info("updated Employee with id = " + id);
		employee.setEmployeeId(id);
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<Response>(new Response("Record Updated Successfully", updatedEmployee, null),
				HttpStatus.OK);
	}

	@PostMapping("/api/v1/add")
	public boolean insertEmployee(@RequestBody Employee employee) throws JbdException {

		logger.info("inserted employee with info as = "+employee);
		return employeeService.insertEmployee(employee);
	}
}
