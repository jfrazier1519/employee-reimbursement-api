package model;

public class Employee {

	private int employeeId;
	private String username;
	private String password;
	private String name;
	private String role;

/////////////////////CONSTRUCTOR

	public Employee() {

	}



public Employee(int employeeId, String username, String password, String name, String role) {
	super();
	this.employeeId = employeeId;
	this.username = username;
	this.password = password;
	this.name = name;
	this.role = role;
}


///////////////////////GETTERS AND SETTERS

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return name;
	}

	public void setEmployeeName(String employeeName) {
		this.name = employeeName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmployeeRole() {
		return role;
	}

	public void setEmployeeRole(String employeeRole) {
		this.role = employeeRole;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", password=" + password + ", name="
				+ name + ", role=" + role + "]";
	}



}
