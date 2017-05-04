package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StepButton extends JButton {

    // Reference to the main panel (so we can call step method
    // or firstStep method on it)
    
    private MainPanel _m;

    /**
     * Constructor
     * Create a button named "Step", set the action listener
     * to the one included here, and set the MainPanel ref.
     * @param m reference to MainPanel of program
     */

    
    public StepButton(MainPanel m) {
	super("Step");
	_m = m;
	addActionListener(new StepButtonListener());
    }

    class StepButtonListener implements ActionListener {

	/**
	 * Called when Step button pressed.
	 * Note that we do NOT start a new thread here!
	 * Although we are doing some work on the Swing
	 * thread, it is so minimal that it's not really worth
	 * the overhead of a new thread.
	 * @param e ActionEvent that called us (ignored)
	 */
	
	public void actionPerformed(ActionEvent e) {
	    // If system is already stepping, then we take
	    // one more step.  Otherwise, we need to first
	    // go into step mode, and call the firstStep
	    // method which does some setup to get us
	    // all set up to step.
	    if (SystemSettings.inStepMode()) {
		_m.step();
	    } else {
		SystemSettings.setStepMode(true);
		_m.firstStep();
	    }
	}
    }    
    
}
