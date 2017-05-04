package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MoseyButton extends JButton {

    // Reference to the main panel (so we can call mosey method
    // on it)
    private MainPanel _m;

    /**
     * Constructor
     * Create a button named "Mosey", set the action listener
     * to the one included here, and set the MainPanel ref.
     * @param m reference to MainPanel of program
     */
    
    public MoseyButton(MainPanel m) {
	super("Mosey");
	_m = m;
	addActionListener(new MoseyButtonListener());
    }

    
    class MoseyButtonListener implements ActionListener {
	
	/**
	 * Called when Mosey button pushed.
	 * Starts a new thread because doing work in the Swing
	 * thread is a bad idea (won't be able to interact 
	 * with the GUI, sleeping would cause GUI to sleep, etc.
	 * Note that this uses the new "stabby lambda" syntax of
	 * Java 8 to make a thread with a very simple lambda.
	 * Mosey will make the program go slowly.
	 */

	public void actionPerformed(ActionEvent e) {
	    Thread t = new Thread(() ->
				  _m.mosey());
	    t.start();
	}
    }    
    
}
