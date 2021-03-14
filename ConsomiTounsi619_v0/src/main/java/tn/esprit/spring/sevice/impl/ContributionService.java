package tn.esprit.spring.sevice.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.controller.UserController;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.ContributionRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.entity.Contribution;
import tn.esprit.spring.sevice.interfece.IContributionService;

@Service
public class ContributionService implements IContributionService {
	@Autowired
	EventService ES;
	@Autowired
	UserServiceImpl US;
	@Autowired
	JackpotService JS;
	@Autowired
	UserRepository UR;
	@Autowired
	JackpotRepository JR;
	@Autowired
	ContributionRepository CR;
	@Autowired
	EventRepository ER;
	
	/**********************************User**********************************/
	
	//Add contribution to a jackpot an event
	@Override
	public String Contribute(Long eid, float amount) {
		Contribution c = new Contribution();
		Event ev = ES.findbyId(eid);
		User u = US.findbyid(UserController.USERCONNECTED.getId());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		if(u.getAccBalance()>=amount) {
			u.setAccBalance(u.getAccBalance()-amount);
			Jackpot j = JS.findJackpot(ev);
			j.setSum(j.getSum()+amount);
			ev.setCollAmount(ev.getCollAmount()+amount);
			c.setAmount(amount);
			c.setContributionDate(dateFormat.format(date));
			c.setEvent(ev);
			c.setUser(u);
			UR.save(u);
			JR.save(j);
			ER.save(ev);
			CR.save(c);
			return "Contribution has been added with success";
		} else{
			return "Sorry, your account balance is insufficient !! ";
		}
	}
	
	@Override
	public List<Contribution> contributionOfEvent(Event event) {
		List<Contribution> list = CR.contributionOfEvent(event);
		return list;
	}
	
	@Override
	public List<Contribution> myContributionHistory() {
		List<Contribution> list = CR.contributionOfUser(UserController.USERCONNECTED);
		return list;
	}
	
}
