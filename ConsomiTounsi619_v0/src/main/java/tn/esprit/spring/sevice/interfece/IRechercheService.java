package tn.esprit.spring.sevice.interfece;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Recherche;

public interface IRechercheService {
	
	Recherche addSearch(Recherche rech,Long user_id);
	//Integer extractt();
	//Long id_user
	String extractt(Long id_user) ;

}
