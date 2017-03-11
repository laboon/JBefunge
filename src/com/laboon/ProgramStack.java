package com.laboon;

import java.util.*;

public class ProgramStack {

    private Stack<Integer> _stack = new Stack<Integer>();

    public void push(int val) {
	_stack.push(new Integer(val));
    }

    public int pop() {
	try {
	    Integer val = _stack.pop();
	    return val.intValue();
	} catch (EmptyStackException esex) {
	    return 0;
	}

    }

    public int peek() {
	Integer val = _stack.peek();
	return val.intValue();
    }
    
    public String toString() {
	return _stack.toString();
    }
    
}
