// Generated from Grammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(GrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(GrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(GrammarParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(GrammarParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#open_comment}.
	 * @param ctx the parse tree
	 */
	void enterOpen_comment(GrammarParser.Open_commentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#open_comment}.
	 * @param ctx the parse tree
	 */
	void exitOpen_comment(GrammarParser.Open_commentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#close_comment}.
	 * @param ctx the parse tree
	 */
	void enterClose_comment(GrammarParser.Close_commentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#close_comment}.
	 * @param ctx the parse tree
	 */
	void exitClose_comment(GrammarParser.Close_commentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#reserved_words}.
	 * @param ctx the parse tree
	 */
	void enterReserved_words(GrammarParser.Reserved_wordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#reserved_words}.
	 * @param ctx the parse tree
	 */
	void exitReserved_words(GrammarParser.Reserved_wordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(GrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(GrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#vname_type}.
	 * @param ctx the parse tree
	 */
	void enterVname_type(GrammarParser.Vname_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#vname_type}.
	 * @param ctx the parse tree
	 */
	void exitVname_type(GrammarParser.Vname_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#refinement}.
	 * @param ctx the parse tree
	 */
	void enterRefinement(GrammarParser.RefinementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#refinement}.
	 * @param ctx the parse tree
	 */
	void exitRefinement(GrammarParser.RefinementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(GrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(GrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#string_lit}.
	 * @param ctx the parse tree
	 */
	void enterString_lit(GrammarParser.String_litContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#string_lit}.
	 * @param ctx the parse tree
	 */
	void exitString_lit(GrammarParser.String_litContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GrammarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GrammarParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expression(GrammarParser.Boolean_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#boolean_expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expression(GrammarParser.Boolean_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#conditions_values}.
	 * @param ctx the parse tree
	 */
	void enterConditions_values(GrammarParser.Conditions_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#conditions_values}.
	 * @param ctx the parse tree
	 */
	void exitConditions_values(GrammarParser.Conditions_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(GrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(GrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expr_value}.
	 * @param ctx the parse tree
	 */
	void enterExpr_value(GrammarParser.Expr_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expr_value}.
	 * @param ctx the parse tree
	 */
	void exitExpr_value(GrammarParser.Expr_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#vname_type_optional}.
	 * @param ctx the parse tree
	 */
	void enterVname_type_optional(GrammarParser.Vname_type_optionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#vname_type_optional}.
	 * @param ctx the parse tree
	 */
	void exitVname_type_optional(GrammarParser.Vname_type_optionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arrays}.
	 * @param ctx the parse tree
	 */
	void enterArrays(GrammarParser.ArraysContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arrays}.
	 * @param ctx the parse tree
	 */
	void exitArrays(GrammarParser.ArraysContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#position}.
	 * @param ctx the parse tree
	 */
	void enterPosition(GrammarParser.PositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#position}.
	 * @param ctx the parse tree
	 */
	void exitPosition(GrammarParser.PositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#pos}.
	 * @param ctx the parse tree
	 */
	void enterPos(GrammarParser.PosContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#pos}.
	 * @param ctx the parse tree
	 */
	void exitPos(GrammarParser.PosContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(GrammarParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(GrammarParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(GrammarParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(GrammarParser.Return_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(GrammarParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(GrammarParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(GrammarParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(GrammarParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(GrammarParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(GrammarParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void enterElse_statement(GrammarParser.Else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void exitElse_statement(GrammarParser.Else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statement(GrammarParser.While_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statement(GrammarParser.While_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#args_def}.
	 * @param ctx the parse tree
	 */
	void enterArgs_def(GrammarParser.Args_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args_def}.
	 * @param ctx the parse tree
	 */
	void exitArgs_def(GrammarParser.Args_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#args_value}.
	 * @param ctx the parse tree
	 */
	void enterArgs_value(GrammarParser.Args_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args_value}.
	 * @param ctx the parse tree
	 */
	void exitArgs_value(GrammarParser.Args_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(GrammarParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(GrammarParser.OperatorContext ctx);
}