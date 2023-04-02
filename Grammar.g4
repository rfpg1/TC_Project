/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog: (comment|declaration|NEWLINE|SPACE|definition|statement)*EOF;

comment:
	'(*' (ANYCHAR|VARIABLE|SPACE|NEWLINE|number|operator)* '*)'
;

declaration:
	vname_type '('args_def?')' SPACE* ';'
;

vname_type:
	(VARIABLE) SPACE* ':' SPACE* type SPACE* refinement? /* Nomes das funções não podem começar com números */
;

type:
	(DOUBLE|INT|BOOLEAN|FLOAT|STRING)
;

refinement:
	'where' SPACE* (VARIABLE) SPACE* operator SPACE* (number|string_lit|TRUE|FALSE)
;

number:
	(NUMBER_INT|NUMBER_FLOAT)
;

string_lit:
	'"' (ANYCHAR|number|operator|SPACE)* '"'
;

statement:
	(return_statement|expression|value|if_statement|while_statement|arrays)
;

value:
	vname_type '=' SPACE* (number|string_lit|VARIABLE) SPACE* ';'
;

arrays:
	(VARIABLE '['position']' ';'
	|'get_array()['position']' ';'
	)
;

position:
	pos
	|pos SPACE* (MATH_OPERATOR) SPACE* pos
	|pos SPACE* (MATH_OPERATOR) SPACE* pos SPACE* (MATH_OPERATOR) SPACE* position
;

pos:
	(NUMBER_INT|VARIABLE)
;

expression:
	(function_call
	| (number|VARIABLE|ANYCHAR|SPACE)*';')
;

function_call:
	VARIABLE'('args_value? ')' ';'
;

return_statement:
	'return' SPACE* (VARIABLE|number) SPACE* ';'
;

definition:
	(function)
;

function:
	vname_type SPACE* '('SPACE* args_def? SPACE*')' SPACE*
	'{'
	(statement|SPACE)*
	'}'
;

if_statement:
	'if' SPACE* boolean_expression SPACE* '{'
	statement*
	'}' else_statement?
;

else_statement:
	'else' '{' statement* '}'
;

while_statement:
	'while' boolean_expression '{' statement* '}'
;

boolean_expression:
	(conditions_values
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
	vname_type SPACE* (',' vname_type SPACE*)*
;

args_value:
	(number|string_lit|TRUE|FALSE) (',' (number|string_lit|TRUE|FALSE))*
;

operator:
	(MATH_OPERATOR | BOOLEAN_OPERATOR)
;

/* terminals start with uppercase, and can be defined using regular expressions. */

DOUBLE: 'Double';
INT: 'Int';
BOOLEAN: 'Boolean';
FLOAT: 'Float';
STRING: 'String';
TRUE: 'true';
FALSE: 'false';
NUMBER_INT: [0-9_]+; /* Underscore can be in any position */
NUMBER_FLOAT: ('.'[0-9]+|[0-9]+.[0-9]+);
MATH_OPERATOR: '+' | '-' | '*' | '/' | '%';
BOOLEAN_OPERATOR: '&&' | '||' | '==' | '!=' | '>=' | '<=' | '<' | '>' ;
NEWLINE : [\r\n]+ -> skip;
SPACE: (' '|'\t') -> skip;
VARIABLE: [a-zA-Z_][a-zA-Z0-9_]*;
ANYCHAR: (.)+?;
