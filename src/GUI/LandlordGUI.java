package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Controller.LandlordPropertiesController;
import Controller.LoginController;
import Controller.MailInboxController;
import Entity.ListOfUsers;
import Entity.User;


public class LandlordGUI extends GUIWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User landlord;
	JButton mailBut = new JButton("Email");
	JButton probBut = new JButton("View My Properties");

	public LandlordGUI(int x, int y) {
    	super();
    	setTitle("Landlord");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
        addComponentsToContainer();
        addActionEvent();
 
    }
    
    public LandlordGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
    
    public LandlordGUI(GUIWindow prev) {
    	this(300, 400, prev);
    }
    
    public LandlordGUI(GUIWindow prev, User landlord) {
    	this(prev);
    	this.landlord = landlord;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mailBut) {
			new MailInboxController(new MailInboxGUI(this), landlord);
			setVisible(false);
		}
		else if(e.getSource() == probBut) {
			new LandlordPropertiesController(new LandlordPropertiesGUI(this), landlord);
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
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		contentPane.add(mailBut);
		contentPane.add(probBut);
		contentPane.add(previousButton);
		
		int w = 150, h = 25;
		mailBut.setPreferredSize(new Dimension(w, h));
		probBut.setPreferredSize(mailBut.getPreferredSize());
		previousButton.setPreferredSize(mailBut.getPreferredSize());

		int xD = getWidth()/2 - w/2;
		layout.putConstraint(SpringLayout.WEST, mailBut, xD, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, mailBut, 5, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST, probBut, xD, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, probBut, 5+h, SpringLayout.NORTH, mailBut);
		layout.putConstraint(SpringLayout.WEST, previousButton, xD, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, previousButton, 5+h, SpringLayout.NORTH, probBut);
		

	}

	@Override
	public void addActionEvent() {
		mailBut.addActionListener(this);
		probBut.addActionListener(this);
		previousButton.addActionListener(this);

	}
	
//	public static void main(String[] args) {
//		  //LoginGUI frame = new LoginGUI(360, 600);
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						new LandlordGUI(null);
//					}
//				});
//		  
//			}

}
