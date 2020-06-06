package tn.esprit.spring.sevice.interfece;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Comment_evaluation;






public interface ICommentService {
	
	
	String addComment(Comment com,Long user_id,Long sub_id) ;
	 List<Comment> list(Long sub_id);
	 List<Comment> mylist(Long user_id);
	 void deleteComment(long id) ;
	 Comment updateComment(Long id,String mot);
	 void addEv(Comment_evaluation e, Long id);
	 List<Comment> Bestcomments();
	 Comment findbyid(long id);
	 Comment_evaluation Evatuation(Long id);
	 
	

	 

}
