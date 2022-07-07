# The Icaro Programming Language

<img align="center" style="width:550px;height:300px" src="./images/icaro_logo.png">

<br>
<br>

## v0.3.0 (TODO)

### high level update
* #### user perspective
  * float type added
  * basic type system added (e.g number: int = 2.4)
* #### technical perspective
  * float can't be assigned to integer (e.g: 2.4 can't became 2)
  * int can be assigned to float (e.g: 2 can became 2.0)

### Tokens added

1. FLOAT_TYPE_DECLARATION
2. INT_TYPE_DECLARATION

<br>

## v0.2.0 (TODO)

### high level update
* #### user perspective
    * modulo and exponentiation math operators added
    * left and right round parenthesis added

### Tokens added

1. MODULO_OPERATOR
2. EXPONENTIATION_OPERATOR
3. LEFT_ROUND_BRACKET
4. RIGHT_ROUND_BRACKET

<br>

## v0.1.0 - hello world (in progress)

### high level language features
* #### user perspective
  * solves only arithmetic expressions with the four basic operators, number literal and variables
  * defining a variable has the same syntax of reassign values to it 
  * implicitly supports only integer numbers but has no type system
  * prints things at the screen
* #### technical perspective
  * makes use of Antlr4
  * this language gets parsed to JVM bytecode

### Language tokens

1. ASSIGN_OPERATOR
2. MULTIPLICATION_OPERATOR
3. SUBTRACTION_OPERATOR
4. ADDITION_OPERATOR
5. DIVISION_OPERATOR
10. STATEMENT_TERMINATOR
11. IDENTIFIER
12. INT_NUMBER
13. PRINT_KEYWORD
