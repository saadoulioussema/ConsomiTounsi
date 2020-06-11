package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.Part;

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

@Scope(value = "session")
@Controller(value = "EventController")
@ELBeanName(value = "EventController")
@Join(path = "/event", to = "/event.jsf")
public class EventController {
	
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
	
	@Autowired
	FileStorageService fileStorageService;

	private Part file;
	@Enumerated(EnumType.STRING)
	private EventCategory category;
	private String name;
	private String description;
	private float goal;
	private int placesNbr;
	private boolean earlyBirdOpt;
	private int nbrEarlyBirdTickets;
	private int discountPercentage;
	private float ticketPrice;
	private Date date;
	private Date hour;
	private String location;
	private String poster;
	private String folder = "./src\\main\\webapp\\uploads\\";
	
	private List<Event> events;
	private List<Event> upevents;
	private Long id;
	private Event e;
	private Long updatedEventId;
	private List<Participation> participants;
	private List<Contribution> contributors;
	private List<Contribution> myContrib;
	private List<Participation> myParticipations;
	private List<Notification> myNotif;
	private float amount;
	private List<Event> filteredEventList = new ArrayList<>();
	private EventCategory th = EventCategory.Theatre;
	private EventCategory ms = EventCategory.Music;
	private EventCategory fm = EventCategory.Film;

//---------------------------------------------ADMIN METHODS------------------------------------------------------
	public String addEvent() {
		try (InputStream input = file.getInputStream()) {
			String fileName = file.getSubmittedFileName();
	        Files.copy(input, new File(folder, fileName).toPath());
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
				.path(file.getSubmittedFileName()).toUriString();
		poster = fileDownloadUri;
		
		Event ev = new Event(category,name,description,date,hour,poster,earlyBirdOpt,
							nbrEarlyBirdTickets,discountPercentage,ticketPrice,placesNbr,
							location,goal);
		ES.addEvent(ev);
		NS.notifyAllUser(ev);
		return "/allEventsAD.xhtml?faces-redirect=true";
	}
	
	public List<Event> getEvents() {
		events = ES.eventsLists();
		return events;
	}
	
	public Event getEventById(Long eid) {	
		e = ES.findbyId(id);
		System.out.println("Nom est :"+e.getName());
		return e;
	}
	
	public void deleteEvent(Long eid) {
		ES.refundUsers(eid);//refund contributions & participations prices to its users
		ES.deleteEvent(eid);
	}
	
	public List<Event> getAllEvents(){
		events = ES.eventsLists();
		return events;
	}
	
	public List<Event> findMusicEvents() {
		events = ES.filterEvent(EventCategory.Music);
		return events;
		}
	
	public List<Event> findTheatreEvents() {
		events = ES.filterEvent(EventCategory.Theatre);
		return events;
		}
	
	public List<Event> findFilmEvents() {
		events = ES.filterEvent(EventCategory.Film);
		return events;
		}
	
	public String update(Event event) {
		this.setUpdatedEventId(id);//event.getId()
		this.setCategory(event.getCategory());
		this.setName(event.getName());
		this.setDescription(event.getDescription());
		this.setDate(event.getDate());
		this.setHour(event.getHour());
		this.setLocation(event.getLocation());
		this.setPoster(event.getPoster());
		this.setEarlyBirdOpt(event.isEarlyBirdOpt());
		this.setNbrEarlyBirdTickets(event.getNbrEarlyBirdTickets());
		this.setDiscountPercentage(event.getDiscountPercentage());
		this.setTicketPrice(event.getTicketPrice());
		this.setPlacesNbr(event.getPlacesNbr());
		this.setGoal(event.getGoal());
		return "/UpdateEvent.xhtml?faces-redirect=true";
	}
	
	public String saveUpdates() {
		Event ev = new Event(updatedEventId,category,name,description,date,hour,poster,earlyBirdOpt,
							nbrEarlyBirdTickets,discountPercentage,ticketPrice,placesNbr,
							location,goal);
		ES.updateEvent(ev);
		System.out.println("updated");
		return "/allEventsAD.xhtml?faces-redirect=true";
	}
	
	public List<Participation> getParticipantsOfEvent(Long ide){
		participants = PR.Participations(ES.findbyId(id));
		return participants;
	}
	
	public List<Contribution> getContributorsOfEvent(Long ide){
		contributors = CR.contributionOfEvent(ES.findbyId(id));
		return contributors;
	}
	
	public List<Event> upcomingEvents() {
		upevents = ES.upcomingEvents();
		return upevents;
	}
	
	public List<String> displayBestEventsByParticipations(){
		return ES.displayBestEventsByParticipations();
		}
	
	public List<String> displayBestEventsByCollects(){
		return ES.displayBestEventsByCollects();
		}
//---------------------------------------------CLIENT METHODS------------------------------------------------------
	
	public String Contribute() {
		CS.Contribute(id, amount);
		return "/myContributions.xhtml?faces-redirect=true";
	}
	
	public String addParticipation(Long eid) {
		PS.addParticipation(id);
		return "/myParticipations.xhtml?faces-redirect=true";
	}
	
	public List<Contribution> myContributionsHistory(){
		myContrib = CS.myContributionHistory();
		return myContrib;
	}

	public List<Participation> getMyParticipations(){
		myParticipations = PS.myParticipations();
		return myParticipations;
	}
	
	public List<Notification> getMyNotifications(){
		myNotif = NS.myNotifications();
		return myNotif;
	}
//----------------------------------GETTERS & SETTERS-------------------------------------------------------	
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public EventService getES() {
		return ES;
	}

	public void setES(EventService eS) {
		ES = eS;
	}

	public EventRepository getER() {
		return ER;
	}

	public void setER(EventRepository eR) {
		ER = eR;
	}

	public NotificationService getNS() {
		return NS;
	}

	public void setNS(NotificationService nS) {
		NS = nS;
	}

	public ParticipationService getPS() {
		return PS;
	}

	public void setPS(ParticipationService pS) {
		PS = pS;
	}

	public ContributionService getCS() {
		return CS;
	}

	public void setCS(ContributionService cS) {
		CS = cS;
	}

	public ParticipationRepository getPR() {
		return PR;
	}

	public void setPR(ParticipationRepository pR) {
		PR = pR;
	}

	public FileStorageService getFileStorageService() {
		return fileStorageService;
	}

	public void setFileStorageService(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getGoal() {
		return goal;
	}

	public void setGoal(float goal) {
		this.goal = goal;
	}

	public int getPlacesNbr() {
		return placesNbr;
	}

	public void setPlacesNbr(int placesNbr) {
		this.placesNbr = placesNbr;
	}

	public boolean isEarlyBirdOpt() {
		return earlyBirdOpt;
	}

	public void setEarlyBirdOpt(boolean earlyBirdOpt) {
		this.earlyBirdOpt = earlyBirdOpt;
	}

	public int getNbrEarlyBirdTickets() {
		return nbrEarlyBirdTickets;
	}

	public void setNbrEarlyBirdTickets(int nbrEarlyBirdTickets) {
		this.nbrEarlyBirdTickets = nbrEarlyBirdTickets;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getE() {
		return e;
	}

	public void setE(Event e) {
		this.e = e;
	}

	public Long getUpdatedEventId() {
		return updatedEventId;
	}

	public void setUpdatedEventId(Long updatedEventId) {
		this.updatedEventId = updatedEventId;
	}

	public List<Participation> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participation> participants) {
		this.participants = participants;
	}

	public ContributionRepository getCR() {
		return CR;
	}

	public void setCR(ContributionRepository cR) {
		CR = cR;
	}

	public List<Contribution> getContributors() {
		return contributors;
	}

	public void setContributors(List<Contribution> contributors) {
		this.contributors = contributors;
	}

	public List<Contribution> getMyContrib() {
		return myContrib;
	}

	public void setMyContrib(List<Contribution> myContrib) {
		this.myContrib = myContrib;
	}

	public List<Notification> getMyNotif() {
		return myNotif;
	}

	public void setMyNotif(List<Notification> myNotif) {
		this.myNotif = myNotif;
	}

	public void setMyParticipations(List<Participation> myParticipations) {
		this.myParticipations = myParticipations;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public List<Event> getUpevents() {
		return upevents;
	}

	public void setUpevents(List<Event> upevents) {
		this.upevents = upevents;
	}

	public List<Event> getFilteredEventList() {
		return filteredEventList;
	}

	public void setFilteredEventList(List<Event> filteredEventList) {
		this.filteredEventList = filteredEventList;
	}

	public EventCategory getTh() {
		return th;
	}

	public void setTh(EventCategory th) {
		this.th = th;
	}

	public EventCategory getMs() {
		return ms;
	}

	public void setMs(EventCategory ms) {
		this.ms = ms;
	}

	public EventCategory getFm() {
		return fm;
	}

	public void setFm(EventCategory fm) {
		this.fm = fm;
	}


	
	
}
