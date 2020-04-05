package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;

public interface IEventService {
	
	Event addEvent(Event event);
	List<Event> eventsLists();
	Event updateEvent(Long id, Event event);
	void deleteEvent(Long id) ;
	Event findbyId(Long id);
	Event findEventByName(String name);
	List<Event> filterEvent(EventCategory category);
//	void addParticipation(Long uid, Long eid);
}
