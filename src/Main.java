import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	private final static String PATH = "test" + File.separator;
	private static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Gson GSON = new Gson();

	public static void main(String[] args) throws IOException {
		boolean treeFlag = containsFlag(args, "--tree");
		for(int i = 0; i < args.length; i++) {
			if(!args[i].equals("--tree" )) {
				try {
					CharStream input = CharStreams.fromFileName(PATH + args[i]);
					GrammarLexer lexer = new GrammarLexer(input);

					CommonTokenStream tokens = new CommonTokenStream(lexer);
					GrammarParser parser = new GrammarParser(tokens);
					parser.setBuildParseTree(true);
					ParseTree p = parser.prog();
					if(treeFlag) {
						System.out.println(toJson(p));
					}
				} catch(IOException e) {
					System.out.println("File " + args[i] + " does not exist");
				}
			}
		}
	}

	public static String toJson(ParseTree tree) {
		return toJson(tree, true);
	}

	public static String toJson(ParseTree tree, boolean prettyPrint) {
		return prettyPrint ? PRETTY_PRINT_GSON.toJson(toMap(tree)) : GSON.toJson(toMap(tree));
	}

	private static Map<String, Object> toMap(ParseTree p) {
		Map<String, Object> map = new LinkedHashMap<>();
		traverse(p, map);
		return map;
	}

	private static void traverse(ParseTree tree, Map<String, Object> map) {
		if (tree instanceof TerminalNodeImpl) {
			Token token = ((TerminalNodeImpl) tree).getSymbol();
			map.put("type", token.getType());
			map.put("text", token.getText());
		}
		else {
			List<Map<String, Object>> children = new ArrayList<>();
			String name = tree.getClass().getSimpleName().replaceAll("Context$", "");
			map.put(Character.toLowerCase(name.charAt(0)) + name.substring(1), children);

			for (int i = 0; i < tree.getChildCount(); i++) {
				Map<String, Object> nested = new LinkedHashMap<>();
				children.add(nested);
				traverse(tree.getChild(i), nested);
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
