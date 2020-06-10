package tn.esprit.spring.dto;

import tn.esprit.spring.config.MessageMode;
import tn.esprit.spring.entity.User;

public class Message {

	private User toUser;
	private User fromUser;
	private String text;
	private MessageMode messageMode;

	public Message() {}
	
	public Message(User toUser, User fromUser, String text, MessageMode messageMode) {
		super();
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.text = text;
		this.messageMode = messageMode;
	}

	public MessageMode getMessageMode() {
		return messageMode;
	}

	public void setMessageMode(MessageMode messageMode) {
		this.messageMode = messageMode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	
}
