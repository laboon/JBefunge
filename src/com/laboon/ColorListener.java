package com.laboon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorListener implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
	JMenuItem source = (JMenuItem) (e.getSource());
	String text = source.getText();

	if (text.equalsIgnoreCase("Red")) {
	    SystemSettings.setColor(Color.RED);
	} else if (text.equalsIgnoreCase("Yellow")) {
	    SystemSettings.setColor(Color.YELLOW);
	} else if (text.equalsIgnoreCase("Blue")) {
	    SystemSettings.setColor(Color.BLUE);
	} else if (text.equalsIgnoreCase("Pink")) {
	    SystemSettings.setColor(Color.PINK);
	} else if (text.equalsIgnoreCase("Green")) {
	    SystemSettings.setColor(Color.GREEN);
	} else if (text.equalsIgnoreCase("Orange")) {
	    SystemSettings.setColor(Color.ORANGE);
	}
	    
    }
}
