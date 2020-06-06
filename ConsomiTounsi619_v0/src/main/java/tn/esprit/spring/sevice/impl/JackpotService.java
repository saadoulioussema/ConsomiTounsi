package tn.esprit.spring.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;
import tn.esprit.spring.repository.JackpotRepository;
import tn.esprit.spring.sevice.interfece.IJackpotService;

@Service
public class JackpotService implements IJackpotService {
	@Autowired
	JackpotRepository JR;
	
	@Override
	public Jackpot addJackpot(Jackpot jackpot) {
		Jackpot j = new Jackpot();
		j.setSum(0);
		return JR.save(j);
		
	}

	@Override
	public Jackpot findJackpot(Event event) {
		Jackpot jackpot = event.getJackpot();
		return jackpot;
	}

	

}
