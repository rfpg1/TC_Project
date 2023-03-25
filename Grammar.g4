/*
	Isto é um comentário em ANTLR
*/

/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog:	(comment|declaration|NEWLINE|definition)* ;

/* o ANTLR suport +,?,* das expressões regulares nas regras */

comment: SPACE* '(*'(WORD|NEWLINE|SPACE|NUMBER)*'*)'
;

declaration:
	SPACE* vname_type '(' SPACE* vname_type SPACE* (',' SPACE* vname_type)* SPACE* ')' SPACE* ';'
;

vname_type:
	WORD SPACE* ':' SPACE* type SPACE* refinement?
;

refinement:
	'where' SPACE* WORD SPACE* OPERATOR SPACE* (WORD|NUMBER|TRUE|FALSE)
;


type:
	(DOUBLE|INT|BOOLEAN|FLOAT|STRING)
;

definition:
	(value|function)
;

function:
	SPACE* vname_type SPACE* '(' SPACE* vname_type SPACE* (',' SPACE* vname_type)* SPACE* ')' SPACE* '{'
	(if_else|while_condition)
	SPACE*
	
;

if_else:
	SPACE* 'if' SPACE* (WORD|NUMBER|TRUE|FALSE) SPACE* OPERATOR SPACE* (WORD|NUMBER|TRUE|FALSE) SPACE* '{'
	SPACE* statement* SPACE* '}' (SPACE|NEWLINE)* ('else' SPACE* '{' SPACE* statement SPACE* '}')?
;

while_condition:
	SPACE* 'while' SPACE* (WORD|NUMBER|TRUE|FALSE) SPACE* OPERATOR SPACE* (WORD|NUMBER|TRUE|FALSE) SPACE* '{'
	SPACE* statement* SPACE* '}'
;

statement:
	(value|return_statement)
;

return_statement:
	'return' SPACE* (WORD|NUMBER) SPACE* ';'
;

value:
	vname_type '=' SPACE* NUMBER SPACE* ';'
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



