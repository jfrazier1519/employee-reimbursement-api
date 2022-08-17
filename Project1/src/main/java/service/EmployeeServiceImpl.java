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
	
//		employ.demoteToEmployee(1);
//		employ.promoteToManager(1);
		
		
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
		
//		if(employee.getEmployeeRole() == "manager") {
//			System.out.println("is already a manager");
//			return false;
//		}else
//			return myEmployeeDao.updateEmployeeToManager(employeeId);
		
	}
	
	@Override
	public boolean demoteToEmployee(int employeeId) {

		boolean boolResults = myEmployeeDao.updateManagerToEmployee(employeeId);

		return boolResults;
		
//		if((employee.getEmployeeId(employeeId)).getEmployeeRole() == "employee") {
//			System.out.println("is already an employee");
//			return false;
//		}else
//			return myEmployeeDao.updateManagerToEmployee(employeeId);

		
		
		
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
