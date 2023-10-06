package com.jbd.utils;

public class Queries {
	
	public static final String GET_ALL_EMPLOYEES = "select * from employeedetails";
	public static final String GET_EMPLOYEE_BY_EMPLOYEE_ID = "select * from employeedetails where employeeId = ?";
	public static final String DELETE_EMPLOYEE_BY_EMPLOYEE_ID = "delete from employeedetails where employeeId = ?";
	public static final String UPDATE_EMPLOYEE = "update employeedetails set firstName = ?, lastName = ?, email = ?, contactNumber = ? where employeeId = ?";
	public static final String INSERT_EMPLOYEE = "insert into employeedetails values (?, ?, ?, ?, ?)";
}
