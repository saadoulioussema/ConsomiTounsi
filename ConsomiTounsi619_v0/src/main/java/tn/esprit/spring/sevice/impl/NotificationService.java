package tn.esprit.spring.sevice.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.controller.UserController;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.INotificationService;

@Service
public class NotificationService implements INotificationService{
	@Autowired
	UserServiceImpl US;
	@Autowired
	NotificationRepository NR;
	@Autowired
	UserRepository UR;

	/**********************************Admin**********************************/
	//Send Notifications to all users for contribution
	@Override
	public void notifyAllUser(Event ev) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		List<User> allUsers = UR.findAll();
		
		for(User u : allUsers) {
			Notification n = new Notification();
			n.setEvent(ev);
			n.setUser(u);
			n.setBody("Dear "+u.getLastName()+" "+u.getFirstName()+", we invite you to contribute by an amount of money to the event "+
					  ev.getName()+" for "+ev.getDescription()+".Thank you.");
			n.setDate(dateFormat.format(date));
			n.setStatus("Not Seen Yet");
			NR.save(n);
		}		
	}

	/**********************************User**********************************/
	//retrieve my own notifications
	@Override
	public List<Notification> myNotifications() {
		List<Notification> list = NR.myNotifications(UserController.USERCONNECTED);
		return list;
	}

}
