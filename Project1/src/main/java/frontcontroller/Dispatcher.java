package frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.EmployeeController;
import controller.HomeController;
import controller.LoginController;
import controller.ManagerController;

public class Dispatcher {

		public static void myVirtualRouterMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//			System.out.println("\n\n\tIN OUR DISPATCHER ( myVirtualRouter() )");
			
//			System.out.println("Current URL: "+ req.getRequestURL());
//			System.out.println("Current URI: "+ req.getRequestURI());
			
			switch(req.getRequestURI()) {
				case "/Project1/master/login":
					System.out.println("you are calling the login controller");
					LoginController.login(req, resp);
				break;
				case "/Project1/master/logout":
					System.out.println("you are calling the login controller");
					LoginController.logout(req, resp);
				break;
				case "/Project1/employee/submitticket":
					System.out.println("You are calling the employee submit ticket method");
					EmployeeController.submitTicket(req, resp);
				break;
				case "/Project1/employee/viewticketbystatus":
					System.out.println("You are calling the employee view tickets by status controller");
					EmployeeController.viewTicketsByStatus(req, resp);
				break;
				case "/Project1/employee/viewticketbytype":
					System.out.println("You are calling the employee view tickets by type controller");
					EmployeeController.viewTicketsByType(req, resp);
				break;
				case "/Project1/manager/viewallemployees":
					System.out.println("You are calling the manager view all employees method");
					ManagerController.viewAllEmployees(req, resp);
				break;
				case "/Project1/manager/createemployee":
					System.out.println("You are calling the manager deny ticket method");
					ManagerController.createEmployee(req, resp);
				break;
				case "/Project1/manager/changerole":
					System.out.println("You are calling the manager change role method");
//					ManagerController.changeRole(req, resp); //THIS IS EXTRA. WORK IN PROGRESS 
				break;
				case "/Project1/manager/viewalltickets":
					System.out.println("You are calling the manager view all tickets method");
					ManagerController.viewAllTickets(req, resp);
				break;
				case "/Project1/manager/viewticket":
					System.out.println("You are calling the manager view ticket method");
					ManagerController.viewTicketById(req, resp);
				break;
				case "/Project1/manager/viewticketbyemployee":
					System.out.println("You are calling the manager view ticket method");
					ManagerController.viewTicketByEmployee(req, resp);
				break;
				case "/Project1/manager/viewallticketbystatus":
					System.out.println("You are calling the manager view pending tickets method");
					ManagerController.viewAllTicketsByStatus(req, resp);
				break;
				
				case "/Project1/manager/approveticket":
					System.out.println("You are calling the manager approve ticket method");
					ManagerController.approveTicket(req, resp);
				break;
				case "/Project1/manager/denyticket":
					System.out.println("You are calling the manager deny ticket method");
					ManagerController.denyTicket(req, resp);
				break;
				case "/Project1/home":
					System.out.println("You are calling the main html page");
					HomeController.home(req, resp);
				break;
				case "/Project1/login/loginwithhtml":
					System.out.println("You are calling login");
					LoginController.loginWithHtml(req, resp);
				break;
				case "/Project1/home/manager":
					System.out.println("You are going to manager home");
					HomeController.managerHome(req, resp);
				break;
				case "/Project1/home/employee":
					System.out.println("You are going to employee home");
//					LoginController.loginForwardResponse(req, resp);
				break;
				default: 
					System.out.println("Bad URI path. Try Again.");;
					System.out.println(req.getRequestURI());
				break;
			}
		}
		
}
