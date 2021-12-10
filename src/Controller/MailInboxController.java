package Controller;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.DatabaseConnectivity;
import Entity.ListOfUsers;
import Entity.RegisteredRenter;
import Entity.User;
import GUI.GUIWindow;
import GUI.MailInboxGUI;

public class MailInboxController implements GUIController {

	GUIWindow view;
	User receiver;
	Object[][] inbox;
	
	private final Pattern M_PATTERN = Pattern.compile("To: (?<receiver>\\p{ASCII}*?)From: (?<sender>\\p{ASCII}*?)Subject: (?<subject>\\p{ASCII}*?)Message:(?<message>\\p{ASCII}*)");
	public MailInboxController(GUIWindow gui) {
		view = gui;
		view.setController(this);
		
	}
	
	public MailInboxController(GUIWindow gui, User receiver) {
		this(gui);
		this.receiver = receiver;
		buildInbox();
		((MailInboxGUI)view).setTableModel(inbox);
	}
	// builds the inbox with all previous conversations
	public void buildInbox() {
		String[] messages = DatabaseConnectivity.retrieveMail(receiver);
		if(messages == null) {
			inbox = null;
			return;
		}
		
		LinkedList<Object[]> list = new LinkedList<Object[]>();
		for(String msg : messages) {
			Matcher matcher = M_PATTERN.matcher(msg);
			if(matcher.find()) {
				list.add(new Object[] {matcher.group("sender"), matcher.group("subject"), matcher.group("message")});
			}
		}
		inbox = list.toArray(new Object[list.size()][]);
	}
	// getter for the receiver
	public User getReceiver() {
		return receiver;
	}
	
	public User getSender(int index) {
		String email = (String)inbox[index][0];
		email = email.replace("\n", "");
		email = email.replace("\r", "");
		return ListOfUsers.getInstance().getUserFromEmail(email);
	}
	// getter for the subject
	public String getSubject(int index) {
		return (String)inbox[index][1];
	}
	// getter for the message
	public String getMessage(int index) {
		return (String)inbox[index][2];
	}

}
