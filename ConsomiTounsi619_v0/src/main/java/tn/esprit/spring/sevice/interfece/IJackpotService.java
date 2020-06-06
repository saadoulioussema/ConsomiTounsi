package tn.esprit.spring.sevice.interfece;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;

public interface IJackpotService {
	public Jackpot addJackpot(Jackpot jackpot);
	public Jackpot findJackpot(Event event);
	
}
