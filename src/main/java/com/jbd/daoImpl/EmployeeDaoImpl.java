package com.jbd.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.jbd.dao.EmployeeDao;
import com.jbd.entity.Employee;
import com.jbd.exception.JbdException;
import com.jbd.utils.Queries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class.getName());

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Employee> getAllEmployees() throws JbdException {

		List<Employee> employeeList = new ArrayList<Employee>();
		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.GET_ALL_EMPLOYEES);

			logger.info("Executing query : " + Queries.GET_ALL_EMPLOYEES);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Employee emp = new Employee(rs.getInt("employeeId"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("email"), rs.getString("contactNumber"));

				employeeList.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JbdException("Error executing query : " + Queries.GET_ALL_EMPLOYEES,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) throws JbdException {

		Employee employee = null;
		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.GET_EMPLOYEE_BY_EMPLOYEE_ID);

			logger.info("Executing query : " + Queries.GET_EMPLOYEE_BY_EMPLOYEE_ID);
			ps.setObject(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				employee = new Employee(rs.getInt("employeeId"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("email"), rs.getString("contactNumber"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JbdException("Error executing query : " + Queries.GET_EMPLOYEE_BY_EMPLOYEE_ID,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return employee;
	}

	@Override
	public boolean deleteEmployee(int id) throws JbdException {

		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			logger.info("Executing query : " + Queries.DELETE_EMPLOYEE_BY_EMPLOYEE_ID);
			ps = connection.prepareStatement(Queries.DELETE_EMPLOYEE_BY_EMPLOYEE_ID);

			ps.setInt(1, id);

			boolean rs = ps.execute();

			if (rs == true) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JbdException("Error executing query : " + Queries.DELETE_EMPLOYEE_BY_EMPLOYEE_ID,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return true;
	}

	@Override
	public Employee updateEmployee(Employee employee) throws JbdException {

		PreparedStatement ps = null;
		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.UPDATE_EMPLOYEE);

			if (employee != null) {

				int employeeId = employee.getEmployeeId();
				ps.setString(1, employee.getFirstName());
				ps.setString(2, employee.getLastName());
				ps.setString(3, employee.getEmail());
				ps.setString(4, employee.getContactNumber());
				ps.setInt(5, employeeId);

				logger.info("Executing query : " + Queries.UPDATE_EMPLOYEE);
				int rs = ps.executeUpdate();

				if (rs == 1) {
					System.out.println("Record updated with id : " + employeeId);
					return employee;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JbdException("Error executing query : " + Queries.UPDATE_EMPLOYEE, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return employee;
	}

	@Override
	public boolean insertEmployee(Employee employee) throws JbdException {

		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.INSERT_EMPLOYEE);

			ps.setInt(1, employee.getEmployeeId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setString(4, employee.getEmail());
			ps.setString(5, employee.getContactNumber());

			logger.info("Executing query : " + Queries.INSERT_EMPLOYEE);
			int rs = ps.executeUpdate();

			if (rs == 1) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JbdException("Error executing query : " + Queries.INSERT_EMPLOYEE, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return true;
	}

}
