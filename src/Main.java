import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import exception.CompilerException;
import exception.FunctionException;
import utils.Pair;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, CompilerException {
		boolean treeFlag = containsFlag(args, "--tree");
		for(int i = 0; i < args.length; i++) {
			if(!args[i].equals("--tree" )) {
				try {
					CharStream input = CharStreams.fromFileName(args[i]);
					GrammarLexer lexer = new GrammarLexer(input);

					CommonTokenStream tokens = new CommonTokenStream(lexer);
					GrammarParser parser = new GrammarParser(tokens);
					parser.setBuildParseTree(true);
					ParseTree p = parser.prog();
					sPlashParser splashParser = new sPlashParser();
					Map<String, Object> tree = splashParser.toJson(p, new LinkedHashMap<>());

					if(treeFlag) {
						System.out.println(tree);
					}
					Context c = new Context();
					addFunctions(c, tree);
					Verifier v = new Verifier();
					v.verify(c, (List<Map<String, Object>>) tree.get(Constant.STATEMENT));
					Emitter e = new Emitter();
					Compiler com = new Compiler(e, c, (List<Map<String, Object>>) tree.get(Constant.STATEMENT));
					com.compileToLLVM();
				} catch(IOException e) {
					System.out.println("File " + args[i] + " does not exist");
				}
			}
		}
	}
	

	@SuppressWarnings("unchecked")
	private static void addFunctions(Context context, Map<String, Object> map) throws CompilerException {
		if(map.get(Constant.STATEMENT) != null) {
			for(Map<String, Object> m : (List<Map<String, Object>>)map.get(Constant.STATEMENT)) {
				List<Map<String, Object>> func = (List<Map<String, Object>>)m.get(Constant.FUNCTION);
				if(func != null) {
					for(Map<String, Object> function : func) {
						String name = (String)function.get(Constant.NAME);
						Map<String, Object> returnType = ((List<Map<String, Object>>) function.get(Constant.RETURN_TYPE)).get(0);
						List<Map<String, Object>> params = (List<Map<String, Object>>)function.get(Constant.PARAMETERS);
						List<Map<String, Object>> paramsType = new ArrayList<>();
						for(Map<String, Object> param : params) {
							String type = (String) param.get(Constant.TYPE);
							Map<String, Object> paramTypeArray = new LinkedHashMap<>();
							paramTypeArray.put(Constant.TYPE, type);
							paramTypeArray.put(Constant.IS_ARRAY, (boolean)param.get(Constant.IS_ARRAY));
							paramsType.add(paramTypeArray);
						}
						if(context.hasFunction(name)) {
							//TODO: Instead of checking name check also parameters
							throw new FunctionException("Function with name: " + name + " already exists");
						}
						context.setFunction(name, new Pair<>(paramsType, returnType));
						addFunctions(context, function);
					}
				} else {
					List<Map<String, Object>> decl = (List<Map<String, Object>>)m.get(Constant.DECLARATION);
					if(decl != null) {
						for(Map<String, Object> declaration : decl) {
							String name = (String)declaration.get(Constant.NAME);
							Map<String, Object> returnType = ((List<Map<String, Object>>) declaration.get(Constant.RETURN_TYPE)).get(0);
							List<Map<String, Object>> params = (List<Map<String, Object>>)declaration.get(Constant.PARAMETERS);
							List<Map<String, Object>> paramsType = new ArrayList<>();
							for(Map<String, Object> param : params) {
								String type = (String) param.get(Constant.TYPE);
								Map<String, Object> paramTypeArray = new LinkedHashMap<>();
								paramTypeArray.put(Constant.TYPE, type);
								paramTypeArray.put(Constant.IS_ARRAY, (boolean)param.get(Constant.IS_ARRAY));
								paramsType.add(paramTypeArray);
							}
							if(context.hasFunction(name)) {
								//TODO: Instead of checking name check also parameters
								throw new FunctionException("Function with name: " + name + " already exists");
							}
							context.setFunction(name, new Pair<>(paramsType, returnType));
						}
					}
				}
			}
		}
		addPreludeFunctions(context);
	}

	private static void addPreludeFunctions(Context context) {
		Map<String, Object> returnType = new HashMap<>();
		returnType.put(Constant.TYPE, Constant.VOID);
		returnType.put(Constant.IS_ARRAY, false);
		List<Map<String, Object>> paramsType = new ArrayList<>();

		context.setFunction("printf", new Pair<>(paramsType, returnType));
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
