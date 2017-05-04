package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RunButton extends JButton {

    // Reference to the main panel (so we can call run method
    // on it)

    private MainPanel _m;

    
    /**
     * Constructor
     * Create a button named "Run", set the action listener
     * to the one included here, and set the MainPanel ref.
     * @param m reference to MainPanel of program
     */

    
    public RunButton(MainPanel m) {
	super("Run");
	_m = m;
	addActionListener(new RunButtonListener());
    }

    /**
     * Called when Run button pushed.
     * Starts a new thread because doing work in the Swing
     * thread is a bad idea (won't be able to interact 
     * with the GUI, sleeping would cause GUI to sleep, etc.
     * Note that this uses the new "stabby lambda" syntax of
     * Java 8 to make a thread with a very simple lambda.
     * Run will run the program as fast as it can go (no sleeps).
     */

    
    class RunButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    Thread t = new Thread(() ->
				  _m.run());
	    t.start();
	}
    }    
    
}
