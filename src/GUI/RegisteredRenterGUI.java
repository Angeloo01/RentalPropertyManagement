package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.LoginController;
import Controller.MailInboxController;
import Controller.RegisteredRenterController;
import Controller.ViewPropertiesController;
import Entity.ListOfProperty;
import Entity.PropertiesModel;
import Entity.Property;
import Entity.RegisteredRenter;
import Entity.User;

public class RegisteredRenterGUI extends GUIWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton inboxButton = new JButton("Inbox");
	JButton propertyButton = new JButton("View Properties");
	User user;//model
	JButton mailButton = new JButton("Email");
	
	public RegisteredRenterGUI(int x, int y) {
		super();
		setTitle("Renter");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
    	
		//setCompProperty();
    	setContainer();
        addComponentsToContainer();
        addActionEvent();
	}
	
	public RegisteredRenterGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
	
	public RegisteredRenterGUI(GUIWindow prev) {
    	this(300, 400);
    	this.prev = prev;
    }
	
	public RegisteredRenterGUI(GUIWindow prev, User user) {
    	this(prev);
    	this.user = user;
    }
	// setter for the user
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == propertyButton) {
			PropertiesModel pModel = new PropertiesModel(true, ListOfProperty.getInstance().getProperies());
	    	new ViewPropertiesController (new ViewPropertiesGUI(this), pModel, user);
	    	setVisible(false);
		}
		else if(e.getSource() == mailButton) {
			new MailInboxController(new MailInboxGUI(this), user);
			setVisible(false);
		}
		else if(e.getSource() == inboxButton) {
			
			ListOfProperty.getInstance().registerObserver(((RegisteredRenterController)controller).getInbox());
			PropertiesModel pModel = new PropertiesModel(true, (List<Property>)((RegisteredRenterController)controller).getMatchingProperties());
			//pModel.getData()[0][0] = "House";
			RenterNotificationGUI renter = new RenterNotificationGUI(this);
	    	new ViewPropertiesController (renter, pModel, user);
	    	((RegisteredRenterController)controller).setSearchModel(renter);
	    	setVisible(false);
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
		FlowLayout fl = new FlowLayout();
		Dimension dm = new Dimension(getWidth(), 50);
		
		JPanel row1 = new JPanel(fl), row2 = new JPanel(fl), row3 = new JPanel(fl), row4 = new JPanel(fl);
		row1.setMaximumSize(dm);
		row2.setMaximumSize(dm);
		row3.setMaximumSize(dm);
		row4.setMaximumSize(dm);
		
		row1.add(inboxButton);
		row2.add(propertyButton);
		row3.add(previousButton);
		row4.add(mailButton);
		
		container.add(row1);
		container.add(row2);
		container.add(row4);
		container.add(row3);
		
		
	}

	@Override
	public void addActionEvent() {
		inboxButton.addActionListener(this);
		propertyButton.addActionListener(this);
		previousButton.addActionListener(this);
		mailButton.addActionListener(this);
		
	}
	
	public void setContainer() {
		JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	setContentPane(mainPanel);
    	container = getContentPane();
    	
    	
	}

}
