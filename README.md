# JBefunge
Java Befunge-93

This application will execute Befunge programs.  See https://esolangs.org/wiki/Befunge for a description of the language as well as sample Befunge programs.

You do not need to execute arbitrary Befunge programs, or write your own.  All problems are in the interpreting of the commands.  Note that the problems we are looking for are not in the graphical display, nor are they in the Befunge programs themselves.  

You may use the provided FizzBuzz program (coded by yours truly) to track down the performance issues.  Simply copy and paste it into the uppermost (Program Area) textbox.

Although it is not necessary, if you do find any functional defects in my Befunge program implementation, it will mean bonus points for you.

## Performance Issues

There are at least THREE particular methods causing performance issues.

You need to find these methods using VisualVM, write at least three pinning tests for each non-performantmethod, and fix them.  All of your tests should be passing both before and after you make the changes!

This program has gaps in its test coverage.  Be sure when writing pinning tests tht you cover any gaps for the particular method you are working on!

## To Execute

### Compilation of Source and Tests

1. Let's start by compiling the program itself.  `cd` to the `./src` subdirectory
2. Type the command `javac ./com/laboon/*.java`
3. This will have generated the class files in the appropriate directory.
4. `cd ..` to return to the root directory
5. Now compile the existing test suite.  `cd` to the `./test` subdirectory.
6. Type the command `javac -cp .:../src:./junit-4.12.jar:./hamcrest-core-1.3.jar ./com/laboon/*.java`  Note that Windows users may have to replace colons (`:`) with semicolons (`;`).
7. Test classes and a test runner should now exist in the correct (`./com/laboon`) subdirectory.

### Execution of Source and Tests

1. From the ./src subdirectory, type `java com.laboon.JBefunge` to run the program.
2. Copy and paste (or type your own Befunge program!) in the uppermost textbox (the "Program Area").
3. Click the `Run` button to execute the program.  Note that if you have an infinite loop or other defect, you may have to kill the program from the command line (Ctrl-C on Linux, OS X or Windows).  The current stack will appear in the `Stack:` textbox and the output from the program in the `Output` textbox.
4. Now to execute tests.  Go to the `./test` subdirectory.
5. Type `java -cp .:../src:./junit-4.12.jar:./hamcrest-core-1.3.jar com.laboon.TestRunner`.  This will execute the TestRunner.  When you first clone the repo, all tests should pass.

### Tips

1. I recommend having two terminals / command prompts open - one for executing tests and one for running the program.
2. Remember that the program should have the EXACT same output, only be faster than before.  If the output is not the same something is wrong.
3. Several places in the code are commented-out debugging statements.  These may help you if the program is not doing what you think it should be doing.

