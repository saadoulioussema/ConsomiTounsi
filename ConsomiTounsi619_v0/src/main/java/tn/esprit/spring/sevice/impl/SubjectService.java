package tn.esprit.spring.sevice.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.sevice.interfece.ISubjectService;



@Service
public class SubjectService implements  ISubjectService{
	
@Autowired
private SubjectRepository var;
@Override	
public Subject addSubject(Subject subject){
		var.save(subject) ;
		return subject;
		
	}

@Override
public Subject findbyid(long id){
	
		return var.findById(id).get() ;
	}

@Override
public List<Subject> myy(){
	
	
	
	return (List<Subject>)var.findAll();
	
	
		
	}

@Override	
public void deleteSubject(long id){
	
		var.deleteById(id); 
	
	}
@Override	
public Subject updateSubject(Subject subject2){
	
	Subject subject1 =var.findById(subject2.getId()).get(); 
	subject1.setType(subject2.getType());
	subject1.setDescription(subject2.getDescription());
	subject1.setCreation_date(subject2.getCreation_date());
		
		var.save(subject1);
		return subject1;
	
	}
@Override	
public Subject test(String type , String description){
	Subject u = var.findByTypeAndDescription(type, description);
		return u;
}

@Override	
public List<String> sub(){
	Double mydate = (double) 3;
	return var.sub(mydate);
	
	
}

@Override
public Subject listbytitle(String title){
	
	return var.findByTitle(title);
	
}
@Override
public List<Subject> findbyType(String type){
	
		return var.findByType(type);
	}




		
		
}
	

		
		
		
		
	
	
	


