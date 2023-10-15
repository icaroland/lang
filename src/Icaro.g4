grammar Icaro;

icaroFile : statement (NEWLINE+ statement)* EOF ;

statement : assignment_statement
            | print_statement ;

assignment_statement : VARIABLE_IDENTIFIER ' ' ASSIGNMENT_OPERATOR ' ' expression ;

print_statement : PRINT_KEYWORD ' ' expression ;

expression : math_expression
           | bool_expression;

math_expression : math_expression ' ' (MULTIPLICATION_OPERATOR | DIVISION_OPERATOR) ' ' math_expression       # MultiplicationOrDivision
                | math_expression ' ' (ADDITION_OPERATOR | SUBTRACTION_OPERATOR) ' ' math_expression          # AdditionOrSubtraction
                | SUBTRACTION_OPERATOR? LEFT_ROUND_BRACKET math_expression RIGHT_ROUND_BRACKET                # ExpressionInRoundBrackets
                | SUBTRACTION_OPERATOR? POSITIVE_INT_NUMBER                                                   # IntNumber
                | SUBTRACTION_OPERATOR? VARIABLE_IDENTIFIER                                                   # Variable ;

bool_expression : NOT_BOOL_OPERATOR? LEFT_ROUND_BRACKET bool_expression RIGHT_ROUND_BRACKET                   # BoolNotExpression
                | bool_expression ' ' AND_BOOL_OPERATOR ' ' bool_expression                                   # BoolAndExpression
                | bool_expression ' ' OR_BOOL_OPERATOR ' ' bool_expression                                    # BoolOrExpression
                | NOT_BOOL_OPERATOR? TRUE_BOOL_VALUE                                                          # TrueBoolValue
                | NOT_BOOL_OPERATOR? FALSE_BOOL_VALUE                                                         # FalseBoolValue ;

TRUE_BOOL_VALUE : 'true';

FALSE_BOOL_VALUE : 'false';

NOT_BOOL_OPERATOR : '!';

OR_BOOL_OPERATOR : '||';

AND_BOOL_OPERATOR : '&&';

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