package com.laboon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class SaveListener implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
	File f;
	MainWindow mw = SystemSettings.getMainWindow();

	if (SystemSettings.getFile() == "") {
	    final JFileChooser fc = new JFileChooser();
	    int returnVal = fc.showSaveDialog((Component) e.getSource());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		f = fc.getSelectedFile();
	    } else {
		return;
	    }
	} else {
	    f = new File(SystemSettings.getFile());
	}
	String text = mw.getProgramArea();
	GeneralUtils.saveFile(f, text);
	String fileName = f.toString();
	SystemSettings.setFile(fileName);
	mw.setTitle(fileName);
    }
}
