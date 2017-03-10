package com.laboon;

import java.util.Stack;

public class ProgramStack {

    private Stack<Integer> _stack = new Stack<Integer>();

    public void push(int val) {
	_stack.push(new Integer(val));
    }

    public int pop() {
	Integer val = _stack.pop();
	return val.intValue();
    }
    
}
