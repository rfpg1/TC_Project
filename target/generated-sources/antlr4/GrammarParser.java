// Generated from Grammar.g4 by ANTLR 4.12.0
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
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, VOID=2, DOUBLE=3, INT=4, BOOLEAN=5, FLOAT=6, STRING=7, TRUE=8, 
		FALSE=9, RETURN=10, IF_COND=11, ELSE_COND=12, WHILE_COND=13, COMMA=14, 
		LEFT_PAR=15, RIGHT_PAR=16, LEFT_BRACKET=17, RIGHT_BRAKCET=18, SEMICOLON=19, 
		LEFT_R=20, RIGHT_R=21, ASPAS=22, DOUBLE_POINTS=23, GET_ARRAY=24, EQUALS=25, 
		WHERE=26, STAR=27, NUMBER_INT=28, NUMBER_FLOAT=29, MATH_OPERATOR=30, BOOLEAN_OPERATOR=31, 
		CONDITIONAL_OPERATOR=32, NOT_OPERATOR=33, NEWLINE=34, SPACE=35, VARIABLE=36, 
		ANYCHAR=37;
	public static final int
		RULE_prog = 0, RULE_comment = 1, RULE_open_comment = 2, RULE_close_comment = 3, 
		RULE_reserved_words = 4, RULE_declaration = 5, RULE_vname_type = 6, RULE_type = 7, 
		RULE_refinement = 8, RULE_number = 9, RULE_string_lit = 10, RULE_statement = 11, 
		RULE_value = 12, RULE_boolean_expression = 13, RULE_conditions_values = 14, 
		RULE_expr = 15, RULE_expr_value = 16, RULE_vname_type_optional = 17, RULE_arrays = 18, 
		RULE_position = 19, RULE_pos = 20, RULE_expression = 21, RULE_function_call = 22, 
		RULE_return_statement = 23, RULE_definition = 24, RULE_function = 25, 
		RULE_if_statement = 26, RULE_else_statement = 27, RULE_while_statement = 28, 
		RULE_args_def = 29, RULE_args_value = 30, RULE_operator = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "comment", "open_comment", "close_comment", "reserved_words", 
			"declaration", "vname_type", "type", "refinement", "number", "string_lit", 
			"statement", "value", "boolean_expression", "conditions_values", "expr", 
			"expr_value", "vname_type_optional", "arrays", "position", "pos", "expression", 
			"function_call", "return_statement", "definition", "function", "if_statement", 
			"else_statement", "while_statement", "args_def", "args_value", "operator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'Void'", "'Double'", "'Int'", "'Boolean'", "'Float'", "'String'", 
			"'true'", "'false'", "'return'", "'if'", "'else'", "'while'", "','", 
			"'('", "')'", "'{'", "'}'", "';'", "'['", "']'", "'\"'", "':'", "'get_array'", 
			"'='", "'where'", "'*'", null, null, null, null, null, "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "VOID", "DOUBLE", "INT", "BOOLEAN", "FLOAT", "STRING", "TRUE", 
			"FALSE", "RETURN", "IF_COND", "ELSE_COND", "WHILE_COND", "COMMA", "LEFT_PAR", 
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
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 206964303872L) != 0)) {
				{
				setState(68);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(64);
					comment();
					}
					break;
				case 2:
					{
					setState(65);
					declaration();
					}
					break;
				case 3:
					{
					setState(66);
					definition();
					}
					break;
				case 4:
					{
					setState(67);
					statement();
					}
					break;
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
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
			setState(75);
			open_comment();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 214614146048L) != 0)) {
				{
				setState(85);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(76);
					match(ANYCHAR);
					}
					break;
				case 2:
					{
					setState(77);
					match(VARIABLE);
					}
					break;
				case 3:
					{
					setState(78);
					number();
					}
					break;
				case 4:
					{
					setState(79);
					operator();
					}
					break;
				case 5:
					{
					setState(80);
					reserved_words();
					}
					break;
				case 6:
					{
					setState(81);
					declaration();
					}
					break;
				case 7:
					{
					setState(82);
					definition();
					}
					break;
				case 8:
					{
					setState(83);
					statement();
					}
					break;
				case 9:
					{
					setState(84);
					comment();
					}
					break;
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
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
			setState(92);
			match(LEFT_PAR);
			setState(93);
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
			setState(95);
			match(STAR);
			setState(96);
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
			setState(98);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1207958528L) != 0)) ) {
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
			setState(100);
			vname_type();
			setState(101);
			match(LEFT_PAR);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABLE) {
				{
				setState(102);
				args_def();
				}
			}

			setState(105);
			match(RIGHT_PAR);
			setState(106);
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
			setState(108);
			match(VARIABLE);
			}
			setState(109);
			match(DOUBLE_POINTS);
			setState(110);
			type();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(111);
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
		public TerminalNode FLOAT() { return getToken(GrammarParser.FLOAT, 0); }
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
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(114);
				match(DOUBLE);
				}
				break;
			case 2:
				{
				setState(115);
				match(INT);
				}
				break;
			case 3:
				{
				setState(116);
				match(BOOLEAN);
				}
				break;
			case 4:
				{
				setState(117);
				match(FLOAT);
				}
				break;
			case 5:
				{
				setState(118);
				match(STRING);
				}
				break;
			case 6:
				{
				setState(119);
				match(VOID);
				}
				break;
			case 7:
				{
				setState(120);
				match(LEFT_R);
				setState(121);
				match(INT);
				setState(122);
				match(RIGHT_R);
				}
				break;
			case 8:
				{
				setState(131);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(123);
					match(LEFT_R);
					setState(124);
					match(DOUBLE);
					setState(125);
					match(RIGHT_R);
					}
					break;
				case 2:
					{
					{
					setState(126);
					match(LEFT_R);
					setState(127);
					match(LEFT_R);
					setState(128);
					match(INT);
					setState(129);
					match(RIGHT_R);
					setState(130);
					match(RIGHT_R);
					}
					}
					break;
				}
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
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(GrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(GrammarParser.VARIABLE, i);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public String_litContext string_lit() {
			return getRuleContext(String_litContext.class,0);
		}
		public TerminalNode TRUE() { return getToken(GrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GrammarParser.FALSE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(WHERE);
			{
			setState(136);
			match(VARIABLE);
			}
			setState(137);
			operator();
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(138);
				number();
				}
				break;
			case 2:
				{
				setState(139);
				string_lit();
				}
				break;
			case 3:
				{
				setState(140);
				match(TRUE);
				}
				break;
			case 4:
				{
				setState(141);
				match(FALSE);
				}
				break;
			case 5:
				{
				setState(142);
				match(VARIABLE);
				}
				break;
			case 6:
				{
				setState(143);
				expr();
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
			setState(146);
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
			setState(148);
			match(ASPAS);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 214479930112L) != 0)) {
				{
				setState(156);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANYCHAR:
					{
					setState(149);
					match(ANYCHAR);
					}
					break;
				case NUMBER_INT:
				case NUMBER_FLOAT:
					{
					setState(150);
					number();
					}
					break;
				case BOOLEAN_OPERATOR:
				case CONDITIONAL_OPERATOR:
					{
					setState(151);
					operator();
					}
					break;
				case VARIABLE:
					{
					setState(152);
					match(VARIABLE);
					}
					break;
				case TRUE:
					{
					setState(153);
					match(TRUE);
					}
					break;
				case FALSE:
					{
					setState(154);
					match(FALSE);
					}
					break;
				case MATH_OPERATOR:
					{
					setState(155);
					match(MATH_OPERATOR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(161);
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
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(163);
				expression();
				}
				break;
			case 2:
				{
				setState(164);
				value();
				}
				break;
			case 3:
				{
				setState(165);
				if_statement();
				}
				break;
			case 4:
				{
				setState(166);
				while_statement();
				}
				break;
			case 5:
				{
				setState(167);
				arrays();
				}
				break;
			case 6:
				{
				setState(168);
				declaration();
				}
				break;
			case 7:
				{
				setState(169);
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
			setState(172);
			vname_type_optional();
			setState(173);
			match(EQUALS);
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(174);
				function_call();
				}
				break;
			case 2:
				{
				setState(175);
				number();
				}
				break;
			case 3:
				{
				setState(176);
				string_lit();
				}
				break;
			case 4:
				{
				setState(177);
				match(VARIABLE);
				}
				break;
			case 5:
				{
				setState(178);
				expr();
				}
				break;
			case 6:
				{
				setState(179);
				match(TRUE);
				}
				break;
			case 7:
				{
				setState(180);
				match(FALSE);
				}
				break;
			}
			setState(183);
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
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(LEFT_PAR);
				setState(186);
				boolean_expression();
				setState(187);
				match(RIGHT_PAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				conditions_values();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(190);
				conditions_values();
				setState(191);
				operator();
				setState(192);
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
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				match(VARIABLE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				match(TRUE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(198);
				match(FALSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				number();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(200);
				function_call();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(201);
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
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(LEFT_PAR);
				setState(205);
				expr();
				setState(206);
				match(RIGHT_PAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				expr_value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(209);
				expr_value();
				setState(210);
				_la = _input.LA(1);
				if ( !(_la==STAR || _la==MATH_OPERATOR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(211);
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
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(215);
				number();
				}
				break;
			case 2:
				{
				setState(216);
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(217);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(220);
			match(VARIABLE);
			}
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLE_POINTS) {
				{
				setState(221);
				match(DOUBLE_POINTS);
				setState(222);
				type();
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
	public static class ArraysContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(GrammarParser.VARIABLE, 0); }
		public TerminalNode LEFT_R() { return getToken(GrammarParser.LEFT_R, 0); }
		public PositionContext position() {
			return getRuleContext(PositionContext.class,0);
		}
		public TerminalNode RIGHT_R() { return getToken(GrammarParser.RIGHT_R, 0); }
		public TerminalNode SEMICOLON() { return getToken(GrammarParser.SEMICOLON, 0); }
		public TerminalNode GET_ARRAY() { return getToken(GrammarParser.GET_ARRAY, 0); }
		public TerminalNode LEFT_PAR() { return getToken(GrammarParser.LEFT_PAR, 0); }
		public TerminalNode RIGHT_PAR() { return getToken(GrammarParser.RIGHT_PAR, 0); }
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
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				match(VARIABLE);
				setState(226);
				match(LEFT_R);
				setState(227);
				position();
				setState(228);
				match(RIGHT_R);
				setState(229);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(VARIABLE);
				setState(232);
				match(T__0);
				setState(233);
				match(GET_ARRAY);
				setState(234);
				match(LEFT_PAR);
				setState(235);
				match(RIGHT_PAR);
				setState(236);
				match(LEFT_R);
				setState(237);
				position();
				setState(238);
				match(RIGHT_R);
				setState(239);
				match(SEMICOLON);
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
		enterRule(_localctx, 38, RULE_position);
		int _la;
		try {
			setState(252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				pos();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				match(LEFT_PAR);
				setState(245);
				pos();
				setState(246);
				match(RIGHT_PAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				pos();
				setState(249);
				_la = _input.LA(1);
				if ( !(_la==STAR || _la==MATH_OPERATOR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(250);
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
		enterRule(_localctx, 40, RULE_pos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(254);
				match(NUMBER_INT);
				}
				break;
			case 2:
				{
				setState(255);
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(256);
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
		enterRule(_localctx, 42, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(259);
				function_call();
				setState(260);
				match(SEMICOLON);
				}
				break;
			case 2:
				{
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 206963736576L) != 0)) {
					{
					setState(265);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case NUMBER_INT:
					case NUMBER_FLOAT:
						{
						setState(262);
						number();
						}
						break;
					case VARIABLE:
						{
						setState(263);
						match(VARIABLE);
						}
						break;
					case ANYCHAR:
						{
						setState(264);
						match(ANYCHAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(269);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(270);
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
		enterRule(_localctx, 44, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(VARIABLE);
			setState(274);
			match(LEFT_PAR);
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69529010944L) != 0)) {
				{
				setState(275);
				args_value();
				}
			}

			setState(278);
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
		enterRule(_localctx, 46, RULE_return_statement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(RETURN);
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69529010944L) != 0)) {
				{
				setState(288);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(281);
					match(VARIABLE);
					}
					break;
				case 2:
					{
					setState(282);
					number();
					}
					break;
				case 3:
					{
					setState(283);
					string_lit();
					}
					break;
				case 4:
					{
					setState(284);
					match(TRUE);
					}
					break;
				case 5:
					{
					setState(285);
					match(FALSE);
					}
					break;
				case 6:
					{
					setState(286);
					function_call();
					}
					break;
				case 7:
					{
					setState(287);
					expr();
					}
					break;
				}
				}
			}

			setState(292);
			match(SEMICOLON);
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(293);
					comment();
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
		enterRule(_localctx, 48, RULE_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(299);
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
		enterRule(_localctx, 50, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			vname_type();
			setState(302);
			match(LEFT_PAR);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABLE) {
				{
				setState(303);
				args_def();
				}
			}

			setState(306);
			match(RIGHT_PAR);
			setState(307);
			match(LEFT_BRACKET);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 206964303872L) != 0)) {
				{
				setState(311);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(308);
					statement();
					}
					break;
				case 2:
					{
					setState(309);
					definition();
					}
					break;
				case 3:
					{
					setState(310);
					comment();
					}
					break;
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316);
			return_statement();
			setState(317);
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
		enterRule(_localctx, 52, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(IF_COND);
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT_OPERATOR) {
				{
				setState(320);
				match(NOT_OPERATOR);
				}
			}

			setState(323);
			boolean_expression();
			setState(324);
			match(LEFT_BRACKET);
			{
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 206964303872L) != 0)) {
				{
				{
				setState(325);
				statement();
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(331);
				return_statement();
				}
			}

			setState(334);
			match(RIGHT_BRAKCET);
			setState(336);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(335);
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
		enterRule(_localctx, 54, RULE_else_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(ELSE_COND);
			setState(339);
			match(LEFT_BRACKET);
			{
			setState(343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 206964303872L) != 0)) {
				{
				{
				setState(340);
				statement();
				}
				}
				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(347);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(346);
				return_statement();
				}
			}

			setState(349);
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
		enterRule(_localctx, 56, RULE_while_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(WHILE_COND);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT_OPERATOR) {
				{
				setState(352);
				match(NOT_OPERATOR);
				}
			}

			setState(355);
			boolean_expression();
			setState(356);
			match(LEFT_BRACKET);
			{
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 206964303872L) != 0)) {
				{
				{
				setState(357);
				statement();
				}
				}
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(363);
				return_statement();
				}
			}

			setState(366);
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
		enterRule(_localctx, 58, RULE_args_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			vname_type();
			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(369);
				match(COMMA);
				setState(370);
				vname_type();
				}
				}
				setState(375);
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
		enterRule(_localctx, 60, RULE_args_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(376);
				function_call();
				}
				break;
			case 2:
				{
				setState(377);
				number();
				}
				break;
			case 3:
				{
				setState(378);
				string_lit();
				}
				break;
			case 4:
				{
				setState(379);
				match(TRUE);
				}
				break;
			case 5:
				{
				setState(380);
				match(FALSE);
				}
				break;
			case 6:
				{
				setState(381);
				match(VARIABLE);
				}
				break;
			case 7:
				{
				setState(382);
				expr();
				}
				break;
			}
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(385);
				match(COMMA);
				setState(393);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(386);
					function_call();
					}
					break;
				case 2:
					{
					setState(387);
					number();
					}
					break;
				case 3:
					{
					setState(388);
					string_lit();
					}
					break;
				case 4:
					{
					setState(389);
					match(TRUE);
					}
					break;
				case 5:
					{
					setState(390);
					match(FALSE);
					}
					break;
				case 6:
					{
					setState(391);
					match(VARIABLE);
					}
					break;
				case 7:
					{
					setState(392);
					expr();
					}
					break;
				}
				}
				}
				setState(399);
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
		enterRule(_localctx, 62, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
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
		"\u0004\u0001%\u0193\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000E\b\u0000\n\u0000\f\u0000H\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001V\b\u0001"+
		"\n\u0001\f\u0001Y\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005h\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006q\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u0084\b\u0007\u0003\u0007\u0086\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0091\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u009d\b\n\n\n\f\n\u00a0\t\n\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00ab\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00b6\b\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u00c3\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u00cb\b\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u00d6\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u00db\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00e0"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00f2"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00fd\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0102\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u010a"+
		"\b\u0015\n\u0015\f\u0015\u010d\t\u0015\u0001\u0015\u0003\u0015\u0110\b"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0115\b\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0121\b\u0017\u0003"+
		"\u0017\u0123\b\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0127\b\u0017"+
		"\n\u0017\f\u0017\u012a\t\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0131\b\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0138\b\u0019\n\u0019\f\u0019"+
		"\u013b\t\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u0142\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a"+
		"\u0147\b\u001a\n\u001a\f\u001a\u014a\t\u001a\u0001\u001a\u0003\u001a\u014d"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0151\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u0156\b\u001b\n\u001b\f\u001b\u0159"+
		"\t\u001b\u0001\u001b\u0003\u001b\u015c\b\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u0162\b\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u0167\b\u001c\n\u001c\f\u001c\u016a\t\u001c\u0001"+
		"\u001c\u0003\u001c\u016d\b\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0005\u001d\u0174\b\u001d\n\u001d\f\u001d\u0177\t\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0003\u001e\u0180\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u018a\b\u001e\u0005\u001e\u018c\b\u001e\n\u001e\f\u001e\u018f\t\u001e"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u0128\u0000 \u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>\u0000\u0004\u0002\u0000\n\u001a\u001e\u001e\u0001\u0000"+
		"\u001c\u001d\u0002\u0000\u001b\u001b\u001e\u001e\u0001\u0000\u001f \u01db"+
		"\u0000F\u0001\u0000\u0000\u0000\u0002K\u0001\u0000\u0000\u0000\u0004\\"+
		"\u0001\u0000\u0000\u0000\u0006_\u0001\u0000\u0000\u0000\bb\u0001\u0000"+
		"\u0000\u0000\nd\u0001\u0000\u0000\u0000\fl\u0001\u0000\u0000\u0000\u000e"+
		"\u0085\u0001\u0000\u0000\u0000\u0010\u0087\u0001\u0000\u0000\u0000\u0012"+
		"\u0092\u0001\u0000\u0000\u0000\u0014\u0094\u0001\u0000\u0000\u0000\u0016"+
		"\u00aa\u0001\u0000\u0000\u0000\u0018\u00ac\u0001\u0000\u0000\u0000\u001a"+
		"\u00c2\u0001\u0000\u0000\u0000\u001c\u00ca\u0001\u0000\u0000\u0000\u001e"+
		"\u00d5\u0001\u0000\u0000\u0000 \u00da\u0001\u0000\u0000\u0000\"\u00dc"+
		"\u0001\u0000\u0000\u0000$\u00f1\u0001\u0000\u0000\u0000&\u00fc\u0001\u0000"+
		"\u0000\u0000(\u0101\u0001\u0000\u0000\u0000*\u010f\u0001\u0000\u0000\u0000"+
		",\u0111\u0001\u0000\u0000\u0000.\u0118\u0001\u0000\u0000\u00000\u012b"+
		"\u0001\u0000\u0000\u00002\u012d\u0001\u0000\u0000\u00004\u013f\u0001\u0000"+
		"\u0000\u00006\u0152\u0001\u0000\u0000\u00008\u015f\u0001\u0000\u0000\u0000"+
		":\u0170\u0001\u0000\u0000\u0000<\u017f\u0001\u0000\u0000\u0000>\u0190"+
		"\u0001\u0000\u0000\u0000@E\u0003\u0002\u0001\u0000AE\u0003\n\u0005\u0000"+
		"BE\u00030\u0018\u0000CE\u0003\u0016\u000b\u0000D@\u0001\u0000\u0000\u0000"+
		"DA\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DC\u0001\u0000\u0000"+
		"\u0000EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000"+
		"\u0000\u0000GI\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000IJ\u0005"+
		"\u0000\u0000\u0001J\u0001\u0001\u0000\u0000\u0000KW\u0003\u0004\u0002"+
		"\u0000LV\u0005%\u0000\u0000MV\u0005$\u0000\u0000NV\u0003\u0012\t\u0000"+
		"OV\u0003>\u001f\u0000PV\u0003\b\u0004\u0000QV\u0003\n\u0005\u0000RV\u0003"+
		"0\u0018\u0000SV\u0003\u0016\u000b\u0000TV\u0003\u0002\u0001\u0000UL\u0001"+
		"\u0000\u0000\u0000UM\u0001\u0000\u0000\u0000UN\u0001\u0000\u0000\u0000"+
		"UO\u0001\u0000\u0000\u0000UP\u0001\u0000\u0000\u0000UQ\u0001\u0000\u0000"+
		"\u0000UR\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UT\u0001\u0000"+
		"\u0000\u0000VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001"+
		"\u0000\u0000\u0000XZ\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000"+
		"Z[\u0003\u0006\u0003\u0000[\u0003\u0001\u0000\u0000\u0000\\]\u0005\u000f"+
		"\u0000\u0000]^\u0005\u001b\u0000\u0000^\u0005\u0001\u0000\u0000\u0000"+
		"_`\u0005\u001b\u0000\u0000`a\u0005\u0010\u0000\u0000a\u0007\u0001\u0000"+
		"\u0000\u0000bc\u0007\u0000\u0000\u0000c\t\u0001\u0000\u0000\u0000de\u0003"+
		"\f\u0006\u0000eg\u0005\u000f\u0000\u0000fh\u0003:\u001d\u0000gf\u0001"+
		"\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000"+
		"ij\u0005\u0010\u0000\u0000jk\u0005\u0013\u0000\u0000k\u000b\u0001\u0000"+
		"\u0000\u0000lm\u0005$\u0000\u0000mn\u0005\u0017\u0000\u0000np\u0003\u000e"+
		"\u0007\u0000oq\u0003\u0010\b\u0000po\u0001\u0000\u0000\u0000pq\u0001\u0000"+
		"\u0000\u0000q\r\u0001\u0000\u0000\u0000r\u0086\u0005\u0003\u0000\u0000"+
		"s\u0086\u0005\u0004\u0000\u0000t\u0086\u0005\u0005\u0000\u0000u\u0086"+
		"\u0005\u0006\u0000\u0000v\u0086\u0005\u0007\u0000\u0000w\u0086\u0005\u0002"+
		"\u0000\u0000xy\u0005\u0014\u0000\u0000yz\u0005\u0004\u0000\u0000z\u0086"+
		"\u0005\u0015\u0000\u0000{|\u0005\u0014\u0000\u0000|}\u0005\u0003\u0000"+
		"\u0000}\u0084\u0005\u0015\u0000\u0000~\u007f\u0005\u0014\u0000\u0000\u007f"+
		"\u0080\u0005\u0014\u0000\u0000\u0080\u0081\u0005\u0004\u0000\u0000\u0081"+
		"\u0082\u0005\u0015\u0000\u0000\u0082\u0084\u0005\u0015\u0000\u0000\u0083"+
		"{\u0001\u0000\u0000\u0000\u0083~\u0001\u0000\u0000\u0000\u0084\u0086\u0001"+
		"\u0000\u0000\u0000\u0085r\u0001\u0000\u0000\u0000\u0085s\u0001\u0000\u0000"+
		"\u0000\u0085t\u0001\u0000\u0000\u0000\u0085u\u0001\u0000\u0000\u0000\u0085"+
		"v\u0001\u0000\u0000\u0000\u0085w\u0001\u0000\u0000\u0000\u0085x\u0001"+
		"\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001"+
		"\u0000\u0000\u0000\u0086\u000f\u0001\u0000\u0000\u0000\u0087\u0088\u0005"+
		"\u001a\u0000\u0000\u0088\u0089\u0005$\u0000\u0000\u0089\u0090\u0003>\u001f"+
		"\u0000\u008a\u0091\u0003\u0012\t\u0000\u008b\u0091\u0003\u0014\n\u0000"+
		"\u008c\u0091\u0005\b\u0000\u0000\u008d\u0091\u0005\t\u0000\u0000\u008e"+
		"\u0091\u0005$\u0000\u0000\u008f\u0091\u0003\u001e\u000f\u0000\u0090\u008a"+
		"\u0001\u0000\u0000\u0000\u0090\u008b\u0001\u0000\u0000\u0000\u0090\u008c"+
		"\u0001\u0000\u0000\u0000\u0090\u008d\u0001\u0000\u0000\u0000\u0090\u008e"+
		"\u0001\u0000\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0011"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0007\u0001\u0000\u0000\u0093\u0013"+
		"\u0001\u0000\u0000\u0000\u0094\u009e\u0005\u0016\u0000\u0000\u0095\u009d"+
		"\u0005%\u0000\u0000\u0096\u009d\u0003\u0012\t\u0000\u0097\u009d\u0003"+
		">\u001f\u0000\u0098\u009d\u0005$\u0000\u0000\u0099\u009d\u0005\b\u0000"+
		"\u0000\u009a\u009d\u0005\t\u0000\u0000\u009b\u009d\u0005\u001e\u0000\u0000"+
		"\u009c\u0095\u0001\u0000\u0000\u0000\u009c\u0096\u0001\u0000\u0000\u0000"+
		"\u009c\u0097\u0001\u0000\u0000\u0000\u009c\u0098\u0001\u0000\u0000\u0000"+
		"\u009c\u0099\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000"+
		"\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000\u0000\u0000"+
		"\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000"+
		"\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0005\u0016\u0000\u0000\u00a2\u0015\u0001\u0000\u0000\u0000"+
		"\u00a3\u00ab\u0003*\u0015\u0000\u00a4\u00ab\u0003\u0018\f\u0000\u00a5"+
		"\u00ab\u00034\u001a\u0000\u00a6\u00ab\u00038\u001c\u0000\u00a7\u00ab\u0003"+
		"$\u0012\u0000\u00a8\u00ab\u0003\n\u0005\u0000\u00a9\u00ab\u0003\u0002"+
		"\u0001\u0000\u00aa\u00a3\u0001\u0000\u0000\u0000\u00aa\u00a4\u0001\u0000"+
		"\u0000\u0000\u00aa\u00a5\u0001\u0000\u0000\u0000\u00aa\u00a6\u0001\u0000"+
		"\u0000\u0000\u00aa\u00a7\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000"+
		"\u0000\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00ab\u0017\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0003\"\u0011\u0000\u00ad\u00b5\u0005\u0019\u0000"+
		"\u0000\u00ae\u00b6\u0003,\u0016\u0000\u00af\u00b6\u0003\u0012\t\u0000"+
		"\u00b0\u00b6\u0003\u0014\n\u0000\u00b1\u00b6\u0005$\u0000\u0000\u00b2"+
		"\u00b6\u0003\u001e\u000f\u0000\u00b3\u00b6\u0005\b\u0000\u0000\u00b4\u00b6"+
		"\u0005\t\u0000\u0000\u00b5\u00ae\u0001\u0000\u0000\u0000\u00b5\u00af\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b0\u0001\u0000\u0000\u0000\u00b5\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0005\u0013\u0000\u0000\u00b8\u0019\u0001"+
		"\u0000\u0000\u0000\u00b9\u00ba\u0005\u000f\u0000\u0000\u00ba\u00bb\u0003"+
		"\u001a\r\u0000\u00bb\u00bc\u0005\u0010\u0000\u0000\u00bc\u00c3\u0001\u0000"+
		"\u0000\u0000\u00bd\u00c3\u0003\u001c\u000e\u0000\u00be\u00bf\u0003\u001c"+
		"\u000e\u0000\u00bf\u00c0\u0003>\u001f\u0000\u00c0\u00c1\u0003\u001a\r"+
		"\u0000\u00c1\u00c3\u0001\u0000\u0000\u0000\u00c2\u00b9\u0001\u0000\u0000"+
		"\u0000\u00c2\u00bd\u0001\u0000\u0000\u0000\u00c2\u00be\u0001\u0000\u0000"+
		"\u0000\u00c3\u001b\u0001\u0000\u0000\u0000\u00c4\u00cb\u0005$\u0000\u0000"+
		"\u00c5\u00cb\u0005\b\u0000\u0000\u00c6\u00cb\u0005\t\u0000\u0000\u00c7"+
		"\u00cb\u0003\u0012\t\u0000\u00c8\u00cb\u0003,\u0016\u0000\u00c9\u00cb"+
		"\u0003\u001e\u000f\u0000\u00ca\u00c4\u0001\u0000\u0000\u0000\u00ca\u00c5"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c6\u0001\u0000\u0000\u0000\u00ca\u00c7"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00c9"+
		"\u0001\u0000\u0000\u0000\u00cb\u001d\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0005\u000f\u0000\u0000\u00cd\u00ce\u0003\u001e\u000f\u0000\u00ce\u00cf"+
		"\u0005\u0010\u0000\u0000\u00cf\u00d6\u0001\u0000\u0000\u0000\u00d0\u00d6"+
		"\u0003 \u0010\u0000\u00d1\u00d2\u0003 \u0010\u0000\u00d2\u00d3\u0007\u0002"+
		"\u0000\u0000\u00d3\u00d4\u0003\u001e\u000f\u0000\u00d4\u00d6\u0001\u0000"+
		"\u0000\u0000\u00d5\u00cc\u0001\u0000\u0000\u0000\u00d5\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d1\u0001\u0000\u0000\u0000\u00d6\u001f\u0001\u0000"+
		"\u0000\u0000\u00d7\u00db\u0003\u0012\t\u0000\u00d8\u00db\u0005$\u0000"+
		"\u0000\u00d9\u00db\u0003,\u0016\u0000\u00da\u00d7\u0001\u0000\u0000\u0000"+
		"\u00da\u00d8\u0001\u0000\u0000\u0000\u00da\u00d9\u0001\u0000\u0000\u0000"+
		"\u00db!\u0001\u0000\u0000\u0000\u00dc\u00df\u0005$\u0000\u0000\u00dd\u00de"+
		"\u0005\u0017\u0000\u0000\u00de\u00e0\u0003\u000e\u0007\u0000\u00df\u00dd"+
		"\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0#\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e2\u0005$\u0000\u0000\u00e2\u00e3\u0005\u0014"+
		"\u0000\u0000\u00e3\u00e4\u0003&\u0013\u0000\u00e4\u00e5\u0005\u0015\u0000"+
		"\u0000\u00e5\u00e6\u0005\u0013\u0000\u0000\u00e6\u00f2\u0001\u0000\u0000"+
		"\u0000\u00e7\u00e8\u0005$\u0000\u0000\u00e8\u00e9\u0005\u0001\u0000\u0000"+
		"\u00e9\u00ea\u0005\u0018\u0000\u0000\u00ea\u00eb\u0005\u000f\u0000\u0000"+
		"\u00eb\u00ec\u0005\u0010\u0000\u0000\u00ec\u00ed\u0005\u0014\u0000\u0000"+
		"\u00ed\u00ee\u0003&\u0013\u0000\u00ee\u00ef\u0005\u0015\u0000\u0000\u00ef"+
		"\u00f0\u0005\u0013\u0000\u0000\u00f0\u00f2\u0001\u0000\u0000\u0000\u00f1"+
		"\u00e1\u0001\u0000\u0000\u0000\u00f1\u00e7\u0001\u0000\u0000\u0000\u00f2"+
		"%\u0001\u0000\u0000\u0000\u00f3\u00fd\u0003(\u0014\u0000\u00f4\u00f5\u0005"+
		"\u000f\u0000\u0000\u00f5\u00f6\u0003(\u0014\u0000\u00f6\u00f7\u0005\u0010"+
		"\u0000\u0000\u00f7\u00fd\u0001\u0000\u0000\u0000\u00f8\u00f9\u0003(\u0014"+
		"\u0000\u00f9\u00fa\u0007\u0002\u0000\u0000\u00fa\u00fb\u0003&\u0013\u0000"+
		"\u00fb\u00fd\u0001\u0000\u0000\u0000\u00fc\u00f3\u0001\u0000\u0000\u0000"+
		"\u00fc\u00f4\u0001\u0000\u0000\u0000\u00fc\u00f8\u0001\u0000\u0000\u0000"+
		"\u00fd\'\u0001\u0000\u0000\u0000\u00fe\u0102\u0005\u001c\u0000\u0000\u00ff"+
		"\u0102\u0005$\u0000\u0000\u0100\u0102\u0003,\u0016\u0000\u0101\u00fe\u0001"+
		"\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0100\u0001"+
		"\u0000\u0000\u0000\u0102)\u0001\u0000\u0000\u0000\u0103\u0104\u0003,\u0016"+
		"\u0000\u0104\u0105\u0005\u0013\u0000\u0000\u0105\u0110\u0001\u0000\u0000"+
		"\u0000\u0106\u010a\u0003\u0012\t\u0000\u0107\u010a\u0005$\u0000\u0000"+
		"\u0108\u010a\u0005%\u0000\u0000\u0109\u0106\u0001\u0000\u0000\u0000\u0109"+
		"\u0107\u0001\u0000\u0000\u0000\u0109\u0108\u0001\u0000\u0000\u0000\u010a"+
		"\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0001\u0000\u0000\u0000\u010c\u010e\u0001\u0000\u0000\u0000\u010d"+
		"\u010b\u0001\u0000\u0000\u0000\u010e\u0110\u0005\u0013\u0000\u0000\u010f"+
		"\u0103\u0001\u0000\u0000\u0000\u010f\u010b\u0001\u0000\u0000\u0000\u0110"+
		"+\u0001\u0000\u0000\u0000\u0111\u0112\u0005$\u0000\u0000\u0112\u0114\u0005"+
		"\u000f\u0000\u0000\u0113\u0115\u0003<\u001e\u0000\u0114\u0113\u0001\u0000"+
		"\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000"+
		"\u0000\u0000\u0116\u0117\u0005\u0010\u0000\u0000\u0117-\u0001\u0000\u0000"+
		"\u0000\u0118\u0122\u0005\n\u0000\u0000\u0119\u0121\u0005$\u0000\u0000"+
		"\u011a\u0121\u0003\u0012\t\u0000\u011b\u0121\u0003\u0014\n\u0000\u011c"+
		"\u0121\u0005\b\u0000\u0000\u011d\u0121\u0005\t\u0000\u0000\u011e\u0121"+
		"\u0003,\u0016\u0000\u011f\u0121\u0003\u001e\u000f\u0000\u0120\u0119\u0001"+
		"\u0000\u0000\u0000\u0120\u011a\u0001\u0000\u0000\u0000\u0120\u011b\u0001"+
		"\u0000\u0000\u0000\u0120\u011c\u0001\u0000\u0000\u0000\u0120\u011d\u0001"+
		"\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000\u0120\u011f\u0001"+
		"\u0000\u0000\u0000\u0121\u0123\u0001\u0000\u0000\u0000\u0122\u0120\u0001"+
		"\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0124\u0001"+
		"\u0000\u0000\u0000\u0124\u0128\u0005\u0013\u0000\u0000\u0125\u0127\u0003"+
		"\u0002\u0001\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0127\u012a\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0128\u0126\u0001"+
		"\u0000\u0000\u0000\u0129/\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000"+
		"\u0000\u0000\u012b\u012c\u00032\u0019\u0000\u012c1\u0001\u0000\u0000\u0000"+
		"\u012d\u012e\u0003\f\u0006\u0000\u012e\u0130\u0005\u000f\u0000\u0000\u012f"+
		"\u0131\u0003:\u001d\u0000\u0130\u012f\u0001\u0000\u0000\u0000\u0130\u0131"+
		"\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0133"+
		"\u0005\u0010\u0000\u0000\u0133\u0139\u0005\u0011\u0000\u0000\u0134\u0138"+
		"\u0003\u0016\u000b\u0000\u0135\u0138\u00030\u0018\u0000\u0136\u0138\u0003"+
		"\u0002\u0001\u0000\u0137\u0134\u0001\u0000\u0000\u0000\u0137\u0135\u0001"+
		"\u0000\u0000\u0000\u0137\u0136\u0001\u0000\u0000\u0000\u0138\u013b\u0001"+
		"\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001"+
		"\u0000\u0000\u0000\u013a\u013c\u0001\u0000\u0000\u0000\u013b\u0139\u0001"+
		"\u0000\u0000\u0000\u013c\u013d\u0003.\u0017\u0000\u013d\u013e\u0005\u0012"+
		"\u0000\u0000\u013e3\u0001\u0000\u0000\u0000\u013f\u0141\u0005\u000b\u0000"+
		"\u0000\u0140\u0142\u0005!\u0000\u0000\u0141\u0140\u0001\u0000\u0000\u0000"+
		"\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000\u0000\u0000"+
		"\u0143\u0144\u0003\u001a\r\u0000\u0144\u0148\u0005\u0011\u0000\u0000\u0145"+
		"\u0147\u0003\u0016\u000b\u0000\u0146\u0145\u0001\u0000\u0000\u0000\u0147"+
		"\u014a\u0001\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0148"+
		"\u0149\u0001\u0000\u0000\u0000\u0149\u014c\u0001\u0000\u0000\u0000\u014a"+
		"\u0148\u0001\u0000\u0000\u0000\u014b\u014d\u0003.\u0017\u0000\u014c\u014b"+
		"\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014e"+
		"\u0001\u0000\u0000\u0000\u014e\u0150\u0005\u0012\u0000\u0000\u014f\u0151"+
		"\u00036\u001b\u0000\u0150\u014f\u0001\u0000\u0000\u0000\u0150\u0151\u0001"+
		"\u0000\u0000\u0000\u01515\u0001\u0000\u0000\u0000\u0152\u0153\u0005\f"+
		"\u0000\u0000\u0153\u0157\u0005\u0011\u0000\u0000\u0154\u0156\u0003\u0016"+
		"\u000b\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0156\u0159\u0001\u0000"+
		"\u0000\u0000\u0157\u0155\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000"+
		"\u0000\u0000\u0158\u015b\u0001\u0000\u0000\u0000\u0159\u0157\u0001\u0000"+
		"\u0000\u0000\u015a\u015c\u0003.\u0017\u0000\u015b\u015a\u0001\u0000\u0000"+
		"\u0000\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000"+
		"\u0000\u015d\u015e\u0005\u0012\u0000\u0000\u015e7\u0001\u0000\u0000\u0000"+
		"\u015f\u0161\u0005\r\u0000\u0000\u0160\u0162\u0005!\u0000\u0000\u0161"+
		"\u0160\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0162"+
		"\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0003\u001a\r\u0000\u0164\u0168"+
		"\u0005\u0011\u0000\u0000\u0165\u0167\u0003\u0016\u000b\u0000\u0166\u0165"+
		"\u0001\u0000\u0000\u0000\u0167\u016a\u0001\u0000\u0000\u0000\u0168\u0166"+
		"\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169\u016c"+
		"\u0001\u0000\u0000\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016b\u016d"+
		"\u0003.\u0017\u0000\u016c\u016b\u0001\u0000\u0000\u0000\u016c\u016d\u0001"+
		"\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u016f\u0005"+
		"\u0012\u0000\u0000\u016f9\u0001\u0000\u0000\u0000\u0170\u0175\u0003\f"+
		"\u0006\u0000\u0171\u0172\u0005\u000e\u0000\u0000\u0172\u0174\u0003\f\u0006"+
		"\u0000\u0173\u0171\u0001\u0000\u0000\u0000\u0174\u0177\u0001\u0000\u0000"+
		"\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000"+
		"\u0000\u0176;\u0001\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000"+
		"\u0178\u0180\u0003,\u0016\u0000\u0179\u0180\u0003\u0012\t\u0000\u017a"+
		"\u0180\u0003\u0014\n\u0000\u017b\u0180\u0005\b\u0000\u0000\u017c\u0180"+
		"\u0005\t\u0000\u0000\u017d\u0180\u0005$\u0000\u0000\u017e\u0180\u0003"+
		"\u001e\u000f\u0000\u017f\u0178\u0001\u0000\u0000\u0000\u017f\u0179\u0001"+
		"\u0000\u0000\u0000\u017f\u017a\u0001\u0000\u0000\u0000\u017f\u017b\u0001"+
		"\u0000\u0000\u0000\u017f\u017c\u0001\u0000\u0000\u0000\u017f\u017d\u0001"+
		"\u0000\u0000\u0000\u017f\u017e\u0001\u0000\u0000\u0000\u0180\u018d\u0001"+
		"\u0000\u0000\u0000\u0181\u0189\u0005\u000e\u0000\u0000\u0182\u018a\u0003"+
		",\u0016\u0000\u0183\u018a\u0003\u0012\t\u0000\u0184\u018a\u0003\u0014"+
		"\n\u0000\u0185\u018a\u0005\b\u0000\u0000\u0186\u018a\u0005\t\u0000\u0000"+
		"\u0187\u018a\u0005$\u0000\u0000\u0188\u018a\u0003\u001e\u000f\u0000\u0189"+
		"\u0182\u0001\u0000\u0000\u0000\u0189\u0183\u0001\u0000\u0000\u0000\u0189"+
		"\u0184\u0001\u0000\u0000\u0000\u0189\u0185\u0001\u0000\u0000\u0000\u0189"+
		"\u0186\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189"+
		"\u0188\u0001\u0000\u0000\u0000\u018a\u018c\u0001\u0000\u0000\u0000\u018b"+
		"\u0181\u0001\u0000\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d"+
		"\u018b\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e"+
		"=\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191"+
		"\u0007\u0003\u0000\u0000\u0191?\u0001\u0000\u0000\u0000,DFUWgp\u0083\u0085"+
		"\u0090\u009c\u009e\u00aa\u00b5\u00c2\u00ca\u00d5\u00da\u00df\u00f1\u00fc"+
		"\u0101\u0109\u010b\u010f\u0114\u0120\u0122\u0128\u0130\u0137\u0139\u0141"+
		"\u0148\u014c\u0150\u0157\u015b\u0161\u0168\u016c\u0175\u017f\u0189\u018d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}