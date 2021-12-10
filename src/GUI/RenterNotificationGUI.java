package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import Controller.LoginController;
import Controller.MailController;
import Controller.RegisteredRenterController;
import Controller.ViewPropertiesController;
import Entity.SearchCriteria;
import Entity.User;

public class RenterNotificationGUI extends GUIWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//view
	//Container container = getContentPane();
	JTable table = new JTable();
	JButton selectButton = new JButton("Select Property");
	JButton searchButton = new JButton("Search");
	JButton addSearchButton = new JButton("Add Search Criteria");
	
	String[] houseTypes = {"Any", "Apartment", "Attached house", "House", "Townhouse"};
	String[] cityQuadrant = {"Any", "SW", "NW", "NE", "SE"};
	
	JLabel typeLabel = new JLabel("Type:");
	JLabel bedLabel = new JLabel("Number of bedrooms:");
	JLabel bathLabel = new JLabel("Number of bathrooms:");
	JLabel furnishedLabel = new JLabel("Furnished:");
	JLabel quadrantLabel = new JLabel("City Quadrant:");
	
	JComboBox<String> typeList = new JComboBox<String>(houseTypes);
	JComboBox<String> quadrantList = new JComboBox<String>(cityQuadrant);
	JTextField bedTF = new JTextField();
	JTextField bathTF = new JTextField();
	JCheckBox furnished = new JCheckBox("");
	
	
	public RenterNotificationGUI(int x, int y) {
		super();
		setTitle("Properties");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
    	
		setCompProperty();
        addComponentsToContainer();
        addActionEvent();
	}
	
	public RenterNotificationGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
	
	public RenterNotificationGUI(GUIWindow prev) {
    	this(800, 800);
    	this.prev = prev;
    }
	
	public void setTableModel(String[] columnNames, Object[][] data) {
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
		table.setModel(tableModel);
	}

	private void setCompProperty() {
		setTableModel(null, null);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
 
    public void addComponentsToContainer() {
    	JPanel contentPane = new JPanel();
    	SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
    	setContentPane(contentPane);
    	//mainPanel.add(Box.createHorizontalStrut(5));
    	
    	//search
    	JPanel searchPanel = new JPanel(new FlowLayout());
    	//searchPanel.setMaximumSize(new Dimension(getWidth(), 100));
    	searchPanel.add(typeLabel);
    	searchPanel.add(typeList);
    	searchPanel.add(bedLabel);
    	bedTF.setColumns(3);
    	searchPanel.add(bedTF);
    	bathTF.setColumns(3);
    	searchPanel.add(bathLabel);
    	searchPanel.add(bathTF);
    	searchPanel.add(furnishedLabel);
    	searchPanel.add(furnished);
    	searchPanel.add(quadrantLabel);
    	searchPanel.add(quadrantList);
    	searchPanel.add(searchButton);
    	searchPanel.add(addSearchButton);
    	
    	searchPanel.setMaximumSize(new Dimension(getWidth(), 75));
    	searchPanel.setPreferredSize(new Dimension(getWidth(), 75));
    	
    	contentPane.add(searchPanel);
    	layout.putConstraint(SpringLayout.WEST, searchPanel, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, searchPanel, 0, SpringLayout.NORTH, contentPane);
    	
    	//table of properties
    	JScrollPane scrollPane = new JScrollPane(table);
    	scrollPane.setMaximumSize(new Dimension(getWidth(), (int)(getHeight()*.4f)));
    	scrollPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*.4f)));
    	table.setFillsViewportHeight(true);
    	
    	contentPane.add(scrollPane);
    	layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, searchPanel);
    	
    	//buttons
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.add(selectButton);
    	buttonPanel.add(previousButton);
    	
    	contentPane.add(buttonPanel);
    	layout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, buttonPanel, 10, SpringLayout.SOUTH, scrollPane);
    }
 
    public void addActionEvent() {
        selectButton.addActionListener(this);
        previousButton.addActionListener(this);
        searchButton.addActionListener(this);
        addSearchButton.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectButton) {
			int rowIn;
			if((rowIn = table.getSelectedRow()) >= 0) {
				User landlord = ((ViewPropertiesController)controller).getLandlord(rowIn), user = ((ViewPropertiesController)controller).getUser();
				if(user == null) {
					JOptionPane.showMessageDialog(this, "Please Register to Contact Property Owner");
				}
				else {
					MailController mCon = new MailController(new MailGUI(this), user, landlord);
					setVisible(false);
				}
			}
		}
		if(e.getSource() == addSearchButton) {
			if(typeList.getSelectedItem().equals("Any") || quadrantList.getSelectedItem().equals("Any")) {
				JOptionPane.showMessageDialog(this, "Invalid search criteria");
				return;
			}
			Integer numBeds = null, numBaths = null;
			try {
				numBeds = Integer.valueOf(bedTF.getText());
				numBaths = Integer.valueOf(bathTF.getText());
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Invalid search criteria");
				return;
			}
			((RegisteredRenterController)prev.getController()).addSearchCriteria(String.valueOf(typeList.getSelectedItem()), numBeds, numBaths, 
					Boolean.valueOf(furnished.isSelected()), String.valueOf(quadrantList.getSelectedItem()));
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
		else if(e.getSource() == searchButton) {
			Integer numBeds = null, numBaths = null;
			try {
				numBeds = Integer.valueOf(bedTF.getText());
			}
			catch(Exception ex) {
			}
			try {
				numBaths = Integer.valueOf(bathTF.getText());
			}
			catch(Exception ex) {	
			}
			((ViewPropertiesController)controller).filterModel(String.valueOf(typeList.getSelectedItem()), numBeds, numBaths, 
																Boolean.valueOf(furnished.isSelected()), String.valueOf(quadrantList.getSelectedItem()));
		}
		
	}

}
