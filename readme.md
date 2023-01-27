# S-expression calculator

Write a command line program that acts as a simple calculator: it takes a
single argument as an expression and prints out the integer result of
evaluating it.

Assuming the program is implemented in Python, invocations should look like:

    $ ./calc.py 123
    123

    $ ./calc.py "(add 12 12)"
    24

You're free to use whatever programming language you like, the invocation above
is just an example. The general point of taking an argument and printing out
its evaluation is the only contract to abide by.


## Compilation and Execution Requirements 

This project was implemented with java version 17 and uses maven  version3.6.3

## Compilation

To compile the project use the below maven command
```console
$ mvn clean install
```

## Execution

This project generate a fat jar into the target folder called 'calc.jar' after successful run this command `mvn clean install`, 
once the jar is ready the application can be executed with the command shown below:
```console 
$ java -jar calc.jar '(multiply 3 (multiply (multiply 3 3) 3))'
81

```
Another alternative to run the application is use the script [calc.sh](calc.sh), 
which execute a fat jar [calc.jar](calc.jar) generated as It is mentioned previously.

```console
$ ./calc.sh  '(multiply 2 (add (multiply 2 3) 8))'
28
```




