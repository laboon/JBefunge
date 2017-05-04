package com.laboon;

import java.awt.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {

    private RunButton _run;

    private WalkButton _walk;

    private MoseyButton _mosey;
    
    private StepButton _step;

    private StopButton _stop;

    public void noRunMode() {
	_run.setEnabled(true);
	_walk.setEnabled(true);
	_mosey.setEnabled(true);
	_step.setEnabled(true);
	_stop.setEnabled(false);
    }

    public void runMode() {
	_run.setEnabled(false);
	_walk.setEnabled(false);
	_mosey.setEnabled(false);
	_step.setEnabled(false);
	_stop.setEnabled(true);
    }

    public void stepMode() {
	_run.setEnabled(false);
	_walk.setEnabled(false);
	_mosey.setEnabled(false);
	_step.setEnabled(true);
	_stop.setEnabled(true);
    }

    
    /**
     * Constructor - add all of the buttons to
     * the ButtonPanel.
     */
    
    public ButtonPanel(MainPanel m) {

	// Send a reference to the Main Panel
	// to all of the buttons.
	
	_run = new RunButton(m);
	_walk = new WalkButton(m);
	_mosey = new MoseyButton(m);
	_step = new StepButton(m);
	_stop = new StopButton(m);
	
	setLayout(new FlowLayout());

	// Add all of the buttons
	
	add(_run);
	add(_walk);
	add(_mosey);
	add(_step);
	add(_stop);

    }
    
}
