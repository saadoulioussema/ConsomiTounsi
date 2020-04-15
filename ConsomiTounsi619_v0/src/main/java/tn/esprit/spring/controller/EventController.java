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
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.sevice.impl.NotificationService;
import tn.esprit.spring.sevice.impl.ParticipationService;
import tn.esprit.spring.sevice.interfece.IEventService;

@RestController
public class EventController {
	@Autowired
	IEventService ES;
	@Autowired
	EventRepository ER;
	@Autowired
	NotificationService NS;
	@Autowired
	ParticipationService PS;
	
	/**********************************Admin**********************************/
	@PostMapping("/add-Event")
	@ResponseBody
	public void addEvent(@RequestBody Event ev) {
		ES.addEvent(ev);
		NS.notifyAllUser(ev.getName(),ev.getEventGoal());//Manque l'objectif de l'event !! Ajouter l'attribut EventGoal !!
	}
	
	@GetMapping("/retrieve-all-Events")
	public List<Event> getEvents(){
		List<Event> list = ES.eventsLists();
		return list;
	}
	
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
	}
	
	@DeleteMapping("/delete-Event/{event-id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("event-id") Long eventID) {
		ES.deleteEvent(eventID);
	}
	
	@GetMapping("/retrieve-all-Participations")
	public List<Participation> getParticipations(){
		return PS.participationsList();
	}
	
	/**********************************User**********************************/
	@PostMapping("/add-Participation/{eid}")
	@ResponseBody
	public void addParticipation(@PathVariable("eid") Long eid) {
		PS.addParticipation(eid);
	}
	
	@GetMapping("/retrieve-my-Participations")
	public List<Participation> getMyParticipations(){
		return PS.myParticipations();
	}
	
	@GetMapping("/retrieve-my-Notifications")
	public List<Notification> getMyNotifications(){
		return NS.myNotifications();
	}
	
	@GetMapping("/retrieve-upcoming-Events")
	public List<Event> upcomingEvents() {
		return ES.upcomingEvents();
	}
	
	@GetMapping("/retrieve-passed-Events")
	public List<Event> passedEvents() {
		return ES.passedEvents();
	}
	
	
}
