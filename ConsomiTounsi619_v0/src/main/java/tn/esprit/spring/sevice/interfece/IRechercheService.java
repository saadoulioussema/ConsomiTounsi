package tn.esprit.spring.sevice.interfece;

import tn.esprit.spring.entity.Recherche;

public interface IRechercheService {
	
	Recherche addSearch(Recherche rech,Long user_id);
	String extractt(Long id_user);

}
