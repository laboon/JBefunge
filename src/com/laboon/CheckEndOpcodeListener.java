package com.laboon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckEndOpcodeListener implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.DESELECTED) {
            SystemSettings.setCheckEndOpcode(false);
        } else {
	    SystemSettings.setCheckEndOpcode(true);
	}
    }
}
