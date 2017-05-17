package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import javax.swing.text.*;

/**
 * The Main Panel of the program.
 * Along with the Button Panel, the entire main GUI frame.
 */

public class MainPanel extends JPanel {

    // The text area for the program itself and its scrollpane
    public JTextArea _pa = new JTextArea(1, 40);
    public JScrollPane _paSp = new JScrollPane(_pa);

    // Actual ProgramArea, only created when running/stepping
    public ProgramArea _programArea = null;

    // Actual ProgramStack, only created when running/stepping
    public ProgramStack _programStack = null;

    // Actual ProgramExecutor, only created when running/stepping
    public ProgramExecutor _programExecutor = null;
    
    // The text area for the stack display and its scrollpane
    public JTextArea _stack = new JTextArea(1, 40);
    public JScrollPane _stackSp = new JScrollPane(_stack);

    // The text are
    public JTextArea _output = new JTextArea(10, 40);
    public JScrollPane _outputSp = new JScrollPane(_output);

    // Label for "Program Stack"
    public JLabel _stackLabel;

    // Label for "Program Output"
    public JLabel _outputLabel;

    // Middle panel of main panel (contains stack label,
    // stack text, and output label
    // This is kind of ugly, but helps us avoid using a GridBagLayout
    public JPanel _midPanel = new JPanel();    

    public void setTextArea(String text) {
	if (text == null) {
	    text = "";
	}
	_pa.setText(text);
    }
    
    /**
     * Get an integer from the user.
     * If the integer is unparseable in ANY way (e.g., nothing entered,
     * "poodle", "4.3"), return 0.  Otherwise, return the entered value.
     * @param int - user-entered integer
     */
    
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

    /**
     * Get a character from the user.
     * If more than one char is entered, accept the first.
    * If no character is entered, assume the null character (ASCII 0).
     * @param int - ASCII value of first user-entered character
     */
    
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
     * Get text from the program text area, return it as a String
     * @return String the text in the text area
     */
    
    public String getTextArea() {

	String text = _pa.getText();
	if (text == null) {
	    text = "";
	}
	return text;
    }

    /**
     * Check to see if the user wants to run the program, even if
     * an end opcode was not found.
     * If no end opcode is found, ask if user wishes to run.  
     * If an end opcode is found, run without asking.
     * SIDE EFFECT: Modal dialog pops up if no end opcode found
     * @param pa - The ProgramArea object to be run
     * @return boolean - true if user wishes to run, false otherwise
     */
    
    public boolean checkEndOpcode(ProgramArea pa) {

	// If the program area passed in does not have an end opcode,
	// check to see (via modal dialog) whether or not user
	// wishes to run.
	
	if (!pa.hasEndOpcode()) {
	    int reply =
		JOptionPane.showConfirmDialog(null,
					      "No end opcode (@) found!\n"
					      + "This may result in a non-terminating program.\n"
					      + "Run anyway?",
					      "WARNING",
					      JOptionPane.YES_NO_OPTION);
	    if (reply == JOptionPane.NO_OPTION) {
		// User does not wish to run, return false
		return false;
	    } else {
		// No end opcode was found, but user wants to run anyways
		return true;
	    }
	} else {
	    // An end opcode was found, so can just run
	    return true;
	}
    }    

    /**
     * Halt execution immediately
     */
    
    public void stop() {
	_pa.getHighlighter().removeAllHighlights();
	SystemSettings.getButtonPanel().noRunMode();
	SystemSettings.setStop(true);
	SystemSettings.setProgramRunning(false);
	SystemSettings.setStepMode(false);
    }

    /**
     * Perform first step of the program in step mode
     */
    
    public void firstStep() {
    	_programArea = new ProgramArea(getTextArea());

	if (SystemSettings.checkEndOpcode()) {
	    boolean cont = checkEndOpcode(_programArea);
	    if (!cont) {
		return;
	    }
	}

    	SystemSettings.getButtonPanel().stepMode();
	
	_programStack = new ProgramStack();
	_programExecutor = new ProgramExecutor(this, _programStack, _programArea);
	
	// Clear non-program text
	_stack.setText("");
	_output.setText("");

	SystemSettings.setStop(false);
	
	boolean complete = _programExecutor.step();
	if (complete) {
	    stop();
	}
	
    }

    
    /**
     * Step through next step of the program
     */

    public void step() {
	boolean complete = _programExecutor.step();
	if (complete) {
	    stop();
	}

    }	

    /**
     * Run until complete
     */

    
    public void runSpeed(int sleepTime) {
	// Put Button GUI in Run Mode 
	SystemSettings.getButtonPanel().runMode();
	
	_programArea = new ProgramArea(getTextArea());

	if (SystemSettings.checkEndOpcode()) {
	    boolean cont = checkEndOpcode(_programArea);
	    if (!cont) {
		return;
	    }
	}
	
	_programStack = new ProgramStack();
	
	// Clear non-program text
	_stack.setText("");
	_output.setText("");

	SystemSettings.setStop(false);
	
	long start = System.nanoTime();
	_programExecutor = new ProgramExecutor(this, _programStack, _programArea);
	_programExecutor.run(sleepTime);
	long end = System.nanoTime();
	long time = (end - start) / 1000;
	
	// Display time to execute if proper setting selected
	if (SystemSettings.timer()) {
	    JOptionPane.showMessageDialog(this, "Time to execute: " + time + " microseconds");
	}

	SystemSettings.getButtonPanel().noRunMode();

    }
    
    
    public void run() {
	runSpeed(0);
    }

    public void walk() {
	runSpeed(50);
    }

    public void mosey() {
	runSpeed(500);
    }


    /**
     * Print character to the output text area
     * @param c Character to print
     */

    public void print(char c) {
	String newText = _output.getText() + String.valueOf(c);
	_output.setText(newText);
    }

    /**
     * Print integer to the output text area
     * @param i - int to print
     */

    
    public void printInt(int i) {
	String newText = _output.getText() + i;
	_output.setText(newText);

    }

    /**
     * Change the display of the stack text area.
     * Note that there is nothing preventing you from any other
     * string!  This is to allow us maximum flexibility if we 
     * change the display later (which is likely).
     * @param stackStr String to display
     */
    
    public void setStack(String stackStr) {
	_stack.setText(stackStr);
    }

    /**
     * Highlight character at position x, y in Program Area
     * SIDE EFFECT: Modifies program area
     * @param x x-pos of char to highlight
     * @param y y-pos of char to highlight
     */
    
    public void highlightChar(ProgramArea p, int x, int y) {
	_pa.getHighlighter().removeAllHighlights();
	try {
	    DefaultHighlighter.DefaultHighlightPainter highlightPainter = 
		new DefaultHighlighter.DefaultHighlightPainter(
							       SystemSettings.getColor());
	    int start = convertLocation(x, y);
	    int end = start + 1;
	    if (start > 0) {
		try {
		    _pa.getHighlighter().addHighlight(start, end, 
						      highlightPainter);
		} catch (BadLocationException blex) {
		    // just ignore for now
		}
	    }
	} catch (ArrayIndexOutOfBoundsException oobex) {
	    // just ignore for now
	}
    }

    /**
     * Given an x, y coordinate, turn it into the nth location
     * of the text area. 
     * Note that this is not as simple as just multiplying x 
     * by 80 and adding y!  This is because null chars are not
     * counted in the string in the text area.  Thus we need to 
     * count ONLY to the end of each line (i.e. until we hit a 
     * \n char).  This necessitates a "counter" variable.
     * There is probably a better/more performant way to do this.
     * @param x x-pos of char
     * @param y y-pos of char
     * @return the string location of the char
     */
    private int convertLocation(int x, int y) {
	char[] text = _pa.getText().toCharArray();
	int c = 0;
	int curX = 0;
	int curY = 0;

	try {
	    while (curX != x || curY != y) {
		if (text[c] == '\n') {
		    curX++;
		    curY = 0;
		} else {
		    curY++;
		}
		c++;
	    }
	} catch (ArrayIndexOutOfBoundsException oobex) {
	    c = -1;
	}
	return c;
    }


    
    /**
     * Refresh the text areas
     * @param pa = should refresh program area if true
     * @param stack = should refresh stack area if true
     * @param output = should refresh output area if true
     */
    
    public void refresh(boolean pa,
			boolean stack,
			boolean output) {
	if (pa) {
	    _pa.update(_pa.getGraphics());
	}
	if (stack) {
	    _stack.update(_stack.getGraphics());
	}
	if (output) {
	    _output.update(_output.getGraphics());
	}

    }
    
    public MainPanel() {
	super();
	
	Font f = new Font("monospaced", Font.PLAIN, 12);
	
	_pa.setFont(f);
	_pa.setBorder(BorderFactory.createLineBorder(Color.black));
	
	_stack.setFont(f);
	_stack.setEditable(false);
	_stack.setLineWrap(true);
	_stack.setBorder(BorderFactory.createLineBorder(Color.black));
	
	_output.setFont(f);
	_output.setEditable(false);
	_output.setBorder(BorderFactory.createLineBorder(Color.black));

	_midPanel.setLayout(new GridLayout(3, 1));
	_midPanel.add(new JLabel("Stack:"));
	_midPanel.add(_stackSp);	
	_midPanel.add(new JLabel("Output:"));
	
	this.setLayout(new GridLayout(3, 1));

	this.add(_paSp);
	this.add(_midPanel);
	this.add(_outputSp);
	    
	
	_pa.setVisible(true);
	_stack.setVisible(true);
	_output.setVisible(true);
    }
	
}
