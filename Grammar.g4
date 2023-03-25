/*
	Isto é um comentário em ANTLR
*/

/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog:	(comment|declaration|NEWLINE|definition)* ;

/* o ANTLR suport +,?,* das expressões regulares nas regras */

comment: SPACE* '(*'(WORD|VARIABLE|NEWLINE|SPACE|NUMBER)*'*)'
;

declaration:
	SPACE* vname_type '(' SPACE* vname_type SPACE* (',' SPACE* vname_type)* SPACE* ')' SPACE* ';'
;

vname_type:
	(VARIABLE|WORD) SPACE* ':' SPACE* type SPACE* refinement?
;

refinement:
	'where' SPACE* (VARIABLE|WORD) SPACE* OPERATOR SPACE* (WORD|NUMBER|TRUE|FALSE|VARIABLE)
;


type:
	(DOUBLE|INT|BOOLEAN|FLOAT|STRING)
;

definition:
	(value|function)
;

function:
	SPACE* vname_type SPACE* '(' SPACE* vname_type SPACE* (',' SPACE* vname_type)* SPACE* ')' SPACE* '{'
	(if_else|while_condition)*
	SPACE*
	
;

if_else:
	SPACE* 'if' SPACE* (WORD|VARIABLE|NUMBER|TRUE|FALSE) SPACE* OPERATOR SPACE* (WORD|VARIABLE|NUMBER|TRUE|FALSE) SPACE* '{'
	SPACE* statement* SPACE* '}' (SPACE|NEWLINE)* ('else' SPACE* '{' SPACE* statement SPACE* '}')?
;

while_condition:
	SPACE* 'while' SPACE* (WORD|VARIABLE|NUMBER|TRUE|FALSE) SPACE* OPERATOR SPACE* (WORD|VARIABLE|NUMBER|TRUE|FALSE) SPACE* '{'
	SPACE* statement* SPACE* '}'
;

statement:
	(value|return_statement)
;

return_statement:
	'return' SPACE* (WORD|VARIABLE|NUMBER) SPACE* ';'
;

value:
	vname_type '=' SPACE* (NUMBER|WORD|VARIABLE) SPACE* ';'
;

/* terminals start with uppercase, and can be defined using regular expressions. */
DOUBLE: 'Double';
INT: 'Int';
BOOLEAN: 'Boolean';
FLOAT: 'Float';
STRING: 'String';
TRUE: 'true';
FALSE: 'false';
OPERATOR: '&&' | '||' | '==' | '!=' | '>=' | '<=' | '<' | '>' | '+' | '-' | '*' | '/' | '%';
NEWLINE : [\r\n]+ -> skip;
SPACE: (' ' | '\t') -> skip;
WORD: [a-zA-Z_]+;
NUMBER: [0-9_]+;
VARIABLE: [a-zA-Z_][a-zA-Z0-9_]*;



