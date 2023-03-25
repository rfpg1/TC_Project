/*
	Isto é um comentário em ANTLR
*/

/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog:	(comment|declaration|NEWLINE|definition)* ;

/* o ANTLR suport +,?,* das expressões regulares nas regras */

comment: '(*'(WORD|NEWLINE|SPACE|NUMBER)*'*)'
;

declaration:
	vname_type '(' SPACE* vname_type SPACE* (',' SPACE* vname_type)* SPACE* ')' SPACE* ';'
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
	(value)
;

function:
	
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
SPACE: ' ' -> skip;
WORD: [a-zA-Z_]+;
NUMBER: [0-9_]+;




