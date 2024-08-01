package in.jsp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import in.jsp.request.Passenger;
import in.jsp.response.Ticket;

@Service
public class MakeMyTripServiceImpl implements MakeMyTripService {

	
	private String BOOK_TICKET_URL="http://13.232.253.164:8080/ticket"; 
	private String GET_TICKET_URL="http://13.232.253.164:8080/ticket/{ticketNum}";
	
	// USING REST TEMPLATE
//	@Override
//	public Ticket bookTicket(Passenger passenger) {
//		RestTemplate rt = new RestTemplate(); 
//		ResponseEntity<Ticket> respEntity =  
//		rt.postForEntity(BOOK_TICKET_URL, passenger, Ticket.class); 
//		Ticket ticket = respEntity.getBody(); 
//		return ticket;
//	}
//
//	@Override
//	public Ticket getTicketByNum(Integer ticketNumber) {
//		RestTemplate rt = new RestTemplate();  
//		ResponseEntity<Ticket> respEntity =  
//		rt.getForEntity(GET_TICKET_URL, Ticket.class, ticketNumber); 
//		Ticket ticket = respEntity.getBody();  
//		return ticket;
//	}

	
	
	//USING WEB-CLIENT
	
	@Override 
	 public Ticket bookTicket(Passenger passenger) {  
	  // get the instance of webclient (impl class) 
	  WebClient webClient = WebClient.create();   
	  // send POST request with passenger data  
	  //and map response to Ticket Object 
	  Ticket ticket = webClient.post() 
	          .uri(BOOK_TICKET_URL) 
	          .bodyValue(passenger) 
	          .retrieve() 
	          .bodyToMono(Ticket.class) 
	          .block(); 
	   
	  return ticket; } 
	 
	 @Override 
	 public Ticket getTicketByNum(Integer ticketNumber) {  
	  // get the instance of webclient (impl class) 
	  WebClient webClient = WebClient.create();   
	  // send get request and map response to Ticket Object   
	  Ticket ticket = webClient.get() 
	     .uri(GET_TICKET_URL, ticketNumber) 
	     .retrieve() 
	     .bodyToMono(Ticket.class) 
	     .block(); // sync call        
	  return ticket;    }
	
	
}
