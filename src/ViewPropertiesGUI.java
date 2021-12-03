import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ViewPropertiesGUI extends GUIWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//view
	Container container = getContentPane();
	JTable table = new JTable();
	JButton selectButton = new JButton("Select Property");

	
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
	
	void setController (LoginController controller) {
    	//this.controller = controller;
    }
 
    public void addComponentsToContainer() {
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	setContentPane(mainPanel);
    	
    	JScrollPane scrollPane = new JScrollPane(table);
    	scrollPane.setMaximumSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	scrollPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*.6f)));
    	table.setFillsViewportHeight(true);
    	
    	mainPanel.add(scrollPane);
    	
    	JPanel buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.add(selectButton);
    	buttonPanel.add(previousButton);
    	mainPanel.add(buttonPanel);
    }
 
    public void addActionEvent() {
        selectButton.addActionListener(this);
        previousButton.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectButton) {
			int rowIn;
			if((rowIn = table.getSelectedRow()) >= 0) {
				((ViewPropertiesController)controller).selectProperty(rowIn);
			}
		}
		else if(e.getSource() == previousButton) {
			 LoginGUI frame = new LoginGUI(360, 600);
		     LoginController controller = new LoginController(frame);
		     frame.setController(controller);
		     dispose();
		}
		
	}

}
