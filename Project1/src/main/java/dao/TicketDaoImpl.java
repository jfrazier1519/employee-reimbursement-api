package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ticket;

public class TicketDaoImpl implements TicketDao {

	public static void main(String[] args) {
		TicketDao ticket = new TicketDaoImpl();
//		System.out.println(ticket.selectAllTickets());
		ticket.insertTicket(new Ticket(1, 523, "Test Description 5", "Test status", 2, 1));
//		ticket.updateTicketApproved(1);
//		ticket.updateTicketDenied(1);
//		ticket.deleteTicket(3);
//		System.out.println(ticket.selectTicketByStatus(2, "pending"));
//		System.out.println(ticket.selectTicketByType(2, "Food"));

	}

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Static block has failed me");
		}
	}

/////////////////////////////////////////////INSERT

	@Override
	public boolean insertTicket(Ticket ticket) {
		// parentheses behind try block is try with resources. auto close
		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "INSERT INTO tickets VALUES(DEFAULT,?,?,'pending', ?, ?);";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);

			ps.setLong(1, ticket.getTicketAmount()); // the first ?
			ps.setString(2, ticket.getTicketDescription());
			ps.setInt(3, ticket.getEmployeeId());
			ps.setInt(4, ticket.getTicketTypeId());

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

/////////////////////////////////////////////SELECT
	

	@Override
	public List<Ticket> selectTicketByEmployeeId(int employeeId) {
		List<Ticket> tickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "SELECT ticket_id, ticket_amount, ticket_description, ticket_status, employee_id, employee_name, ticket_type\r\n"
					+ "FROM tickets\r\n" + "INNER JOIN employees\r\n"
					+ "ON employees.employee_id = tickets.ticket_employee_id\r\n" + "INNER JOIN  ticket_type\r\n"
					+ "ON ticket_type.ticket_type_id = tickets.ticket_type_id\r\n" + "WHERE employee_id = (?);";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, employeeId); 

			ps.executeQuery(); 

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				
				Ticket newTicket = new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));

				tickets.add(newTicket);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;

	}

	@Override
	public List<Ticket> selectTicketByStatus(int employeeId, String ticketStatus) {

		List<Ticket> tickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "SELECT ticket_id, ticket_amount, ticket_description, ticket_status, employee_id, employee_name, ticket_type\r\n"
					+ "FROM tickets\r\n" + "INNER JOIN employees\r\n"
					+ "ON employees.employee_id = tickets.ticket_employee_id\r\n" + "INNER JOIN  ticket_type\r\n"
					+ "ON ticket_type.ticket_type_id = tickets.ticket_type_id\r\n"
					+ "WHERE employee_id = (?) AND ticket_status = (?);";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, employeeId); 
			ps.setString(2, ticketStatus);

			ps.executeQuery(); 

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				
				Ticket newTicket = new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));

				tickets.add(newTicket);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;
	}

	@Override
	public List<Ticket> selectAllTicketByStatus(String ticketStatus) {

		List<Ticket> tickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "SELECT ticket_id, ticket_amount, ticket_description, ticket_status, employee_id, employee_name, ticket_type\r\n"
					+ "FROM tickets\r\n" + "INNER JOIN employees\r\n"
					+ "ON employees.employee_id = tickets.ticket_employee_id\r\n" + "INNER JOIN  ticket_type\r\n"
					+ "ON ticket_type.ticket_type_id = tickets.ticket_type_id\r\n" + "WHERE ticket_status = (?);";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setString(1, ticketStatus);

			ps.executeQuery(); 

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				
				Ticket newTicket = new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));

				tickets.add(newTicket);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;
	}

	public List<Ticket> selectTicketByType(int employeeId, String ticketType) {

		List<Ticket> tickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "SELECT ticket_id, ticket_amount, ticket_description, ticket_status, employee_id, employee_name, ticket_type\r\n"
					+ "FROM tickets\r\n" + "INNER JOIN employees\r\n"
					+ "ON employees.employee_id = tickets.ticket_employee_id\r\n" + "INNER JOIN  ticket_type\r\n"
					+ "ON ticket_type.ticket_type_id = tickets.ticket_type_id\r\n"
					+ "WHERE employee_id = (?) AND ticket_type = (?);";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, employeeId);
			ps.setString(2, ticketType);

			ps.executeQuery();

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				
				Ticket newTicket = new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));

				tickets.add(newTicket);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;

	}

	@Override
	public List<Ticket> selectAllTickets() {
		List<Ticket> tickets = new ArrayList<>();

		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "SELECT ticket_id, ticket_amount, ticket_description, ticket_status, employee_id, employee_name, ticket_type\r\n"
					+ "FROM tickets\r\n" + "INNER JOIN employees\r\n"
					+ "ON employees.employee_id = tickets.ticket_employee_id\r\n" + "INNER JOIN  ticket_type\r\n"
					+ "ON ticket_type.ticket_type_id = tickets.ticket_type_id;";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				Ticket newTicket = new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));

				tickets.add(newTicket);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;
	}

/////////////////////////////////////////////UPDATE
	@Override
	public boolean updateTicketApproved(int ticketId) {
		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "UPDATE tickets SET ticket_status = 'approved' WHERE ticket_id = (?)";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, ticketId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean updateTicketDenied(int ticketId) {
		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "UPDATE tickets SET ticket_status = 'denied' WHERE ticket_id = (?)";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, ticketId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

/////////////////////////////////////////////DELETE
	@Override
	public boolean deleteTicket(int ticketId) {
		try (Connection conn = CustomConnection.getConnection()) {

			String ourSQLStatement = "DELETE FROM tickets WHERE ticket_id = (?)";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, ticketId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

}
