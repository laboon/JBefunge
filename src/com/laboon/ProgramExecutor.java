package com.laboon;

public class ProgramExecutor {

    private boolean _programComplete = false;
    
    private ProgramArea _pa;

    private ProgramStack _ps;

    private Direction _d = Direction.RIGHT;

    private int _x = 0;

    private int _y = 0;

    // Commands:
    // +   Addition: Pop two values a and b, then push the result of a+b
    // -   Subtraction: Pop two values a and b, then push the result of b-a
    // *   Multiplication: Pop two values a and b, then push the result of a*b
    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. According to the specifications, if a is zero, ask the user what result they want.
    // %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // >   PC direction right
    // <   PC direction left
    // ^   PC direction up
    // v   PC direction down
    // ?   Random PC direction
    // _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // |   Vertical IF: pop a value; set direction to down if value=0, set to up otherwise
    // "   Toggle stringmode (push each character's ASCII value all the way up to the next ")
    // :   Duplicate top stack value
    // \   Swap top stack values
    // $   Pop (remove) top stack value and discard
    // .   Pop top of stack and output as integer
    // ,   Pop top of stack and output as ASCII character
    // #   Bridge: jump over next command in the current direction of the current PC
    // g   A "get" call (a way to retrieve data in storage). Pop two values y and x, then push the ASCII value of the character at that position in the program. If (x,y) is out of bounds, push 0
    // p   A "put" call (a way to store a value for later use). Pop three values y, x and v, then change the character at the position (x,y) in the program to the character with ASCII value v
    // &   Get integer from user and push it
    // ~   Get character from user and push it
    // @   End program
    // 0 – 9   Push corresponding number onto the stack


    // TODO - implement!
    public void executeInstruction(char x) {
	switch (x) {
	    // +   Addition: Pop two values a and b, then push the result of a+b
	case '+':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	 // -   Subtraction: Pop two values a and b, then push the result of b-a	    
	case '-':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;
	    
	// *   Multiplication: Pop two values a and b, then push the result of a*b
	case '*':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;
		    
	// /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. According to the specifications, if a is zero, ask the user what result they want.
	case '/':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
	case '%':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
	case '!':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
	case '`':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// >   PC direction right
	case '>':
	    _d = RIGHT;
	    break;

	// <   PC direction left
	case '<':
	    _d = LEFT;
	    break;

	// ^   PC direction up
	case '^':
	    _d = UP;
	    break;

	// v   PC direction down
	case 'v':
	    _d = DOWN;
	    break;

	// ?   Random PC direction
	case '?':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
	case '_':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// |   Vertical IF: pop a value; set direction to down if value=0, set to up otherwise
	case '|':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// "   Toggle stringmode (push each character's ASCII value all the way up to the next ")
	case '"':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// :   Duplicate top stack value
	case ':':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// \   Swap top stack values
	case '\\':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// $   Pop (remove) top stack value and discard
	case '$':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// .   Pop top of stack and output as integer
	case '.':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// ,   Pop top of stack and output as ASCII character
	case ',':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// #   Bridge: jump over next command in the current direction of the current PC
	case '#':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// g   A "get" call (a way to retrieve data in storage). Pop two values y and x, then push the ASCII value of the character at that position in the program. If (x,y) is out of bounds, push 0
	case 'g':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// p   A "put" call (a way to store a value for later use). Pop three values y, x and v, then change the character at the position (x,y) in the program to the character with ASCII value v
	case 'p':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// &   Get integer from user and push it
	case '&':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// ~   Get character from user and push it
	case '~':
	    System.err.println(x + "NOT YET IMPLEMENTED!");
	    break;

	// @   End program
	case '@':	
	    _programComplete = true;
	    break;
        // 0 – 9   Push corresponding number onto the stack
	case '0':
	case '1':
	case '2':
	case '3':
	case '4':
	case '5':
	case '6':
	case '7':
	case '8':
	case '9':
	    int intX = Character.getNumericValue(x);
	    _ps.push(intX);
	    break;
		
	default:  
	    System.err.println(x + "NOT SUPPORTED!");
	    
	}
    

	
    }

    public void moveOneSpace() {
	switch (_d) {
	case LEFT:
	    break;
	case RIGHT:
	    break;
	case UP:
	    break;
	case DOWN:
	    break;
	}
    }
    
    public void executeOneStep() {
	// Get character at current location and execute it
	char opCode = _pa.getOpCode(_x, _y);
	executeInstruction(opCode);
	// Move one space in correct direction
	moveOneSpace();
    }
    
    public ProgramExecutor(ProgramArea pa, ProgramStack ps) {
	_pa = pa;
	_ps = ps;
    }

    public void run() {
	// Restart program
	// Location = 0,0
	// Direction = left to right
	_programComplete = false;
	_x = 0;
	_y = 0;
	_d = RIGHT;
    }

    public void step() {
    }
    
}
