package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.Date;

import javax.swing.*;

import Controller.LandlordPropertiesController;
import Controller.LoginController;
import Controller.ManagerGUIController;
import Entity.Property;
import Entity.SummaryReport;

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
	JButton propButton = new JButton("Property Status");
	
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
		if(e.getSource() == feeButton) {
			String fee = feeChargeTF.getText();
            String period = feePeriodTF.getText();
            if(fee.isEmpty() || period.isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Invalid fee or period");
        		return;
        	}
            else {
            	int feeF;
            	int periodI;
	            try {
	            	feeF = (Integer.parseInt(fee));
	            	periodI = (Integer.parseInt(period));
	            }
	            catch(Exception ex) {
	            	JOptionPane.showMessageDialog(this, "Invalid fee or period");
	            	return;
	            }
	            if(((ManagerGUIController)controller).setFee(feeF, periodI))
	            	JOptionPane.showMessageDialog(this, "New fee has been set");
	            else
	            	JOptionPane.showMessageDialog(this, "Error in setting fee");
            }
		}
		else if(e.getSource() == propButton) {
			LandlordPropertiesGUI lpgui = new LandlordPropertiesGUI(this);
			lpgui.removeRegisterPanel();
			new LandlordPropertiesController(lpgui, null);
			setVisible(false);
		}
		else if(e.getSource() == summButton) {
			String inp = JOptionPane.showInputDialog("Enter a period");
			if(inp != null) {
				try {
					int period = Integer.valueOf(inp);
					if (period < 0) {
						JOptionPane.showMessageDialog(new JFrame(), "Period cannot be negative", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						SummaryReport report = ((ManagerGUIController)controller).getSummaryReport((long)period);
						new SummaryReportGUI(this, report);
						dispose();
					}
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getSource() == dbButton) {
			new DatabaseGUI(this);
			dispose();
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
    	
    	Dimension panelSize = new Dimension(getWidth(), 40);
    	
    	JPanel feePanel1 = new JPanel(new FlowLayout());
    	JPanel feePanel2 = new JPanel(new FlowLayout());
    	JPanel buttonPanel1 = new JPanel(new FlowLayout());
    	JPanel buttonPanel2 = new JPanel(new FlowLayout());
    	JPanel buttonPanel3 = new JPanel(new FlowLayout());
    	JPanel buttonPanel4 = new JPanel(new FlowLayout());
    	JPanel buttonPanel5 = new JPanel(new FlowLayout());
    	
    	feePanel1.setMaximumSize(panelSize);
    	feePanel2.setMaximumSize(panelSize);
    	buttonPanel1.setMaximumSize(panelSize);
    	buttonPanel2.setMaximumSize(panelSize);
    	buttonPanel3.setMaximumSize(panelSize);
    	buttonPanel4.setMaximumSize(panelSize);
    	buttonPanel5.setMaximumSize(panelSize);
    	
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
    	
    	mainPanel.add(Box.createVerticalStrut(10));
    	buttonPanel5.add(propButton);
    	mainPanel.add(buttonPanel5);
    	
    	mainPanel.add(Box.createVerticalStrut(10));
    	buttonPanel4.add(previousButton);
    	mainPanel.add(buttonPanel4);
    	
    	
    	

	}

	@Override
	public void addActionEvent() {
		feeButton.addActionListener(this);
		summButton.addActionListener(this);
		dbButton.addActionListener(this);
		previousButton.addActionListener(this);
		propButton.addActionListener(this);

	}
	
	public void showFee(int amount, int period) {
		feeChargeTF.setText(String.valueOf(amount));
		feePeriodTF.setText(String.valueOf(period));
	}
	
//	public static void main(String[] args) {
//        //LoginGUI frame = new LoginGUI(360, 600);
//    	ListOfUsers users = ListOfUsers.getInstance();
//    	users.add(new User("admin", "admin", 0));
//    	users.add(new User("user", "user", 2));
//    	SwingUtilities.invokeLater(new Runnable() {
//    		public void run() {
//    			ManagerGUI login = new ManagerGUI(null);
//    		}
//    	});
//        
//    }

}
