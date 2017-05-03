package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RunButton extends JButton {

    private MainPanel _m;
    
    public RunButton(MainPanel m) {
	super("Run");
	_m = m;
	addActionListener(new RunButtonListener());
    }

    class RunButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    Thread t = new Thread(() ->
				  _m.run());
	    t.start();
	}
    }    
    
}
