package com.laboon;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

public class QuitListener implements ActionListener {

    /**
     * Called whenever the user opts to quit.
     * Will ask whether or not the user wishes to quit.
     * If so, quit normally (exit code 0).
     * If not, continue as if nothing ever happened.
     * SIDE EFFECT: Creates modal window, may shut down system
     * @param e ActionEvent which we ignore
     */
    
    public void actionPerformed(ActionEvent e) {
	int reply =
	    JOptionPane.showConfirmDialog(null,
					  "Really quit JBefunge?",
					  "INFO",
					  JOptionPane.YES_NO_OPTION);
	if (reply == JOptionPane.YES_OPTION) {
	    // Exit system with normal status (exit code 0)
	    System.exit(0);
	} else {
	    // Do nothing, continue on and run
	}

    }
}
