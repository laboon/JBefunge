package com.laboon;

/**
 * The actual input program, converted from a big String to an 
 * m * n array of chars.
 */

public class ProgramArea {

    // Default size of board is 80x80
    // Note that we currently do not have the option to extend it

    private final int DEFAULT_X_SIZE = 80;
    private final int DEFAULT_Y_SIZE = 80;

    // The actual char array holding the program.
    // Converted from a string.
    private char[][] _area = new char[DEFAULT_X_SIZE][DEFAULT_Y_SIZE];

    // The (publicly accessible) x and y sizes of the array.
    public int _xSize = DEFAULT_X_SIZE;

    public int _ySize = DEFAULT_Y_SIZE;

    /**
     * Fills up the program area with spaces (the "ignore" opcode)
     * before reading in the program.
     */
    
    private void fillUpSpaces() {
	// Fill up area with spaces to start
	for (int j = 0; j < DEFAULT_X_SIZE; j++) {
	    for (int k = 0; k < DEFAULT_Y_SIZE; k++) {
		_area[j][k] = ' ';
	    }
	}

    }

    /**
     * Constructor
     * @param init - The initial string of the program
     */
    
    public ProgramArea(String init) {

	// Fill up initial board with spaces
	
	fillUpSpaces();

	// Read the string in, converting it into lines, and then
	// putting  each character in a line into the main char array
	
	String[] lines = init.split("\n");
	String line;

	int len = lines.length;
	int lineLen = 0;

	// ignore lines > _xSize
	if (len > _xSize) {
	    len = _xSize;
	}
	
	for (int j = 0; j < len; j++) {
	    line = lines[j];
	    lineLen = line.length();

	    // Ignore chars past _ySize
	    if (lineLen > _ySize) {
		lineLen = _ySize;
	    }
	    for (int k = 0; k < lineLen; k++) {
		_area[j][k] = line.charAt(k);
	    }
	}

	
    }

    /**
     * Default constructor.
     * Creates a ProgramArea which is all spaces
     */
    
    public ProgramArea() {
	fillUpSpaces();
    }

    /**
     * Get the opcode from a particular place in the program area.
     * If opcode is not in program space, return null char (ASCII 0).
     * @param x x-coordinate
     * @param y y-coordinate
     * @return char The opcode (char) at that location
     */
    
    public char getOpCode(int x, int y) {
	if (x >= _xSize || x < 0 || y >= _ySize || y < 0) {
	    return (char) 0;
	} else {
	    return _area[x][y];
	}
    }

    /**
     * Set the opcode at a particular place in the program area.
     * If out of bounds, do nothing.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return char The opcode (char) to set at that location
     */
    
    public void setOpCode(int x, int y, char v) {
	if (x >= _xSize || x < 0 || y >= _ySize || y < 0) {
	    // Ignore
	} else {
	    _area[x][y] = v;
	}
    }

    /**
     * Returns whether or not the program has "end" opcode.
     * If it does not, you may not want to run it - it 
     * may run forever!
     * @return true if @ symbol exists, false otherwise
     */

    public boolean hasEndOpcode() {
	boolean hasEnd = false;
	for (int j = 0; j < _xSize && hasEnd == false; j++) {
	    for (int k = 0; k < _ySize && hasEnd == false; k++) {
		if (_area[j][k] == '@') {
		    hasEnd = true;
		}
	    }
	}
	return hasEnd;
    }
    
    /**
     * Convert the ProgramArea back into a String
     * @return String string representation of the ProgramArea
     */

    public String toString() {
	StringBuilder sb = new StringBuilder("");
	for (int j = 0; j < _xSize; j++) {
	    for (int k = 0; k < _ySize; k++) {
		sb.append(_area[j][k]);
	    }
	    sb.append("\n");
	}
	return sb.toString();
    }
    
}
