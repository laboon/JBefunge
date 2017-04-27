package com.laboon;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

public class QuitListener implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
	int reply =
	    JOptionPane.showConfirmDialog(null,
					  "Really quit JBefunge?",
					  "INFO",
					  JOptionPane.YES_NO_OPTION);
	if (reply == JOptionPane.YES_OPTION) {
	    System.exit(0);
	} else {
	    // Do nothing, continue on and run
	}

    }
}
