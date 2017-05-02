package com.laboon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class SaveAsListener implements ActionListener {

    
    public void actionPerformed(ActionEvent e) {
	final JFileChooser fc = new JFileChooser();
	MainWindow mw = SystemSettings.getMainWindow();
	int returnVal = fc.showSaveDialog((Component) e.getSource());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
	    String text = mw.getProgramArea();
	    GeneralUtils.saveFile(f, text);
	    String fileName = f.toString();
	    SystemSettings.setFile(fileName);
	    mw.setTitle(fileName);
        } else {
	    // Cancel, do nothing
        }
    }
}
