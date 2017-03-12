package com.laboon;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Test methods in ProgramExecutor class
 * 
 * TODO: These methods do not use stubs/mocks/doubles.  This would
 * be a good refactoring effort in the future!
 */

public class ProgramExecutorTest {

    // Shared ProgramExecutor
    // A new one is created for each test - this is inefficient but
    // ensures a clean slate for each test
    
    public ProgramExecutor _e = null;

    // Create simple executor before each test
    // Consists of a program "123++@" and a stack that already has
    // the values [2, 3, 4] (4 being the "top" of the stack)
    @Before
    public void setup() {
    	ProgramStack ps = new ProgramStack();
    	ps.push(2);
    	ps.push(3);
    	ps.push(4); 
    	ProgramArea pa = new ProgramArea("123++@");
    	MainPanel mp = new MainPanel();
    	_e = new ProgramExecutor(mp, ps, pa);
    }

    // Test the '+' (add) command.   3 + 4 should == 7.
    @Test
    public void testAdd() {
	_e.add();
	assertEquals(_e._ps.peek(), 7);
    }

    // * -   Subtraction: Pop two values a and b, then push the result of b-a
    @Test
    public void testSubtract() {
	_e.subtract();
	assertEquals(_e._ps.peek(), -1);
    }
    
    // *   Multiplication: Pop two values a and b, then push the result of a*b
    @Test
    public void testMultiply() {
	_e.multiply();
	assertEquals(_e._ps.peek(), 12);
    }
    
    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check for "normal" (nonzero) division
    @Test
    public void testDivide() {
	_e._ps.push(25); // b
	_e._ps.push(5);  // a
	_e.divide();
	assertEquals(5, _e._ps.peek());

    }

    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check for "normal" (nonzero) division, rounding down
    @Test
    public void testDivideRounding() {
	_e._ps.push(26); // b
	_e._ps.push(5);  // a
	_e.divide();
	assertEquals(5, _e._ps.peek());

    }

    
    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check that dividing by zero returns 0
    @Test
    public void testDivideZero() {
	_e._ps.push(5);
	_e._ps.push(0);
	_e.divide();
	assertEquals(0, _e._ps.peek());
    }

    
    // %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
    @Test
    public void modulo() {
	_e.modulo();
	assertEquals(3, _e._ps.peek());
    }


    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
// Check for nonzero value returns 0
    @Test
    public void notNonZero() {
	_e._ps.push(1);
	_e.not();
	assertEquals(0, _e._ps.peek());

    }

    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
    // Check for 0 value returns 1
    @Test
    public void testNotZero() {
	_e._ps.push(0);
	_e.not();
	assertEquals(1, _e._ps.peek());
    }
    
    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b > a returns 1
    @Test
    public void testGreaterThanBgtA() {
	_e._ps.push(5); // b
	_e._ps.push(2); // a
	_e.greaterThan();
	assertEquals(1, _e._ps.peek());
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b == a returns 0
    @Test
    public void testGreaterThanBeqA() {
	_e._ps.push(10);
	_e._ps.push(10);
	_e.greaterThan();
	assertEquals(0, _e._ps.peek());
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b < a returns 0
    @Test
    public void testGreaterThanBltA() {
	_e._ps.push(10); // b
	_e._ps.push(99); // a
	_e.greaterThan();
	assertEquals(0, _e._ps.peek());
    }

    
    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going right if value is 0
    
    public void testHorizontalIfZero() {
    	_e._ps.push(0);
	_e.horizontalIf();
	assertEquals(Direction.RIGHT, _e._d);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going left if value is positive
    
    public void testHorizontalIfPositive() {
    	_e._ps.push(19);
	_e.horizontalIf();
	assertEquals(Direction.LEFT, _e._d);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going left if value is positive
    
    public void testHorizontalIfNegative() {
    	_e._ps.push(-3);
	_e.horizontalIf();
	assertEquals(Direction.LEFT, _e._d);
    }

}
