# TC Project - sPlash Compiler

## Author
Ricardo Gonçalves 52765

#### Grammar definition
A program will be defined by either a comment, a definition, a declaration or break lines multiple times

Every whitespace, breakline or tab is being ignore so that makes this language case insensitive

\t or \n inside a String is not recognized as a tab or a newline

##### Comment
A comment will begin with (* in the middle can every kind of character include break lines and ends with *). 

##### Declaration
A declaration is a function and will always begin with the name of the function followed by ':' and the return type

It is possible to have refinement but it is not mandatory

Then it is followed by parentheses open and close and between parentheses we have the input separated by commas

The input is defined as the name of the variable and it's type and can also have refinement and again not mandatory

##### Definition
It can be a definition of a variable and its value which is defined as the variable name and its type separated by ':' then a equals sign and the value, which can be String, Double, Int, Float or Boolean

It can also be a function

###### Function
It starts like a declaration and then we open and close brackets instead of ';'

Inside the brackets we can have have multiple statements

##### Statements
Types of staments: 

Variable assignment which can be variable name ':' type '=' a value (String, number, boolean) or the type can be optional in case the variable has been defined before.

return_statement is "return" followed by a value or defining a variable

if_statement is "if" followed by a optional not_operator followed by a boolean_expression then open and close brackets and inside can have multiple statements. After closing brackets we can have an optional else_statement

else_statement is equal do if statement but 'else' instead of 'if', there isn't any else_statement after closing brackets and there isn't any boolean_expression to be evaluated

while_statement is equal do if statement but 'while' instead of 'if' and there isn't any else_statement after closing brackets

###### Boolean Expression

A boolean_expression can condition_value, a boolean_expresion inside parenthesis, a condition_value followed by an operator followed by a condition_value and a condition_value followed by an operator followed by a condition_value followed by an operator and another boolean_expression

###### Condition Value

Can be a number, variable or a boolean value

## Run:

chmod +x script.sh

./script.sh <--tree> <filename>

Example: ./script.sh --tree tests/positive/hello_world.sp
Example: ./script.sh tests/positive/hello_world.sp
