package tn.esprit.spring.sevice.interfece;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Recherche;
import tn.esprit.spring.entity.User;




public interface IRechercheService {
	
	Recherche addSearch(Recherche rech,User u);
	//Integer extractt();
	//Long id_user
	String extractt(Long id_user) ;

}
