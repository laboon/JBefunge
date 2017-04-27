package com.laboon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class OpenListener implements ActionListener {


    public String openFile(File f) {
	try(BufferedReader br = new BufferedReader(new FileReader(f))) {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
		sb.append(line);
		sb.append(System.lineSeparator());
		line = br.readLine();
	    }
	    return sb.toString();
	} catch (IOException ioex) {
	    return "ERROR READING FILE";
	}

    }
    
    public void actionPerformed(ActionEvent e) {
	Component mw = (Component) e.getSource();
	final JFileChooser fc = new JFileChooser();
	int returnVal = fc.showOpenDialog(mw);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
	    System.out.println("Selected " + f.toString());
	    String text = openFile(f);
	    SystemSettings._mainWindow.setProgramArea(text);

        } else {
	    // Cancel, do nothing
        }
    }
}
