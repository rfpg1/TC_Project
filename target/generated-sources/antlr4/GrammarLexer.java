// Generated from Grammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "VOID", "DOUBLE", "INT", "BOOLEAN", "FLOAT", "STRING", "TRUE", 
			"FALSE", "RETURN", "IF_COND", "ELSE_COND", "WHILE_COND", "COMMA", "LEFT_PAR", 
			"RIGHT_PAR", "LEFT_BRACKET", "RIGHT_BRAKCET", "SEMICOLON", "LEFT_R", 
			"RIGHT_R", "ASPAS", "DOUBLE_POINTS", "GET_ARRAY", "EQUALS", "WHERE", 
			"STAR", "NUMBER_INT", "NUMBER_FLOAT", "MATH_OPERATOR", "BOOLEAN_OPERATOR", 
			"CONDITIONAL_OPERATOR", "NOT_OPERATOR", "NEWLINE", "SPACE", "VARIABLE", 
			"ANYCHAR"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000%\u00fe\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0004\u001b\u00bc\b\u001b\u000b\u001b\f\u001b\u00bd\u0001\u001c"+
		"\u0001\u001c\u0004\u001c\u00c2\b\u001c\u000b\u001c\f\u001c\u00c3\u0001"+
		"\u001c\u0004\u001c\u00c7\b\u001c\u000b\u001c\f\u001c\u00c8\u0001\u001c"+
		"\u0001\u001c\u0004\u001c\u00cd\b\u001c\u000b\u001c\f\u001c\u00ce\u0003"+
		"\u001c\u00d1\b\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u00de\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0003\u001f\u00e4\b\u001f\u0001 \u0001 \u0001!\u0004!\u00e9\b!"+
		"\u000b!\f!\u00ea\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001#"+
		"\u0001#\u0005#\u00f5\b#\n#\f#\u00f8\t#\u0001$\u0004$\u00fb\b$\u000b$\f"+
		"$\u00fc\u0001\u00fc\u0000%\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'"+
		"\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d"+
		";\u001e=\u001f? A!C\"E#G$I%\u0001\u0000\b\u0002\u000009__\u0001\u0000"+
		"09\u0004\u0000%%*+--//\u0002\u0000<<>>\u0002\u0000\n\n\r\r\u0002\u0000"+
		"\t\t  \u0003\u0000AZ__az\u0004\u000009AZ__az\u010a\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u0000"+
		"1\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001"+
		"\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000"+
		"\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000"+
		"?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001"+
		"\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000"+
		"\u0000\u0000I\u0001\u0000\u0000\u0000\u0001K\u0001\u0000\u0000\u0000\u0003"+
		"M\u0001\u0000\u0000\u0000\u0005R\u0001\u0000\u0000\u0000\u0007Y\u0001"+
		"\u0000\u0000\u0000\t]\u0001\u0000\u0000\u0000\u000be\u0001\u0000\u0000"+
		"\u0000\rk\u0001\u0000\u0000\u0000\u000fr\u0001\u0000\u0000\u0000\u0011"+
		"w\u0001\u0000\u0000\u0000\u0013}\u0001\u0000\u0000\u0000\u0015\u0084\u0001"+
		"\u0000\u0000\u0000\u0017\u0087\u0001\u0000\u0000\u0000\u0019\u008c\u0001"+
		"\u0000\u0000\u0000\u001b\u0092\u0001\u0000\u0000\u0000\u001d\u0094\u0001"+
		"\u0000\u0000\u0000\u001f\u0096\u0001\u0000\u0000\u0000!\u0098\u0001\u0000"+
		"\u0000\u0000#\u009a\u0001\u0000\u0000\u0000%\u009c\u0001\u0000\u0000\u0000"+
		"\'\u009e\u0001\u0000\u0000\u0000)\u00a0\u0001\u0000\u0000\u0000+\u00a2"+
		"\u0001\u0000\u0000\u0000-\u00a4\u0001\u0000\u0000\u0000/\u00a6\u0001\u0000"+
		"\u0000\u00001\u00b0\u0001\u0000\u0000\u00003\u00b2\u0001\u0000\u0000\u0000"+
		"5\u00b8\u0001\u0000\u0000\u00007\u00bb\u0001\u0000\u0000\u00009\u00d0"+
		"\u0001\u0000\u0000\u0000;\u00d2\u0001\u0000\u0000\u0000=\u00dd\u0001\u0000"+
		"\u0000\u0000?\u00e3\u0001\u0000\u0000\u0000A\u00e5\u0001\u0000\u0000\u0000"+
		"C\u00e8\u0001\u0000\u0000\u0000E\u00ee\u0001\u0000\u0000\u0000G\u00f2"+
		"\u0001\u0000\u0000\u0000I\u00fa\u0001\u0000\u0000\u0000KL\u0005.\u0000"+
		"\u0000L\u0002\u0001\u0000\u0000\u0000MN\u0005V\u0000\u0000NO\u0005o\u0000"+
		"\u0000OP\u0005i\u0000\u0000PQ\u0005d\u0000\u0000Q\u0004\u0001\u0000\u0000"+
		"\u0000RS\u0005D\u0000\u0000ST\u0005o\u0000\u0000TU\u0005u\u0000\u0000"+
		"UV\u0005b\u0000\u0000VW\u0005l\u0000\u0000WX\u0005e\u0000\u0000X\u0006"+
		"\u0001\u0000\u0000\u0000YZ\u0005I\u0000\u0000Z[\u0005n\u0000\u0000[\\"+
		"\u0005t\u0000\u0000\\\b\u0001\u0000\u0000\u0000]^\u0005B\u0000\u0000^"+
		"_\u0005o\u0000\u0000_`\u0005o\u0000\u0000`a\u0005l\u0000\u0000ab\u0005"+
		"e\u0000\u0000bc\u0005a\u0000\u0000cd\u0005n\u0000\u0000d\n\u0001\u0000"+
		"\u0000\u0000ef\u0005F\u0000\u0000fg\u0005l\u0000\u0000gh\u0005o\u0000"+
		"\u0000hi\u0005a\u0000\u0000ij\u0005t\u0000\u0000j\f\u0001\u0000\u0000"+
		"\u0000kl\u0005S\u0000\u0000lm\u0005t\u0000\u0000mn\u0005r\u0000\u0000"+
		"no\u0005i\u0000\u0000op\u0005n\u0000\u0000pq\u0005g\u0000\u0000q\u000e"+
		"\u0001\u0000\u0000\u0000rs\u0005t\u0000\u0000st\u0005r\u0000\u0000tu\u0005"+
		"u\u0000\u0000uv\u0005e\u0000\u0000v\u0010\u0001\u0000\u0000\u0000wx\u0005"+
		"f\u0000\u0000xy\u0005a\u0000\u0000yz\u0005l\u0000\u0000z{\u0005s\u0000"+
		"\u0000{|\u0005e\u0000\u0000|\u0012\u0001\u0000\u0000\u0000}~\u0005r\u0000"+
		"\u0000~\u007f\u0005e\u0000\u0000\u007f\u0080\u0005t\u0000\u0000\u0080"+
		"\u0081\u0005u\u0000\u0000\u0081\u0082\u0005r\u0000\u0000\u0082\u0083\u0005"+
		"n\u0000\u0000\u0083\u0014\u0001\u0000\u0000\u0000\u0084\u0085\u0005i\u0000"+
		"\u0000\u0085\u0086\u0005f\u0000\u0000\u0086\u0016\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0005e\u0000\u0000\u0088\u0089\u0005l\u0000\u0000\u0089\u008a"+
		"\u0005s\u0000\u0000\u008a\u008b\u0005e\u0000\u0000\u008b\u0018\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0005w\u0000\u0000\u008d\u008e\u0005h\u0000\u0000"+
		"\u008e\u008f\u0005i\u0000\u0000\u008f\u0090\u0005l\u0000\u0000\u0090\u0091"+
		"\u0005e\u0000\u0000\u0091\u001a\u0001\u0000\u0000\u0000\u0092\u0093\u0005"+
		",\u0000\u0000\u0093\u001c\u0001\u0000\u0000\u0000\u0094\u0095\u0005(\u0000"+
		"\u0000\u0095\u001e\u0001\u0000\u0000\u0000\u0096\u0097\u0005)\u0000\u0000"+
		"\u0097 \u0001\u0000\u0000\u0000\u0098\u0099\u0005{\u0000\u0000\u0099\""+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0005}\u0000\u0000\u009b$\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0005;\u0000\u0000\u009d&\u0001\u0000\u0000\u0000"+
		"\u009e\u009f\u0005[\u0000\u0000\u009f(\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0005]\u0000\u0000\u00a1*\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\""+
		"\u0000\u0000\u00a3,\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005:\u0000\u0000"+
		"\u00a5.\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005g\u0000\u0000\u00a7\u00a8"+
		"\u0005e\u0000\u0000\u00a8\u00a9\u0005t\u0000\u0000\u00a9\u00aa\u0005_"+
		"\u0000\u0000\u00aa\u00ab\u0005a\u0000\u0000\u00ab\u00ac\u0005r\u0000\u0000"+
		"\u00ac\u00ad\u0005r\u0000\u0000\u00ad\u00ae\u0005a\u0000\u0000\u00ae\u00af"+
		"\u0005y\u0000\u0000\u00af0\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005="+
		"\u0000\u0000\u00b12\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005w\u0000\u0000"+
		"\u00b3\u00b4\u0005h\u0000\u0000\u00b4\u00b5\u0005e\u0000\u0000\u00b5\u00b6"+
		"\u0005r\u0000\u0000\u00b6\u00b7\u0005e\u0000\u0000\u00b74\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0005*\u0000\u0000\u00b96\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bc\u0007\u0000\u0000\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bd\u00be\u0001\u0000\u0000\u0000\u00be8\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c1\u0005.\u0000\u0000\u00c0\u00c2\u0007\u0001\u0000\u0000\u00c1\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00d1"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c7\u0007\u0001\u0000\u0000\u00c6\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00ca"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cc\u0005.\u0000\u0000\u00cb\u00cd\u0007"+
		"\u0001\u0000\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001"+
		"\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d1\u0001\u0000\u0000\u0000\u00d0\u00bf\u0001"+
		"\u0000\u0000\u0000\u00d0\u00c6\u0001\u0000\u0000\u0000\u00d1:\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0007\u0002\u0000\u0000\u00d3<\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d5\u0005=\u0000\u0000\u00d5\u00de\u0005=\u0000\u0000\u00d6"+
		"\u00d7\u0005!\u0000\u0000\u00d7\u00de\u0005=\u0000\u0000\u00d8\u00d9\u0005"+
		">\u0000\u0000\u00d9\u00de\u0005=\u0000\u0000\u00da\u00db\u0005<\u0000"+
		"\u0000\u00db\u00de\u0005=\u0000\u0000\u00dc\u00de\u0007\u0003\u0000\u0000"+
		"\u00dd\u00d4\u0001\u0000\u0000\u0000\u00dd\u00d6\u0001\u0000\u0000\u0000"+
		"\u00dd\u00d8\u0001\u0000\u0000\u0000\u00dd\u00da\u0001\u0000\u0000\u0000"+
		"\u00dd\u00dc\u0001\u0000\u0000\u0000\u00de>\u0001\u0000\u0000\u0000\u00df"+
		"\u00e0\u0005&\u0000\u0000\u00e0\u00e4\u0005&\u0000\u0000\u00e1\u00e2\u0005"+
		"|\u0000\u0000\u00e2\u00e4\u0005|\u0000\u0000\u00e3\u00df\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4@\u0001\u0000\u0000\u0000"+
		"\u00e5\u00e6\u0005!\u0000\u0000\u00e6B\u0001\u0000\u0000\u0000\u00e7\u00e9"+
		"\u0007\u0004\u0000\u0000\u00e8\u00e7\u0001\u0000\u0000\u0000\u00e9\u00ea"+
		"\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ed"+
		"\u0006!\u0000\u0000\u00edD\u0001\u0000\u0000\u0000\u00ee\u00ef\u0007\u0005"+
		"\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f1\u0006\"\u0000"+
		"\u0000\u00f1F\u0001\u0000\u0000\u0000\u00f2\u00f6\u0007\u0006\u0000\u0000"+
		"\u00f3\u00f5\u0007\u0007\u0000\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f8\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7H\u0001\u0000\u0000\u0000\u00f8"+
		"\u00f6\u0001\u0000\u0000\u0000\u00f9\u00fb\t\u0000\u0000\u0000\u00fa\u00f9"+
		"\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd"+
		"\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fdJ\u0001"+
		"\u0000\u0000\u0000\u000b\u0000\u00bd\u00c3\u00c8\u00ce\u00d0\u00dd\u00e3"+
		"\u00ea\u00f6\u00fc\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}