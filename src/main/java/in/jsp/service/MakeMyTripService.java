package in.jsp.service;

import in.jsp.request.Passenger;
import in.jsp.response.Ticket;

public interface MakeMyTripService {

	public Ticket bookTicket(Passenger passenger);
	
	public Ticket getTicketByNum(Integer ticketNumber);

	
}
