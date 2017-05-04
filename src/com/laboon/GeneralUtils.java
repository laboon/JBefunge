package com.laboon;

import java.io.*;
import java.util.*;

public class GeneralUtils {

    /**
     * Given an array of characters, make a string of 
     * Examples: [1, 2] -> "[ 1, 2 ]"
     *           [3] -> "[ 3 ]";
     * @param chars - array of chars
     * @return String - string form of array
     */
    
    public static String generateCharString(char[] chars) {
	String toReturn = "[ ";
	for (int j = 0; j < chars.length; j++) {
	    if (j == chars.length - 1) {
		toReturn += chars[j] + " ";
	    } else {
		toReturn += chars[j] + ", ";
	    }
	}
	toReturn += "]";
	return toReturn;
    }
    
    /**
     * Given a file to write to, and data to write to it, 
     * it will write the data.
     * If there are _any_ issues, simply writes an error message.
     * SIDE EFFECT: Writes data to specified file.
     * @param f file to write to
     * @param text text to write to
     */
    
    public static void saveFile(File f, String text) {
	try (PrintWriter out = new PrintWriter(f)) {
	    out.println(text);
	} catch (IOException ioex) {
	    System.out.println("Could not write to file " + f.toString());
	}
    }


}
