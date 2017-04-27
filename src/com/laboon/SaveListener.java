package com.laboon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class SaveListener implements ActionListener {


    public void saveFile(File f, String text) {
	try (PrintWriter out = new PrintWriter(f)) {
	    out.println(text);
	} catch (IOException ioex) {
	    System.out.println("Could not write to file " + f.toString());
	}
    }
    
    public void actionPerformed(ActionEvent e) {
	final JFileChooser fc = new JFileChooser();
	int returnVal = fc.showSaveDialog((Component) e.getSource());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
	    String text = SystemSettings._mainWindow.getProgramArea();
	    saveFile(f, text);
	    // TODO: Save this to file
        } else {
	    // Cancel, do nothing
        }
    }
}
