package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MoseyButton extends JButton {

    private MainPanel _m;
    
    public MoseyButton(MainPanel m) {
	super("Mosey");
	_m = m;
	addActionListener(new MoseyButtonListener());
    }

    class MoseyButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    Thread t = new Thread(() ->
				  _m.mosey());
	    t.start();
	}
    }    
    
}
