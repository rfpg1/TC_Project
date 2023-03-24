/*
	Isto é um comentário em ANTLR
*/

/* Casual has to be the filename */
grammar Grammar;

/* non-terminals start with lowercase */
prog:	(comment|declaration)* ;

/* o ANTLR suport +,?,* das expressões regulares nas regras */
/*
expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
*/
declaration: 
;

comment: '(*'(ALPHANUM|NEWLINE|WHITESPACE)*'*)'
	;

definition: ;
	
		
/* Entre aspas '1' ficam os caracteres correspondentes */

/* terminals start with uppercase, and can be defined using regular expressions. */
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
ALPHANUM: [a-zA-Z0-9][a-zA-Z0-9_]+;
WHITESPACE: [ ]+ -> skip;

