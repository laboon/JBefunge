package com.laboon;

public class ProgramArea {


    private int DEFAULT_X_SIZE = 100;
    private int DEFAULT_Y_SIZE = 100;
    
    private char[][] _area = new char[DEFAULT_X_SIZE][DEFAULT_Y_SIZE];
    
    public int _xSize = DEFAULT_X_SIZE;

    public int _ySize = DEFAULT_Y_SIZE;

    public void fillUpSpaces() {
	// Fill up area with spaces to start
	for (int j = 0; j < DEFAULT_X_SIZE; j++) {
	    for (int k = 0; k < DEFAULT_Y_SIZE; k++) {
		_area[j][k] = ' ';
	    }
	}

    }

    public ProgramArea(String init) {
	fillUpSpaces();
	// System.out.println("PA - init string: " + init);
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
	    // System.out.println("PA - j: " + j + " / " + line);
	    if (lineLen > _ySize) {
		lineLen = _ySize;
	    }
	    for (int k = 0; k < lineLen; k++) {
		// System.out.println("\tPA - k " + k + "/" + line.charAt(k));
		_area[j][k] = line.charAt(k);
	    }
	}

	
    }
    
    public ProgramArea() {
	fillUpSpaces();
    }

    public char getOpCode(int x, int y) {
	return _area[x][y];
    }
 
    public void setOpCode(int x, int y, char v) {
	_area[x][y] = v;
    }
   
    

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
