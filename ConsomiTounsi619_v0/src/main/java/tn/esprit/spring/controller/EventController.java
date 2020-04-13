package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.sevice.interfece.IEventService;

@RestController
public class EventController {
	@Autowired
	IEventService ES;
	@Autowired
	EventRepository ER;
	private Event ev;
	
//	private EventCategory category;
//	private String name;
//	private int placesNbr;
//	private Date date;
//	private String hour;
//	private String location;
//	private String poster;
//	private int ticketPrice;
//	private int eventCost;
//	private boolean status;
//	private long eventIdToBeUpdated;
	/**********************************Admin**********************************/
	@PostMapping("/add-Event")
	@ResponseBody
	public Event addEvent(@RequestBody Event ev) {
		Event event = ES.addEvent(ev);
		return event;
	}
	
	@GetMapping("/retrieve-all-Events")
	public List<Event> getEvents(){
		List<Event> list = ES.eventsLists();
		return list;
	}
	
	//My Id
	@GetMapping("/myId")
	public Long getMyId() {
		return UserController.USERCONNECTED.getId();
		}
	
	@GetMapping("/retrieve-Event-ById/{id}")
	public Event getEventById(@PathVariable Long id) {
		return ES.findbyId(id);
		}
	
	@GetMapping("/retrieve-Event-ByName/{name}")
	public Event getEventByName(@PathVariable String name) {
		final Event ev = ES.findEventByName(name);
		return ev;
		}
	
	@GetMapping("/retrieve-Events-ByCategory/{category}")
	public List<Event> findEventByCategory(@PathVariable EventCategory category) {
		List<Event> list = ES.filterEvent(category);
		return list;
		}
	
	@GetMapping("/update-Event/{eid}")
	@ResponseBody
	public void updateEvent(@PathVariable Long eid/*,Event event*/) {
		ev = getEventById(eid);
		
//		Event event = new Event(eventIdToBeUpdated,category,name,placesNbr,date,hour,location,poster,ticketPrice,eventCost,status);
//		event = ES.findbyId(eid);
//		ev.setCategory(event.getCategory());
//		ev.setName(event.getName());
//		ev.setPlacesNbr(event.getPlacesNbr());
//		ev.setDate(event.getDate());
//		ev.setHour(event.getHour());
//		ev.setLocation(event.getLocation());
//		ev.setPoster(event.getPoster());
//		ev.setTicketPrice(event.getTicketPrice());
//		ev.setEventCost(event.getEventCost());
//		ev.setStatus(event.isStatus());
//		ev.setEventIdToBeUpdated(event.getId());
		//ES.updateEvent(event);
	}
//	public Response updateEmployeeByID(@PathParam(value="cin")String cin, Employe nvEmploye) {
//		for(int i=0;i<ListeEmploye.size();i++) {
//			if(cin.equals(ListeEmploye.get(i).getCin())) {
//				Employe empl = ListeEmploye.get(i);
//				ListeEmploye.remove(empl);
//				ListeEmploye.add(nvEmploye);}}}
	
	@DeleteMapping("/delete-Event/{event-id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("event-id") Long eventID) {
		ES.deleteEvent(eventID);
	}
	/**********************************User**********************************/
	@PostMapping("/add-Participation/{eid}")
	@ResponseBody
	public void addParticipation(@PathVariable("eid") Long eid) {
		ES.addParticipation(eid);
	}
	
}
