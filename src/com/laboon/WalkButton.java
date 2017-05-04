package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WalkButton extends JButton {
    
    // Reference to the main panel (so we can call mosey method
    // on it
    private MainPanel _m;
    
    /**
     * Constructor
     * Create a button named "Walk", set the action listener
     * to the one included here, and set the MainPanel ref.
     * @param m reference to MainPanel of program
     */
    
    public WalkButton(MainPanel m) {
	super("Walk");
	_m = m;
	addActionListener(new WalkButtonListener());
    }

    class WalkButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    Thread t = new Thread(() ->
				  _m.walk());
	    t.start();
	}
    }    
    
}
