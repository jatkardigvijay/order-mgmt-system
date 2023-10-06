package com.jbd.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbd.dao.EmployeeDao;
import com.jbd.entity.Employee;
import com.jbd.exception.JbdException;
import com.jbd.service.EmployeeService;

@Service
@org.springframework.transaction.annotation.Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class.getName());

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> getAllEmployees() throws JbdException {

		logger.info("inside EmployeeServiceImpl with method : getAllEmployees()");
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) throws JbdException {

		logger.info("inside EmployeeServiceImpl with method : getEmployeeById()");
		return employeeDao.getEmployeeById(id);
	}

	@Override
	public boolean deleteEmployee(int id) throws JbdException {

		logger.info("inside EmployeeServiceImpl with method : deleteEmployee()");
		return employeeDao.deleteEmployee(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws JbdException {

		logger.info("inside EmployeeServiceImpl with method : updateEmployee()");
		Employee updatedEmployee = employeeDao.updateEmployee(employee);
		return updatedEmployee;
	}

	@Override
	public boolean insertEmployee(Employee employee) throws JbdException {

		logger.info("inside EmployeeServiceImpl with method : updateEmployee()");
		return employeeDao.insertEmployee(employee);

	}

}
