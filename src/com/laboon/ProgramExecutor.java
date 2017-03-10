package com.laboon;

import java.util.*;

public class ProgramExecutor {

    private MainPanel _mp;
    
    private boolean _programComplete = false;
    
    private ProgramArea _pa;

    private ProgramStack _ps;

    private Direction _d = Direction.RIGHT;

    private int _x = 0;

    private int _y = 0;

    private boolean _inStringMode = false;

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
    public void executeInstruction(char c) {
	// If the stack is empty, ignore the instruction
	try {
	    int a = 0;
	    int b = 0;
	    int x = 0;
	    int y = 0;
	
	    // System.out.println("String mode?" + _inStringMode);
	    if (_inStringMode) {
		if (c == '"') {
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
	
	    switch (c) {

		// (space) - ignore
	    case ' ':
		// DO NOTHING, KEEP MOVING
		break;
	    
		// +   Addition: Pop two values a and b, then push the result of a+b
	    case '+':
		a = _ps.pop();
		b = _ps.pop();
		_ps.push(a + b);
		break;

		// -   Subtraction: Pop two values a and b, then push the result of b-a	    
	    case '-':
		a = _ps.pop();
		b = _ps.pop();
		_ps.push(b - a);

		break;
	    
		// *   Multiplication: Pop two values a and b, then push the result of a*b
	    case '*':
		a = _ps.pop();
		b = _ps.pop();
		_ps.push(a * b);

		break;
		    
		// /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. According to the specifications, if a is zero, ask the user what result they want.
	    case '/':
		a = _ps.pop();
		b = _ps.pop();
		if (a == 0) {
		    _ps.push(0);
		} else {
		    _ps.push(b / a);
		}
		break;

		// %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
	    case '%':
		a = _ps.pop();
		b = _ps.pop();
		_ps.push(b % a);
		break;

		// !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
	    case '!':
		a = _ps.pop();
		if (a == 0) {
		    _ps.push(1);
		} else {
		    _ps.push(0);
		}
		break;

		// `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
	    case '`':
		a = _ps.pop();
		b = _ps.pop();
		if (b > a) {
		    _ps.push(1);
		} else {
		    _ps.push(0);
		}
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
		System.err.println(c + "NOT YET IMPLEMENTED!");
		break;

		// _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
	    case '_':
		a = _ps.pop();
		if (a == 0) {
		    _d = Direction.RIGHT;
		} else {
		    _d = Direction.LEFT;
		}
		break;

		// |   Vertical IF: pop a value; set direction to down if value=0, set to up otherwise
	    case '|':
		a = _ps.pop();
		if (a == 0) {
		    _d = Direction.DOWN;
		} else {
		    _d = Direction.UP;
		}

		break;

		// "   Toggle stringmode (push each character's ASCII value all the way up to the next ")
	    case '"':
		_inStringMode = true;
		break;

		// :   Duplicate top stack value
	    case ':':
		a = _ps.peek();
		_ps.push(a);
		break;

		// \   Swap top stack values
	    case '\\':
		a = _ps.pop();
		b = _ps.pop();
		_ps.push(a);
		_ps.push(b);
		break;

		// $   Pop (remove) top stack value and discard
	    case '$':
		a = _ps.pop();
		break;

		// .   Pop top of stack and output as integer
	    case '.':
		a = _ps.pop();
		_mp.printInt(a);
		System.out.print(a);
		break;

		// ,   Pop top of stack and output as ASCII character
	    case ',':
		a = _ps.pop();
		_mp.print((char) a);
		System.out.print((char) a);
		break;

		// #   Bridge: jump over next command in the current direction of the current PC
	    case '#':
		// Move but don't execute
		moveOneSpace();
		break;

		// g   A "get" call (a way to retrieve data in storage). Pop two values y and x, then push the ASCII value of the character at that position in the program. If (x,y) is out of bounds, push 0
	    case 'g':
		y = _ps.pop();
		x = _ps.pop();
		if (x > _pa._xSize || y > _pa._ySize || x < 0 || y < 0) {
		    _ps.push(0);
		} else {
		    char toWrite = _pa.getOpCode(x, y);
		    _ps.push((int) toWrite);
		}
		break;

		// p   A "put" call (a way to store a value for later use). Pop three values y, x and v, then change the character at the position (x,y) in the program to the character with ASCII value v
	    case 'p':

		y = _ps.pop();
		x = _ps.pop();
		int v = _ps.pop();
		if (x > _pa._xSize || y > _pa._ySize || x < 0 || y < 0) {
		    // Ignore, do nothing
		} else {
		    _pa.setOpCode(x, y, (char) v);
		}

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
		int intC = Character.getNumericValue(c);
		_ps.push(intC);
		break;
		
	    default:  
		System.err.println(x + "NOT SUPPORTED!");
	    
	    }
	} catch (EmptyStackException esex) {
	    // Do nothing
	}

	
    }

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
    
    public void executeOneStep() {
	// Get character at current location and execute it
	char opCode = _pa.getOpCode(_x, _y);
	// System.out.println("Loc = [" + _x + "," + _y + "], d = " + _d.toString() + ", op = " + opCode);
	executeInstruction(opCode);
	// Move one space in correct direction
	moveOneSpace();
    }
    
    public ProgramExecutor(MainPanel mp, ProgramStack ps, ProgramArea pa) {
	_mp = mp;
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
	_d = Direction.RIGHT;
	while (!_programComplete) {
	    // System.out.println("Executing @ [" + _x + "," + _y + "]");
	    executeOneStep();
	    // System.out.println("Stack " + _ps);
	    _mp.setStack(_ps.toString());
	    // Give some time to redraw screen
	    // try {
	    // 	_mp.refresh();
	    // 	Thread.sleep(100);
	    // } catch (InterruptedException iex) {
	    // 	// do nothing
	    // }
	}
    }

    public void step() {
    }
    
}
