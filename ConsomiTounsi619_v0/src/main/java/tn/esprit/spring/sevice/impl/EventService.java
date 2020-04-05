package tn.esprit.spring.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.sevice.interfece.IEventService;

@Service
public class EventService implements IEventService {
	@Autowired
	EventRepository ER;
	@Autowired
	JackpotRepository JR;
	
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
	public Event updateEvent(Long id, Event event) {
		Event ev = ER.findById(id).get();
//		event.setId(ev.getId());
		ev.setCategory(event.getCategory());
		ev.setName(event.getName());
		ev.setPlacesNbr(event.getPlacesNbr());
		ev.setDate(event.getDate());
		ev.setHour(event.getHour());
		ev.setLocation(event.getLocation());
		ev.setPoster(event.getPoster());
		ev.setTicketPrice(event.getTicketPrice());
		ev.setEventCost(event.getEventCost());
		ev.setStatus(event.isStatus());
		ev.setJackpot(event.getJackpot());
		return ER.save(ev);
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

//	@Override
//	public void addParticipation(Long uid, Long eid) {
//		ER.participate(uid,eid);
//	}

}
