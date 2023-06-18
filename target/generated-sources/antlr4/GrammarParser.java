// Generated from Grammar.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, VOID=2, DOUBLE=3, INT=4, BOOLEAN=5, STRING=6, TRUE=7, FALSE=8, 
		RETURN=9, IF_COND=10, ELSE_COND=11, WHILE_COND=12, COMMA=13, LEFT_PAR=14, 
		RIGHT_PAR=15, LEFT_BRACKET=16, RIGHT_BRAKCET=17, SEMICOLON=18, LEFT_R=19, 
		RIGHT_R=20, ASPAS=21, DOUBLE_POINTS=22, GET_ARRAY=23, EQUALS=24, WHERE=25, 
		STAR=26, NUMBER_INT=27, NUMBER_FLOAT=28, MATH_OPERATOR=29, BOOLEAN_OPERATOR=30, 
		CONDITIONAL_OPERATOR=31, NOT_OPERATOR=32, NEWLINE=33, SPACE=34, VARIABLE=35, 
		ANYCHAR=36;
	public static final int
		RULE_prog = 0, RULE_comment = 1, RULE_open_comment = 2, RULE_close_comment = 3, 
		RULE_reserved_words = 4, RULE_declaration = 5, RULE_vname_type = 6, RULE_type = 7, 
		RULE_refinement = 8, RULE_number = 9, RULE_string_lit = 10, RULE_statement = 11, 
		RULE_value = 12, RULE_boolean_expression = 13, RULE_conditions_values = 14, 
		RULE_expr = 15, RULE_expr_value = 16, RULE_vname_type_optional = 17, RULE_arrays = 18, 
		RULE_array_def = 19, RULE_position = 20, RULE_pos = 21, RULE_expression = 22, 
		RULE_function_call = 23, RULE_return_statement = 24, RULE_definition = 25, 
		RULE_function = 26, RULE_if_statement = 27, RULE_else_statement = 28, 
		RULE_while_statement = 29, RULE_args_def = 30, RULE_args_value = 31, RULE_operator = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "comment", "open_comment", "close_comment", "reserved_words", 
			"declaration", "vname_type", "type", "refinement", "number", "string_lit", 
			"statement", "value", "boolean_expression", "conditions_values", "expr", 
			"expr_value", "vname_type_optional", "arrays", "array_def", "position", 
			"pos", "expression", "function_call", "return_statement", "definition", 
			"function", "if_statement", "else_statement", "while_statement", "args_def", 
			"args_value", "operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'Void'", "'Double'", "'Int'", "'Boolean'", "'String'", 
			"'true'", "'false'", "'return'", "'if'", "'else'", "'while'", "','", 
			"'('", "')'", "'{'", "'}'", "';'", "'['", "']'", "'\"'", "':'", "'get_array'", 
			"'='", "'where'", "'*'", null, null, null, null, null, "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "VOID", "DOUBLE", "INT", "BOOLEAN", "STRING", "TRUE", "FALSE", 
			"RETURN", "IF_COND", "ELSE_COND", "WHILE_COND", "COMMA", "LEFT_PAR", 
			"RIGHT_PAR", "LEFT_BRACKET", "RIGHT_BRAKCET", "SEMICOLON", "LEFT_R", 
			"RIGHT_R", "ASPAS", "DOUBLE_POINTS", "GET_ARRAY", "EQUALS", "WHERE", 
			"STAR", "NUMBER_INT", "NUMBER_FLOAT", "MATH_OPERATOR", "BOOLEAN_OPERATOR", 
			"CONDITIONAL_OPERATOR", "NOT_OPERATOR", "NEWLINE", "SPACE", "VARIABLE", 
			"ANYCHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 103482151936L) != 0)) {
				{
				setState(70);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(66);
					comment();
					}
					break;
				case 2:
					{
					setState(67);
					declaration();
					}
					break;
				case 3:
					{
					setState(68);
					definition();
					}
					break;
				case 4:
					{
					setState(69);
					statement();
					}
					break;
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommentContext extends ParserRuleContext {
		public Open_commentContext open_comment() {
			return getRuleContext(Open_commentContext.class,0);
		}
		public Close_commentContext close_comment() {
			return getRuleContext(Close_commentContext.class,0);
		}
		public List<TerminalNode> ANYCHAR() { return getTokens(GrammarParser.ANYCHAR); }
		public TerminalNode ANYCHAR(int i) {
			return getToken(GrammarParser.ANYCHAR, i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(GrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(GrammarParser.VARIABLE, i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public List<Reserved_wordsContext> reserved_words() {
			return getRuleContexts(Reserved_wordsContext.class);
		}
		public Reserved_wordsContext reserved_words(int i) {
			return getRuleContext(Reserved_wordsContext.class,i);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			open_comment();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 107307073024L) != 0)) {
				{
				setState(87);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(78);
					match(ANYCHAR);
					}
					break;
				case 2:
					{
					setState(79);
					match(VARIABLE);
					}
					break;
				case 3:
					{
					setState(80);
					number();
					}
					break;
				case 4:
					{
					setState(81);
					operator();
					}
					break;
				case 5:
					{
					setState(82);
					reserved_words();
					}
					break;
				case 6:
					{
					setState(83);
					declaration();
					}
					break;
				case 7:
					{
					setState(84);
					definition();
					}
					break;
				case 8:
					{
					setState(85);
					statement();
					}
					break;
				case 9:
					{
					setState(86);
					comment();
					}
					break;
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			close_comment();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Open_commentContext extends ParserRuleContext {
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode STAR() { return getToken(GrammarParser.STAR, 0); }
		public Open_commentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_open_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOpen_comment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOpen_comment(this);
		}
	}

	public final Open_commentContext open_comment() throws RecognitionException {
		Open_commentContext _localctx = new Open_commentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_open_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(LEFT_PAR);
			setState(95);
			match(STAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Close_commentContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(GrammarParser.STAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public Close_commentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_close_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterClose_comment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitClose_comment(this);
		}
	}

	public final Close_commentContext close_comment() throws RecognitionException {
		Close_commentContext _localctx = new Close_commentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_close_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(STAR);
			setState(98);
			match(RIGHT_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Reserved_wordsContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(GrammarParser.RETURN, 0); }
		public TerminalNode IF_COND() { return getToken(GrammarParser.IF_COND, 0); }
		public TerminalNode ELSE_COND() { return getToken(GrammarParser.ELSE_COND, 0); }
		public TerminalNode WHILE_COND() { return getToken(GrammarParser.WHILE_COND, 0); }
		public TerminalNode COMMA() { return getToken(GrammarParser.COMMA, 0); }
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(GrammarParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRAKCET() { return getToken(GrammarParser.RIGHT_BRAKCET, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public TerminalNode LEFT_R() { return getToken(GrammarParser.LEFT_R, 0); }
		public TerminalNode RIGHT_R() { return getToken(GrammarParser.RIGHT_R, 0); }
		public TerminalNode ASPAS() { return getToken(GrammarParser.ASPAS, 0); }
		public TerminalNode DOUBLE_POINTS() { return getToken(GrammarParser.DOUBLE_POINTS, 0); }
		public TerminalNode GET_ARRAY() { return getToken(GrammarParser.GET_ARRAY, 0); }
		public TerminalNode EQUALS() { return getToken(GrammarParser.EQUALS, 0); }
		public TerminalNode WHERE() { return getToken(GrammarParser.WHERE, 0); }
		public TerminalNode MATH_OPERATOR() { return getToken(GrammarParser.MATH_OPERATOR, 0); }
		public Reserved_wordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reserved_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterReserved_words(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitReserved_words(this);
		}
	}

	public final Reserved_wordsContext reserved_words() throws RecognitionException {
		Reserved_wordsContext _localctx = new Reserved_wordsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_reserved_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 603979264L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public Vname_typeContext vname_type() {
			return getRuleContext(Vname_typeContext.class,0);
		}
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public Args_defContext args_def() {
			return getRuleContext(Args_defContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			vname_type();
			setState(103);
			match(LEFT_PAR);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABLE) {
				{
				setState(104);
				args_def();
				}
			}

			setState(107);
			match(RIGHT_PAR);
			setState(108);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Vname_typeContext extends ParserRuleContext {
		public TerminalNode DOUBLE_POINTS() { return getToken(GrammarParser.DOUBLE_POINTS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public RefinementContext refinement() {
			return getRuleContext(RefinementContext.class,0);
		}
		public Vname_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vname_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterVname_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitVname_type(this);
		}
	}

	public final Vname_typeContext vname_type() throws RecognitionException {
		Vname_typeContext _localctx = new Vname_typeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_vname_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(110);
			match(VARIABLE);
			}
			setState(111);
			match(DOUBLE_POINTS);
			setState(112);
			type();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(113);
				refinement();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode DOUBLE() { return getToken(GrammarParser.DOUBLE, 0); }
		public TerminalNode INT() { return getToken(GrammarParser.INT, 0); }
		public TerminalNode BOOLEAN() { return getToken(GrammarParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(GrammarParser.STRING, 0); }
		public TerminalNode VOID() { return getToken(GrammarParser.VOID, 0); }
		public List<TerminalNode> LEFT_R() { return getTokens(GrammarParser.LEFT_R); }
		public TerminalNode LEFT_R(int i) {
			return getToken(GrammarParser.LEFT_R, i);
		}
		public List<TerminalNode> RIGHT_R() { return getTokens(GrammarParser.RIGHT_R); }
		public TerminalNode RIGHT_R(int i) {
			return getToken(GrammarParser.RIGHT_R, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(116);
				match(DOUBLE);
				}
				break;
			case 2:
				{
				setState(117);
				match(INT);
				}
				break;
			case 3:
				{
				setState(118);
				match(BOOLEAN);
				}
				break;
			case 4:
				{
				setState(119);
				match(STRING);
				}
				break;
			case 5:
				{
				setState(120);
				match(VOID);
				}
				break;
			case 6:
				{
				setState(121);
				match(LEFT_R);
				setState(122);
				match(INT);
				setState(123);
				match(RIGHT_R);
				}
				break;
			case 7:
				{
				setState(124);
				match(LEFT_R);
				setState(125);
				match(DOUBLE);
				setState(126);
				match(RIGHT_R);
				}
				break;
			case 8:
				{
				setState(127);
				match(LEFT_R);
				setState(128);
				match(STRING);
				setState(129);
				match(RIGHT_R);
				}
				break;
			case 9:
				{
				setState(130);
				match(LEFT_R);
				setState(131);
				match(LEFT_R);
				setState(132);
				match(INT);
				setState(133);
				match(RIGHT_R);
				setState(134);
				match(RIGHT_R);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RefinementContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(GrammarParser.WHERE, 0); }
		public List<TerminalNode> BOOLEAN_OPERATOR() { return getTokens(GrammarParser.BOOLEAN_OPERATOR); }
		public TerminalNode BOOLEAN_OPERATOR(int i) {
			return getToken(GrammarParser.BOOLEAN_OPERATOR, i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(GrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(GrammarParser.VARIABLE, i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<TerminalNode> TRUE() { return getTokens(GrammarParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(GrammarParser.TRUE, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(GrammarParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(GrammarParser.FALSE, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CONDITIONAL_OPERATOR() { return getTokens(GrammarParser.CONDITIONAL_OPERATOR); }
		public TerminalNode CONDITIONAL_OPERATOR(int i) {
			return getToken(GrammarParser.CONDITIONAL_OPERATOR, i);
		}
		public RefinementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refinement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRefinement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRefinement(this);
		}
	}

	public final RefinementContext refinement() throws RecognitionException {
		RefinementContext _localctx = new RefinementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_refinement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(WHERE);
			{
			setState(138);
			match(VARIABLE);
			}
			setState(139);
			match(BOOLEAN_OPERATOR);
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(140);
				number();
				}
				break;
			case 2:
				{
				setState(141);
				match(TRUE);
				}
				break;
			case 3:
				{
				setState(142);
				match(FALSE);
				}
				break;
			case 4:
				{
				setState(143);
				match(VARIABLE);
				}
				break;
			case 5:
				{
				setState(144);
				expr();
				}
				break;
			}
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CONDITIONAL_OPERATOR) {
				{
				{
				setState(147);
				match(CONDITIONAL_OPERATOR);
				{
				setState(148);
				match(VARIABLE);
				}
				setState(149);
				match(BOOLEAN_OPERATOR);
				setState(155);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(150);
					number();
					}
					break;
				case 2:
					{
					setState(151);
					match(TRUE);
					}
					break;
				case 3:
					{
					setState(152);
					match(FALSE);
					}
					break;
				case 4:
					{
					setState(153);
					match(VARIABLE);
					}
					break;
				case 5:
					{
					setState(154);
					expr();
					}
					break;
				}
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER_INT() { return getToken(GrammarParser.NUMBER_INT, 0); }
		public TerminalNode NUMBER_FLOAT() { return getToken(GrammarParser.NUMBER_FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !(_la==NUMBER_INT || _la==NUMBER_FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class String_litContext extends ParserRuleContext {
		public List<TerminalNode> ASPAS() { return getTokens(GrammarParser.ASPAS); }
		public TerminalNode ASPAS(int i) {
			return getToken(GrammarParser.ASPAS, i);
		}
		public List<TerminalNode> ANYCHAR() { return getTokens(GrammarParser.ANYCHAR); }
		public TerminalNode ANYCHAR(int i) {
			return getToken(GrammarParser.ANYCHAR, i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(GrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(GrammarParser.VARIABLE, i);
		}
		public List<TerminalNode> TRUE() { return getTokens(GrammarParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(GrammarParser.TRUE, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(GrammarParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(GrammarParser.FALSE, i);
		}
		public List<TerminalNode> MATH_OPERATOR() { return getTokens(GrammarParser.MATH_OPERATOR); }
		public TerminalNode MATH_OPERATOR(int i) {
			return getToken(GrammarParser.MATH_OPERATOR, i);
		}
		public String_litContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_lit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterString_lit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitString_lit(this);
		}
	}

	public final String_litContext string_lit() throws RecognitionException {
		String_litContext _localctx = new String_litContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_string_lit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(ASPAS);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 107239965056L) != 0)) {
				{
				setState(172);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANYCHAR:
					{
					setState(165);
					match(ANYCHAR);
					}
					break;
				case NUMBER_INT:
				case NUMBER_FLOAT:
					{
					setState(166);
					number();
					}
					break;
				case BOOLEAN_OPERATOR:
				case CONDITIONAL_OPERATOR:
					{
					setState(167);
					operator();
					}
					break;
				case VARIABLE:
					{
					setState(168);
					match(VARIABLE);
					}
					break;
				case TRUE:
					{
					setState(169);
					match(TRUE);
					}
					break;
				case FALSE:
					{
					setState(170);
					match(FALSE);
					}
					break;
				case MATH_OPERATOR:
					{
					setState(171);
					match(MATH_OPERATOR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
			match(ASPAS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public While_statementContext while_statement() {
			return getRuleContext(While_statementContext.class,0);
		}
		public ArraysContext arrays() {
			return getRuleContext(ArraysContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(179);
				expression();
				}
				break;
			case 2:
				{
				setState(180);
				value();
				}
				break;
			case 3:
				{
				setState(181);
				if_statement();
				}
				break;
			case 4:
				{
				setState(182);
				while_statement();
				}
				break;
			case 5:
				{
				setState(183);
				arrays();
				}
				break;
			case 6:
				{
				setState(184);
				declaration();
				}
				break;
			case 7:
				{
				setState(185);
				comment();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public Vname_type_optionalContext vname_type_optional() {
			return getRuleContext(Vname_type_optionalContext.class,0);
		}
		public TerminalNode EQUALS() { return getToken(GrammarParser.EQUALS, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public String_litContext string_lit() {
			return getRuleContext(String_litContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode TRUE() { return getToken(GrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GrammarParser.FALSE, 0); }
		public Array_defContext array_def() {
			return getRuleContext(Array_defContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			vname_type_optional();
			setState(189);
			match(EQUALS);
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(190);
				function_call();
				}
				break;
			case 2:
				{
				setState(191);
				number();
				}
				break;
			case 3:
				{
				setState(192);
				string_lit();
				}
				break;
			case 4:
				{
				setState(193);
				match(VARIABLE);
				}
				break;
			case 5:
				{
				setState(194);
				expr();
				}
				break;
			case 6:
				{
				setState(195);
				match(TRUE);
				}
				break;
			case 7:
				{
				setState(196);
				match(FALSE);
				}
				break;
			case 8:
				{
				setState(197);
				array_def();
				}
				break;
			}
			setState(200);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Boolean_expressionContext extends ParserRuleContext {
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public Conditions_valuesContext conditions_values() {
			return getRuleContext(Conditions_valuesContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public Boolean_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBoolean_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBoolean_expression(this);
		}
	}

	public final Boolean_expressionContext boolean_expression() throws RecognitionException {
		Boolean_expressionContext _localctx = new Boolean_expressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_boolean_expression);
		try {
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				match(LEFT_PAR);
				setState(203);
				boolean_expression();
				setState(204);
				match(RIGHT_PAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				conditions_values();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(207);
				conditions_values();
				setState(208);
				operator();
				setState(209);
				boolean_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Conditions_valuesContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public TerminalNode TRUE() { return getToken(GrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GrammarParser.FALSE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Conditions_valuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterConditions_values(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitConditions_values(this);
		}
	}

	public final Conditions_valuesContext conditions_values() throws RecognitionException {
		Conditions_valuesContext _localctx = new Conditions_valuesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_conditions_values);
		try {
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				match(VARIABLE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				match(TRUE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(215);
				match(FALSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
				number();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(217);
				function_call();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(218);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public Expr_valueContext expr_value() {
			return getRuleContext(Expr_valueContext.class,0);
		}
		public TerminalNode MATH_OPERATOR() { return getToken(GrammarParser.MATH_OPERATOR, 0); }
		public TerminalNode STAR() { return getToken(GrammarParser.STAR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr);
		int _la;
		try {
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				match(LEFT_PAR);
				setState(222);
				expr();
				setState(223);
				match(RIGHT_PAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				expr_value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				expr_value();
				setState(227);
				_la = _input.LA(1);
				if ( !(_la==STAR || _la==MATH_OPERATOR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(228);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_valueContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Expr_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterExpr_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitExpr_value(this);
		}
	}

	public final Expr_valueContext expr_value() throws RecognitionException {
		Expr_valueContext _localctx = new Expr_valueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expr_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(232);
				number();
				}
				break;
			case 2:
				{
				setState(233);
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(234);
				function_call();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Vname_type_optionalContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public TerminalNode LEFT_R() { return getToken(GrammarParser.LEFT_R, 0); }
		public PositionContext position() {
			return getRuleContext(PositionContext.class,0);
		}
		public TerminalNode RIGHT_R() { return getToken(GrammarParser.RIGHT_R, 0); }
		public TerminalNode DOUBLE_POINTS() { return getToken(GrammarParser.DOUBLE_POINTS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Vname_type_optionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vname_type_optional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterVname_type_optional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitVname_type_optional(this);
		}
	}

	public final Vname_type_optionalContext vname_type_optional() throws RecognitionException {
		Vname_type_optionalContext _localctx = new Vname_type_optionalContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_vname_type_optional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(237);
			match(VARIABLE);
			}
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFT_R:
				{
				setState(238);
				match(LEFT_R);
				setState(239);
				position();
				setState(240);
				match(RIGHT_R);
				}
				break;
			case DOUBLE_POINTS:
				{
				setState(242);
				match(DOUBLE_POINTS);
				setState(243);
				type();
				}
				break;
			case EQUALS:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArraysContext extends ParserRuleContext {
		public Array_defContext array_def() {
			return getRuleContext(Array_defContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public ArraysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrays; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArrays(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArrays(this);
		}
	}

	public final ArraysContext arrays() throws RecognitionException {
		ArraysContext _localctx = new ArraysContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arrays);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			array_def();
			setState(247);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Array_defContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public TerminalNode LEFT_R() { return getToken(GrammarParser.LEFT_R, 0); }
		public PositionContext position() {
			return getRuleContext(PositionContext.class,0);
		}
		public TerminalNode RIGHT_R() { return getToken(GrammarParser.RIGHT_R, 0); }
		public TerminalNode GET_ARRAY() { return getToken(GrammarParser.GET_ARRAY, 0); }
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public Array_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArray_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArray_def(this);
		}
	}

	public final Array_defContext array_def() throws RecognitionException {
		Array_defContext _localctx = new Array_defContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_array_def);
		try {
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(VARIABLE);
				setState(250);
				match(LEFT_R);
				setState(251);
				position();
				setState(252);
				match(RIGHT_R);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(VARIABLE);
				setState(255);
				match(T__0);
				setState(256);
				match(GET_ARRAY);
				setState(257);
				match(LEFT_PAR);
				setState(258);
				match(RIGHT_PAR);
				setState(259);
				match(LEFT_R);
				setState(260);
				position();
				setState(261);
				match(RIGHT_R);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PositionContext extends ParserRuleContext {
		public PosContext pos() {
			return getRuleContext(PosContext.class,0);
		}
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public PositionContext position() {
			return getRuleContext(PositionContext.class,0);
		}
		public TerminalNode MATH_OPERATOR() { return getToken(GrammarParser.MATH_OPERATOR, 0); }
		public TerminalNode STAR() { return getToken(GrammarParser.STAR, 0); }
		public PositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_position; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPosition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPosition(this);
		}
	}

	public final PositionContext position() throws RecognitionException {
		PositionContext _localctx = new PositionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_position);
		int _la;
		try {
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				pos();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(LEFT_PAR);
				setState(267);
				pos();
				setState(268);
				match(RIGHT_PAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				pos();
				setState(271);
				_la = _input.LA(1);
				if ( !(_la==STAR || _la==MATH_OPERATOR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(272);
				position();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PosContext extends ParserRuleContext {
		public TerminalNode NUMBER_INT() { return getToken(GrammarParser.NUMBER_INT, 0); }
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public PosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPos(this);
		}
	}

	public final PosContext pos() throws RecognitionException {
		PosContext _localctx = new PosContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_pos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(276);
				match(NUMBER_INT);
				}
				break;
			case 2:
				{
				setState(277);
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(278);
				function_call();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(GrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(GrammarParser.VARIABLE, i);
		}
		public List<TerminalNode> ANYCHAR() { return getTokens(GrammarParser.ANYCHAR); }
		public TerminalNode ANYCHAR(int i) {
			return getToken(GrammarParser.ANYCHAR, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(281);
				function_call();
				setState(282);
				match(SEMICOLON);
				}
				break;
			case 2:
				{
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 103481868288L) != 0)) {
					{
					setState(287);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case NUMBER_INT:
					case NUMBER_FLOAT:
						{
						setState(284);
						number();
						}
						break;
					case VARIABLE:
						{
						setState(285);
						match(VARIABLE);
						}
						break;
					case ANYCHAR:
						{
						setState(286);
						match(ANYCHAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(291);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(292);
				match(SEMICOLON);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public Args_valueContext args_value() {
			return getRuleContext(Args_valueContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFunction_call(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(VARIABLE);
			setState(296);
			match(LEFT_PAR);
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34764505472L) != 0)) {
				{
				setState(297);
				args_value();
				}
			}

			setState(300);
			match(RIGHT_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_statementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(GrammarParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public String_litContext string_lit() {
			return getRuleContext(String_litContext.class,0);
		}
		public TerminalNode TRUE() { return getToken(GrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GrammarParser.FALSE, 0); }
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitReturn_statement(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_return_statement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(RETURN);
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34764505472L) != 0)) {
				{
				setState(310);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(303);
					match(VARIABLE);
					}
					break;
				case 2:
					{
					setState(304);
					number();
					}
					break;
				case 3:
					{
					setState(305);
					string_lit();
					}
					break;
				case 4:
					{
					setState(306);
					match(TRUE);
					}
					break;
				case 5:
					{
					setState(307);
					match(FALSE);
					}
					break;
				case 6:
					{
					setState(308);
					function_call();
					}
					break;
				case 7:
					{
					setState(309);
					expr();
					}
					break;
				}
				}
			}

			setState(314);
			match(SEMICOLON);
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(315);
					comment();
					}
					} 
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinitionContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDefinition(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(321);
			function();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public Vname_typeContext vname_type() {
			return getRuleContext(Vname_typeContext.class,0);
		}
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(GrammarParser.LEFT_BRACKET, 0); }
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public TerminalNode RIGHT_BRAKCET() { return getToken(GrammarParser.RIGHT_BRAKCET, 0); }
		public Args_defContext args_def() {
			return getRuleContext(Args_defContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			vname_type();
			setState(324);
			match(LEFT_PAR);
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABLE) {
				{
				setState(325);
				args_def();
				}
			}

			setState(328);
			match(RIGHT_PAR);
			setState(329);
			match(LEFT_BRACKET);
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 103482151936L) != 0)) {
				{
				setState(333);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(330);
					statement();
					}
					break;
				case 2:
					{
					setState(331);
					definition();
					}
					break;
				case 3:
					{
					setState(332);
					comment();
					}
					break;
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(338);
			return_statement();
			setState(339);
			match(RIGHT_BRAKCET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF_COND() { return getToken(GrammarParser.IF_COND, 0); }
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
		public TerminalNode LEFT_BRACKET() { return getToken(GrammarParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRAKCET() { return getToken(GrammarParser.RIGHT_BRAKCET, 0); }
		public TerminalNode NOT_OPERATOR() { return getToken(GrammarParser.NOT_OPERATOR, 0); }
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public Else_statementContext else_statement() {
			return getRuleContext(Else_statementContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitIf_statement(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(IF_COND);
			setState(343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT_OPERATOR) {
				{
				setState(342);
				match(NOT_OPERATOR);
				}
			}

			setState(345);
			boolean_expression();
			setState(346);
			match(LEFT_BRACKET);
			{
			setState(350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 103482151936L) != 0)) {
				{
				{
				setState(347);
				statement();
				}
				}
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(353);
				return_statement();
				}
			}

			setState(356);
			match(RIGHT_BRAKCET);
			setState(358);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(357);
				else_statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Else_statementContext extends ParserRuleContext {
		public TerminalNode ELSE_COND() { return getToken(GrammarParser.ELSE_COND, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(GrammarParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRAKCET() { return getToken(GrammarParser.RIGHT_BRAKCET, 0); }
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Else_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterElse_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitElse_statement(this);
		}
	}

	public final Else_statementContext else_statement() throws RecognitionException {
		Else_statementContext _localctx = new Else_statementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_else_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(ELSE_COND);
			setState(361);
			match(LEFT_BRACKET);
			{
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 103482151936L) != 0)) {
				{
				{
				setState(362);
				statement();
				}
				}
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(368);
				return_statement();
				}
			}

			setState(371);
			match(RIGHT_BRAKCET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class While_statementContext extends ParserRuleContext {
		public TerminalNode WHILE_COND() { return getToken(GrammarParser.WHILE_COND, 0); }
		public Boolean_expressionContext boolean_expression() {
			return getRuleContext(Boolean_expressionContext.class,0);
		}
		public TerminalNode LEFT_BRACKET() { return getToken(GrammarParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRAKCET() { return getToken(GrammarParser.RIGHT_BRAKCET, 0); }
		public TerminalNode NOT_OPERATOR() { return getToken(GrammarParser.NOT_OPERATOR, 0); }
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public While_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterWhile_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitWhile_statement(this);
		}
	}

	public final While_statementContext while_statement() throws RecognitionException {
		While_statementContext _localctx = new While_statementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_while_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(WHILE_COND);
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT_OPERATOR) {
				{
				setState(374);
				match(NOT_OPERATOR);
				}
			}

			setState(377);
			boolean_expression();
			setState(378);
			match(LEFT_BRACKET);
			{
			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 103482151936L) != 0)) {
				{
				{
				setState(379);
				statement();
				}
				}
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(385);
				return_statement();
				}
			}

			setState(388);
			match(RIGHT_BRAKCET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Args_defContext extends ParserRuleContext {
		public List<Vname_typeContext> vname_type() {
			return getRuleContexts(Vname_typeContext.class);
		}
		public Vname_typeContext vname_type(int i) {
			return getRuleContext(Vname_typeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrammarParser.COMMA, i);
		}
		public Args_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArgs_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArgs_def(this);
		}
	}

	public final Args_defContext args_def() throws RecognitionException {
		Args_defContext _localctx = new Args_defContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_args_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			vname_type();
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(391);
				match(COMMA);
				setState(392);
				vname_type();
				}
				}
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Args_valueContext extends ParserRuleContext {
		public List<Array_defContext> array_def() {
			return getRuleContexts(Array_defContext.class);
		}
		public Array_defContext array_def(int i) {
			return getRuleContext(Array_defContext.class,i);
		}
		public List<Function_callContext> function_call() {
			return getRuleContexts(Function_callContext.class);
		}
		public Function_callContext function_call(int i) {
			return getRuleContext(Function_callContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<String_litContext> string_lit() {
			return getRuleContexts(String_litContext.class);
		}
		public String_litContext string_lit(int i) {
			return getRuleContext(String_litContext.class,i);
		}
		public List<TerminalNode> TRUE() { return getTokens(GrammarParser.TRUE); }
		public TerminalNode TRUE(int i) {
			return getToken(GrammarParser.TRUE, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(GrammarParser.FALSE); }
		public TerminalNode FALSE(int i) {
			return getToken(GrammarParser.FALSE, i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(GrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(GrammarParser.VARIABLE, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrammarParser.COMMA, i);
		}
		public Args_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArgs_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArgs_value(this);
		}
	}

	public final Args_valueContext args_value() throws RecognitionException {
		Args_valueContext _localctx = new Args_valueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_args_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(398);
				array_def();
				}
				break;
			case 2:
				{
				setState(399);
				function_call();
				}
				break;
			case 3:
				{
				setState(400);
				number();
				}
				break;
			case 4:
				{
				setState(401);
				string_lit();
				}
				break;
			case 5:
				{
				setState(402);
				match(TRUE);
				}
				break;
			case 6:
				{
				setState(403);
				match(FALSE);
				}
				break;
			case 7:
				{
				setState(404);
				match(VARIABLE);
				}
				break;
			case 8:
				{
				setState(405);
				expr();
				}
				break;
			}
			setState(421);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(408);
				match(COMMA);
				setState(417);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(409);
					array_def();
					}
					break;
				case 2:
					{
					setState(410);
					function_call();
					}
					break;
				case 3:
					{
					setState(411);
					number();
					}
					break;
				case 4:
					{
					setState(412);
					string_lit();
					}
					break;
				case 5:
					{
					setState(413);
					match(TRUE);
					}
					break;
				case 6:
					{
					setState(414);
					match(FALSE);
					}
					break;
				case 7:
					{
					setState(415);
					match(VARIABLE);
					}
					break;
				case 8:
					{
					setState(416);
					expr();
					}
					break;
				}
				}
				}
				setState(423);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_OPERATOR() { return getToken(GrammarParser.BOOLEAN_OPERATOR, 0); }
		public TerminalNode CONDITIONAL_OPERATOR() { return getToken(GrammarParser.CONDITIONAL_OPERATOR, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			_la = _input.LA(1);
			if ( !(_la==BOOLEAN_OPERATOR || _la==CONDITIONAL_OPERATOR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u01ab\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000G\b\u0000\n\u0000\f\u0000J\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"X\b\u0001\n\u0001\f\u0001[\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005j\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006s\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0088\b\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0092\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u009c\b\b\u0005\b\u009e\b\b\n\b\f\b\u00a1\t\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u00ad\b\n\n\n\f\n\u00b0\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00bb\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u00c7\b\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d4"+
		"\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00dc\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00e7\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00ec"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u00f5\b\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0108\b\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u0113\b\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u0118\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u0120\b\u0016\n\u0016\f\u0016"+
		"\u0123\t\u0016\u0001\u0016\u0003\u0016\u0126\b\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u012b\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u0137\b\u0018\u0003\u0018\u0139\b\u0018"+
		"\u0001\u0018\u0001\u0018\u0005\u0018\u013d\b\u0018\n\u0018\f\u0018\u0140"+
		"\t\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u0147\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0005\u001a\u014e\b\u001a\n\u001a\f\u001a\u0151\t\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0003\u001b\u0158\b\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u015d\b\u001b\n\u001b"+
		"\f\u001b\u0160\t\u001b\u0001\u001b\u0003\u001b\u0163\b\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u0167\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0005\u001c\u016c\b\u001c\n\u001c\f\u001c\u016f\t\u001c\u0001\u001c\u0003"+
		"\u001c\u0172\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u0178\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u017d"+
		"\b\u001d\n\u001d\f\u001d\u0180\t\u001d\u0001\u001d\u0003\u001d\u0183\b"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0005"+
		"\u001e\u018a\b\u001e\n\u001e\f\u001e\u018d\t\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0003\u001f\u0197\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f"+
		"\u01a2\b\u001f\u0005\u001f\u01a4\b\u001f\n\u001f\f\u001f\u01a7\t\u001f"+
		"\u0001 \u0001 \u0001 \u0001\u013e\u0000!\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:"+
		"<>@\u0000\u0004\u0002\u0000\t\u0019\u001d\u001d\u0001\u0000\u001b\u001c"+
		"\u0002\u0000\u001a\u001a\u001d\u001d\u0001\u0000\u001e\u001f\u01fa\u0000"+
		"H\u0001\u0000\u0000\u0000\u0002M\u0001\u0000\u0000\u0000\u0004^\u0001"+
		"\u0000\u0000\u0000\u0006a\u0001\u0000\u0000\u0000\bd\u0001\u0000\u0000"+
		"\u0000\nf\u0001\u0000\u0000\u0000\fn\u0001\u0000\u0000\u0000\u000e\u0087"+
		"\u0001\u0000\u0000\u0000\u0010\u0089\u0001\u0000\u0000\u0000\u0012\u00a2"+
		"\u0001\u0000\u0000\u0000\u0014\u00a4\u0001\u0000\u0000\u0000\u0016\u00ba"+
		"\u0001\u0000\u0000\u0000\u0018\u00bc\u0001\u0000\u0000\u0000\u001a\u00d3"+
		"\u0001\u0000\u0000\u0000\u001c\u00db\u0001\u0000\u0000\u0000\u001e\u00e6"+
		"\u0001\u0000\u0000\u0000 \u00eb\u0001\u0000\u0000\u0000\"\u00ed\u0001"+
		"\u0000\u0000\u0000$\u00f6\u0001\u0000\u0000\u0000&\u0107\u0001\u0000\u0000"+
		"\u0000(\u0112\u0001\u0000\u0000\u0000*\u0117\u0001\u0000\u0000\u0000,"+
		"\u0125\u0001\u0000\u0000\u0000.\u0127\u0001\u0000\u0000\u00000\u012e\u0001"+
		"\u0000\u0000\u00002\u0141\u0001\u0000\u0000\u00004\u0143\u0001\u0000\u0000"+
		"\u00006\u0155\u0001\u0000\u0000\u00008\u0168\u0001\u0000\u0000\u0000:"+
		"\u0175\u0001\u0000\u0000\u0000<\u0186\u0001\u0000\u0000\u0000>\u0196\u0001"+
		"\u0000\u0000\u0000@\u01a8\u0001\u0000\u0000\u0000BG\u0003\u0002\u0001"+
		"\u0000CG\u0003\n\u0005\u0000DG\u00032\u0019\u0000EG\u0003\u0016\u000b"+
		"\u0000FB\u0001\u0000\u0000\u0000FC\u0001\u0000\u0000\u0000FD\u0001\u0000"+
		"\u0000\u0000FE\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001"+
		"\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IK\u0001\u0000\u0000\u0000"+
		"JH\u0001\u0000\u0000\u0000KL\u0005\u0000\u0000\u0001L\u0001\u0001\u0000"+
		"\u0000\u0000MY\u0003\u0004\u0002\u0000NX\u0005$\u0000\u0000OX\u0005#\u0000"+
		"\u0000PX\u0003\u0012\t\u0000QX\u0003@ \u0000RX\u0003\b\u0004\u0000SX\u0003"+
		"\n\u0005\u0000TX\u00032\u0019\u0000UX\u0003\u0016\u000b\u0000VX\u0003"+
		"\u0002\u0001\u0000WN\u0001\u0000\u0000\u0000WO\u0001\u0000\u0000\u0000"+
		"WP\u0001\u0000\u0000\u0000WQ\u0001\u0000\u0000\u0000WR\u0001\u0000\u0000"+
		"\u0000WS\u0001\u0000\u0000\u0000WT\u0001\u0000\u0000\u0000WU\u0001\u0000"+
		"\u0000\u0000WV\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001"+
		"\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000\u0000"+
		"[Y\u0001\u0000\u0000\u0000\\]\u0003\u0006\u0003\u0000]\u0003\u0001\u0000"+
		"\u0000\u0000^_\u0005\u000e\u0000\u0000_`\u0005\u001a\u0000\u0000`\u0005"+
		"\u0001\u0000\u0000\u0000ab\u0005\u001a\u0000\u0000bc\u0005\u000f\u0000"+
		"\u0000c\u0007\u0001\u0000\u0000\u0000de\u0007\u0000\u0000\u0000e\t\u0001"+
		"\u0000\u0000\u0000fg\u0003\f\u0006\u0000gi\u0005\u000e\u0000\u0000hj\u0003"+
		"<\u001e\u0000ih\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0001"+
		"\u0000\u0000\u0000kl\u0005\u000f\u0000\u0000lm\u0005\u0012\u0000\u0000"+
		"m\u000b\u0001\u0000\u0000\u0000no\u0005#\u0000\u0000op\u0005\u0016\u0000"+
		"\u0000pr\u0003\u000e\u0007\u0000qs\u0003\u0010\b\u0000rq\u0001\u0000\u0000"+
		"\u0000rs\u0001\u0000\u0000\u0000s\r\u0001\u0000\u0000\u0000t\u0088\u0005"+
		"\u0003\u0000\u0000u\u0088\u0005\u0004\u0000\u0000v\u0088\u0005\u0005\u0000"+
		"\u0000w\u0088\u0005\u0006\u0000\u0000x\u0088\u0005\u0002\u0000\u0000y"+
		"z\u0005\u0013\u0000\u0000z{\u0005\u0004\u0000\u0000{\u0088\u0005\u0014"+
		"\u0000\u0000|}\u0005\u0013\u0000\u0000}~\u0005\u0003\u0000\u0000~\u0088"+
		"\u0005\u0014\u0000\u0000\u007f\u0080\u0005\u0013\u0000\u0000\u0080\u0081"+
		"\u0005\u0006\u0000\u0000\u0081\u0088\u0005\u0014\u0000\u0000\u0082\u0083"+
		"\u0005\u0013\u0000\u0000\u0083\u0084\u0005\u0013\u0000\u0000\u0084\u0085"+
		"\u0005\u0004\u0000\u0000\u0085\u0086\u0005\u0014\u0000\u0000\u0086\u0088"+
		"\u0005\u0014\u0000\u0000\u0087t\u0001\u0000\u0000\u0000\u0087u\u0001\u0000"+
		"\u0000\u0000\u0087v\u0001\u0000\u0000\u0000\u0087w\u0001\u0000\u0000\u0000"+
		"\u0087x\u0001\u0000\u0000\u0000\u0087y\u0001\u0000\u0000\u0000\u0087|"+
		"\u0001\u0000\u0000\u0000\u0087\u007f\u0001\u0000\u0000\u0000\u0087\u0082"+
		"\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u000f"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0019\u0000\u0000\u008a\u008b"+
		"\u0005#\u0000\u0000\u008b\u0091\u0005\u001e\u0000\u0000\u008c\u0092\u0003"+
		"\u0012\t\u0000\u008d\u0092\u0005\u0007\u0000\u0000\u008e\u0092\u0005\b"+
		"\u0000\u0000\u008f\u0092\u0005#\u0000\u0000\u0090\u0092\u0003\u001e\u000f"+
		"\u0000\u0091\u008c\u0001\u0000\u0000\u0000\u0091\u008d\u0001\u0000\u0000"+
		"\u0000\u0091\u008e\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u009f\u0001\u0000\u0000"+
		"\u0000\u0093\u0094\u0005\u001f\u0000\u0000\u0094\u0095\u0005#\u0000\u0000"+
		"\u0095\u009b\u0005\u001e\u0000\u0000\u0096\u009c\u0003\u0012\t\u0000\u0097"+
		"\u009c\u0005\u0007\u0000\u0000\u0098\u009c\u0005\b\u0000\u0000\u0099\u009c"+
		"\u0005#\u0000\u0000\u009a\u009c\u0003\u001e\u000f\u0000\u009b\u0096\u0001"+
		"\u0000\u0000\u0000\u009b\u0097\u0001\u0000\u0000\u0000\u009b\u0098\u0001"+
		"\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009b\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u0093\u0001"+
		"\u0000\u0000\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u0011\u0001"+
		"\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0007"+
		"\u0001\u0000\u0000\u00a3\u0013\u0001\u0000\u0000\u0000\u00a4\u00ae\u0005"+
		"\u0015\u0000\u0000\u00a5\u00ad\u0005$\u0000\u0000\u00a6\u00ad\u0003\u0012"+
		"\t\u0000\u00a7\u00ad\u0003@ \u0000\u00a8\u00ad\u0005#\u0000\u0000\u00a9"+
		"\u00ad\u0005\u0007\u0000\u0000\u00aa\u00ad\u0005\b\u0000\u0000\u00ab\u00ad"+
		"\u0005\u001d\u0000\u0000\u00ac\u00a5\u0001\u0000\u0000\u0000\u00ac\u00a6"+
		"\u0001\u0000\u0000\u0000\u00ac\u00a7\u0001\u0000\u0000\u0000\u00ac\u00a8"+
		"\u0001\u0000\u0000\u0000\u00ac\u00a9\u0001\u0000\u0000\u0000\u00ac\u00aa"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad\u00b0"+
		"\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u00b1\u0001\u0000\u0000\u0000\u00b0\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\u0015\u0000\u0000\u00b2\u0015"+
		"\u0001\u0000\u0000\u0000\u00b3\u00bb\u0003,\u0016\u0000\u00b4\u00bb\u0003"+
		"\u0018\f\u0000\u00b5\u00bb\u00036\u001b\u0000\u00b6\u00bb\u0003:\u001d"+
		"\u0000\u00b7\u00bb\u0003$\u0012\u0000\u00b8\u00bb\u0003\n\u0005\u0000"+
		"\u00b9\u00bb\u0003\u0002\u0001\u0000\u00ba\u00b3\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b4\u0001\u0000\u0000\u0000\u00ba\u00b5\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b6\u0001\u0000\u0000\u0000\u00ba\u00b7\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bb\u0017\u0001\u0000\u0000\u0000\u00bc\u00bd\u0003\"\u0011\u0000\u00bd"+
		"\u00c6\u0005\u0018\u0000\u0000\u00be\u00c7\u0003.\u0017\u0000\u00bf\u00c7"+
		"\u0003\u0012\t\u0000\u00c0\u00c7\u0003\u0014\n\u0000\u00c1\u00c7\u0005"+
		"#\u0000\u0000\u00c2\u00c7\u0003\u001e\u000f\u0000\u00c3\u00c7\u0005\u0007"+
		"\u0000\u0000\u00c4\u00c7\u0005\b\u0000\u0000\u00c5\u00c7\u0003&\u0013"+
		"\u0000\u00c6\u00be\u0001\u0000\u0000\u0000\u00c6\u00bf\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c0\u0001\u0000\u0000\u0000\u00c6\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c2\u0001\u0000\u0000\u0000\u00c6\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005\u0012\u0000"+
		"\u0000\u00c9\u0019\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005\u000e\u0000"+
		"\u0000\u00cb\u00cc\u0003\u001a\r\u0000\u00cc\u00cd\u0005\u000f\u0000\u0000"+
		"\u00cd\u00d4\u0001\u0000\u0000\u0000\u00ce\u00d4\u0003\u001c\u000e\u0000"+
		"\u00cf\u00d0\u0003\u001c\u000e\u0000\u00d0\u00d1\u0003@ \u0000\u00d1\u00d2"+
		"\u0003\u001a\r\u0000\u00d2\u00d4\u0001\u0000\u0000\u0000\u00d3\u00ca\u0001"+
		"\u0000\u0000\u0000\u00d3\u00ce\u0001\u0000\u0000\u0000\u00d3\u00cf\u0001"+
		"\u0000\u0000\u0000\u00d4\u001b\u0001\u0000\u0000\u0000\u00d5\u00dc\u0005"+
		"#\u0000\u0000\u00d6\u00dc\u0005\u0007\u0000\u0000\u00d7\u00dc\u0005\b"+
		"\u0000\u0000\u00d8\u00dc\u0003\u0012\t\u0000\u00d9\u00dc\u0003.\u0017"+
		"\u0000\u00da\u00dc\u0003\u001e\u000f\u0000\u00db\u00d5\u0001\u0000\u0000"+
		"\u0000\u00db\u00d6\u0001\u0000\u0000\u0000\u00db\u00d7\u0001\u0000\u0000"+
		"\u0000\u00db\u00d8\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000"+
		"\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00dc\u001d\u0001\u0000\u0000"+
		"\u0000\u00dd\u00de\u0005\u000e\u0000\u0000\u00de\u00df\u0003\u001e\u000f"+
		"\u0000\u00df\u00e0\u0005\u000f\u0000\u0000\u00e0\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e7\u0003 \u0010\u0000\u00e2\u00e3\u0003 \u0010\u0000\u00e3"+
		"\u00e4\u0007\u0002\u0000\u0000\u00e4\u00e5\u0003\u001e\u000f\u0000\u00e5"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e6\u00dd\u0001\u0000\u0000\u0000\u00e6"+
		"\u00e1\u0001\u0000\u0000\u0000\u00e6\u00e2\u0001\u0000\u0000\u0000\u00e7"+
		"\u001f\u0001\u0000\u0000\u0000\u00e8\u00ec\u0003\u0012\t\u0000\u00e9\u00ec"+
		"\u0005#\u0000\u0000\u00ea\u00ec\u0003.\u0017\u0000\u00eb\u00e8\u0001\u0000"+
		"\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ea\u0001\u0000"+
		"\u0000\u0000\u00ec!\u0001\u0000\u0000\u0000\u00ed\u00f4\u0005#\u0000\u0000"+
		"\u00ee\u00ef\u0005\u0013\u0000\u0000\u00ef\u00f0\u0003(\u0014\u0000\u00f0"+
		"\u00f1\u0005\u0014\u0000\u0000\u00f1\u00f5\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f3\u0005\u0016\u0000\u0000\u00f3\u00f5\u0003\u000e\u0007\u0000\u00f4"+
		"\u00ee\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f5\u0001\u0000\u0000\u0000\u00f5#\u0001\u0000\u0000\u0000\u00f6\u00f7"+
		"\u0003&\u0013\u0000\u00f7\u00f8\u0005\u0012\u0000\u0000\u00f8%\u0001\u0000"+
		"\u0000\u0000\u00f9\u00fa\u0005#\u0000\u0000\u00fa\u00fb\u0005\u0013\u0000"+
		"\u0000\u00fb\u00fc\u0003(\u0014\u0000\u00fc\u00fd\u0005\u0014\u0000\u0000"+
		"\u00fd\u0108\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005#\u0000\u0000\u00ff"+
		"\u0100\u0005\u0001\u0000\u0000\u0100\u0101\u0005\u0017\u0000\u0000\u0101"+
		"\u0102\u0005\u000e\u0000\u0000\u0102\u0103\u0005\u000f\u0000\u0000\u0103"+
		"\u0104\u0005\u0013\u0000\u0000\u0104\u0105\u0003(\u0014\u0000\u0105\u0106"+
		"\u0005\u0014\u0000\u0000\u0106\u0108\u0001\u0000\u0000\u0000\u0107\u00f9"+
		"\u0001\u0000\u0000\u0000\u0107\u00fe\u0001\u0000\u0000\u0000\u0108\'\u0001"+
		"\u0000\u0000\u0000\u0109\u0113\u0003*\u0015\u0000\u010a\u010b\u0005\u000e"+
		"\u0000\u0000\u010b\u010c\u0003*\u0015\u0000\u010c\u010d\u0005\u000f\u0000"+
		"\u0000\u010d\u0113\u0001\u0000\u0000\u0000\u010e\u010f\u0003*\u0015\u0000"+
		"\u010f\u0110\u0007\u0002\u0000\u0000\u0110\u0111\u0003(\u0014\u0000\u0111"+
		"\u0113\u0001\u0000\u0000\u0000\u0112\u0109\u0001\u0000\u0000\u0000\u0112"+
		"\u010a\u0001\u0000\u0000\u0000\u0112\u010e\u0001\u0000\u0000\u0000\u0113"+
		")\u0001\u0000\u0000\u0000\u0114\u0118\u0005\u001b\u0000\u0000\u0115\u0118"+
		"\u0005#\u0000\u0000\u0116\u0118\u0003.\u0017\u0000\u0117\u0114\u0001\u0000"+
		"\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117\u0116\u0001\u0000"+
		"\u0000\u0000\u0118+\u0001\u0000\u0000\u0000\u0119\u011a\u0003.\u0017\u0000"+
		"\u011a\u011b\u0005\u0012\u0000\u0000\u011b\u0126\u0001\u0000\u0000\u0000"+
		"\u011c\u0120\u0003\u0012\t\u0000\u011d\u0120\u0005#\u0000\u0000\u011e"+
		"\u0120\u0005$\u0000\u0000\u011f\u011c\u0001\u0000\u0000\u0000\u011f\u011d"+
		"\u0001\u0000\u0000\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u0120\u0123"+
		"\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0121\u0122"+
		"\u0001\u0000\u0000\u0000\u0122\u0124\u0001\u0000\u0000\u0000\u0123\u0121"+
		"\u0001\u0000\u0000\u0000\u0124\u0126\u0005\u0012\u0000\u0000\u0125\u0119"+
		"\u0001\u0000\u0000\u0000\u0125\u0121\u0001\u0000\u0000\u0000\u0126-\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0005#\u0000\u0000\u0128\u012a\u0005\u000e"+
		"\u0000\u0000\u0129\u012b\u0003>\u001f\u0000\u012a\u0129\u0001\u0000\u0000"+
		"\u0000\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000"+
		"\u0000\u012c\u012d\u0005\u000f\u0000\u0000\u012d/\u0001\u0000\u0000\u0000"+
		"\u012e\u0138\u0005\t\u0000\u0000\u012f\u0137\u0005#\u0000\u0000\u0130"+
		"\u0137\u0003\u0012\t\u0000\u0131\u0137\u0003\u0014\n\u0000\u0132\u0137"+
		"\u0005\u0007\u0000\u0000\u0133\u0137\u0005\b\u0000\u0000\u0134\u0137\u0003"+
		".\u0017\u0000\u0135\u0137\u0003\u001e\u000f\u0000\u0136\u012f\u0001\u0000"+
		"\u0000\u0000\u0136\u0130\u0001\u0000\u0000\u0000\u0136\u0131\u0001\u0000"+
		"\u0000\u0000\u0136\u0132\u0001\u0000\u0000\u0000\u0136\u0133\u0001\u0000"+
		"\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0136\u0135\u0001\u0000"+
		"\u0000\u0000\u0137\u0139\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000"+
		"\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000"+
		"\u0000\u0000\u013a\u013e\u0005\u0012\u0000\u0000\u013b\u013d\u0003\u0002"+
		"\u0001\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013d\u0140\u0001\u0000"+
		"\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000"+
		"\u0000\u0000\u013f1\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000"+
		"\u0000\u0141\u0142\u00034\u001a\u0000\u01423\u0001\u0000\u0000\u0000\u0143"+
		"\u0144\u0003\f\u0006\u0000\u0144\u0146\u0005\u000e\u0000\u0000\u0145\u0147"+
		"\u0003<\u001e\u0000\u0146\u0145\u0001\u0000\u0000\u0000\u0146\u0147\u0001"+
		"\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000\u0000\u0148\u0149\u0005"+
		"\u000f\u0000\u0000\u0149\u014f\u0005\u0010\u0000\u0000\u014a\u014e\u0003"+
		"\u0016\u000b\u0000\u014b\u014e\u00032\u0019\u0000\u014c\u014e\u0003\u0002"+
		"\u0001\u0000\u014d\u014a\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000"+
		"\u0000\u0000\u014d\u014c\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000"+
		"\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000"+
		"\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000"+
		"\u0000\u0000\u0152\u0153\u00030\u0018\u0000\u0153\u0154\u0005\u0011\u0000"+
		"\u0000\u01545\u0001\u0000\u0000\u0000\u0155\u0157\u0005\n\u0000\u0000"+
		"\u0156\u0158\u0005 \u0000\u0000\u0157\u0156\u0001\u0000\u0000\u0000\u0157"+
		"\u0158\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159"+
		"\u015a\u0003\u001a\r\u0000\u015a\u015e\u0005\u0010\u0000\u0000\u015b\u015d"+
		"\u0003\u0016\u000b\u0000\u015c\u015b\u0001\u0000\u0000\u0000\u015d\u0160"+
		"\u0001\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000\u015e\u015f"+
		"\u0001\u0000\u0000\u0000\u015f\u0162\u0001\u0000\u0000\u0000\u0160\u015e"+
		"\u0001\u0000\u0000\u0000\u0161\u0163\u00030\u0018\u0000\u0162\u0161\u0001"+
		"\u0000\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0001"+
		"\u0000\u0000\u0000\u0164\u0166\u0005\u0011\u0000\u0000\u0165\u0167\u0003"+
		"8\u001c\u0000\u0166\u0165\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000"+
		"\u0000\u0000\u01677\u0001\u0000\u0000\u0000\u0168\u0169\u0005\u000b\u0000"+
		"\u0000\u0169\u016d\u0005\u0010\u0000\u0000\u016a\u016c\u0003\u0016\u000b"+
		"\u0000\u016b\u016a\u0001\u0000\u0000\u0000\u016c\u016f\u0001\u0000\u0000"+
		"\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000"+
		"\u0000\u016e\u0171\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000\u0000"+
		"\u0000\u0170\u0172\u00030\u0018\u0000\u0171\u0170\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000"+
		"\u0173\u0174\u0005\u0011\u0000\u0000\u01749\u0001\u0000\u0000\u0000\u0175"+
		"\u0177\u0005\f\u0000\u0000\u0176\u0178\u0005 \u0000\u0000\u0177\u0176"+
		"\u0001\u0000\u0000\u0000\u0177\u0178\u0001\u0000\u0000\u0000\u0178\u0179"+
		"\u0001\u0000\u0000\u0000\u0179\u017a\u0003\u001a\r\u0000\u017a\u017e\u0005"+
		"\u0010\u0000\u0000\u017b\u017d\u0003\u0016\u000b\u0000\u017c\u017b\u0001"+
		"\u0000\u0000\u0000\u017d\u0180\u0001\u0000\u0000\u0000\u017e\u017c\u0001"+
		"\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0182\u0001"+
		"\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0181\u0183\u0003"+
		"0\u0018\u0000\u0182\u0181\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000"+
		"\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184\u0185\u0005\u0011"+
		"\u0000\u0000\u0185;\u0001\u0000\u0000\u0000\u0186\u018b\u0003\f\u0006"+
		"\u0000\u0187\u0188\u0005\r\u0000\u0000\u0188\u018a\u0003\f\u0006\u0000"+
		"\u0189\u0187\u0001\u0000\u0000\u0000\u018a\u018d\u0001\u0000\u0000\u0000"+
		"\u018b\u0189\u0001\u0000\u0000\u0000\u018b\u018c\u0001\u0000\u0000\u0000"+
		"\u018c=\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018e"+
		"\u0197\u0003&\u0013\u0000\u018f\u0197\u0003.\u0017\u0000\u0190\u0197\u0003"+
		"\u0012\t\u0000\u0191\u0197\u0003\u0014\n\u0000\u0192\u0197\u0005\u0007"+
		"\u0000\u0000\u0193\u0197\u0005\b\u0000\u0000\u0194\u0197\u0005#\u0000"+
		"\u0000\u0195\u0197\u0003\u001e\u000f\u0000\u0196\u018e\u0001\u0000\u0000"+
		"\u0000\u0196\u018f\u0001\u0000\u0000\u0000\u0196\u0190\u0001\u0000\u0000"+
		"\u0000\u0196\u0191\u0001\u0000\u0000\u0000\u0196\u0192\u0001\u0000\u0000"+
		"\u0000\u0196\u0193\u0001\u0000\u0000\u0000\u0196\u0194\u0001\u0000\u0000"+
		"\u0000\u0196\u0195\u0001\u0000\u0000\u0000\u0197\u01a5\u0001\u0000\u0000"+
		"\u0000\u0198\u01a1\u0005\r\u0000\u0000\u0199\u01a2\u0003&\u0013\u0000"+
		"\u019a\u01a2\u0003.\u0017\u0000\u019b\u01a2\u0003\u0012\t\u0000\u019c"+
		"\u01a2\u0003\u0014\n\u0000\u019d\u01a2\u0005\u0007\u0000\u0000\u019e\u01a2"+
		"\u0005\b\u0000\u0000\u019f\u01a2\u0005#\u0000\u0000\u01a0\u01a2\u0003"+
		"\u001e\u000f\u0000\u01a1\u0199\u0001\u0000\u0000\u0000\u01a1\u019a\u0001"+
		"\u0000\u0000\u0000\u01a1\u019b\u0001\u0000\u0000\u0000\u01a1\u019c\u0001"+
		"\u0000\u0000\u0000\u01a1\u019d\u0001\u0000\u0000\u0000\u01a1\u019e\u0001"+
		"\u0000\u0000\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a1\u01a0\u0001"+
		"\u0000\u0000\u0000\u01a2\u01a4\u0001\u0000\u0000\u0000\u01a3\u0198\u0001"+
		"\u0000\u0000\u0000\u01a4\u01a7\u0001\u0000\u0000\u0000\u01a5\u01a3\u0001"+
		"\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000\u0000\u0000\u01a6?\u0001\u0000"+
		"\u0000\u0000\u01a7\u01a5\u0001\u0000\u0000\u0000\u01a8\u01a9\u0007\u0003"+
		"\u0000\u0000\u01a9A\u0001\u0000\u0000\u0000-FHWYir\u0087\u0091\u009b\u009f"+
		"\u00ac\u00ae\u00ba\u00c6\u00d3\u00db\u00e6\u00eb\u00f4\u0107\u0112\u0117"+
		"\u011f\u0121\u0125\u012a\u0136\u0138\u013e\u0146\u014d\u014f\u0157\u015e"+
		"\u0162\u0166\u016d\u0171\u0177\u017e\u0182\u018b\u0196\u01a1\u01a5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}