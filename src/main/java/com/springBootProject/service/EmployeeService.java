package com.springBootProject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBootProject.model.Employee;
import com.springBootProject.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee AddEmployee(Employee emp) {
		Employee save = employeeRepository.save(emp);
		return save;
	}

	// with order By ASC
	public List<Employee> GetAllEmployee() {
		List<Employee> allEmployee = employeeRepository.findAllByOrderByJoiningDateAsc();
		return allEmployee;
	}

	public Employee UpdateEmployee(Employee emp, int empId) {
		emp.setId(empId);
		return employeeRepository.save(emp);
	}

	public Employee GetEmployeeById(int empId) {
		try {
			return employeeRepository.findById(empId).get();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Employee> GetEmployeeByDepatment(String department) {
		List<Employee> findByDepartment = employeeRepository.findByDepartment(department);
		return findByDepartment;
	}

	// HardDelete Or FostDelete
	public void DeleteEmployee(int empId) {
		employeeRepository.deleteById(empId);
	}

	public void HardDelete(int empId) throws SQLException, ClassNotFoundException {
		String sql = "delete from employee where id = ?";
		final String JDBC_URL = "jdbc:mysql://localhost:3306/springdata";
		final String USERNAME = "root";
		final String PASSWORD = "Rahul@1234";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		PreparedStatement pr = con.prepareStatement(sql);
		pr.setInt(1, empId);
		pr.executeUpdate();
		con.close();

	}
}
