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
import exception.FunctionAlreadyExistsException;
import utils.Pair;

public class Main {

	private static final String COMMENT = "Comment";
	private static final String DEFINITION = "Definition";
	private static final String VNAME_TYPE = "Vname_type";
	private static final String VNAME_TYPE_OPTIONAL = "Vname_type_optional";
	private static final String FUNCTION = "Function";
	private static final String STATEMENT = "Statement";
	private static final String ARGS_DEF = "Args_def";
	private static final String IF_STATEMENT = "If_statement";
	private static final String ELSE_STATEMENT = "Else_statement";
	private static final String CONDITIONAL_VALUES = "Conditions_values";
	private static final String OPERATOR = "Operator";
	private static final String RETURN = "Return_statement";
	private static final String BOOLEAN_EXPRESSION = "Boolean_expression";
	private static final String WHILE_STATEMENT = "While_statement";
	private static final String DECLARATION = "Declaration";
	private static final String VALUE = "Value";
	private static final String STRING_LIT = "String_lit";
	private static final String EXPR = "Expr";
	private static final String REFINEMENT = "Refinement";
	private static final String EXPRESSION_VALUE = "Expr_value";
	private static final String FUNCTION_CALL = "Function_call";
	private static final String EXPRESSION = "Expression";
	private static final String ARRAYS = "Arrays";
	private static final String POSITION = "Position";
	private static final String POS = "Pos";

	public static void main(String[] args) throws IOException, FunctionAlreadyExistsException {
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
					verify(new Context(), toMap(p));
					if(treeFlag) {
						//System.out.println(toMap(p));
					}
				} catch(IOException e) {
					System.out.println("File " + args[i] + " does not exist");
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void verify(Context context, Map<String, Object> map) throws FunctionAlreadyExistsException {
		String first = new ArrayList<>(map.keySet()).get(0);
		List<Map<String, Object>> prog = (List<Map<String, Object>>) map.get(first);
		for(Map<String, Object> p: prog) {
			List<String> setKeys = new ArrayList<>(p.keySet());
			String type = setKeys.get(0);
			if(type.equals(DEFINITION) || type.equals(STATEMENT)) {
				verify(context, p);

			} else if(type.equals(FUNCTION) || type.equals(DECLARATION)) {
				function(context, p, type);
			}
		}
		System.out.println("Teste");
	}

	private static Map<String, Object> toMap(ParseTree p) {
		Map<String, Object> map = new LinkedHashMap<>();
		traverse(p, map);
		return map;
	}

	private static void traverse(ParseTree tree, Map<String, Object> map) {
		boolean b = true;
		if(tree.getParent() != null) {
			String parent = tree.getParent().getClass().getSimpleName().replaceAll("Context$", "");
			if(parent.equals(EXPR) || parent.equals(POSITION)) {
				if(tree instanceof TerminalNodeImpl) {
					Token token = ((TerminalNodeImpl) tree).getSymbol();
					map.put(OPERATOR, token.getText());
					b = false;
				}
			}
		} 
		if(b) {
			List<Map<String, Object>> children = new ArrayList<>();
			String name = tree.getClass().getSimpleName().replaceAll("Context$", "");
			if(name.equals(COMMENT)) {
				StringBuilder bob = new StringBuilder();
				for (int i = 0; i < tree.getChildCount(); i++) {
					bob.append(tree.getChild(i).getText());
				}
				map.put(name, bob.toString());

			} else if(name.equals(VNAME_TYPE)) {
				map.put(name, children);
				Map<String, Object> nested = new LinkedHashMap<>();
				children.add(nested);

				nested.put("name", tree.getChild(0).getText());
				nested.put("type", tree.getChild(2).getChild(0).getText());
				if(tree.getChildCount() > 2) {
					if(tree.getChild(3) != null) {
						List<Map<String, Object>> refinement = new ArrayList<>();
						Map<String, Object> values = new LinkedHashMap<>();
						values.put("Variable", tree.getChild(3).getChild(1).getText());
						values.put("Operator", tree.getChild(3).getChild(2).getText());
						values.put("Value", tree.getChild(3).getChild(3).getText());

						refinement.add(values);
						nested.put("Refinement", refinement);						
					}
				}

			} else if(name.equals(RETURN)) {
				map.put(name, children);
				Map<String, Object> nested = new LinkedHashMap<>();
				children.add(nested);

				nested.put("return", tree.getChild(1).getText());			
			} else if(name.equals(EXPRESSION_VALUE)) {
				String nameChild = tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "");
				if(nameChild.equals(STRING_LIT)) {
					StringBuilder bob = new StringBuilder();
					for(int i = 0; i < tree.getChild(0).getChildCount(); i++) {
						bob.append(tree.getChild(0).getChild(i));
					}
					map.put(name, bob.toString());
				} else {
					map.put(name, tree.getChild(0).getChild(0));
				}

			} else if(name.equals(FUNCTION_CALL)) {
				List<Map<String, Object>> function = new ArrayList<>();
				Map<String, Object> values = new LinkedHashMap<>();
				values.put("function_name", tree.getChild(0).getText());
				List<Map<String, String>> parameters = new ArrayList<>();
				if(tree.getChildCount() > 4) {
					for(int j = 0; j < tree.getChild(2).getChildCount(); j++) {
						if(!(tree.getChild(2).getChild(j) instanceof TerminalNodeImpl)) {
							Map<String, String> p = new LinkedHashMap<>();
							String nameChild = tree.getChild(2).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
							String value = tree.getChild(2).getChild(j).getText();
							p.put("type", nameChild);
							p.put("value", value);
							parameters.add(p);
						}						
					}

				}

				values.put("parameters", parameters);
				function.add(values);
				map.put(FUNCTION_CALL, function);
			} else if(name.equals(EXPRESSION) && !(tree.getChild(0).getClass().getSimpleName().replaceAll("Context$", "").equals(FUNCTION_CALL) )) {
				map.put("value", tree.getChild(0).getText());
			} else if (name.equals(ARRAYS)) {
				String varName = tree.getChild(0).getText();
				int index = -1;
				if(tree.getChildCount() == 5) {
					index = 2;
				} else {
					index = 6;
				}
				String namedChild = tree.getChild(index).getClass().getSimpleName().replaceAll("Context$", "");
				if(namedChild.equals(POSITION)) {
					Map<String, Object> expr_map = new LinkedHashMap<>();
					for(int j = 0; j < tree.getChild(index).getChildCount(); j++) {
						String nameChildChild = tree.getChild(index).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
						if(nameChildChild.equals(POSITION)) {
							traverse(tree.getChild(index), expr_map);
						} else {
							expr_map.put(namedChild, tree.getChild(index).getChild(j).getText());
						}
					}
					expr_map.put("Variable", varName);
					children.add(expr_map);
					map.put(name, expr_map);
				}
			} else if(name.equals(POS)){
				map.put(name, tree.getChild(0).getText());
			} else {
				map.put(name, children);
				for (int i = 0; i < tree.getChildCount(); i++) {
					if(name.equals(FUNCTION) || name.equals(ARGS_DEF)) {
						if(tree.getChild(i).getText().length() > 1) {
							Map<String, Object> nested = new LinkedHashMap<>();
							children.add(nested);
							traverse(tree.getChild(i), nested);
						}
					} else if(name.equals(DECLARATION)) {
						if(!(tree.getChild(i) instanceof TerminalNodeImpl)) {
							Map<String, Object> nested = new LinkedHashMap<>();
							children.add(nested);
							traverse(tree.getChild(i), nested);
						}

					} else if(name.equals(BOOLEAN_EXPRESSION)) {
						if (!(tree instanceof TerminalNodeImpl)) {
							String nameChild = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");
							if(nameChild.equals(BOOLEAN_EXPRESSION) || nameChild.equals(CONDITIONAL_VALUES) || nameChild.equals(OPERATOR)) {
								Map<String, Object> nested = new LinkedHashMap<>();
								children.add(nested);
								traverse(tree.getChild(i), nested);
							}
						}

					} else if(name.equals(IF_STATEMENT) || name.equals(WHILE_STATEMENT) || name.equals(ELSE_STATEMENT)) {
						if(!(tree.getChild(i) instanceof TerminalNodeImpl)) {
							Map<String, Object> nested = new LinkedHashMap<>();
							children.add(nested);
							traverse(tree.getChild(i), nested);
						}

					} else if(name.equals(CONDITIONAL_VALUES)) {
						Map<String, Object> nested = new LinkedHashMap<>();
						children.add(nested);
						nested.put("cond_value", tree.getText());

					} else if(name.equals(OPERATOR)) {
						Map<String, Object> nested = new LinkedHashMap<>();
						children.add(nested);
						nested.put("operator", tree.getText());

					} else if(name.equals(VALUE)) {
						if(!(tree.getChild(i) instanceof TerminalNodeImpl)) {
							String nameChild = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");
							if(nameChild.equals(STRING_LIT)) {
								StringBuilder bob = new StringBuilder();
								for (int j = 0; j < tree.getChild(i).getChildCount(); j++) {
									bob.append(tree.getChild(i).getChild(j).getText());
								}
								Map<String, Object> nested = new LinkedHashMap<>();

								List<Map<String, Object>> value = new ArrayList<>();
								Map<String, Object> values = new LinkedHashMap<>();
								values.put("Value", bob.toString());
								values.put("Type", nameChild);
								value.add(values);

								children.add(nested);
								nested.put("Value", value);


							} else if(nameChild.equals(EXPR)){
								Map<String, Object> expr_map = new LinkedHashMap<>();
								for(int j = 0; j < tree.getChild(i).getChildCount(); j++) {
									String nameChildChild = tree.getChild(i).getChild(j).getClass().getSimpleName().replaceAll("Context$", "");
									if(nameChildChild.equals(EXPR)) {
										traverse(tree.getChild(i), expr_map);
									}
								}
								children.add(expr_map);

							} else if(nameChild.equals(VNAME_TYPE_OPTIONAL)) {
								map.put(name, children);
								Map<String, Object> nested = new LinkedHashMap<>();
								children.add(nested);

								nested.put("name", tree.getChild(i).getChild(0).getText());
								String nameChildChild = tree.getChild(i).getChild(1).getClass().getSimpleName().replaceAll("Context$", "");

								if(nameChildChild.equals(REFINEMENT)) {
									List<Map<String, Object>> refinement = new ArrayList<>();
									Map<String, Object> values = new LinkedHashMap<>();
									values.put("Variable", tree.getChild(0).getChild(1).getChild(1).getText());
									values.put("Operator", tree.getChild(0).getChild(1).getChild(2).getText());
									values.put("Value", tree.getChild(0).getChild(1).getChild(3).getText());

									refinement.add(values);
									nested.put("Refinement", refinement);	

								} else {
									nested.put("type", tree.getChild(0).getChild(2).getText());
								}								
							} else {
								String className = tree.getChild(i).getClass().getSimpleName().replaceAll("Context$", "");
								List<Map<String, Object>> value = new ArrayList<>();
								Map<String, Object> values = new LinkedHashMap<>();
								values.put("Value", tree.getChild(i).getText());
								values.put("Type", className);
								value.add(values);

								Map<String, Object> nested = new LinkedHashMap<>();
								children.add(nested);
								nested.put("Value", value);
							}

						}
					} else {
						Map<String, Object> nested = new LinkedHashMap<>();
						children.add(nested);
						traverse(tree.getChild(i), nested);
					}
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

	@SuppressWarnings("unchecked")
	private static void function(Context context, Map<String, Object> p, String type) throws FunctionAlreadyExistsException {
		List<Map<String, Object>> function = (List<Map<String, Object>>) p.get(type);
		Map<String, Object> f = (Map<String, Object>) function.get(0);
		List<Map<String, Object>> vnameType = (List<Map<String, Object>>) f.get(VNAME_TYPE);
		String name = (String) vnameType.get(0).get("name");
		if(!context.hasFunction(name)) {
			String returnType = (String) vnameType.get(0).get("type");
			List<Map<String, Object>> argsDef = (List<Map<String, Object>>) function.get(1).get(ARGS_DEF);
			List<String> argsType = new ArrayList<>();
			if(argsDef != null) {
				for(Map<String, Object> arg : argsDef) {
					List<Map<String, Object>> argNameType = (List<Map<String, Object>>) arg.get(VNAME_TYPE);
					String argType = (String) argNameType.get(0).get("type");
					argsType.add(argType);
				}
			}
			context.setFunction(name, new Pair<String, List<String>>(returnType, argsType));
			verify(context, p);
		} else {
			throw new FunctionAlreadyExistsException("Function with name " + name + " already exists");
		}
	}
}
