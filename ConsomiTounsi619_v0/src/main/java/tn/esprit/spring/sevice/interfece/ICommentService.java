package tn.esprit.spring.sevice.interfece;

import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Comment_evaluation;
import tn.esprit.spring.entity.Subject_evaluation;


public interface ICommentService {
	
	
	Comment addComment(Comment com,Long user_id,Long sub_id);
	
	 List<Comment> list(Long sub_id);
	 
	 List<Comment> mylist(Long sub_id, Long user_id);
	 
	
	 void deleteComment(long id) ;
	  Comment updateComment(Long id,String mot);
	  Comment_evaluation addEv(Comment_evaluation e, Long id);
	  
	  List<Comment> Bestcomments();
	
	
	
	

}
