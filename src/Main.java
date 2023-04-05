import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

	private final static String PATH = "test/";

	public static void main(String[] args) throws IOException {
		boolean treeFlag = containsFlag(args, "--tree");
		for(int i = 0; i < args.length; i++) {
			if(!args[i].equals("--tree" )) {
				CharStream input = CharStreams.fromFileName(PATH + args[i]);
				GrammarLexer lexer = new GrammarLexer(input);

				CommonTokenStream tokens = new CommonTokenStream(lexer);
				GrammarParser parser = new GrammarParser(tokens);
				parser.setBuildParseTree(true);
				parser.addParseListener(new SplashListener());
				ParseTree p = parser.prog();
				if(treeFlag) {
					System.out.println(p.toStringTree(parser));
				}
			}
		}
	}

	private static boolean containsFlag(String[] args, String flag) {
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals(flag)) {
				return true;
			}
		}
		return false;
	}
}
