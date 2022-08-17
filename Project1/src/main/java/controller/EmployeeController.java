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
import service.TicketService;
import service.TicketServiceImpl;

public class EmployeeController {

	/**
	 * Method checks current session to see if an employee is logged in. 
	 * If true, an employee can submit a ticket via field parameters.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void submitTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession employeeSession = req.getSession();
		Employee currentEmployee = (Employee) employeeSession.getAttribute("currentEmployee");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		int ticketId = Integer.parseInt(req.getParameter("ticketId"));
		int ticketAmount = Integer.parseInt(req.getParameter("ticketAmount"));
		String ticketDescription = req.getParameter("ticketDescription");
		String ticketStatus = req.getParameter("ticketStatus");
		int employeeId = Integer.parseInt(req.getParameter("employeeId"));
		int ticketType = Integer.parseInt(req.getParameter("ticketTypeId"));

		if (currentEmployee != null) {

			ticket.addTicket(
					new Ticket(ticketId, ticketAmount, ticketDescription, ticketStatus, employeeId, ticketType));
			printer.println("Ticket submitted");

		} else {
			printer.println("No employee is logged in...");
		}

	}

	/**
	 * Method checks current session to see if an employee is logged in. 
	 * If true, an employee can view his/her previous requests filtered by status
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewTicketsByStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession employeeSession = req.getSession();
		Employee currentEmployee = (Employee) employeeSession.getAttribute("currentEmployee");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		String ticketStatus = req.getParameter("ticketStatus");

		if (currentEmployee != null) {
			int employeeId = currentEmployee.getEmployeeId();

			List<Ticket> queryTickets = ticket.getTicketByStatus(employeeId, ticketStatus);

			printer.write(new ObjectMapper().writeValueAsString(queryTickets));
		} else {
			printer.println("No employee is logged in...");
		}

	}

	/**
	 * Method checks current session to see if an employee is logged in. 
	 * If true, an employee can view his/her tickets filtered by type.
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public static void viewTicketsByType(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		TicketService ticket = new TicketServiceImpl();
		List<Ticket> tickets = ticket.getAllTickets();

		HttpSession employeeSession = req.getSession();
		Employee currentEmployee = (Employee) employeeSession.getAttribute("currentEmployee");

		resp.setContentType("application/json");
		PrintWriter printer = resp.getWriter();

		String ticketType = req.getParameter("ticketType");

		if (currentEmployee != null) {
			int employeeId = currentEmployee.getEmployeeId();

			List<Ticket> queryTickets = ticket.getTicketByType(employeeId, ticketType);

			printer.write(new ObjectMapper().writeValueAsString(queryTickets));
		} else {
			printer.println("No employee is logged in...");
		}

	}

}
