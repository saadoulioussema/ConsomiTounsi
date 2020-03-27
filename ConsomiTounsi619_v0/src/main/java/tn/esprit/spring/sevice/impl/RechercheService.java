package tn.esprit.spring.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Recherche;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.RechercheRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.IRechercheService;


@Service
public class RechercheService implements IRechercheService{
	
	@Autowired
	private RechercheRepository var;
	
	@Autowired
	private UserRepository var1;
	
	
	@Override
	public String extractt(Long id_user){
		
		  List<String> list=var.extract(id_user);
		 String  v = list.get(0);
		 return v;
		
	}
	
	@Override	
	public Recherche addSearch(Recherche rech,Long user_id){
		
		User u = var1.findById(user_id).get();
		rech.setUser(u);
		var.save(rech);
		return rech;
			
		}

	
	

}
