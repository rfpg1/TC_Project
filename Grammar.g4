/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog: (comment|declaration|definition|statement)*;

comment:
	LEFT_PAR STAR (ANYCHAR|VARIABLE|number|operator|reserved_words)* STAR RIGHT_PAR
;

reserved_words:
	RETURN
	|IF_COND
	|ELSE_COND
	|WHILE_COND
	|COMMA
	|LEFT_PAR
	|RIGHT_PAR
	|LEFT_BRACKET
	|RIGHT_BRAKCET
	|SEMICOLON
	|LEFT_R
	|RIGHT_R
	|ASPAS
	|DOUBLE_POINTS
	|GET_ARRAY
	|EQUALS
	|WHERE
	|STAR
;

declaration:
	vname_type LEFT_PAR args_def? RIGHT_PAR SEMICOLON
;

vname_type:
	(VARIABLE) DOUBLE_POINTS type refinement? /* Nomes das funções não podem começar com números */
;

type:
	(DOUBLE|INT|BOOLEAN|FLOAT|STRING)(LEFT_R RIGHT_R)?
;

refinement:
	WHERE (VARIABLE) operator (number|string_lit|TRUE|FALSE)
;

number:
	(NUMBER_INT|NUMBER_FLOAT)
;

string_lit:
	ASPAS (ANYCHAR|number|operator|VARIABLE|TRUE|FALSE)* ASPAS
;

statement:
	(expression|value|if_statement|while_statement|arrays|declaration|comment)
;

value:
	vname_type_optional EQUALS (number|string_lit|VARIABLE|expr) SEMICOLON
;

expr:
	expr_value
	|LEFT_PAR expr_value RIGHT_PAR
	|expr_value (MATH_OPERATOR|STAR) expr_value
	|expr_value (MATH_OPERATOR|STAR) expr_value (MATH_OPERATOR|STAR) expr
;

expr_value:
	(number|string_lit|VARIABLE)
;

vname_type_optional:
	(VARIABLE) (DOUBLE_POINTS type)? refinement? /* Nomes das funções não podem começar com números */
;

arrays
 : VARIABLE LEFT_R position RIGHT_R SEMICOLON
 | GET_ARRAY LEFT_PAR RIGHT_PAR LEFT_R position RIGHT_R SEMICOLON
 ;

position:
	pos
	|LEFT_PAR pos RIGHT_PAR
	|pos (MATH_OPERATOR|STAR) pos
	|pos (MATH_OPERATOR|STAR) pos (MATH_OPERATOR|STAR) position
;

pos:
	(NUMBER_INT|VARIABLE)
;

expression:
	(function_call
	| (number|VARIABLE|ANYCHAR)* SEMICOLON)
;

function_call:
	VARIABLE LEFT_PAR args_value? RIGHT_PAR SEMICOLON
;

return_statement:
	RETURN (VARIABLE|number|string_lit|TRUE|FALSE) SEMICOLON comment*?
;

definition:
	(function)
;

function:
	vname_type LEFT_PAR args_def? RIGHT_PAR
	LEFT_BRACKET
	(statement|function|comment)* return_statement?
	RIGHT_BRAKCET
;

if_statement:
	IF_COND (NOT_OPERATOR)?boolean_expression LEFT_BRACKET
	(statement*) return_statement?
	RIGHT_BRAKCET else_statement?
;

else_statement:
	ELSE_COND LEFT_BRACKET (statement*) return_statement? RIGHT_BRAKCET
;

while_statement:
	WHILE_COND (NOT_OPERATOR)? boolean_expression LEFT_BRACKET (statement*)return_statement? RIGHT_BRAKCET
;

boolean_expression:
	(LEFT_PAR boolean_expression RIGHT_PAR
	|conditions_values
	| conditions_values operator conditions_values
	| (conditions_values operator conditions_values operator boolean_expression)
	)
;

conditions_values:
	VARIABLE
	|TRUE
	|FALSE
	|number
;

args_def:
	vname_type (COMMA vname_type)*
;

args_value:
	(number|string_lit|TRUE|FALSE) (COMMA (number|string_lit|TRUE|FALSE))*
;

operator:
	(MATH_OPERATOR | BOOLEAN_OPERATOR|STAR)
;

/* terminals start with uppercase, and can be defined using regular expressions. */
DOUBLE: 'Double';
INT: 'Int';
BOOLEAN: 'Boolean';
FLOAT: 'Float';
STRING: 'String';
TRUE: 'true';
FALSE: 'false';
RETURN: 'return';
IF_COND: 'if';
ELSE_COND: 'else';
WHILE_COND: 'while';
COMMA: ',';
LEFT_PAR: '(';
RIGHT_PAR: ')';
LEFT_BRACKET: '{';
RIGHT_BRAKCET: '}';
SEMICOLON: ';';
LEFT_R: '[';
RIGHT_R: ']';
ASPAS: '"';
DOUBLE_POINTS: ':';
GET_ARRAY: 'get_array';
EQUALS: '=';
WHERE: 'where';
STAR: '*';
NUMBER_INT: [0-9_]+; /* Underscore can be in any position */
NUMBER_FLOAT: ('.'[0-9]+|[0-9]+'.'[0-9]+);
MATH_OPERATOR: '+' | '-' | '*' | '/' | '%';
BOOLEAN_OPERATOR: '&&' | '||' | '==' | '!=' | '>=' | '<=' | '<' | '>' ;
NOT_OPERATOR: '!';
NEWLINE : [\r\n]+ -> skip;
SPACE: (' '|'\t') -> skip;
VARIABLE: [a-zA-Z_][a-zA-Z0-9_]*;
ANYCHAR: (.)+?;


