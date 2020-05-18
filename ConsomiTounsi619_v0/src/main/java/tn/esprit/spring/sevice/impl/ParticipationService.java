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
import tn.esprit.spring.repository.UserRepository;
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
	@Autowired
	UserRepository UR;

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
		java.util.Date date = new java.util.Date();
		
		List<Participation> pl = PR.Participations(ES.findbyId(eid));
		for(Participation participation : pl) {
			if((participation.getUser()).equals(u)) {
				return "you have already participated";
			}
		}
		
		if(ev.getPlacesNbr()>0 && u.getAccBalance()>ev.getTicketPrice()) {
//			if((ev.getDate().equals(date))&&(date.getHours()-Integer.valueOf(ev.getHour().substring(0, 2))<=2)) {
				p.setEvent(ev);
				p.setUser(u);
				p.setParticipationDate(dateFormat.format(date));
				p.setPrice(ev.getTicketPrice());
				ev.setPlacesNbr(ev.getPlacesNbr()-1);
				ev.setParticipantsNbr(ev.getParticipantsNbr()+1);
				ev.setCollAmount(ev.getCollAmount()+ev.getTicketPrice());
				u.setAccBalance(u.getAccBalance()-ev.getTicketPrice());	
				PR.save(p);
				ER.saveAndFlush(ev);
				UR.save(u);
				
			if((participationsList().size()<=ev.getNbrEarlyBirdTickets()) && (ev.isEarlyBirdOpt())) { //Early bird ticket // 1 sera modifié par nombre de tickets des EarlyBirdTicket
				float newPrice = (float) (ev.getTicketPrice()*(ev.getDiscountPercentage()/100));// pourcentage de réduction en early bird ticket
				p.setEvent(ev);
				p.setUser(u);
				p.setParticipationDate(dateFormat.format(date));
				p.setPrice(newPrice);
				ev.setPlacesNbr(ev.getPlacesNbr()-1);
				ev.setParticipantsNbr(ev.getParticipantsNbr()+1);
				ev.setCollAmount(ev.getCollAmount()+ev.getTicketPrice());
				u.setAccBalance(u.getAccBalance()-newPrice);	
				PR.save(p);
				ER.saveAndFlush(ev);
				UR.save(u);
				return "you are among the first 20 participants, discount by 20%";
			}
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
