import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
	
	public static void main(String[] args) throws IOException {
		CharStream i = CharStreams.fromFileName("hello_world.sp");
		GrammarLexer lexer = new GrammarLexer(i);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GrammarParser parser = new GrammarParser(tokens);
		parser.setBuildParseTree(true);
		parser.addParseListener(new SplashListener());
		ParseTree p = parser.prog();
		System.out.println(p.toStringTree(parser));
	}
}
