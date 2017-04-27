# JBefunge
Java Befunge-93

This application will execute Befunge programs.  See https://esolangs.org/wiki/Befunge for a description of the language as well as sample Befunge programs.

## To Execute

### Easy Method

1. If you are running on a Unix-like system, you should be able to execute the `compile.sh`, `run.sh`, and `runTests.sh` scripts to compile, run, and run the tests, respectively.

### Compilation of Source and Tests

#### Details

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

