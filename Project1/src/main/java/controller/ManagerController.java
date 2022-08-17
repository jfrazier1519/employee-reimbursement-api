package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;
import model.Ticket;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.TicketService;
import service.TicketServiceImpl;

public class ManagerController extends LoginController {

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager retrieves a list of all employees in the system.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		EmployeeService employee = new EmployeeServiceImpl();
		List<Employee> employees = employee.getAllEmployees();

		HttpSession managerSession = req.getSession();
//			managerSession.setAttribute("currentEmployee", foundEmployee);
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		if (currentManager != null) {
			printer.write(new ObjectMapper().writeValueAsString(employees));
		} else {
			printer.println("No manager is logged in...");
		}

	}

/////////////////////////////////////////////
	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager can change the role of an employee.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void changeRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		EmployeeService employee = new EmployeeServiceImpl();
		List<Employee> employees = employee.getAllEmployees();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		int employeeId = Integer.parseInt(req.getParameter("employeeId"));
		String changeRole = req.getParameter("roleChange");

		if (currentManager != null && changeRole.equals("manager")) {
			for (Employee temp : employees) {

				if (temp.getEmployeeId() == employeeId && temp.getEmployeeRole().equals("employee")) {
					employee.promoteToManager(employeeId);
					printer.println("user has been promoted to manager");

				} else {
					printer.println("target user is already a manager");
				}
				break;
			}
		} else if (currentManager != null && changeRole.equals("employee")) {
			for (Employee temp : employees) {

				if (temp.getEmployeeId() == employeeId && temp.getEmployeeRole().equals("manager")) {
					employee.demoteToEmployee(employeeId);
					printer.println("user has been demoted to employee");
				} else {
					printer.println("target user is already a employee");
				}
				break;
			}
		} else {
			printer.println("No manager is logged in...");
		}

	}
	///////////////////////////////////////
	
	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager retrieves a list of all tickets in the system
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewAllTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		if (currentManager != null) {
			printer.write(new ObjectMapper().writeValueAsString(tickets));
		} else {
			printer.println("No manager is logged in...");
		}

	}

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager retrieves a ticket by primary key (id)
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewTicketById(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		int ticketId = Integer.parseInt(req.getParameter("ticketId"));

		if (currentManager != null) {
			for (Ticket temp : tickets) {
				if (temp.getTicketId() == ticketId) {
					printer.write(new ObjectMapper().writeValueAsString(temp));
				}
			}
		} else {
			printer.println("No manager is logged in...");
		}

	}

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager approves a reimbursement ticket via the ticket id
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void approveTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		int ticketId = Integer.parseInt(req.getParameter("ticketId"));

		if (currentManager != null) {
			for (Ticket temp : tickets) {

				if (temp.getTicketId() == ticketId && temp.getTicketStatus().equals("pending")) {
					ticket.approveTicket(ticketId);
					printer.println("Ticket # " + ticketId + " has been approved");
				} else if (temp.getTicketId() == ticketId) {
					printer.println("Ticket has already been processed");
				}
			}
		} else {
			printer.println("No manager is logged in...");
		}

	}

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager denies a reimbursement ticket via the ticket id
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void denyTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		int ticketId = Integer.parseInt(req.getParameter("ticketId"));

		if (currentManager != null) {
			for (Ticket temp : tickets) {

				if (temp.getTicketId() == ticketId && temp.getTicketStatus().equals("pending")) {
					ticket.denyTicket(ticketId);
					printer.println("Ticket # " + ticketId + " has been denied");
				} else if (temp.getTicketId() == ticketId) {
					printer.println("Ticket has already been processed");
				}
			}
		} else {
			printer.println("No manager is logged in...");
		}

	}

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager can add a new employee to the database via a JSON object.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		EmployeeService employee = new EmployeeServiceImpl();
		List<Employee> employees = employee.getAllEmployees();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		if (currentManager != null) {
			ObjectMapper mapper = new ObjectMapper();

			Employee newEmployee = mapper.readValue(req.getInputStream(), Employee.class);

			printer.println("New employee " + newEmployee.getEmployeeName() + " added");

			employee.addEmployee(newEmployee);

		} else {
			printer.println("No manager is logged in...");
		}

	}

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager can view all the tickets for a specific employee
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewTicketByEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		int ticketId = Integer.parseInt(req.getParameter("ticketId"));

		if (currentManager != null) {
			List<Ticket> employeeTickets = ticket.getTicketByEmployeeId(ticketId);
			printer.write(new ObjectMapper().writeValueAsString(employeeTickets));
		} else {
			printer.println("No manager is logged in...");
		}

	}

	/**
	 * Method checks current session to see if a manager is logged in. 
	 * If true, a manager can view all tickets filtered by status (pending, approved, denied).
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewAllTicketsByStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession managerSession = req.getSession();
		Employee currentManager = (Employee) managerSession.getAttribute("currentManager");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		String ticketStatus = req.getParameter("ticketStatus");

		if (currentManager != null) {
			int employeeId = currentManager.getEmployeeId();

			List<Ticket> queryTickets = ticket.getAllTicketByStatus(ticketStatus);

			printer.write(new ObjectMapper().writeValueAsString(queryTickets));
		} else {
			printer.println("No manager is logged in...");
		}

	}

}
