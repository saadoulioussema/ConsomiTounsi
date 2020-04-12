package tn.esprit.spring.sevice.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.controller.UserController;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.sevice.interfece.IEventService;

@Service
public class EventService implements IEventService {
	@Autowired
	EventRepository ER;
	@Autowired
	EventService ES;
	@Autowired
	JackpotRepository JR;
	@Autowired
	UserServiceImpl US;
	@Autowired
	ParticipationRepository PR;
	
	private Long eventIdToBeUpdated;
	private EventCategory category;
	private String name;
	private int placesNbr;
	private Date date;
	private String hour;
	private String location;
	private String poster;
	private int ticketPrice;
	private int eventCost;
	private boolean status;
	
	public Long getEventIdToBeUpdated() {
		return eventIdToBeUpdated;
	}

	public void setEventIdToBeUpdated(Long eventIdToBeUpdated) {
		this.eventIdToBeUpdated = eventIdToBeUpdated;
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

	public int getPlacesNbr() {
		return placesNbr;
	}

	public void setPlacesNbr(int placesNbr) {
		this.placesNbr = placesNbr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
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

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getEventCost() {
		return eventCost;
	}

	public void setEventCost(int eventCost) {
		this.eventCost = eventCost;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**********************************Admin**********************************/
	@Override
	public Event addEvent(Event event) {
		Event NewEvent = new Event();
		NewEvent.setCategory(event.getCategory());
		NewEvent.setName(event.getName());
		NewEvent.setPlacesNbr(event.getPlacesNbr());
		NewEvent.setDate(event.getDate());
		NewEvent.setHour(event.getHour());
		NewEvent.setLocation(event.getLocation());
		NewEvent.setPoster(event.getPoster());
		NewEvent.setTicketPrice(event.getTicketPrice());
		NewEvent.setEventCost(event.getEventCost());
		NewEvent.setStatus(false);
		Jackpot j = new Jackpot();
		j.setSum(0);
		JR.save(j);
		NewEvent.setJackpot(j);
		return ER.save(NewEvent);
	}

	@Override
	public List<Event> eventsLists() {
		return (List<Event>) ER.findAll();
	}

	@Override
	public void updateEvent(Event event) {
		ER.saveAndFlush(event);
	}

	@Override
	public void deleteEvent(Long id) {
		ER.deleteById(id);
	}

	@Override
	public Event findbyId(Long id) {
		return ER.findById(id).get();
	}

	@Override
	public Event findEventByName(String name) {
		return ER.findByName(name); 
	}

	@Override
	public List<Event> filterEvent(EventCategory category) {
		return ER.filterByCategory(category);
	}


	/**********************************User**********************************/

	@Override
	public String addParticipation(Long eid) {
		Participation p = new Participation();
		Event ev = ES.findbyId(eid);
		User u = US.findbyid(UserController.USERCONNECTED.getId());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime d = LocalDateTime.now();
		java.util.Date date = new java.util.Date();
		
		if(ev.getPlacesNbr()>0) {
		p.setEvent(ev);
		p.setUser(u);
		p.setParticipationDate(dateFormat.format(date));
		PR.save(p);
		ev.setPlacesNbr(ev.getPlacesNbr()-1);
		ER.saveAndFlush(ev);
		return "Participation successfully added. You're welcome.";
		}else {
			return "Sorry, there are no places available.";
		}
	
	}

}
