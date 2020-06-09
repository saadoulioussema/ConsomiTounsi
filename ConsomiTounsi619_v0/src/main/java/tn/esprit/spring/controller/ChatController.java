package tn.esprit.spring.controller;

import java.util.List;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.esprit.spring.config.MessageMode;
import tn.esprit.spring.dto.Message;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.sevice.impl.UserServiceImpl;
import tn.esprit.spring.sevice.impl.WebSocketService;


@Scope ("view")
@Component (value = "chatController")
@ELBeanName(value = "chatController")
@Join(path = "/chat", to = "/principal/chat.jsf")
public class ChatController {

	private List<User> users;
	
	private String message;
	
	
	
	@Autowired
	UserServiceImpl userService;
	
	
	
	@Autowired
	tn.esprit.spring.frontcontroller.UserController loginController;
	
	User toUser;
	
	@Autowired
	WebSocketService wsService;
	
	public String sendMessage() throws Exception  {		
		 
		String [] wordsFromText = message.split(" ");	
		 
		
		     Message chatMessage = new Message(null, loginController.getAuthenticatedUser(), message, MessageMode.PUBLIC);
			 wsService.send("/topic/chat", chatMessage);
		 
		 
		 
	     message = "";
		 return message;	
	}

	public  List<User> refreshUsers() {
		return getUsers();
	}
	
	private List<User> findAllUsers() {
		return userService.mylist();
	}

	public List<User> getUsers() {
		users= userService.mylist();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public User getToUser() {
		return toUser;
	}


	public void setToUser(User toUser) {
		this.toUser = toUser;
	}


	
	
	
}
