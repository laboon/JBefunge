package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StepButton extends JButton {

    private MainPanel _m;
    
    public StepButton(MainPanel m) {
	super("Step");
	_m = m;
	addActionListener(new StepButtonListener());
    }

    class StepButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    // _m.step();
	}
    }    
    
}
