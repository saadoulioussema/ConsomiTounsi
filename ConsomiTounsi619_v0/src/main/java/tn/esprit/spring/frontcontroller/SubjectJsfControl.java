package tn.esprit.spring.frontcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.RateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Comment_evaluation;
import tn.esprit.spring.entity.Recherche;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.entity.Subject_evaluation;
import tn.esprit.spring.sevice.impl.CommentService;
import tn.esprit.spring.sevice.interfece.IRechercheService;
import tn.esprit.spring.sevice.interfece.ISubjectService;



@Scope(value = "session")
@Controller(value = "subjectController")
@ELBeanName(value = "subjectController")
@Join(path = "/welcomea", to = "/welcomea.jsf")
public class SubjectJsfControl{

	@Autowired
	ISubjectService isubjectService;
	
	@Autowired
	IRechercheService rechercheService;
	@Autowired
	UserController userJsfControl;
	@Autowired
	CommentService commentService ;
	
	
	
	
	private String typesearched;
	private String type;
	private String title;
	private String description;
	private Date creation_date;
	private long subjectIdToBeUpdated;
	private List<Subject> subjects;
	private List<Subject> list ;
	private List<Subject> list1 ;
	private List<Subject> list2 ;
	private List<Comment> mylist ;
	private List<Comment> allcomm ;
	private List<Comment> pertcomm ;
	private List<Subject> subevaluated = Collections.EMPTY_LIST ;
	
	
	private long subidselected ;
	private List<Comment> comments;
	private List<Subject_evaluation> ratings;
	///comment///
	private String mot;
	private long commentIdToBeUpdated;
	//comment evaluation 
	

	private String LImageURL = "/resources/images/L.png";
	private String DImageURL = "/resources/images/D.jpg";
	private String HImageURL = "/resources/images/H.jpg";
	private String SImageURL = "/resources/images/S.jpg";
	private String MImageURL = "/resources/images/M.png";
	private String RImageURL = "/resources/images/R.jpg";


	
	//rating///
	private int rating1;   

	
	
	
	//getters and setters 
	
	
	public String getType() {
		return type;
	}
	
	



	public String getRImageURL() {
		return RImageURL;
	}





	public void setRImageURL(String rImageURL) {
		RImageURL = rImageURL;
	}





	public String getLImageURL() {
		return LImageURL;
	}

	public void setLImageURL(String lImageURL) {
		LImageURL = lImageURL;
	}

	public String getDImageURL() {
		return DImageURL;
	}

	public void setDImageURL(String dImageURL) {
		DImageURL = dImageURL;
	}

	public String getHImageURL() {
		return HImageURL;
	}

	public void setHImageURL(String hImageURL) {
		HImageURL = hImageURL;
	}

	public String getSImageURL() {
		return SImageURL;
	}

	public void setSImageURL(String sImageURL) {
		SImageURL = sImageURL;
	}

	public String getMImageURL() {
		return MImageURL;
	}

	public void setMImageURL(String mImageURL) {
		MImageURL = mImageURL;
	}

	public int getRating1() {
		return rating1;
	}
	public void setRating1(int rating1) {
		this.rating1 = rating1;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
	public List<Subject_evaluation> getRatings() {
		return ratings;
	}
	public void setRatings(List<Subject_evaluation> ratings) {
		this.ratings = ratings;
	}
	
	
	public String getTypesearched() {
		return typesearched;
	}
	public void setTypesearched(String typesearched) {
		this.typesearched = typesearched;
	}
	public long getSubjectIdToBeUpdated() {
		return subjectIdToBeUpdated;
	}
	public void setSubjectIdToBeUpdated(long subjectIdToBeUpdated) {
		this.subjectIdToBeUpdated = subjectIdToBeUpdated;
	}
	public SubjectJsfControl( String type, String title, String description,
			Date creation_date, List<Comment> comments, List<Subject_evaluation> ratings) {
		super();
		
		this.type = type;
		this.title = title;
		this.description = description;
		this.creation_date = creation_date;
		this.comments = comments;
		this.ratings = ratings;
	}
	public SubjectJsfControl() {
		super();
	}
	
	
	public long getCommentIdToBeUpdated() {
		return commentIdToBeUpdated;
	}
	public void setCommentIdToBeUpdated(long commentIdToBeUpdated) {
		this.commentIdToBeUpdated = commentIdToBeUpdated;
	}
	public List<Comment> getComments() {
		
		return comments;
	}
	public List<Comment> getLMylist() {
		return mylist;
	}
	public void setMylist(List<Comment> mylist) {
		this.mylist = mylist;
	}

	


	public String getMot() {
		return mot;
	}
	public void setMot(String mot) {
		this.mot = mot;
	}
	public long getSubidselected() {
		return subidselected;
	}
	public void setSubidselected(long subidselected) {
		this.subidselected = subidselected;
	}
	public ISubjectService getIsubjectService() {
		return isubjectService;
	}
	public void setIsubjectService(ISubjectService isubjectService) {
		this.isubjectService = isubjectService;
	}
	/**************************************************************************************************
	  *****************                  CRUD Subject                    ********************
	 ***************************************************************************************************/
	public void handleException (Throwable exception){
	    String message="";
	    if (exception instanceof ApplicationError){
	        message = ""+ exception.getMessage();
	    }
	    
	    
	    FacesMessage facesMessage = new FacesMessage(message);
	    FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
	}	
	

public void ajouterSubject(){
Subject s = new Subject(type, title, description, creation_date) ;
Subject subexists = isubjectService.test(s.getType(), s.getDescription());


if (subexists != null) {
	try{	
	throw new ApplicationError("This subject exists already ");
    } catch (Throwable t){
        handleException(t);
    }
    }
          
else {
	isubjectService.addSubject(s);
	 
}
}


public List<Subject> getSubjects() {
	subjects = isubjectService.myy();
	return subjects;
}

public void deleteSubject(long subjectId)
{
	isubjectService.deleteSubject(subjectId);
}

public void modifier(Subject subject)
{
this.setSubjectIdToBeUpdated(subject.getId());
this.setTitle(subject.getTitle());
this.setType(subject.getType());
this.setDescription(subject.getDescription());
this.setCreation_date(subject.getCreation_date());

}

public void mettreAjourSubject(){
	Subject e = new Subject(subjectIdToBeUpdated,type, title, description, creation_date) ;

	isubjectService.addSubject(e);
}

/**************************sub à la une**********************************************/
	
public List<Subject> getList() {
	 list = isubjectService.sub();
return list;
}
/**************************Sub Interested**********************************************/
	
public List<Subject> getList1() {
	
	String max = rechercheService.extractt(userJsfControl.getAuthenticatedUser().getId());
	 list1 = isubjectService.findbyType(max);
	 if (list1.size()==0) {
		 try{	
				throw new ApplicationError("Do a little search ;) ");
			    } catch (Throwable t){
			        handleException(t);
			    }
	 }
	 
return list1;

}	
/****************save my search + return subjects researched*************Recherche********************/	

public List<Subject> getList2() {
	
	Recherche r = new Recherche(typesearched);
	 
	  list2 = isubjectService.findbyType(typesearched);
   
	  if(list2.size() != 0) {
    		rechercheService.addSearch(r,userJsfControl.getAuthenticatedUser());
    	
	  }
	  typesearched=null;
	  return list2 ;
    
	 
	  }
/**************************************************************************************************
 *****************      CRUD COMMENTS      ********************
***************************************************************************************************/
	
public List<Comment> showlist(long subjectId) {
 mylist = commentService.list(subjectId);
comments=mylist;
if (mylist.size()==0) {
	
	try{	
		throw new ApplicationError("There's no comment related to this subject ! ");
	    } catch (Throwable t){
	        handleException(t);
	    }
}
return mylist ;
}


//////////////////Dictionnaire mots interdits///////////////////////

public void addComment(long subjectId) {
	Comment u = new Comment(mot);
	String msg = commentService.addComment(u,userJsfControl.getAuthenticatedUser().getId(),subjectId);

	try{	
		throw new ApplicationError(msg);
	    } catch (Throwable t){
	        handleException(t);
	    }
	
    }

public void deleteComment( long commentId) {
	commentService.deleteComment(commentId);

}
////all my comments////
public List<Comment> getAllcomm() {
allcomm = commentService.mylist(userJsfControl.getAuthenticatedUser().getId());
return allcomm ;

}

public void modifiercomm(Comment comment)
{
this.setCommentIdToBeUpdated(comment.getId());
this.setMot(comment.getMot());

}

public void mettreAjourcomment(){
	Comment e = new Comment(commentIdToBeUpdated, mot);
long id = commentService.findbyid(commentIdToBeUpdated).getSubject().getId();
	commentService.addComment(e,userJsfControl.getAuthenticatedUser().getId(),id);
}


/*************************************subject Rating***************************************************/
	
public void Rate(long subjectId) {
	isubjectService.addrate(rating1, subjectId);
	if(true) {
	try{	
		throw new ApplicationError("You rated:" + rating1);
	    } catch (Throwable t){
	        handleException(t);
	    }
	}
   }

public int maxrate(long id) {

	return isubjectService.maxrate(id) ; 
	
	
}

public int minrate(long id) {
	
	return isubjectService.minrate(id) ; 
	
	
}
	

public List<Subject> getSubevaluated() {
	subevaluated = isubjectService.Subevaluated();
	if (subevaluated.size()==0) {
		
		try{	
			throw new ApplicationError("There's no comment related to this subject ! ");
		    } catch (Throwable t){
		        handleException(t);
		    }
	
	
}
	return subevaluated;
}


public void setSubevaluated(List<Subject> subevaluated) {
	this.subevaluated = subevaluated;
}





/*************************************comment evaluation***************************************************/
	
//Like : 
public void addLevaluation(Long commentId) {
	Comment_evaluation u = new Comment_evaluation();

	u.setL(1);
	commentService.addEv(u, commentId);
	if(true) {
		try{	
			throw new ApplicationError("You gived a like !");
		    } catch (Throwable t){
		        handleException(t);
		    }
	}
    }

//Dislike:
public void addDevaluation(long commentId) {
	Comment_evaluation v = new Comment_evaluation();

	v.setD(1);
	commentService.addEv(v, commentId);
	if(true) {
		try{	
			throw new ApplicationError("You gived a dislike !");
		    } catch (Throwable t){
		        handleException(t);
		    }
	}
  }

//Happy : 
public void addHevaluation(long commentId) {
	Comment_evaluation u1 = new Comment_evaluation();

	u1.setH(1);
	commentService.addEv(u1, commentId);
	if(true) {
		try{	
			throw new ApplicationError("evaluation saved : Happy ");
		    } catch (Throwable t){
		        handleException(t);
		    }
	}
  }
//Sad : 
public void addSevaluation(long commentId) {
	Comment_evaluation v1 = new Comment_evaluation();

	v1.setS(1);
	commentService.addEv(v1, commentId);
	if(true) {
		try{	
			throw new ApplicationError("evaluation saved : Sad ");
		    } catch (Throwable t){
		        handleException(t);
		    }
	}
  }
//Medium : 
public void addMevaluation(long commentId) {
	Comment_evaluation v2 = new Comment_evaluation();

	v2.setM(1);
	commentService.addEv(v2, commentId);
	if(true) {
		try{	
			throw new ApplicationError("evaluation saved : Medium ");
		    } catch (Throwable t){
		        handleException(t);
		    }
	}
  }
  
/*************************************get evaluations***************************************************/


public String getLike(long commentId) {
	Comment_evaluation c = commentService.Evatuation(commentId) ;
if (c == null) {
	
	return "0";
	}
else {
	return String.valueOf(c.getL()) ;
			 
}
  }

public String getDislike(long commentId) {
	
	Comment_evaluation c = commentService.Evatuation(commentId) ;
if (c == null) {
	
	return "0";
	}
else {
	return String.valueOf(c.getD()) ;
			 
}
  }
public String getHappy(long commentId) {
	
	Comment_evaluation c = commentService.Evatuation(commentId) ;
if (c == null) {
	
	return "0";
	}
else {
	return String.valueOf(c.getH()) ;
			 
}
  }
public String getSad(long commentId) {
	
	Comment_evaluation c = commentService.Evatuation(commentId) ;
if (c == null) {
	
	return "0";
	}
else {
	return String.valueOf(c.getS()) ;
			 
}
  }
public String getMedium(long commentId) {
	
	Comment_evaluation c = commentService.Evatuation(commentId) ;
if (c == null) {
	
	return "0";
	}
else {
	return String.valueOf(c.getM()) ;
			 
}
  }


/*************************************comments most pertinent ***************************************************/
	
public List<Comment> getpertcomm() {
pertcomm = commentService.Bestcomments();
return pertcomm ;

}



/**************************redirection*********************************************/
public String ToSubMan() {
	String navigateTo = "null";
	
	navigateTo = "/pages/forum/admin/gererSujet.xhtml?faces-redirect=true";
	return navigateTo;
}
public String ToSub() {
	String navigateTo = "null";
	
	navigateTo = "/welcomec.xhtml?faces-redirect=true";
	return navigateTo;
}
public String ToRech() {
	String navigateTo = "null";
	
	navigateTo = "/recherche.xhtml?faces-redirect=true";
	return navigateTo;
}
public String ToLaUne() {
	String navigateTo = "null";
	
	navigateTo = "/ALaUne.xhtml?faces-redirect=true";
	return navigateTo;
}
public String ToInterest() {
	String navigateTo = "null";
	
	navigateTo = "/findInterested.xhtml?faces-redirect=true";
	return navigateTo;
}
public String ToAll() {
	String navigateTo = "null";
	
	navigateTo = "/allSubjects.xhtml?faces-redirect=true";
	return navigateTo;
}
public String MyComm() {
	String navigateTo = "null";
	
	navigateTo = "/myComments.xhtml?faces-redirect=true";
	return navigateTo;
}

public String PertComm() {
	String navigateTo = "null";
	
	navigateTo = "/pertinentComments.xhtml?faces-redirect=true";
	return navigateTo;
}
public String ToRatings() {
	String navigateTo = "null";
	
	navigateTo = "/ratings.xhtml?faces-redirect=true";
	return navigateTo;
}














}
