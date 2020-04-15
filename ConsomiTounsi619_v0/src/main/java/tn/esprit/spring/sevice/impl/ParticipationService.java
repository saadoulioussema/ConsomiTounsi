package tn.esprit.spring.sevice.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.controller.UserController;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.sevice.interfece.IParticipationService;

@Service
public class ParticipationService implements IParticipationService {
	@Autowired
	UserServiceImpl US;
	@Autowired
	EventService ES;
	@Autowired
	ParticipationRepository PR;
	@Autowired
	EventRepository ER;

	/**********************************Admin**********************************/
	//retrieve all participations
	@Override
	public List<Participation> participationsList() {
		List<Participation> list= PR.findAll();
		return list;
	}

	/**********************************User**********************************/

	//Participate to an event	
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

	//Retrieve my participations
	@Override
	public List<Participation> myParticipations() {
		List<Participation> list = PR.myParticipations(UserController.USERCONNECTED);
		return list;
	}
	
	

}
