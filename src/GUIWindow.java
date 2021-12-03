import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class GUIWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Container container = getContentPane();
	protected JButton previousButton = new JButton("Back");
	protected GUIWindow prev;
	protected GUIController controller;
	
	public abstract void addComponentsToContainer();
	public abstract void addActionEvent();
	
	void setController (GUIController controller) {
    	this.controller = controller;
    }
	

}
