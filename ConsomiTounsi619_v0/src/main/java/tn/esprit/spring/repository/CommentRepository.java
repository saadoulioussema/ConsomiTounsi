package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.entity.User;



public interface CommentRepository extends CrudRepository<Comment,Long>{
	//list of comments
	 @Query("select s from Comment s where s.subject=:subject")
	 public List<Comment> list(@Param("subject") Subject subject);
	 
	 
	 //list of user's comments of the subject
	 @Query("select s from Comment s where s.subject=:subject and s.user=:user")
	 public List<Comment> mylist(@Param("subject") Subject subject,@Param("user") User user);
	 
	

	 
	 
	 
	 
	 
	 

}
