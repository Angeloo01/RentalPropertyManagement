package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.LoginController;
import Controller.MailController;
import Controller.MailInboxController;
import Database.DatabaseConnectivity;
import Entity.ListOfUsers;
import Entity.User;

public class MailInboxGUI extends GUIWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable inbox = new JTable();
	JButton viewButton = new JButton("View and Reply");

	public MailInboxGUI(int x, int y) {
    	super();
    	setTitle("Mail");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	setSize(x, y);
        addComponentsToContainer();
        addActionEvent();
        setTableModel(null);
 
    }
    
    public MailInboxGUI(int x, int y, GUIWindow prev) {
    	this(x, y);
    	this.prev = prev;
    }
    
    public MailInboxGUI(GUIWindow prev) {
    	this(800, 600, prev);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewButton) {
			int rowSel =  inbox.getSelectedRow();
			if(rowSel >= 0) {
				MailController mail = new MailController(new MailGUI(this), ((MailInboxController)controller).getReceiver(), ((MailInboxController)controller).getSender(rowSel));
				mail.initializeMail("Re: "+((MailInboxController)controller).getSubject(rowSel), 
						"\n -------------------------------------------------------------------------------------------\n"+((MailInboxController)controller).getMessage(rowSel));
				setVisible(false);
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
	
	public void setTableModel(Object[][] data) {
		String[] columnNames = {"Sender", "Message"};
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
		
		inbox.setModel(tableModel);
		inbox.getTableHeader().setReorderingAllowed(false);
		
		inbox.getColumnModel().getColumn(0).setMaxWidth(200);
		inbox.getColumnModel().getColumn(0).setMinWidth(200);
		inbox.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	@Override
	public void addComponentsToContainer() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		//table
		JScrollPane scrollPane = new JScrollPane(inbox);
    	scrollPane.setMaximumSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	scrollPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	inbox.setFillsViewportHeight(true);
    	
    	contentPane.add(scrollPane);
    	layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, contentPane);
		
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.setPreferredSize(new Dimension(getWidth(), 100));
    	buttonPanel.add(viewButton);
    	buttonPanel.add(previousButton);

    	contentPane.add(buttonPanel);
    	layout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, contentPane);
    	layout.putConstraint(SpringLayout.NORTH, buttonPanel, (int)scrollPane.getMaximumSize().getHeight()+10, SpringLayout.NORTH, scrollPane);
	}

	@Override
	public void addActionEvent() {
		previousButton.addActionListener(this);
		viewButton.addActionListener(this);

	}
	
//	public static void main(String[] args) {
//		  //LoginGUI frame = new LoginGUI(360, 600);
//		DatabaseConnectivity.initializeConnection("root", "ensf480");
//		DatabaseConnectivity.updateAllEntities();
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						ListOfUsers.getInstance().printList();
//						new MailInboxController(new MailInboxGUI(null), ListOfUsers.getInstance().getUserFromEmail("landlord@email.ca"));
//					}
//				});
//		  
//			}

}
