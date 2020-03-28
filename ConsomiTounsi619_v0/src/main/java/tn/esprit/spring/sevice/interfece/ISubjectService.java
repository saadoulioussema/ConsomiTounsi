package tn.esprit.spring.sevice.interfece;

import java.util.Date;
import java.util.List;


import tn.esprit.spring.entity.Subject;



public interface ISubjectService {
	 List<Subject> myy();
	 Subject addSubject(Subject user);
	 Subject findbyid(long id);
	 void deleteSubject(long id) ;
	 Subject updateSubject(Subject user2);
	 Subject test(String type , String description);
	  List<String> sub() ;
	  Subject listbytitle(String title);
	  List<Subject> findbyType(String type);
	 
	  

	  
}
