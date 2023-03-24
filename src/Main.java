import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
	
	public static void main(String[] args) {
		CodePointCharStream input = CharStreams.fromString("(*    Comment \n OLA    *)");
		GrammarLexer lexer = new GrammarLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GrammarParser parser = new GrammarParser(tokens);
		parser.setBuildParseTree(true);
		parser.addParseListener(new SplashListener());
		ParseTree p = parser.prog();
		System.out.println(p.toStringTree(parser));
	}
}
