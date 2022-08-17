package service;

import java.util.List;

import model.Employee;

public interface EmployeeService {

	// inserts
	public boolean addEmployee(Employee employee);

	// selects
	public Employee getEmployeeById(int employeeId);

	public List<Employee> getAllEmployees();

	//updates
	public boolean promoteToManager(int employeeId);
	public boolean demoteToEmployee(int employeeId);
	
	// delete
	public boolean removeEmployee(int employeeId);
}
