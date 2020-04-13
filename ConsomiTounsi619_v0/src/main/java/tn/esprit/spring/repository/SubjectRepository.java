package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Subject;
@Repository
public interface SubjectRepository extends CrudRepository<Subject,Long> {
	
	 Subject findByTypeAndDescription(String type,String description);
	 @Query("select title from Subject s where CURRENT_DATE - s.creation_date <=:mydate")
	 public List<String> sub(@Param("mydate") Double mydate);
	 Subject findByTitle(String title);
	 
	 List<Subject> findByType(String type);
	
	 /////////supp auto subject//////////
	 @Query("select s.id from Subject s where CURRENT_DATE - s.creation_date >=:mydate ")
	 public List<Long> subs(@Param("mydate") Double mydate);
	 
	
		 
	 
	 

	
}



