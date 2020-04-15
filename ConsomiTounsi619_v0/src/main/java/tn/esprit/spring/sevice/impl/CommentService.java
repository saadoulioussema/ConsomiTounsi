package tn.esprit.spring.sevice.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Comment_evaluation;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.Comment_evaluationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.ICommentService;


@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private CommentRepository var;
	
	@Autowired
	private UserRepository var1;
	
	@Autowired
	private SubjectService var2;
	
	@Autowired
	private Comment_evaluationRepository var3;
	
	@Override
	public Comment addComment(Comment com,Long user_id,Long sub_id) {
		
		User u = var1.findById(user_id).get();
		
		Subject s = var2.findbyid(sub_id);
		
		com.setUser(u);
		com.setSubject(s);
		
		var.save(com);
		return com ;
		
		
	}
	@Override
	public List<Comment> list(Long sub_id){
				
	Subject s = var2.findbyid(sub_id) ;
	List<Comment> l = var.list(s);
	return l ;
		
		
	}
	
	@Override	
	public void deleteComment(long id){
		
			var.deleteById(id); 
		
		}

	@Override
	public List<Comment> mylist(Long sub_id, Long user_id){
				
	Subject s = var2.findbyid(sub_id);
	User u = var1.findById(user_id).get();

	List<Comment> l = var.mylist(s, u);
	return l ;
		
		
	}
	///modify comment ////
	
	@Override	
	public Comment updateComment(Long id,String mot){
		
		Comment com1 =var.findById(id).get(); 
		com1.setMot(mot);
		
			var.save(com1);
			return com1;
		
		}
	
	
	
	///////evaluation/////////
	////initialiser e dans le controlleur >> view 
	@Override
	public Comment_evaluation addEv(Comment_evaluation e, Long id) {
		
		Comment c = var.findById(id).get();
		
		List<Comment> my = var3.evsave();
		if(my.contains(c)) {
			Comment_evaluation v = var3.findev(c);
			v.setL(v.getL()+e.getL());
			v.setD(v.getD()+e.getD());
			v.setH(v.getH()+e.getH());
			v.setS(v.getS()+e.getS());
			v.setM(v.getM()+e.getM());
			
			var3.save(v);
			return v ;
			
			
	}
		else {
			e.setComment(c);
			var3.save(e);
			return e;
			
			
		}
	}
	///////////////////comments + pertinents//////////////////////
		@Override
		public List<Comment> Bestcomments(){
			
			
			
			List<Comment> list1 = var3.myfind(var3.best1(), var3.best2()) ;
			
		    
			return list1;
			
			
			
			
		}
			
		
		


}
