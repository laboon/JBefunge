package com.laboon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * This class keeps track of system-wide data.
 * IMPORTANT NOTE - Since access is coming via Swing, which is 
 * NOT thread-safe, you should make sure that all methods are
 * synchronized!
 */

public class SystemSettings {

    private static MainWindow _mainWindow = null;

    private static MainPanel _mainPanel = null;

    private static ButtonPanel _buttonPanel = null;
    
    private static boolean _timer = false;
    
    private static boolean _checkEndOpcode = false;

    private static boolean _programRunning = false;

    private static boolean _stepMode = false;
    
    private static boolean _stopPressed = false;
    
    private static String _file = "";

    private static Color _color = Color.YELLOW;

    public static synchronized void setColor(Color c) {
	_color = c;
    }

    public static synchronized Color getColor() {
	return _color;
    }
    
    public static synchronized void setStepMode(boolean stepMode) {
	_stepMode = stepMode;
    }

    public static synchronized boolean inStepMode() {
	return _stepMode;
    }
    
    public static synchronized boolean checkForStop() {
	if (_stopPressed) {
	    // return true, but first reset _stopPressed
	    _stopPressed = false;
	    return true;
	} else {
	    return false;
	}
    }

    public static synchronized void setStop(boolean s) {
	_stopPressed = s;
    }
    
    public static synchronized boolean getProgramRunning() {
	return _programRunning;
    }

    public static synchronized void setProgramRunning(boolean pr) {
	_programRunning = pr;
    }
    
    public static synchronized boolean checkEndOpcode() {
	return _checkEndOpcode;
    }

    public static synchronized void setCheckEndOpcode(boolean ceo) {
	_checkEndOpcode = ceo;
    }

    public static synchronized boolean timer() {
	return _timer;
    }

    public static synchronized void setTimer(boolean timer) {
	_timer = timer;
    }
    
    public static synchronized MainWindow getMainWindow() {
	return _mainWindow;
    }

    public static synchronized void setMainWindow(MainWindow mw) {
	_mainWindow = mw;
    }

    public static synchronized MainPanel getMainPanel() {
	return _mainPanel;
    }

    public static synchronized void setMainPanel(MainPanel mp) {
	_mainPanel = mp;
    }

    public static synchronized ButtonPanel getButtonPanel() {
	return _buttonPanel;
    }

    public static synchronized void setButtonPanel(ButtonPanel bp) {
	_buttonPanel = bp;
    }

    
    public static synchronized String getFile() {
	return _file;
    }

    public static synchronized void setFile(String f) {
	_file = f;
    }

    
}
