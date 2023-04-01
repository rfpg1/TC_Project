# TC_Project

## Author
Ricardo Gonçalves 52765

#### Grammar definition
A program will be defined by either a comment, a definition, a declaration or break lines multiple times

##### Comment
A comment will begin with (* in the middle can every kind of character include break lines and ends with *). 

For know the comments can only be made of alpha numeric characters. 

Right know our grammar can identify comments

##### Declaration
A declaration will always begin with the name of the function followed by ':' and the return type

It is possible to have refinement but it is not mandatory

Then it is followed by parentheses open and close and between parentheses we have the input separated by commas

The input is defined as the name of the variable and it's type and can also have refinement and again not mandatory

##### Definition
It can be a definition of a variable and its value which is defined as the variable name and its type separated by ':' then a equals sign and the value, which can be String, Double, Int, Float or Boolean
It can also be a function

###### Function
It starts like a declaration and then we open and close brackets

Inside the brackets we can have and if else situation or a while either one will be followed by a condition and then open and close brackets and inside we have multiple statements

##### Statements
Statements can be a return statement with a value or defining a variable

### TODO:
Allowed other statements

Change definition of number to be Integer or Float or Double

Allow unary operator

Define strings, strings aren't yet allowed

