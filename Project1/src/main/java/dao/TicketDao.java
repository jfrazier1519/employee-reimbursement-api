package dao;

import java.util.List;

import model.Ticket;

public interface TicketDao {

	
	public boolean insertTicket(Ticket ticket);

	// selects
	public List<Ticket> selectAllTickets();

	public List<Ticket> selectTicketByEmployeeId(int employeeId);

	public List<Ticket> selectTicketByStatus(int employeeId, String ticketStatus);

	public List<Ticket> selectTicketByType(int employeeId, String ticketType);

	public List<Ticket> selectAllTicketByStatus(String ticketStatus);

	// update
	public boolean updateTicketApproved(int ticketId);

	public boolean updateTicketDenied(int ticketId);

	// delete - don't think I'll be using
	public boolean deleteTicket(int ticketId);

}
