package Controller;

import Database.DatabaseConnectivity;
import Entity.*;
import GUI.*;

public class MailController implements GUIController{

	GUIWindow view;
	User sender, receiver;
	public MailController(GUIWindow gui) {
		view = gui;
		view.setController(this);
	}
	
	public MailController(GUIWindow gui, User sender, User receiver) {
		view = gui;
		view.setController(this);
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public boolean sendMessage(String message) {
		message = "To: "+receiver.getEmail() +"\nFrom: "+sender.getEmail()+"\n"+message;
		return DatabaseConnectivity.sendMail(message, sender, receiver);
	}

}
