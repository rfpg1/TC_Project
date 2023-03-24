/*
	Isto é um comentário em ANTLR
*/

/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog:	(comment|declaration|NEWLINE)* ;

/* o ANTLR suport +,?,* das expressões regulares nas regras */

comment: '(*'(WORD|NEWLINE|SPACE|NUMBER)*'*)'
;

declaration:
	WORD SPACE* ':' SPACE* type SPACE* ';'
;


type:
	(DOUBLE|INT)
;

/* terminals start with uppercase, and can be defined using regular expressions. */
DOUBLE: 'Double';
INT: 'Int';
NEWLINE : [\r\n]+ -> skip;
SPACE: ' ' -> skip;
WORD: [a-zA-Z]+;
NUMBER: [0-9];




