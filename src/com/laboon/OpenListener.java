package com.laboon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class OpenListener implements ActionListener {


    public String openFile(File f) {
	return "";
    }
    
    public void actionPerformed(ActionEvent e) {
	final JFileChooser fc = new JFileChooser();
	int returnVal = fc.showOpenDialog((Component) e.getSource());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
	    String r = openFile(f);
	    // TODO: Set this in main
        } else {
	    // Cancel, do nothing
        }
    }
}
