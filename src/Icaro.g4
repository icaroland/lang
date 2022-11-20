grammar Icaro;

icaroFile : statement (NEWLINE+ statement)* EOF ;

statement : assignment_statement
            | print_statement ;

assignment_statement : VARIABLE_IDENTIFIER ' ' ASSIGNMENT_OPERATOR ' ' expression ;

print_statement : PRINT_KEYWORD ' ' expression ;

expression : expression ' ' (MULTIPLICATION_OPERATOR | DIVISION_OPERATOR) ' ' expression     # MultiplicationOrDivision
             | expression ' ' (ADDITION_OPERATOR | SUBTRACTION_OPERATOR) ' ' expression      # AdditionOrSubtraction
             | SUBTRACTION_OPERATOR? LEFT_ROUND_BRACKET expression RIGHT_ROUND_BRACKET       # ExpressionInRoundBrackets
             | SUBTRACTION_OPERATOR? POSITIVE_INT_NUMBER                                     # IntNumber
             | SUBTRACTION_OPERATOR? VARIABLE_IDENTIFIER                                     # Variable ;

LEFT_ROUND_BRACKET : '(';

RIGHT_ROUND_BRACKET : ')';

ASSIGNMENT_OPERATOR : '=' ;

MULTIPLICATION_OPERATOR : '*' ;

ADDITION_OPERATOR : '+' ;

SUBTRACTION_OPERATOR : '-' ;

DIVISION_OPERATOR : '/' ;

PRINT_KEYWORD : 'print' ;

NEWLINE : '\n' | '\r' ;

POSITIVE_INT_NUMBER : [0-9]+ ;

VARIABLE_IDENTIFIER : [a-z][A-Za-z0-9]* ;

ANY: . ;