package com.laboon;

public class ProgramArea {


    private int DEFAULT_X_SIZE = 80;
    private int DEFAULT_Y_SIZE = 25;
    
    private char[][] _area = new char[DEFAULT_X_SIZE][DEFAULT_Y_SIZE];
    
    private int _xSize = -1;

    private int _ySize = -1;
    
    public ProgramArea() {
	// Fill up area with spaces to start
	for (int j = 0; j < DEFAULT_X_SIZE; j++) {
	    for (int k = 0; k < DEFAULT_Y_SIZE; k++) {
		_area[j][k] = ' ';
	    }
	}
    }

    public char getOpCode(int x, int y) {
	return _area[x][y];
    }
    
    
}
