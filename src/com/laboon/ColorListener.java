package com.laboon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorListener implements ItemListener {

    /**
     * Called whenever the item state has changed on the Color
     * menu (i.e. whenever somebody clicks something in the 
     * Color menu).
     * It will then set the color of the highlight cursor.
     * Note that updating the color list will also require
     * updating this list!
     * If the color from the source is not recognized (should
     * not happen, but one should prepare for eventualities!)
     * then the color will be set to GRAY.
     * SIDE EFFECT: Updates color value in SystemSettings
     * @param e ItemEvent from which we can get the source's text
     */
    
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
	} else {
	    SystemSettings.setColor(Color.GRAY);
	}
	    
    }
}
