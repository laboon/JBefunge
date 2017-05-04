package com.laboon;

import java.util.*;

/**
 * This class handles all actual execution of the program.
 * Note that it is _extremely_ stateful - methods will modify the
 * ProgramStack _ps and ProgramArea _pa, but will seldom return
 * values.  This adds a challenge to testing and may make doubling
 * or mocking difficult!
 */

// Befunge Commands:
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

public class ProgramExecutor {
    
    // Random number generator.  Used solely for the '?' opcode.
    // Static because we don't really need a new RNG for each program
    // Also, no worries about thread safety since program execution is
    // single-threaded
    public static Random _r = new Random();

    // A reference to the MainPanel, which will allow us to update
    // the stack and output areas.
    private MainPanel _mp;

    // True if program has completed execution; false otherwise.
    private boolean _programComplete = false;

    // A reference to a ProgramArea (i.e., the array with the code in it)
    public ProgramArea _pa;

    // The ProgramStack for this program.
    public ProgramStack _ps;

    // The current direction of the PC.  It starts moving right but
    // can be modified by the '<', '>', '^' and 'v' opcodes.
    public Direction _d = Direction.RIGHT;

    // The current (x, y) location of the PC.
    private int _x = 0;
    private int _y = 0;

    // True if anything to update on text area, false otherwise
    private boolean _updateTa = false;

    // True if anything to update on stack area, false otherwise
    private boolean _updateStack = false;
    
    // True if anything to update on output area, false otherwise
    private boolean _updateOutput = false;
    
    // Whether or not we are currently reading the input in as a string.
    // The opcode '"' puts us into string mode when first encountered, and
    // will exit that mode when a second '"' opcode is encountered.
    public boolean _inStringMode = false;

    public void executeInstruction(char c) {
	// If the stack is empty, and is not otherwise caught,
	// ignore the instruction
	try {
	    // Default variables, re-used throughout the big switch..case
	    int a = 0;
	    int b = 0;
	    int x = 0;
	    int y = 0;

	    // Uncomment for debugging info
	    // System.out.println("String mode?" + _inStringMode);

	    // If we are in string mode, read in char as the int version
	    // of its ASCII value.  
	    
	    if (_inStringMode) {
		if (c == '"') {
		    // Uncomment for debugging info
		    // System.out.println("Leaving string mode...");
		    // Leave string mode
		    _inStringMode = false;
		} else {
		    // Get int version of character, put it on the stack
		    int intC = (int) c;
		    _ps.push(intC);
		}
		return;
	    }

	    // If not in string entry mode, we are executing instructions
	    
	    switch (c) {

		// (space) - ignore
	    case ' ':
		// DO NOTHING, KEEP MOVING
		break;
	    
	    // +   Addition: Pop two values a and b, then push the result of a+b
	    case '+':
		add();
		break;

		// -   Subtraction: Pop two values a and b, then push the result of b-a	    
	    case '-':
		subtract();
		break;
	    
		// *   Multiplication: Pop two values a and b, then push the result of a*b
	    case '*':
		multiply();
		break;
		    
		// /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. According to the specifications, if a is zero, ask the user what result they want.
	    case '/':
		divide();
		break;

		// %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
	    case '%':
		modulo();
		break;

	     // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
	    case '!':
		not();
		break;

            // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
	    case '`':
		greaterThan();
		break;

	    // >   PC direction right
	    case '>':
		_d = Direction.RIGHT;
		break;

	    // <   PC direction left
	    case '<':
		_d = Direction.LEFT;
		break;

	    // ^   PC direction up
	    case '^':
		_d = Direction.UP;
		break;

            // v   PC direction down
	    case 'v':
		_d = Direction.DOWN;
		break;

            // ?   Random PC direction
	    case '?':
		randomDir();
		break;

	    // _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
	    case '_':
		horizontalIf();
		break;

		// |   Vertical IF: pop a value; set direction to down if value=0, set to up otherwise
	    case '|':
		verticalIf();
		break;

		// "   Toggle stringmode (push each character's ASCII value all the way up to the next ")
	    case '"':
		_inStringMode = true;
		break;

		// :   Duplicate top stack value
	    case ':':
		duplicate();
		break;

		// \   Swap top stack values
	    case '\\':
		swap();
		break;

		// $   Pop (remove) top stack value and discard
	    case '$':
		pop();
		break;

		// .   Pop top of stack and output as integer
	    case '.':
		printInt();
		break;

		// ,   Pop top of stack and output as ASCII character
	    case ',':
		printChar();
		break;

		// #   Bridge: jump over next command in the current direction of the current PC
	    case '#':
		// Move but don't execute
		moveOneSpace();
		break;

		// g   A "get" call (a way to retrieve data in storage). Pop two values y and x, then push the ASCII value of the character at that position in the program. If (x,y) is out of bounds, push 0
	    case 'g':
		get();
		break;

		// p   A "put" call (a way to store a value for later use). Pop three values y, x and v, then change the character at the position (x,y) in the program to the character with ASCII value v
	    case 'p':
		put();
		break;

		// &   Get integer from user and push it
	    case '&':
		getInt();
		break;

		// ~   Get character from user and push it
	    case '~':
		getChar();
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
		int intC = Character.getNumericValue(c);
		_ps.push(intC);
		break;
		
	    default:  
		System.err.println("'" + c + "' " + "(ASCII "
				   + (int) c + ") NOT SUPPORTED!");
	    
	    }
	} catch (EmptyStackException esex) {
	    // Do nothing
	}

	
    }

    /**
     * Called when TextArea should be updated this iteration
     */

    private void shouldUpdateTa() {
	_updateTa = true;
    }

    
    /**
     * Called when Stack area should be updated this iteration
     */

    private void shouldUpdateStack() {
	_updateStack = true;
    }

    /**
     * Called when Output area should be updated this iteration
     */

    private void shouldUpdateOutput() {
	_updateOutput = true;
    }

    /**
     * Reset whether or not anything should be updated
     * Should be called at beginning of every execution step (iteration)
     */
    
    private void resetUpdates() {
	_updateTa = false;
	_updateStack = false;
	_updateOutput = false;
    }
    
    /**
     * +   Addition: Pop two values a and b, then push the result of a+b
     */
    
    public void add() {
	shouldUpdateStack();
	int a = _ps.pop();
	int b = _ps.pop();
	_ps.push(a + b);
    }

    /**
     * -   Subtraction: Pop two values a and b, then push the result of b-a
     */

    public void subtract() {
	shouldUpdateStack();
	int a = _ps.pop();
	int b = _ps.pop();
	_ps.push(b - a);
    }
    
    /**
     *   Multiplication: Pop two values a and b, then push the result of a*b
     */

    public void multiply() {
	shouldUpdateStack();
	int a = _ps.pop();
	int b = _ps.pop();
	_ps.push(a * b);
    }
    
    /**
     * /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
     */
 
    public void divide() {
	shouldUpdateStack();
	int a = _ps.pop();
	int b = _ps.pop();
	if (a == 0) {
	    _ps.push(0);
	} else {
	    _ps.push(b / a);
	}

    }

    /**
     * %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
     */

    public void modulo() {
	shouldUpdateStack();
	int a = _ps.pop();
	int b = _ps.pop();
	_ps.push(b % a);
    }


    /**
     * !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
     */
    public void not() {
	shouldUpdateStack();
	int a = _ps.pop();
	if (a == 0) {
	    _ps.push(1);
	} else {
	    _ps.push(0);
	}

    }


    /**
     * `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
     */

    public void greaterThan() {
	shouldUpdateStack();

	int a = _ps.pop();
	int b = _ps.pop();
	if (b > a) {
	    _ps.push(1);
	} else {
	    _ps.push(0);
	}
    }
    
    /**
     * ?   Random PC direction
     */

    public void randomDir() {
	int a = _r.nextInt(4);
	switch (a) {
	case 0:
	    _d = Direction.RIGHT;
	    break;
	case 1:
	    _d = Direction.LEFT;
	    break;
	case 2:
	    _d = Direction.UP;
	    break;
	case 3:
	    _d = Direction.DOWN;
	    break;
	}

    }

    /**
     *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
     */

    public void horizontalIf() {
	int a = _ps.pop();
	if (a == 0) {
	    _d = Direction.RIGHT;
	} else {
	    _d = Direction.LEFT;
	}
    }

    /**
     * |   Vertical IF: pop a value; set direction to down if value=0, set to up otherwise
     */
    
    public void verticalIf() {
	int a = _ps.pop();
	if (a == 0) {
	    _d = Direction.DOWN;
	} else {
	    _d = Direction.UP;
	}

    }

    /**
     * :   Duplicate top stack value
     */

    public void duplicate() {
	shouldUpdateStack();
	int a = _ps.peek();
	_ps.push(a);
    }

    /**
     * \   Swap top stack values
     */
    
    public void swap() {
	shouldUpdateStack();
	int a = _ps.pop();
	int b = _ps.pop();
	_ps.push(a);
	_ps.push(b);
    }

    /**
     * $   Pop (remove) top stack value and discard
     */
    
    public void pop() {
	shouldUpdateStack();
	int a = _ps.pop();
    }
    
    /**
     * .   Pop top of stack and output as integer
     */
    
    public void printInt() {
	shouldUpdateStack();
	shouldUpdateOutput();
	int a = _ps.pop();
	_mp.printInt(a);

    }
    
    /**
     * ,   Pop top of stack and output as ASCII character
     */
    public void printChar() {
	shouldUpdateStack();
	shouldUpdateOutput();
	int a = _ps.pop();
	_mp.print((char) a);
    }

    /**
     * g   A "get" call (a way to retrieve data in storage). 
     * Pop two values y and x, then push the ASCII value of the character
     *  at that position in the program. 
     * If (x,y) is out of bounds, push 0
     */
    
    public void get() {
	shouldUpdateStack();
	shouldUpdateTa();

	int x = _ps.pop();
	int y = _ps.pop();
	if (x > _pa._xSize || y > _pa._ySize || x < 0 || y < 0) {
	    _ps.push(0);
	} else {
	    char toWrite = _pa.getOpCode(x, y);
	    _ps.push((int) toWrite);
	}
    }

    /**
     *  p   A "put" call (a way to store a value for later use). 
     * Pop three values x, y and v, then change the character at
     * the position (x,y) in the program to the character with
     * ASCII value v
     */
    
    public void put() {
	shouldUpdateStack();
	shouldUpdateTa();
	int x = _ps.pop();
	int y = _ps.pop();
	int v = _ps.pop();
	if (x > _pa._xSize || y > _pa._ySize || x < 0 || y < 0) {
	    // Ignore, do nothing
	} else {
	    _pa.setOpCode(x, y, (char) v);
	}

    }
    
    /**
     *  &   Get integer from user and push it
     */
    
    public void getInt() {
	shouldUpdateStack();
	int x = _mp.getIntFromUser();
	_ps.push(x);
    }

    /**
     * ~   Get character from user and push ASCII value
     */

    public void getChar() {
	shouldUpdateStack();
	int newChar = _mp.getCharFromUser();
	_ps.push(newChar);
    }
    

    /**
     * Move the PC one space in the current direction.
     * Wrap around to the other side if we hit the ends - e.g.
     * if we hit -1, go to _xSize - 1, or if we hit _xSize, go to
     * 0.
     */
    
    public void moveOneSpace() {
	switch (_d) {
	case LEFT:
	    _y = (_y - 1);
	    if (_y == -1) {
		_y = _pa._ySize - 1;
	    }
	    break;
	case RIGHT:
	    _y = (_y + 1);
	    if (_y == _pa._ySize) {
		_y = 0;
	    }

	    break;
	case UP:
	    _x = (_x - 1);
	    if (_x == -1) {
		_x = _pa._xSize - 1;
	    }

	    break;
	case DOWN:
	    _x = (_x + 1);
	    if (_x == _pa._xSize) {
		_x = 0;
	    }

	    break;
	}
    }

    /**
     * Get and execute the next instruction, and then move to the next
     * instruction.  One "step" of program execution.
     */
    
    public void executeOneStep() {
	// Get character at current location and execute it
	char opCode = _pa.getOpCode(_x, _y);
	// Uncomment next two lines for debugging
	// System.out.println("Loc = [" + _x + "," + _y + "], d = " + _d.toString() + ", op = " + opCode);
	// System.out.println("Stack " + _ps);
	executeInstruction(opCode);
	// Move one space in correct direction
	moveOneSpace();
    }

    /**
     * Constructor
     * Sets up a new program to execute
     * @param mp Reference to MainPanel (for I/O purposes)
     * @param ps ProgramStack to use
     * @param pa ProgramArea (text of program) to execute
     */
    
    public ProgramExecutor(MainPanel mp, ProgramStack ps, ProgramArea pa) {
	_mp = mp;
	_pa = pa;
	_ps = ps;
    }

    /**
     * Restart program
     * Set Location = 0,0
     * Set Direction = left to right (Direction.RIGHT)
     */

    public void setup() {
	_programComplete = false;
	_x = 0;
	_y = 0;
	_d = Direction.RIGHT;

    }

    /**
     * Execute one step of the program for this Executor
     * @return true if program complete, false otherwise
     */

    public boolean step() {
	resetUpdates();
	_mp.highlightChar(_pa, _x, _y);
	executeOneStep();
	_mp.setStack(_ps.toString());
	_mp.refresh(_updateTa, _updateStack, _updateOutput);
	return _programComplete;

    }
    
    /**
     * Run current program for this Executor
     */
    
    public void run(int sleepTime) {
	// Continue while program is not finished running
	// AND stop button not pressed

	while (!_programComplete && !SystemSettings.checkForStop()) {
	    // Uncomment for debugging
	    // System.out.println("Executing @ [" + _x + "," + _y + "]");

	    // If there is some positive amount of time to sleep, do it
	    // If interrupted, ignore and go about your business
	    if (sleepTime > 0) {
		try {
		    Thread.sleep(sleepTime);
		} catch (InterruptedException iex) {
		    // ignore
		}
	    }
	    resetUpdates();
	    _mp.highlightChar(_pa, _x, _y);
	    executeOneStep();
	    _mp.setStack(_ps.toString());
	    _mp.refresh(_updateTa, _updateStack, _updateOutput);
	}
    }

    
}
