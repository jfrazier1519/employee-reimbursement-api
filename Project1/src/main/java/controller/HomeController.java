package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

	//Stretch Goal. Never implemented.
	
	public static void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String myPath = "/resources/html/index.html";
			
			req.getRequestDispatcher(myPath).forward(req, resp);
		}
	
	
	public static void managerHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String myPath = "/resources/html/manager.html";
		
		req.getRequestDispatcher(myPath).forward(req, resp);
	}
	
	
	public static void employeeHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String myPath = "/resources/html/employee.html";
		
		req.getRequestDispatcher(myPath).forward(req, resp);
	}
}
