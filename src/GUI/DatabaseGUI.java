package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Controller.LoginController;
import Database.DatabaseConnectivity;

public class DatabaseGUI extends GUIWindow{
    JTable propertiesTable;
    JTable rentersTable;
    JTable landlordsTable;

    JButton backButton;

    public DatabaseGUI(GUIWindow prev) {
        super();
        this.prev = prev;

        Object[] propCols = {"PropertyID", "Type", "Address", "Bedrooms", "Bathrooms", "Furnished", "Quadrant", "Landlord", "Date registered", "Date rented", "Status"};
        propertiesTable = new JTable(DatabaseConnectivity.getPropertiesTable(), propCols);
        Object[] userCols = {"Username", "Password", "Email", "Type"};
        rentersTable = new JTable(DatabaseConnectivity.getRentersTable(), userCols);
        landlordsTable = new JTable(DatabaseConnectivity.getLandlordsTable(), userCols);
        
        backButton = new JButton("Back");

        setTitle("Database summary");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(1920, 600);
        addComponentsToContainer();
        addActionEvent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
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
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        this.setContentPane(contentPane);

        JPanel tablesPane = new JPanel();
        SpringLayout layout = new SpringLayout();
		tablesPane.setLayout(layout);

        JScrollPane propsPane = new JScrollPane(propertiesTable);
        propsPane.setMaximumSize(new Dimension((int)(getWidth()*0.6f), (int)(getHeight()*.8f)));
    	propsPane.setPreferredSize(new Dimension((int)(getWidth()*0.6f), (int)(getHeight()*.8f)));
    	propertiesTable.setFillsViewportHeight(true);
    	tablesPane.add(propsPane);
    	layout.putConstraint(SpringLayout.WEST, propsPane, 0, SpringLayout.WEST, tablesPane);
        layout.putConstraint(SpringLayout.NORTH, propsPane, 0, SpringLayout.NORTH, tablesPane);

        JScrollPane rentersPane = new JScrollPane(rentersTable);
        rentersPane.setMaximumSize(new Dimension((int)(getWidth()*0.4f), (int)(getHeight()*.4f)));
    	rentersPane.setPreferredSize(new Dimension((int)(getWidth()*0.4f), (int)(getHeight()*.4f)));
    	rentersTable.setFillsViewportHeight(true);
    	tablesPane.add(rentersPane);
    	layout.putConstraint(SpringLayout.WEST, rentersPane, 0, SpringLayout.EAST, propsPane);
        layout.putConstraint(SpringLayout.NORTH, rentersPane, 0, SpringLayout.NORTH, tablesPane);
        
        JScrollPane landlordsPane = new JScrollPane(landlordsTable);
        landlordsPane.setMaximumSize(new Dimension((int)(getWidth()*0.4f), (int)(getHeight()*.4f)));
    	landlordsPane.setPreferredSize(new Dimension((int)(getWidth()*0.4f), (int)(getHeight()*.4f)));
    	landlordsTable.setFillsViewportHeight(true);
    	tablesPane.add(landlordsPane);
    	layout.putConstraint(SpringLayout.WEST, landlordsPane, 0, SpringLayout.EAST, propsPane);
        layout.putConstraint(SpringLayout.NORTH, landlordsPane, 0, SpringLayout.SOUTH, rentersPane);

        JPanel buttonsPane = new JPanel();
        buttonsPane.add(backButton);

        contentPane.add(tablesPane);
        contentPane.add(buttonsPane);
    }

    @Override
    public void addActionEvent() {
        backButton.addActionListener(this);
    }
}
