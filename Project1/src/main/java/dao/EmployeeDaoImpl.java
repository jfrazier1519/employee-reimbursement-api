package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

//	public static void main(String[] args) {
//		EmployeeDao employee = new EmployeeDaoImpl();
//		System.out.println(employee.selectAllEmployee());
//		employee.updateEmployeeToManager(1);
//		employee.updateManagerToEmployee(1);
//		employee.selectEmployeeById(1);
		
//		employee.insertEmployee(new Employee(1, "randomUser", "randomPass", "Random Dude", "jester"));
		
//		employee.deleteEmployee(2);
		
//	}
	
	/**
	 * Necessary to establish a connection to database
	 */
    static { 
          try {
              Class.forName("org.postgresql.Driver");
          }catch(ClassNotFoundException e) {
              e.printStackTrace();
              System.out.println("Static block has failed me");
          }
    }
	
	@Override
	public boolean insertEmployee(Employee employee) {

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "INSERT INTO employees VALUES(DEFAULT,?,?,?,'employee')";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setString(1, employee.getUsername()); // the first ?
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getEmployeeName());
//			ps.setString(4, employee.getEmployeeRole());

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Employee selectEmployeeById(int employeeId) {
//		try (Connection conn = CustomConnection.getConnection()) {
//
//			String ourSQLStatement = "SELECT * FROM employees WHERE employee_id = 1";
//
//			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
//			ps.setString(1, employee.getUsername()); // the first ?
//			ps.setString(2, employee.getPassword());
//			ps.setString(3, employee.getEmployeeName());
////			ps.setString(4, employee.getEmployeeRole());
//
//			ps.executeUpdate();
//
//			return employee;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//			return null;
//		}
		return null;
	}

	@Override
	public List<Employee> selectAllEmployee() {

		List<Employee> employees = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "SELECT * FROM employees";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);

			ResultSet resultSet = ps.executeQuery(); // NOTE...this is NOT "executeUpdate" it's "executeQuery"

			while (resultSet.next()) {
				// resultSet.getInt(columnIndex)
				Employee newEmployee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5));

				employees.add(newEmployee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}
	
///////////////////////////////////////////UPDATE	
	@Override
	public boolean updateEmployeeToManager(int employeeId) {
		try (Connection conn = CustomConnection.getConnection()) {

			// CHANGE SQL STATEMENT TO UPDATE
			String ourSQLStatement = "UPDATE employees SET employee_role = 'manager' WHERE employee_id = (?)";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			 ps.setInt(1, employeeId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}
	
	@Override
	public boolean updateManagerToEmployee(int employeeId) {
		try (Connection conn = CustomConnection.getConnection()) {

			// CHANGE SQL STATEMENT TO UPDATE
			String ourSQLStatement = "UPDATE employees SET employee_role = 'employee' WHERE employee_id = (?)";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			 ps.setInt(1, employeeId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}
	
///////////////////////////////////////////DELETE	
	@Override
	public boolean deleteEmployee(int employeeId) {
		
			try (Connection conn = CustomConnection.getConnection()) {

				// CHANGE SQL STATEMENT TO DELETE
				String ourSQLStatement = "DELETE FROM employees WHERE employee_id = (?)";

				PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
				ps.setInt(1, employeeId);

				ps.executeUpdate();

				return true;

			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			}
		}
	



}
