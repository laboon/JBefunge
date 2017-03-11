package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.util.*;


public class MainPanel extends JPanel {


    public JTextArea _ta = new JTextArea(1, 40);
    public JScrollPane _taSp = new JScrollPane(_ta);
    
    public JTextArea _stack = new JTextArea(1, 40);
    public JScrollPane _stackSp = new JScrollPane(_stack);
    
    public JTextArea _output = new JTextArea(10, 40);
    public JScrollPane _outputSp = new JScrollPane(_output);

    public JLabel _taLabel = new JLabel();
    public JLabel _stackLabel = new JLabel();
    public JLabel _outputLabel = new JLabel();

    public JPanel _midPanel = new JPanel();

    public int getIntFromUser() {
	String inp = JOptionPane.showInputDialog("Enter an integer (if unparseable, counts as 0)");
	int toReturn = 0;
	try {
	    toReturn = Integer.parseInt(inp);
	} catch (NumberFormatException nfex) {
	    toReturn = 0;
	}
	return toReturn;
	    
    }

    public int getCharFromUser() {
	String inp = JOptionPane.showInputDialog("Enter a single char (if > 1, will use first; if none, null (0) char)");
	int toReturn = 0;
	try {
	    toReturn = (int) inp.charAt(0);
	} catch (Exception nfex) {
	    toReturn = 0;
	}
	return toReturn;
	    
    }

    
    /**
     * Convert the Main Panel into a String
     * which can be written to a file.
     */
    
    public String toString() {
	return "TODO";
    }

    public String convertTextAreaToString() {

	String text = _ta.getText();
	return text;
    }
    
    /**
     * Run until complete
     */
    
    public void run() {
	// Clear non-program text
	_stack.setText("");
	_output.setText("");
	ProgramStack ps = new ProgramStack();
	ProgramArea pa = new ProgramArea(convertTextAreaToString());
	// System.out.println(pa.toString());
	ProgramExecutor pe = new ProgramExecutor(this, ps, pa);
	pe.run();
    }


    /**
     * Load in a previous Befunge program.
     */
    
    public void load(ArrayList<String> lines) {
    }
    

    public void print(char c) {
	String newText = _output.getText() + String.valueOf(c);
	_output.setText(newText);
    }

    public void printInt(int i) {
	String newText = _output.getText() + i;
	_output.setText(newText);

    }
    
    public void setStack(String stackStr) {
	_stack.setText(stackStr);
    }

    public void refresh() {
	_ta.setVisible(true);
	_stack.setVisible(true);
	_output.setVisible(true);
    }
    
    public MainPanel() {
	super();

	Font f = new Font("monospaced", Font.PLAIN, 12);
	
	_ta.setFont(f);
	_ta.setBorder(BorderFactory.createLineBorder(Color.black));
	
	_stack.setFont(f);
	_stack.setEditable(false);
	_stack.setBorder(BorderFactory.createLineBorder(Color.black));
	
	_output.setFont(f);
	_output.setEditable(false);
	_output.setBorder(BorderFactory.createLineBorder(Color.black));

	_midPanel.setLayout(new GridLayout(3, 1));
	_midPanel.add(new JLabel("Stack:"));
	_midPanel.add(_stackSp);	
	_midPanel.add(new JLabel("Output:"));
	
	this.setLayout(new GridLayout(3, 1));

	this.add(_taSp);
	this.add(_midPanel);
	this.add(_outputSp);
	    
	
	_ta.setVisible(true);
	_stack.setVisible(true);
	_output.setVisible(true);
    }
	
}
