package com.laboon;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.*;

/**
 * Test methods in ProgramExecutor class
 */

public class ProgramExecutorTest {

    
    // ProgramStack, ProgramArea, MainPanel, ProgramExecutor
    // To be doubled - variable reused for each test
    ProgramStack _ps;
    ProgramArea _pa;
    MainPanel _mp;
    ProgramExecutor _e;

    
    // Create simple doubles before each test and an actual ProgramExecutor
    // (as the latter is the class under test)
    // We can add specific verification/stubs to each double in each test,
    // as they will be created anew for each test that is run

    // Note the @Before means this will run before each test case
    // Use @After for code you want to run after each test case
    // (this is usually called the teardown() method
    // Use @BeforeClass / @AfterClass annotations for code you want
    // to run before/after the entire class is executed
    @Before
    public void setup() {
	_ps = mock(ProgramStack.class);
    	_pa = mock(ProgramArea.class);
    	_mp = mock(MainPanel.class);
	_e = new ProgramExecutor(_mp, _ps, _pa);

    }

    // Test the '+' (add) command.   3 + 3 should == 6.
    @Test
    public void testAdd() {
	when(_ps.pop()).thenReturn(3).thenReturn(3);
	_e.add();
	verify(_ps).push(6);
    }

    // * -   Subtraction: Pop two values a and b, then push the result of b-a
    // Check that 5 - 4 will return 1
    @Test
    public void testSubtract() {
    	when(_ps.pop()).thenReturn(4).thenReturn(5);
    	_e.subtract();
    	verify(_ps).push(1);
    }
    
    // *   Multiplication: Pop two values a and b, then push the result of a*b
    @Test
    public void testMultiply() {
    	when(_ps.pop()).thenReturn(5).thenReturn(2);
    	_e.multiply();
    	verify(_ps).push(10);

    }
    
    //   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check for "normal" (nonzero) division
    @Test
    public void testDivide() {
	when(_ps.pop()).thenReturn(5).thenReturn(25);
    	_e.divide();
        verify(_ps).push(5);
    }

    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check for "normal" (nonzero) division, rounding down
    @Test
    public void testDivideRounding() {
	when(_ps.pop()).thenReturn(5).thenReturn(26);
    	_e.divide();
        verify(_ps).push(5);

    }
    
    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check that dividing 0 divided by something returns 0
    @Test
    public void testDivideZeroNumerator() {
	when(_ps.pop()).thenReturn(5).thenReturn(0);
    	_e.divide();
        verify(_ps).push(0);
    }

    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check that dividing something by zero returns 0
    @Test
    public void testDivideZeroDenominator() {
	when(_ps.pop()).thenReturn(0).thenReturn(5);
    	_e.divide();
        verify(_ps).push(0);
    }

    // %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
    // Check that 6 % 5 = 1
    @Test
    public void modulo() {
	when(_ps.pop()).thenReturn(5).thenReturn(6);
	_e.modulo();
        verify(_ps).push(1);
    }


    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
    // Check for nonzero value returns 0
    @Test
    public void notNonZero() {
	when(_ps.pop()).thenReturn(6);
	_e.not();
	verify(_ps).push(0);
    }

    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
    // Check for 0 value returns 1
    @Test
    public void testNotZero() {
	when(_ps.pop()).thenReturn(0);
	_e.not();
	verify(_ps).push(1);
    }
    
    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b > a returns 1 ( 5 > 2 -> 1 )
    @Test
    public void testGreaterThanBgtA() {
	when(_ps.pop()).thenReturn(2).thenReturn(5);
	_e.greaterThan();
	verify(_ps).push(1);
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b == a pushes 0
    @Test
    public void testGreaterThanBeqA() {
	when(_ps.pop()).thenReturn(10).thenReturn(10);
	_e.greaterThan();
	verify(_ps).push(0);
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b < a returns 0
    @Test
    public void testGreaterThanBltA() {
	when(_ps.pop()).thenReturn(99).thenReturn(10);
	_e.greaterThan();
	verify(_ps).push(0);
    }

    
    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going right if value is 0
    
    public void testHorizontalIfZero() {
	when(_ps.pop()).thenReturn(0);
	_e.horizontalIf();
	assertEquals(Direction.RIGHT, _e._d);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going left if value is positive    
    public void testHorizontalIfPositive() {
	when(_ps.pop()).thenReturn(19);
	_e.horizontalIf();
	assertEquals(Direction.LEFT, _e._d);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going left if value is positive
    
    public void testHorizontalIfNegative() {
	when(_ps.pop()).thenReturn(-3);
	_e.horizontalIf();
	assertEquals(Direction.LEFT, _e._d);
    }

}
