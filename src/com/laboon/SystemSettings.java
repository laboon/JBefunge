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
    
    private static boolean _timer = false;
    
    private static boolean _checkSyntax = false;

    private static boolean _checkEndOpcode = false;

    private static boolean _viewProgramArea = true;

    private static boolean _viewProgramStack = true;

    private static boolean _viewProgramOutput = true;

    private static String _file = "";

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
    public static synchronized boolean checkSyntax() {
	return _checkSyntax;
    }

    public static synchronized void setCheckSyntax(boolean cs) {
	_checkSyntax = cs;
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

    
    
    public static synchronized String getFile() {
	return _file;
    }

    public static synchronized void setFile(String f) {
	_file = f;
    }

    
}
