package tn.esprit.spring.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.entity.Contribution;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.repository.ContributionRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.sevice.impl.ContributionService;
import tn.esprit.spring.sevice.impl.EventService;
import tn.esprit.spring.sevice.impl.FileStorageService;
import tn.esprit.spring.sevice.impl.NotificationService;
import tn.esprit.spring.sevice.impl.ParticipationService;

@RestController
public class EventRestController {
	@Autowired
	EventService ES;
	@Autowired
	EventRepository ER;
	@Autowired
	NotificationService NS;
	@Autowired
	ParticipationService PS;
	@Autowired
	ContributionService CS;
	@Autowired
	ParticipationRepository PR;
	@Autowired
	ContributionRepository CR;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	FileStorageService fileStorageService;
	
	/**********************************Admin**********************************/
	@PostMapping(value="/add-Event",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public void addEvent(@RequestParam(value = "evJson", required = true) String evJson,
						 @RequestParam(required = true, value ="file") MultipartFile file)
						throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
				.path(fileName).toUriString();
		
		Event ev = objectMapper.readValue(evJson, Event.class);
		ev.setPoster(fileDownloadUri);
		ES.addEvent(ev);
		
		NS.notifyAllUser(ev);
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
	
//	@GetMapping("/update-Event/{eid}")
//	@ResponseBody
//	public void updateEvent(@PathVariable Long eid) {
//	}
	
	@DeleteMapping("/delete-Event/{event-id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("event-id") Long eventID) {
		ES.refundUsers(eventID);//refund contributions & participations prices to its users
		ES.deleteEvent(eventID);
	}
	
	@GetMapping("/retrieve-all-Participations")
	public List<Participation> getParticipations(){
		return PS.participationsList();
	}
	
	@GetMapping("/ParticipantsOfEvent/{eid}")
	public List<Participation> getParticipationsOfEvent(@PathVariable Long eid){
		return PR.Participations(ES.findbyId(eid));
	}
	
	@GetMapping("/ContributorsOfEvent/{eid}")
	public List<Contribution> getContributorsOfEvent(@PathVariable Long eid){
		return CR.contributionOfEvent(ES.findbyId(eid));
	}
	/**********************************User**********************************/
	@PostMapping("/add-Contribution/{eid}/{amount}")
	@ResponseBody
	public void Contribute(@PathVariable("eid") Long eid,@PathVariable("amount") float amount) {
		CS.Contribute(eid, amount);
	}
	
	@GetMapping("/retrieve-my-Contributions")
	public List<Contribution> myContributionsHistory(){
		return CS.myContributionHistory();
	}
	
	@RequestMapping("/add-Participation/{eid}")
	@ResponseBody
	public String addParticipation(@PathVariable("eid") Long eid) {
		return PS.addParticipation(eid);
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
	
	@GetMapping("/bestEventsByViews")
	public Map<Long, Integer> bestEventsByViews(){
		return ES.bestEventsByViews();
		}
	
	@GetMapping("/retrieveBestEventsByViews")
	public List<String> displayBestEventsByViews(){
		return ES.displayBestEventsByViews();
		}
	
	@GetMapping("/retrieveBestEventsByParticipations")
	public List<String> displayBestEventsByParticipations(){
		return ES.displayBestEventsByParticipations();
		}
	
	@GetMapping("/retrieveBestEventsByCollects")
	public List<String> displayBestEventsByCollects(){
		return ES.displayBestEventsByCollects();
		}
}
