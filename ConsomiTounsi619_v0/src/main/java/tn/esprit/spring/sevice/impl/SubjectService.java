package tn.esprit.spring.sevice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.entity.Subject_evaluation;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.repository.Subject_evaluationRepository;
import tn.esprit.spring.sevice.interfece.ISubjectService;






@Service
public class SubjectService implements  ISubjectService{
	
@Autowired
private SubjectRepository var;

@Autowired
private CommentRepository var1;

@Autowired
private Subject_evaluationRepository var2;



private static final Logger logger = LogManager.getLogger(SubjectService.class);
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
public List<Subject> sub(){
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


/////////subject sans interaction//////////

@Override
public List<Long> notcommented() {
	 List<Long> mylist = var.list1() ; //1
	 Double a = (double) 10;
	 List<Long> mylist1 = var.subs(a);  //1 2 3
	for(Long i : mylist) {
		
		if(mylist1.contains(i)) {
			mylist1.remove(i);
			
		}
	}
	
 return mylist1 ;
 
	  
}

/////////////supp auto sub sans interaction///////////
//@Scheduled(cron = "* * * * * ?")
@Override
 public void autodeleteSubject() {
	
	for(Long i : notcommented()) {
		
		var.deleteById(i);
		
	}
	
	
}
/////////////////////subject rating /////////////////////
public void addrate(int value,long id) {
	
	Subject s = var.findById(id).get();
	Subject_evaluation e = new Subject_evaluation();
	e.setValue(value);
	e.setsubject(s);
	var2.save(e);
	
	
}
/////////////////////affichage subject rating /////////////////////	
@Override
public int maxrate(long id) {
Subject s = var.findById(id).get();
return var2.maxrate(s);


}
@Override
public int minrate(long id) {
Subject s = var.findById(id).get();
return var2.minrate(s);


}

@Override
public List<Subject> Subevaluated() {
	return var2.Listsubev();


}



	
		
}
	

		
		
		
		
	
	
	


