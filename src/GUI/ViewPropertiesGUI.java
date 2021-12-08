package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import Controller.LoginController;
import Controller.MailController;
import Controller.ViewPropertiesController;
import Entity.User;

public class ViewPropertiesGUI extends GUIWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//view
	//Container container = getContentPane();
	JTable table = new JTable();
	JButton selectButton = new JButton("Select Property");
	JButton searchButton = new JButton("Search");
	
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
	
	
	public ViewPropertiesGUI(int x, int y) {
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
	
	public ViewPropertiesGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
	
	public ViewPropertiesGUI(GUIWindow prev) {
    	this(800, 600);
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
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	setContentPane(mainPanel);
    	mainPanel.add(Box.createHorizontalStrut(5));
    	
    	//search
    	JPanel searchPanel = new JPanel(new FlowLayout());
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
    	mainPanel.add(searchPanel);
    	
    	//table of properties
    	JScrollPane scrollPane = new JScrollPane(table);
    	scrollPane.setMaximumSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	scrollPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	table.setFillsViewportHeight(true);
    	
    	mainPanel.add(scrollPane);
    	
    	//buttons
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.add(selectButton);
    	buttonPanel.add(previousButton);
    	mainPanel.add(buttonPanel);
    }
 
    public void addActionEvent() {
        selectButton.addActionListener(this);
        previousButton.addActionListener(this);
        searchButton.addActionListener(this);
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
