package com.laboon;

import java.awt.*;
import javax.swing.*;
import java.util.*;


public class MainWindow extends JFrame {

    private final int HEIGHT = 600;
    private final int WIDTH = 800;
    
    private JFrame _frame = new JFrame("JBefunge");

    private MainPanel _mainPanel;

    private ButtonPanel _buttonPanel;

    private JMenuBar _menuBar;

    // File menu and its menu items
    private JMenu _fileMenu;

    private JMenuItem _open;
    private JMenuItem _save;
    private JMenuItem _exit;
    
    // Run menu and its menu items
    
    private JMenu _runMenu;

    private JMenuItem _run;
    private JMenuItem _step;
    
    public MainWindow() {

	// Add menu options
	_menuBar = new JMenuBar();
	_fileMenu = new JMenu("File");
	_runMenu = new JMenu("Run");

	_open = new JMenuItem("Open");
	_save = new JMenuItem("Save");
	_exit = new JMenuItem("Exit");
    
	_run = new JMenuItem("Run");
	_step = new JMenuItem("Step");

	_fileMenu.add(_open);
	_fileMenu.add(_save);
	_fileMenu.add(_exit);
	
	_runMenu.add(_run);
	_runMenu.add(_step);

	_menuBar.add(_fileMenu);
	_menuBar.add(_runMenu);
	
	this.setJMenuBar(_menuBar);
	
	_frame.setSize(WIDTH, HEIGHT);
	// Close program when window is closed
	_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	// Add Main Panel and Button Panel
	
	_mainPanel = new MainPanel();

	_buttonPanel = new ButtonPanel(_mainPanel);
	
	_frame.add(_mainPanel, BorderLayout.NORTH);
	_frame.add(_buttonPanel, BorderLayout.SOUTH);
	
	_frame.setVisible(true);
	
		
    }
    
    
}
