package model;

public class Ticket {

	private int ticketId;
	private int ticketAmount;
	private String ticketDescription;
	private String ticketStatus;
	private int employeeId;
	private String employeeName;
	private String ticketType;
	private int ticketTypeId;

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticketId, int ticketAmount, String ticketDescription, String ticketStatus, int employeeId,
			String employeeName, String ticketType) {
		this.ticketId = ticketId;
		this.ticketAmount = ticketAmount;
		this.ticketDescription = ticketDescription;
		this.ticketStatus = ticketStatus;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.ticketType = ticketType;
	}
	public Ticket(int ticketId, int ticketAmount, String ticketDescription, String ticketStatus, int employeeId, int ticketTypeId) {
		super();
		this.ticketId = ticketId;
		this.ticketAmount = ticketAmount;
		this.ticketDescription = ticketDescription;
		this.ticketStatus = ticketStatus;
		this.employeeId = employeeId;
		this.ticketTypeId = ticketTypeId;
		
	}
	

	public int getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}


	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "\nTicket [ticketId=" + ticketId + ", ticketAmount=" + ticketAmount + ", ticketDescription="
				+ ticketDescription + ", ticketStatus=" + ticketStatus + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", ticketType=" + ticketType + "]";
	}
	
	
	
	
	
}
