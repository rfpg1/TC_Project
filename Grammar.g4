/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog: (comment|declaration|definition|statement)*EOF;

comment:
	'(*' (ANYCHAR|VARIABLE|number|operator)* '*)'
;

declaration:
	vname_type '('args_def?')' ';'
;

vname_type:
	(VARIABLE) ':' type refinement? /* Nomes das funções não podem começar com números */
;

type:
	(DOUBLE|INT|BOOLEAN|FLOAT|STRING)('[]')?
;

refinement:
	'where' (VARIABLE) operator (number|string_lit|TRUE|FALSE)
;

number:
	(NUMBER_INT|NUMBER_FLOAT)
;

string_lit:
	'"' (ANYCHAR|number|operator|VARIABLE|TRUE|FALSE)* '"'
;

statement:
	(return_statement|expression|value|if_statement|while_statement|arrays)
;

value:
	vname_type '=' (number|string_lit|VARIABLE) ';'
;

arrays
 : VARIABLE '[' position ']' ';'
 | 'get_array' '(' ')' '[' position ']' ';'
 ;

position:
	pos
	| '(' pos ')'
	|pos MATH_OPERATOR pos
	|position (MATH_OPERATOR) pos (MATH_OPERATOR) position
;

pos:
	(NUMBER_INT|VARIABLE)
;

expression:
	(function_call
	| (number|VARIABLE|ANYCHAR)*';')
;

function_call:
	VARIABLE'('args_value? ')' ';'
;

return_statement:
	'return' (VARIABLE|number|string_lit|TRUE|FALSE) ';'
;

definition:
	(function)
;

function:
	vname_type '('args_def?')'
	'{'
	(statement)*
	'}'
;

if_statement:
	'if' (NOT_OPERATOR)?boolean_expression '{'
	statement*
	'}' else_statement?
;

else_statement:
	'else' '{' statement* '}'
;

while_statement:
	'while' (NOT_OPERATOR)? boolean_expression '{' statement* '}'
;

boolean_expression:
	('(' boolean_expression ')'
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
	vname_type (',' vname_type)*
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
NUMBER_FLOAT: ('.'[0-9]+|[0-9]+'.'[0-9]+);
MATH_OPERATOR: '+' | '-' | '*' | '/' | '%';
BOOLEAN_OPERATOR: '&&' | '||' | '==' | '!=' | '>=' | '<=' | '<' | '>' ;
NOT_OPERATOR: '!';
NEWLINE : [\r\n]+ -> skip;
SPACE: (' '|'\t') -> skip;
VARIABLE: [a-zA-Z_][a-zA-Z0-9_]*;
ANYCHAR: (.)+?;


