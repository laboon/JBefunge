package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.util.*;


public class MainPanel extends JPanel {


    public JTextArea _ta = new JTextArea(80, 25);

    public JTextArea _stack = new JTextArea(80, 1);
    
    public JTextArea _output = new JTextArea(80, 10);
	
    
    /**
     * Convert the Main Panel into a String
     * which can be written to a file.
     */
    
    public String toString() {
	return "TODO";
    }

    /**
     * Run until complete
     */
    
    public void run() {
    }

    public void step() {
    }


    /**
     * Load in a previous Befunge program.
     */
    
    public void load(ArrayList<String> lines) {
    }
    

    public MainPanel() {
	super();
	_ta.setFont(new Font("monospaced"));
	_ta.setWordWrap(false);

	_stack.setFont(new Font("monospaced"));
	_stack.setWordWrap(false);

	_output.setFont(new Font("monospaced"));
	_output.setWordWrap(false);

	
	_ta.setVisible(true);
	_stack.setVisible(true);
	_output.setVisible(true);
    }
	
}
