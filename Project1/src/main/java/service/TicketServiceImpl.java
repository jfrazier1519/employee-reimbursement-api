package service;

import java.util.List;

import dao.TicketDao;
import dao.TicketDaoImpl;
import model.Ticket;

public class TicketServiceImpl implements TicketService {

	TicketDao myTicketDao = new TicketDaoImpl();
	
	public static void main(String[] args) {
	
	TicketService ticket = new TicketServiceImpl();
//	System.out.println(ticket.getAllTickets());
		
	ticket.addTicket(new Ticket(1, 234, "Test Description 6", "pending", 4, 2));
	
//	System.out.println(ticket.getTicketByEmployeeId(2));
}
	
	
	
/////////////////////////////////////////////INSERT
	@Override
	public boolean addTicket(Ticket ticket) {
		boolean boolResults = myTicketDao.insertTicket(ticket);
		
		return boolResults;
	}

/////////////////////////////////////////////SELECT


	@Override
	public List<Ticket> getAllTickets() {
		return myTicketDao.selectAllTickets();
	}

	@Override
	public List<Ticket> getTicketByEmployeeId(int employeeId) {
		return myTicketDao.selectTicketByEmployeeId(employeeId);
	}

	@Override
	public List<Ticket> getTicketByStatus(int employeeId, String ticketStatus) {
		return myTicketDao.selectTicketByStatus(employeeId, ticketStatus);
	}
	
	@Override
	public List<Ticket> getAllTicketByStatus(String ticketStatus) {
		return myTicketDao.selectAllTicketByStatus(ticketStatus);
	}
	
	@Override
	public List<Ticket> getTicketByType(int employeeId, String ticketType) {
		return myTicketDao.selectTicketByType(employeeId, ticketType);
	}
	
/////////////////////////////////////////////UPDATE
	@Override
	public boolean approveTicket(int ticketId) {
		boolean boolResults = myTicketDao.updateTicketApproved(ticketId);
		
		return boolResults;
	}

	@Override
	public boolean denyTicket(int ticketId) {
		boolean boolResults = myTicketDao.updateTicketDenied(ticketId);
		
		return boolResults;
	}
	
/////////////////////////////////////////////DELETE
	@Override
	public boolean deleteTicket(int ticketId) {
		boolean boolResults = myTicketDao.deleteTicket(ticketId);
		
		return boolResults;
	}

}
