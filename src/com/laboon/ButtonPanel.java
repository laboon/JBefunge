package com.laboon;

import java.awt.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {

    private RunButton _run;

    private StepButton _step;

    /**
     * Constructor - add all of the buttons to
     * the ButtonPanel.
     */
    
    public ButtonPanel(MainPanel m) {

	// Send a reference to the Main Panel
	// to all of the buttons.
	
	_run = new RunButton(m);
	// _step = new StepButton(m);
	setLayout(new FlowLayout());

	// Add all of the buttons
	
	add(_run);
	// add(_step);
    }
    
}
