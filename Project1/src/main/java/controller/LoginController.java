package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class LoginController {

	public static void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		EmployeeService employee = new EmployeeServiceImpl();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		List<Employee> employees = employee.getAllEmployees();

		if (!req.getMethod().equals("POST")) {
			resp.getWriter().println("You didn't use a POST method; Try again.");

		} else {

			for (Employee temp : employees) {
				if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
					System.out.println("The user and pass match!!!");
//						System.out.println(temp);
					Employee foundEmployee = temp;
					if (foundEmployee.getEmployeeRole().equals("employee")) {
						System.out.println("this is an employee!!!");

						HttpSession employeeSession = req.getSession();
						employeeSession.setAttribute("currentEmployee", foundEmployee);

						PrintWriter printer = resp.getWriter();
						printer.println(foundEmployee.getEmployeeName() + " is logged in");

					} else if (foundEmployee.getEmployeeRole().equals("manager")) {
						System.out.println("this is a manager!!!");

						HttpSession managerSession = req.getSession();
						managerSession.setAttribute("currentManager", foundEmployee);

						resp.setContentType("application/json");
						PrintWriter printer = resp.getWriter();

						printer.println(foundEmployee.getEmployeeName() + " is logged in");

					}

				}

			}

		}
	}

	//Stretch Goal. Not Implemented.
	public static void loginWithHtml(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		EmployeeService employee = new EmployeeServiceImpl();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		List<Employee> employees = employee.getAllEmployees();

		PrintWriter printer1 = resp.getWriter();

		for (Employee temp : employees) {

			if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
				System.out.println("The user and pass match!!!");
//					System.out.println(temp);
				Employee foundEmployee = temp;
				if (foundEmployee.getEmployeeRole().equals("employee")) {
					System.out.println("this is an employee!!!");

					HttpSession employeeSession = req.getSession();
					employeeSession.setAttribute("currentEmployee", foundEmployee);

					req.getRequestDispatcher("/employee/home").forward(req, resp);

				} else if (foundEmployee.getEmployeeRole().equals("manager")) {
					System.out.println("this is a manager!!!");

					HttpSession managerSession = req.getSession();
					managerSession.setAttribute("currentManager", foundEmployee);

					req.getRequestDispatcher("/home/manager").forward(req, resp);
				}
			}
		}
	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession employeeSession = req.getSession();
		Employee currentEmployee = (Employee) employeeSession.getAttribute("currentEmployee");
		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");


		if (currentEmployee != null) {
			employeeSession.invalidate();
		} else if (currentManager != null) {
			managerSession.invalidate();
		}

		PrintWriter printer = resp.getWriter();
		printer.println("You have logged out.");

	}

}
