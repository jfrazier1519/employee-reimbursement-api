package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao myEmployeeDao = new EmployeeDaoImpl();
	
	Employee employee = new Employee();


	
	public static void main(String[] args) {
	
		EmployeeService employ = new EmployeeServiceImpl();
	

		
		
	}
	
/////////////////////////////////////////////INSERT
	@Override
	public boolean addEmployee(Employee employee) {

		boolean boolResults = myEmployeeDao.insertEmployee(employee);

		return boolResults;
	}
/////////////////////////////////////////////SELECT
	@Override
	public Employee getEmployeeById(int employeeId) {
		return myEmployeeDao.selectEmployeeById(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return myEmployeeDao.selectAllEmployee();
	}
	
/////////////////////////////////////////////UPDATE
	@Override
	public boolean promoteToManager(int employeeId) {

		boolean boolResults = myEmployeeDao.updateEmployeeToManager(employeeId);

		return boolResults;
		

		
	}
	
	@Override
	public boolean demoteToEmployee(int employeeId) {

		boolean boolResults = myEmployeeDao.updateManagerToEmployee(employeeId);

		return boolResults;
		


		
		
		
	}
	
	
/////////////////////////////////////////////DELETE
	@Override
	public boolean removeEmployee(int employeeId) {

		if (myEmployeeDao.selectEmployeeById(employeeId) == null)
			return false;
		else
			return myEmployeeDao.deleteEmployee(employeeId);
	}

}
