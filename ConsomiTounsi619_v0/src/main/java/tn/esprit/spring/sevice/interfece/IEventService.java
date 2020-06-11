package tn.esprit.spring.sevice.interfece;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;

public interface IEventService {
	
	public void addEvent(Event event);
	public List<Event> eventsLists();
	public void updateEvent(Event ev);
	public void deleteEvent(Long id) ;
	public Event findbyId(Long id);
	public Event findEventByName(String name);
	public List<Event> filterEvent(EventCategory category);
	public List<Event> upcomingEvents();
	public List<Event> passedEvents();
	public void refundUsers(Long eid);
	public Map<Long, Integer> bestEventsByViews();
	public List<String> displayBestEventsByViews();
	public List<String> displayBestEventsByParticipations();
	public List<String> displayBestEventsByCollects();
}
