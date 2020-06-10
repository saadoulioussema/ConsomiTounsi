package tn.esprit.spring.sevice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Comment_evaluation;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.Comment_evaluationRepository;
import tn.esprit.spring.repository.DictionaryRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.ICommentService;


@Service
public class CommentService implements ICommentService {
	public String msg;
	@Autowired
	private CommentRepository var;

	@Autowired
	private UserRepository var1;

	@Autowired
	private SubjectService var2;

	@Autowired
	private Comment_evaluationRepository var3;

	@Autowired
	private DictionaryRepository var4;

	////////////////////// add a comment/////////Dictionnaire mots interdits/////////////////////

	@Override
	public String addComment(Comment com,Long user_id,Long sub_id) {
	
		User u = var1.findById(user_id).get();
		
		Subject s = var2.findbyid(sub_id);
		
		com.setUser(u);
		com.setSubject(s);
		
		
		
		
		 if(com.getMot().equals("")) {
				
				msg= "you can't insert an empty comment" ;
				
			}
		 else {
		
			 //dictionary//
		List<String> mydictionary = var4.dictionaryList();
		ArrayList<String> listOfStrings = new ArrayList<>(mydictionary.size());
		listOfStrings.addAll(mydictionary);
		//liste des mots du commentaire
		String[] words = com.getMot().split("\\s+");
			for (int i = 0; i < words.length; i++) {
			    
			words[i] = words[i].replaceAll("[^\\w]", "");
				
			
			}
			boolean found = false;
			
			for (String element:words ) {
				for(String elt:listOfStrings) {	
					
					if ( (elt.trim()).equals(element)) {
				        found = true;
				        msg= "you can't insert this word : " + element ;
					}
					}}
	 if(!found) {	
			
			var.save(com); 
	 		msg= "add successful ";
			 } }
		
	return msg ;
	
	}

	@Override
	public List<Comment> list(Long sub_id) {

		Subject s = var2.findbyid(sub_id);
		return var.list(s);

	}

	@Override
	public void deleteComment(long id) {

		var.deleteById(id);

	}

	@Override
	public List<Comment> mylist(Long user_id) {

		User u = var1.findById(user_id).get();

		List<Comment> l = var.mylist(u);
		return l;

	}
	/// modify comment ////

	@Override
	public Comment updateComment(Long id, String mot) {

		Comment com1 = var.findById(id).get();
		com1.setMot(mot);

		var.save(com1);
		return com1;

	}

	/////// evaluation/////////
	
	@Override
@Transactional
public void addEv(Comment_evaluation e, Long id) {

		Comment c = var.findById(id).get();
		
		List<Comment> my = var3.evsave();

		if (my.contains(c)) {
			Comment_evaluation v = var3.findev(c);
			v.setL(v.getL() + e.getL());
			v.setD(v.getD() + e.getD());
			v.setH(v.getH() + e.getH());
			v.setS(v.getS() + e.getS());
			v.setM(v.getM() + e.getM());
			var3.save(v);

		
		}else {

			e.setComment(c);
			var3.save(e);
		}

	}

	/////////////////// list get evaluation by comment id//////////////////
	public Comment_evaluation Evatuation(Long id) {

		Comment c = var.findById(id).get();
		return  var3.findev(c);
		

	}

	/////////////////// comments + pertinents/////////////////////
	@Override
	public List<Comment> Bestcomments() {
		List<Comment> list1 = var3.myfind(var3.best1(), var3.best2());
		return list1;
	}

	@Override
	public Comment findbyid(long id) {

		return var.findById(id).get();

	}

}
