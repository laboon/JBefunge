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

    public void step() {
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
	_stack.setBorder(BorderFactory.createLineBorder(Color.black));
	
	_output.setFont(f);
	_output.setBorder(BorderFactory.createLineBorder(Color.black));
	
	
	this.setLayout(new GridLayout(3, 1));

	this.add(_taSp);
	this.add(_stackSp);
	this.add(_outputSp);
	    
	
	_ta.setVisible(true);
	_stack.setVisible(true);
	_output.setVisible(true);
    }
	
}
