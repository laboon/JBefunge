package com.laboon;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Test methods in ProgramArea class
 */

public class ProgramAreaTest {

    // Test default X size is correct
    @Test
    public void testDefaultXSize() {
	ProgramArea pa = new ProgramArea();
	assertEquals(pa._xSize, 100);
    }

    // Test default Y size is correct
    @Test
    public void testDefaultYSize() {
	ProgramArea pa = new ProgramArea();
	assertEquals(pa._ySize, 100);
    }
    
    // Test that the default will have program area filled up with spaces
    // Note that we only check a sampling of these.
    // Since we are testing basically the same thing, it is not a problem that
    // we have three asserts in one test.  Much more than that and we would
    // be pushing it, though!
    @Test
    public void testDefaultConfig() {
	ProgramArea pa = new ProgramArea();
	assertEquals(pa.getOpCode(0, 0), ' ');
	assertEquals(pa.getOpCode(3, 1), ' ');
	assertEquals(pa.getOpCode(5, 5), ' ');
    }

    // If we pass in a single-line program, see that it gets read in
    // correctly
    @Test
    public void testSingleLineProgram() {
	ProgramArea pa = new ProgramArea("123++@");
	char[] expectedArr = {'1', '2', '3', '+', '+', '@'};
	for (int j = 0; j < 6; j++) {
	    assertEquals(pa.getOpCode(0,j), expectedArr[j]);
	}
    }
	
    // If we pass in a multi-line program, see that it gets read in
    // correctly
    @Test
    public void testMultiLineProgram() {
	ProgramArea pa = new ProgramArea(">123v\n^...<");
	char[] expectedArr1 = {'>', '1', '2', '3', 'v' };
	char[] expectedArr2 = {'^', '.', '.', '.', '<' };
	for (int j = 0; j < 5; j++) {
	    assertEquals(pa.getOpCode(0,j), expectedArr1[j]);
	}
	for (int j = 0; j < 5; j++) {
	    assertEquals(pa.getOpCode(1,j), expectedArr2[j]);
	}

    }

    // Test that writing to in-area writes the correct character
    // to the correct location, and nowhere else
    @Test
    public void testWriteInArea() {
	ProgramArea pa = new ProgramArea("");
	pa.setOpCode(1, 2, '$');
	for (int j = 0; j < pa._xSize; j++) {
	    for (int k = 0; k < pa._ySize; k++) {
		if (j == 1 && k == 2) {
		    assertEquals(pa.getOpCode(j, k), '$');
		} else {
		    assertEquals(pa.getOpCode(j, k), ' ');
		}
	    }
	}
    }

    
    // Test that writing to out-area does nothing
    // We will write to an invalid address and the entire field should
    // still consist only of the default (spaces)
    @Test
    public void testWriteOutArea() {
	ProgramArea pa = new ProgramArea("");
	pa.setOpCode(9000, 9000, '$');
	// Everything should still be spaces
	for (int j = 0; j < pa._xSize; j++) {
	    for (int k = 0; k < pa._ySize; k++) {
		assertEquals(pa.getOpCode(j , k), ' ');
	    }
	}
    }

    
    // Test that reading from in-area returns the correct value
    @Test
    public void testReadInArea() {
	ProgramArea pa = new ProgramArea("123++@");
	assertEquals(pa.getOpCode(0, 5), '@');
    }
    
    // Test that reading from out-area (negative X) returns char 0
    @Test
    public void testReadOutAreaXTooLow() {
	ProgramArea pa = new ProgramArea("123++@");
	assertEquals(pa.getOpCode(-1, 5), (char) 0);
    }

    // Test that reading from out-area (too high X) returns char 0
    @Test
    public void testReadOutAreaXTooHigh() {
	ProgramArea pa = new ProgramArea("123++@");
	assertEquals(pa.getOpCode(9000, 5), (char) 0);
    }

    // Test that reading from out-area (negative Y) returns char 0
    @Test
    public void testReadOutAreaYTooLow() {
	ProgramArea pa = new ProgramArea("123++@");
	assertEquals(pa.getOpCode(-3, -1), (char) 0);
    }

    // Test that reading from out-area (too high X) returns char 0
    @Test
    public void testReadOutAreaYTooHigh() {
	ProgramArea pa = new ProgramArea("123++@");
	assertEquals(pa.getOpCode(2, 9000), (char) 0);
    }

    // Test that toString works correctly for an empty program
    // Given an empty program area, check that all strings are spaces
    // separated by newlines
    @Test
    public void testToStringEmptyProgram() {
	ProgramArea pa = new ProgramArea("");
	String stringRep = pa.toString();
	String[] lines = stringRep.split("\n");
	// There should be _xSize lines
	assertEquals(lines.length, pa._xSize);
	// Each line should be all spaces
	// Trimming removes all spaces, so once they are removed,
	// each line should be of length 0
	for (String line : lines) {
	    assertEquals(line.trim().length(), 0);
	}

    }

    // Test that toString works correctly for an empty program
    // Given an empty program area, check that all strings are spaces
    // separated by newlines
    @Test
    public void testToStringSimpleProgram() {
	String simpleProgram = "123456789@";
	ProgramArea pa = new ProgramArea(simpleProgram);
	String stringRep = pa.toString();
	assertTrue(stringRep.substring(0, 10).equals(simpleProgram));

    }

    
}
