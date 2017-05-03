package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WalkButton extends JButton {

    private MainPanel _m;
    
    public WalkButton(MainPanel m) {
	super("Walk");
	_m = m;
	addActionListener(new WalkButtonListener());
    }

    class WalkButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    Thread t = new Thread(() ->
				  _m.walk());
	    t.start();
	}
    }    
    
}
