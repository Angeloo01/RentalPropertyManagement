package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Controller.*;
import Database.DatabaseConnectivity;
import Entity.User;

public class MailGUI extends GUIWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel subjectLabel = new JLabel("Subject: ");
	JLabel messageLabel = new JLabel("Message: ");
	JTextField subjectTF = new JTextField();
	JTextArea messageTA = new JTextArea(10, 10);
	JButton sendButton = new JButton("Send");

	public MailGUI(int x, int y) {
		super();
		setTitle("Mail");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
    	
        addComponentsToContainer();
        addActionEvent();
	}
	
	public MailGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
	
	public MailGUI(GUIWindow prev) {
    	this(600, 400);
    	this.prev = prev;
    }
	// setter for the subject
	public void setSubject(String sub) {
		subjectTF.setText(sub);
	}
	//setter for the message
	public void setMessage(String mes) {
		messageTA.setText(mes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			if(((MailController)controller).sendMessage("Subject: "+subjectTF.getText() + "\nMessage:" + messageTA.getText())) {
				JOptionPane.showMessageDialog(this, "Message Sent Successfully");
				if(prev == null) {
					LoginGUI frame = new LoginGUI(360, 600);
				    LoginController controller = new LoginController(frame);
				    frame.setController(controller);
				    dispose();
				}
				else {
					prev.setVisible(true);
					dispose();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Error in Sending Message");
			}
		}
		else if(e.getSource() == previousButton) {
			if(prev == null) {
				LoginGUI frame = new LoginGUI(360, 600);
			    LoginController controller = new LoginController(frame);
			    frame.setController(controller);
			    dispose();
			}
			else {
				prev.setVisible(true);
				dispose();
			}
		}

	}

	@Override
	public void addComponentsToContainer() {
		JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	setContentPane(mainPanel);
    	
    	JPanel subjectPanel = new JPanel(new FlowLayout());
    	subjectPanel.add(subjectLabel);
    	subjectTF.setColumns(40);
    	subjectPanel.add(subjectTF);
    	subjectPanel.setMaximumSize(new Dimension(getWidth(), 100));
    	mainPanel.add(subjectPanel);
    	
    	mainPanel.add(messageLabel);

    	JScrollPane scrollPane = new JScrollPane(messageTA); 
    	messageTA.setLineWrap(true);
    	messageTA.setEditable(true);
    	mainPanel.add(scrollPane);
    	
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.setMaximumSize(new Dimension(getWidth(), 100));
    	buttonPanel.add(sendButton);
    	buttonPanel.add(previousButton);
    	mainPanel.add(buttonPanel);

	}

	@Override
	public void addActionEvent() {
		previousButton.addActionListener(this);
		sendButton.addActionListener(this);

	}
}
