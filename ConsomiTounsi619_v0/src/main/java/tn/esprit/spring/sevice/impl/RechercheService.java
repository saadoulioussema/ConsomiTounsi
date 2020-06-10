package tn.esprit.spring.sevice.impl;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

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
	
	//Long id_user
	@Override
	public String extractt(Long id_user){
		 
		Long max = var.lemax(id_user);
		
		String type = var.extract(max);
		 
		 
		 return type;
		
	}
	
	
	
	
	@Override	
	@Transactional
	public Recherche addSearch(Recherche rech,User u){
		
		//le type introduit :
		String t = rech.getType();
		//la recherche sur ce type :
		Recherche r = var.findByTypeAndUser(t, u);
		
		Long a = 1L;
		///si la recherche sur ce type n'existe pas : 
		if(r==null) {
			
			rech.setUser(u);
			rech.setNbr(a);
			var.save(rech);
		}
		else {
			
			//get nbr from recherche et incrémenter nbr 
			Long count = r.getNbr()+a;
			
			//modifier le recherche et enregistrer
			r.setNbr(count);
			rech=r;
			var.save(rech);
		}
	return rech;
			
		}

	
	

}
