package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StopButton extends JButton {

    // Reference to the main panel (so we can call stop method
    // on it)
    
    private MainPanel _m;

    /**
     * Constructor
     * Create a button named "Stop", set the action listener
     * to the one included here, and set the MainPanel ref.
     * @param m reference to MainPanel of program
     */

    
    public StopButton(MainPanel m) {
	super("Stop");
	_m = m;
	addActionListener(new StopButtonListener());
    }

    class StopButtonListener implements ActionListener {

	/**
	 * Called when Stop button pushed.
	 * Stops the program via the MainPanel.
	 */

	
	public void actionPerformed(ActionEvent e) {
	    _m.stop();
	}
    }    
    
}
