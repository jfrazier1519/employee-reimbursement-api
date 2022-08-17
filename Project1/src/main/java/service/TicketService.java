package service;

import java.util.List;

import model.Ticket;

public interface TicketService {

	/*
	 * This is how:
	 * 	Employees are going to create tickets
	 * 	Managers are going to read/update tickets
	 */
	//inserts
	public boolean addTicket(Ticket ticket);
	
	//selects
	public List<Ticket> getAllTickets();
	public List<Ticket> getTicketByEmployeeId(int employeeId);
	public List<Ticket> getTicketByStatus(int employeeId, String ticketStatus);
	public List<Ticket> getTicketByType(int employeeId, String ticketType);
	public List<Ticket> getAllTicketByStatus(String ticketStatus);
	
	
	
	//update
	public boolean approveTicket(int ticketId);
	public boolean denyTicket(int ticketId);
	
	
	//delete - don't think I'll be using
	public boolean deleteTicket(int ticketId);

}
