package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Comment_evaluation;
import tn.esprit.spring.entity.Recherche;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.sevice.impl.UserServiceImpl;
import tn.esprit.spring.sevice.interfece.ICommentService;
import tn.esprit.spring.sevice.interfece.IRechercheService;
import tn.esprit.spring.sevice.interfece.ISubjectService;
import tn.esprit.spring.sevice.interfece.IUserService;



@RestController
public class ForumController {
	
	
	
	@Autowired
	UserServiceImpl UserService;
	
	@Autowired
	ISubjectService subjectService;
	
	@Autowired
	IRechercheService rechercheService;
	
	@Autowired
	ICommentService commentService;
	


////////afficher sujets à la une//////////

@GetMapping("/listsubject")
@ResponseBody
public List<Subject> getdate() {
	List<Subject> list = subjectService.sub();
return list;
}

/////////////////sujects adéquats au profil/////////////////////

@GetMapping("/findinterested")
@ResponseBody
public Response findinterested() {
	
	String max = rechercheService.extractt(UserController.USERCONNECTED.getId());

List<Subject> list = subjectService.findbyType(max);


if (list.size()==0) {
	return Response.status(Status.NOT_FOUND).entity( "Do a little search ;) ").build();

                   
}
else{
	return Response.status(Status.OK).entity(list).build();

	}
}

//////////////////save my search + return subjects researched/////////////////////
@GetMapping("/search/{type}")
@ResponseBody
public Response addSearch(@PathVariable("type") String type) {
	
	
	Recherche r = new Recherche(type);
	 
	 List<Subject> list = subjectService.findbyType(type);
	 
	 
	 
    if (list.size()==0) {
    	return Response.status(Status.NOT_FOUND).entity( "There is no subject with the type provided !").build();
                       
    }
    else {
    	
rechercheService.addSearch(r, UserService.findbyid(UserController.USERCONNECTED.getId()) );
return Response.status(Status.OK).entity(list).build();
    }
	 
	 
}
//////CRUD SUBJECT//////
///////list of all subjects///////////
@GetMapping("/subjects")
@ResponseBody
public List<Subject> myy() {
List<Subject> l = subjectService.myy();
return l ;
}

@PutMapping("/modify-subject")
@ResponseBody
public Subject updateUser(@RequestBody Subject s) {
return subjectService.updateSubject(s);
}





//////pour ADMIN/////test sujets redondants/////
@PostMapping("/addSubject")
@ResponseBody
public Response addSubject(@RequestBody Subject u) {
	Subject subexists = subjectService.test(u.getType(), u.getDescription());
    if (subexists != null) {
    	return Response.status(Status.NOT_FOUND).entity( "There is already a subject exists with these informations").build();
                       
    }
    else {
    	 subjectService.addSubject(u);
    	 return Response.status(Status.OK).entity("add successful").build();
    }
}

@DeleteMapping("/delete-subject/{subject-id}")
@ResponseBody
public void deleteSubject(@PathVariable("subject-id") long subjectId) {
	subjectService.deleteSubject(subjectId);
}



////CRUD COMMERNT //  AVEC CONDITION (mots interdits includes empty word)////

@PostMapping("/addComment/{subjectId}")
@ResponseBody
public Response addComment(@RequestBody Comment u,@PathVariable("subjectId") Long subjectId) {
	
	String msg = commentService.addComment(u,UserController.USERCONNECTED.getId(),subjectId);
return Response.status(Status.OK).entity(msg).build();

    }

//all comments 
@GetMapping("/ListComment/{subjectId}")
@ResponseBody
public Response list(@PathVariable("subjectId") Long subjectId) {
List<Comment> l = commentService.list(subjectId);

if (l.size()==0) {
	return Response.status(Status.NOT_FOUND).entity("There's no comment related to this subject !").build();

	}
else {
	return Response.status(Status.OK).entity(l).build();

}
}
//all userconnected comments ajouter une exception 
@GetMapping("/myComments")
@ResponseBody
public List<Comment> mylist() {
List<Comment> l = commentService.mylist(UserController.USERCONNECTED.getId());
return l ;

}

@PutMapping("/modify-comment/{commentId}")
@ResponseBody
public Comment updateComment(@RequestBody String mot,@PathVariable("commentId") Long id) {
return commentService.updateComment(id, mot);
}


@DeleteMapping("/delete-comment/{commentId}")
@ResponseBody
public void deleteComment(@PathVariable("commentId") long commentId) {
	commentService.deleteComment(commentId);
}


///////////////////////subject evaluation//////////////////////
/*
@GetMapping("/rate/{subjectId}")
@ResponseBody
public Response Rate(@PathVariable("subjectId") Long subjectId) {
	subjectService.addrate(rate1, subjectId);
	
return Response.status(Status.OK).entity("add successful").build();

    }  

*/

///////////////////comment evaluation///////////////////

@PostMapping("/evaluate/{commentId}")
@ResponseBody
public Response addevaluation(@RequestBody Comment_evaluation u,@PathVariable("commentId") Long commentId) {
	
	commentService.addEv(u, commentId);
return Response.status(Status.OK).entity("add successful").build();

    }

	
	
////////////// commentaires + pertinents /////////////////// 	
	
@GetMapping("/BestComments")
@ResponseBody
public List<Comment> Best() {

	
	return commentService.Bestcomments();

}








}
