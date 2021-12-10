package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.*;

import Controller.LoginController;
import Entity.PropertiesModel;
import Entity.SummaryReport;

public class SummaryReportGUI extends GUIWindow {
    SummaryReport reportModel;
    PropertiesModel pModel;

    JLabel dateLabel;
    JLabel numListedPropsLabel;
    JLabel numRentedPropsLabel;
    JLabel numActiveListingsLabel;

    JTable rentedPropsTable;

    JButton backButton;

    SummaryReportGUI(GUIWindow prev, SummaryReport r) {
        super();
        this.prev = prev;
        this.reportModel = r;

        dateLabel = new JLabel("Showing summary report from " + reportModel.getStartDate() + " to " + reportModel.getEndDate());
        numListedPropsLabel = new JLabel("Properties listed: " + reportModel.getNumPropertiesListed());
        numRentedPropsLabel = new JLabel("Properties rented: " + reportModel.getNumPropertiesRented());
        numActiveListingsLabel = new JLabel("Active listings: " + reportModel.getNumActiveListings());

        this.pModel = new PropertiesModel(false, reportModel.getPropertiesRented());
        String[] colNames = {"Landlord", "Property ID", "Address"};
        LinkedList<Integer> columns = new LinkedList<Integer>();
        columns.push(7);
        columns.push(6);
        columns.push(1);
        Object[][] data = pModel.getColumns(columns);
        rentedPropsTable = new JTable(data, colNames);

        backButton = new JButton("Back");

    	setTitle("Summary Report");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(700, 600);
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
        this.setContentPane(contentPane);

        SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);

        JPanel labelPane = new JPanel();
        BoxLayout labelLayout = new BoxLayout(labelPane, BoxLayout.PAGE_AXIS);
        labelPane.setLayout(labelLayout);
        labelPane.add(dateLabel);
        labelPane.add(numListedPropsLabel);
        labelPane.add(numRentedPropsLabel);
        labelPane.add(numActiveListingsLabel);
        labelPane.add(new JLabel("All properties rented in the period:"));
        contentPane.add(labelPane);

        JScrollPane propsPane = new JScrollPane(rentedPropsTable);
        propsPane.setMaximumSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	propsPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	rentedPropsTable.setFillsViewportHeight(true);
    	contentPane.add(propsPane);
    	layout.putConstraint(SpringLayout.WEST, propsPane, 0, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, propsPane, 0, SpringLayout.SOUTH, labelPane);

        JPanel buttonPane = new JPanel();
        buttonPane.add(backButton);
        contentPane.add(buttonPane);
        layout.putConstraint(SpringLayout.NORTH, buttonPane, 0, SpringLayout.SOUTH, propsPane);
    }

    @Override
    public void addActionEvent() {
        backButton.addActionListener(this);
    }
    
}
