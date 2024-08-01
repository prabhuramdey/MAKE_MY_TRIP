package in.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.jsp.request.Passenger;
import in.jsp.response.Ticket;
import in.jsp.service.MakeMyTripService;

@Controller
public class MakeMyTripController {

	@Autowired
	private MakeMyTripService service;
	
	
	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("passenger", new Passenger());
		return "index";
	}
	
	
	@PostMapping("/book-ticket")
	public String bookTicket( @ModelAttribute("passenger") Passenger passenger,Model model) {
		Ticket ticket = service.bookTicket(passenger);
		model.addAttribute("msg", "Your Ticket Booker With ID: "+ticket.getTicketNumber());
		return "index";
	}
	
	
	@GetMapping("/ticket")
	public String getTicketForm(Model model ) {
		model.addAttribute("ticket", new Ticket());
		return "ticket-form";
	}
	
	
	@GetMapping("/get-ticket")
	public String getTicket(@RequestParam Integer ticketNum, Model model) {
		Ticket ticketByNum = service.getTicketByNum(ticketNum);
		model.addAttribute("ticket", ticketByNum);
		return "ticket-form";
	}
}
