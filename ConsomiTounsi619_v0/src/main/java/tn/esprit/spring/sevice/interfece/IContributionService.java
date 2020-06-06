package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.Contribution;
import tn.esprit.spring.entity.Event;

public interface IContributionService {
	public String Contribute(Long eid, float amount);
	public List<Contribution> contributionOfEvent(Event event);
	public List<Contribution> myContributionHistory();
}
