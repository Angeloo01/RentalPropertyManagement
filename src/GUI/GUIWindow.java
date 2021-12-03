package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.GUIController;
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
	
	public void setController (GUIController controller) {
    	this.controller = controller;
    }
	

}
