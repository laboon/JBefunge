package com.laboon;

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

}
