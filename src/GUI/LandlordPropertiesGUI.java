package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.LandlordPropertiesController;
import Controller.LoginController;
import Controller.ViewPropertiesController;
import Database.DatabaseConnectivity;
import Entity.ListOfUsers;

public class LandlordPropertiesGUI extends GUIWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//updating property state
	String[] states = {"registered", "active", "rented", "cancelled", "suspended"};
	
	JLabel feeLabel = new JLabel();
	JTable properties = new JTable();
	JButton changeButton = new JButton("Change Property State");
	JComboBox<String> state = new JComboBox<String>(states);
	
	//registering a property
	JButton registerButton = new JButton("Register Property");
	
	String[] houseTypes = {"Apartment", "Attached house", "House", "Townhouse"};
	String[] cityQuadrant = {"SW", "NW", "NE", "SE"};
	
	JLabel typeLabel = new JLabel("Type:");
	JLabel addressLabel = new JLabel("Address:");
	JLabel bedLabel = new JLabel("Number of bedrooms:");
	JLabel bathLabel = new JLabel("Number of bathrooms:");
	JLabel furnishedLabel = new JLabel("Furnished:");
	JLabel quadrantLabel = new JLabel("City Quadrant:");
	
	JComboBox<String> typeList = new JComboBox<String>(houseTypes);
	JComboBox<String> quadrantList = new JComboBox<String>(cityQuadrant);
	JTextField addressTF = new JTextField("");
	JTextField bedTF = new JTextField("0");
	JTextField bathTF = new JTextField("0");
	JCheckBox furnished = new JCheckBox("");
	
	JPanel registerPanel = new JPanel(new FlowLayout());
	
	public LandlordPropertiesGUI(int x, int y) {
    	super();
    	setTitle("Properties");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
        addComponentsToContainer();
        addActionEvent();
        setTableModel(null);
        setFeeModel("");
 
    }
    
    public LandlordPropertiesGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
    
    public LandlordPropertiesGUI(GUIWindow prev) {
    	this(1000, 600, prev);
    }
    
    public void setTableModel(Object[][] data) {
    	String[] columnNames = { "Type", "Address", "Bedrooms", "Bathrooms", "Furnished", "City Quadrant", "Status" };
		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		properties.setModel(tableModel);
		properties.getTableHeader().setReorderingAllowed(false);
		
	}
    
    public void setFeeModel(String fee) {
    	feeLabel.setText(fee);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changeButton) {
			int rowSel =  properties.getSelectedRow();
			if(rowSel >= 0) {
				if(((LandlordPropertiesController)controller).changePropertyState(rowSel, (String)state.getSelectedItem())) {
					JOptionPane.showMessageDialog(this, "State successfully changed");
				}
				else {
					JOptionPane.showMessageDialog(this, "Property state could not be changed");
				}
			}
		}
		else if(e.getSource() == registerButton) {
			Integer numBeds = null, numBaths = null;
			try {
				numBeds = Integer.valueOf(bedTF.getText());
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Invalid number of beds");
				return;
			}
			try {
				numBaths = Integer.valueOf(bathTF.getText());
			}
			catch(Exception ex) {	
				JOptionPane.showMessageDialog(this, "Invalid number of baths");
				return;
			}
			if(((LandlordPropertiesController)controller).registerProperty(String.valueOf(typeList.getSelectedItem()), addressTF.getText(), numBeds, numBaths, 
																Boolean.valueOf(furnished.isSelected()), String.valueOf(quadrantList.getSelectedItem()))) {
				JOptionPane.showMessageDialog(this, "Property has been registered");
			}
			else {
				JOptionPane.showMessageDialog(this, "Property could not be registered");
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
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		//table
		JScrollPane scrollPane = new JScrollPane(properties);
    	scrollPane.setMaximumSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	scrollPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	properties.setFillsViewportHeight(true);
    	
    	contentPane.add(scrollPane);
    	layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, contentPane);
		
    	//register property
    	//JPanel registerPanel = new JPanel(new FlowLayout());
    	registerPanel.setPreferredSize(new Dimension(getWidth(), 100));
    	registerPanel.add(typeLabel);
    	registerPanel.add(typeList);
		registerPanel.add(addressLabel);
		registerPanel.add(addressTF);
		addressTF.setColumns(15);
    	registerPanel.add(bedLabel);
    	bedTF.setColumns(3);
    	registerPanel.add(bedTF);
    	bathTF.setColumns(3);
    	registerPanel.add(bathLabel);
    	registerPanel.add(bathTF);
    	registerPanel.add(furnishedLabel);
    	registerPanel.add(furnished);
    	registerPanel.add(quadrantLabel);
    	registerPanel.add(quadrantList);
    	registerPanel.add(registerButton);
    	
    	contentPane.add(registerPanel);
    	layout.putConstraint(SpringLayout.WEST, registerPanel, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, registerPanel, (int)scrollPane.getMaximumSize().getHeight()+10, SpringLayout.NORTH, scrollPane);
    	
    	
    	//change state
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.setPreferredSize(new Dimension(getWidth(), 150));
    	buttonPanel.add(feeLabel);
    	buttonPanel.add(state);
    	buttonPanel.add(changeButton);
    	buttonPanel.add(previousButton);

    	contentPane.add(buttonPanel);
    	layout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, buttonPanel, (int)(scrollPane.getMaximumSize().getHeight()+10+registerPanel.getPreferredSize().getHeight()) + 10, SpringLayout.NORTH, scrollPane);
		
	}
	
	public void removeRegisterPanel() {
		getContentPane().remove(registerPanel);
	}

	@Override
	public void addActionEvent() {
		previousButton.addActionListener(this);
		changeButton.addActionListener(this);
		registerButton.addActionListener(this);
		
	}
	
//	public static void main(String[] args) {
//	  //LoginGUI frame = new LoginGUI(360, 600);
//		DatabaseConnectivity.initializeConnection("root", "ensf480");
//		DatabaseConnectivity.updateAllEntities();
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					new LandlordPropertiesController(new LandlordPropertiesGUI(null), ListOfUsers.getInstance().getUser("landlord"));
//				}
//			});
//	  
//		}

}
