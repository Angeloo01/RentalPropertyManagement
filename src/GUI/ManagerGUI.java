package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import Entity.ListOfUsers;
import Entity.User;

public class ManagerGUI extends GUIWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel feeCharge = new JLabel("Fee Amount");
	JLabel feePeriod = new JLabel("Fee Period (Days)");
	JTextField feeChargeTF = new JTextField();
	JTextField feePeriodTF = new JTextField();
	JButton feeButton = new JButton("Edit Fee");
	
	JButton summButton = new JButton("Request Summary");
	JButton dbButton = new JButton("Display Database");
	

	public ManagerGUI(int x, int y) {
		super();
		setTitle("Renter");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
    	
        addComponentsToContainer();
        addActionEvent();
	}
	
	public ManagerGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
	
	public ManagerGUI(GUIWindow prev) {
    	this(300, 400);
    	this.prev = prev;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComponentsToContainer() {
		JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	setContentPane(mainPanel);
    	
    	Dimension panelSize = new Dimension(getWidth(), 40);
    	
    	JPanel feePanel1 = new JPanel(new FlowLayout());
    	JPanel feePanel2 = new JPanel(new FlowLayout());
    	JPanel buttonPanel1 = new JPanel(new FlowLayout());
    	JPanel buttonPanel2 = new JPanel(new FlowLayout());
    	JPanel buttonPanel3 = new JPanel(new FlowLayout());
    	
    	feePanel1.setMaximumSize(panelSize);
    	feePanel2.setMaximumSize(panelSize);
    	buttonPanel1.setMaximumSize(panelSize);
    	buttonPanel2.setMaximumSize(panelSize);
    	buttonPanel3.setMaximumSize(panelSize);
    	
    	mainPanel.add(Box.createVerticalStrut(25));
    	
    	feePanel1.add(feeCharge);
    	feePanel1.add(Box.createHorizontalStrut(29));
    	feeChargeTF.setColumns(10);
    	feePanel1.add(feeChargeTF);
    	mainPanel.add(feePanel1);
    	
    	feePanel2.add(feePeriod);
    	feePeriodTF.setColumns(10);
    	feePanel2.add(feePeriodTF);
    	mainPanel.add(feePanel2);
    	
    	//mainPanel.add(Box.createVerticalStrut(5));
    	buttonPanel1.add(feeButton);
    	mainPanel.add(buttonPanel1);
    	
    	mainPanel.add(Box.createVerticalStrut(10));
    	buttonPanel2.add(summButton);
    	mainPanel.add(buttonPanel2);
    	
    	mainPanel.add(Box.createVerticalStrut(10));
    	buttonPanel3.add(dbButton);
    	mainPanel.add(buttonPanel3);
    	

    	

	}

	@Override
	public void addActionEvent() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
        //LoginGUI frame = new LoginGUI(360, 600);
    	ListOfUsers users = ListOfUsers.getInstance();
    	users.add(new User("admin", "admin", 0));
    	users.add(new User("user", "user", 2));
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			ManagerGUI login = new ManagerGUI(null);
    		}
    	});
        
    }

}
