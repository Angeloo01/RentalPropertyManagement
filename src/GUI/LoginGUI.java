package GUI;
import java.awt.event.ActionEvent;
import javax.swing.*;

import Controller.LoginController;
import Controller.ManagerGUIController;
import Controller.RegisteredRenterController;
import Controller.ViewPropertiesController;
import Entity.ListOfProperty;
import Entity.ListOfUsers;
import Entity.PropertiesModel;
import Entity.User;

public class LoginGUI extends GUIWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel nameLabel = new JLabel("Rental Property Management System");
    JLabel userLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JButton guestButton = new JButton("Enter as Guest");
    JCheckBox showPassword = new JCheckBox("Show Password");
 
 
    public LoginGUI(int x, int y) {
    	super();
    	setTitle("Login");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
    
    public LoginGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
    
    public LoginGUI(GUIWindow prev) {
    	this(360, 600, prev);
    }
    
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	int xMid = getWidth()/2;
    	int width;
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        registerButton.setBounds(200, 300, 100, 30);
        guestButton.setBounds(75, 350, 200, 30);
        width = nameLabel.getFontMetrics(nameLabel.getFont()).stringWidth(nameLabel.getText());
        nameLabel.setBounds(xMid-width/2, 100, width, 30);
    }
 
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(registerButton);
        container.add(guestButton);
        container.add(nameLabel);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
        guestButton.addActionListener(this);
        passwordField.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of Login button
        if (e.getSource() == loginButton || e.getSource() == passwordField) {
            String userText;
            char[] pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getPassword();
            
            int type = ((LoginController)controller).checkCredentials(userText, pwdText);
            if(type == 0) {
            	new ManagerGUIController(new ManagerGUI(null));
            	dispose();
            }
            else if (type == 1) {
            	
            	LandlordGUI landlordGUI = new LandlordGUI(null, ListOfUsers.getInstance().getUser(userText));
            	dispose();
            } 
            else if (type == 2) {
            	
                new RegisteredRenterController(null, ListOfUsers.getInstance().getUser(userText));
            	dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
 
        }
        //Coding Part of Register button
        if (e.getSource() == registerButton) {
        	String userText = userTextField.getText();
            String pwdText = new String(passwordField.getPassword());
            if(userText.isEmpty() || pwdText.isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Username or password is empty");
        	}
            else if (((LoginController)controller).registerUser(userTextField.getText(), passwordField.getPassword())) {
                JOptionPane.showMessageDialog(this, "Registration Successful");
            }
        	else {
                //JOptionPane.showMessageDialog(this, "Username is taken");
            }
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
 
        }
        
        if(e.getSource() == guestButton) {
//        	String email = new String();
//            while(!email.contains("@"))
//            	email = JOptionPane.showInputDialog(this, "Enter your email:").toString();
//            if (ListOfUsers.getInstance().getUserFromEmail(email) != null) {
//				JOptionPane.showMessageDialog(this, "Email is taken");
//	            return;
//	        }
        	PropertiesModel pModel = new PropertiesModel(true, ListOfProperty.getInstance().getProperies());
        	new ViewPropertiesController (new ViewPropertiesGUI(null), pModel);
        	dispose();
        	
        }
    }
    
    

}
