package tn.esprit.spring.sevice.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Contribution;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.ContributionRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.IEventService;

@Service
public class EventService implements IEventService {
	@Autowired
	EventRepository ER;
	@Autowired
	JackpotRepository JR;
	@Autowired
	ParticipationRepository PR;
	@Autowired
	UserRepository UR;
	@Autowired
	ContributionRepository CR;
	@Autowired
	NotificationRepository NR;
	

	/**********************************Admin**********************************/
	@Override
	public void addEvent(Event event) {
		Jackpot j = new Jackpot();
		j.setSum(0);
		event.setJackpot(j);
		ER.save(event);
		JR.save(j);
		
	}

	@Override
	public List<Event> eventsLists() {
		return (List<Event>) ER.findAll();
	}

	@Override
	public void updateEvent(Event ev) {
		ER.save(ev);
	}

	@Override
	public void deleteEvent(Long id) {
		//delete event poster
		String post = ER.findById(id).get().getPoster();
		String filename = post.substring(30);
		File file = new File("./src\\main\\webapp\\uploads\\"+filename);
		if (file.delete()) {System.out.println("file deleted");}else {System.out.println("file not deleted");}
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

	@Override
	public List<Event> upcomingEvents() {
		List<Event> list= ER.upcomingEvents();
		return list;
	}

	@Override
	public List<Event> passedEvents() {
		List<Event> list= ER.passedEvents();
		return list;
	}

	@Override
	public void refundUsers(Long eid) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		Event ev = findbyId(eid);
		
		List<Participation> participationsOfEvent = PR.Participations(ev);
		
		for(Participation p : participationsOfEvent) {
			User u = p.getUser();
			float refundedAmount = p.getPrice();
			ev.setCollAmount(ev.getCollAmount()-refundedAmount);
			u.setAccBalance(u.getAccBalance()+refundedAmount);
			UR.save(u);
			PR.deleteById(p.getId());
			ER.save(ev);
			Notification n = new Notification();
			n.setUser(u);
			n.setBody("Dear "+u.getLastName()+" "+u.getFirstName()+""
					+ ",we regret to announce that the event "+ev.getName()+" you want to attend has been canceled for a some reasons."
					+ " That's why, we have refunded your ticket price. If there is a problem, do not hesitate to contact us."
					+ " Thank you.");
			n.setDate(dateFormat.format(date));
			n.setStatus("Not Seen Yet");
			NR.save(n);
				
		}	
		
		List<Contribution> contributionsOfEvent = CR.contributionOfEvent(ev);
		
		for(Contribution c : contributionsOfEvent) {
			User u = c.getUser();
			float refundedAmount = c.getAmount();
			ev.getJackpot().setSum(ev.getJackpot().getSum()-refundedAmount);
			ev.setCollAmount(ev.getCollAmount()-refundedAmount);
			u.setAccBalance(u.getAccBalance()+refundedAmount);
			Notification n = new Notification();
			n.setUser(u);
			n.setBody("Dear "+u.getLastName()+" "+u.getFirstName()+""
					+ ",we regret to announce that the event "+ev.getName()+" to which you contributed was canceled for several reasons."
					+ " That's why, we have refunded your contribution. If there is a problem, do not hesitate to contact us."
					+ " Thank you.");
			n.setDate(dateFormat.format(date));
			n.setStatus("Not Seen Yet");
			NR.save(n);
			UR.save(u);
			CR.deleteById(c.getId());
			ER.save(ev);
		}
	}

	@Override
	public Map<Long, Integer> bestEventsByViews() {
		
		Map<Long,Integer> h = new HashMap<>();
		List<Long> listId = new ArrayList<>();
		List<Integer> listViews= new ArrayList<>();
		List<Event> listEvent = ER.findAll();
		
		for (Event ev : listEvent) {
			listId.add(ev.getId());
			listViews.add(ev.getViews());
		}
		
		List<Integer> sortedList = new ArrayList<>(listViews);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Long ind = listId.get(listViews.indexOf(max));// prend nbre de vue et retourne id d'event corresspondant
			h.put(ind, max);
			System.out.println(ind +" "+ max);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
		}
		
		return h;
	}

	@Override
	public List<String> displayBestEventsByViews() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Long> listId = new ArrayList<>();
		List<Integer> listViews= new ArrayList<>();
		List<Event> listEvent = ER.findAll();
		
		for (Event ev : listEvent) {
			listId.add(ev.getId());
			listViews.add(ev.getViews());
		}
		
		List<Integer> sortedList = new ArrayList<>(listViews);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Long ind = listId.get(listViews.indexOf(max));// prend nbre de vue et retourne id d'event corresspondant
			s = (i+1)+"- Event: "+ER.findById(ind).get().getName()+" with "+max+" views ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
		}
		
		return list;
	}

	@Override
	public List<String> displayBestEventsByParticipations() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Long> listId = new ArrayList<>();
		List<Integer> lp= new ArrayList<>();//nbre de participants
		List<Event> listEvent = ER.findAll();
		
		for (Event ev : listEvent) {
			listId.add(ev.getId());
			lp.add(ev.getParticipantsNbr());
		}
		
		List<Integer> sortedList = new ArrayList<>(lp);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Long ind = listId.get(lp.indexOf(max));// prend nbre de participations et retourne id d'event corresspondant
			s =(i+1)+" - Event: "+ER.findById(ind).get().getName()+" with "+max+" participations ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lp.set(lp.indexOf(max), -1);
		}
		
		return list;
	}

	@Override
	public List<String> displayBestEventsByCollects() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Long> listId = new ArrayList<>();
		List<Integer> lc= new ArrayList<>();
		List<Event> listEvent = ER.findAll();
		
		for (Event ev : listEvent) {
			listId.add(ev.getId());
			lc.add((int) ev.getCollAmount());
		}
		
		List<Integer> sortedList = new ArrayList<>(lc);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Long ind = listId.get(lc.indexOf(max));// prend le collecte et retourne id d'event corresspondant
			s =(i+1)+" - Event: "+ER.findById(ind).get().getName()+" with "+max+"DT collected amount. ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lc.set(lc.indexOf(max), -1);
		}
		
		return list;
	}

}
