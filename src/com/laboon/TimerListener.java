package com.laboon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerListener implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.DESELECTED) {
            SystemSettings.setTimer(false);
        } else {
	    SystemSettings.setTimer(true);
	}
    }
}
