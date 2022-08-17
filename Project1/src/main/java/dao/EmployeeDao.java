package dao;

import java.util.List;

import model.Employee;

public interface EmployeeDao {

	//For new employee to register and manager to manage employee records
	//inserts
	public boolean insertEmployee(Employee employee);
	
	//selects
	public Employee selectEmployeeById(int employeeId);
	public List<Employee> selectAllEmployee();
	
	
	//update -- only manager can change other employee roles
	public boolean updateEmployeeToManager(int employeeId);
	public boolean updateManagerToEmployee(int employeeId);
	
	//delete
	public boolean deleteEmployee(int employeeId);
	
}

